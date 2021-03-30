/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fp.concesionario;

import java.util.ArrayList;

/**
 *
 * @author Karina
 */
public class Empleado extends Persona {
    private ArrayList<Nomina> listaNominas;
    private String puestoTrabajo;

    /**
     * Constructor vacio
     */
    public Empleado() {    
    }
    
    /**
     * Constructor con todos los parametros
     * @param listaNominas Lista de las nominas
     * @param puestoTrabajo Puesto de trabajo
     * @param nombre Nombre del trabajador
     * @param apellidos Apellidos del trabajador
     * @param nif NIF del trabajador
     * @param telefono Telefono del trabajador
     * @param domicilio Domicilio del trabajador
     */
    public Empleado(ArrayList<Nomina> listaNominas, String puestoTrabajo, String nombre, String apellidos, String nif, Integer telefono, String domicilio) {
        super(nombre, apellidos, nif, telefono, domicilio);
        this.listaNominas = listaNominas;
        this.puestoTrabajo = puestoTrabajo;
    }

    @Override
    public String toString() {
        return "{ Nóminas almacenadas: " + listaNominas.size() + " | Puesto de trabajo: " + puestoTrabajo + " }" + super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    // GETTERS Y SETTERS
    public ArrayList<Nomina> getListaNominas() {
        return listaNominas;
    }

    public void setListaNominas(ArrayList<Nomina> listaNominas) {
        this.listaNominas = listaNominas;
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }
    
}
