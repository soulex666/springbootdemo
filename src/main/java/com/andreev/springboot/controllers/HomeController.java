package com.andreev.springboot.controllers;

import com.andreev.springboot.entities.Category;
import com.andreev.springboot.entities.Country;
import com.andreev.springboot.entities.ShopItem;
import com.andreev.springboot.services.ItemService;
import com.andreev.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String index(Model model) {
        List<ShopItem> items = itemService.getAllItems();
        List<Country> countries = itemService.getAllCountries();
        model.addAttribute("tovari", items);
        model.addAttribute("countries", countries);

        model.addAttribute("currentUser", getUserData());

        return "index";

    }

    @GetMapping(value = "/about")
    public String about(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "about";
    }

    @PostMapping(value = "/additem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MODERATOR')")
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
        model.addAttribute("currentUser", getUserData());

        ShopItem item = itemService.getItem(id);
        model.addAttribute("item", item);

        List<Country> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Category> categories = itemService.getAllCategories();
        model.addAttribute("categories",categories);

        return "details";
    }

    @GetMapping(value = "/edititem/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MODERATOR')")
    public String editItem(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("currentUser", getUserData());

        ShopItem item = itemService.getItem(id);
        model.addAttribute("item", item);

        List<Country> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Category> categories = itemService.getAllCategories();
        model.addAttribute("categories",categories);

        return "edititem";
    }

    @PostMapping(value = "/saveitem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MODERATOR')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MODERATOR')")
    public String deleteItemItem(@RequestParam(name = "id", defaultValue = "No item") Long id) {
        ShopItem item = itemService.getItem(id);
        if(item != null) {
            itemService.deleteItem(item);
        }

        return "redirect:/";
    }

    @PostMapping(value = "/assigncategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MODERATOR')")
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

                return "redirect:/edititem/" + itemId;
            }
        }

        return "redirect:/";

    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "403";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "login";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "profile";
    }

    @GetMapping(value = "/additem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addItem(Model model) {
        model.addAttribute("currentUser", getUserData());

        List<Country> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        return "additem";
    }

    private com.andreev.springboot.entities.User getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User secUser = (User)authentication.getPrincipal();
            com.andreev.springboot.entities.User myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }

        return null;
    }
}
