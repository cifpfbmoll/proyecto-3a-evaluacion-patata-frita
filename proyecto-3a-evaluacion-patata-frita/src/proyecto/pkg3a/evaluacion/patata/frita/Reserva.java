
package proyecto.pkg3a.evaluacion.patata.frita;

/**
 *
 * @author Marat Rafael
 */
public class Reserva {
    //atributos
    String fechaHoraReserva;
    String espacioReservado;

    // constructor vacio
    public Reserva() {
    }
    
    // constructor con todos atributos
    public Reserva(String fechaHoraReserva, String espacioReservado) {
        this.fechaHoraReserva = fechaHoraReserva;
        this.espacioReservado = espacioReservado;
    }
    
    // constructor copia
        public Reserva(Reserva reserva) {
        this.fechaHoraReserva = reserva.getFechaHoraReserva();
        this.espacioReservado = reserva.getEspacioReservado();
    }

    // getter/setter
    public String getFechaHoraReserva() {
        return fechaHoraReserva;
    }

    public void setFechaHoraReserva(String fechaHoraReserva) {
        this.fechaHoraReserva = fechaHoraReserva;
    }

    public String getEspacioReservado() {
        return espacioReservado;
    }

    public void setEspacioReservado(String espacioReservado) {
        this.espacioReservado = espacioReservado;
    }

    // toString
    @Override
    public String toString() {
        return "Reserva{" + "fechaHoraReserva=" + fechaHoraReserva + ", espacioReservado=" + espacioReservado + '}';
    }
     
    
}//fin clase reserva
