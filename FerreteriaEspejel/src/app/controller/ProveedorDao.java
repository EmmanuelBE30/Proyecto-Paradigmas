/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.controller;

import app.model.Proveedor;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vanesa
 */
public interface ProveedorDao {
    public void guardarProveedor(Proveedor provedeor);
    public void actualizarProveedor(Proveedor proveedor, int id);
    public Proveedor consultarProveedor(int id);
    public void eliminarProveedor(int id);
    public void construirTabla(DefaultTableModel modeloTabla);
    
}
