package com.echanneling.model.structure;

/**
 * @author shalithasenanayaka on 2019-09-09 using IntelliJ IDEA
 */
public class User {

    private int UserID;
    private String Email;
    private String Password;
    private boolean isActive;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
