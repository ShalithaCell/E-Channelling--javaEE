package com.echanneling.model.TableModels;

import java.util.Date;

/**
 * @author shalithasenanayaka on 2019-09-30 using IntelliJ IDEA
 */
public class RegistedUserModel {
    public int UserID;
    public int FK_RoleID;
    public String FirstName;
    public String LastName;
    public String Email;
    public int FK_GenderID;
    public String ContactNo;
    public Date DOB;
    public boolean AccountVerified;
    public boolean isActive;
    public boolean isLocked;
}
