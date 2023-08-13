package org.example.dao.impl;

import org.example.dao.ProductDao;
import org.example.dao.PurchaseDao;
import org.example.dao.UserDao;
import org.example.dto.ProductDto;
import org.example.dto.PurchaseDto;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcPurchaseDao implements PurchaseDao {
    UserDao user;
    ProductDao product;
    {
        user=new MySqlJdbcUserDao();
        product=new MySqlJdbcProductDao();
    }
    @Override
    public void savePurchase(PurchaseDto order) {
        try(var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn_it_db","root","test");
            var ps=conn.prepareStatement("Insert into purchase(fk_purchase_user) values(?)");
            var psProduct=conn.prepareStatement("Insert into purchase_product(purchase_id, product_id) values(?,?)")){
            ps.setInt(1,order.getUser().getId());
            ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){
                if(rs.next()){
                    for(ProductDto product: order.getProducts()){
                        psProduct.setLong(1,rs.getLong(1));
                        psProduct.setLong(2,product.getId());
                        psProduct.addBatch();
                    }
                    psProduct.executeBatch();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PurchaseDto> getPurchasesByUserId(int userId) {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn_it_db","root","test");
             var ps = conn.prepareStatement("SELECT * FROM purchase WHERE fk_purchase_user = ?")) {
            ps.setInt(1, userId);

            List<PurchaseDto> purchases = new ArrayList<>();

            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    PurchaseDto purchase = new PurchaseDto();
                    purchase.setId(rs.getInt("id"));
                    purchase.setUser(user.getUserById(rs.getInt("fk_purchase_user")));

                    List<ProductDto> products = new ArrayList<>();
                    try (var psProducts = conn.prepareStatement("SELECT * FROM purchase_product WHERE purchase_id = " + purchase.getId());
                         var rsProducts = psProducts.executeQuery()) {

                        while (rsProducts.next()) {
                            int i=rsProducts.getInt("product_id");
                            products.add(product.getProductById(i));
                        }
                    }
                    purchase.setProducts(products);
                    purchases.add(purchase);
                }
            }

            return purchases;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<PurchaseDto> getPurchases() {
        try(var conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/learn_it_db","root","test");
            var ps = conn.prepareStatement("select * from purchase")){
            List<PurchaseDto> purchases= new ArrayList<>();
            try(var rs=ps.executeQuery()){
                while(rs.next()) {
                    PurchaseDto purchase = new PurchaseDto();
                    purchase.setId(rs.getInt("id"));
                    purchase.setUser(user.getUserById(rs.getInt("fk_purchase_user")));
                    List<ProductDto> products = new ArrayList<>();
                    try (var psProduct = conn.prepareStatement("select * from purchase_product where purchase_id= " + purchase.getId());
                         var rsProduct = psProduct.executeQuery()) {
                        while (rs.next()) {
                            products.add(product.getProductById(rsProduct.getInt("product_id")));
                        }
                    }
                    purchase.setProducts(products);
                    purchases.add(purchase);
                }
            }
            return purchases;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
