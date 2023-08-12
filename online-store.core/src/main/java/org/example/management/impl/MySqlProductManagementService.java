package org.example.management.impl;

import org.example.dao.ProductDao;
import org.example.dao.impl.MySqlJdbcProductDao;
import org.example.dto.impl.ProductDtoToConverter;
import org.example.entities.Product;
import org.example.management.ProductManagementService;

import java.util.List;

public class MySqlProductManagementService implements ProductManagementService {
    private final ProductDao productDao;
    private final ProductDtoToConverter productConverter;
    {
        productDao=new MySqlJdbcProductDao();
        productConverter=new ProductDtoToConverter();
    }
    @Override
    public List<Product> getProducts() {
        return productConverter.productDtosToProduct(productDao.getProducts());
    }

    @Override
    public Product getProductById(int productIdToAddToChart) {
        return productConverter.productDaoToProduct(productDao.getProductById(productIdToAddToChart));
    }
}
