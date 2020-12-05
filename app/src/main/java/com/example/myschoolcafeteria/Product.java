package com.example.myschoolcafeteria;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Product {
    public static final List<Product> ITEMS = new ArrayList<Product>();

    String name;
    String description;
    Bitmap photo;
    float price;
    int quantity;

    public Product(String name, String description,
                   Bitmap photo, float price, int quantity) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.quantity = quantity;
    }
}
