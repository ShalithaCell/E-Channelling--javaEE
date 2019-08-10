package com.echanneling.delegate;

import com.echanneling.model.Constants;
import com.echanneling.service.support.CustomMailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.io.InputStream;
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

    static {
        try {

            // Read the property only once when load the class
            InputStream inputStream = AppDelegate.class.getResourceAsStream(Constants.PROPERTY_FILE);
            properties.load(inputStream);

        } catch (IOException e) {
            CustomExceptionHandle(e.getMessage());
        }
    }

    //write log files
    public static void CustomExceptionHandle(String message){
        if(logger.isDebugEnabled()){
            logger.debug("This is debug");
        }

        //logs an error message with parameter
        logger.error("This is error : " + message);

        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(new Runnable() {
            public void run() {

                try{
                    CustomMailSender.generateAndSendEmail("subject", "body");
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
