package com.ordersystem.orderservice.service;

import com.ordersystem.orderservice.client.InventoryClient;
import com.ordersystem.orderservice.model.Order;
import com.ordersystem.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryClient inventoryClient;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        boolean inStock = inventoryClient.isInStock(order.getProductId(), order.getQuantity());

        if (!inStock) {
            throw new RuntimeException("Product is out of stock");
        }

        // Decrease inventory for the product
        inventoryClient.decreaseInventory(order.getProductId(), order.getQuantity());

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}