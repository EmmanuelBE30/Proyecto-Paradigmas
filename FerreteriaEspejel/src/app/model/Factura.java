/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Vanesa
 */
public class Factura {
    private String sucursal;
    private String fechaEmisionFactura;
    private String producto;
    private int totalFactura;
    private String estadoFactura;
    private String formaPago;
    private String descripcionFactura;
    private String categoria;
    private String clienteFactura;

    public Factura(String sucursal, String fechaEmisionFactura, String producto, int totalFactura, String estadoFactura, String formaPago, String descripcionFactura, String categoria, String clienteFactura) {
        this.sucursal = sucursal;
        this.fechaEmisionFactura = fechaEmisionFactura;
        this.producto = producto;
        this.totalFactura = totalFactura;
        this.estadoFactura = estadoFactura;
        this.formaPago = formaPago;
        this.descripcionFactura = descripcionFactura;
        this.categoria = categoria;
        this.clienteFactura = clienteFactura;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getFechaEmisionFactura() {
        return fechaEmisionFactura;
    }

    public void setFechaEmisionFactura(String fechaEmisionFactura) {
        this.fechaEmisionFactura = fechaEmisionFactura;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(int totalFactura) {
        this.totalFactura = totalFactura;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getDescripcionFactura() {
        return descripcionFactura;
    }

    public void setDescripcionFactura(String descripcionFactura) {
        this.descripcionFactura = descripcionFactura;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getClienteFactura() {
        return clienteFactura;
    }

    public void setClienteFactura(String clienteFactura) {
        this.clienteFactura = clienteFactura;
    }
    
    
    
    
}
