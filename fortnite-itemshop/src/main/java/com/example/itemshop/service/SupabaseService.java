package com.example.itemshop.service;

import com.example.itemshop.dto.ItemDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class SupabaseService {

    private static final Logger log = LoggerFactory.getLogger(SupabaseService.class);

    // Hardcoded supabase settings (kept in code per project requirement)
    private static final String SUPABASE_REST_URL = "https://pwlmzsptxansbsdeplam.supabase.co/rest/v1/Items";
    private static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InB3bG16c3B0eGFuc2JzZGVwbGFtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjI3NTIyNzAsImV4cCI6MjA3ODMyODI3MH0.D2KOZ7Ekwlk5HkoQi2t3Q7nKsnGVYsK2LFAFKZvmC7A";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SupabaseService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<ItemDto> fetchItems() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("apikey", SUPABASE_ANON_KEY);
            headers.set("Authorization", "Bearer " + SUPABASE_ANON_KEY);
            headers.set("Accept", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> resp = restTemplate.exchange(SUPABASE_REST_URL + "?select=*", HttpMethod.GET, entity, String.class);
            if (!resp.getStatusCode().is2xxSuccessful() || resp.getBody() == null) {
                log.warn("Supabase returned non-OK or empty body: {}", resp.getStatusCode());
                return Collections.emptyList();
            }

            List<ItemDto> items = objectMapper.readValue(resp.getBody(), new TypeReference<List<ItemDto>>() {});
            log.info("Fetched {} items from Supabase", items.size());
            return items;
        } catch (Exception e) {
            log.error("Failed to fetch items from Supabase", e);
            return Collections.emptyList();
        }
    }
}
