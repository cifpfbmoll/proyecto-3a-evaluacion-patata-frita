import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase vehiculo donde se guardan los datos de todos los vehiculos
 * Contiene el conjunto de variables necesarias, 2 enums para la clase y el estado
 * y un Motor @see #Motor.java
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

    private Date fecha_fabricacion;
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
    public Vehiculo(){

    }

    /**
     * Constructor con todos los datos por parámetro
     * @param motor
     * @param fecha_fabricacion
     * @param bastidor
     * @param kilometraje
     * @param autonomia
     * @param puertas
     * @param asientos
     * @param extras
     * @param color
     * @param marca
     * @param modelo
     */
    public Vehiculo(Motor motor, Date fecha_fabricacion, String bastidor, int kilometraje, int autonomia, int puertas, int asientos, int precio, String extras, String color, String marca, String modelo, claseVehiculo tipo, estadoVehiculo estado) {
        //Estos dos llaman al set para comprobar errores
        try {
            this.setPuertas(puertas);
            this.setAsientos(asientos);
            this.setKilometraje(kilometraje);
            this.setAutonomia(autonomia);
        }catch(Exception ex) {
            System.out.println("Error con los datos insertados, reviselos");
        }
        this.motor = motor;
        this.fecha_fabricacion = fecha_fabricacion;
        this.precio = precio;
        this.bastidor = bastidor;
        this.extras = extras;
        this.color = color;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.estado = estado;
    }

    /**
     * Constructor copia
     * @param vec
     */
    public Vehiculo(Vehiculo vec){
        this.motor = vec.getMotor();
        this.fecha_fabricacion = vec.getFecha_fabricacion();
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

    public estadoVehiculo getEstado() { return this.estado; }

    public claseVehiculo getTipo() { return this.tipo; }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Date getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(Date fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
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
        if(kilometraje<0){
            throw new IllegalArgumentException("El kilometraje no puede ser inferior a 0");
        }else{
            this.kilometraje = kilometraje;
        }
    }

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) throws IllegalArgumentException{
        if(autonomia<0){
            throw new IllegalArgumentException("La autonomia no puede ser inferior a 0");
        }else{
            this.autonomia = autonomia;
        }
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) throws IllegalArgumentException{
        if(puertas<=0){
            throw new IllegalArgumentException("No puede haber menos de 1 puerta");
        }else{
            this.puertas = puertas;
        }
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) throws IllegalArgumentException{
        if(asientos<=0){
            throw new IllegalArgumentException("No puede haber menos de 1 asiento");
        }else {
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
        return "Vehiculo{" +
                "motor=" + motor +
                ", fecha_fabricacion=" + fecha_fabricacion +
                ", bastidor='" + bastidor + '\'' +
                ", kilometraje=" + kilometraje +
                ", autonomia=" + autonomia +
                ", puertas=" + puertas +
                ", asientos=" + asientos +
                ", extras='" + extras + '\'' +
                ", color='" + color + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
    /**
     * crear objeto vehiculo
     * @param motor
     * @return Vehiculo
     */
    public static Vehiculo crearVehiculo(Motor motor) {
        Vehiculo vehiculo = new Vehiculo();
        try {
            vehiculo.setMotor(motor); //Seguramente por parametro sea lo mas facil, o llamar a motor y mostrar los diferentes id/datos de los motores
            System.out.println("Ponga la fecha de fabricación del vehiculo: ");
            String date = null;
            //Pedimos la fecha por partes al usuario, formateamos correctamente la fecha y la pasamos a tipo Date
            date += Utils.kString("Dia") + "/";
            date += Utils.kString("Mes") + "/";
            date += Utils.kString("Año");
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            vehiculo.setFecha_fabricacion(fecha);
            vehiculo.setBastidor(Utils.kString("Numero de bastidor"));
            vehiculo.setKilometraje(Utils.kInteger("Kilometraje"));
            vehiculo.setAutonomia(Utils.kInteger("Autonomia"));
            vehiculo.setPuertas(Utils.kInteger("Numero de puertas"));
            vehiculo.setAsientos(Utils.kInteger("Numero de asientos"));
            vehiculo.setExtras(Utils.kString("Escriba los extras del vehiculo"));
            vehiculo.setColor(Utils.kString(Utils.kString("Color del vehiculo")));
            vehiculo.setMarca(Utils.kString("Marca del vehiculo"));
            vehiculo.setModelo(Utils.kString("Modelo de vehiculo"));
        } catch (Exception e) {
            System.out.println("Error al crear el vehiculo, seguramente algun dato introducido fuera incorrecto, pruebe otra vez.");
        }
        return vehiculo;
    }

    /**
     * Insertar datos de la clase actual a la base de datos
     *
     */
    public void insertarDatosVehiculoBBDD() {
        //INSERT de todos los datos excepto ventaid y clientenif, ya que se supone que el vehiculo aun no se ha vendido, para ello habra otro metodo
        if(motor.getId() != -1) {
            String consulta = "INSERT INTO VEHICULO (BASTIDOR, TIPO, ESTADO, KILOMETRAJE, AUTONOMIA, PUERTAS, ASIENTOS, COLOR, MARCA, MODELO, PRECIO, EXTRAS, MOTORID ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try {
                Utils.connection = Utils.conectarBBDD();
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
                Utils.prst.executeUpdate();
                System.out.println("Datos insertados correctomnte!");
            } catch (SQLException e) {
                System.out.println("Error al insertar datos a la BBDD");
            }
        }else{
            System.out.println("Debe guardar primero el motor actual en la base de datos");
        }
    }

    /**
     * buscamos un vehiculo segun id, si no lo ecuentra devuelve null, si lo encuentra devuelve una clase vehiculo con todos los datos
     *
     * @param bastidor
     * @return
     */
    public static Vehiculo buscarVehiculoBBDD(String bastidor) {
        //Testeando con LIKE, es posible que tenga que ser =
        String consulta = "SELECT * FROM VEHICULO WHERE bastidor LIKE ?";
        Vehiculo vehiculo = new Vehiculo();
        try {
            Utils.connection = Utils.conectarBBDD();
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
        } catch (SQLException e) {
            System.out.println("Error al buscar vehiculo");
            vehiculo = null;
        }
        return vehiculo;
    }

    /**
     * Se modifica el concesionario actual en la base de datos con los datos actuales de la clase
     * @return Devuelve 0 si correcto, -1 si error
     */
    public int modificarConcesionarioBBDD() {
        int ret = 0;
        String consulta = "UPDATE VEHICULO SET TIPO=?, ESTADO=?, KILOMETRAJE=?, AUTONOMIA=?, PUERTAS=?, ASIENTOS=?, COLOR=?, MARCA=?, MODELO=?, PRECIO=?, EXTRAS=? WHERE BASTIDOR=?";
        try {
            Utils.connection = Utils.conectarBBDD();
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
            Utils.prst.setString(12, this.getBastidor());
            Utils.prst.executeUpdate();
            System.out.println("Datos actualizados correctamente!");
        } catch (SQLException e) {
            System.out.println("Error actualizar datos");
            ret = -1;
        }
        return ret;
    }

    /**
     * Se borra el vehiculo actual de la base de datos
     */
    public void borrarVehiculoBBDD() {
        String consulta = " DELETE FROM VEHICULO WHERE BASTIDOR LIKE ?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getBastidor());
            Utils.prst.executeUpdate();
            System.out.println("Vehiculo borrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error borrar datos");
        }
    }

    /**
     * Se muestran todos los vehiculos en la base de datos
     */
    public static void mostrarTodosConcesionariosBBDD() {
        String consulta = "SELECT * FROM VEHICULO ORDER BY BASTIDOR";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery(consulta);

            while (Utils.rs.next()) {
                System.out.println("BASTIDOR: " + Utils.rs.getString(1) + "," +
                            "TIPO: " + Utils.rs.getString(2) + "," +
                            "ESTADO: " + Utils.rs.getString(3) + "," +
                            "KILOMETRAJE: " + Utils.rs.getInt(4) + "," +
                            "AUTONOMIA: " + Utils.rs.getInt(5) + "," +
                            "PUERTAS: " + Utils.rs.getInt(6) + "," +
                            "ASIENTOS: " + Utils.rs.getInt(7) + "," +
                            "COLOR: " + Utils.rs.getString(8) + "," +
                            "MARCA: " + Utils.rs.getString(9) + "," +
                            "MODELO: " + Utils.rs.getString(10) + "," +
                            "PRECIO: " + Utils.rs.getInt(11) + "," +
                            "EXTRAS: " + Utils.rs.getString(12));
                //Insertar datos del motor, select con el valor 13 del select actual
            }
        } catch (SQLException e) {
            System.out.println("Error mostrando todos los vehiculos");
        }
    }

    /**
     * Comprueba si el vehiculo actual ya existe en la base de datos
     */
    public boolean existsInDB(){
        boolean ret = false;
        String consulta = "SELECT * FROM VEHICULO WHERE BASTIDOR LIKE ?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1,bastidor);
            if (Utils.rs != null){
                ret = true;
            }else{
                ret = false;
            }
        } catch (SQLException e) {
            System.out.println("Error mostrando todos los vehiculos");
            ret = false;
        }
        return ret;
    }

    /**
     * Comprueba si el vehiculo con ese bastidor existe en la base de datos
     */
    public static boolean existsInDB(String bastidor){
        boolean ret = false;
        String consulta = "SELECT * FROM VEHICULO WHERE BASTIDOR LIKE ?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1,bastidor);
            if (Utils.rs != null){
                ret = true;
            }else{
                ret = false;
            }
        } catch (SQLException e) {
            System.out.println("Error mostrando todos los vehiculos");
            ret = false;
        }
        return ret;
    }
}

