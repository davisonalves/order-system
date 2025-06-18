package com.ordersystem.inventoryservice.controller;

import com.ordersystem.inventoryservice.model.InventoryItem;
import com.ordersystem.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryItem> getAllItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/{id}")
    public InventoryItem getItemById(@PathVariable String id) {
        return inventoryService.getItemById(id);
    }

    @PostMapping
    public InventoryItem createItem(@RequestBody InventoryItem item) {
        item.setId(null); // Ensure ID is null for new items
        return inventoryService.createItem(item);
    }

    @PutMapping("/{id}")
    public InventoryItem updateItem(@PathVariable String id, @RequestBody InventoryItem item) {        
        return inventoryService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        inventoryService.deleteItem(id);
    }

    @PutMapping("/decrease/{id}")
    public InventoryItem decreaseQuantity(@PathVariable String id, @RequestBody InventoryItem item) {
        return inventoryService.decreaseQuantity(id, item.getQuantity());
    }
}