package com.ordersystem.orderservice.client;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class InventoryClient {
    private RestClient inventoryClient;

    public InventoryClient(RestClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public boolean isInStock(String productId, int quantity) {
        InventoryItem response = inventoryClient.get()
                .uri("/api/inventory/{productId}", productId)
                .retrieve()
                .body(InventoryItem.class);

        return response != null && response.quantity() >= quantity;
    }

    public InventoryItem decreaseInventory(String productId, int quantityToDecrease) {
        Map<String, Integer> body = Map.of("quantity", quantityToDecrease);
        
        return inventoryClient.put()
                .uri("/api/inventory/decrease/{productId}", productId)
                .body(body)
                .retrieve()
                .body(InventoryItem.class);
    }

    public static record InventoryItem(String id, String product, int quantity) {}
}

