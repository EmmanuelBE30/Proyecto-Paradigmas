/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.controller;

import app.model.Producto;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vanesa
 */
public interface ProductoDao {
     public void guardarProducto(Producto producto);
    public void actualizarProducto(Producto producto, int id);
    public Producto consultarProducto(int id);
    public void eliminarProducto(int id);
    public void construirTabla(DefaultTableModel modeloTabla);
    
}
