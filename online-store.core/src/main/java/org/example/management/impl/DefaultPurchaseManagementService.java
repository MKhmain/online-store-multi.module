package org.example.management.impl;

import org.example.entities.Purchase;
import org.example.management.PurchaseManagementService;
import org.example.storage.impl.OrderStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultPurchaseManagementService implements PurchaseManagementService {

	private static DefaultPurchaseManagementService instance;

	private static OrderStorage orderStorage;
	private List<Purchase> purchases;
	private int lastIdx;
	{
		orderStorage=OrderStorage.getInstance();
		purchases =new ArrayList<>();
	}
	
	public static PurchaseManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultPurchaseManagementService();
		}
		return instance;
	}

	@Override
	public void addPurchase(Purchase purchase) {
		if(purchase ==null)
			return;
		purchases.add(purchase);
		orderStorage.saveOrder(purchases);
	}

	@Override
	public List<Purchase> getPurchaseByUserId(int userId) {
		List<Purchase> purchase =orderStorage.loadOrder();
		if(purchase !=null)
		return purchase.stream().
				filter(Objects::nonNull).
				filter(s->s.getCustomerId()==userId).
				collect(Collectors.toList());
		return null;
	}

	@Override
	public List<Purchase> getPurchases() {
		if(purchases ==null|| purchases.size()==0)
			purchases =orderStorage.loadOrder();
		return new ArrayList<>(purchases);
	}
	
	void clearServiceState() {
		purchases.clear();
	}

}
