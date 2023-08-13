package org.example.management;

import org.example.entities.Purchase;

import java.util.List;

public interface PurchaseManagementService {
    void addPurchase(Purchase purchase);
    List<Purchase> getPurchaseByUserId(int userId);
    List<Purchase> getPurchases();
}
