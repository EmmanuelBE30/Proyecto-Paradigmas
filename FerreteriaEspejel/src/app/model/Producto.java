/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author emmanuel
 */
public class Producto {
    
    private String nombreProducto;
    private String descripcionProducto;
    private int cantidadProducto;
    private int costoPubProducto;
    private int costoProvProducto;
    private String garantiaProducto;
    private String categoriaProducto;
    private String ProveedorProducto;

    public Producto(String nombreProducto, String descripcionProducto, int cantidadProducto, int costoPubProducto, int costoProvProducto, String garantiaProducto, String categoriaProducto, String ProveedorProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadProducto = cantidadProducto;
        this.costoPubProducto = costoPubProducto;
        this.costoProvProducto = costoProvProducto;
        this.garantiaProducto = garantiaProducto;
        this.categoriaProducto = categoriaProducto;
        this.ProveedorProducto = ProveedorProducto;
    }
    

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public int getCostoPubProducto() {
        return costoPubProducto;
    }

    public void setCostoPubProducto(int costoPubProducto) {
        this.costoPubProducto = costoPubProducto;
    }

    public int getCostoProvProducto() {
        return costoProvProducto;
    }

    public void setCostoProvProducto(int costoProvProducto) {
        this.costoProvProducto = costoProvProducto;
    }

    public String getGarantiaProducto() {
        return garantiaProducto;
    }

    public void setGarantiaProducto(String garantiaProducto) {
        this.garantiaProducto = garantiaProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public String getProveedorProducto() {
        return ProveedorProducto;
    }

    public void setProveedorProducto(String ProveedorProducto) {
        this.ProveedorProducto = ProveedorProducto;
    }

    
    
}
