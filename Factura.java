
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase Factura
 *
 * @author Marat Rafael
 */
public class Factura {

    //atributos
    private static int idFacturaGeneral = 1;
    private int idFactura = idFacturaGeneral;

    private String fechaFactura;
    private double costeFactura;
    private String trabajoRealizado;

    //constructor vacio
    public Factura() {
        idFacturaGeneral++;
    }

    //constructor con todos atributos
    public Factura(String fechaFactura, double costeFactura, String trabajoRealizado) {
        idFacturaGeneral++;
        this.fechaFactura = fechaFactura;
        this.costeFactura = costeFactura;
        this.trabajoRealizado = trabajoRealizado;
    }

    //constructor copia
    public Factura(Factura factura) {
        idFacturaGeneral++;
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

    public double getCosteFactura() {
        return costeFactura;
    }

    public void setCosteFactura(double costeFactura) throws IllegalArgumentException {

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

    public int getIdFactura() {
        return idFactura;
    }

    // toString
    @Override
    public String toString() {
        return "Factura { " + "idFactura=" + idFactura + ", fechaFactura=" + fechaFactura + ", costeFactura=" + costeFactura + ", trabajoRealizado=" + trabajoRealizado + '}';
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
            factura.setCosteFactura(Utils.kDouble());

            System.out.println("Trabajos realizados: ");
            factura.setTrabajoRealizado(Utils.kString());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return factura;
    }

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
     * metodo para meter en la BBDD una insert con 4 campos
     *
     * @param facturaID int insert id ( a lo mejor lo quitamos para usar
     * autoincrement en BBDD)
     * @param fechaFactura String fecha de la insert
     * @param facturaCoste Double
     * @param trabajosRealizados String
     */
    public static void insertarDatosFacturaBBDD(int facturaID, String fechaFactura, double facturaCoste, String trabajosRealizados) {
        String factura = "INSERT INTO factura VALUES (?,?,?,?)";
        try {
            Connection con = Utils.conectarBBDD();
            Utils.prst = con.prepareStatement(factura);
            Utils.prst.setInt(1, facturaID);
            Utils.prst.setString(2, fechaFactura);
            Utils.prst.setDouble(3, facturaCoste);
            Utils.prst.setString(4, trabajosRealizados);

            Utils.prst.executeUpdate();
            System.out.println("Datos insertados");

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
     * Insertar a BBDD informacion apartir de un objeto factura
     *
     * @param factura
     */
    public static void insertarObjetoFacturaBBDD(Factura factura) {
        String insert = "INSERT INTO factura VALUES (?,?,?,?)";
        try {
            Connection con = Utils.conectarBBDD();
            Utils.prst = con.prepareStatement(insert);
            Utils.prst.setInt(1, factura.getIdFactura());
            Utils.prst.setString(2, factura.getFechaFactura());
            Utils.prst.setDouble(3, factura.getCosteFactura());
            Utils.prst.setString(4, factura.getTrabajoRealizado());

            Utils.prst.executeUpdate();
            System.out.println("Datos insertados");

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
                System.out.println(Utils.rs.getString(1) + " " + Utils.rs.getString(2) + " " + Utils.rs.getString(3) + " " + Utils.rs.getString(4));
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
     * metodo para borrar de la base de datos una insert, indicando ID de esta
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
     * Metodo para modifica insert existente
     *
     * @param id
     * @param fechaFactura
     * @param costeFactura
     * @param trabajosRealizados
     */
    public static void modificarFacturaBBDD(int id, String fechaFactura, double costeFactura, String trabajosRealizados) {
        String consulta = "UPDATE FACTURA SET FECHA=? , COSTE=? , TRABAJOREALIZADO=? WHERE ID=?";

        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, fechaFactura);
            Utils.prst.setDouble(2, costeFactura);
            Utils.prst.setString(3, trabajosRealizados);
            Utils.prst.setInt(4, id);

            Utils.prst.executeUpdate();
            System.out.println("Datos uctualizados correctamente");
        } catch (SQLException ex) {
            System.out.println("Error actualizar datos");
        }finally{
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

    public static int buscarFacturaBBDD(int IDFactura) {
        int posicion = -1;
        String consulta = "SELECT * FROM FACTURA WHERE ID=?";
        try {

            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, IDFactura);
            Utils.rs = Utils.prst.executeQuery();

            if (Utils.rs.next()) {
                Utils.rs.next();
                posicion = Utils.rs.getInt(1);
                return posicion;
            } else {
                return posicion;
            }

        } catch (SQLException ex) {
            System.out.println("error buscar factura");
            return posicion;
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
