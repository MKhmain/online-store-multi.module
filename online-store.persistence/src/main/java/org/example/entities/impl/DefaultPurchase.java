package org.example.entities.impl;


import org.example.entities.Purchase;
import org.example.entities.Product;

import java.util.List;

public class DefaultPurchase implements Purchase {

	private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;
	
	private String creditCardNumber;
	private List<Product> products;
	private int customerId;

	@Override
	public boolean isCreditCardNumberValid(String creditCardNumber) {
		for(char ch: creditCardNumber.toCharArray()){
			if(!Character.isDigit(ch))
				return false;
		}
		if(creditCardNumber.length()==16)
			return true;
		return false;
	}


	@Override
	public void setCreditCardNumber(String creditCardNumber) {
		if(isCreditCardNumberValid(creditCardNumber))
			this.creditCardNumber=creditCardNumber;
		else
			System.out.println("Invalid credit card number");
	}

	@Override
	public void setProducts(List<Product> products) {
		this.products=products;
	}

	@Override
	public void setCustomerId(int customerId) {
		this.customerId=customerId;
	}


	@Override
	public int getCustomerId() {
		return this.customerId;
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public String toString() {
		return "Order: customerId=" + customerId +
				"creditCardNumber='" + creditCardNumber + '\'' +
				", products=" + products ;
	}


}
