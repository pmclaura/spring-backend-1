package com.sales.market.controller;

import com.sales.market.dto.ItemInventoryDto;
import com.sales.market.model.ItemInventory;
import com.sales.market.service.ItemInventoryService;
import com.sales.market.service.GenericService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/iteminventorys")
public class ItemInventoryController extends GenericController<ItemInventory, ItemInventoryDto> {
    private ItemInventoryService service;

    public ItemInventoryController(ItemInventoryService service) {
        this.service = service;
    }

    @GetMapping("/itemsLowerAndUpper")
    public List<ItemInventoryDto> getItemslowerBoundThreshold() throws MessagingException {
        List<ItemInventory> itemInventories = service.getItemsLowerAndUpperBoundery();
        return new ItemInventoryDto().toListDto(itemInventories, modelMapper);
    }

    @Override
    protected GenericService getService() {
        return service;
    }
}
