package com.sales.market.service;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.ItemInventoryEntry;
import com.sales.market.repository.InventoryEntryRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryEntryServiceImpl extends GenericServiceImpl<ItemInventoryEntry> implements InventoryEntryService {
    private final InventoryEntryRepository repository;
    private final ItemInventoryService inventoryService;

    public InventoryEntryServiceImpl(InventoryEntryRepository repository, ItemInventoryService inventoryService) {
        this.repository = repository;
        this.inventoryService = inventoryService;
    }

    @Override
    protected GenericRepository<ItemInventoryEntry> getRepository() {
        return repository;
    }

    @Override
    public ItemInventoryEntry save(ItemInventoryEntry model){
        ItemInventory inventory =  inventoryService.findById(model.getItemInventory().getId());
        model.setItemInventory(inventory);
        super.save(model);
        inventoryService.updateStockQuantity(inventory,model);
        return model;
    }
}
