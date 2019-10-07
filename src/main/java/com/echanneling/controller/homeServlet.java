package com.echanneling.controller;

import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Constants;
import com.echanneling.service.biz.AppointmentManegement;
import com.echanneling.service.biz.DoctorManagement;
import com.echanneling.service.biz.HospitalManagementService;
import com.echanneling.service.support.CatchException;

import java.io.IOException;
import java.sql.SQLException;

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
		//return json
		String ReturnMessage = "{ \"result\":\"%s\", \"message\":\"%s\" }";

		try {

			//get Command from request
			String command = request.getParameter("command");
			switch (command){
				case "book":
					boolean success = AppointmentManegement.AddAppoinment(request);
					if(success){
						ReturnMessage = String.format(ReturnMessage, Constants.TRUE, "Appointment Added Successfully");
					}else{
						ReturnMessage = String.format(ReturnMessage, Constants.FALSE, "Please log to the site first");
					}
					break;
			}

			response.setContentType("text/plain");
			response.getWriter().write(ReturnMessage);

		} catch (SQLException | ClassNotFoundException e) {
			CatchException.Handle(e);
			response.sendError(500, e.getMessage());
		}

	}
	
}
