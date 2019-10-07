package com.echanneling.controller;

import com.echanneling.model.Constants;
import com.echanneling.service.biz.DoctorManagement;
import com.echanneling.service.biz.HospitalManagementService;
import com.echanneling.service.biz.SessionOperations;
import com.echanneling.service.support.CatchException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/adminPanel")
public class AdminPanelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //return json
        String ReturnMessage = "{ \"result\":\"%s\", \"message\":\"%s\" }";

        try {

            //get Command from request
            String command = request.getParameter("command");
            switch (command){
                case "getHospital":
                    ReturnMessage = HospitalManagementService.GetAllHospitalList();
                    break;
                case "newitem":
                    HospitalManagementService.AddNewHospital(request);
                    ReturnMessage = String.format(ReturnMessage, Constants.TRUE, "New item added successfully.");
                    break;
                case "getHospitalByID":
                    ReturnMessage = HospitalManagementService.GetHospitalByID(request.getParameter("HospitalID"));
                    break;

                case "updatehospital":
                    HospitalManagementService.UpdateHospital(request);
                    ReturnMessage = String.format(ReturnMessage, Constants.TRUE, "item updated !");
                    break;
                case "RemoveHospital":
                    HospitalManagementService.RemoveHospital(request);
                    ReturnMessage = String.format(ReturnMessage, Constants.TRUE, "item deleted !");
                    break;
                case "getDoctors":
                    ReturnMessage = DoctorManagement.GetAllDoctors();
                    break;
                case "addDoctors":
                    DoctorManagement.AddNewDoctor(request);
                    ReturnMessage = String.format(ReturnMessage, Constants.TRUE, "New item added successfully.");
                    break;
                case "GetDoctorByID":
                    ReturnMessage = DoctorManagement.GetDoctorByID(request.getParameter("DoctorID"));
                    break;
                case "updateDoctor":
                    DoctorManagement.UpdateDoctor(request);
                    ReturnMessage = String.format(ReturnMessage, Constants.TRUE, "Doctor Updated");
                    break;
                case "RemoveDoctor":
                    DoctorManagement.RemoveDoctor(request);
                    ReturnMessage = String.format(ReturnMessage, Constants.TRUE, "item deleted !");
                    break;
            }

            response.setContentType("text/plain");
            response.getWriter().write(ReturnMessage);

        } catch (SQLException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            CatchException.Handle(e);
            response.sendError(500, e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            if(!SessionOperations.CheckSessionExists(request)){
                response.sendRedirect(request.getContextPath() + "home");
            }else{
                request.getRequestDispatcher(Constants.ADMINPANELPAGE).forward(request, response);
            }
        }catch (Exception e){
            CatchException.Handle(e);
            request.getRequestDispatcher(Constants.ERROR_PAGE_KEY).forward(request, response);
        }



    }
}
