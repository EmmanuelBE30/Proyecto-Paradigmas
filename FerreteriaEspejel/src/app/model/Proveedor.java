/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Vanesa
 */
public class Proveedor {
    
    private String nombreProveedor;
    private String calleProveedor;
    private String coloniaProveedor;
    private int cpProveedor;
    private String ciudadProveedor;
    private String paisProveedor;
    private String telefonoProveedor;
    private String emailProveedor;
    private String categoriaProveedor;

    public Proveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Proveedor() {
    }
         

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getCalleProveedor() {
        return calleProveedor;
    }

    public void setCalleProveedor(String calleProveedor) {
        this.calleProveedor = calleProveedor;
    }

    public String getColoniaProveedor() {
        return coloniaProveedor;
    }

    public void setColoniaProveedor(String coloniaProveedor) {
        this.coloniaProveedor = coloniaProveedor;
    }

    public int getCpProveedor() {
        return cpProveedor;
    }

    public void setCpProveedor(String CpProveedor) {
        this.cpProveedor = cpProveedor;
    }

    public String getCiudadProveedor() {
        return ciudadProveedor;
    }

    public void setCiudadProveedor(String ciudadProveedor) {
        this.ciudadProveedor = ciudadProveedor;
    }

    public String getPaisProveedor() {
        return paisProveedor;
    }

    public void setPaisProveedor(String paisProveedor) {
        this.paisProveedor = paisProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    public String getCategoriaProveedor() {
        return categoriaProveedor;
    }

    public void setCategoriaProveedor(String categoriaProveedor) {
        this.categoriaProveedor = categoriaProveedor;
    }
    
    
}
