package org.example.menu.impl;

import org.example.application.ApplicationContext;
import org.example.entities.Purchase;
import org.example.management.PurchaseManagementService;
import org.example.management.impl.DefaultPurchaseManagementService;
import org.example.management.impl.MySqlPurchaseManagementService;
import org.example.menu.Menu;

import java.util.List;

public class MyOrdersMenu implements Menu {

	private final ApplicationContext context;
	private final PurchaseManagementService purchaseManagementService;

	{
		context = ApplicationContext.getInstance();
		purchaseManagementService = new MySqlPurchaseManagementService();
	}

	@Override
	public void start() {
		printMenuHeader();
		if(context.getLoggedInUser()==null){
			System.out.println("Please, log in or create new account to see list of your orders");
			new MainMenu().start();
			return;
		}else{
			List<Purchase> ordersByUserId = purchaseManagementService.getPurchaseByUserId(context.getLoggedInUser().getId());
			if(ordersByUserId==null||ordersByUserId.size()==0){
				System.out.println(
						"Unfortunately, you don't have any orders yet. "
								+ "Navigate back to main menu to place a new order");
			}else{
				for(Purchase purchase : ordersByUserId){
					System.out.println(purchase);
				}
			}
		}
		new MainMenu().start();

	}

	@Override
	public void printMenuHeader() {
		System.out.println("******** ORDERS MENU *********");
	}

}
