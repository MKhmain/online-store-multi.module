package org.example.entities;

import java.io.Serializable;

public interface User extends Serializable {
    long serialVersionUID=1L;
    String getFirstName();
    String getLastName();
    String getPassword();
    String getEmail();
    int getId();
    void setId(int id);
    void setPassword(String newPassword);
    void setEmail(String newEmail);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    String getRoleName();
    void setRoleName(String roleName);

    double getMoney();
    void setMoney(double money);

    String getCreditCard();
    void setCreditCard(String creditCard);
}
