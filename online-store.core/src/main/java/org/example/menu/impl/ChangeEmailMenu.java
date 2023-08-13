package org.example.menu.impl;


import org.example.application.ApplicationContext;
import org.example.entities.User;
import org.example.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;

public class ChangeEmailMenu implements Menu {

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
		System.out.println(resourceBundle.getString("enter.new.email.cta"));
		String newEmail=sc.nextLine();
		User loggedInUser = context.getLoggedInUser();
		loggedInUser.setEmail(newEmail);
		System.out.println(resourceBundle.getString("mail.changed.msg"));
		new MainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(resourceBundle.getString("change.email.header"));
	}

}
