package org.example.dao.impl;

import org.example.dao.CategoryDao;
import org.example.dto.CategoryDto;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlJdbcCategoryDao implements CategoryDao {
    @Override
    public CategoryDto getByCategoryId(int id) {
        try(var conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/learn_it_db","root","test");
            var ps = conn.prepareStatement("Select * from category where id =?")) {
            ps.setInt(1,id);
            try(var rs=ps.executeQuery()){
                if(rs.next()){
                    CategoryDto category=new CategoryDto();
                    category.setId(rs.getInt("id"));
                    category.setCategory_name(rs.getString("category_name"));
                    return category;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
