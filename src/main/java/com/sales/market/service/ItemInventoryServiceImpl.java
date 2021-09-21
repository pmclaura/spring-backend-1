package com.sales.market.service;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.RoleType;
import com.sales.market.model.User;
import com.sales.market.repository.ItemInventoryRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService {
    private final ItemInventoryRepository repository;
    private final InventoryLowerBounderyService service;

    public ItemInventoryServiceImpl(ItemInventoryRepository repository, InventoryLowerBounderyService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    protected GenericRepository<ItemInventory> getRepository() {
        return repository;
    }

    @Override
    public List<ItemInventory> getItemsLowerBoundery() throws MessagingException {
        List<ItemInventory> listInventories = repository.geItemsLowerBoundery();
        service.sendEmailToSupervisor(listInventories);
        return listInventories;
    }
}
