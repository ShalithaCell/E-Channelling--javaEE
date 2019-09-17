package com.echanneling.controller;

import com.echanneling.model.Constants;
import com.echanneling.model.structure.TempUser;
import com.echanneling.service.biz.CommonOperations;
import com.echanneling.service.biz.UserManagementService;
import com.echanneling.service.support.CatchException;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author shalithasenanayaka on 2019-09-17 using IntelliJ IDEA
 */
@WebServlet(urlPatterns = {"/verification"})
public class AccountVerificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try{
            String command = request.getParameter("command");

            switch (command){
                case "login":
                    UserManagementService.GetTempUserDetails();
                    break;
            }

        }catch (ClassNotFoundException | SQLException e){
            CatchException.Handle(e);
            response.sendError(500, e.getMessage());
        }catch (Exception e){
            CatchException.Handle(e);
            response.sendError(500, e.getMessage());
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            String verificationCode = request.getParameter(Constants.VERIFICATION_CODE);

            if(verificationCode == null){
                request.getRequestDispatcher(Constants.HOMEPAGE).forward(request, response);
            }else{

                TempUser tempUser = UserManagementService.GetTempUserFromVerificationCode(verificationCode);

                if(tempUser.getUserID() == 0){
                    request.getRequestDispatcher(Constants.HOMEPAGE).forward(request, response);
                }else{
                    request.setAttribute("email", tempUser.getEmail());
                    request.setAttribute("userID", 0);
                    request.setAttribute("passCorrect", false);
                    request.getRequestDispatcher(Constants.VERIFICATIONPAGE).forward(request, response);
                }


            }
        }catch (ClassNotFoundException | SQLException e){
            CatchException.Handle(e);
            response.sendError(500, e.getMessage());
        }



    }
}
