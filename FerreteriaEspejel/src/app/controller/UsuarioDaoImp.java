/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.Usuario;
import app.utils.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Emmanuel
 */
public class UsuarioDaoImp implements UsuarioDao {

    @Override
    public  Usuario validarUsuario(String login, String password) {
       Usuario usuario = null;
       try{
                     Connection conn = conexion.getConexion();                                  
            //Recuperar los datosde mis tablas
             String query= "select * from usuarios u, personas p where p.id_persona = u.id_persona and usuario =? and contraseña =?";
             PreparedStatement ps = conn.prepareStatement(query);
             ps.setString(1, login);
             ps.setString(2, password);
             
             ResultSet rs = ps.executeQuery();
             
             while(rs.next()){
                 usuario = new Usuario(rs.getString("rfc"),rs.getString("curp"),
                                      rs.getString("nombre"),rs.getInt("edad"),
                                      rs.getString("sexo").charAt(0),rs.getString("correo"),
                                      rs.getString("usuario"), rs.getString("contraseña"));
                                            
             }
           
       }catch(SQLException e){
           System.out.println("Error al validar Usuario");
       }
       return usuario;
    }
    
}
