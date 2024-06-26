package org.example.management;

import org.example.entities.User;

import java.util.List;

public interface UserManagementService {
    String registerUser(User user);
    List<User> getUsers();
    User getUserByEmail(String userEmail);
    void resetPasswordForUser(User user);
}
