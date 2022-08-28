package org.sapataria;

import org.sapataria.dbo.cashier.Cashier;
import org.sapataria.models.product.ProductQuery;

import java.sql.SQLException;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws SQLException {
        ProductQuery pq = ProductQuery.builder().name("Sapato").brand("Adidas").build();
        System.out.println(pq.getName());
        Cashier cashier = new Cashier();
        System.out.println(cashier.search(pq));


    }
    
}