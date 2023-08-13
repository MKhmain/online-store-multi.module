package org.example.storage;

import org.example.entities.User;

import java.util.List;

public interface StorageServiceUser {
    void saveUser(User user);
    List<User> loadUser();
}
