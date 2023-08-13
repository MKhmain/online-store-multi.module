package org.example.entities.impl;


import org.example.entities.Cart;
import org.example.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class DefaultCart implements Cart {
	private static final int DEFAULT_CAPACITY=10;
	List<Product> products;
	private int idx;
	{
		products=new ArrayList<>(DEFAULT_CAPACITY);
	}
	@Override
	public boolean isEmpty() {
		return products.isEmpty();
	}

	@Override
	public void addProduct(Product product) {
		if(product==null)
			return;
		products.add(product);
	}

	@Override
	public List<Product> getProducts() {
		return new ArrayList<>(products);
	}

	@Override
	public void clear() {
		products.clear();
	}

}
