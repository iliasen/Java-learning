package org.iliasen.simpleJDBC.testOfTables;

import org.iliasen.simpleJDBC.connection.JDBC;

import java.sql.*;
import java.util.*;

public class CreateTables {
    public static void main(String[] args) {
        Statement stmt = null;
        try {
            System.out.println("This will DELETE all data, do you want to continue? (y/n) ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            input.toLowerCase();
            if (input.equals("y")) {
                JDBC.connect();
                stmt = JDBC.connection.createStatement();

                String drop = "DROP TABLE IF EXISTS student";
                stmt.executeUpdate(drop);
                System.out.println("Table was deleted");

                String studentTab = "CREATE TABLE student" + "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                        + "name CHAR(20), " + "age INT(9))";
                stmt.executeUpdate(studentTab);
                System.out.println("Created student table");
                InsertTestData.updateTables();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {         // Finally block, used to close resources
            if (stmt != null) {
                JDBC.close();
            }
        }
    }
}