package com.echanneling.model.structure;

/**
 * @author shalithasenanayaka on 2019-10-06 using IntelliJ IDEA
 */
public class Doctor {
    private int DoctorID;
    private String DoctorName;
    private String Speciality;
    private boolean isActive;

    public Doctor() {
        DoctorID = 0;
        DoctorName = "";
        Speciality = "";
        isActive = false;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(int doctorID) {
        DoctorID = doctorID;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
