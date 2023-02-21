package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConection {
    private String url;
    private String username;
    private String pass;
    public static Connection getConnection(String url, String username, String pass)
    {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            String url = "jdbc:mysql://localhost:3306/productmanagement";
//            String username="root";
//            String password="";
            conn = DriverManager.getConnection(url, username, pass);
            System.out.println("Connect Successfully");

        } catch (SQLException e) {
            System.out.println("Connect Error");
            throw new RuntimeException(e);
        }
        return conn;
    }
    public static void closeConnection(Connection conn) {
        try {
            if(conn!=null) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
