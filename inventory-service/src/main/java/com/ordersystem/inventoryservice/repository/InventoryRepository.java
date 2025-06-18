package com.ordersystem.inventoryservice.repository;


import com.ordersystem.inventoryservice.model.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<InventoryItem, String> {
}
