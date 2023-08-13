package org.example.management;

import org.example.entities.Product;

import java.util.List;

public interface ProductManagementService {
    List<Product> getProducts();
    Product getProductById(int productIdToAddToChart);
}
