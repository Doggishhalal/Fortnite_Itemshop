package com.example.itemshop.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private String id;
    private Map<Long, Integer> items = new LinkedHashMap<>(); // itemId -> quantity

    public Cart() {}

    public Cart(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Long, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Long, Integer> items) {
        this.items = items;
    }

    public void addItem(Long itemId, int quantity) {
        if (quantity <= 0) return;
        items.merge(itemId, quantity, Integer::sum);
    }

    public void updateItem(Long itemId, int quantity) {
        if (quantity <= 0) {
            items.remove(itemId);
        } else {
            items.put(itemId, quantity);
        }
    }

    public void removeItem(Long itemId) {
        items.remove(itemId);
    }

    public void clear() {
        items.clear();
    }
}
