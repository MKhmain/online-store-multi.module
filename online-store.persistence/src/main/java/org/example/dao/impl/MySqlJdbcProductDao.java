package org.example.dao.impl;

import org.example.dao.CategoryDao;
import org.example.dao.ProductDao;
import org.example.dto.ProductDto;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcProductDao implements ProductDao {
    private final CategoryDao category;

    {
        category = new MySqlJdbcCategoryDao();
    }

    @Override
    public List<ProductDto> getProducts() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn_it_db","root","test");
             var ps = conn.prepareStatement("Select * from product");
             var rs = ps.executeQuery()) {
            List<ProductDto> products = new ArrayList<>();
            while (rs.next()) {
                ProductDto product = new ProductDto();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCategory(category.getByCategoryId(rs.getInt("category_id")));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDto getProductById(int id) {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn_it_db","root","test");
             var ps = conn.prepareStatement("Select * from product where id=?")) {
            ps.setInt(1,id);
            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductDto product = new ProductDto();
                    product.setId(rs.getInt("id"));
                    product.setProduct_name(rs.getString("product_name"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setCategory(category.getByCategoryId(rs.getInt("category_id")));
                    return product;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
