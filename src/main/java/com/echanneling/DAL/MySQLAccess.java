package com.echanneling.DAL;

import com.echanneling.delegate.AppParams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author shalithasenanayaka on 2019-08-12 using IntelliJ IDEA
 */
public class MySQLAccess {

    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        if (connection == null || connection.isClosed()) {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(AppParams.SQLDriver);

            // Setup the connection with the DB
            connection = DriverManager.getConnection(AppParams.ConnectionString);
        }

        return connection;

    }



}
