package org.example;

import org.example.entities.User;
import org.example.entities.impl.DefaultUser;
import org.example.management.ResetPasswordService;
import org.example.management.impl.DefaultResetPasswordService;
import org.example.utils.mail.MailSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DefaultResetPasswordServiceTest {
    @Mock
    private MailSender mailSender;
    @InjectMocks
    private ResetPasswordService resetPassword=new DefaultResetPasswordService();
    @Captor
    private ArgumentCaptor<String> captor;
    @Test
    void shouldSendMessageWhenResetCalled(){
        User user=new DefaultUser();
        String email="test@gmail.com";
        user.setEmail(email);
        resetPassword.resetPasswordForUser(user);
        verify(mailSender).sendEmail(captor.capture(),anyString());
        assertEquals(captor.getValue(),email);
    }
    @Test
    void shouldSendPassword(){
        User user=new DefaultUser();
        String password="pass";
        user.setPassword(password);
        resetPassword.resetPasswordForUser(user);
        verify(mailSender).sendEmail(any(),captor.capture());
        assertEquals(captor.getValue().split(":")[1].trim(), password);
    }

}
