package org.example.menu.impl;

import org.example.application.ApplicationContext;
import org.example.menu.Menu;

import java.util.ResourceBundle;

public class SignOutMenu implements Menu {

	private final ApplicationContext context;
	ResourceBundle resourceBundle;
	{
		resourceBundle=ResourceBundle.getBundle("labels");
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		context.setLoggedInUser(null);
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(resourceBundle.getString("sign.out.header"));
		System.out.println(resourceBundle.getString("bye.msg"));
	}

}
