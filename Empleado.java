package eu.fp.concesionario;

import java.sql.SQLException;

/**
 *
 * @author Karina
 */
public class Empleado extends Persona {

    private String puestoTrabajo;
    private Taller taller;
    private Venta venta;

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
    public Empleado(String puestoTrabajo, String nombre, String apellidos, String nif, Integer telefono, String domicilio, String password, Taller taller, Venta venta) {
        super(nombre, apellidos, nif, telefono, domicilio, password);
        this.puestoTrabajo = puestoTrabajo;
        this.taller = taller;
        this.venta = venta;
    }

    /**
     * Constructor copia de empleado
     *
     * @param copia Empleado a copiar
     */
    public Empleado(Empleado copia) {
        super(copia.getNombre(), copia.getApellidos(), copia.getNif(), copia.getTelefono(), copia.getDomicilio(), copia.getPassword());
        this.setPuestoTrabajo(copia.getPuestoTrabajo());
        this.setTaller(Taller.buscarTaller(copia.getTaller().getId()));
        this.setVenta(Venta.buscarVenta(copia.getVenta().getId()));
    }

    // GETTERS Y SETTERS
    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Puesto de trabajo: " + puestoTrabajo + " | VentaID: " + venta + " | TallerID: " + taller; //Sin el conjunto de nominas, eso vendra con la base de datos y serà una simple llamada
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
            empleado.setPassword("Contraseña del empleado");
            empleado.setTaller(Taller.buscarTaller(Utils.kInteger("Taller del empleado")));
            empleado.setVenta(Venta.buscarVenta(Utils.kInteger("Venta del empleado")));
        }catch(Exception e){
            System.out.println("Error al insertar los datos, intentelo otra vez");
        }
        return empleado;
    }

    /**
     * Insertar un empleado en la base de datos
     */
    public void insertarDatosEmpleadoBBDD() {
        String consulta = "INSERT INTO EMPLEADO (NIF, NOMBRE, APELLIDOS, TELEFONO, DOMICILIO, PUESTO, TALLERID, VENTAID, PASSWORD) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNif());
            Utils.prst.setString(2, this.getNombre());
            Utils.prst.setString(3, this.getApellidos());
            Utils.prst.setInt(4, this.getTelefono());
            Utils.prst.setString(5, this.getDomicilio());
            Utils.prst.setString(6, this.getPuestoTrabajo());
            Utils.prst.setInt(7, this.getTaller().getId());
            Utils.prst.setInt(8, this.getVenta().getId());
            Utils.prst.setString(9,this.getPassword());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctomnte!");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos del empleado a la BBDD");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
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
            empleado.setTaller(Taller.buscarTaller(Utils.rs.getInt(7)));
            empleado.setVenta(Venta.buscarVenta(Utils.rs.getInt(8)));
            empleado.setPassword(Utils.rs.getString(9));
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente");
            empleado = null;
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
        return empleado;
    }

    /**
     * Modificar un empleado en la base de datos
     * @return
     */
    public int modificarEmpleadoBBDD() {
        int ret = 0;
        String consulta = "UPDATE EMPLEADO SET NOMBRE=?, APELLIDOS=?, TELEFONO=?, DOMICILIO=?, PUESTO=?, TALLERID=?, VENTAID=?, PASSWORD=? WHERE NIF=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNombre());
            Utils.prst.setString(2, this.getApellidos());
            Utils.prst.setInt(3, this.getTelefono());
            Utils.prst.setString(4, this.getDomicilio());
            Utils.prst.setString(5, this.getPuestoTrabajo());
            Utils.prst.setInt(6, this.getTaller().getId());
            Utils.prst.setInt(7, this.getVenta().getId());
            Utils.prst.setString(8, this.getNif());
            Utils.prst.setString(9, this.getPassword());
            Utils.prst.executeUpdate();
            System.out.println("Datos actualizados correctamente!");
        } catch (SQLException e) {
            System.out.println("Error actualizar datos");
            ret = -1;
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
        return ret;
    }

    /**
     * Borrar un empleado de la base de datos
     */
    public void borrarEmpleadoBBDD() {
        String consulta = " DELETE FROM EMPLEADO WHERE NIF LIKE ?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNif());
            Utils.prst.executeUpdate();
            System.out.println("Empleado borrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error borrando datos, es posible que cuelguen tablas de esta tabla");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Mostrar todos los empleados
     */
    public static void mostrarTodosEmpleadosBBDD() {
        String consulta = "SELECT * FROM EMPLEADO ORDER BY NIF";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                //No se mostraran las contraseñas por razones obvias de seguridad
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
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }
    
    /**
     * Devolver todos los clientes de la base de datos
     * @return 
     */
    public static Object[][] devolverTodosEmpleadosBBDD() {
        String consulta = "SELECT * FROM EMPLEADO ORDER BY NIF";
        String[][] objectList = null;
        try {
            //Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery("SELECT count(*) FROM EMPLEADO");
            Utils.rs.next();
            objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.rs = Utils.st.executeQuery(consulta);
            while (Utils.rs.next()) {
                String[] list = new String[8];
                list[0] = (Utils.rs.getString(1));
                list[1] = (Utils.rs.getString(2));
                list[2] = (Utils.rs.getString(3));
                list[3] = Integer.toString(Utils.rs.getInt(4));
                list[4] = (Utils.rs.getString(5));
                list[5] = (Utils.rs.getString(6));
                list[6] = Integer.toString(Utils.rs.getInt(7));
                list[7] = Integer.toString(Utils.rs.getInt(8));
                objectList[i] = list;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error mostrando todos los clientes");
        } finally {
            try{
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return objectList;
    }

    /**
     * Comprueba si existe el empleado actual en la base de datos
     * @return
     */
    public boolean existsInDB(){
        boolean ret = false;
        String consulta = "SELECT * FROM EMPLEADO WHERE NIF LIKE ?";
        try {
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
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
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
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
        return ret;
    }

    /**
     *  Devuelve todos los datos de empleado en la base de datos en un archivo txt
     */
    public static void escribirReservasArchivo(){
        Utils.abrirArchivo("Empleado.txt");
        String consulta = "SELECT * FROM EMPLEADO";
        try{
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while(Utils.rs.next()){

                Utils.escribirLineaArchivo("Empleado nif: " + Utils.rs.getString(1) + " {");
                Utils.escribirLineaArchivo("    Nombre: " + Utils.rs.getString(2) + " " + Utils.rs.getString(3));
                Utils.escribirLineaArchivo("    Telefono: " + Utils.rs.getString(4));
                Utils.escribirLineaArchivo("    Domicilio:" + Utils.rs.getString(5));
                Utils.escribirLineaArchivo("    Puesto: " + Utils.rs.getString(6));
                Utils.escribirLineaArchivo("    Id taller: " + Utils.rs.getString(7));
                Utils.escribirLineaArchivo("    Id venta: " + Utils.rs.getString(8)+" } ");

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
}
