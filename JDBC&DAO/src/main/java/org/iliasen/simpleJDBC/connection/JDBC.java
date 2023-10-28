package org.iliasen.simpleJDBC.connection;

import java.sql.*;

public class JDBC {

    public static Connection connection = null;


    public static void connect() throws SQLException{
        String url = "jdbc:mariadb://localhost:3306/jbdc";
        String username = "root";
        String password = "7756";
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);
            if(connection == null){
                throw new SQLException();
            }else System.out.println("Success");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Closing connection");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection!");
        }
    }
}
