package com.echanneling.delegate;

import com.echanneling.model.Constants;
import com.echanneling.service.biz.CommonOperations;
import com.echanneling.service.biz.IOOperations;
import com.echanneling.service.support.CatchException;

import java.io.*;

/**
 * @author shalithasenanayaka on 2019-08-10 using IntelliJ IDEA
 */
public class AppParams {

    /**
     * Common required values
     * */

    public static String InitParam;
    public static String DeveloperMail;
    public static String MailSender;
    public static String SQLDriver;
    public static String ConnectionString;
    public static String VerificationURL;
    public static String PasswordResetURL;
    public static String AppDomain;

    public static String ExceptionMailContent;
    public static String VerificationMailContent;
    public static String PasswordRestMailContent;

    static {
        InitParam = "Started";
        DeveloperMail = AppDelegate.properties.getProperty(Constants.DEVELOPER_EMAIL);
        MailSender = AppDelegate.properties.getProperty(Constants.MAIL_SENDER);
        SQLDriver = AppDelegate.properties.getProperty(Constants.DRIVER_NAME);
        ConnectionString = AppDelegate.properties.getProperty(Constants.CONNECTION_STRING);
        VerificationURL = AppDelegate.properties.getProperty(Constants.VERIFICATION_URL);
        AppDomain = AppDelegate.properties.getProperty(Constants.PROJECTDOMAIN);
        PasswordResetURL = AppDelegate.properties.getProperty(Constants.FORGETPASSWORD_CODE);
        try{
            //get Exception mail template content
            ExceptionMailContent = CommonOperations.readString(IOOperations.ReadFromContexPath(Constants.EXEPTION_MAIL));
            VerificationMailContent = CommonOperations.readString(IOOperations.ReadFromContexPath(Constants.VERIFICATION_MAIL));
            PasswordRestMailContent = CommonOperations.readString(IOOperations.ReadFromContexPath(Constants.FORGETPASSWORD_MAIL));
        } catch (UnsupportedEncodingException e) {
            CatchException.Handle(e);
        } catch (IOException e) {
            CatchException.Handle(e);
        }

    }

}
