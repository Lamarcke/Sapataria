package org.sapataria.dbo.cashier;

import org.sapataria.models.product.Product;
import org.sapataria.models.product.ProductModels;
import org.sapataria.models.product.ProductQuery;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.*;

public class CashierTest {
    private final Product exampleProduct = new Product("Sapato testador", "36/37", 3, 36.50F, "Adidas", ProductModels.Sapatos);


    @Test
    public void search() throws SQLException {
        Cashier cashier = new Cashier();
        ProductQuery pq = ProductQuery.builder().name("Sapato").model(ProductModels.Generico.toString()).brand("Adidas").build();
        ArrayList<Product> products = cashier.search(pq);
        assert products.size() > 0;
    }

    @Test
    public void registerProduct() throws SQLException {
        Cashier cashier = new Cashier();

        cashier.registerProduct(this.exampleProduct);

    }

    @Test
    public void removeProduct() throws SQLException{
        Cashier cashier = new Cashier();
        cashier.removeProduct(this.exampleProduct);

    }
}