
package app.controller;

import java.util.ArrayList;
import app.model.Proveedor;
/**
 *
 * @author emmanuel
 */
public class ListaProveedor {
    ArrayList<Proveedor> lista;
    
    //constrcutor

    public ListaProveedor() {
        lista = new ArrayList();
    }
    //métodos de agregar proveedores
    public void AgregarProveedor(Proveedor proveedores){
        lista.add(proveedores);
    }
    
}
