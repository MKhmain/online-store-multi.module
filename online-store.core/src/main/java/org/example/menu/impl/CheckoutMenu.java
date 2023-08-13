package org.example.menu.impl;


import org.example.application.ApplicationContext;
import org.example.entities.Purchase;
import org.example.entities.impl.DefaultPurchase;
import org.example.management.PurchaseManagementService;
import org.example.management.impl.DefaultPurchaseManagementService;
import org.example.management.impl.MySqlPurchaseManagementService;
import org.example.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;

public class CheckoutMenu implements Menu {

	private final ApplicationContext context;
	private final PurchaseManagementService purchaseManagementService;

	private final ResourceBundle resourceBundle;

	{
		context = ApplicationContext.getInstance();
		purchaseManagementService = new MySqlPurchaseManagementService();
		resourceBundle= ResourceBundle.getBundle("labels");
	}
	
	@Override
	public void start() {
		while(true) {
			printMenuHeader();
			Scanner sc = new Scanner(System.in);
			System.out.println(resourceBundle.getString("enter.credit.card.number.cta"));
			String creditCard = sc.nextLine();
			if (!createOrder(creditCard))
				continue;
			context.getSessionCart().clear();
			break;
		}
		System.out.println(resourceBundle.getString("thank.you.msg"));
		new MainMenu().start();


	}
	private boolean createOrder(String creditCard){
		Purchase purchase =new DefaultPurchase();
		if(purchase.isCreditCardNumberValid(creditCard)){
			purchase.setCreditCardNumber(creditCard);
			purchase.setProducts(context.getSessionCart().getProducts());
			purchase.setCustomerId(context.getLoggedInUser().getId());
			purchaseManagementService.addPurchase(purchase);
			return true;
		}
		return false;
	}
	@Override
	public void printMenuHeader() {
		System.out.println(resourceBundle.getString("checkout.menu.header"));
	}

}
