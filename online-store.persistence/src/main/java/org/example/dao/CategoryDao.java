package org.example.dao;


import org.example.dto.CategoryDto;

public interface CategoryDao {
        CategoryDto getByCategoryId(int id);
}
