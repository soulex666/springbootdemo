package com.andreev.springboot.controllers;

import com.andreev.springboot.dbrepository.DBManager;
import com.andreev.springboot.entity.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model) {
        List<Item> items = DBManager.getItems();
        model.addAttribute("tovari", items);
        return "index";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }

    @PostMapping(value = "/additem")
    public String addItem(@RequestParam(name = "item_name", defaultValue = "No item") String name,
                          @RequestParam(name = "item_price", defaultValue = "0") int price) {
        DBManager.addItem(new Item(null, name, price));
        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        Item item = DBManager.getItem(id);
        model.addAttribute("item", item);
        return "details";
    }
}
