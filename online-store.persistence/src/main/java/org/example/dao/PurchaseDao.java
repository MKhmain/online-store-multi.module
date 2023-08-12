package org.example.dao;


import org.example.dto.PurchaseDto;

import java.util.List;

public interface PurchaseDao {
    void savePurchase(PurchaseDto order);

    List<PurchaseDto> getPurchasesByUserId(int userId);

    List<PurchaseDto> getPurchases();
}
