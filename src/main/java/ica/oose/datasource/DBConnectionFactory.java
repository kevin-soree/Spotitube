package ica.oose.datasource;

import ica.oose.datasource.util.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Kevin on 23-3-2017.
 */
public class DBConnectionFactory { //NFN
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/spotitube";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String MSSQL_JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String MSURL = "jdbc:mysql://127.0.0.1:3306/spotitube";
    private static final String MSUSER = "sa";
    private static final String MSPASSWORD = "test";

    static {
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(DBServer dbServer) {
        Connection con = null;
        try {
            switch (dbServer) {
                case MYSQL:
                    con = DriverManager.getConnection(URL, USER, PASSWORD);
                case MSSQL:
                    Class.forName(MSSQL_JDBC_DRIVER);
                    con = DriverManager.getConnection(MSURL, MSUSER, MSPASSWORD);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}
