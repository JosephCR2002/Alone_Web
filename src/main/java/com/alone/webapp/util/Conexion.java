package com.alone.webapp.util;

import java.sql.*;

public class Conexion {
    
    private static String url = "jdbc:mysql://sql10.freesqldatabase.com/sql10632273";
    private static String username = "sql10632273";
    private static String password = "kYlN743i9z";
        
    public static Connection getConexion() throws SQLException, ClassNotFoundException{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url,username,password);
    }
}
