/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fp.concesionario;

/**
 *
 * @author Karina
 */
public abstract class Persona {
    private String nombre;
    private String apellidos;
    private String nif;
    private Integer telefono;
    private String domicilio;

    /**
     * Constructor vacio
     */
    public Persona() {
    }

    /**
     * Constructor con todos los parámetros
     * @param nombre Nombre de la persona
     * @param apellidos Apellidos de la persona
     * @param nif NIF de la persona
     * @param telefono Telefono de la persona
     * @param domicilio Domicilio de la persona
     */
    public Persona(String nombre, String apellidos, String nif, Integer telefono, String domicilio) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.telefono = telefono;
        this.domicilio = domicilio;
    }

    /**
     * toString de los atributos de la clase 
     * @return String con los atributos
     */
    @Override
    public String toString() {
        return "{ Nombre: " + nombre + " | Apellidos: " + apellidos + " | NIF: " + nif + " | Telefono: " + telefono + " | Domicilio: " + domicilio + "}";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    
    
}
