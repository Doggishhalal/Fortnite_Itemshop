package com.example.itemshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
public class ItemDto {
        // For template compatibility
        public Double getPrice() { return vbucks; }
        public String getImageUrl() { return picture; }
    @JsonProperty("id")
    private Long id;

    @JsonProperty("category_title")
    private String categoryTitle;

    @JsonProperty("name")
    private String name;

    @JsonProperty("vbucks")
    private Double vbucks;

    @JsonProperty("type")
    private String type;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("created_at")
    private String createdAt;

    public ItemDto() {}

    public ItemDto(Long id, String categoryTitle, String name, Double vbucks, String type, String picture, String createdAt) {
        this.id = id;
        this.categoryTitle = categoryTitle;
        this.name = name;
        this.vbucks = vbucks;
        this.type = type;
        this.picture = picture;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategoryTitle() { return categoryTitle; }
    public void setCategoryTitle(String categoryTitle) { this.categoryTitle = categoryTitle; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getVbucks() { return vbucks; }
    public void setVbucks(Double vbucks) { this.vbucks = vbucks; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
