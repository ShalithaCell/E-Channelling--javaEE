package com.echanneling.controller;

import com.echanneling.model.Constants;
import com.echanneling.model.structure.RegistredUser;
import com.echanneling.model.structure.SessionDetails;
import com.echanneling.service.biz.CommonOperations;
import com.echanneling.service.biz.SessionOperations;
import com.echanneling.service.biz.UserManagementService;
import com.echanneling.service.support.CatchException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * @author shalithasenanayaka on 2019-10-02 using IntelliJ IDEA
 */
@WebServlet(urlPatterns = {"/account", "/user"})
public class UserAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //return json
        String ReturnMessage = "{ \"result\":\"%s\", \"message\":\"%s\" }";
        try{
            //get Command from request
            String command = request.getParameter("command");

            switch (command){
                case "update":
                    ReturnMessage = UserManagementService.UpdateRegisterUser(request);
                    break;
            }

            response.setContentType("text/plain");
            response.getWriter().write(ReturnMessage);

        }catch (SQLException | ClassNotFoundException  | ParseException
                e){
            CatchException.Handle(e);
            response.sendError(500, e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            if(!SessionOperations.CheckSessionExists(request)){
                response.sendRedirect(request.getContextPath() + "home");
            }else{
                SessionDetails objSession = SessionOperations.GetSessionDetails(request);
                RegistredUser objUser = objSession.getRegistredUser();
                request.setAttribute("UserID", objUser.getUserID());
                request.setAttribute("FirstName", objUser.getFirstName());
                request.setAttribute("LastName", objUser.getLastName());
                request.setAttribute("Email", objUser.getEmail());
                request.setAttribute("DOB", CommonOperations.DateToString(objUser.getDOB()));
                request.setAttribute("GenderID", objUser.getFK_GenderID());
                request.setAttribute("Gender", objUser.getFK_GenderID() == 1 ? "Male" : "Female");
                request.setAttribute("Contact", objUser.getContactNo());

                request.getRequestDispatcher(Constants.USERACCOUNTPAGE).forward(request, response);
            }
        }catch (Exception e){
            CatchException.Handle(e);
            request.getRequestDispatcher(Constants.ERROR_PAGE_KEY).forward(request, response);
        }




    }
}
