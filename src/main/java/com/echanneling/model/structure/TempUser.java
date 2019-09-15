package com.echanneling.model.structure;

import java.util.Date;

/**
 * @author shalithasenanayaka on 2019-09-12 using IntelliJ IDEA
 */
public class TempUser extends User {
    private String verificationCode;
    private Date registrationDate;


    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
