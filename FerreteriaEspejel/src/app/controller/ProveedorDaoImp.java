/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.utils.Conexion;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import app.model.Proveedor;

/**
 *
 * @author Vanesa
 */
public class ProveedorDaoImp implements ProveedorDao{

    @Override
    public void guardarProveedor(Proveedor proveedor) {
        try {
            Connection con = Conexion.getConexion();
            String query = "insert into proveedores(nombre,calle,colonia,CP,ciudad,pais,telefono,email,categoria) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            // Preparar la consulta para insertar en la tabla 'proveedores'
            ps.setString(1, proveedor.getNombreProveedor());
            ps.setString(2, proveedor.getCalleProveedor());
            ps.setString(3, proveedor.getColoniaProveedor());
            ps.setInt(4, proveedor.getCpProveedor());
            ps.setString(5, proveedor.getCiudadProveedor());
            ps.setString(6, proveedor.getPaisProveedor());
            ps.setString(7, proveedor.getTelefonoProveedor());
            ps.setString(8, proveedor.getEmailProveedor());
            ps.setString(9, proveedor.getCategoriaProveedor());

            // Ejecutar la consulta
            ps.executeUpdate();

            // Obtener el ID generado para la tabla 'proveedores'
            ResultSet generatedKeys = ps.getGeneratedKeys();
            int idProveedor = 0;

            if (generatedKeys.next()) {
                idProveedor = generatedKeys.getInt(1);
                JOptionPane.showMessageDialog(null, "El proveedor se guardó con éxito. ID: " + idProveedor);
            } else {
                // Si no se genera un ID, mostrar un mensaje de error
                JOptionPane.showMessageDialog(null, "Error al insertar proveedor");
            }

        } catch (SQLException e) {
            // Manejo de excepciones SQL
            System.out.println("Error al insertar proveedor: " + e);
        }
    }

    @Override
    public void actualizarProveedor(Proveedor proveedor, int id) {
        try {
            // Obtener la conexión a la base de datos
            Connection con = Conexion.getConexion();

            // Consulta SQL para actualizar los datos del proveedor
            String query = "UPDATE proveedores SET "
                         + "nombre = ?, "
                         + "calle = ?, "
                         + "colonia = ?, "
                         + "CP = ?, "
                         + "ciudad = ?, "
                         + "pais = ?, "
                         + "telefono = ?, "
                         + "email = ?, "
                         + "categoria = ? "
                         + "WHERE id_proveedor = ?";

            // Preparar la consulta de actualización
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, proveedor.getNombreProveedor());
            ps.setString(2, proveedor.getCalleProveedor());
            ps.setString(3, proveedor.getColoniaProveedor());
            ps.setInt(4, proveedor.getCpProveedor());
            ps.setString(5, proveedor.getCiudadProveedor());
            ps.setString(6, proveedor.getPaisProveedor());
            ps.setString(7, proveedor.getTelefonoProveedor());
            ps.setString(8, proveedor.getEmailProveedor());
            ps.setString(9, proveedor.getCategoriaProveedor());
            ps.setInt(10, id); // Aquí pasamos el ID del proveedor a actualizar

            // Ejecutar la consulta
            int rowsAffected = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Proveedor actualizado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un proveedor con ese ID.");
            }

        } catch (SQLException e) {
            // Manejo de excepciones SQL
            System.out.println("Error al actualizar proveedor: " + e);
        }
    }

    

    @Override
    public void eliminarProveedor(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void construirTabla(DefaultTableModel modeloTabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Proveedor consultarProveedor(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }   
}
