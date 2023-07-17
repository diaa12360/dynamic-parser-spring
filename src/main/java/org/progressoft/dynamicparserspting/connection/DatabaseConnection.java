package org.progressoft.dynamicparserspting.connection;

import java.sql.*;
import java.util.Properties;


public class DatabaseConnection {

    private final String dbName;



    public DatabaseConnection(String dbName) {
        this.dbName = dbName;
    }


    public Connection setConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        Properties connectionProps = new Properties();
        String userName = "root";
        connectionProps.put("user", userName);
        String password = "Deya@12@";
        connectionProps.put("password", password);

        String serverName = "localhost";
        String portNumber = "3306";
        String dbms = "mysql";
        conn = DriverManager.getConnection(
                "jdbc:" + dbms + "://" +
                        serverName +
                        ":" + portNumber + "/" + dbName,
                connectionProps);
        System.out.println("Connected to database");
        return conn;
    }
    public static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
        return resultSet.next();
    }

    public static boolean hasRecord(Connection connection, String value, String table, String key) throws SQLException {
        String sql = "Select " + key + " from " + table + " where " + key + " = " + value;
        ResultSet rs = connection.createStatement().executeQuery(sql);
        return rs.next();
    }
}
