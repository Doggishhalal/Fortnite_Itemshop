package com.example.itemshop.service;

import com.example.itemshop.dto.CartDto;
import com.example.itemshop.dto.CartItemDto;
import com.example.itemshop.dto.ItemDto;
import com.example.itemshop.model.Cart;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final Map<String, Cart> carts = new LinkedHashMap<>();
    private final ItemStoreService itemStoreService;

    public CartService(ItemStoreService itemStoreService) {
        this.itemStoreService = itemStoreService;
    }

    public String createCart() {
        String id = UUID.randomUUID().toString();
        carts.put(id, new Cart(id));
        return id;
    }

    public Optional<CartDto> getCart(String cartId) {
        Cart cart = carts.get(cartId);
        if (cart == null) return Optional.empty();
        return Optional.of(toDto(cart));
    }

    public Optional<CartDto> addItem(String cartId, Long itemId, int quantity) {
        if (quantity <= 0) return Optional.empty();
        Cart cart = carts.get(cartId);
        if (cart == null) return Optional.empty();
        // validate item exists
        Optional<ItemDto> opt = itemStoreService.get(itemId);
        if (!opt.isPresent()) return Optional.empty();
        cart.addItem(itemId, quantity);
        return Optional.of(toDto(cart));
    }

    public Optional<CartDto> updateItem(String cartId, Long itemId, int quantity) {
        Cart cart = carts.get(cartId);
        if (cart == null) return Optional.empty();
        if (quantity <= 0) {
            cart.removeItem(itemId);
        } else {
            // validate item exists
            Optional<ItemDto> opt = itemStoreService.get(itemId);
            if (!opt.isPresent()) return Optional.empty();
            cart.updateItem(itemId, quantity);
        }
        return Optional.of(toDto(cart));
    }

    public boolean removeItem(String cartId, Long itemId) {
        Cart cart = carts.get(cartId);
        if (cart == null) return false;
        cart.removeItem(itemId);
        return true;
    }

    public boolean clearCart(String cartId) {
        Cart cart = carts.get(cartId);
        if (cart == null) return false;
        cart.clear();
        return true;
    }

    public boolean deleteCart(String cartId) {
        return carts.remove(cartId) != null;
    }

    private CartDto toDto(Cart cart) {
        List<CartItemDto> items = cart.getItems().entrySet().stream().map(e -> {
            Long itemId = e.getKey();
            int qty = e.getValue();
            Optional<ItemDto> opt = itemStoreService.get(itemId);
            String name = opt.map(ItemDto::getName).orElse("<unknown>");
            Double price = opt.map(ItemDto::getVbucks).orElse(0.0);
            return new CartItemDto(itemId, name, price, qty);
        }).collect(Collectors.toList());
        CartDto dto = new CartDto(cart.getId(), items);
        dto.recalc();
        return dto;
    }
}
