package org.example.storage;

import org.example.entities.Purchase;

import java.util.List;

public interface StorageServiceOrder {
    void saveOrder(List<Purchase> purchases);
    List<Purchase> loadOrder();
}
