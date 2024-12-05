/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.Producto;
import app.model.Proveedor;
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
public class ProductoDaoImp implements ProductoDao {

    @Override
    public void guardarProducto(Producto producto) {
        try {
            Connection con = Conexion.getConexion();
            String query = "insert into productos(nombre,descripcion,cantidad,costo_publico,costo_proveedor,garantia,categoria,id_proveedor) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            Proveedor id = new Proveedor();
            id = new ProveedorDaoImp().obtenerIdProveedor(producto.getProveedorProducto());
            // Preparar la consulta para insertar en la tabla 'productoes'
            ps.setString(1, producto.getNombreProducto());
            ps.setString(2, producto.getDescripcionProducto());
            ps.setInt(3, producto.getCantidadProducto());
            ps.setInt(4, producto.getCostoPubProducto());
            ps.setInt(5, producto.getCostoProvProducto());
            ps.setString(6, producto.getGarantiaProducto());
            ps.setString(7, producto.getCategoriaProducto());
            ps.setInt(8, id.getId_proveedor());
          
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
            String query = "UPDATE productos\n"
                    + "   SET nombre = ?, \n"
                    + "    descripcion = ?, \n"
                    + "    cantidad = ?, \n"
                    + "    costo_publico = ?, \n"
                    + "    costo_proveedor = ?, \n"
                    + "    garantia = ?, \n"
                    + "    categoria = ?, \n"
                    + "    id_proveedor = (SELECT id_proveedor FROM proveedores WHERE nombre = ?)\n"
                    + "WHERE id_producto = ?;";

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
             ps.executeUpdate();
           JOptionPane.showMessageDialog(null, "El producto se actualizo exitósamente ");
           } catch (SQLException e) {
            // Manejo de excepciones SQL
            System.out.println("Error al actualizar producto: " + e);
        }
      }

        

    @Override
    public Producto consultarProducto(int id) {
         try {
    // Obtener la conexión a la base de datos
    Connection con = Conexion.getConexion();

    // Consulta SQL para buscar un proveedor por ID
    String query = "SELECT id_producto, nombre, descripcion, cantidad, costo_publico, costo_proveedor, garantia, categoria, id_proveedor, "
             + "(SELECT nombre FROM proveedores WHERE id_proveedor = productos.id_proveedor) AS nombre_proveedor "
             + "FROM productos "
             + "WHERE id_producto = ?";
    
    // Preparar la consulta de selección
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id); // Pasar el ID del proveedor a consultar

    // Ejecutar la consulta
    ResultSet rs = ps.executeQuery();

    // Verificar si hay resultados
    if (rs.next()) {
        // Obtener los datos del proveedor desde el ResultSet
        String nombre = rs.getString("nombre");
        String descripcion = rs.getString("descripcion");
        int cantidad = rs.getInt("cantidad");
        int costoPublico = rs.getInt("costo_publico");
        int costoProveedor = rs.getInt("costo_proveedor");
        String garantia = rs.getString("garantia");
        String categoria = rs.getString("categoria");
        String ProvedorProd  = rs.getString("id_proveedor");

        // Mostrar los datos en un mensaje o asignarlos a los componentes gráficos
        return new Producto(nombre, descripcion, cantidad, costoPublico, costoProveedor, garantia,categoria,ProvedorProd);
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

    @Override
    public void eliminarProducto(int id) {
        try {
            Connection con = Conexion.getConexion();
            String query = "DELETE FROM productos WHERE id_producto = ?";
            PreparedStatement ps = con.prepareStatement(query);

            // Establecer el parámetro de la consulta (ID del producto a eliminar)
            ps.setInt(1, id);

            // Ejecutar la consulta
           ps.executeUpdate();// Ejecutar la consulta
            
            JOptionPane.showMessageDialog(null, "El producto se elimino exitósamente");
            
        } catch (SQLException e) {
            // Manejo de excepciones SQL
            System.out.println("Error al eliminar producto: " + e);
        }
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
    
}
