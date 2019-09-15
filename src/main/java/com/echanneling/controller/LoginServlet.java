package com.echanneling.controller;

import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Constants;
import com.echanneling.model.UserMessages;
import com.echanneling.service.biz.SessionOperations;
import com.echanneling.service.biz.UserManagementService;
import com.echanneling.service.support.CatchException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author shalithasenanayaka on 2019-08-11 using IntelliJ IDEA
 */
@WebServlet(urlPatterns = {"/login", "/register", "/sighup"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //return json
        String ReturnMessage = "{ \"result\":\"%s\", \"message\":\"%s\" }";
        try{
            //get Command from request
            String command = request.getParameter("command");

            switch (command){
                case "login":
                    ReturnMessage = UserManagementService.RegisterTempUser(request);
                    break;
            }

            response.setContentType("text/plain");
            response.getWriter().write(ReturnMessage);

        }catch (SQLException | ClassNotFoundException | MessagingException e){
            CatchException.Handle(e);
            response.sendError(500, e.getMessage());
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionOperations.setPageToSession(request,Constants.LOGIN_PAGE_KEY);

        request.getRequestDispatcher(Constants.LOGINPAGE).forward(request, response);
    }
}
