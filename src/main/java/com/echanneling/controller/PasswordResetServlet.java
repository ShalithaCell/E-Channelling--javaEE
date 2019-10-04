package com.echanneling.controller;

import com.echanneling.model.Constants;
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

/**
 * @author shalithasenanayaka on 2019-10-04 using IntelliJ IDEA
 */
@WebServlet(urlPatterns = {"/passwordreset"})
public class PasswordResetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//return json
        String ReturnMessage = "{ \"result\":\"%s\", \"message\":\"%s\" }";
        try{
            //get Command from request
            String command = request.getParameter("command");

            switch (command){
                case "changepassword":
                    UserManagementService.ChangeUserPassword(request.getParameter("token"),request.getParameter("txtPassword"));
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
        try{
            String verificationCode = request.getParameter(Constants.ACCOUNT_RESET_CODE);
            Boolean success = UserManagementService.CheckVerificationCodeValidation(verificationCode);


            String Message = "";
            String Result = Constants.FALSE;
            if(success){
                Result = Constants.TRUE;

            }else {
                Result = Constants.FALSE;
                Message = "reset password this link has expired. please try to reset your password again";
            }

            request.setAttribute("Result", Result);
            request.setAttribute("Message", Message);

            request.getRequestDispatcher(Constants.PASSWORDRESETPAGE).forward(request, response);

        }catch (ClassNotFoundException | SQLException | MessagingException e){
            CatchException.Handle(e);
            request.getRequestDispatcher(Constants.ERROR_PAGE_KEY).forward(request, response);
        }
    }
}
