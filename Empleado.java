/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author Karina
 */
public class Empleado extends Persona {
    private Nomina nomina;
    private String puestoTrabajo;

    /**
     * Constructor vacio
     */
    public Empleado() {    
    }
    
    /**
     * Constructor con todos los parametros
     * @param nomina Lista de las nominas
     * @param puestoTrabajo Puesto de trabajo
     * @param nombre Nombre del trabajador
     * @param apellidos Apellidos del trabajador
     * @param nif NIF del trabajador
     * @param telefono Telefono del trabajador
     * @param domicilio Domicilio del trabajador
     */
    public Empleado(Nomina nomina, String puestoTrabajo, String nombre, String apellidos, String nif, Integer telefono, String domicilio) {
        super(nombre, apellidos, nif, telefono, domicilio);
        this.nomina = nomina;
        this.puestoTrabajo = puestoTrabajo;
    }

    // GETTERS Y SETTERS
    public Nomina getListaNominas() {
        return nomina;
    }

    public void setListaNominas(Nomina nomina) {
        this.nomina = nomina;
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public String toString(){
        return super.toString() + " puesto de trabajo: " + puestoTrabajo; //Sin el conjunto de nominas, eso vendra con la base de datos y serà una simple llamada
    }

    // TODO: Crear una funcion para crear una nueva nomina directamente (pide los datos al usuario y las pasa a la nomina, controlar errores!)
    // TODO: Constructor copia
    
}
