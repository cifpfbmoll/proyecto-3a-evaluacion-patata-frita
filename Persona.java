package eu.fp.concesionario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Karina
 */
public abstract class Persona {

    private String nombre;
    private String apellidos;
    private String password;
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
     *
     * @param nombre Nombre de la persona
     * @param apellidos Apellidos de la persona
     * @param nif NIF de la persona
     * @param telefono Telefono de la persona
     * @param domicilio Domicilio de la persona
     */
    public Persona(String nombre, String apellidos, String nif, Integer telefono, String domicilio, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.setNif(nif);
        this.telefono = telefono;
        this.domicilio = domicilio;
    }

    /**
     * Constructor copia de la clase persona
     *
     * @param copia Persona a copiar
     */
    public Persona(Persona copia) {
        this.setNombre(copia.getNombre());
        this.setApellidos(copia.getApellidos());
        this.setNif(copia.getNif());
        this.setPassword(copia.getPassword());
        this.setTelefono(copia.getTelefono());
        this.setDomicilio(copia.getDomicilio());
    }

    /**
     * toString de los atributos de la clase
     *
     * @return String con los atributos
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Apellidos: " + apellidos + " | NIF: " + nif + " | Telefono: " + telefono + " | Domicilio: " + domicilio;
    }

    // GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
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

    public void setNif(String nif) throws IllegalArgumentException {
        if (nif.length() == 9) {
            try {
                Integer.parseInt(nif.substring(0, 7));
                nif.substring(8).toString();
                this.nif = nif;
            } catch (Exception ex) {
                throw new IllegalArgumentException("Esto no es un NIF válido. Ha de contener 8 numeros y una letra al final.");
            }
        } else {
            throw new IllegalArgumentException("Esto no es un NIF válido. Ha de contener 9 carácteres.");
        }
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