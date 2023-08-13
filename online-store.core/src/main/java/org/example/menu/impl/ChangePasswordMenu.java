package org.example.menu.impl;

import org.example.application.ApplicationContext;
import org.example.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;

public class ChangePasswordMenu implements Menu {
	
	private final ApplicationContext context;
	private final ResourceBundle resourceBundle;

	{
		context = ApplicationContext.getInstance();
		resourceBundle=ResourceBundle.getBundle("labels");
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc=new Scanner(System.in);
		System.out.println(resourceBundle.getString("enter.new.pass.cta"));
		String newPassword=sc.nextLine();
		context.getLoggedInUser().setPassword(newPassword);
		System.out.println(resourceBundle.getString("change.password.msg"));
		new MainMenu().start();

	}

	@Override
	public void printMenuHeader() {
		System.out.println(resourceBundle.getString("change.password.header"));

	}

}
