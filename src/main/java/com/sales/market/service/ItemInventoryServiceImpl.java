package com.sales.market.service;

import com.sales.market.model.*;
import com.sales.market.repository.ItemInventoryRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService {
    private final ItemInventoryRepository repository;
    private final InventorySendEmailService serviceSendEmail;
    private final ItemService itemService;
    private final ItemInstanceService itemInstanceService;

    public ItemInventoryServiceImpl(ItemInventoryRepository repository, InventorySendEmailService serviceSendEmail, ItemService itemService, ItemInstanceService itemInstanceService) {
        this.repository = repository;
        this.serviceSendEmail = serviceSendEmail;
        this.itemService = itemService;
        this.itemInstanceService = itemInstanceService;
    }

    @Override
    protected GenericRepository<ItemInventory> getRepository() {
        return repository;
    }

    @Override
    public ItemInventory save (ItemInventory model){
        Item item = itemService.findById(model.getItem().getId());
        model.setItem(item);
        return super.save(model);
    }

    @Scheduled(cron = "0 25 23 * * ?", zone = "America/La_Paz")
    public List<ItemInventory> getItemsLowerAndUpperBoundery() throws MessagingException {
        List<ItemInventory> listItemsLower = repository.geItemsLowerBoundery();
        List<ItemInventory> listItemsUpper = repository.geItemsUpperBoundery();
        serviceSendEmail.sendEmailToSupervisorItemsLowerAndUpper(listItemsLower,listItemsUpper);
        return listItemsLower;
    }

    public ItemInventory updateStockQuantity(ItemInventory inventory, ItemInventoryEntry inventoryEntry) {
        String[] skus = inventoryEntry.getItemInstanceSkus().split(",");
        if(inventoryEntry.getMovementType() == MovementType.BUY){
            inventory.setStockQuantity(inventory.getStockQuantity().add(inventoryEntry.getQuantity()));
            inventory.setTotalPrice(inventory.getTotalPrice().add(inventoryEntry.getTotalCost()));
            BigDecimal price = inventoryEntry.getTotalCost().divide(inventoryEntry.getQuantity());
            itemInstanceService.registerItemInstances(inventory,skus,ItemInstanceStatus.AVAILABLE,price);
        }else if(inventoryEntry.getMovementType() == MovementType.SALE){
            inventory.setStockQuantity(inventory.getStockQuantity().subtract(inventoryEntry.getQuantity()));
            inventory.setTotalPrice(inventory.getTotalPrice().subtract(inventoryEntry.getTotalCost()));
            itemInstanceService.updateItemInstances(skus,ItemInstanceStatus.SOLD);
        }else if(inventoryEntry.getMovementType() == MovementType.REMOVED){
            inventory.setStockQuantity(inventory.getStockQuantity().subtract(inventoryEntry.getQuantity()));
            inventory.setTotalPrice(inventory.getTotalPrice().subtract(inventoryEntry.getTotalCost()));
            itemInstanceService.updateItemInstances(skus,ItemInstanceStatus.OUT);
        }
        return repository.save(inventory);
    }

}
