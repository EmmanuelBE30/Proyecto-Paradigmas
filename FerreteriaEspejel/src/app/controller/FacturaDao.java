/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app.controller;

import app.model.Factura;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vanesa
 */
public interface FacturaDao {
    public void guardarFactura(Factura factura);
    public void actualizarFactura(Factura factura, int id);
    public Factura consultarFactura(int id);
    public void eliminarFactura(int id);
    public void construirTabla(DefaultTableModel modeloTabla);
    
}
