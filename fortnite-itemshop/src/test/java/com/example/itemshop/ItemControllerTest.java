package com.example.itemshop;

// No Mockito: tests exercise the in-memory controller directly
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.itemshop.controller.ItemController;
import com.example.itemshop.dto.ItemDto;
// controller-only tests, no service mocks
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// no Mockito
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// no Collections import needed

public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ItemController itemController;

    @BeforeEach
    public void setUp() {
        itemController = new ItemController();
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void testGetAllItems() throws Exception {
    // no items initially
    mockMvc.perform(get("/api/items")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void testGetItemById() throws Exception {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("Test Item");
        itemDto.setPrice(10.0);
        itemDto.setDescription("Test Description");
    // create the item first
    mockMvc.perform(post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Test Item\",\"price\":10.0,\"description\":\"Test Description\"}"))
            .andExpect(status().isCreated());

    mockMvc.perform(get("/api/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Item"));
    }

    @Test
    public void testCreateItem() throws Exception {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("New Item");
        itemDto.setPrice(15.0);
        itemDto.setDescription("New Description");

    mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Item\",\"price\":15.0,\"description\":\"New Description\"}"))
        .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateItem() throws Exception {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("Updated Item");
        itemDto.setPrice(20.0);
        itemDto.setDescription("Updated Description");

    // create first
    mockMvc.perform(post("/api/items")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Updated Item\",\"price\":20.0,\"description\":\"Updated Description\"}"))
        .andExpect(status().isCreated());

    mockMvc.perform(put("/api/items/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Updated Item\",\"price\":20.0,\"description\":\"Updated Description\"}"))
        .andExpect(status().isOk());
    }

    @Test
    public void testDeleteItem() throws Exception {
    // create an item first
    mockMvc.perform(post("/api/items")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"ToDelete\",\"price\":5.0,\"description\":\"Delete me\"}"))
        .andExpect(status().isCreated());

    mockMvc.perform(delete("/api/items/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }
}