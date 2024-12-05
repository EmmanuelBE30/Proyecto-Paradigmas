/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.Cliente;
import app.model.Factura;
import app.utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
            String query = "insert into facturas(sucursal,fecha_emision,producto,total,estado,forma_pago,descripcion,categoria,id_cliente) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            Cliente id = new Cliente();
            id = new ProveedorDaoImp().obtenerIdProveedor(cliente.);
            // Preparar la consulta para insertar en la tabla 'productoes'
            ps.setString(1, factura.getSucursal());
            ps.setString(2, factura.getFechaEmisionFactura());
            ps.setString(3, factura.getProductoFac());
            ps.setInt(4, factura.getTotalFactura());
            ps.setString(5, factura.getEstadoFactura());
            ps.setString(6, factura.getFormaPago());
            ps.setString(7, factura.getDescripcionFactura());
            ps.setString(8, factura.getRfcCli());
            ps.setString(9, factura.getCategoria());
            ps.setString(10, factura.getClienteFactura());
            ps.setInt(11, id.getId_cliente());
            
          
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
    public void actualizarFactura(Factura factura, int id) {
        try {
            // Obtener la conexión a la base de datos
            Connection con = Conexion.getConexion();

            // Consulta SQL para actualizar los datos del proveedor
            String query = "UPDATE facturas\n"
                    + "   SET sucursal = ?, \n"
                    + "    fecha_emision = ?, \n"
                    + "    cantidad = ?, \n"
                    + "    costo_publico = ?, \n"
                    + "    costo_proveedor = ?, \n"
                    + "    garantia = ?, \n"
                    + "    categoria = ?, \n"
                    + "    id_proveedor = (SELECT id_proveedor FROM proveedores WHERE nombre = ?)\n"
                    + "WHERE id_producto = ?;";

            // Preparar la consulta de actualización
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, factura.getNombreProveedor());
            ps.setString(2, factura.getCalleProveedor());
            ps.setString(3, factura.getColoniaProveedor());
            ps.setInt(4, factura.getCpProveedor());
            ps.setString(5, factura.getCiudadProveedor());
            ps.setString(6, factura.getPaisProveedor());
            ps.setString(7, factura.getTelefonoProveedor());
            ps.setString(8, factura.getEmailProveedor());
            ps.setString(9, factura.getCategoriaProveedor());
            ps.setInt(10, id); // Aquí pasamos el ID del proveedor a actualizar

            // Ejecutar la consulta
           ps.executeUpdate();
           JOptionPane.showMessageDialog(null, "El proveedor se actualizo exitósamente ");
    }catch(SQLException e){
            System.out.println("Error al actualizar proveedor");
    }
        }
    

    @Override
    public Factura consultarFactura(int id) {
            try {
    // Obtener la conexión a la base de datos
    Connection con = Conexion.getConexion();

    // Consulta SQL para buscar un proveedor por ID
    String query = "SELECT * FROM facturas WHERE id_factura = ?";
    
    // Preparar la consulta de selección
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id); // Pasar el ID del proveedor a consultar

    // Ejecutar la consulta
    ResultSet rs = ps.executeQuery();

    // Verificar si hay resultados
    if (rs.next()) {
        // Obtener los datos del proveedor desde el ResultSet
        String sucursal = rs.getString("sucursal");
        String fechaEmisionFactura = rs.getString("fechaEmisionFactura");
        String productoFac = rs.getString("productoFac");
        int totalFactura = rs.getInt("totalFactura");
        String estadoFactura = rs.getString("estadoFactura");
        String formaPago = rs.getString("formaPago");
        String descripcionFactura = rs.getString("descripcionFactura");
        String rfcCli = rs.getString("rfcCli");
        String categoria = rs.getString("categoria");
        String clienteFactura = rs.getString("clienteFactura");

        // Mostrar los datos en un mensaje o asignarlos a los componentes gráficos
        return new Factura( sucursal,  fechaEmisionFactura,  productoFac,  totalFactura,  estadoFactura,  formaPago,  descripcionFactura,  rfcCli,  categoria,  clienteFactura);
    } else {
        // Si no se encuentra el proveedor, mostrar un mensaje
        JOptionPane.showMessageDialog(null, "No se encontró la factura con el ID especificado.");
    }
} catch (SQLException e) {
    // Manejo de excepciones SQL
    System.out.println("Error al consultar la factura: " + e);
    JOptionPane.showMessageDialog(null, "Ocurrió un error al consultar la factura.");
}
        return null;
        }

    @Override
    public void eliminarFactura(int id) {
      }

    @Override
    public void construirTabla(DefaultTableModel modeloTabla) {
          try{
            Connection conn = Conexion.getConexion();
            String query = "SELECT p.id_producto,p.nombre AS producto_nombre, p.descripcion, p.cantidad, p.costo_publico, p.costo_proveedor, p.garantia, p.categoria, pr.nombre AS proveedor_nombre\n"
                    + "FROM productos p\n"
                    + "JOIN proveedores pr \n"
                    + "ON p.id_proveedor = pr.id_proveedor";
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
        try{
            Connection con = Conexion.getConexion();
            String query = "select id_cliente from clientes where nombre = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, rfcCliente);
            ResultSet rs = ps.executeQuery();
            Cliente id = new Cliente();
            if(rs.next()){
                id.setId(rs.getInt("id_cliente"));
              }
            return id;
              }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

   
