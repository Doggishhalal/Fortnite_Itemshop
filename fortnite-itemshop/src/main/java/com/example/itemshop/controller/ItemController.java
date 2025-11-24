package com.example.itemshop.controller;

import com.example.itemshop.dto.ItemDto;
import com.example.itemshop.service.ItemStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemStoreService store;

    // default constructor for tests that instantiate controller directly
    public ItemController() {
        this(new com.example.itemshop.service.ItemStoreService());
    }

    public ItemController(ItemStoreService store) {
        this.store = store;
    }

    @GetMapping
    public List<ItemDto> list() {
        return store.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> get(@PathVariable Long id) {
        return store.get(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemDto> create(@RequestBody ItemDto input) {
        ItemDto created = store.add(input);
        return ResponseEntity.created(URI.create("/api/items/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> update(@PathVariable Long id, @RequestBody ItemDto input) {
        return store.update(id, input).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        store.delete(id);
        return ResponseEntity.noContent().build();
    }
}