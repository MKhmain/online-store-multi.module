package org.example.menu.impl;

import org.example.entities.User;
import org.example.management.ResetPasswordService;
import org.example.management.UserManagementService;
import org.example.management.impl.DefaultResetPasswordService;
import org.example.management.impl.DefaultUserManagementService;
import org.example.management.impl.MySqlUserManagementService;
import org.example.menu.Menu;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class ResetPassword implements Menu {
    public final ResetPasswordService reset;
    private final UserManagementService userManagementService;
    private final ResourceBundle resourceBundle;

    {
        resourceBundle= ResourceBundle.getBundle("labels");
        reset=new DefaultResetPasswordService();
        userManagementService= new MySqlUserManagementService();
    }
    @Override
    public void start() {
        printMenuHeader();
        Scanner input=new Scanner(System.in);
        System.out.print(resourceBundle.getString("enter.your.email.msg"));
        String email=input.nextLine();
        CompletableFuture.runAsync(()-> {
            User user=userManagementService.getUserByEmail(email);
            reset.resetPasswordForUser(user);
        });
        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(resourceBundle.getString("reset.pass.header"));
    }
}
