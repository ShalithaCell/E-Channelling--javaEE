package com.echanneling.DAL;

import java.sql.*;

/**
 * @author shalithasenanayaka on 2019-08-12 using IntelliJ IDEA
 */
public class DBDataAccess {
    protected static Connection connection = null;
    protected static Statement statement = null;
    protected static PreparedStatement preparedStatement = null;
    protected static ResultSet resultSet = null;

    protected  static int rowsImpact = 0;

    public DBDataAccess() {
        connection = null;
        statement = null;
        preparedStatement = null;
        resultSet = null;
    }

    protected static void ReInitializeDBParams() throws SQLException {

        if(connection != null || !connection.isClosed()){
            connection.close();
        }
        if(statement != null){
            statement = null;
        }

        if(preparedStatement != null){
            preparedStatement = null;
        }

        if (resultSet != null){
            resultSet = null;
        }

        rowsImpact = 0;

    }

}
