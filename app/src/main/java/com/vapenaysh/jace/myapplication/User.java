package com.vapenaysh.jace.myapplication;

/**
 * Created by bijanfarahani on 5/3/16.
 * Class represents a user, with a username, email, and password.
 */
public class User {
    private String userName;
    private String email;
    private String photoUri;
    private String uid;
    private String partnerEmail;
    private String partnerName;

    public User() {
    }

    public User(String email, String partnerEmail, String photoUri, String uid, String userName, String partnerName) {
        this.email = email;
        this.partnerEmail = partnerEmail;
        this.photoUri = photoUri;
        this.uid = uid;
        this.userName = userName;
        this.partnerName = partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public boolean hasPartner(){
        return !(partnerEmail.equals(""));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPartnerEmail(String partnerEmail) {
        this.partnerEmail = partnerEmail;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public String getPartnerEmail() {
        return partnerEmail;
    }

    public String getUserName() {
        return userName;
    }


}
