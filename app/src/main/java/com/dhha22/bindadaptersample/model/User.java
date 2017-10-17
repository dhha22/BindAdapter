package com.dhha22.bindadaptersample.model;

import com.dhha22.bindadapter.Item;

/**
 * Created by DavidHa on 2017. 10. 12..
 */

public class User  implements Item{
    public String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
