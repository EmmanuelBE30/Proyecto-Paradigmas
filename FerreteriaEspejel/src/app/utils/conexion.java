/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.utils;

/**
 *
 * @author Emmanuel
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class conexion {
    public static void main (String[] args){
        } 
    public static Connection getConexion(){
        
      
        
        String url = "jdbc:mysql://localhost:3306/ferreteria_espejel";
        try{
        Connection conn = DriverManager.getConnection(url,"root","12345678");
        return conn;
        }catch(SQLException e){
                System.out.println("Error de conexion "+e.toString());
                return null;
                }
    
    
}
    
}
