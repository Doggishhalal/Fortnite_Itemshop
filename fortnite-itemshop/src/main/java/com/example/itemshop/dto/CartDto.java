package com.example.itemshop.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private String id;
    private List<CartItemDto> items = new ArrayList<>();
    private Double total = 0.0;

    public CartDto() {}

    public CartDto(String id, List<CartItemDto> items) {
        this.id = id;
        this.items = items;
        recalc();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public List<CartItemDto> getItems() { return items; }
    public void setItems(List<CartItemDto> items) { this.items = items; recalc(); }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public void recalc() {
        this.total = items.stream().mapToDouble(i -> i.getSubtotal() == null ? 0.0 : i.getSubtotal()).sum();
    }
}
