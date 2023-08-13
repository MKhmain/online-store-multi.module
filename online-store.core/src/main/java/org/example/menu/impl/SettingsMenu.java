package org.example.menu.impl;

import org.example.application.ApplicationContext;
import org.example.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;


public class SettingsMenu implements Menu {

	private final ApplicationContext context;

	private final ResourceBundle resourceBundle;

	{
		resourceBundle= ResourceBundle.getBundle("labels");
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		Menu menu=null;
		main:
		while(true) {
			printMenuHeader();
			if (context.getLoggedInUser() == null) {
				System.out.println(
						"Please, log in or create new account to change your account settings");
				new MainMenu().start();
			} else {
				System.out.println(resourceBundle.getString("settings.options"));
				System.out.print(
						resourceBundle.getString("enter.option"));
				Scanner sc = new Scanner(System.in);
				String option = sc.nextLine();
				if (option.equalsIgnoreCase(MainMenu.MENU_COMMAND)) {
					menu=new MainMenu();
					break main;
				}
				int userOption=Integer.parseInt(option);
				switch (userOption) {
					case 1:
						menu=new ChangePasswordMenu();
						break main;
					case 2:
						menu=new ChangeEmailMenu();
						break main;
					default:
						System.out.println(resourceBundle.getString("settings.option.validation.msg"));
				}

			}
		}
		menu.start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(resourceBundle.getString("settings.menu.header"));
	}

}
