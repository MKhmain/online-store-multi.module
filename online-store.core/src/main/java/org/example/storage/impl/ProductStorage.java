package org.example.storage.impl;

import org.example.entities.Product;
import org.example.entities.impl.DefaultProduct;
import org.example.storage.StorageServiceProduct;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductStorage implements StorageServiceProduct {
    public static final String RESOURCES_FOLDER="resource";
    public static final String PRODUCTS_CSV ="products.csv";
    private static ProductStorage instance;
    public static ProductStorage getInstance(){
        if(instance==null)
            instance=new ProductStorage();
        return instance;
    }
    @Override
    public List<Product> loadProduct() {
        try(var list=Files.lines(Paths.get(RESOURCES_FOLDER,PRODUCTS_CSV))) {
            return list.filter(Objects::nonNull).
                    filter(s -> !s.isEmpty()).
                    map(s -> s.split(",")).
                    map(s -> new DefaultProduct(Integer.parseInt(s[0]), s[1], s[2], Double.parseDouble(s[3]))).
                    collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
