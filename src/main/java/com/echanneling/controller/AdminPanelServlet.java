package com.echanneling.controller;

import com.echanneling.model.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shalithasenanayaka on 2019-09-19 using IntelliJ IDEA
 */
@WebServlet(urlPatterns = "/adminPanel")
public class AdminPanelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constants.ADMINPANELPAGE).forward(request, response);
    }
}
