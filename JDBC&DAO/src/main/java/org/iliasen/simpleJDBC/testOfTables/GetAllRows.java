package org.iliasen.simpleJDBC.testOfTables;

import org.iliasen.simpleJDBC.connection.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetAllRows {
    Statement stmt = null;

    public static void main(String[] args) {
        Statement stmt = null;
        try{
            JDBC.connect();
            stmt = JDBC.connection.createStatement();

            String sql = "SELECT * FROM student";

                ResultSet resultSet = stmt.executeQuery(sql);

                while (resultSet.next()) {
                    // Получаем значения столбцов по их индексу или имени
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    System.out.println("ID: " + id + ", Name: " + name + ", age: " + age);
                }

                // Закрываем ресурсы
                resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            JDBC.close();
        }
    }
}
