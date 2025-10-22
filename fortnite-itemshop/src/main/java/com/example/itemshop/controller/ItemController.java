package com.example.itemshop.controller;

import com.example.itemshop.dto.ItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final Map<Long, ItemDto> store = new LinkedHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @GetMapping
    public List<ItemDto> list() {
        return new ArrayList<>(store.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> get(@PathVariable Long id) {
        ItemDto dto = store.get(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ItemDto> create(@RequestBody ItemDto input) {
        Long id = idGen.getAndIncrement();
        input.setId(id);
        store.put(id, input);
        return ResponseEntity.created(URI.create("/api/items/" + id)).body(input);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> update(@PathVariable Long id, @RequestBody ItemDto input) {
        if (!store.containsKey(id)) return ResponseEntity.notFound().build();
        input.setId(id);
        store.put(id, input);
        return ResponseEntity.ok(input);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        store.remove(id);
        return ResponseEntity.noContent().build();
    }
}