package com.example.itemshop.dto;

public class ItemDto {
    private Long id;
    private String name;
    private String rarity;
    private Double price;
    private String imageUrl;
    private String description;

    public ItemDto() {}

    public ItemDto(Long id, String name, String rarity, Double price, String imageUrl, String description) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRarity() { return rarity; }
    public void setRarity(String rarity) { this.rarity = rarity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
