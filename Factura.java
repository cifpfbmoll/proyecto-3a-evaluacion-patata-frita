import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;

/**
 * Clase Factura
 *
 * @author Marat Rafael
 */
public class Factura {

    //atributos
    private int id = -1; //Se carga cuando se lee de la base de datos unicamente
    private String trabajoRealizado;
    private float costeFactura;
    private String fechaFactura;
    private Reserva reserva;
    private Venta venta;
    private Vehiculo vehiculo;

    /**
     * constructor vacio
     */
    public Factura() {
    }

    /**
     * Constructor con todos los atributos
     *
     * @param trabajoRealizado
     * @param costeFactura
     * @param fechaFactura
     * @param reserva
     * @param venta
     * @param vehiculo
     */
    public Factura(String trabajoRealizado, float costeFactura, String fechaFactura, Reserva reserva, Venta venta, Vehiculo vehiculo) {
        this.trabajoRealizado = trabajoRealizado;
        this.costeFactura = costeFactura;
        this.fechaFactura = fechaFactura;
        this.reserva = reserva;
        this.venta = venta;
        this.vehiculo = vehiculo;
    }

    /**
     * Constructor copia
     *
     * @param factura
     */
    public Factura(Factura factura) {
        this.fechaFactura = factura.getFechaFactura();
        this.costeFactura = factura.getCosteFactura();
        this.trabajoRealizado = factura.getTrabajoRealizado();
        this.reserva = factura.getReserva();
        this.venta = factura.getVenta();
        this.vehiculo = factura.getVehiculo();
    }

    /**
     * constructos solo con Reserva (sin venta y vehiculo)
     *
     * @param trabajoRealizado
     * @param costeFactura
     * @param fechaFactura
     * @param reserva
     */
    public Factura(String trabajoRealizado, float costeFactura, String fechaFactura, Reserva reserva) {
        this.trabajoRealizado = trabajoRealizado;
        this.costeFactura = costeFactura;
        this.fechaFactura = fechaFactura;
        this.reserva = reserva;
    }

    /**
     * Constructor con Venta y Vehiculo (sin reserva)
     *
     * @param trabajoRealizado
     * @param costeFactura
     * @param fechaFactura
     * @param venta
     * @param vehiculo
     */
    public Factura(String trabajoRealizado, float costeFactura, String fechaFactura, Venta venta, Vehiculo vehiculo) {
        this.trabajoRealizado = trabajoRealizado;
        this.costeFactura = costeFactura;
        this.fechaFactura = fechaFactura;
        this.venta = venta;
        this.vehiculo = vehiculo;
    }

    //setter/getter
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public String getTrabajoRealizado() {
        return trabajoRealizado;
    }

    public void setTrabajoRealizado(String trabajoRealizado) {
        this.trabajoRealizado = trabajoRealizado;
    }

    /**
     * toString
     *
     * @return
     */
    @Override
    public String toString() {
        return "Factura{"
                + "id=" + id
                + ", trabajoRealizado=" + trabajoRealizado
                + ", costeFactura=" + costeFactura
                + ", fechaFactura=" + fechaFactura
                + ", reserva=" + reserva
                + ", venta=" + venta
                + ", vehiculo=" + vehiculo + '}';
    }

    /**
     * metodo estatico para crear una factura con objeto Reserva
     *
     * @return objeto Factura que tiene reserva
     */
    public static Factura crearFacturaConReserva() throws IllegalArgumentException {
        Factura factura = new Factura();
        try {
            String fechaFactura = Utils.establecerFechaActual();
            factura.setFechaFactura(fechaFactura);

            System.out.println("Coste: ");
            factura.setCosteFactura(Utils.kFloat());

            System.out.println("Trabajos realizados: ");
            factura.setTrabajoRealizado(Utils.kString());
            System.out.println("mostramos todas las reservas");
            Reserva.mostrarTodasReservas();
            System.out.println("Id de la Reserva: ");
            int ReservaId = Utils.kInt();
            // buscamos reserva y la establecemos

            factura.setReserva(Reserva.buscarReservaBBDD(ReservaId));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return factura;
    }

    /**
     * metodo estatico para crear una factura con objeto Venta y Vehiculo
     *
     * @return objeto Factura que tiene venta y vehiculo
     */
    public static Factura crearFacturaConVentaVehiculo() throws IllegalArgumentException {
        Factura factura = new Factura();
        try {
            String fechaFactura = Utils.establecerFechaActual();
            factura.setFechaFactura(fechaFactura);

            System.out.println("Coste: ");
            factura.setCosteFactura(Utils.kFloat());

            System.out.println("Trabajos realizados: ");
            factura.setTrabajoRealizado(Utils.kString());

            System.out.println("Id de la Venta: ");
            int VentaId = Utils.kInt();
            // buscamos venta y la establecemos
            factura.setVenta(Venta.buscarVenta(VentaId));

            System.out.println("Bastidor del Vehiculo: ");
            String VehiculoId = Utils.kString();
            // buscamos vehiculo y la establecemos
            factura.setVehiculo(Vehiculo.buscarVehiculoBBDD(VehiculoId));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return factura;
    }

    /**
     * metodo obsoleto? buscar una factura por su ID
     *
     * @param numID
     * @return ResultSet
     */
    public static ResultSet consultaFacturaPorID(int numID) {

        String consultaPreparada = "SELECT * FROM FACTURA WHERE ID=?";

        try {
            // creamos consulta
            Utils.prst = Utils.connection.prepareStatement(consultaPreparada);
            // establecer parametros
            Utils.prst.setInt(1, numID);
            // ejecutar consulta 
            Utils.rs = Utils.prst.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error consulta");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return Utils.rs;
    }

    /**
     * metodo estatico para insertar factura a BBDD, apartir de un objeto
     * factura
     *
     * @param factura
     */
    public static void insertarObjetoFacturaBBDD(Factura factura) {
        String insert = "INSERT INTO FACTURA (TRABAJO, COSTE, FECHA, RESERVAID, VENTAID, VEHICULOID )  VALUES (?,?,?,?,?,?)";
        try {

            java.sql.Date sqlDate = Utils.adaptarFechaMYSQL(factura.getFechaFactura());

            Utils.prst = Utils.connection.prepareStatement(insert);
            Utils.prst.setString(1, factura.getTrabajoRealizado());
            Utils.prst.setFloat(2, factura.getCosteFactura());
            Utils.prst.setDate(3, sqlDate);
            Utils.prst.setInt(4, factura.getReserva().getId());
            Utils.prst.setInt(5, factura.getVenta().getId());
            Utils.prst.setString(6, factura.getVehiculo().getBastidor());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar datos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * metode de la INSTANCIA factura para ingresar datos de la factura a la
     * base de datos
     */
    public void insertarObjetoFacturaBBDD() {
        String insert = "INSERT INTO FACTURA (TRABAJO, COSTE, FECHA, RESERVAID, VENTAID, VEHICULOID )  VALUES (?,?,?,?,?,?)";
        try {

            java.sql.Date sqlDate = Utils.adaptarFechaMYSQL(this.getFechaFactura());

            Utils.prst = Utils.connection.prepareStatement(insert);
            Utils.prst.setString(1, this.getTrabajoRealizado());
            Utils.prst.setFloat(2, this.getCosteFactura());
            Utils.prst.setDate(3, sqlDate);
            if (this.getReserva() != null){
                Utils.prst.setInt(4, this.getReserva().getId());
            }else{
                Utils.prst.setInt(4, -1);
            }
            if (this.getVenta()!=null){
                Utils.prst.setInt(5, this.getVenta().getId());
            }else{
                Utils.prst.setInt(5, -1);
            }
            Utils.prst.setString(6, this.getVehiculo().getBastidor());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctamente");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar datos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Metodo para mostrar toda informacion de la tabla factura
     */
    public static void mostrarTablaFacturaCompleta() {
        String consulta = "SELECT * FROM FACTURA ORDER BY ID";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();

            while (Utils.rs.next()) {
                System.out.println(
                        "ID: " + Utils.rs.getString(1) + ", "
                        + "TRABAJO REALIZADO: " + Utils.rs.getString(2) + ", "
                        + "COSTE: " + Utils.rs.getString(3) + ", "
                        + "FECHA: " + Utils.rs.getString(4) + ", "
                        + "RESERVA ID: " + Utils.rs.getInt(5) + ", "
                        + "VENTA ID: " + Utils.rs.getInt(6) + ", "
                        + "VEHICULO BASTIDOR: " + Utils.rs.getString(7)
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al mostrar datos de la tabla");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * obsoleto??? metodo para mostrar ResultSet , muestra todo como String ,
     * muestra 6 columnas
     *
     * @param rs
     */
    public static void mostrarResultSetFactura(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + " " + rs.getString(2)
                        + " " + rs.getString(3)
                        + " " + rs.getString(4)
                        + " " + rs.getString(5)
                        + " " + rs.getString(6)
                        + " " + rs.getString(7));
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
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("factura con id " + id + " eliminada");
        } catch (SQLException ex) {
            System.out.println("Error borrar datos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
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
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, idReserva);
            Utils.prst.setInt(2, idFactura);
            Utils.prst.executeUpdate();
            System.out.println("Factura " + idFactura + " esta relacionada con Reserva " + idReserva + " correctamente");
        } catch (SQLException e) {
            System.out.println("Error relacionar Factura con Reserva");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
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
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, idVenta);
            Utils.prst.setInt(2, idFactura);
            Utils.prst.executeUpdate();
            System.out.println("Factura " + idFactura + " esta relacionada con Venta " + idVenta + " correctamente");
        } catch (SQLException e) {
            System.out.println("Error relacionar Factura con Venta");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * relacionamos una factura con un vehiculo que ya esta en la base de datos
     *
     * @param idFactura
     * @param VehiculoBastidor
     */
    public static void relacionarFacturaConVehiculo(int idFactura, String VehiculoBastidor) {
        String consulta = "UPDATE FACTURA SET VEHICULOID=? WHERE ID=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, VehiculoBastidor);
            Utils.prst.setInt(2, idFactura);
            Utils.prst.executeUpdate();
            System.out.println("Factura " + idFactura + " esta relacionada con Vehiculo " + VehiculoBastidor + " correctamente");
        } catch (SQLException e) {
            System.out.println("Error relacionar Factura con Vehiculo");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
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
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
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
        boolean existe = false;
        String consulta = "SELECT * FROM FACTURA WHERE ID=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, IDFactura);
            Utils.rs = Utils.prst.executeQuery();
            if (Utils.rs.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            System.out.println("error buscar factura");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return existe;
    }

    /**
     * metodo para buscar una factura en BBDD devuelve un objeto factura
     *
     * @param idFactura
     * @return factura
     */
    public static Factura buscarFacturaBBDD(int idFactura) {
        String consulta = "SELECT TRABAJO, COSTE, FECHA, RESERVAID, VENTAID, VEHICULOID FROM FACTURA WHERE ID=?";
        if (!existFacturaBBDD(idFactura)) {
            return null;
        } else {
            Factura f = new Factura();
            try {
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setInt(1, idFactura);
                Utils.rs = Utils.prst.executeQuery();
                Utils.rs.next();
                f.setId(idFactura);
                f.setTrabajoRealizado(Utils.rs.getString(1));
                f.setCosteFactura(Utils.rs.getFloat(2));
                f.setFechaFactura(Utils.rs.getString(3));
                int ReservaID = Utils.rs.getInt(4);// nos devuelve id de la reserva
                f.setReserva(Reserva.buscarReservaBBDD(ReservaID)); // establecer reserva
                int VentaID = Utils.rs.getInt(5);// nos devuelve id de la venta
                f.setVenta(Venta.buscarVenta(VentaID)); // establecer venta
                String VehiculoID = Utils.rs.getString(6);// nos devuelve bastidor id de la vehiculo
                f.setVehiculo(Vehiculo.buscarVehiculoBBDD(VehiculoID)); // establecer vehiculo
                System.out.println("Factura encontrada y creada " + f.toString());

            } catch (SQLException ex) {
                System.out.println("Error buscar factura");
            } finally {
                try {
                    Utils.cerrarVariables();
                } catch (Exception e) {
                    System.out.println("Error al cerrar variables");
                }
            }
            return f;
        }

    }

    /**
     * Devolver todos las facturas de la base de datos
     *
     * @return objectList
     */
    public static Object[][] devolverTodasFacturasBBDD() {
        String consulta = "SELECT * FROM FACTURA ORDER BY ID";
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement("SELECT COUNT(*) FROM FACTURA");
            Utils.rs = Utils.prst.executeQuery(); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs.next();
            objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                String[] list = new String[6]; // MODIFICAR LONGITUD DE LA LISTA EN OTRAS CLASES

                list[0] = (Utils.rs.getString(1));
                list[1] = (Utils.rs.getString(2));
                if (Utils.rs.getString(5) != null) {
                    list[2] = ("Venta " + Utils.rs.getString(5));
                } else {
                    list[2] = ("Taller " + Utils.rs.getString(6));
                }
                list[3] = (Utils.rs.getString(7));
                list[4] = (Utils.rs.getString(4));
                list[5] = (Utils.rs.getString(3));
                objectList[i] = list;
                i++;
            }
            return objectList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error mostrando todos los clientes");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return objectList;
    }

    /**
     * Devuelve todos los datos de factura en la base de datos en un archivo txt
     */
    public static void escribirFacturasArchivo() {
        Utils.abrirArchivo("Factura.txt");
        String consulta = "SELECT * FROM FACTURA";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {

                Utils.escribirLineaArchivo("Factura id: " + Utils.rs.getString(1) + " {");
                Utils.escribirLineaArchivo("    Trabajo: " + Utils.rs.getString(2));
                Utils.escribirLineaArchivo("    Coste: " + Utils.rs.getString(3));
                Utils.escribirLineaArchivo("    Fecha:" + Utils.rs.getString(4));
                Utils.escribirLineaArchivo("    Reserva: " + Utils.rs.getString(5));
                Utils.escribirLineaArchivo("    Venta: " + Utils.rs.getString(6));
                Utils.escribirLineaArchivo("    Vehiculo: " + Utils.rs.getString(7) + " } ");

                //Dejamos espacio para poder diferenciar facilmente entre vehiculos
                Utils.escribirLineaArchivo(" ");
            }
            Utils.cerrarArchivo();
            System.out.println("Datos escritos correctamente en fichero");
        } catch (Exception e) {
            System.out.println("Problema al leer datos de la base de datos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    public static Object[][] devolverTodasFacturasBBDD(String nif) {
        String consulta = "SELECT `factura`.*,`cliente`.`nif` "
                + "FROM `factura` "
                + "LEFT JOIN `vehiculo` ON `vehiculo`.`bastidor` = `factura`.`vehiculoid` "
                + "LEFT JOIN `cliente` ON `vehiculo`.`clientenif` = `cliente`.`nif` "
                + "WHERE \"" + nif + "\" like `vehiculo`.`clientenif` ORDER BY ID";
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement("SELECT COUNT(*) FROM FACTURA");
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                String[] list = new String[7]; // MODIFICAR LONGITUD DE LA LISTA EN OTRAS CLASES

                list[0] = (Utils.rs.getString(1));
                list[1] = (Utils.rs.getString(2));
                if (Utils.rs.getString(5) != null) {
                    list[2] = ("Taller " + Utils.rs.getString(5));
                } else {
                    list[2] = ("Reserva " + Utils.rs.getString(6));
                }
                list[3] = (Utils.rs.getString(7));
                list[4] = (Utils.rs.getString(8));
                list[5] = (Utils.rs.getString(4));
                list[6] = (Utils.rs.getString(3));
                objectList[i] = list;
                i++;
            }
            return objectList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error mostrando todos los clientes");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return objectList;
    }

    /**
     * Descargar una factura de forma local desde la tabla data[0] = ID, data[1]
     * = Concepto, data[2] = Local, data[3] = Vehículo, data[4] = NIF, data[5] =
     * Fecha, data[6] = Coste,
     *
     * @param data Información de la factura a imprimir
     * @throws Exception
     */
    public static void descargarFactura(String[] data) throws Exception {
        File txt = new File("factura_" + data[4] + "_" + data[0] + ".txt");
        BufferedWriter escritor = new BufferedWriter(new FileWriter(txt));

        String separador = "##################################################";

        escritor.newLine();
        escritor.write(separador);
        escritor.newLine();
        escritor.newLine();
        escritor.write("   NIF: " + data[4] + "      Vehículo: " + data[3]);
        escritor.newLine();
        escritor.newLine();
        escritor.write(separador);
        escritor.newLine();
        escritor.newLine();
        escritor.write("    ID de factura: " + data[0]);
        escritor.newLine();
        escritor.write("            Fecha: " + data[5]);
        escritor.newLine();
        escritor.newLine();
        escritor.write("     Local emisor: " + data[2]);
        escritor.newLine();
        escritor.write("         Concepto: " + data[1]);
        escritor.newLine();
        escritor.write("            Coste: " + data[6]);
        escritor.newLine();
        escritor.newLine();
        escritor.write(separador);
        escritor.newLine();
        escritor.close();
    }

    /**
     * Devolver facturas de la base de datos compatible con el filtro
     *
     * @param trabajoRealizado
     * @param costeFactura
     * @param fechaFactura
     * @return
     */
    public static Object[][] devolverTodosFacturaBBDD(String trabajoRealizado, float costeFactura, String fechaFactura) {
        boolean where = false;
        //SQL devuelve Reserva + Nombre cliente + apellidos cliente + tablas relacionadas
        String consulta = "select * from test.factura f  join test.reserva r on f.reservaid = r.id join test.venta v on f.ventaid = v.id join test.vehiculo v2 on f.vehiculoid = v2.bastidor";

        if (trabajoRealizado != null && !where) {
            consulta += " WHERE f.trabajo like \"" + trabajoRealizado + "\"";
            where = true;
        } else if (trabajoRealizado != null) {
            consulta += " AND f.trabajo like \"" + trabajoRealizado + "\"";
        }

        if (costeFactura > 0 && !where) {
            consulta += " WHERE f.coste = \"" + costeFactura + "\"";
            where = true;
        } else if (costeFactura > 0) {
            consulta += " AND f.coste = \"" + costeFactura + "\"";
        }

        if (fechaFactura != null && !where) {
            consulta += " WHERE f.fecha = \"" + fechaFactura + "\"";
            where = true;
        } else if (fechaFactura != null) {
            consulta += " AND f.fecha = \"" + fechaFactura + "\"";
        }

        consulta += " ORDER BY f.ID";
        //Borrar prints, solo para testeo
        System.out.println(consulta);
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.last();
            objectList = new String[Utils.rs.getRow()][];
            int i = 0;
            Utils.rs.first();
            while (Utils.rs.next()) {
                //Columnas tiene que ser el numero de columnas que devuelva vuestro sql adaptado
                //Contar únicamente que columnas son importantes!
                Integer COLUMNAS = 7;
                //1-trabajo, 2-coste, 3-fecha, 4-vehiculo_id, 5-espacio_reservado, 6-taller_id, 7-nif_cliente
                String[] list = new String[COLUMNAS];
                list[0] = Utils.rs.getString(2); // trabajo
                list[1] = Utils.rs.getString(3); // coste
                list[2] = Utils.rs.getString(4); // fecha
                list[3] = Utils.rs.getString(7); // vehiculo_id
                list[4] = Utils.rs.getString(9); // espacio_reservado
                list[5] = Utils.rs.getString(11); // taller_id
                list[6] = Utils.rs.getString(12); // nif_cliente

                objectList[i] = list;
                i++;
            }
            return objectList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error mostrando todos los motores");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return objectList;
    }
}
