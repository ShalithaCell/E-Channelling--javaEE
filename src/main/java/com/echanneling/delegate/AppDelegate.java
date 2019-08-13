package com.echanneling.delegate;

import com.echanneling.model.Constants;
import com.echanneling.model.ExceptionDetails;
import com.echanneling.service.support.MailInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author shalithasenanayaka on 2019-08-10 using IntelliJ IDEA
 */
public class AppDelegate {

    /**
     * All delegate methods
     * */

    //private static final Logger LOGGER = Logger.getLogger( AppDelegate.class.getName() );
    final static Logger logger = LogManager.getLogger(AppDelegate.class);
    public static final Properties properties = new Properties();

    public static ServletContext sContext;

    static {
        try {

            // Read the property only once when load the class
            InputStream inputStream = AppDelegate.class.getResourceAsStream(Constants.PROPERTY_FILE);
            properties.load(inputStream);

        } catch (IOException e) {
            CustomExceptionHandle("IOException",e);
        }
    }

    public static void Init(ServletContext context){

        //run all delegates
        sContext = context;
        System.out.println(AppParams.InitParam  +" the Ap delegate");

    }

    //write log files
    public static void CustomExceptionHandle(String type, Exception e){
        if(logger.isDebugEnabled()){
            logger.debug("This is debug");
        }
        //logs an error message with parameter
        logger.error("This is error : " + e.getMessage());

        final String message = e.getMessage();
        final String stackTrace = e.getStackTrace().toString();
        final String ExceptionType = type;

        //execute mail in background
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(new Runnable() {
            public void run() {

                try{
                    DateFormat dateFormat = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT);
                    Date date = new Date();

                    //create exception object
                    ExceptionDetails exceptionDetails = new ExceptionDetails(dateFormat.format(date), ExceptionType , message, stackTrace);

                    //initialize and send the mail
                    MailInitializer.InitAndSendErrorMessage(exceptionDetails);

                }catch (AddressException e){
                    logger.error("This is error : " + e);
                }
                catch (MessagingException e){
                    logger.error("This is error : " + e);
                }

            }
        });



    }



}
