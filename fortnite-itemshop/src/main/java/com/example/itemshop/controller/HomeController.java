package com.example.itemshop.controller;

import com.example.itemshop.dto.ItemDto;
import com.example.itemshop.service.ItemStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ItemStoreService itemStoreService;

    @GetMapping("/")
    public String showItemShop(Model model) {
        List<ItemDto> items = itemStoreService.getAllItems();
        model.addAttribute("items", items);
        return "itemshop";
    }
}
