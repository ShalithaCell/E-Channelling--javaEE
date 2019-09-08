package com.echanneling.controller;

import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Constants;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/home")
public class homeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("name", "shalitha");
		request.setAttribute("title", AppDelegate.properties.getProperty("projectName"));

		//ArithmeticException e = new ArithmeticException("test");

		//CatchException.Handle(e);


		request.getRequestDispatcher(Constants.HOMEPAGE).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
}
