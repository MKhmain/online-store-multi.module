package org.example.dao;


import org.example.dao.impl.MySqlJdbcUserDao;
import org.example.dto.UserDto;

import java.math.BigDecimal;

public class DaoDemo {
	
	public static void main(String[] args) {
		
		UserDao userDao = new MySqlJdbcUserDao();
		UserDto user = userDao.getUserById(3);
		
		System.out.println(user);
		System.out.println(userDao.getUserByEmail(user.getEmail()));
		
		
		UserDto newUser = new UserDto();
		newUser.setFirstName("Evheniy");
		newUser.setLastName("Kachanov");
		newUser.setEmail("e.kachanov@email.com");
		newUser.setMoney(BigDecimal.valueOf(1000));
		
		userDao.saveUser(newUser);
		
		System.out.println(userDao.getUserByEmail("e.kachanov@email.com"));

	}

}
