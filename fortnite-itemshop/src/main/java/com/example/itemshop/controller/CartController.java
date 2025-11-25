package com.example.itemshop.controller;

import com.example.itemshop.dto.CartDto;
import com.example.itemshop.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createCart() {
        String id = cartService.createCart();
        return ResponseEntity.created(URI.create("/api/carts/" + id)).body(Map.of("cartId", id));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String cartId) {
        return cartService.getCart(cartId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartDto> addItem(@PathVariable String cartId, @RequestBody Map<String, Object> body) {
        Object itemIdObj = body.get("itemId");
        Object qtyObj = body.getOrDefault("quantity", 1);
        if (itemIdObj == null) return ResponseEntity.badRequest().build();
        Long itemId = ((Number) itemIdObj).longValue();
        int qty = ((Number) qtyObj).intValue();
        return cartService.addItem(cartId, itemId, qty).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartDto> updateItem(@PathVariable String cartId, @PathVariable Long itemId, @RequestBody Map<String, Object> body) {
        Object qtyObj = body.get("quantity");
        if (qtyObj == null) return ResponseEntity.badRequest().build();
        int qty = ((Number) qtyObj).intValue();
        return cartService.updateItem(cartId, itemId, qty).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable String cartId, @PathVariable Long itemId) {
        boolean ok = cartService.removeItem(cartId, itemId);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> clearCart(@PathVariable String cartId) {
        boolean ok = cartService.clearCart(cartId);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
