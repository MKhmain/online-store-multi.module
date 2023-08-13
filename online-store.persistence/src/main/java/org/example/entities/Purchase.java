package org.example.entities;

import java.io.Serializable;
import java.util.List;

public interface Purchase extends Serializable {
    long serialVersionUID=1L;
    boolean isCreditCardNumberValid(String userInput);
    void setCreditCardNumber(String userInput);
    void setProducts(List<Product> products);
    void setCustomerId(int customerId);
    int getCustomerId();
    List<Product> getProducts();

}
