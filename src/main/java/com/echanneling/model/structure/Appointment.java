package com.echanneling.model.structure;

/**
 * @author shalithasenanayaka on 2019-10-07 using IntelliJ IDEA
 */
public class Appointment {
    private int AppointmentID ;
    private String FullName ;
    private String Address ;
    private String Guardian ;
    private String Email ;
    private String Phone ;
    private int Age ;
    private int FK_UserID;
    private int FK_Hospital;
    private int FK_Doctor;

    public int getAppointmentID() {
        return AppointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        AppointmentID = appointmentID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGuardian() {
        return Guardian;
    }

    public void setGuardian(String guardian) {
        Guardian = guardian;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getFK_UserID() {
        return FK_UserID;
    }

    public void setFK_UserID(int FK_UserID) {
        this.FK_UserID = FK_UserID;
    }

    public int getFK_Hospital() {
        return FK_Hospital;
    }

    public void setFK_Hospital(int FK_Hospital) {
        this.FK_Hospital = FK_Hospital;
    }

    public int getFK_Doctor() {
        return FK_Doctor;
    }

    public void setFK_Doctor(int FK_Doctor) {
        this.FK_Doctor = FK_Doctor;
    }
}
