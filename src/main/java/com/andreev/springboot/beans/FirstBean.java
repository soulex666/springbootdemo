package com.andreev.springboot.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirstBean {
    private String name;
    private int age;

    public FirstBean() {
        System.out.println("Using default constructor of FirstBean class");
        this.name = "noname";
        this.age = 0;
    }

    public FirstBean(String name, int age) {
        System.out.println("Using parametrized constructor of FirstBean class");
        this.name = name;
        this.age = age;
    }

    public String getText() {
        return this.name + " " + this.age + " years old";
    }


}
