package com.echanneling.DAL;

import com.echanneling.service.support.DConvert;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

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


}
