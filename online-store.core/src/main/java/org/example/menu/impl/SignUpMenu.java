package org.example.menu.impl;

import org.example.application.ApplicationContext;
import org.example.entities.User;
import org.example.entities.impl.DefaultUser;
import org.example.management.UserManagementService;
import org.example.management.impl.DefaultUserManagementService;
import org.example.management.impl.MySqlUserManagementService;
import org.example.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;


public class SignUpMenu implements Menu {

	private final UserManagementService userManagementService;
	private final ApplicationContext context;
	ResourceBundle resourceBundle;
	{
		resourceBundle=ResourceBundle.getBundle("labels");
		userManagementService = new MySqlUserManagementService();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc=new Scanner(System.in);
		System.out.println(resourceBundle.getString("enter.your.first.name"));
		String firstName=sc.nextLine();
		System.out.println(resourceBundle.getString("enter.your.last.name"));
		String lastName=sc.nextLine();
		System.out.println(resourceBundle.getString("enter.your.pass"));
		String password=sc.nextLine();
		System.out.println(resourceBundle.getString("enter.your.email"));
		String email=sc.nextLine();
		User user = new DefaultUser(firstName, lastName, password, email);
		String result=userManagementService.registerUser(user);
		if(result==null||result.isEmpty()){
			context.setLoggedInUser(user);
			System.out.println(resourceBundle.getString("user.created.msg"));
		}else{
			System.out.println(result);
		}
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(resourceBundle.getString("sign.up.header"));
	}

}
