package com.echanneling.controller;

import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Constants;
import com.echanneling.service.biz.SessionOperations;
import com.echanneling.service.biz.UserManagementService;
import com.echanneling.service.support.CatchException;

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

        try{
            //get Command from request
            String command = request.getParameter("command");

            switch (command){
                case "login":
                    UserManagementService.checkUserEmailIsExists("shalithax@gmail.com");
                    break;
            }
        }catch (SQLException | ClassNotFoundException e){
            CatchException.Handle(e);
        }

        String greetings = "Hello ";
        response.setContentType("text/plain");
        response.getWriter().write(greetings);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionOperations.setPageToSession(request,Constants.LOGIN_PAGE_KEY);

        request.getRequestDispatcher(Constants.LOGINPAGE).forward(request, response);
    }
}
