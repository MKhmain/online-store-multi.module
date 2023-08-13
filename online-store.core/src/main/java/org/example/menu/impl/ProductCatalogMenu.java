package org.example.menu.impl;

import org.example.application.ApplicationContext;
import org.example.entities.Cart;
import org.example.entities.Product;
import org.example.management.ProductManagementService;
import org.example.management.impl.DefaultProductManagementService;
import org.example.management.impl.MySqlProductManagementService;
import org.example.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;


public class ProductCatalogMenu implements Menu {

	private static final String CHECKOUT_COMMAND = "checkout";
	private final ApplicationContext context;
	private final ProductManagementService productManagementService;

	private final ResourceBundle resourceBundle;
	{
		resourceBundle= ResourceBundle.getBundle("labels");
		context = ApplicationContext.getInstance();
		productManagementService = new MySqlProductManagementService();
	}

	@Override
	public void start() {
		Menu menu=null;
		while(true) {
			printMenuHeader();
			printProductsToConsole();

			Scanner sc=new Scanner(System.in);
			System.out.print(resourceBundle.getString("proceed.to.checkout"));
			String userInput=sc.nextLine();

			if(userInput.equals(MainMenu.MENU_COMMAND)){
				menu=new MainMenu();
				break;
			}
			if(context.getLoggedInUser()==null){
				menu = new MainMenu();
				System.out.println(resourceBundle.getString("not.logged.in.msg"));
				break;
			}
			if(userInput.equalsIgnoreCase(CHECKOUT_COMMAND)){
				Cart cart=context.getSessionCart();
				if(cart==null|| cart.isEmpty()){
					System.out.println(resourceBundle.getString("empty.cart.err.msg"));
				} else {
					menu = new CheckoutMenu();
					break;
				}
			}else {
				int id=Integer.parseInt(userInput);
				Product product= productManagementService.getProductById(id);
				if(product==null){
					System.out.println(resourceBundle.getString("enter.product.id"));
					continue;
				}
				context.getSessionCart().addProduct(product);
				System.out.printf(resourceBundle.getString("product.added.to.cart"), product.getProductName());
			}

		}

		menu.start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(resourceBundle.getString("product.catalog.header"));
		System.out.println(resourceBundle.getString("catalog.cta"));
	}
	private void printProductsToConsole(){
		for(Product product:productManagementService.getProducts()){
			System.out.println(product);
		}
	}
}
