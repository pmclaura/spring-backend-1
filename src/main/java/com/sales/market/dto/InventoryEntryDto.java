package com.sales.market.dto;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.ItemInventoryEntry;
import com.sales.market.model.MovementType;

import java.math.BigDecimal;

public class InventoryEntryDto extends DtoBase<ItemInventoryEntry> {
    private ItemInventoryDto itemInventory;
    private MovementType movementType;
    private BigDecimal quantity;
    private String itemInstanceSkus;
    private BigDecimal totalCost;

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public ItemInventoryDto getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(ItemInventoryDto itemInventory) {
        this.itemInventory = itemInventory;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getItemInstanceSkus() {
        return itemInstanceSkus;
    }

    public void setItemInstanceSkus(String itemInstanceSkus) {
        this.itemInstanceSkus = itemInstanceSkus;
    }
}
