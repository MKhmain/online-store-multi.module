package org.example.management.impl;

import org.example.dao.PurchaseDao;
import org.example.dao.impl.MySqlJdbcPurchaseDao;
import org.example.dto.PurchaseDto;
import org.example.dto.impl.PurchaseDtoToConverter;
import org.example.entities.Purchase;
import org.example.management.PurchaseManagementService;

import java.util.List;

public class MySqlPurchaseManagementService implements PurchaseManagementService {
    private final PurchaseDao purchaseDao;
    private final PurchaseDtoToConverter purchaseConverter;

    {
        purchaseDao = new MySqlJdbcPurchaseDao();
        purchaseConverter = new PurchaseDtoToConverter();
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseDao.savePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
    }

    @Override
    public List<Purchase> getPurchaseByUserId(int userId) {
        List<PurchaseDto> purchasesDtos = purchaseDao.getPurchasesByUserId(userId);
        return purchaseConverter.convertPurchaseDtosToPurchases(purchasesDtos);
    }

    @Override
    public List<Purchase> getPurchases() {
        List<PurchaseDto> purchasesDtos = purchaseDao.getPurchases();
        return purchaseConverter.convertPurchaseDtosToPurchases(purchasesDtos);
    }

}
