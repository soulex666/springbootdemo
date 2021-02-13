package com.andreev.springboot.controllers;

import com.andreev.springboot.entities.Category;
import com.andreev.springboot.entities.Country;
import com.andreev.springboot.entities.ShopItem;
import com.andreev.springboot.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/")
    public String index(Model model) {
        List<ShopItem> items = itemService.getAllItems();
        List<Country> countries = itemService.getAllCountries();
        model.addAttribute("tovari", items);
        model.addAttribute("countries", countries);

        return "index";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }

    @PostMapping(value = "/additem")
    public String addItem(@RequestParam(name = "item_name", defaultValue = "No item") String name,
                          @RequestParam(name = "item_price", defaultValue = "0") int price,
                          @RequestParam(name = "item_amount", defaultValue = "0") int amount,
                          @RequestParam(name = "country_id", defaultValue = "0L") Long countryId) {
        Country country = itemService.getCountryById(countryId);

        if(country != null) {
            ShopItem item = new ShopItem();
            item.setName(name);
            item.setCountry(country);
            item.setPrice(price);
            item.setAmount(amount);
            itemService.addItem(item);
        }


        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        List<Country> countries = itemService.getAllCountries();
        ShopItem item = itemService.getItem(id);
        model.addAttribute("item", item);
        model.addAttribute("countries", countries);

        List<Category> categories = itemService.getAllCategories();
        model.addAttribute("categories",categories);

        return "details";
    }

    @PostMapping(value = "/saveitem")
    public String saveItem(@RequestParam(name = "id", defaultValue = "No item") Long id,
                           @RequestParam(name = "item_name", defaultValue = "No item") String name,
                           @RequestParam(name = "item_price", defaultValue = "0") int price,
                           @RequestParam(name = "item_amount", defaultValue = "0") int amount,
                           @RequestParam(name = "country_id", defaultValue = "No item") Long countryId) {

        ShopItem item = itemService.getItem(id);
        if(item != null) {
            Country country = itemService.getCountryById(countryId);
            if(country != null) {
                item.setName(name);
                item.setPrice(price);
                item.setAmount(amount);
                item.setCountry(country);
                itemService.saveItem(item);
            }
        }

        return "redirect:/";
    }

    @PostMapping(value = "/deleteitem")
    public String deleteItemItem(@RequestParam(name = "id", defaultValue = "No item") Long id) {

        ShopItem item = itemService.getItem(id);
        if(item != null) {
            itemService.deleteItem(item);
        }

        return "redirect:/";
    }

    @PostMapping(value = "/assigncategory")
    public String assignCategory(@RequestParam(name = "item_id") Long itemId,
                                 @RequestParam(name = "category_id") Long categoryId) {

        Category category = itemService.getCategory(categoryId);

        if(category != null) {
            ShopItem item = itemService.getItem(itemId);

            if(item != null) {

                List<Category> categories = item.getCategories();
                if (categories == null) {
                    categories = new ArrayList<>();
                }
                categories.add(category);

                itemService.saveItem(item);

                return "redirect:/details/" + itemId;
            }
        }

        return "redirect:/";

    }
}
