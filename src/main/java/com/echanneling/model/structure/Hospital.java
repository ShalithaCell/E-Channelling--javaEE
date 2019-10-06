package com.echanneling.model.structure;

/**
 * @author shalithasenanayaka on 2019-10-06 using IntelliJ IDEA
 */
public class Hospital {
    private int HospitalID;
    private String Description;
    private String Address;
    private boolean IsActive;
    private String Active;

    public Hospital() {
        HospitalID = 0;
        Description = "";
        Address = "";
        IsActive = false;
        Active = "";
    }

    public int getHospitalID() {
        return HospitalID;
    }

    public void setHospitalID(int hospitalID) {
        HospitalID = hospitalID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }
}
