package com.example.itemshop.dto;

public class CartItemDto {
    private Long itemId;
    private String name;
    private Double price;
    private int quantity;
    private Double subtotal;

    public CartItemDto() {}

    public CartItemDto(Long itemId, String name, Double price, int quantity) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = price == null ? 0.0 : price * quantity;
    }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; this.subtotal = (price == null ? 0.0 : price * quantity); }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}
