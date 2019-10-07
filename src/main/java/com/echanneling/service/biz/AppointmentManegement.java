package com.echanneling.service.biz;

import com.echanneling.DAL.CDataAccess;
import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Constants;
import com.echanneling.model.ProcedureParams;
import com.echanneling.model.structure.Appointment;
import com.echanneling.model.structure.Doctor;
import com.echanneling.model.structure.RegistredUser;
import com.echanneling.model.structure.SessionDetails;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author shalithasenanayaka on 2019-10-07 using IntelliJ IDEA
 */
public class AppointmentManegement {
    public static boolean AddAppoinment(HttpServletRequest request) throws SQLException, ClassNotFoundException {

        if(!SessionOperations.CheckSessionExists(request)){
            return false;
        }

        Appointment objAppointment = new Appointment();
        objAppointment.setFullName((String) request.getParameter("name"));
        objAppointment.setAddress((String) request.getParameter("adress"));
        objAppointment.setGuardian((String) request.getParameter("gurdian"));
        objAppointment.setEmail((String) request.getParameter("emailPatient"));
        objAppointment.setPhone((String) request.getParameter("phone"));
        objAppointment.setAge(Integer.parseInt(request.getParameter("Age")));



        SessionDetails sessionDetails = SessionOperations.GetSessionDetails(request);
        RegistredUser registredUser = sessionDetails.getRegistredUser();

        objAppointment.setFK_UserID(registredUser.getUserID());
        objAppointment.setFK_Hospital(Integer.parseInt(request.getParameter("hospital")));
        objAppointment.setFK_Doctor(Integer.parseInt(request.getParameter("doctor")));

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_FullName", objAppointment.getFullName(), false);
        procedureParams.setParamSet("_Address", objAppointment.getAddress(), false);
        procedureParams.setParamSet("_Guardian", objAppointment.getGuardian(), false);
        procedureParams.setParamSet("_Email", objAppointment.getEmail(), false);
        procedureParams.setParamSet("_Phone", objAppointment.getPhone(), false);
        procedureParams.setParamSet("_Age", objAppointment.getAge(), false);
        procedureParams.setParamSet("_FK_UserID", objAppointment.getFK_UserID(), false);
        procedureParams.setParamSet("_FK_Hospital", objAppointment.getFK_Hospital(), false);
        procedureParams.setParamSet("_FK_Doctor", objAppointment.getFK_Doctor(), false);

        CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_ADD_APPOINTMENT), procedureParams.getParamSet());

        return true;
    }
}
