package org.example.management.impl;


import org.example.entities.User;
import org.example.entities.impl.DefaultUser;
import org.example.management.UserManagementService;
import org.example.storage.impl.UserStorage;

import java.util.List;

public class DefaultUserManagementService implements UserManagementService {
	
	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	

	private static DefaultUserManagementService instance;
	private static UserStorage userStorage;
	private int idx;
	{
		userStorage=UserStorage.getInstance();
	}

	private DefaultUserManagementService() {
	}
	
	@Override
	public String registerUser(User user) {

		if(user==null)
			return NO_ERROR_MESSAGE;
		if(user.getEmail().isEmpty())
			return EMPTY_EMAIL_ERROR_MESSAGE;
		List<User> users=userStorage.loadUser();
		for(User user1: users){
			if(user1!=null&&
					user1.getEmail()!=null&&
					user1.getEmail().equalsIgnoreCase(user.getEmail())){
				return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
			}
		}
		user.setId(users.size()+1);
		userStorage.saveUser(user);

		return NO_ERROR_MESSAGE;
	}
	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}

	
	@Override
	public List<User> getUsers() {
		List<User> users=userStorage.loadUser();
		DefaultUser.setCounter(
				users.stream().mapToInt(User::getId).max().getAsInt()
		);
		return users;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		List<User> users=userStorage.loadUser();
		for(User user: users){
			if(user!=null&&user.getEmail().equalsIgnoreCase(userEmail)){
				return user;
			}
		}
		return null;
	}

	@Override
	public void resetPasswordForUser(User user) {

	}

}
