package org.sapataria.models.sale;

import lombok.Getter;
import org.sapataria.models.product.Product;

import java.util.ArrayList;

@Getter
public class Sale {
    /*
    A sale is a collection of products ready to be sold, in a cart or equivalent.
     */

    private final ArrayList<Product> products = new ArrayList<Product>();

    public Sale(ArrayList<Product> product){
        this.products.addAll(product);

    }
    public Sale(Product product){
        this.products.add(product);
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
    public void addProducts(ArrayList<Product> products){
        this.products.addAll(products);
    }


    public boolean removeProduct(Product product){
        return this.products.remove(product);
    }

    public boolean removeProducts(ArrayList<Product> products){
        return this.products.removeAll(products);
    }


}
