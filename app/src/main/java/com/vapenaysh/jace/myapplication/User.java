package com.vapenaysh.jace.myapplication;

/**
 * Created by bijanfarahani on 5/3/16.
 */
public class User {
    protected String userName;
    protected String emailAddress;
    protected String password;
    protected User partner;
    public User(String userName, String emailAddress, String password) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        partner = null;
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

    public User getPartner() {
        return partner;
    }
    public void setPartner(User p) {
        partner = p;
    }
}
