package org.example.menu.impl;

import org.example.menu.Menu;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ChangeLanguage implements Menu {
    private final ResourceBundle resourceBundle;
    {
        resourceBundle=ResourceBundle.getBundle("labels");
    }
    @Override
    public void start() {
        printMenuHeader();
        System.out.println(resourceBundle.getString("select.language.cta"));
        Scanner input=new Scanner(System.in);
        int option=input.nextInt();
        switch (option){
            case 1:
                Locale.setDefault(new Locale("en"));
                break;
            case 2:
                Locale.setDefault(new Locale("ru"));
                break;
            default:
                System.out.println("Only Russian(ru) or English(en) is supported");
                new ChangeLanguage().start();
        }

        new MainMenu().start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println(resourceBundle.getString("change.language.header"));
    }
}
