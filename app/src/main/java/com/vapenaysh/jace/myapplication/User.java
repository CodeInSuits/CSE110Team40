package com.vapenaysh.jace.myapplication;

/**
 * Created by bijanfarahani on 5/3/16.
 * Class represents a user, with a username, email, and password.
 */
public class User {
    protected String userName;
    protected String emailAddress;
    protected String password;

    public User(String userName, String emailAddress, String password) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;

    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

}
