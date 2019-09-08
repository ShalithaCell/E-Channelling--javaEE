package com.echanneling.controller;

import com.echanneling.delegate.AppDelegate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shalithasenanayaka on 2019-09-07 using IntelliJ IDEA
 */
@WebServlet(urlPatterns = "/Delegate",loadOnStartup = 1,asyncSupported = true)
public class DelegateServlet extends HttpServlet {

    public void init(ServletConfig config) {
        try{
            super.init(config);
            ServletContext servletContext = getServletContext();
            AppDelegate.Init(servletContext);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
