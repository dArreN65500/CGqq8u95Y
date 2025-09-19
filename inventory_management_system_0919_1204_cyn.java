// 代码生成时间: 2025-09-19 12:04:06
package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class InventoryManagementSystem {

    // In-memory map to simulate a database
    private final Map<String, InventoryItem> inventoryDatabase = new HashMap<>();

    // Constructor to initialize some inventory items
    public InventoryManagementSystem() {
        // Initialize inventory with some items
        inventoryDatabase.put("item1", new InventoryItem("item1", 100));
        inventoryDatabase.put("item2", new InventoryItem("item2", 150));
    }

    // GET endpoint to retrieve an inventory item by ID
    @GetMapping("/inventory/{itemId}")
    public InventoryItem getItemById(@PathVariable String itemId) {
        InventoryItem item = inventoryDatabase.get(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Item not found with ID: " + itemId);
        }
        return item;
    }

    // POST endpoint to add or update an inventory item
    @PostMapping("/inventory")
    public InventoryItem addOrUpdateItem(@RequestBody InventoryItem newItem) {
        if (inventoryDatabase.containsKey(newItem.getId())) {
            // Update existing item
            inventoryDatabase.get(newItem.getId()).setQuantity(newItem.getQuantity());
        } else {
            // Add new item
            inventoryDatabase.put(newItem.getId(), newItem);
        }
        return newItem;
    }

    // Inner class to represent an inventory item
    public static class InventoryItem {
        private String id;
        private int quantity;

        public InventoryItem(String id, int quantity) {
            this.id = id;
            this.quantity = quantity;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystem.class, args);
        System.out.println("Inventory Management System started!");
    }
}
