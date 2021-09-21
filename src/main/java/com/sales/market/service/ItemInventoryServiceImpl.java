package com.sales.market.service;
import com.sales.market.model.ItemInventory;
import com.sales.market.repositories.ItemInventoryRepository;
import com.sales.market.repositories.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService{
    private final ItemInventoryRepository repository;

    public ItemInventoryServiceImpl(ItemInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<ItemInventory> getRepository() {
        return repository;
    }
}
