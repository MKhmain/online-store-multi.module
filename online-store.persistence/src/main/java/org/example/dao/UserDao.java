package org.example.dao;


import org.example.dto.UserDto;

import java.util.List;

public interface UserDao {
	
	UserDto getUserById(int id);
	UserDto getUserByEmail(String email);
	boolean saveUser(UserDto user);
	List<UserDto> getUsers();
}
