import java.sql.*;
package patatafrita;


/**
 * Clase Factura
 *
 * @author Marat Rafael
 */
public class Factura {

    //atributos
    private String trabajoRealizado;
    private float costeFactura;
    private String fechaFactura;

    //constructor vacio
    public Factura() {
    }

    //constructor con todos atributos
    public Factura(String fechaFactura, float costeFactura, String trabajoRealizado) {
        this.fechaFactura = fechaFactura;
        this.costeFactura = costeFactura;
        this.trabajoRealizado = trabajoRealizado;
    }

    //constructor copia
    public Factura(Factura factura) {
        this.fechaFactura = factura.getFechaFactura();
        this.costeFactura = factura.getCosteFactura();
        this.trabajoRealizado = factura.getTrabajoRealizado();
    }

    //setter/getter
    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public float getCosteFactura() {
        return costeFactura;
    }

    public void setCosteFactura(float costeFactura) throws IllegalArgumentException {

        this.costeFactura = costeFactura;
        if (costeFactura < 0) {
            throw new IllegalArgumentException("Valor coste factura no valido");
        }
    }

    public String getTrabajoRealizado() {
        return trabajoRealizado;
    }

    public void setTrabajoRealizado(String trabajoRealizado) {
        this.trabajoRealizado = trabajoRealizado;
    }

    // toString
    @Override
    public String toString() {
        return "Factura { " + ", trabajoRealizado=" + trabajoRealizado + ", costeFactura=" + costeFactura + ", fechaFactura=" + fechaFactura + '}';
    }

    /**
     * metodo estatico para crear una insert
     *
     * @return objeto Factura
     */
    public static Factura crearFactura() throws IllegalArgumentException {
        Factura factura = new Factura();
        try {
            String fechaFactura = Utils.establecerFechaActual();
            factura.setFechaFactura(fechaFactura);

            System.out.println("Coste: ");
            factura.setCosteFactura(Utils.kFloat());

            System.out.println("Trabajos realizados: ");
            factura.setTrabajoRealizado(Utils.kString());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return factura;
    }

    /**
     * buscar una factura por su ID
     *
     * @param numID
     * @return ResultSet
     */
    public static ResultSet consultaFacturaPorID(int numID) {

        String consultaPreparada = "SELECT * FROM FACTURA WHERE ID=?";

        try {
            // crear conexion
            Utils.connection = Utils.conectarBBDD();
            // creamos consulta
            Utils.prst = Utils.connection.prepareStatement(consultaPreparada);
            // establecer parametros
            Utils.prst.setInt(1, numID);
            // ejecutar consulta 
            Utils.rs = Utils.prst.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error consulta");
        }
        return Utils.rs;
    }

    /**
     * Insertar a BBDD informacion apartir de un objeto factura
     *
     * @param factura
     */
    public static void insertarObjetoFacturaBBDD(Factura factura) {
        String insert = "INSERT INTO FACTURA (TRABAJO, COSTE, FECHA )  VALUES (?,?,?)";
        try {

            java.sql.Date sqlDate = Utils.adaptarFechaMYSQL(factura.getFechaFactura());

            Connection con = Utils.conectarBBDD();
            Utils.prst = con.prepareStatement(insert);
            Utils.prst.setString(1, factura.getTrabajoRealizado());
            Utils.prst.setFloat(2, factura.getCosteFactura());
            Utils.prst.setDate(3, sqlDate);
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar datos");
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
     * metode de la INSTANCIA factura para ingresar datos de la factura a la
     * base de datos
     */
    public void insertarObjetoFacturaBBDD() {
        String insert = "INSERT INTO FACTURA (TRABAJO, COSTE, FECHA )  VALUES (?,?,?)";
        try {

            java.sql.Date sqlDate = Utils.adaptarFechaMYSQL(this.getFechaFactura());

            Connection con = Utils.conectarBBDD();
            Utils.prst = con.prepareStatement(insert);
            Utils.prst.setString(1, this.getTrabajoRealizado());
            Utils.prst.setFloat(2, this.getCosteFactura());
            Utils.prst.setDate(3, sqlDate);
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar datos");
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
     * Metodo para mostrar toda informacion de la tabla insert
     */
    public static void mostrarTablaFacturaCompleta() {
        String consulta = "SELECT * FROM FACTURA ORDER BY ID";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery(consulta);

            while (Utils.rs.next()) {
                System.out.println(
                        "ID: " + Utils.rs.getString(1) + ", "
                        + "TRABAJO REALIZADO: " + Utils.rs.getString(2) + ", "
                        + "COSTE: " + Utils.rs.getString(3) + ", "
                        + "FECHA: " + Utils.rs.getString(4) + ", "
                        + "RESERVA ID: " + Utils.rs.getInt(5) + ", "
                        + "VENTA ID: " + Utils.rs.getInt(6) + ", "
                        + "VEHICULO ID: " + Utils.rs.getInt(7)
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al mostrar datos de la tabla");
        } finally {
            try {
                if (Utils.rs != null) {
                    Utils.rs.close();
                }
                if (Utils.st != null) {
                    Utils.st.close();
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
     * metodo para mostrar ResultSet , muestra todo como String , muestra 4
     * columnas
     *
     * @param rs
     */
    public static void mostrarResultSetFactura(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println("Error mostrar datos de la tabla");
        }
    }

    /**
     * metodo para borrar de la base de datos una factura, indicando ID de esta
     * insert
     *
     * @param id
     */
    public static void borrarFacturaBBDD(int id) {
        try {
            String consulta = "DELETE FROM FACTURA WHERE ID=?";
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("factura con id " + id + " eliminada");
        } catch (SQLException ex) {
            System.out.println("Error borrar datos");
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
     * Relacionar una factura con una reserva en BBDD
     *
     * @param idFactura
     * @param idReserva
     */
    public static void relacionarFacturaConReserva(int idFactura, int idReserva) {
        String consulta = "UPDATE FACTURA SET RESERVAID=? WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, idReserva);
            Utils.prst.setInt(2, idFactura);
            Utils.prst.executeUpdate();
            System.out.println("Factura " + idFactura + " esta relacionada con Reserva " + idReserva + " correctamente");
        } catch (SQLException e) {
            System.out.println("Error relacionar Factura con Reserva");
        } finally {
            try {
                if (Utils.st != null) {
                    Utils.st.close();
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
     * Relacionamos una factura con una venta registrada en BBDD
     *
     * @param idFactura
     * @param idVenta
     */
    public static void relacionarFacturaConVenta(int idFactura, int idVenta) {
        String consulta = "UPDATE FACTURA SET VENTAID=? WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, idVenta);
            Utils.prst.setInt(2, idFactura);
            Utils.prst.executeUpdate();
            System.out.println("Factura " + idFactura + " esta relacionada con Venta " + idVenta + " correctamente");
        } catch (SQLException e) {
            System.out.println("Error relacionar Factura con Venta");
        } finally {
            try {
                if (Utils.st != null) {
                    Utils.st.close();
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
     * relacionamos una factura con un vehiculo que ya esta en la base de datos
     *
     * @param idFactura
     * @param idVehiculo
     */
    public static void relacionarFacturaConVehiculo(int idFactura, int idVehiculo) {
        String consulta = "UPDATE FACTURA SET VEHICULOID=? WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, idVehiculo);
            Utils.prst.setInt(2, idFactura);
            Utils.prst.executeUpdate();
            System.out.println("Factura " + idFactura + " esta relacionada con Vehiculo " + idVehiculo + " correctamente");
        } catch (SQLException e) {
            System.out.println("Error relacionar Factura con Vehiculo");
        } finally {
            try {
                if (Utils.st != null) {
                    Utils.st.close();
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
     * Metodo para modifica factura existente
     *
     * @param id
     * @param trabajos
     * @param fechaFactura
     * @param costeFactura
     */
    public static void modificarFacturaBBDD(int id, String trabajos, float costeFactura, String fechaFactura) {
        String consulta = "UPDATE FACTURA SET TRABAJO=?, COSTE=? , FECHA=?  WHERE ID=?";

        java.sql.Date sqlDate = Utils.adaptarFechaMYSQL(fechaFactura);

        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, trabajos);
            Utils.prst.setFloat(2, costeFactura);
            Utils.prst.setDate(3, sqlDate);
            Utils.prst.setInt(4, id);

            Utils.prst.executeUpdate();
            System.out.println("Datos uctualizados correctamente");
        } catch (SQLException ex) {
            System.out.println("Error actualizar datos");
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
     * buscar factura en BBDD segun ID, devuelve true o false
     *
     * @param IDFactura
     * @return boolean o true o false
     */
    public static boolean existFacturaBBDD(int IDFactura) {
        boolean encontrado = false;
        String consulta = "SELECT * FROM FACTURA WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, IDFactura);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            if (Utils.rs != null) {
                encontrado = true;
            }

        } catch (SQLException ex) {
            System.out.println("error buscar factura");
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
     * metodo para buscar una factura en BBDD devuelve un objeto factura
     *
     * @param idFactura
     * @return factura
     */
    public static Factura buscarFacturaBBDD(int idFactura) {
        String consulta = "SELECT TRABAJO, COSTE, FECHA FROM FACTURA WHERE ID=?";
        if (!existFacturaBBDD(idFactura)) {
            return null;
        } else {
            Factura f = new Factura();
            Utils.connection = Utils.conectarBBDD();
            try {
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setInt(1, idFactura);
                Utils.rs = Utils.prst.executeQuery();
                Utils.rs.next();
                f.setTrabajoRealizado(Utils.rs.getString(1));
                f.setCosteFactura(Utils.rs.getFloat(2));
                f.setFechaFactura(Utils.rs.getString(3));
                System.out.println("Factura encontrada y creada " + f.toString());

            } catch (SQLException ex) {
                System.out.println("Error buscar factura");
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
            return f;
        }

    }

}
