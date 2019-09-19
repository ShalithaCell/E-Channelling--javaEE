package com.echanneling.service.biz;

import com.echanneling.DAL.CDataAccess;
import com.echanneling.delegate.AppDelegate;
import com.echanneling.delegate.AppParams;
import com.echanneling.model.Constants;
import com.echanneling.model.ProcedureParams;
import com.echanneling.model.TableModels.TempUserModel;
import com.echanneling.model.UserMessages;
import com.echanneling.model.structure.RegistredUser;
import com.echanneling.model.structure.TempUser;
import com.echanneling.service.support.MailInitializer;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

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

    public static String RegisterTempUser(HttpServletRequest request) throws SQLException, ClassNotFoundException, MessagingException {

        //return json message format
        String ReturnMessage = "{ \"result\":\"%s\", \"message\":\"%s\" }";

        String email = request.getParameter("txtEmail");
        String password = EncryptionModule.Encrypt(request.getParameter("password"));

        if(checkUserEmailIsExists(email)){
            ReturnMessage = String.format(ReturnMessage, Constants.FALSE, UserMessages.EMAIL_DUPLICATE);
            return ReturnMessage;
        }

        //create user object and assign values
        TempUser objUser = new TempUser();
        objUser.setEmail(email);
        objUser.setPassword(password);
        objUser.setVerificationCode(CommonOperations.GetSaltString(Constants.VERIFICATION_CODE_SIZE));
        objUser.setRegistrationDate(CommonOperations.getCurrentLocalDateTime());
        objUser.setActive(true);

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_Email", objUser.getEmail(), false);
        procedureParams.setParamSet("_UPassword", objUser.getPassword(), false);
        procedureParams.setParamSet("_Verification_Code", objUser.getVerificationCode(), false);
        procedureParams.setParamSet("_RegisteredDate", objUser.getRegistrationDate(), false);
        procedureParams.setParamSet("_IsActive", objUser.isActive(), false);

        CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_REGISTER_TEMP_USER), procedureParams.getParamSet());
        MailInitializer.InitAndSendVerificationMessage("User", AppParams.VerificationURL+objUser.getVerificationCode(), objUser.getEmail());
        ReturnMessage = String.format(ReturnMessage, Constants.TRUE, Constants.SUCCESS);

        return ReturnMessage;
    }

    public static TempUser GetTempUserFromVerificationCode(String verificationCode) throws SQLException, ClassNotFoundException {

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_verificationCode", verificationCode, false);
        procedureParams.setParamSet("_TempUserID", "0", true);
        procedureParams.setParamSet("_Email", "", true);

        HashMap<String, String> params = CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_GET_TEMP_USER), procedureParams.getParamSet());

        TempUser tempUser = new TempUser();

        if(!params.get("_TempUserID").equals("0")){
            tempUser.setUserID(Integer.parseInt(params.get("_TempUserID")));
            tempUser.setEmail(params.get("_Email"));
        }else{
            tempUser.setUserID(0);
        }

        return tempUser;


    }

    public static TempUserModel GetTempUserDetails(String verificationCode, String password) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_verificationCode", verificationCode, false);
        procedureParams.setParamSet("_password", password, false);

        JTable table = CDataAccess.ExecuateProcedureToJTable(AppDelegate.GetSQLQuery(Constants.SQL_GET_TEMP_USER_DETAILS), procedureParams.getParamSet());

        List<Object> objectList = CommonOperations.PopulateDataListFromJTable(new TempUserModel(), table);

        TempUserModel tempUserModel = new TempUserModel();

        if(objectList.size() != 0){
            tempUserModel = (TempUserModel) objectList.get(0);
        }

        return tempUserModel;
    }

    public static void RegisterUser(HttpServletRequest request) throws ParseException, SQLException, ClassNotFoundException {

        RegistredUser registredUser = new RegistredUser();

        registredUser.setFirstName(request.getParameter("first_name"));
        registredUser.setLastName(request.getParameter("last_name"));

        SimpleDateFormat formatter1=new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT_WEB_INPUT);

        registredUser.setDOB(formatter1.parse(request.getParameter("birthday")));
        registredUser.setFK_GenderID(Integer.parseInt(request.getParameter("gender")));
        registredUser.setContactNo(request.getParameter("phone"));

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_FK_RoleID", Constants.STANDUSER_ROLE_CODE, false);
        procedureParams.setParamSet("_FirstName", registredUser.getFirstName(), false);
        procedureParams.setParamSet("_LastName", registredUser.getLastName(), false);
        procedureParams.setParamSet("_TempUserID", request.getParameter("TempUserID"), false);
        procedureParams.setParamSet("_FK_GenderID", registredUser.getFK_GenderID(), false);
        procedureParams.setParamSet("_ContactNo", registredUser.getContactNo(), false);
        procedureParams.setParamSet("_DOB", registredUser.getDOB(), false);
        procedureParams.setParamSet("_UserID", 0, true);

        HashMap<String, String> params = CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_REGISTER_USER), procedureParams.getParamSet());

        System.out.println(params.get("_UserID"));

    }

}
