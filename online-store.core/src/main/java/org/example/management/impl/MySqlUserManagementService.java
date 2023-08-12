package org.example.management.impl;

import org.example.dao.UserDao;
import org.example.dao.impl.MySqlJdbcUserDao;
import org.example.dto.UserDto;
import org.example.dto.impl.UserDtoToConverter;
import org.example.entities.User;
import org.example.management.UserManagementService;
import org.example.utils.mail.MailSender;

import java.util.List;

public class MySqlUserManagementService implements UserManagementService {
    public static final String SUCCESSFUL_REGISTRATION_MESSAGE = "User is registered!";
    private static final String REGISTRATION_ERROR_MESSAGE = "The email is already in use by other user.";

    private final UserDao userDao;
    private final UserDtoToConverter userConverter;

    private final MailSender mailSender;

    {
        userDao = new MySqlJdbcUserDao();
        userConverter = new UserDtoToConverter();
        mailSender = DefaultMailSender.getInstance();
    }

    @Override
    public String registerUser(User user) {
        boolean isCreated = userDao.saveUser(userConverter.convertUserToUserDto(user));

        if (isCreated) {
            return SUCCESSFUL_REGISTRATION_MESSAGE;
        } else {
            return REGISTRATION_ERROR_MESSAGE;
        }
    }

    @Override
    public List<User> getUsers() {
        List<UserDto> userDtos = userDao.getUsers();
        return userConverter.convertUserDtosToUsers(userDtos);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        UserDto userDto = userDao.getUserByEmail(userEmail);
        return userConverter.convertUserDtoToUser(userDto);
    }

    @Override
    public void resetPasswordForUser(User user) {
        mailSender.sendEmail(user.getEmail(), "Please, use this password to login: " + user.getPassword());
    }
}
