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

    @Override
    public String toString() {
        return "{ Nóminas almacenadas: " + nomina.size() + " | Puesto de trabajo: " + puestoTrabajo + " }" + super.toString(); //To change body of generated methods, choose Tools | Templates.
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
    
}
