package org.example.management.impl;

import org.example.entities.Product;
import org.example.management.ProductManagementService;
import org.example.storage.impl.ProductStorage;

import java.util.ArrayList;
import java.util.List;

public class DefaultProductManagementService implements ProductManagementService {
	
	private static DefaultProductManagementService instance;
	private static ProductStorage productsFromStorage;
	private static List<Product> products;
	static {
		productsFromStorage=ProductStorage.getInstance();
		initProduct();
	}
	public static void initProduct(){
		products=productsFromStorage.loadProduct();
	}
	private DefaultProductManagementService() {
		
	}

	public static ProductManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultProductManagementService();
		}
		return instance;
	}

	@Override
	public List<Product> getProducts() {
		return new ArrayList<>(products);
	}

	@Override
	public Product getProductById(int productIdToAddToCart) {
		if(productIdToAddToCart<0||productIdToAddToCart>=products.size())
			return null;
		for(Product product: products){
			if(product!=null&&product.getId()==productIdToAddToCart)
				return product;
		}
		return null;
	}

}
