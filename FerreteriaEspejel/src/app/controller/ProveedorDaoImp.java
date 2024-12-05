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
            ResultSet generatedKeys = ps.getGeneratedKeys();

            // Verificar si se actualizó algún registro
           int idProveedor = 0;

            if (generatedKeys.next()) {
                idProveedor = generatedKeys.getInt(1);
                JOptionPane.showMessageDialog(null, "El proveedor se actualizo con éxito. ID: " + idProveedor);
            } else {
                // Si no se genera un ID, mostrar un mensaje de error
                JOptionPane.showMessageDialog(null, "Error al actualizar proveedor");
            }
        } catch (SQLException e) {
            // Manejo de excepciones SQL
            System.out.println("Error al actualizar proveedor: " + e);
        }
    }

    

    @Override
    public void eliminarProveedor(int id) {
         try {
            Connection con = Conexion.getConexion();
            String query = "DELETE FROM proveedores WHERE id_proveedor = ?";
            PreparedStatement ps = con.prepareStatement(query);

            // Establecer el parámetro de la consulta (ID del producto a eliminar)
            ps.setInt(1, id);

            // Ejecutar la consulta
            ResultSet generatedKeys = ps.getGeneratedKeys();
            int idProveedor = 0;

            if (generatedKeys.next()) {
                idProveedor = generatedKeys.getInt(1);
                JOptionPane.showMessageDialog(null, "El proveedor se elimino con éxito. ID: " + idProveedor);
            } else {
                // Si no se genera un ID, mostrar un mensaje de error
                JOptionPane.showMessageDialog(null, "Error al eliminar proveedor");
            }

        } catch (SQLException e) {
            // Manejo de excepciones SQL
            System.out.println("Error al eliminar proveedor: " + e);
        }
    }

    @Override
    public void construirTabla(DefaultTableModel modeloTabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Proveedor consultarProveedor(int id) {
        try {
    // Obtener la conexión a la base de datos
    Connection con = Conexion.getConexion();

    // Consulta SQL para buscar un proveedor por ID
    String query = "SELECT * FROM proveedores WHERE id_proveedor = ?";
    
    // Preparar la consulta de selección
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id); // Pasar el ID del proveedor a consultar

    // Ejecutar la consulta
    ResultSet rs = ps.executeQuery();

    // Verificar si hay resultados
    if (rs.next()) {
        // Obtener los datos del proveedor desde el ResultSet
        String nombre = rs.getString("nombre");
        String calle = rs.getString("calle");
        String colonia = rs.getString("colonia");
        int cp = rs.getInt("CP");
        String ciudad = rs.getString("ciudad");
        String pais = rs.getString("pais");
        String telefono = rs.getString("telefono");
        String email = rs.getString("email");
        String categoria = rs.getString("categoria");

        // Mostrar los datos en un mensaje o asignarlos a los componentes gráficos
        JOptionPane.showMessageDialog(null, 
            "Datos del Proveedor:\n" +
            "Nombre: " + nombre + "\n" +
            "Calle: " + calle + "\n" +
            "Colonia: " + colonia + "\n" +
            "CP: " + cp + "\n" +
            "Ciudad: " + ciudad + "\n" +
            "País: " + pais + "\n" +
            "Teléfono: " + telefono + "\n" +
            "Email: " + email + "\n" +
            "Categoría: " + categoria
        );
    } else {
        // Si no se encuentra el proveedor, mostrar un mensaje
        JOptionPane.showMessageDialog(null, "No se encontró el proveedor con el ID especificado.");
    }
} catch (SQLException e) {
    // Manejo de excepciones SQL
    System.out.println("Error al consultar proveedor: " + e);
    JOptionPane.showMessageDialog(null, "Ocurrió un error al consultar el proveedor.");
}
        return null;
        
    } 
    
}
