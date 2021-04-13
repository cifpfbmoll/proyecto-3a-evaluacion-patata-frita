/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karina
 */
public class Cliente extends Persona {
    private Integer reserva;
    private Integer factura;

    /**
     * Constructor vacio
     */
    public Cliente() {
    }
    
    /**
     * Constructor con todos los parametros
     * @param reserva   Reserva del cliente
     * @param factura   Factura del cliente
     * @param nombre    Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param nif       NIF del ciente
     * @param telefono  Telefono del cliente
     * @param domicilio Domicilio del cliente
     */
    public Cliente(Integer reserva, Integer factura, String nombre, String apellidos, String nif, Integer telefono, String domicilio) {
        super(nombre, apellidos, nif, telefono, domicilio);
        this.reserva = reserva;
        this.factura = factura;
    }
    
    /**
     * Constructor copia de un cliente
     * @param copia Cliente a copiar
     */
    public Cliente(Cliente copia) {
        super(copia.getNombre(), copia.getApellidos(), copia.getNif(), copia.getTelefono(), copia.getDomicilio());
        this.setReserva(copia.getReserva());
        this.setFactura(copia.getFactura());
    }
    
    // GETTERS Y SETTERS
    public Integer getReserva() {
        return reserva;
    }

    public void setReserva(Integer reserva) {
        this.reserva = reserva;
    }

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }
        
    //TODO falta el toString, basta con llamar al super.toString(), reservas y facturas iran por base de datos
    @Override
    public String toString() {
        return super.toString();
    }

}
