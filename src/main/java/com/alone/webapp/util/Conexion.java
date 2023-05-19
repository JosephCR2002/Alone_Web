package com.alone.webapp.util;

import java.sql.*;

public class Conexion {
    
    private static String url = "jdbc:mysql://localhost:3306/alone_db?serverTimezone=America/Lima";
    private static String username = "root";
    private static String password = "";
        
    public static Connection getConexion() throws SQLException, ClassNotFoundException{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url,username,password);
    }
}
