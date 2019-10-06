package com.echanneling.service.biz;

import com.echanneling.DAL.CDataAccess;
import com.echanneling.delegate.AppDelegate;
import com.echanneling.model.Constants;
import com.echanneling.model.ProcedureParams;
import com.echanneling.model.TableModels.HospitalModel;
import com.echanneling.model.structure.Hospital;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shalithasenanayaka on 2019-10-05 using IntelliJ IDEA
 */
public class HospitalManagementService {
    public static String GetAllHospitalList() throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        ProcedureParams procedureParams = new ProcedureParams();

        JTable table = CDataAccess.ExecuateProcedureToJTable(AppDelegate.GetSQLQuery(Constants.SQL_GET_ALL_HOSPITALS), procedureParams.getParamSet());

        List<Object> objectList = CommonOperations.PopulateDataListFromJTable(new HospitalModel(), table);

        String json = new Gson().toJson(objectList);
        return json;
    }

    public static void AddNewHospital(HttpServletRequest request) throws SQLException, ClassNotFoundException {

        Hospital objHospital = new Hospital();
        objHospital.setDescription((String) request.getParameter("HosName"));
        objHospital.setAddress((String) request.getParameter("address"));


        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_name", objHospital.getDescription(), false);
        procedureParams.setParamSet("_address", objHospital.getAddress(), false);

        CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_ADD_NEW_HOSPITAL), procedureParams.getParamSet());
    }

    public static String GetHospitalByID(String HospitalID) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_HospitalID", HospitalID, false);
        JTable table = CDataAccess.ExecuateProcedureToJTable(AppDelegate.GetSQLQuery(Constants.SQL_GET_HOSPITAL_BY_ID), procedureParams.getParamSet());

        List<Object> objectList = CommonOperations.PopulateDataListFromJTable(new HospitalModel(), table);

        String json = new Gson().toJson(objectList);
        return json;
    }

    public static void UpdateHospital(HttpServletRequest request) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        Hospital hospital = new Hospital();

        hospital.setHospitalID(Integer.parseInt(request.getParameter("hospitalID")));
        hospital.setDescription((String) request.getParameter("hospitalname"));
        hospital.setAddress((String) request.getParameter("hospitalAddress"));

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("_HospitalID", hospital.getHospitalID(), false);
        procedureParams.setParamSet("_Description", hospital.getDescription(), false);
        procedureParams.setParamSet("_Address", hospital.getAddress(), false);
        CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_UPDATE_HOSPITAL), procedureParams.getParamSet());

    }

    public static void RemoveHospital(HttpServletRequest request) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        ProcedureParams procedureParams = new ProcedureParams();
        procedureParams.setParamSet("HosID", Integer.parseInt(request.getParameter("hospitalID")), false);

        CDataAccess.ExecuateProcedure(AppDelegate.GetSQLQuery(Constants.SQL_DELETE_HOSPITAL), procedureParams.getParamSet());

    }

}
