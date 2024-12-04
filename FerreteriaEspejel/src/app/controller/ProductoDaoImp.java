/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.Producto;
import app.utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vanesa
 */
public class ProductoDaoImp implements ProductoDao {

    @Override
    public void guardarProducto(Producto producto) {
        try {
            Connection con = Conexion.getConexion();
            String query = "insert into productos(nombre,descripcion,cantidad,costo_publico,costo_producto,garantia,categoria,id_producto) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            // Preparar la consulta para insertar en la tabla 'productoes'
            ps.setString(1, producto.getNombreProducto());
            ps.setString(2, producto.getDescripcionProducto());
            ps.setInt(3, producto.getCantidadProducto());
            ps.setInt(4, producto.getCostoPubProducto());
            ps.setInt(5, producto.getCostoProvProducto());
            ps.setString(6, producto.getGarantiaProducto());
            ps.setString(7, producto.getCategoriaProducto());
            ps.setString(8, producto.getProveedorProducto());
          
            // Ejecutar la consulta
            ps.executeUpdate();

            // Obtener el ID generado para la tabla 'productoes'
            ResultSet generatedKeys = ps.getGeneratedKeys();
            int idProducto = 0;

            if (generatedKeys.next()) {
                idProducto = generatedKeys.getInt(1);
                JOptionPane.showMessageDialog(null, "El producto se guardó con éxito. ID: " + idProducto);
            } else {
                // Si no se genera un ID, mostrar un mensaje de error
                JOptionPane.showMessageDialog(null, "Error al insertar producto");
            }

        } catch (SQLException e) {
            // Manejo de excepciones SQL
            System.out.println("Error al insertar producto: " + e);
        }
    }

    @Override
    public void actualizarProducto(Producto producto, int id) {
        try {
            // Obtener la conexión a la base de datos
            Connection con = Conexion.getConexion();

            // Consulta SQL para actualizar los datos del producto
            String query = "UPDATE productos SET "
                         + "nombre = ?, "
                         + "descripcion = ?, "
                         + "cantidad = ?, "
                         + "costo_publico = ?, "
                         + "costo_proveedor = ?, "
                         + "garantia = ?, "
                         + "categoria = ?, "
                         + "id_proveedor = ?, "
                         + "WHERE id_producto = ?";

            // Preparar la consulta de actualización
            PreparedStatement ps = con.prepareStatement(query);
             ps.setString(1, producto.getNombreProducto());
            ps.setString(2, producto.getDescripcionProducto());
            ps.setInt(3, producto.getCantidadProducto());
            ps.setInt(4, producto.getCostoPubProducto());
            ps.setInt(5, producto.getCostoProvProducto());
            ps.setString(6, producto.getGarantiaProducto());
            ps.setString(7, producto.getCategoriaProducto());
            ps.setString(8, producto.getProveedorProducto());
            ps.setInt(9, id); // Aquí pasamos el ID del producto a actualizar

            // Ejecutar la consulta
            ResultSet generatedKeys = ps.getGeneratedKeys();
            int idProducto = 0;

            if (generatedKeys.next()) {
                idProducto = generatedKeys.getInt(1);
                JOptionPane.showMessageDialog(null, "El producto se actualizo con éxito. ID: " + idProducto);
            } else {
                // Si no se genera un ID, mostrar un mensaje de error
                JOptionPane.showMessageDialog(null, "Error al actualizar producto");
            }

        } catch (SQLException e) {
            // Manejo de excepciones SQL
            System.out.println("Error al actualizar producto: " + e);
        }
      }

    @Override
    public Producto consultarProducto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarProducto(int id) {
        try {
            Connection con = Conexion.getConexion();
            String query = "DELETE FROM productos WHERE id_producto = ?";
            PreparedStatement ps = con.prepareStatement(query);

            // Establecer el parámetro de la consulta (ID del producto a eliminar)
            ps.setInt(1, id);

            // Ejecutar la consulta
            ResultSet generatedKeys = ps.getGeneratedKeys();
            int idProducto = 0;

            if (generatedKeys.next()) {
                idProducto = generatedKeys.getInt(1);
                JOptionPane.showMessageDialog(null, "El producto se elimino con éxito. ID: " + idProducto);
            } else {
                // Si no se genera un ID, mostrar un mensaje de error
                JOptionPane.showMessageDialog(null, "Error al eliminar producto");
            }

        } catch (SQLException e) {
            // Manejo de excepciones SQL
            System.out.println("Error al eliminar producto: " + e);
        }
    }

    @Override
    public void construirTabla(DefaultTableModel modeloTabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
