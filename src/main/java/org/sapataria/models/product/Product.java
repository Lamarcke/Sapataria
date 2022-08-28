package org.sapataria.models.product;

import lombok.Getter;
import lombok.Setter;

// Representa um par de determinado calçado.
@Getter
@Setter
public class Product{
    private final String name;
    private String size;
    private int stock;
    private float price;
    private final String brand;
    private final ProductModels model;

    public Product(String name, String size, int stock, float price, String brand, ProductModels model) {
        this.name = name;
        this.size = size;
        this.stock = stock;
        this.price = price;
        this.brand = brand;
        this.model = model;
    }

}
