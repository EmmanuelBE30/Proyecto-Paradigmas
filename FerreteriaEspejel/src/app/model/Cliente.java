/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Vanesa
 */
public class Cliente extends Persona {
    
    private int noCliente;
    private int id;
     
    

    public Cliente(int noCliente, String rfc, String curp, String nombre, int edad, char sexo, String correo) {
        super(rfc, curp, nombre, edad, sexo, correo);
        this.noCliente = noCliente;
    }
    
    public Cliente(){
        
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(int noCliente) {
        this.noCliente = noCliente;
    }
    
    
    
}
