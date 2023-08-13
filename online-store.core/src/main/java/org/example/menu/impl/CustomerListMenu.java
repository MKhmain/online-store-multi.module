package org.example.menu.impl;

import org.example.application.ApplicationContext;
import org.example.entities.User;
import org.example.management.UserManagementService;
import org.example.management.impl.DefaultUserManagementService;
import org.example.management.impl.MySqlUserManagementService;
import org.example.menu.Menu;

import java.util.List;
import java.util.ResourceBundle;

public class CustomerListMenu implements Menu {

	private final ApplicationContext context;
	private final UserManagementService userManagementService;

	private final ResourceBundle resourceBundle;

	{
		resourceBundle= ResourceBundle.getBundle("labels");
		userManagementService = new MySqlUserManagementService();
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		List<User> users = userManagementService.getUsers();
		if(users.size()==0){
			System.out.println(resourceBundle.getString("no.users.msg"));
		}else{
			for (User user : users) {
				System.out.println(user);
			}
		}
		context.getMainMenu().start();

	}

	@Override
	public void printMenuHeader() {
		System.out.println(resourceBundle.getString("customer.list.header"));
	}

}
