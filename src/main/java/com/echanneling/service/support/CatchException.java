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

    /** =======================All Exceptions are handled in here=================================== */

    public static void Handle(ArithmeticException e){
        AppDelegate.CustomExceptionHandle("test");
    }

    public static void Handle(IOException e){

    }

    public static void Handle(FileNotFoundException e){

    }

    public static void Handle(ArrayStoreException e){

    }

    public static void Handle(ClassCastException e){

    }

    public static void Handle(EnumConstantNotPresentException e){

    }

    public static void Handle(IllegalThreadStateException e){

    }

    public static void Handle(NumberFormatException e){

    }

    public static void Handle(IllegalMonitorStateException e){

    }

    public static void Handle(IllegalStateException e){

    }

    public static void Handle(ArrayIndexOutOfBoundsException e){

    }

    public static void Handle(StringIndexOutOfBoundsException e){

    }

    public static void Handle(NegativeArraySizeException e){

    }

    public static void Handle(NullPointerException e){

    }

    public static void Handle(SecurityException e){

    }

    public static void Handle(TypeNotPresentException e){

    }

    public static void Handle(UnsupportedOperationException e){

    }

    public static void Handle(InternalError e){

    }

    public static void Handle(OutOfMemoryError e){

    }

    /** ---Mail exceptions--- */
    public static void Handle(AddressException e){

    }
    public static void Handle(MessagingException e){

    }

    public static void Handle(Exception e){

    }

}
