package org.example.storage;

import org.example.entities.Product;

import java.util.List;

public interface StorageServiceProduct {
    List<Product> loadProduct();
}
