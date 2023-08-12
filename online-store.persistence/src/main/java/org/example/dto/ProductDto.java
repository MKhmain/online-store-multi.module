package org.example.dto;

import java.math.BigDecimal;

public class ProductDto {
    private int id;
    private String product_name;
    private BigDecimal price;
    private CategoryDto category;

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", price=" + price +
                '}';
    }
}
