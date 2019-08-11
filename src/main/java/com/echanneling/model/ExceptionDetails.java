package com.echanneling.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shalithasenanayaka on 2019-08-11 using IntelliJ IDEA
 */
public class ExceptionDetails {
    private String date;
    private String ExceptionType;
    private String Message;
    private String StackTrace;

    public ExceptionDetails() {
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ExceptionType = Constants.STRING_EMPTY;
        Message = Constants.STRING_EMPTY;
        StackTrace = Constants.STRING_EMPTY;
    }

    public ExceptionDetails(String date, String exceptionType, String message, String stackTrace) {
        this.date = date;
        ExceptionType = exceptionType;
        Message = message;
        StackTrace = stackTrace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExceptionType() {
        return ExceptionType;
    }

    public void setExceptionType(String exceptionType) {
        ExceptionType = exceptionType;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStackTrace() {
        return StackTrace;
    }

    public void setStackTrace(String stackTrace) {
        StackTrace = stackTrace;
    }
}
