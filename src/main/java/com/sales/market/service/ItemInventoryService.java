package com.sales.market.service;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.ItemInventoryEntry;

import javax.mail.MessagingException;
import java.util.List;

public interface ItemInventoryService extends GenericService<ItemInventory> {
    List<ItemInventory> getItemsLowerAndUpperBoundery() throws MessagingException;

    ItemInventory updateStockQuantity(ItemInventory inventory, ItemInventoryEntry inventoryEntry);

}
