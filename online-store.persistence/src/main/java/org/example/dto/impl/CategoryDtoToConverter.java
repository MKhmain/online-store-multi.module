package org.example.dto.impl;


import org.example.dto.CategoryDto;

public class CategoryDtoToConverter {
    public CategoryDto convertCategoryNameToCategoryDto(String name){
        CategoryDto category=new CategoryDto();
        category.setCategory_name(name);
        return category;
    }
}
