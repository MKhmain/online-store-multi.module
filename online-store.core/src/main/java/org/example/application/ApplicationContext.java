package org.example.application;

import org.example.entities.Cart;
import org.example.entities.User;
import org.example.entities.impl.DefaultCart;
import org.example.menu.Menu;

import java.util.Objects;

public class ApplicationContext {
    private static ApplicationContext instance;

    private User loggedInUser;
    private Menu mainMenu;
    private Cart sessionCart;

    private ApplicationContext() {
    }

    public void setLoggedInUser(User user) {
        if (this.sessionCart != null) {
            this.sessionCart.clear(); // we have to clear session cart when new user is logged in
        }
        this.loggedInUser = user;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    public void setMainMenu(Menu menu) {
        this.mainMenu = menu;
    }

    public Menu getMainMenu() {
        return this.mainMenu;
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public Cart getSessionCart() {
        if (this.sessionCart == null) {
            this.sessionCart = new DefaultCart();
        }
        return this.sessionCart;
    }
    int id=20;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationContext that)) return false;
        return id == that.id && Objects.equals(loggedInUser, that.loggedInUser) && Objects.equals(mainMenu, that.mainMenu) && Objects.equals(sessionCart, that.sessionCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loggedInUser, mainMenu, sessionCart, id);
    }
}
