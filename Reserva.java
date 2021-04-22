
/**
 * Clase Reserva
 *
 * @author Marat Rafael
 */
import java.sql.*;

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
        if (espacioReservado.length() == 0) {
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

    /**
     * insertar datos de una reserva en BBDD, apartir de un objeto reserva
     *
     * @param reserva
     */
    public static void insertarDatosReservaBBDD(Reserva reserva) {
        String consulta = "INSERT INTO RESERVA (ESPACIO_RESERVADO, FECHA_HORA_RESERVA) VALUES(?,?)";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, reserva.getEspacioReservado());
            Utils.prst.setString(2, reserva.getFechaHoraReserva());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctomente señor!");
        } catch (SQLException e) {
            System.out.println("Error insertar datos");
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.prst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error cerrar conexion");
            }
        }
    }

    /**
     * buscar reserva por su ID (Primary key - unico) devuelve numero de id si
     * encuentra si no encuentra develve -1
     *
     * @param id
     * @return
     */
    public static int buscarReservaBBDD(int id) {
        String consulta = "SELECT * FROM RESERVA WHERE ID=? ORDER BY ID";
        int posicion = -1;
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.rs = Utils.prst.executeQuery();

            while (Utils.rs.next()) {
                posicion = Utils.rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error buscar reseva");
        } finally {
            try {
                if (Utils.rs != null) {
                    Utils.rs.close();
                }
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones");
            }
        }
        return posicion;
    }

    /**
     * modificar BBDD de la tabla reserva
     *
     * @param id
     * @param fechaHoraReserva
     * @param espacioReservado
     */
    public static void modificarReservaBBDD(int id, String fechaHoraReserva, String espacioReservado) {
        String consulta = "UPDATE RESERVA SET ESPACIO_RESERVADO=? FECHA_HORA_RESERVA=?,  WHERE ID=?";

        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(2, fechaHoraReserva);
            Utils.prst.setString(1, espacioReservado);
            Utils.prst.setInt(3, id);
            Utils.prst.executeUpdate();
            System.out.println("Datos modificados correctamente señor!");
        } catch (SQLException e) {
            System.out.println("Error modificar datos");
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones");
            }
        }
    }

    public static void mostrarTodasReservas() {
        String consulta = "SELECT * FROM RESERVA ORDER BY ID";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery(consulta);

            while (Utils.rs.next()) {
                System.out.println(
                        "ID: " + Utils.rs.getInt(1) + ", "
                        + "FECHA HORA DE RESERVA: " + Utils.rs.getString(2) + ", "
                        + "ESPACIO RESERVADO: " + Utils.rs.getString(3)
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar datos");
        } finally {
            try {
                if (Utils.rs != null) {
                    Utils.rs.close();
                }
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones");
            }
        }
    }

    public static void borrarReserva(int id) {
        String consulta = "DELETE FROM RESERVA WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("reserva con id " + id + " borrado con exito");
        } catch (SQLException e) {
            System.out.println("Error al borrar reserva");
        } finally {
            try {
                if (Utils.rs != null) {
                    Utils.rs.close();
                }
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones");
            }
        }
    }
}
