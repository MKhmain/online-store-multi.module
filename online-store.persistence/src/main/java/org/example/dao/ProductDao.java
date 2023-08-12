package org.example.dao;


import org.example.dto.ProductDto;

import java.util.List;

public interface ProductDao {
    List<ProductDto> getProducts();
    ProductDto getProductById(int id);
}
