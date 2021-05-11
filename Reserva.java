package patatafrita;

/**
 * Clase Reserva
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

    public void setEspacioReservado(String espacioReservado) throws IllegalArgumentException {
        this.espacioReservado = espacioReservado;
        if (espacioReservado.length() == 0 ) {
            throw new IllegalArgumentException("Espacio reservado no puede estar vacio");
        }
    }

    // toString
    @Override
    public String toString() {
        return "Reserva {" + "fechaHoraReserva=" + fechaHoraReserva + ", espacioReservado=" + espacioReservado + '}';
    }

    public static Reserva crearReserva() throws IllegalArgumentException {
        Reserva reserva = new Reserva();
        try {
            String fecha = Utils.establecerFechaActual();
            reserva.setFechaHoraReserva(fecha);

            System.out.println("Espacio reservado: ");
            reserva.setEspacioReservado(Utils.kString());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return reserva;
    }
}
