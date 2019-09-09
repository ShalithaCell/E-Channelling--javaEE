package com.echanneling.service.biz;

import com.echanneling.DAL.CDataAccess;
import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Constants;
import com.echanneling.model.ProcedureParams;
import org.javatuples.Quartet;
import org.javatuples.Triplet;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

/**
 * @author shalithasenanayaka on 2019-09-03 using IntelliJ IDEA
 */
public class UserManagementService {

    public static boolean checkUserEmailIsExists(String email) throws SQLException, ClassNotFoundException {

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("UserEmail", email, false);
        procedureParams.setParamSet("result", "0", true);

        HashMap<String, String> params = CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_CHECK_USER_EMAIL_DUPLICATES), procedureParams.getParamSet());

        if(params.get("result").equals("0"))
            return false;
        else
            return true;
    }

    public static String RegisterTempUser(HttpServletRequest request){
        return "";
    }

}
