package org.example.management.impl;

import org.example.entities.User;
import org.example.management.ResetPasswordService;
import org.example.utils.mail.MailSender;

import java.util.ResourceBundle;

public class DefaultResetPasswordService implements ResetPasswordService {
    private final MailSender mailSender;
    ResourceBundle resourceBundle;
    {
        resourceBundle=ResourceBundle.getBundle("labels");
        mailSender=DefaultMailSender.getInstance();
    }
    @Override
    public void resetPasswordForUser(User user) {
        System.out.println(resourceBundle.getString("pass.sent.to.email") );
        mailSender.sendEmail(user.getEmail(),"Your password is: "+user.getPassword());
    }
}
