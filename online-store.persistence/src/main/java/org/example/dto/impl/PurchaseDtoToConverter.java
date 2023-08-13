package org.example.dto.impl;


import org.example.dto.PurchaseDto;
import org.example.entities.Purchase;
import org.example.entities.impl.DefaultPurchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDtoToConverter {
    ProductDtoToConverter productDtoToConverter;
    UserDtoToConverter userDtoToConverter;
    {
        userDtoToConverter=new UserDtoToConverter();
        productDtoToConverter=new ProductDtoToConverter();
    }
    public Purchase convertPurchaseDtoToPurchase(PurchaseDto purchaseDto){
        Purchase purchase=new DefaultPurchase();
        purchase.setCustomerId(purchaseDto.getId());
        purchase.setCreditCardNumber(purchaseDto.getUser().getCreditCard());
        purchase.setProducts(productDtoToConverter.productDtosToProduct(purchaseDto.getProducts()));
        return purchase;
    }
    public PurchaseDto convertPurchaseToPurchaseDto(Purchase purchase){
        PurchaseDto purchaseDto=new PurchaseDto();
        purchaseDto.setProducts(productDtoToConverter.productsToProductDtos(purchase.getProducts()));
        purchaseDto.setUser(userDtoToConverter.convertUserToUserDtoWithId(purchase.getCustomerId()));
        return purchaseDto;
    }
    public List<Purchase> convertPurchaseDtosToPurchases(List<PurchaseDto> purchaseDtos){
        List<Purchase> list=new ArrayList<>();
        for(var purchaseDto: purchaseDtos){
            list.add(convertPurchaseDtoToPurchase(purchaseDto));
        }
        return list;
    }
}
