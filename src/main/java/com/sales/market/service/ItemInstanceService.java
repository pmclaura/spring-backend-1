/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;
import com.sales.market.model.ItemInventory;
import com.sales.market.model.ItemInventoryEntry;

import java.math.BigDecimal;

public interface ItemInstanceService extends GenericService<ItemInstance> {
    void registerItemInstances(ItemInventory inventory, String[] skus, ItemInstanceStatus status, BigDecimal price);
    void updateItemInstances(String[] skus, ItemInstanceStatus status);

}
