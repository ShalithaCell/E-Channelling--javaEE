package com.echanneling.service.biz;

import com.echanneling.model.Operations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author shalithasenanayaka on 2019-08-11 using IntelliJ IDEA
 */
public class SessionOperations extends Operations {
    public static void setPageToSession(HttpServletRequest request,String value){
        HttpSession session = request.getSession(false);

        if(session == null)
            session = request.getSession(true);


        session.setAttribute("page", value);

        //setting session to expiry in 30 mins
        session.setMaxInactiveInterval(30*60);

    }
}
