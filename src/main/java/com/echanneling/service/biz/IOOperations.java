package com.echanneling.service.biz;

import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Operations;

import java.io.InputStream;

/**
 * @author shalithasenanayaka on 2019-08-11 using IntelliJ IDEA
 */
public class IOOperations extends Operations {

    public static InputStream ReadFromContexPath(String path){
        InputStream inputStream = AppDelegate.sContext.getResourceAsStream(path);
        return inputStream;
    }

}
