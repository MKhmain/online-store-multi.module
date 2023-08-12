package org.example.dto;

import java.util.List;

public class PurchaseDto {
    private int id;
    private UserDto user;
    private List<ProductDto> products;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "PurchaseDto{" +
                "id=" + id +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
