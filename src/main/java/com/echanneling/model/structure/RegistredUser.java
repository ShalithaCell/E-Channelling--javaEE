package com.echanneling.model.structure;

import java.util.Date;

/**
 * @author shalithasenanayaka on 2019-09-18 using IntelliJ IDEA
 */
public class RegistredUser extends User {
    private String FirstName;
    private String LastName;
    private int FK_GenderID;
    private int FK_RoleID;
    private String GenderDescription;
    private String ContactNo;
    private Date DOB;
    private String Password;
    private Date LastLoginDate;
    private int FailedLoginAttempt;
    private Date FailedLoginDate;
    private boolean AccountVerified;
    private boolean isLocked;
    public boolean isActive;


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getFK_GenderID() {
        return FK_GenderID;
    }

    public void setFK_GenderID(int FK_GenderID) {
        this.FK_GenderID = FK_GenderID;
    }

    public int getFK_RoleID() {
        return FK_RoleID;
    }

    public void setFK_RoleID(int FK_RoleID) {
        this.FK_RoleID = FK_RoleID;
    }

    public String getGenderDescription() {
        return GenderDescription;
    }

    public void setGenderDescription(String genderDescription) {
        GenderDescription = genderDescription;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    @Override
    public void setPassword(String password) {
        Password = password;
    }

    public Date getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }

    public int getFailedLoginAttempt() {
        return FailedLoginAttempt;
    }

    public void setFailedLoginAttempt(int failedLoginAttempt) {
        FailedLoginAttempt = failedLoginAttempt;
    }

    public Date getFailedLoginDate() {
        return FailedLoginDate;
    }

    public void setFailedLoginDate(Date failedLoginDate) {
        FailedLoginDate = failedLoginDate;
    }

    public boolean isAccountVerified() {
        return AccountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        AccountVerified = accountVerified;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "[FirstName="
                + FirstName
                + ", LastName="
                + LastName
                + ", FK_GenderID="
                + FK_GenderID
                + ", FK_RoleID="
                + FK_RoleID
                + ", GenderDescription="
                + GenderDescription
                + ", ContactNo="
                + ContactNo
                + ", DOB="
                + DOB+
        "]";
    }
}
