import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase vehiculo donde se guardan los datos de todos los vehiculos Contiene el
 * conjunto de variables necesarias, 2 enums para la clase y el estado y un
 * Motor @see #Motor.java
 *
 * @author Joan
 */
public class Vehiculo {

    public enum claseVehiculo {
        SUV,
        Sedan,
        Sport,
        Coupe,
        Hatchback,
        Convertible,
        Minivan,
        Pickup
    }

    public enum estadoVehiculo {
        Vendido,
        Venta,
        Alquilado
    }
    private Motor motor;
    //Venta y cliente empiezan en null, se debe actualizar la venta al crear el vehiculo y cliente al venderse el vehiculo
    private Venta venta = null;
    private Cliente cliente = null;

    private String bastidor;
    private int kilometraje;
    private int autonomia;
    private int puertas;
    private int asientos;
    private float precio;
    private String extras;
    private String color;
    private String marca;
    private String modelo;
    private claseVehiculo tipo;
    private estadoVehiculo estado;

    /**
     * Constructor vacío
     */
    public Vehiculo() {

    }

    /**
     * Constructor con todos los datos por parámetro
     *
     * @param motor
     * @param bastidor
     * @param kilometraje
     * @param autonomia
     * @param puertas
     * @param asientos
     * @param extras
     * @param color
     * @param marca
     * @param modelo
     * @param cliente
     */
    public Vehiculo(Motor motor, String bastidor, int kilometraje, int autonomia, int puertas, int asientos, int precio, String extras, String color, String marca, String modelo, claseVehiculo tipo, estadoVehiculo estado, Venta venta,Cliente cliente) {
        //Estos dos llaman al set para comprobar errores
        try {
            this.setPuertas(puertas);
            this.setAsientos(asientos);
            this.setKilometraje(kilometraje);
            this.setAutonomia(autonomia);
        } catch (Exception ex) {
            System.out.println("Error con los datos insertados, reviselos");
        }
        this.motor = motor;
        this.precio = precio;
        this.bastidor = bastidor;
        this.extras = extras;
        this.color = color;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.estado = estado;
        this.venta = venta;
        this.cliente = cliente;
    }

    /**
     * Constructor copia
     *
     * @param vec
     */
    public Vehiculo(Vehiculo vec) {
        this.motor = vec.getMotor();
        this.bastidor = vec.getBastidor();
        this.kilometraje = vec.getKilometraje();
        this.autonomia = vec.getAutonomia();
        this.puertas = vec.getPuertas();
        this.asientos = vec.getAsientos();
        this.precio = vec.getPrecio();
        this.extras = vec.getExtras();
        this.color = vec.getColor();
        this.marca = vec.getMarca();
        this.modelo = vec.getModelo();
        this.tipo = vec.getTipo();
        this.estado = vec.getEstado();
        this.venta = vec.getVenta();
        this.cliente = vec.getCliente();
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setTipo(claseVehiculo tipo) {
        this.tipo = tipo;
    }

    public void setEstado(estadoVehiculo estado) {
        this.estado = estado;
    }

    public estadoVehiculo getEstado() {
        return this.estado;
    }

    public claseVehiculo getTipo() {
        return this.tipo;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public String getBastidor() {
        return bastidor;
    }

    public void setBastidor(String bastidor) {
        this.bastidor = bastidor;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) throws IllegalArgumentException {
        if (kilometraje < 0) {
            throw new IllegalArgumentException("El kilometraje no puede ser inferior a 0");
        } else {
            this.kilometraje = kilometraje;
        }
    }

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) throws IllegalArgumentException {
        if (autonomia < 0) {
            throw new IllegalArgumentException("La autonomia no puede ser inferior a 0");
        } else {
            this.autonomia = autonomia;
        }
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) throws IllegalArgumentException {
        if (puertas <= 0) {
            throw new IllegalArgumentException("No puede haber menos de 1 puerta");
        } else {
            this.puertas = puertas;
        }
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) throws IllegalArgumentException {
        if (asientos <= 0) {
            throw new IllegalArgumentException("No puede haber menos de 1 asiento");
        } else {
            this.asientos = asientos;
        }
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Vehiculo{"
                + "motor=" + motor
                + ", bastidor='" + bastidor + '\''
                + ", kilometraje=" + kilometraje
                + ", autonomia=" + autonomia
                + ", puertas=" + puertas
                + ", asientos=" + asientos
                + ", extras='" + extras + '\''
                + ", color='" + color + '\''
                + ", marca='" + marca + '\''
                + ", modelo='" + modelo + '\''
                + '}';
    }

    /**
     * crear objeto vehiculo
     *
     * @param motor
     * @return Vehiculo
     */
    public static Vehiculo crearVehiculo(Motor motor) {
        Vehiculo vehiculo = new Vehiculo();
        try {
            vehiculo.setMotor(motor); //Seguramente por parametro sea lo mas facil, o llamar a motor y mostrar los diferentes id/datos de los motores
            System.out.println("Ponga la fecha de fabricación del vehiculo: ");
            String date = "";
            //Pedimos la fecha por partes al usuario, formateamos correctamente la fecha y la pasamos a tipo Date
            date += Utils.kString("Dia") + "/";
            date += Utils.kString("Mes") + "/";
            date += Utils.kString("Año");
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            vehiculo.setBastidor(Utils.kString("Numero de bastidor"));
            vehiculo.setKilometraje(Utils.kInteger("Kilometraje"));
            vehiculo.setAutonomia(Utils.kInteger("Autonomia"));
            vehiculo.setPuertas(Utils.kInteger("Numero de puertas"));
            vehiculo.setAsientos(Utils.kInteger("Numero de asientos"));
            vehiculo.setExtras(Utils.kString("Escriba los extras del vehiculo"));
            vehiculo.setColor((Utils.kString("Color del vehiculo")));
            vehiculo.setMarca(Utils.kString("Marca del vehiculo"));
            vehiculo.setModelo(Utils.kString("Modelo de vehiculo"));
            System.out.println("Clases de vehiculo: SUV,\n" +
                    "        Sedan,\n" +
                    "        Sport,\n" +
                    "        Coupe,\n" +
                    "        Hatchback,\n" +
                    "        Convertible,\n" +
                    "        Minivan,\n" +
                    "        Pickup");
            vehiculo.setTipo(claseVehiculo.valueOf(Utils.kString("Clase de vehiculo")));
            vehiculo.setEstado(estadoVehiculo.valueOf(Utils.kString("En Venta, Vendido o Alquilado?")));
            Venta.mostrarVenta();
            vehiculo.setVenta(Venta.buscarVenta(Utils.kInt("Venta")));
            Cliente.mostrarTodosClienteBBDD();
            vehiculo.setCliente(Cliente.buscarClienteBBDD(Utils.kString("NIF cliente")));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear el vehiculo, seguramente algun dato introducido fuera incorrecto, pruebe otra vez.");
        }
        return vehiculo;
    }

    /**
     * Insertar datos de la clase actual a la base de datos. Se inserta un
     * cliente vacío si el vehiculo aun no se ha vendido
     *
     */
    public void insertarDatosVehiculoBBDD() {
        //INSERT de todos los datos, cliente puede ser null si aun no se ha vendido el vehiculo
        if (motor.getId() != -1) {
            String consulta = "INSERT INTO VEHICULO (BASTIDOR, TIPO, clase, KILOMETRAJE, AUTONOMIA, PUERTAS, ASIENTOS, COLOR, MARCA, MODELO, PRECIO, EXTRAS, MOTORID, VENTAID, CLIENTENIF ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setString(1, this.getBastidor());
                Utils.prst.setString(2, this.getTipo().toString());
                Utils.prst.setString(3, this.getEstado().toString());
                Utils.prst.setInt(4, this.getKilometraje());
                Utils.prst.setInt(5, this.getAutonomia());
                Utils.prst.setInt(6, this.getPuertas());
                Utils.prst.setInt(7, this.getAsientos());
                Utils.prst.setString(8, this.getColor());
                Utils.prst.setString(9, this.getMarca());
                Utils.prst.setString(10, this.getModelo());
                Utils.prst.setFloat(11, this.getPrecio());
                Utils.prst.setString(12, this.getExtras());
                Utils.prst.setInt(13, motor.getId());
                Utils.prst.setInt(14, venta.getId());
                Utils.prst.setString(15, cliente.getNif());
                Utils.prst.executeUpdate();
                System.out.println("Datos insertados correctomnte!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al insertar datos a la BBDD");
            } finally {
                try {
                    Utils.cerrarVariables();
                } catch (Exception e) {
                    System.out.println("Error al cerrar variables");
                }
            }
        } else {
            System.out.println("Debe guardar primero el motor actual en la base de datos");
        }
    }

    /**
     * buscamos un vehiculo segun id, si no lo ecuentra devuelve null, si lo
     * encuentra devuelve una clase vehiculo con todos los datos
     *
     * @param bastidor
     * @return
     */
    public static Vehiculo buscarVehiculoBBDD(String bastidor) {
        //Testeando con LIKE, es posible que tenga que ser =
        String consulta = "SELECT * FROM VEHICULO WHERE bastidor LIKE ?";
        Vehiculo vehiculo = new Vehiculo();
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, bastidor);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            vehiculo.setBastidor(Utils.rs.getString(1));
            vehiculo.setTipo(claseVehiculo.valueOf(Utils.rs.getString(2)));
            vehiculo.setEstado(estadoVehiculo.valueOf(Utils.rs.getString(3)));
            vehiculo.setKilometraje(Utils.rs.getInt(4));
            vehiculo.setAutonomia(Utils.rs.getInt(5));
            vehiculo.setPuertas(Utils.rs.getInt(6));
            vehiculo.setAsientos(Utils.rs.getInt(7));
            vehiculo.setColor(Utils.rs.getString(8));
            vehiculo.setMarca(Utils.rs.getString(9));
            vehiculo.setModelo(Utils.rs.getString(10));
            vehiculo.setPrecio(Utils.rs.getInt(11));
            vehiculo.setExtras(Utils.rs.getString(12));
            vehiculo.setMotor(Motor.buscarMotorBBDD(Utils.rs.getInt(13)));
            /*
            vehiculo.setVenta(Venta.buscarVentaBBDD(Utils.rs.getInt(14))); //TODO
            //TODO: Comprobar que pasa si se sube un null a la base de datos
            if(Utils.rs.getString(15).length() > 1){
                vehiculo.setCliente(Cliente.buscarClienteBBDD(Utils.rs.getString(15))); //TODO
            }
             */
        } catch (SQLException e) {
            System.out.println("Error al buscar vehiculo");
            vehiculo = null;
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return vehiculo;
    }

    /**
     * Se modifica el vehiculo actual en la base de datos con los datos actuales
     * de la clase
     *
     * @return Devuelve 0 si correcto, -1 si error
     */
    public int modificarVehiculoBBDD() {
        int ret = 0;
        String consulta = "UPDATE VEHICULO SET TIPO=?, ESTADO=?, KILOMETRAJE=?, AUTONOMIA=?, PUERTAS=?, ASIENTOS=?, COLOR=?, MARCA=?, MODELO=?, PRECIO=?, EXTRAS=?, MOTORID=?, VENTAID=?, CLIENTEID=? WHERE BASTIDOR=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getTipo().toString());
            Utils.prst.setString(2, this.getEstado().toString());
            Utils.prst.setInt(3, this.getKilometraje());
            Utils.prst.setInt(4, this.getAutonomia());
            Utils.prst.setInt(5, this.getPuertas());
            Utils.prst.setInt(6, this.getAsientos());
            Utils.prst.setString(7, this.getColor());
            Utils.prst.setString(8, this.getMarca());
            Utils.prst.setString(9, this.getModelo());
            Utils.prst.setFloat(10, this.getPrecio());
            Utils.prst.setString(11, this.getExtras());
            Utils.prst.setInt(12, this.getMotor().getId());
            Utils.prst.setInt(13, this.getVenta().getId());
            Utils.prst.setString(14, this.getCliente().getNif());
            Utils.prst.setString(15, this.getBastidor());
            Utils.prst.executeUpdate();
            System.out.println("Datos actualizados correctamente!");
        } catch (SQLException e) {
            System.out.println("Error actualizar datos");
            ret = -1;
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return ret;
    }

    public static void modificarVehiculoBBDD(String tipo, String estado, int kilometraje, int autonomia, int puertas, int asientos, String color, String marca, String modelo, float precio, String extras, int motor, int venta, String nif, String bastidor) {
        String consulta = "UPDATE VEHICULO SET TIPO=?, ESTADO=?, KILOMETRAJE=?, AUTONOMIA=?, PUERTAS=?, ASIENTOS=?, COLOR=?, MARCA=?, MODELO=?, PRECIO=?, EXTRAS=?, MOTORID=?, VENTAID=?, CLIENTEID=? WHERE BASTIDOR=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, tipo);
            Utils.prst.setString(2, estado);
            Utils.prst.setInt(3, kilometraje);
            Utils.prst.setInt(4, autonomia);
            Utils.prst.setInt(5, puertas);
            Utils.prst.setInt(6, asientos);
            Utils.prst.setString(7, color);
            Utils.prst.setString(8, marca);
            Utils.prst.setString(9, modelo);
            Utils.prst.setFloat(10, precio);
            Utils.prst.setString(11, extras);
            Utils.prst.setInt(12, motor);
            Utils.prst.setInt(13, venta);
            Utils.prst.setString(14, nif);
            Utils.prst.setString(15, bastidor);
            Utils.prst.executeUpdate();
            System.out.println("Datos actualizados correctamente!");
        } catch (SQLException e) {
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
     * Se borra el vehiculo actual de la base de datos
     */
    public void borrarVehiculoBBDD() {
        String consulta = " DELETE FROM VEHICULO WHERE BASTIDOR LIKE ?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getBastidor());
            Utils.prst.executeUpdate();
            System.out.println("Vehiculo borrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al borrar datos, es posible que cuelguen otras tablas de esta");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }
    
    /**
     * Se borra el vehiculo actual de la base de datos
     * @param ID
     */
    public static void borrarVehiculoBBDD(String ID) {
        String consulta = " DELETE FROM VEHICULO WHERE BASTIDOR LIKE ?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, ID);
            Utils.prst.executeUpdate();
            System.out.println("Vehiculo borrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al borrar datos, es posible que cuelguen otras tablas de esta");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Se muestran todos los vehiculos en la base de datos
     */
    public static void mostrarTodosVehiculosBBDD() {
        String consulta = "SELECT * FROM VEHICULO ORDER BY BASTIDOR";
        ResultSet rs = null; //ResultSet para guardar las querys de los motores
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                System.out.print("BASTIDOR: " + Utils.rs.getString(1) + ","
                        + "TIPO: " + Utils.rs.getString(2) + ","
                        + "ESTADO: " + Utils.rs.getString(3) + ","
                        + "KILOMETRAJE: " + Utils.rs.getInt(4) + ","
                        + "AUTONOMIA: " + Utils.rs.getInt(5) + ","
                        + "PUERTAS: " + Utils.rs.getInt(6) + ","
                        + "ASIENTOS: " + Utils.rs.getInt(7) + ","
                        + "COLOR: " + Utils.rs.getString(8) + ","
                        + "MARCA: " + Utils.rs.getString(9) + ","
                        + "MODELO: " + Utils.rs.getString(10) + ","
                        + "PRECIO: " + Utils.rs.getInt(11) + ","
                        + "EXTRAS: " + Utils.rs.getString(12));
                //Con el valor del id del motor buscamos los datos del motor del vehiculo
                //Es posible que realice demasiadas query, otra opcion para solucionarlo
                //Seria coger todos los motores ya que por lo general se comparten un motor
                //Con varios vehiculos y imprimir los datos del motor que toca
                consulta = "SELECT * FROM MOTOR WHERE ID=?";
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setInt(1, Utils.rs.getInt(13));
                rs = Utils.prst.executeQuery();
                System.out.println("ID MOTOR: " + rs.getInt(1) + ","
                        + "TIPO: " + rs.getString(2) + ","
                        + "POTENCIA: " + rs.getFloat(3) + ","
                        + "CILINDRADA: " + rs.getFloat(4) + ","
                        + "NUM_MOTORES: " + rs.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println("Error mostrando todos los vehiculos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Se cargan todos los vehiculos en la base de datos
     */
    public static Vehiculo[] cargarVehiculos() {
        String consulta = "SELECT * FROM VEHICULO ORDER BY BASTIDOR";
        Vehiculo[] vehiculoList = null;
        ResultSet auxiliar = null; //ResultSet para guardar las querys de los motores
        Motor motor = null;
        Venta venta = null;
        Cliente cliente = null;
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery("SELECT COUNT(*) FROM VEHICULO");
            Utils.rs.next();
            vehiculoList = new Vehiculo[Utils.rs.getInt(1)];
            Utils.rs = Utils.prst.executeQuery();
            for(int i=0;Utils.rs.next();i++){
                auxiliar=Utils.rs;
                if(Utils.rs.getInt(13) != 0){
                    motor = Motor.buscarMotorBBDD(Utils.rs.getInt(13));
                    Utils.rs=auxiliar;
                }
                if(Utils.rs.getInt(14) != 0){
                    venta = Venta.buscarVenta(Utils.rs.getInt(14));
                    Utils.rs=auxiliar;
                }
                if(Utils.rs.getString(15) != null){
                    cliente = Cliente.buscarClienteBBDD(Utils.rs.getString(15));
                    Utils.rs=auxiliar;
                }
                //Motor motor, String bastidor, int kilometraje, int autonomia, int puertas, int asientos, int precio, String extras, String color, String marca, String modelo, claseVehiculo tipo, estadoVehiculo estado, Venta venta
                vehiculoList[i] = new Vehiculo(motor, Utils.rs.getString(1),Utils.rs.getInt(4),Utils.rs.getInt(5),Utils.rs.getInt(6),Utils.rs.getInt(7),Utils.rs.getInt(11),Utils.rs.getString(12),Utils.rs.getString(8),Utils.rs.getString(9),Utils.rs.getString(10),claseVehiculo.valueOf(Utils.rs.getString(2)),estadoVehiculo.valueOf(Utils.rs.getString(3)),venta,cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error mostrando todos los vehiculos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return vehiculoList;
    }

    /**
     * Devolver todos los Vehiculos de la base de datos, útil para la gui
     * @return Vehiculo[][]
     */
    //Si hay algun fallo en esta clase puede ser por el finally, no se como reorganizarlo...
    public static Object[][] devolverTodosVehiculoBBDD() {
        String consulta = "SELECT `VEHICULO`.*,`MOTOR`.`tipo`,`MOTOR`.`potencia`,`MOTOR`.`cilindrada` FROM VEHICULO,MOTOR WHERE `VEHICULO`.`motorid` like `MOTOR`.`id` ORDER BY bastidor";
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement("SELECT count(*) FROM Vehiculo"); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                Integer COLUMNAS = 18;
                String[] list = new String[COLUMNAS]; // MODIFICAR LONGITUD DE LA LISTA EN OTRAS CLASES
                int x = 0;
                while (x < COLUMNAS) {
                    switch (x) {
                        case 4:
                            list[x] = (Utils.rs.getString(x + 1) + "km");
                            break;
                        default:
                            list[x] = (Utils.rs.getString(x + 1));
                    }
                    x++;
                }
                objectList[i] = list;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error devolviendo todos los vehiculos");
            e.getStackTrace();
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
     * Comprueba si el vehiculo actual ya existe en la base de datos
     */
    public boolean existsInDB() {
        boolean ret = false;
        String consulta = "SELECT * FROM VEHICULO WHERE BASTIDOR LIKE ?";
        try {

            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, bastidor);
            Utils.rs = Utils.prst.executeQuery();
            if (Utils.rs.next()) {
                ret = true;
            } else {
                ret = false;
            }
        } catch (SQLException e) {
            System.out.println("No existe el vehiculo en la base de datos");
            ret = false;
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return ret;
    }

    /**
     * Comprueba si el vehiculo con ese bastidor existe en la base de datos
     */
    public static boolean existsInDB(String bastidor) {
        boolean ret = false;
        String consulta = "SELECT * FROM VEHICULO WHERE BASTIDOR LIKE ?";
        try {

            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, bastidor);
            if (Utils.rs != null) {
                ret = true;
            } else {
                ret = false;
            }
        } catch (SQLException e) {
            System.out.println("No existe el vehiculo en la base de datos");
            ret = false;
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return ret;
    }

    /**
     *  Devuelve todos los datos de vehiculos en la base de datos en un archivo txt
     */
    public static void escribirVehiculosArchivo(){
        Utils.abrirArchivo("Vehiculo.txt");
        String consulta = "SELECT * FROM VEHICULO";
        try{
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while(Utils.rs.next()){
                Utils.escribirLineaArchivo("Vehiculo bastidor: " + Utils.rs.getString(1) + " {");
                Utils.escribirLineaArchivo("    Tipo: " + Utils.rs.getString(2));
                Utils.escribirLineaArchivo("    Clase: " + Utils.rs.getString(3));
                Utils.escribirLineaArchivo("    Kilometraje:" + Integer.toString(Utils.rs.getInt(4)));
                Utils.escribirLineaArchivo("    Autonomia: " + Integer.toString(Utils.rs.getInt(5)));
                Utils.escribirLineaArchivo("    Puertas: " + Integer.toString(Utils.rs.getInt(6)));
                Utils.escribirLineaArchivo("    Asientos: " + Integer.toString(Utils.rs.getInt(7)));
                Utils.escribirLineaArchivo("    Color: " + Utils.rs.getString(8));
                Utils.escribirLineaArchivo("    Marca: " + Utils.rs.getString(9));
                Utils.escribirLineaArchivo("    Modelo: " + Utils.rs.getString(10));
                Utils.escribirLineaArchivo("    Precio: " + Integer.toString(Utils.rs.getInt(11)));
                Utils.escribirLineaArchivo("    Autonomia: " + Utils.rs.getString(12));
                Utils.escribirLineaArchivo("    MotorID: " + Integer.toString(Utils.rs.getInt(13)));
                Utils.escribirLineaArchivo("    VentaID: " + Integer.toString(Utils.rs.getInt(14)));
                Utils.escribirLineaArchivo("    ClienteNIF: " + Utils.rs.getString(15));
                //Dejamos espacio para poder diferenciar facilmente entre vehiculos
                Utils.escribirLineaArchivo(" ");
            }
            Utils.cerrarArchivo();
            System.out.println("Datos escritos correctamente en fichero");
        }catch(Exception e){
            System.out.println("Problema al leer datos de la base de datos");
        } finally{
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Devuelve los vehiculos filtrados de la base de datos en formato tabla
     *
     * @param bastidor
     * @param tipo
     * @param kilometraje
     * @param asientos
     * @param puertas
     * @param marca
     * @param modelo
     * @param precio
     * @return
     */
    public static Object[][] devolverTodosVehiculosBBDD(String bastidor, String tipo, int kilometraje, int asientos, int puertas,
    String marca, String modelo, int precio) {
        boolean where = false;
        //SQL devuelve Reserva + Nombre cliente + apellidos cliente + tablas relacionadas
        String consulta = "SELECT vehiculo.*\n" +
                "FROM test.vehiculo INNER JOIN motor m on vehiculo.motorid = m.id\n" +
                "LEFT JOIN venta v on vehiculo.ventaid = v.id\n" +
                "LEFT JOIN cliente c on vehiculo.clientenif = c.nif"; //Cambiar test a concesionario
        if (bastidor != null && !where) {
            consulta += " WHERE bastidor like \"" + bastidor + "\"";
            where = true;
        }else if(bastidor != null){
            consulta += " AND bastidor like \"" + bastidor + "\"";
        }
        if (tipo != null && !where) {
            consulta += " WHERE tipo like \"" + tipo + "\"";
            where = true;
        }else if(tipo != null){
            consulta += " AND tipo like \"" + tipo + "\"";
        }
        if ( kilometraje > 0 && !where) {
            consulta += " WHERE kilometraje = \"" + kilometraje + "\"";
            where = true;
        }else if(kilometraje > 0){
            consulta += " AND kilometraje = \"" + kilometraje + "\"";
        }
        if (asientos > 0 && !where){
            consulta += " WHERE asientos = \"" + asientos + "\"";
            where = true;
        }else if ( asientos > 0) {
            consulta += " AND asientos = \"" + asientos + "\"";
        }
        if ( puertas > 0 && !where) {
            consulta += " WHERE puertas = \"" + puertas + "\"";
            where = true;
        }else if(puertas > 0){
            consulta += " AND puertas = \"" + puertas + "\"";
        }
        if (precio > 0 && !where){
            consulta += " WHERE precio = \"" + precio + "\"";
            where = true;
        }else if ( precio > 0) {
            consulta += " AND precio = \"" + precio + "\"";
        }
        if (marca != null && !where) {
            consulta += " WHERE marca like \"" + marca + "\"";
            where = true;
        }else if(marca != null){
            consulta += " AND marca like \"" + marca + "\"";
        }
        if (modelo != null && !where) {
            consulta += " WHERE modelo like \"" + modelo + "\"";
            where = true;
        }else if(modelo != null){
            consulta += " AND modelo like \"" + modelo + "\"";
        }
        consulta += " ORDER BY v.bastidor";
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
                Integer COLUMNAS = 19;
                /**
                 * Bastidor, tipo, clase, kilometraje, autonomia, puertas
                 * asientos, color, marca, modelo, precio, extras, potencia
                 * par, tipo, venta id, nombre, apellidos
                 */
                //ReservaID, Espacio_res, fecha, clientenif, espacios, horario, nombre,apellidos
                String[] list = new String[COLUMNAS];
                list[0] = Utils.rs.getString(1); //Bastidor
                list[1] = Utils.rs.getString(2); //Vehiculo tipo
                list[2] = Utils.rs.getString(3); //Estado vehiculo
                list[3] = Utils.rs.getString(5); //Kilometraje
                list[4] = Utils.rs.getString(7); //Autonomia
                list[5] = Utils.rs.getString(8); //Puertas
                list[6] = Utils.rs.getString(9); //Asientos
                list[7] = Utils.rs.getString(10); //Color
                list[8] = Utils.rs.getString(11); //Marca
                list[9] = Utils.rs.getString(12); //Modelo
                list[10] = Utils.rs.getString(13); //Precio
                list[11] = Utils.rs.getString(14); //Extras
                list[12] = Utils.rs.getString(18); //Potencia
                list[13] = Utils.rs.getString(19); //Cilindrada
                list[14] = Utils.rs.getString(20); //Potencia
                list[15] = Utils.rs.getString(21); //Par
                list[16] = Utils.rs.getString(22); //Tipo
                list[17] = Utils.rs.getString(23); //Id venta
                list[18] = Utils.rs.getString(24); //Horario venta
                list[19] = Utils.rs.getString(25) + Utils.rs.getString(26); //Nombre + apellidos
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
}
