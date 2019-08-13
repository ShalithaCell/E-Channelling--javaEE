package com.echanneling.service.support;

import com.echanneling.DAL.CDataAccess;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author shalithasenanayaka on 2019-08-12 using IntelliJ IDEA
 */
public class testSQL {
    public static void test(){
        try{
            ResultSet resultSet = CDataAccess.ExecuateQuery("select * from users;");

            DefaultTableModel jTable = DConvert.convertToJTable(resultSet);

            JSONArray jsonArray = DConvert.convertToJSONArray(resultSet);

            JSONObject jsonObject = DConvert.convertToJSONObject(resultSet, "users");

            String xml = DConvert.convertToXML(resultSet);

            String x = "ytr";
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
