package org.sapataria.dbo.cashier;

import lombok.Builder;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;
import org.sapataria.models.employee.Employee;
import org.sapataria.models.product.Product;
import org.sapataria.models.product.ProductModels;
import org.sapataria.models.product.ProductQuery;
import org.sapataria.models.sale.Sale;

import java.sql.*;
import java.util.ArrayList;


public class Cashier {
    /* This classes interacts with the database through CashierDBO. */

    private final CashierDBO dbo;
    private final Connection dboConnection;

    public Cashier() throws SQLException {
        this.dbo = new CashierDBO();
        this.dboConnection = dbo.connection;
    }

    public ArrayList<Product> search(@NotNull ProductQuery query) throws SQLException {
        dbo.open();
        ArrayList<Product> products = new ArrayList<Product>();
        String baseSql = """
        SELECT * FROM sapataria.products WHERE name LIKE ? AND brand LIKE ? AND model LIKE ?""";
        try {
            PreparedStatement ps = dboConnection.prepareStatement(baseSql);
            ps.setString(1, query.getName() != null ? query.getName() + "%" : "%");
            ps.setString(2, query.getBrand() != null ? query.getBrand() + "%" : "%");
            ps.setString(3, query.getModel() != null ? query.getModel() + "%" : "%");
            ResultSet results = ps.executeQuery();
            while (results.next()){
                ProductModels model;
                try {
                    model = ProductModels.valueOf(results.getString("model"));
                } catch (IllegalArgumentException e){
                    model = ProductModels.Generico;
                }
                Product currentProduct = new Product(
                        results.getString("name"), results.getString("size"),
                        results.getInt("stock"), results.getFloat("price"),
                        results.getString("brand"), model);


                products.add(currentProduct);
            }
            return products;
        }catch (IllegalArgumentException ignored){

        }finally {
            dbo.close();
        }
        return products;
    }

    public void registerProduct(@NotNull Product product) throws SQLException {
        dbo.open();
        String addSql = "INSERT INTO sapataria.products (name, size, brand, price, stock, model) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preSql = dboConnection.prepareStatement(addSql);
        preSql.setString(1, product.getName());
        preSql.setString(2, product.getSize());
        preSql.setString(3, product.getBrand());
        preSql.setFloat(4, product.getPrice());
        preSql.setInt(5, product.getStock());
        preSql.setString(6, product.getModel().toString());
        preSql.executeUpdate();
        dbo.close();

    }

    public void removeProduct(@NotNull Product product) throws SQLException {
        dbo.open();
        @Language("MySQL") String removeSql = """
                DELETE FROM sapataria.products WHERE name = ? AND size = ? AND model = ? AND brand = ? AND stock = ? AND price = ?""";
        PreparedStatement preSql = dboConnection.prepareStatement(removeSql);
        preSql.setString(1, product.getName());
        preSql.setString(2, product.getSize());
        preSql.setString(3, product.getModel().toString());
        preSql.setString(4, product.getBrand());
        preSql.setInt(5, product.getStock());
        preSql.setFloat(6, product.getPrice());
        preSql.executeUpdate();
        dbo.close();
    }

    public void registerSale(@NotNull Sale sale, @NotNull Employee employee) throws SQLException{
        dbo.open();
        @Language("MySQL") String addSql = """
        INSERT INTO sapataria.sales(numOfProducts, totalValue, author) VALUES (?, ?, ?)""";
        PreparedStatement preSql = dboConnection.prepareStatement(addSql);
    }

}
