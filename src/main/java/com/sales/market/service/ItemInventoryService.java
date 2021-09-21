package com.sales.market.service;

import com.sales.market.model.ItemInventory;

import javax.mail.MessagingException;
import java.util.List;

public interface ItemInventoryService extends GenericService<ItemInventory> {
    List<ItemInventory> getItemsLowerBoundery() throws MessagingException;
}
