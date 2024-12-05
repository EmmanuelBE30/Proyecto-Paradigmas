/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.Factura;
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
public class FacturaDaoImp implements FacturaDao {

    @Override
    public void guardarFactura(Factura factura) {
       try {
            Connection con = Conexion.getConexion();
            String query = "insert into factura(sucursal,fecha_emision,producto,total,estado,forma_pago,descripcion,categoria,id_cliente) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            // Preparar la consulta para insertar en la tabla 'productoes'
            ps.setString(1, factura.getSucursal());
            ps.setString(2, factura.getFechaEmisionFactura());
            ps.setString(3, factura.getProductoFac());
            ps.setInt(4, factura.getTotalFactura());
            ps.setString(5, factura.getEstadoFactura());
            ps.setString(6, factura.getFormaPago());
            ps.setString(7, factura.getDescripcionFactura());
            ps.setString(8, factura.getCategoria());
            ps.setString(9, factura.getClienteFactura());
          
            // Ejecutar la consulta
            ps.executeUpdate();

            // Obtener el ID generado para la tabla 'productoes'
            ResultSet generatedKeys = ps.getGeneratedKeys();
            int idFactura = 0;

            if (generatedKeys.next()) {
                idFactura = generatedKeys.getInt(1);
                JOptionPane.showMessageDialog(null, "El producto se guardó con éxito. ID: " + idFactura);
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
    public void actualizarFactura(Factura producto, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Factura consultarFactura(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarFactura(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void construirTabla(DefaultTableModel modeloTabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
