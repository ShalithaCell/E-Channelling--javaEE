package com.echanneling.service.support;

import com.echanneling.delegate.AppParams;
import com.echanneling.model.ExceptionDetails;
import org.apache.commons.lang3.text.StrSubstitutor;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shalithasenanayaka on 2019-08-11 using IntelliJ IDEA
 */
public class MailInitializer extends CustomMailSender {

    /**
     * Mail Initialize and send methods
     */

    public static void InitAndSendErrorMessage(ExceptionDetails exceptionDetails) throws MessagingException {

        String mailBody ="";

        Map<String, String> valuesMap = new HashMap<String, String>();

        valuesMap.put("date", exceptionDetails.getDate());
        valuesMap.put("type", exceptionDetails.getExceptionType());
        valuesMap.put("message", exceptionDetails.getMessage());
        valuesMap.put("trace", exceptionDetails.getStackTrace());

        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        mailBody = sub.replace(AppParams.ExceptionMailContent);


        CustomMailSender.generateAndSendEmail("*** Error in E Channelling PORTAL ***",mailBody);

    }

    public static void InitAndSendVerificationMessage(String userName, String URL, String UserEmail) throws MessagingException {

        String mailBody ="";

        Map<String, String> valuesMap = new HashMap<String, String>();

        valuesMap.put("username", userName);
        valuesMap.put("redirecturl", URL);

        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        mailBody = sub.replace(AppParams.VerificationMailContent);


        CustomMailSender.generateAndSendEmail("365Care Account Verification",mailBody, new String[] {UserEmail});

    }

}
