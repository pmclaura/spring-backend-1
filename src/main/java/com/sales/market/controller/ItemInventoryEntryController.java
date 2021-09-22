package com.sales.market.controller;

import com.sales.market.dto.InventoryEntryDto;
import com.sales.market.model.ItemInventoryEntry;
import com.sales.market.service.GenericService;
import com.sales.market.service.InventoryEntryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/iteminventoryentrys")
public class ItemInventoryEntryController extends GenericController<ItemInventoryEntry, InventoryEntryDto> {
    private InventoryEntryService service;

    public ItemInventoryEntryController(InventoryEntryService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }
}
