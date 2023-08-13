package org.example.menu.impl;


import org.example.Main;
import org.example.application.ApplicationContext;
import org.example.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;

public class MainMenu implements Menu {

	public static final String MENU_COMMAND = "menu";

	private final ApplicationContext context;

	private final ResourceBundle resourceBundle;

	{
		resourceBundle= ResourceBundle.getBundle("labels");
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		if(context.getMainMenu()==null){
			context.setMainMenu(this);
		}
		Scanner sc =new Scanner(System.in);
		Menu menu=null;
		loop: 	while(true){
					printMenuHeader();
					System.out.print(resourceBundle.getString("user.input"));
					String input=sc.nextLine();
					if(input.equals(Main.EXIT_COMMAND))
						System.exit(0);
					else {
						int command=Integer.parseInt(input);
						switch (command) {
							case 1:
								menu=new SignUpMenu();
								break loop;
							case 2:
								if(context.getLoggedInUser()==null)
									menu=new SignInMenu();
								else
									menu=new SignOutMenu();
								break loop;
							case 3:
								menu= new ProductCatalogMenu();
								break loop;
							case 4:
								menu= new MyOrdersMenu();
								break loop;
							case 5:
								menu= new SettingsMenu();
								break loop;
							case 6:
								menu=new CustomerListMenu();
								break loop;
							case 7:
								menu=new ResetPassword();
								break loop;
							case 8:
								menu=new ChangeLanguage();
								break loop;
							default:
								System.out.println(resourceBundle.getString("err.msg"));
						}
					}
				}
		menu.start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(resourceBundle.getString("main.menu.header"));
		if(context.getLoggedInUser()==null)
			System.out.println(resourceBundle.getString("menu.for.not.logged.in.user"));
		else{
			System.out.println(resourceBundle.getString("menu.for.logged.in.user"));
		}
	}

}
