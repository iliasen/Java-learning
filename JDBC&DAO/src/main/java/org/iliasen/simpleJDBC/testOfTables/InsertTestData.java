package org.iliasen.simpleJDBC.testOfTables;

import org.iliasen.simpleJDBC.connection.JDBC;

import java.sql.SQLException;
import java.sql.Statement;

public class InsertTestData {
    public static void main(String[] argv) {
        updateTables();
    }

    public static void updateTables() {
        try {
            JDBC.connect();
            Statement stmt = JDBC.connection.createStatement();
            updateStudentTable(stmt);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        JDBC.close();
    }

    private static void updateStudentTable(Statement stmt) {
        String names[] = {"Ilay", "Sergio", "Dima", "Ksu", "Lera"};
        int ages[] = {19, 20, 21, 22};

        String updateTab = "ALTER TABLE student AUTO_INCREMENT = 1";
        try {
            stmt.executeUpdate(updateTab);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        for (int i = 1; i <= names.length; i++) {
            updateTab = "INSERT INTO student (name, age)" + "VALUES ('" + names[i - 1] + "', '" + ages[i - 1] + "');";
            try {
                stmt.executeUpdate(updateTab);
            } catch (SQLException e) {
                System.out.println("Execute Update Failed!");
                e.printStackTrace();
                return;
            }
        }
    }
}