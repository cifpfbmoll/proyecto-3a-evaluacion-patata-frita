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
    int id =-1;
    private String fechaHoraReserva;
    private int espacioReservado;
    private Taller taller;
    private Cliente cliente;
    

    // constructor vacio
    public Reserva() {
    }

    // constructor con todos atributos

    public Reserva(int id, String fechaHoraReserva, int espacioReservado, Taller taller, Cliente cliente) {
        this.id = id;
        this.fechaHoraReserva = fechaHoraReserva;
        this.espacioReservado = espacioReservado;
        this.taller = taller;
        this.cliente = cliente;
    }


    // constructor copia
    public Reserva(Reserva reserva) {
        this.id = reserva.getId();
        this.fechaHoraReserva = reserva.getFechaHoraReserva();
        this.espacioReservado = reserva.getEspacioReservado();
        this.taller = reserva.getTaller();
        this.cliente = reserva.getCliente();
    }

    public int getId() {
        return id;
    }

    public Taller getTaller() {
        return taller;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        return "Reserva { " + 
                "id=" + id + 
                ", fechaHoraReserva=" + fechaHoraReserva + 
                ", espacioReservado=" + espacioReservado + 
                ", taller=" + taller + 
                ", cliente=" + cliente + '}';
    }


    /**
     * Metodo estatico para crear una reserva
     * @return
     * @throws IllegalArgumentException 
     */
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
            
            // TODO setCliente 
            System.out.println("Nif Cliente: ");
            String nifCliente = Utils.kString();
            reserva.setCliente(Cliente.buscarClienteBBDD(nifCliente));
            
            // TODO setTaller  
            System.out.println("Id del Taller: ");
            int idTaller = Utils.kInt();
            reserva.setTaller(Taller.buscarTaller(idTaller));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return reserva;
    }

    /**
     * metodo estatico insertar datos de una reserva en BBDD, apartir de un objeto reserva
     *
     * @param reserva
     */
    public static void insertarDatosReservaBBDD(Reserva reserva) {
        String consulta = "INSERT INTO RESERVA (ESPACIO_RESERVADO, FECHA, TALLERID, CLIENTENIF) VALUES(?,?,?,?)";

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
            Utils.prst.setInt(3, reserva.getTaller().getId());
            Utils.prst.setString(4, reserva.getCliente().getNif());
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
        String consulta = "INSERT INTO RESERVA (ESPACIO_RESERVADO, FECHA, TALLERID, CLIENTENIF ) VALUES(?,?,?,?)";

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
            Utils.prst.setInt(3, this.getTaller().getId());
            Utils.prst.setString(4, this.getCliente().getNif());
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
        String consulta = "SELECT ESPACIO_RESERVADO, FECHA, TALLERID, CLIENTENIF  FROM RESERVA WHERE ID=?";
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
                int idTaller = Utils.rs.getInt(3); // nos devuelve id del taller
                r.setTaller(Taller.buscarTaller(idTaller)); //establecemos taller 
                String nifCliente = Utils.rs.getString(4);// nos devuelve nifCliente
                r.setCliente(Cliente.buscarClienteBBDD(nifCliente)); // setablecemos cliente

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
