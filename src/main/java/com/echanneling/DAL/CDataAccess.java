package com.echanneling.DAL;

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
            rowsImpact = 0;
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
            rowsImpact = 0;
        }
    }


    //Execute plain SQL query to ResultSet
    public static ResultSet ExecuateQuery(String query) throws SQLException, ClassNotFoundException {

        try{

            ReInitializeDBParams();

            connection = MySQLAccess.getConnection();

            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery(query);

            return resultSet;
        }
        catch (SQLException | ClassNotFoundException e){
            throw e;
        }
        finally {
            rowsImpact = 0;
        }

    }

    //Execute SQL query with parameters to ResultSet (Secure method - This way can prevent sql injection)
    public static ResultSet ExecuateQuery(String query, String[] args) throws SQLException, ClassNotFoundException {

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
            resultSet = preparedStatement.executeQuery(query);

            return resultSet;
        }
        catch (SQLException | ClassNotFoundException e){
            throw e;
        }
        finally {
            rowsImpact = 0;
        }
    }


}
