package com.echanneling.DAL;

import com.echanneling.service.biz.CommonOperations;
import com.echanneling.service.support.DConvert;
import com.mysql.cj.jdbc.CallableStatement;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shalithasenanayaka on 2019-08-12 using IntelliJ IDEA
 */
public class CDataAccess extends DBDataAccess{

    //Execute plain SQL query
    public static boolean ExecuateNonQuery(String query) throws SQLException, ClassNotFoundException {

        try{
            ReInitializeDBParams();

            connection = MySQLAccess.getConnection();

            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();

            // Result set get the result of the SQL query
            rowsImpact = statement.executeUpdate(query);

            return rowsImpact > 0;
        }
        catch (SQLException | ClassNotFoundException e){
            throw e;
        }
        finally {
            ReInitializeDBParams();
        }

    }

    //Execute SQL query with parameters (Secure method - This way can prevent sql injection)
    public static boolean ExecuateNonQuery(String query, String[] args) throws SQLException, ClassNotFoundException {

        try{
            ReInitializeDBParams();

            connection = MySQLAccess.getConnection();

            // Statements allow to issue SQL queries to the database
            preparedStatement = connection.prepareStatement(query);

            for(int i = 0; i < args.length; i++){
                // Parameters start with 1
                preparedStatement.setString(i+1, args[i]);
            }

            // Result set get the result of the SQL query
            rowsImpact = preparedStatement.executeUpdate(query);

            return rowsImpact > 0;
        }
        catch (SQLException | ClassNotFoundException e){
            throw e;
        }
        finally {
            ReInitializeDBParams();
        }
    }


    //Execute plain SQL query to ResultSet
    public static JTable ExecuateQuery(String query) throws SQLException, ClassNotFoundException {

        try{

            connection = MySQLAccess.getConnection();

            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery(query);

            JTable jTable = DConvert.convertToJTable(resultSet);

            return jTable;
        }
        catch (SQLException | ClassNotFoundException e){
            throw e;
        }
        finally {
            ReInitializeDBParams();
        }

    }

    //Execute SQL query with parameters to ResultSet (Secure method - This way can prevent sql injection)
    public static JTable ExecuateQuery(String query, String[] args) throws SQLException, ClassNotFoundException {

        try{

            connection = MySQLAccess.getConnection();

            // Statements allow to issue SQL queries to the database
            preparedStatement = connection.prepareStatement(query);

            for(int i = 0; i < args.length; i++){
                // Parameters start with 1
                preparedStatement.setString(i+1, args[i]);
            }

            // Result set get the result of the SQL query
            resultSet = preparedStatement.executeQuery(query);

            JTable jTable = DConvert.convertToJTable(resultSet);

            return jTable;
        }
        catch (SQLException | ClassNotFoundException e){
            throw e;
        }
        finally {
            ReInitializeDBParams();
        }
    }

    public static HashMap<String, String> ExecuateProcedure (String procedureName, Triplet<String, Object, Boolean>[] paramSet) throws SQLException, ClassNotFoundException{
        /**
         * procedureName = Stored procedure name
         *
         * Quartet - { param name, param value, is output}
         */

        connection = MySQLAccess.getConnection();

        String procedure = CommonOperations.InitSQLProcedure(procedureName, paramSet);

        CallableStatement stmt= (CallableStatement) connection.prepareCall(procedure);

        int counter = 1;
        HashMap<String ,Integer> outputIndexes = new HashMap<String ,Integer>();

        for (Triplet<String, Object, Boolean> item: paramSet) {
            if(item.getValue2()){
                //Set OUT parameter
                stmt.registerOutParameter(counter, Types.VARCHAR);
                outputIndexes.put(item.getValue0(), counter);
                counter++;
                continue;
            }

            if((item.getValue1() instanceof String)){
                stmt.setString(counter, (String) item.getValue1());
            }

            if((item.getValue1() instanceof Integer)){
                stmt.setString(counter, Integer.toString((Integer) item.getValue1()));
            }

            if((item.getValue1() instanceof Boolean)){
                stmt.setString(counter, (Boolean) item.getValue1() ? "1" : "0");
            }
            counter++;
        }

        //Execute stored procedure
        stmt.execute();

        HashMap<String, String> outputParamSet = new HashMap<>();
        for (Map.Entry<String, Integer> entry : outputIndexes.entrySet()) {
            outputParamSet.put(entry.getKey(), stmt.getString(entry.getValue()));
        }

        return outputParamSet;
    }


}
