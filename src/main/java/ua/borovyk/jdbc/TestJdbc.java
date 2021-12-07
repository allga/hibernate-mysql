package ua.borovyk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String passw = "HBstudent7&";
        try {
            System.out.println("Connecting to Database: " + jdbcUrl);
            Connection myconn =
                    DriverManager.getConnection(jdbcUrl, user, passw);
            System.out.println("Connection successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
