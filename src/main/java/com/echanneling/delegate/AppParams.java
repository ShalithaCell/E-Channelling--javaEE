package com.echanneling.delegate;

import com.echanneling.model.Constants;

/**
 * @author shalithasenanayaka on 2019-08-10 using IntelliJ IDEA
 */
public class AppParams {

    /**
     * Common required values
     * */

    public static String DeveloperMail;

    static {
        DeveloperMail = AppDelegate.properties.getProperty(Constants.DEVELOPER_EMAIL);
    }

}
