package com.echanneling.service.support;

import com.echanneling.delegate.AppDelegate;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author shalithasenanayaka on 2019-08-10 using IntelliJ IDEA
 */
public class CatchException {

    /** ======================= All Exceptions are handled in here =================================== */

    /**
     * We catch exceptions separated. Because of in some cases we want to handle it here by different ways
     * */

    public static void Handle(ArithmeticException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(IOException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(FileNotFoundException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(ArrayStoreException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(ClassCastException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(EnumConstantNotPresentException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(IllegalThreadStateException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(NumberFormatException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(IllegalMonitorStateException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(IllegalStateException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(ArrayIndexOutOfBoundsException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(StringIndexOutOfBoundsException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(NegativeArraySizeException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(NullPointerException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(SecurityException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(TypeNotPresentException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(UnsupportedOperationException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    /** ---Mail exceptions--- */
    public static void Handle(AddressException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }
    public static void Handle(MessagingException e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

    public static void Handle(Exception e){
        AppDelegate.CustomExceptionHandle(e.getClass().getCanonicalName(),e);
    }

}
