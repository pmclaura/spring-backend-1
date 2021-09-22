/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.*;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInstanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemInstanceServiceImpl extends GenericServiceImpl<ItemInstance> implements ItemInstanceService {
    private final ItemInstanceRepository repository;
    private final ItemService itemService;

    public ItemInstanceServiceImpl(ItemInstanceRepository repository, ItemService itemService) {
        this.repository = repository;
        this.itemService = itemService;
    }

    @Override
    protected GenericRepository<ItemInstance> getRepository() {
        return repository;
    }

    public void registerItemInstances(ItemInventory inventory, String[] skus, ItemInstanceStatus status, BigDecimal price){
        Item item = itemService.findById(inventory.getItem().getId());
        List<ItemInstance> instances = new ArrayList<>();
        for(String sku: skus){
            ItemInstance itemInstance = new ItemInstance();
            itemInstance.setItem(item);
            itemInstance.setIdentifier(sku.trim());
            itemInstance.setItemInstanceStatus(status);
            itemInstance.setPrice(price);
            instances.add(itemInstance);
        }
        repository.saveAll(instances);
    }

    public void updateItemInstances(String[] skus, ItemInstanceStatus status){
        List<ItemInstance> instances = new ArrayList<>();
        for(String sku: skus){
            ItemInstance itemInstance = repository.findByIdentifier(sku.trim());
            itemInstance.setItemInstanceStatus(status);
            instances.add(itemInstance);
        }
        repository.saveAll(instances);
    }

    @Override
    public ItemInstance bunchSave(ItemInstance itemInstance) {
        // here make all objects save other than this resource
        if (itemInstance.getItem() != null) {
            // todo habria que distinguir si permitiremos guardar y  actualizar o ambos mitando el campo id
            itemService.save(itemInstance.getItem());
        }
        return super.bunchSave(itemInstance);
    }
}
