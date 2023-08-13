package org.example.entities;

import java.io.Serializable;

public interface Product extends Serializable {
    long serialVersionUID=1L;
    int getId();
    String getProductName();

    String getCategoryName();

    double getPrice();

    void setPrice(double price);

    void setId(int id);
    void setProductName(String name);
    void setCategoryName(String name);

}
