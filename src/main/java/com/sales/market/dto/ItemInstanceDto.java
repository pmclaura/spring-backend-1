package com.sales.market.dto;

import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;

import java.math.BigDecimal;

public class ItemInstanceDto extends DtoBase<ItemInstance> {
    private ItemDto item;
    private String identifier;// sku
    private Boolean featured = Boolean.FALSE;
    private BigDecimal price;
    private ItemInstanceStatus itemInstanceStatus;

    public ItemInstanceStatus getItemInstanceStatus() {
        return itemInstanceStatus;
    }

    public void setItemInstanceStatus(ItemInstanceStatus itemInstanceStatus) {
        this.itemInstanceStatus = itemInstanceStatus;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
