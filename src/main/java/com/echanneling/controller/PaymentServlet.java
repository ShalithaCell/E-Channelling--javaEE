package com.echanneling.controller;

import com.echanneling.model.Constants;
import com.echanneling.service.biz.CommonOperations;
import com.echanneling.service.biz.PaymentService;
import com.echanneling.service.biz.SessionOperations;
import com.echanneling.service.support.CatchException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author shalithasenanayaka on 2019-10-07 using IntelliJ IDEA
 */
@WebServlet(urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {

            if(!SessionOperations.CheckSessionExists(request)){
                request.getRequestDispatcher(Constants.HOMEPAGE).forward(request, response);
            }

            PaymentService.AddPayment(request);
            request.getRequestDispatcher(Constants.HOMEPAGE).forward(request, response);

        } catch (Exception e) {
            CatchException.Handle(e);
            response.sendError(500, e.getMessage());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!SessionOperations.CheckSessionExists(request)){
            request.getRequestDispatcher(Constants.HOMEPAGE).forward(request, response);
        }else{
            request.getRequestDispatcher(Constants.PAYMENTPAGE).forward(request, response);
        }

    }
}
