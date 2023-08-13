package org.example.management.impl;

import org.example.utils.mail.MailSender;

public class DefaultMailSender implements MailSender {
    private static MailSender instance;
    private DefaultMailSender(){}

    public static MailSender getInstance(){
        if(instance==null){
            instance=new DefaultMailSender();
        }
        return instance;
    }
    @Override
    public void sendEmail(String sendTo, String messageToSend) {

    }
}
