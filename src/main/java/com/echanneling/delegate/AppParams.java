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

    public static String ExceptionMailContent;

    static {
        InitParam = "Started";
        DeveloperMail = AppDelegate.properties.getProperty(Constants.DEVELOPER_EMAIL);
        MailSender = AppDelegate.properties.getProperty(Constants.MAIL_SENDER);
        SQLDriver = AppDelegate.properties.getProperty(Constants.DRIVER_NAME);
        ConnectionString = AppDelegate.properties.getProperty(Constants.CONNECTION_STRING);

        try{
            //get Exception mail template content
            ExceptionMailContent = CommonOperations.readString(IOOperations.ReadFromContexPath(Constants.EXEPTION_MAIL));
        } catch (UnsupportedEncodingException e) {
            CatchException.Handle(e);
        } catch (IOException e) {
            CatchException.Handle(e);
        }

    }

}
