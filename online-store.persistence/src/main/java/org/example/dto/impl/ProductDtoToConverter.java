package org.example.dto.impl;


import org.example.dao.ProductDao;
import org.example.dto.ProductDto;
import org.example.entities.Product;
import org.example.entities.impl.DefaultProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDtoToConverter {
    private CategoryDtoToConverter categoryConverter;
    {
        categoryConverter=new CategoryDtoToConverter();
    }
    public List<Product> productDtosToProduct(List<ProductDto> productDtos){
        List<Product> products=new ArrayList<>();
        for(var productDto: productDtos){
            Product product=new DefaultProduct();
            product.setId(productDto.getId());
            product.setProductName(productDto.getProduct_name());
            if(productDto.getCategory()!=null) {
                product.setCategoryName(productDto.getCategory().getCategory_name());
            }
            products.add(product);
        }
        return products;
    }
    public List<ProductDto> productsToProductDtos(List<Product> products){
        List<ProductDto> productDtos=new ArrayList<>();
        for(var product:products){
            ProductDto productDto=new ProductDto();
            productDto.setProduct_name(product.getProductName());
            productDto.setPrice(BigDecimal.valueOf(product.getPrice()));
            productDto.setId(product.getId());
            productDto.setCategory(categoryConverter.convertCategoryNameToCategoryDto(product.getCategoryName()));
            productDtos.add(productDto);
        }
        return productDtos;
    }
    public Product productDaoToProduct(ProductDto productDto){
        Product product=new DefaultProduct();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProduct_name());
        if(productDto.getCategory()!=null) {
            product.setCategoryName(productDto.getCategory().getCategory_name());
        }
        return product;
    }
}
