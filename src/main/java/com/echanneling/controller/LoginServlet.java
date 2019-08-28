package com.echanneling.controller;

import com.echanneling.model.Constants;
import com.echanneling.service.biz.SessionOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shalithasenanayaka on 2019-08-11 using IntelliJ IDEA
 */
@WebServlet(urlPatterns = {"/login", "/register", "/sighup"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().write("greetings");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionOperations.setPageToSession(request,Constants.LOGIN_PAGE_KEY);

        request.getRequestDispatcher(Constants.LOGINPAGE).forward(request, response);
    }
}
