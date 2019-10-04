package com.echanneling.service.biz;

import com.echanneling.DAL.CDataAccess;
import com.echanneling.delegate.AppDelegate;
import com.echanneling.delegate.AppParams;
import com.echanneling.model.Constants;
import com.echanneling.model.ProcedureParams;
import com.echanneling.model.TableModels.RegistedUserModel;
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

    public static RegistedUserModel GetRegisterUserDetails(int UserID) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_UserID", UserID, false);

        JTable table = CDataAccess.ExecuateProcedureToJTable(AppDelegate.GetSQLQuery(Constants.SQL_GET_REGISTED_USER), procedureParams.getParamSet());

        List<Object> objectList = CommonOperations.PopulateDataListFromJTable(new RegistedUserModel(), table);

        RegistedUserModel registedUserModel = new RegistedUserModel();

        if(objectList.size() != 0){
            registedUserModel = (RegistedUserModel) objectList.get(0);
        }

        return registedUserModel;
    }

    public static void setLoginSession(RegistedUserModel objUser, HttpServletRequest request){
        RegistredUser registredUser = new RegistredUser();

        registredUser.setUserID(objUser.UserID);
        registredUser.setFK_RoleID(objUser.FK_RoleID);
        registredUser.setFirstName(objUser.FirstName);
        registredUser.setLastName(objUser.LastName);
        registredUser.setEmail(objUser.Email);
        registredUser.setFK_GenderID(objUser.FK_GenderID);
        registredUser.setDOB(objUser.DOB);
        registredUser.setContactNo(objUser.ContactNo);

        SessionOperations.SessionInit(request, registredUser);
    }

    public static String LogIn(HttpServletRequest request) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        //return json message format
        String ReturnMessage = "{ \"result\":\"%s\", \"message\":\"%s\" }";

        String Email = request.getParameter("txtEmail");
        String Password = EncryptionModule.Encrypt(request.getParameter("password"));

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_Email", Email, false);
        procedureParams.setParamSet("_Password", Password, false);
        procedureParams.setParamSet("_Result", 0, true);
        procedureParams.setParamSet("_UserID", 0, true);

        HashMap<String, String> params = CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_USER_LOGIN), procedureParams.getParamSet());

        if(params.get("_Result").equals("1")){
            ReturnMessage = String.format(ReturnMessage, Constants.TRUE, Constants.SUCCESS);
            setLoginSession(GetRegisterUserDetails(Integer.parseInt(params.get("_UserID"))), request);
        }else{
            ReturnMessage = String.format(ReturnMessage, Constants.FALSE, "email or password is invalid !");
        }

        return ReturnMessage;
    }

    public static String UpdateRegisterUser(HttpServletRequest request) throws ParseException, SQLException, ClassNotFoundException {

        RegistredUser objUser = new RegistredUser();
        String ReturnMessage = "{ \"result\":\"%s\", \"message\":\"%s\" }";

        objUser.setFirstName(request.getParameter("first_name"));
        objUser.setLastName(request.getParameter("last_name"));

        SimpleDateFormat formatter1=new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT_WEB_INPUT);

        objUser.setDOB(formatter1.parse(request.getParameter("birthday")));
        objUser.setFK_GenderID(Integer.parseInt(request.getParameter("gender")));
        objUser.setContactNo(request.getParameter("phone"));
        objUser.setUserID(Integer.parseInt(request.getParameter("UserID")));
        objUser.setEmail((String)request.getSession().getAttribute(Constants.SESSION_Email));

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_UserID", objUser.getUserID(), false);
        procedureParams.setParamSet("_FirstName", objUser.getFirstName(), false);
        procedureParams.setParamSet("_LastName", objUser.getLastName(), false);
        procedureParams.setParamSet("_FK_GenderID", objUser.getFK_GenderID(), false);
        procedureParams.setParamSet("_ContactNo", objUser.getContactNo(), false);
        procedureParams.setParamSet("_DOB", objUser.getDOB(), false);
        procedureParams.setParamSet("_Password", EncryptionModule.Encrypt(request.getParameter("password")), false);

        SessionOperations.UpdateSession(request, objUser);

        CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_UPDATE_USER), procedureParams.getParamSet());
        ReturnMessage = String.format(ReturnMessage, Constants.TRUE, "User Account Updated !");
        return ReturnMessage;
    }

    public static String SendPasswordResetLink(String Email) throws SQLException, ClassNotFoundException, MessagingException {


        String ReturnMessage = "{ \"result\":\"%s\", \"message\":\"%s\" }";

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_email", Email, false);
        procedureParams.setParamSet("_UserID", 0, true);

        HashMap<String, String> params = CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_GET_USER_BY_EMAIL), procedureParams.getParamSet());

        if(params.get("_UserID").equals("0")){
            ReturnMessage = String.format(ReturnMessage, Constants.FALSE, "cannot finds user for " + Email);
            return ReturnMessage;
        }

        String TempCode = CommonOperations.GetSaltString(Constants.PASSWORD_RESET_TOKEN_LINK);

        procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_Token", TempCode, false);
        procedureParams.setParamSet("_UserID", Integer.parseInt(params.get("_UserID")), false);

        CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_SAVE_RESET_TOKEN), procedureParams.getParamSet());

        MailInitializer.InitAndSendForgetPasswordResetMessage(AppParams.PasswordResetURL + TempCode, Email);
        ReturnMessage = String.format(ReturnMessage, Constants.TRUE, "password recovery link sent to your email");

        return ReturnMessage;
    }


    public static Boolean CheckVerificationCodeValidation(String token) throws SQLException, ClassNotFoundException, MessagingException {

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_token", token, false);
        procedureParams.setParamSet("_result", 0, true);

        HashMap<String, String> params = CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_VALIDATE_TOKEN), procedureParams.getParamSet());

        Boolean success = false;

        if(params.get("_result").equals("0")){
            success = false;
        }else{
            success = true;
        }

        return  success;

    }

    public static void ChangeUserPassword(String token, String Password) throws SQLException, ClassNotFoundException, MessagingException {

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_token", token, false);
        procedureParams.setParamSet("_password", EncryptionModule.Encrypt(Password) , false);

        CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_PASSWORD_CHANGE), procedureParams.getParamSet());

    }


}
