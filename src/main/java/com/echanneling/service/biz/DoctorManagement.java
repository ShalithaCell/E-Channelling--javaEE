package com.echanneling.service.biz;

import com.echanneling.DAL.CDataAccess;
import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Constants;
import com.echanneling.model.ProcedureParams;
import com.echanneling.model.TableModels.DoctorModel;
import com.echanneling.model.TableModels.HospitalModel;
import com.echanneling.model.structure.Doctor;
import com.echanneling.model.structure.Hospital;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shalithasenanayaka on 2019-10-06 using IntelliJ IDEA
 */
public class DoctorManagement {
    public static String GetAllDoctors() throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        ProcedureParams procedureParams = new ProcedureParams();

        JTable table = CDataAccess.ExecuateProcedureToJTable(AppDelegate.GetSQLQuery(Constants.SQL_GET_ALL_DOCTORS), procedureParams.getParamSet());

        List<Object> objectList = CommonOperations.PopulateDataListFromJTable(new DoctorModel(), table);

        String json = new Gson().toJson(objectList);
        return json;
    }

    public static void AddNewDoctor(HttpServletRequest request) throws SQLException, ClassNotFoundException {

        Doctor objDoctor = new Doctor();
        objDoctor.setDoctorName((String) request.getParameter("name"));
        objDoctor.setSpeciality((String) request.getParameter("speciality"));


        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_DocName", objDoctor.getDoctorName(), false);
        procedureParams.setParamSet("_DocSpeciality", objDoctor.getSpeciality(), false);

        CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_ADD_NEW_DOCTOR), procedureParams.getParamSet());
    }
}
