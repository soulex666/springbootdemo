package com.andreev.springboot.services.impl;

import com.andreev.springboot.entities.Category;
import com.andreev.springboot.entities.Country;
import com.andreev.springboot.entities.ShopItem;
import com.andreev.springboot.repositories.CategoryRepository;
import com.andreev.springboot.repositories.CountryRepository;
import com.andreev.springboot.repositories.ShopItemRepository;
import com.andreev.springboot.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ShopItemRepository repository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ShopItem addItem(ShopItem item) {
        return repository.save(item);
    }

    @Override
    public List<ShopItem> getAllItems() {
        return repository.findAllByAmountGreaterThanOrderByPriceDesc(0);
    }

    @Override
    public ShopItem getItem(Long id) {
        return repository.findByIdAndAmountGreaterThan(id, 0);
    }

    @Override
    public void deleteItem(ShopItem item) {
        repository.delete(item);
    }

    @Override
    public ShopItem saveItem(ShopItem item) {
        return repository.save(item);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.getOne(id);
    }

    @Override
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
