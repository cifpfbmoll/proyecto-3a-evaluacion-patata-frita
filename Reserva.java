/**
 * Clase Reserva
 *
 * @author Marat Rafael
 */
import java.sql.*;
import java.text.*;
import java.util.Date;

public class Reserva {

    //atributos
    String fechaHoraReserva;
    int espacioReservado;

    // constructor vacio
    public Reserva() {
    }

    // constructor con todos atributos
    public Reserva(String fechaHoraReserva, int espacioReservado) {
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

    public int getEspacioReservado() {
        return espacioReservado;
    }

    public void setEspacioReservado(int espacioReservado) throws IllegalArgumentException {
        this.espacioReservado = espacioReservado;
        if (espacioReservado <= 0) {
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
//            String fechaReserva = Utils.establecerFechaActual();
//            reserva.setFechaHoraReserva(fechaReserva);
            System.out.println("Fecha de reserva ( formato dd-MM-yyyy HH:mm )");
            String fechaReserva = Utils.kString();

            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date miFecha = null;
            try {
                miFecha = formatter.parse(fechaReserva);
            } catch (ParseException ex) {
                System.out.println("Error aplicar formato fecha");
            }
            reserva.setFechaHoraReserva(fechaReserva);
            System.out.println("Espacio reservado: ");
            reserva.setEspacioReservado(Utils.kInt());

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
        String consulta = "INSERT INTO RESERVA (ESPACIO_RESERVADO, FECHA) VALUES(?,?)";

        // convertir string fechaHoraReserva a date
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date myDate = null;
        try {
            myDate = formatter.parse(reserva.getFechaHoraReserva());
        } catch (ParseException ex) {
            System.out.println("Error aplicar formato fecha");
        }
        // creamos objeto sql date y le pasamos date anteriormente creada
        Object fechaSQL = new java.sql.Timestamp(myDate.getTime());
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, reserva.getEspacioReservado());
            //ojo es objeto fechaSQL
            Utils.prst.setObject(2, fechaSQL);
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
     * metodo de la INSTANCIA para insertar datos
     */
    public void insertarDatosReservaBBDD() {
        String consulta = "INSERT INTO RESERVA (ESPACIO_RESERVADO, FECHA) VALUES(?,?)";

        // convertir string fechaHoraReserva a date
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date myDate = null;
        try {
            myDate = formatter.parse(fechaHoraReserva);
        } catch (ParseException ex) {
            System.out.println("Error aplicar formato fecha");
        }
        // creamos objeto sql date y le pasamos date anteriormente creada
        Object fechaSQL = new java.sql.Timestamp(myDate.getTime());
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.getEspacioReservado());
            //ojo es objeto fechaSQL
            Utils.prst.setObject(2, fechaSQL);
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
     * buscar reserva por su ID, devuelve true o false
     *
     * @param id
     * @return
     */
    public static boolean existReservaBBDD(int id) {
        String consulta = "SELECT * FROM RESERVA WHERE ID=? ORDER BY ID";
        boolean encontrado = false;
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            if (Utils.rs != null) {
                encontrado = true;
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
        return encontrado;
    }

    /**
     * modificar BBDD de la tabla reserva
     *
     * @param id
     * @param fechaHoraReserva
     * @param espacioReservado
     */
    public static void modificarReservaBBDD(int id, String fechaHoraReserva, int espacioReservado) {
        String consulta = "UPDATE RESERVA SET ESPACIO_RESERVADO=?, FECHA=? WHERE ID=?";

        try {
            // convertir string fechaHoraReserva a date
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date myDate = null;
            try {
                myDate = formatter.parse(fechaHoraReserva);
            } catch (ParseException ex) {
                System.out.println("Error aplicar formato fecha");
            }
            // creamos objeto sql date y le pasamos date anteriormente creada
            Object fechaSQL = new java.sql.Timestamp(myDate.getTime());

            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, espacioReservado);
            //Utils.prst.setDate(2, sqlDate);
            Utils.prst.setObject(2, fechaSQL);
            Utils.prst.setInt(3, id);
            System.out.println(Utils.prst.toString());

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

    /**
     * mostrar todos Reservas de la BBDD
     */
    public static void mostrarTodasReservas() {
        String consulta = "SELECT * FROM RESERVA ORDER BY ID";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery(consulta);

            while (Utils.rs.next()) {
                System.out.println(
                        "ID: " + Utils.rs.getInt(1) + ", "
                        + "ESPACIO RESERVADO: " + Utils.rs.getString(2) + ", "
                        + "FECHA: " + Utils.rs.getString(3) + ", "
                        + "TALLER: " + Utils.rs.getInt(4) + ", "
                        + "NIF CLIENTE: " + Utils.rs.getString(5)
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

    /**
     * Borrar una reserva pasando ID de la reserva
     *
     * @param id
     */
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

    /**
     * relacionar una Reserva con un ID del Taller
     *
     * @param ReservaID
     * @param TallerID
     */
    public static void relacionarReservaConTaller(int ReservaID, int TallerID) {
        String consulta = "UPDATE RESERVA SET TALLERID=? WHERE ID=?";

        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, TallerID);
            Utils.prst.setInt(2, ReservaID);
            Utils.prst.executeUpdate();
            System.out.println("Reserva " + ReservaID + " esta relacionado con Taller " + TallerID + " con exito");
        } catch (SQLException e) {
            System.out.println("Error al relacionar Reserva " + ReservaID + " con Taller " + TallerID);
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
    }

    /**
     * relacionar una Reserva con Cliente NIF
     *
     * @param ReservaID
     * @param ClienteNIF
     */
    public static void relacionarReservaConCliente(int ReservaID, String ClienteNIF) {
        String consulta = "UPDATE RESERVA SET CLIENTENIF=? WHERE ID=?";

        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, ClienteNIF);
            Utils.prst.setInt(2, ReservaID);
            Utils.prst.executeUpdate();
            System.out.println("Reserva " + ReservaID + " esta relacionado con Cliente " + ClienteNIF + " con exito");
        } catch (SQLException e) {
            System.out.println("Error al relacionar Reserva " + ReservaID + " con Cliente " + ClienteNIF);
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
    }

    /**
     * buscar una reserva en BBDD y devuelve un objeto reserva
     *
     * @param idReserva
     * @return reserva
     */
    public static Reserva buscarReservaBBDD(int idReserva) {
        String consulta = "SELECT ESPACIO_RESERVADO, FECHA  FROM RESERVA WHERE ID=?";
        if (!Reserva.existReservaBBDD(idReserva)) {
            return null;
        } else {
            Reserva r = new Reserva();
            try {
                Utils.connection = Utils.conectarBBDD();
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setInt(1, idReserva);
                Utils.rs = Utils.prst.executeQuery();
                Utils.rs.next();
                r.setEspacioReservado(Utils.rs.getInt(1));
                r.setFechaHoraReserva(Utils.rs.getString(2));
                System.out.println("Reserva encontrada y creada " + r.toString());

            } catch (Exception e) {
                System.out.println("Error buscar reserva");
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
                } catch (SQLException ex) {
                    System.out.println("Error cerrar conexion");
                }
            }
            return r;
        }
    }
}
