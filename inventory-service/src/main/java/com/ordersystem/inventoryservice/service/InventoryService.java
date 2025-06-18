package com.ordersystem.inventoryservice.service;

import com.ordersystem.inventoryservice.model.InventoryItem;
import com.ordersystem.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }

    public InventoryItem getItemById(String id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public InventoryItem createItem(InventoryItem item) {
        return inventoryRepository.save(item);
    }

    public InventoryItem updateItem(String id, InventoryItem item) {
        if (!inventoryRepository.existsById(id)) {
            throw new RuntimeException("Item not exists");
        }
        item.setId(id);

        return inventoryRepository.save(item);
    }

    public void deleteItem(String id) {
        inventoryRepository.deleteById(id);
    }

    public InventoryItem decreaseQuantity(String productId, int quantityToDecrease ) {
        InventoryItem item = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        
        int currentQuantity = item.getQuantity();
        if(quantityToDecrease  > currentQuantity ) {
            throw new RuntimeException("Not enough stock to decrease");
        }

        item.setQuantity(currentQuantity - quantityToDecrease);
        return inventoryRepository.save(item);
    }
}