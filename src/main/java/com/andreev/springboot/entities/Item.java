package com.andreev.springboot.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long id;
    private String name;
    private int price;
}
