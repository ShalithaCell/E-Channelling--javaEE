package com.echanneling.service.biz;

import com.echanneling.DAL.CDataAccess;
import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Constants;
import com.echanneling.model.ProcedureParams;
import com.echanneling.model.structure.Doctor;
import com.echanneling.model.structure.RegistredUser;
import com.echanneling.model.structure.SessionDetails;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author shalithasenanayaka on 2019-10-07 using IntelliJ IDEA
 */
public class PaymentService {
    public static void AddPayment(HttpServletRequest request) throws SQLException, ClassNotFoundException {

        SessionDetails sessionDetails = SessionOperations.GetSessionDetails(request);
        RegistredUser registredUser = sessionDetails.getRegistredUser();

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_amount", request.getParameter("amount"), false);
        procedureParams.setParamSet("_User", registredUser.getUserID(), false);

        CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_ADD_PAYMENT), procedureParams.getParamSet());
    }
}
