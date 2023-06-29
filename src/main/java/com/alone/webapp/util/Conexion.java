package com.alone.webapp.util;

import java.sql.*;

public class Conexion {
    
    private static String url = "jdbc:mysql://sql10.freesqldatabase.com/sql10629177";
    private static String username = "sql10629177";
    private static String password = "yjulwiAY1j";
        
    public static Connection getConexion() throws SQLException, ClassNotFoundException{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url,username,password);
    }
}
