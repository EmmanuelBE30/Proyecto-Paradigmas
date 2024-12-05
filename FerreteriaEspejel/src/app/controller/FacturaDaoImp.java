/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.Cliente;
import app.model.Factura;
import app.utils.Conexion;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vanesa
 */
public class FacturaDaoImp implements FacturaDao {

    


public void guardarFactura(Factura factura) {
    try {
        Connection con = Conexion.getConexion();
        String query = """
            INSERT INTO facturas 
            (sucursal, fecha_emision, producto, total, estado, froma_pago, descripcion, categoria, id_cliente) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        // Conversión de fecha
        Date fechaEmision;
        try {
            LocalDate localDate = LocalDate.parse(factura.getFechaEmisionFactura(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            fechaEmision = Date.valueOf(localDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato de fecha debe ser yyyy-MM-dd.", e);
        }

        // Setear valores
        ps.setString(1, factura.getSucursal());
        ps.setDate(2, (java.sql.Date) fechaEmision);
        ps.setString(3, factura.getProductoFac());
        ps.setBigDecimal(4, factura.getTotalFactura());
        ps.setString(5, factura.getEstadoFactura());
        ps.setString(6, factura.getFormaPago());
        ps.setString(7, factura.getDescripcionFactura());
        ps.setString(8, factura.getCategoria());

        // Recuperamos el id del cliente
        Cliente cliente = obtenerIdCliente(factura.getRfcCli());
        if (cliente == null || cliente.getId() == 0) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }
        ps.setInt(9, cliente.getId());

        // Ejecutar la consulta
        ps.executeUpdate();

        // Obtener la clave generada
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            int idFactura = generatedKeys.getInt(1);
            JOptionPane.showMessageDialog(null, "Factura guardada con éxito. ID: " + idFactura);
        } else {
            JOptionPane.showMessageDialog(null, "Error al generar ID de la factura.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al guardar la factura: " + e.getMessage());
    }
}


    

    @Override
    public void actualizarFactura(Factura factura) {
    try {
        // Obtener la conexión
        Connection con = Conexion.getConexion();
        
        // Consulta SQL para actualizar una factura por su ID
        String query = "UPDATE facturas SET sucursal = ?, fecha_emision = ?, producto = ?, total = ?, estado = ?, froma_pago = ?, descripcion = ?, categoria = ?, id_cliente = ? WHERE id_factura = ?";
        
        // Preparar la sentencia
        PreparedStatement ps = con.prepareStatement(query);
        
        // Asignar los valores a los parámetros
        ps.setString(1, factura.getSucursal());
        ps.setDate(2, Date.valueOf(LocalDate.parse(factura.getFechaEmisionFactura(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        ps.setString(3, factura.getProductoFac());
        ps.setBigDecimal(4, factura.getTotalFactura());
        ps.setString(5, factura.getEstadoFactura());
        ps.setString(6, factura.getFormaPago());
        ps.setString(7, factura.getDescripcionFactura());
        ps.setString(8, factura.getCategoria());
        ps.setInt(9, Integer.parseInt(factura.getClienteFactura())); // Asumiendo que clienteFactura guarda el ID del cliente como String.
        ps.setInt(10, factura.getId_factura()); // Suponiendo que la clase Factura tiene un campo idFactura.

        // Ejecutar la actualización
        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("La factura fue actualizada exitosamente.");
        } else {
            System.out.println("No se encontró ninguna factura con el ID proporcionado.");
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error al actualizar la factura: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error general: " + e.getMessage());
    }
}

    

    @Override
    public Factura consultarFactura(int id) {
    try {
        // Obtener la conexión a la base de datos
        Connection con = Conexion.getConexion();

        // Consulta SQL para buscar una factura por ID
        String query = "SELECT * FROM facturas WHERE id_factura = ?";
        
        // Preparar la consulta de selección
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id); // Pasar el ID de la factura a consultar

        // Ejecutar la consulta
        ResultSet rs = ps.executeQuery();

        // Verificar si hay resultados
        if (rs.next()) {
            // Obtener los datos de la factura desde el ResultSet
            String sucursal = rs.getString("sucursal");
            String fechaEmisionFactura = rs.getDate("fecha_emision").toString(); // Convertir la fecha a String
            String productoFac = rs.getString("producto");
            BigDecimal totalFactura = rs.getBigDecimal("total"); // BigDecimal para valores monetarios
            String estadoFactura = rs.getString("estado");
            String formaPago = rs.getString("forma_pago"); // Corrección del nombre
            String descripcionFactura = rs.getString("descripcion");
            String categoria = rs.getString("categoria");
            int idCliente = rs.getInt("id_cliente");

            // Puedes adaptar este campo según cómo lo manejes en tu clase Factura
            String clienteFactura = String.valueOf(idCliente);

            // Retornar un nuevo objeto Factura con los datos obtenidos
            return new Factura(sucursal, fechaEmisionFactura, productoFac, totalFactura, estadoFactura, formaPago, descripcionFactura, null, categoria, clienteFactura);
        } else {
            // Si no se encuentra la factura, mostrar un mensaje
            JOptionPane.showMessageDialog(null, "No se encontró la factura con el ID especificado.");
        }
    } catch (SQLException e) {
        // Manejo de excepciones SQL
        System.out.println("Error al consultar la factura: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Ocurrió un error al consultar la factura.");
    }
    return null;
}


    @Override
    public void eliminarFactura(int id) {
        try {
            Connection con = Conexion.getConexion();
            String query = "DELETE FROM facturas WHERE id_factura = ?";
            PreparedStatement ps = con.prepareStatement(query);

            // Establecer el parámetro de la consulta (ID del producto a eliminar)
            ps.setInt(1, id);

            // Ejecutar la consulta
           ps.executeUpdate();// Ejecutar la consulta
            
            JOptionPane.showMessageDialog(null, "La factura se elimino exitósamente");
            
        } catch (SQLException e) {
            // Manejo de excepciones SQL
            System.out.println("Error al eliminar la factura: " + e);
        }
      }

    @Override
    public void construirTabla(DefaultTableModel modeloTabla) {
          try{
            Connection conn = Conexion.getConexion();
            String query = "SELECT \n" +
                "    f.id_factura AS \"ID Factura\",\n" +
                "    f.sucursal AS \"Sucursal\",\n" +
                "    f.fecha_emision AS \"Fecha de Emisión\",\n" +
                "    f.producto AS \"Producto\",\n" +
                "    f.total AS \"Total\",\n" +
                "    f.estado AS \"Estado\",\n" +
                "    f.froma_pago AS \"Forma de Pago\",\n" +
                "    f.descripcion AS \"Descripcion\",\n" +
                "    f.categoria AS \"Categoría\",\n" +
                "    p.rfc AS \"RFC\" -- Obtener el RFC del cliente desde la tabla 'personas'\n" +
                "FROM \n" +
                "    facturas f\n" +
                "JOIN \n" +
                "    clientes c ON f.id_cliente = c.id_persona\n" +
                "JOIN \n" +
                "    personas p ON c.id_persona = p.id_persona;";
            PreparedStatement ps=conn.prepareStatement(query);
            ResultSet rs= ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            
            while(rs.next()){
                Object[] fila=new Object[columns];
                for(int indice=0; indice<columns; indice++){
                    fila[indice]=rs.getObject(indice + 1);
                }
                modeloTabla.addRow(fila);
            }
        }catch(SQLException e){
            System.out.println("Error al construir tabla");
        }
       }

    @Override
    public Cliente obtenerIdCliente(String rfcCliente) {
    try {
        Connection con = Conexion.getConexion();
        String query = """
            SELECT c.id_persona AS id_cliente 
            FROM clientes c 
            JOIN personas p ON c.id_persona = p.id_persona 
            WHERE p.rfc = ?
        """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, rfcCliente);
        ResultSet rs = ps.executeQuery();
        
        Cliente cliente = new Cliente();
        if (rs.next()) {
            cliente.setId(rs.getInt("id_cliente"));
        }
        return cliente;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}

   
