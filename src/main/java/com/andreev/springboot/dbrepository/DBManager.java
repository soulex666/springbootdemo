package com.andreev.springboot.dbrepository;

import com.andreev.springboot.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static List<Item> items = new ArrayList<>();
    private static Long id = 5L;

    static {
        items.add(new Item(1L, "IPhone X", 76000));
        items.add(new Item(2L, "Xiaomi Redmi Note 9 Pro", 25000));
        items.add(new Item(3L, "Samsung Galaxy Note 2", 47000));
        items.add(new Item(4L, "Nokia 3110", 4200));
    }

    public static List<Item> getItems() {
        return items;
    }

    public static void addItem(Item item) {
        item.setId(id);
        items.add(item);
        id++;
    }

    public static Item getItem(Long id) {
        for(Item i : items) {
            if (i.getId().equals(id)) {
                return i;
            }
        }

        return null;
    }
}
