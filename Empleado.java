package eu.fp.concesionario;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Karina
 */
public class Empleado extends Persona {

    private String puestoTrabajo;
    private Integer tallerId;
    private Integer ventaId;
    private Nomina nomina;

    /**
     * Constructor vacio
     */
    public Empleado() {
        super();
    }

    /**
     * Constructor con todos los parametros
     *
     * @param nomina Nominas
     * @param puestoTrabajo Puesto de trabajo
     * @param nombre Nombre del trabajador
     * @param apellidos Apellidos del trabajador
     * @param nif NIF del trabajador
     * @param telefono Telefono del trabajador
     * @param domicilio Domicilio del trabajador
     */
    public Empleado(Nomina nomina, String puestoTrabajo, String nombre, String apellidos, String nif, Integer telefono, String domicilio, Integer tallerId, Integer ventaId) {
        super(nombre, apellidos, nif, telefono, domicilio);
        this.puestoTrabajo = puestoTrabajo;
        this.nomina = nomina;
        this.tallerId = tallerId;
        this.ventaId = ventaId;
    }

    /**
     * Constructor copia de empleado
     *
     * @param copia Empleado a copiar
     */
    public Empleado(Empleado copia) {
        super(copia.getNombre(), copia.getApellidos(), copia.getNif(), copia.getTelefono(), copia.getDomicilio());
        this.setPuestoTrabajo(copia.getPuestoTrabajo());
    }

    // GETTERS Y SETTERS
    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public Integer getTallerId() {
        return tallerId;
    }

    public void setTallerId(Integer tallerId) {
        this.tallerId = tallerId;
    }

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Puesto de trabajo: " + puestoTrabajo + " | VentaID: " + ventaId + " | TallerID: " + tallerId; //Sin el conjunto de nominas, eso vendra con la base de datos y serà una simple llamada
    }

    /**
     * Crea una variavle empleado con los datos recibidos por consola
     * @return Empleado con los datos recibidos por consola
     */
    public static Empleado crearEmpleado(){
        Empleado empleado = new Empleado();
        try{
            empleado.setNombre(Utils.kString("Nombre del empleado"));
            empleado.setApellidos(Utils.kString("Apellidos del empleado"));
            empleado.setNif(Utils.kString("NIF del empleado"));
            empleado.setTelefono(Utils.kInteger("Telefono del empleado"));
            empleado.setDomicilio(Utils.kString("Direccion de empleado"));
            empleado.setPuestoTrabajo(Utils.kString("Puesto del empleado"));
        }catch(Exception e){
            System.out.println("Error al insertar los datos, intentelo otra vez");
        }
        return empleado;
    }

    /**
     * Insertar un empleado en la base de datos
     */
    public void insertarDatosEmpleadoBBDD() {
        String consulta = "INSERT INTO EMPLEADO (NIF, NOMBRE, APELLIDOS, TELEFONO, DOMICILIO, PUESTO, TALLERID, VENTAID) VALUES (?,?,?,?,?,?,?,?)";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNif());
            Utils.prst.setString(2, this.getNombre());
            Utils.prst.setString(3, this.getApellidos());
            Utils.prst.setInt(4, this.getTelefono());
            Utils.prst.setString(5, this.getDomicilio());
            Utils.prst.setString(6, this.getPuestoTrabajo());
            Utils.prst.setInt(7, this.getTallerId());
            Utils.prst.setInt(8, this.getVentaId());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctomnte!");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos del empleado a la BBDD");
        }
    }

    /**
     * Buscar un empleado en la base de datos
     * @param nif Nif del empleado a buscar
     * @return
     */
    public static Empleado buscarEmpleadoBBDD(String nif) {
        String consulta = "SELECT * FROM EMPLEADO WHERE nif LIKE ?";
        Empleado empleado = new Empleado();
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, nif);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            empleado.setNif(Utils.rs.getString(1));
            empleado.setNombre(Utils.rs.getString(2));
            empleado.setApellidos(Utils.rs.getString(3));
            empleado.setTelefono(Utils.rs.getInt(4));
            empleado.setDomicilio(Utils.rs.getString(5));
            empleado.setPuestoTrabajo(Utils.rs.getString(6));
            empleado.setTallerId(Utils.rs.getInt(7));
            empleado.setVentaId(Utils.rs.getInt(8));
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente");
            empleado = null;
        }
        return empleado;
    }

    /**
     * Modificar un empleado en la base de datos
     * @return
     */
    public int modificarEmpleadoBBDD() {
        int ret = 0;
        String consulta = "UPDATE EMPLEADO SET NOMBRE=?, APELLIDOS=?, TELEFONO=?, DOMICILIO=?, PUESTO=?, TALLERID=?, VENTAID=? WHERE NIF=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNombre());
            Utils.prst.setString(2, this.getApellidos());
            Utils.prst.setInt(3, this.getTelefono());
            Utils.prst.setString(4, this.getDomicilio());
            Utils.prst.setString(5, this.getPuestoTrabajo());
            Utils.prst.setInt(6, this.getTallerId());
            Utils.prst.setInt(7, this.getVentaId());
            Utils.prst.setString(8, this.getNif());
            Utils.prst.executeUpdate();
            System.out.println("Datos actualizados correctamente!");
        } catch (SQLException e) {
            System.out.println("Error actualizar datos");
            ret = -1;
        }
        return ret;
    }

    /**
     * Borrar un empleado de la base de datos
     */
    public void borrarEmpleadoBBDD() {
        String consulta = " DELETE FROM EMPLEADO WHERE NIF LIKE ?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNif());
            Utils.prst.executeUpdate();
            System.out.println("Empleado borrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error borrar datos");
        }
    }

    /**
     * Mostrar todos los empleados
     */
    public static void mostrarTodosEmpleadosBBDD() {
        String consulta = "SELECT * FROM EMPLEADO ORDER BY NIF";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery(consulta);
            ResultSet rs;
            while (Utils.rs.next()) {
                System.out.print(
                    "NIF: " + Utils.rs.getString(1) + "," +
                    "NOMBRE: " + Utils.rs.getString(2) + "," +
                    "APELLIDOS: " + Utils.rs.getString(3) + "," +
                    "TELEFONO: " + Utils.rs.getInt(4) + "," +
                    "DOMICILIO: " + Utils.rs.getString(5) + "," +
                    "PUESTO: " + Utils.rs.getString(6) + "," +
                    "TALLERID: " + Utils.rs.getInt(7) + "," +
                    "VENTAID: " + Utils.rs.getInt(8)
                );
            }
        } catch (SQLException e) {
            System.out.println("Error mostrando todos los empleados");
        }
    }
    
    /**
     * Devolver todos los clientes de la base de datos
     * @return 
     */
    public static Object[][] devolverTodosEmpleadosBBDD() {
        String consulta = "SELECT * FROM EMPLEADO ORDER BY NIF";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery("SELECT count(*) FROM EMPLEADO");
            Utils.rs.next();
            String[][] listaClientes = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.rs = Utils.st.executeQuery(consulta);
            while (Utils.rs.next()) {
                String[] cl = new String[5];
                cl[0] = (Utils.rs.getString(1));
                cl[1] = (Utils.rs.getString(2));
                cl[2] = (Utils.rs.getString(3));
                cl[3] = Integer.toString(Utils.rs.getInt(4));
                cl[4] = (Utils.rs.getString(5));
                listaClientes[i] = cl;
                i++;
            }
            return listaClientes;

        } catch (SQLException e) {
            System.out.println("Error mostrando todos los clientes");
        }
        return null;
    }

    /**
     * Comprueba si existe el empleado actual en la base de datos
     * @return
     */
    public boolean existsInDB(){
        boolean ret = false;
        String consulta = "SELECT * FROM EMPLEADO WHERE NIF LIKE ?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1,getNif());
            if (Utils.rs != null){
                ret = true;
            }else{
                ret = false;
            }
        } catch (SQLException e) {
            System.out.println("No existe el empleado en la base de datos");
            ret = false;
        }
        return ret;
    }

    /**
     * Busca si existe un empleado en concreto y devuelve el resultado
     * @param nif NIF del empleado a comprobar
     * @return
     */
    public static boolean existsInDB(String nif){
        boolean ret = false;
        String consulta = "SELECT * FROM EMPLEADO WHERE NIF LIKE ?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1,nif);
            if (Utils.rs != null){
                ret = true;
            }else{
                ret = false;
            }
        } catch (SQLException e) {
            System.out.println("No existe el empleado en la base de datos");
            ret = false;
        }
        return ret;
    }
}
