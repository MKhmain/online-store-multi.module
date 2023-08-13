package org.example.menu.impl;

import org.example.application.ApplicationContext;
import org.example.entities.User;
import org.example.management.UserManagementService;
import org.example.management.impl.DefaultUserManagementService;
import org.example.management.impl.MySqlUserManagementService;
import org.example.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;


public class SignInMenu implements Menu {

	private final ApplicationContext context;
	private final UserManagementService userManagementService;
	private final ResourceBundle resourceBundle;
	{
		resourceBundle=ResourceBundle.getBundle("labels");
		context = ApplicationContext.getInstance();
		userManagementService = new MySqlUserManagementService();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc=new Scanner(System.in);
		System.out.println(resourceBundle.getString("please.enter.email"));
		String email=sc.nextLine();
		System.out.println(resourceBundle.getString("please.enter.pass"));
		String password=sc.nextLine();
		User user=userManagementService.getUserByEmail(email);
		if(user!=null&&user.getPassword().equals(password)){
			System.out.println(resourceBundle.getString("glad.to.see.you.back")+user.getFirstName()+" "+user.getLastName());
			context.setLoggedInUser(user);
		}else{
			System.out.println(resourceBundle.getString("login.and.password.not.exist"));
		}
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println(resourceBundle.getString("sign.in.header"));
	}

}
