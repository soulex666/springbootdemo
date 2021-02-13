package com.andreev.springboot.services;

import com.andreev.springboot.entities.Category;
import com.andreev.springboot.entities.Country;
import com.andreev.springboot.entities.ShopItem;

import java.util.List;

public interface ItemService {

    ShopItem addItem(ShopItem item);

    List<ShopItem> getAllItems();

    ShopItem getItem(Long id);

    void deleteItem(ShopItem item);

    ShopItem saveItem(ShopItem item);

    List<Country> getAllCountries();

    Country getCountryById(Long id);

    Country addCountry(Country country);

    Country saveCountry(Country country);

    List<Category> getAllCategories();

    Category getCategory(Long id);

    Category saveCategory(Category category);

    Category addCategory(Category category);
}
