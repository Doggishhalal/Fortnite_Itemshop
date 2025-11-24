package com.example.itemshop.loader;

import com.example.itemshop.dto.ItemDto;
import com.example.itemshop.service.ItemStoreService;
import com.example.itemshop.service.SupabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupabaseLoader {

    private static final Logger log = LoggerFactory.getLogger(SupabaseLoader.class);

    private final SupabaseService supabaseService;
    private final ItemStoreService itemStoreService;

    public SupabaseLoader(SupabaseService supabaseService, ItemStoreService itemStoreService) {
        this.supabaseService = supabaseService;
        this.itemStoreService = itemStoreService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        log.info("Application ready - loading items from Supabase...");
        try {
            List<ItemDto> items = supabaseService.fetchItems();
            if (items != null && !items.isEmpty()) {
                itemStoreService.addAll(items);
                log.info("Loaded {} items into local store", items.size());
            } else {
                log.info("No items fetched from Supabase");
            }
        } catch (Exception e) {
            log.error("Error while loading items from Supabase", e);
        }
    }
}
