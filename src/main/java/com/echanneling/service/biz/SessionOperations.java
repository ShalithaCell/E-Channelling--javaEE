package com.echanneling.service.biz;

import com.echanneling.model.Constants;
import com.echanneling.model.Operations;
import com.echanneling.model.structure.RegistredUser;
import com.echanneling.model.structure.SessionDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * @author shalithasenanayaka on 2019-08-11 using IntelliJ IDEA
 */
public class SessionOperations extends Operations {
    public static void SessionInit(HttpServletRequest request, RegistredUser user){
        //get the old session and invalidate
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        //generate a new session
        session = request.getSession(true);

        session.setAttribute(Constants.SESSION_USER_ID, user.getUserID());
        session.setAttribute(Constants.SESSION_RoleID, user.getFK_RoleID());
        session.setAttribute(Constants.SESSION_FirstName, user.getFirstName());
        session.setAttribute(Constants.SESSION_LastName, user.getLastName());
        session.setAttribute(Constants.SESSION_Email, user.getEmail());
        session.setAttribute(Constants.SESSION_GenderID, user.getFK_GenderID());
        session.setAttribute(Constants.SESSION_DOB, user.getDOB());

        //setting session to expiry in 15 mins
        session.setMaxInactiveInterval(15*60);

    }

    public static boolean CheckSessionExists(HttpServletRequest request){
        session = request.getSession(false);
        if (session != null) {
            if(session.getAttribute(Constants.SESSION_USER_ID) == null)
                return false;
            else
                return true;
        }
        return false;
    }

    public static SessionDetails GetSessionDetails(HttpServletRequest request){
        SessionDetails sessionDetails = new SessionDetails();

        RegistredUser registredUser = new RegistredUser();

        session = request.getSession(true);

        registredUser.setUserID((Integer)session.getAttribute(Constants.SESSION_USER_ID));
        registredUser.setFK_RoleID((Integer)session.getAttribute(Constants.SESSION_RoleID));
        registredUser.setFirstName((String) session.getAttribute(Constants.SESSION_FirstName));
        registredUser.setLastName((String) session.getAttribute(Constants.SESSION_LastName));
        registredUser.setEmail((String) session.getAttribute(Constants.SESSION_Email));
        registredUser.setFK_GenderID((Integer) session.getAttribute(Constants.SESSION_GenderID));
        registredUser.setDOB((Date) session.getAttribute(Constants.SESSION_DOB));

        sessionDetails.setRegistredUser(registredUser);

        return sessionDetails;

    }


}
