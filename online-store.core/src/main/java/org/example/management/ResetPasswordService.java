package org.example.management;

import org.example.entities.User;

public interface ResetPasswordService {
    void resetPasswordForUser(User user);
}
