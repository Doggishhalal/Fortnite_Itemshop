package com.example.itemshop.service;

import com.example.itemshop.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemStoreService {

    private final Map<Long, ItemDto> store = new LinkedHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public synchronized List<ItemDto> list() {
        return new ArrayList<>(store.values());
    }

    // For homepage rendering
    public synchronized List<ItemDto> getAllItems() {
        return list();
    }

    public synchronized Optional<ItemDto> get(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public synchronized ItemDto add(ItemDto dto) {
        Long provided = dto.getId();
        long idVal;
        if (provided == null) {
            idVal = idGen.getAndIncrement();
        } else {
            idVal = provided;
            // ensure idGen doesn't conflict with provided ids
            final long captured = idVal;
            idGen.updateAndGet(curr -> Math.max(curr, captured + 1));
        }
        dto.setId(idVal);
        store.put(idVal, dto);
        return dto;
    }

    public synchronized Optional<ItemDto> update(Long id, ItemDto dto) {
        if (!store.containsKey(id)) return Optional.empty();
        dto.setId(id);
        store.put(id, dto);
        return Optional.of(dto);
    }

    public synchronized void delete(Long id) {
        store.remove(id);
    }

    public synchronized void addAll(Collection<ItemDto> items) {
        if (items == null) return;
        for (ItemDto it : items) add(it);
    }
}
