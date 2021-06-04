
/**
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.SQLException;


/**
 *
 * @author Karina
 */
public class Cliente extends Persona {

    /**
     * Constructor vacio
     */
    public Cliente() {
    }

    /**
     * Constructor con todos los parametros
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param nif NIF del ciente
     * @param telefono Telefono del cliente
     * @param domicilio Domicilio del cliente
     */
    public Cliente(String nombre, String apellidos, String nif, Integer telefono, String domicilio, String password) {
        super(nombre, apellidos, nif, telefono, domicilio, password);
    }

    /**
     * Constructor copia de un cliente
     *
     * @param copia Cliente a copiar
     */
    public Cliente(Cliente copia) {
        super(copia.getNombre(), copia.getApellidos(), copia.getNif(), copia.getTelefono(), copia.getDomicilio(), copia.getDomicilio());
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Pide por consola los datos del cliente y devuelve una clase
     *
     * @return Cliente con los datos recibidos por consola
     */
    public static Cliente crearCliente() {
        Cliente cliente = new Cliente();
        try {
            cliente.setNombre(Utils.kString("Nombre del cliente"));
            cliente.setApellidos(Utils.kString("Apellidos del cliente"));
            cliente.setNif(Utils.kString("NIF del cliente"));
            cliente.setTelefono(Utils.kInteger("Telefono del cliente"));
            cliente.setDomicilio(Utils.kString("Direccion de cliente"));
        }catch(Exception e){
            System.out.println("Error al insertar los datos, intentelo otra vez");
        }
        return cliente;
    }

    /**
     * Insertar un cliente en la base de datos
     */
    public void insertarClienteBBDD() {
        String consulta = "INSERT INTO CLIENTE (NIF, NOMBRE, APELLIDOS, TELEFONO, DOMICILIO, PASSWORD) VALUES (?,?,?,?,?,?)";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1,this.getNif());
            Utils.prst.setString(2,this.getNombre());
            Utils.prst.setString(3,this.getApellidos());
            Utils.prst.setInt(4,this.getTelefono());
            Utils.prst.setString(5,this.getDomicilio());
            Utils.prst.setString(6,this.getPassword());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctamente!");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos del cliente a la BBDD");
            e.printStackTrace();
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Buscar un cliente almacenado en la base de datos
     *
     * @param nif NIF del cliente a buscar
     * @return
     */
    public static Cliente buscarClienteBBDD(String nif) {
        String consulta = "SELECT * FROM CLIENTE WHERE nif LIKE ?";
        Cliente cliente = new Cliente();
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, nif);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            cliente.setNif(Utils.rs.getString(1));
            cliente.setNombre(Utils.rs.getString(2));
            cliente.setApellidos(Utils.rs.getString(3));
            cliente.setTelefono(Utils.rs.getInt(4));
            cliente.setDomicilio(Utils.rs.getString(5));
            cliente.setPassword(Utils.rs.getString(6));
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente");
            cliente = null;
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
        return cliente;
    }

    /**
     * Modificar un cliente en la base de datos
     *
     * @return
     */
    public int modificarClienteBBDD() {
        int ret = 0;
        String consulta = "UPDATE CLIENTE SET NOMBRE=?, APELLIDOS=?, TELEFONO=?, DOMICILIO=?, PASSWORD=? WHERE NIF=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNombre());
            Utils.prst.setString(2, this.getApellidos());
            Utils.prst.setInt(3, this.getTelefono());
            Utils.prst.setString(4, this.getDomicilio());
            Utils.prst.setString(5, this.getNif());
            Utils.prst.setString(6, this.getPassword());
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
     * Borrar un cliente de la base de datos
     */
    public void borrarClienteBBDD() {
        String consulta = " DELETE FROM CLIENTE WHERE NIF LIKE ?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNif());
            Utils.prst.executeUpdate();
            System.out.println("Cliente borrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error borrar datos");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }
    
    /**
     * Borrar un cliente de la base de datos
     * @param ID
     */
    public static void borrarClienteBBDD(String ID) {
        String consulta = " DELETE FROM CLIENTE WHERE NIF LIKE ?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, ID);
            Utils.prst.executeUpdate();
            System.out.println("Cliente borrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error borrar datos");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Mostrar todos los clientes de la base de datos
     */
    public static void mostrarTodosClienteBBDD() {
        String consulta = "SELECT * FROM CLIENTE ORDER BY NIF";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                //No se mostraran las contraseñas por razones obvias de seguridad
                System.out.print(
                        "NIF: " + Utils.rs.getString(1) + ","
                        + "NOMBRE: " + Utils.rs.getString(2) + ","
                        + "APELLIDOS: " + Utils.rs.getString(3) + ","
                        + "TELEFONO: " + Utils.rs.getInt(4) + ","
                        + "DOMICILIO: " + Utils.rs.getString(5)
                );
            }
        } catch (SQLException e) {
            System.out.println("Error mostrando todos los clientes");
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
    public static Object[][] devolverTodosClienteBBDD() {
        String consulta = "SELECT * FROM CLIENTE ORDER BY NIF";
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement("SELECT count(*) FROM CLIENTE"); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            objectList = new String[Utils.rs.getInt(1)][];
            
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            int i = 0;
            while (Utils.rs.next()) {
                String[] list = new String[5]; // MODIFICAR LONGITUD DE LA LISTA EN OTRAS CLASES
                list[0] = (Utils.rs.getString(1));
                list[1] = (Utils.rs.getString(2));
                list[2] = (Utils.rs.getString(3));
                list[3] = Integer.toString(Utils.rs.getInt(4));
                list[4] = (Utils.rs.getString(5));
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
     * Comprobar si el cliente actual existe en la base de datos
     *
     * @return
     */
    public boolean existsInDB() {
        boolean existe = false;
        String consulta = "SELECT * FROM CLIENTE WHERE NIF LIKE ?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, getNif());
            if (Utils.rs != null) {
                existe = true;
            } else {
                existe = false;
            }
        } catch (SQLException e) {
            System.out.println("No existe el cliente en la base de datos");
            existe = false;
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
        return existe;
    }

    /**
     * Buscar si existe un cliente en concreto en la base de datos
     *
     * @param nif NIF a buscar
     * @return
     */
    public static boolean existsInDB(String nif) {
        boolean existe = false;
        String consulta = "SELECT * FROM CLIENTE WHERE NIF LIKE ?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, nif);
            if (Utils.rs != null) {
                existe = true;
            } else {
                existe = false;
            }
        } catch (SQLException e) {
            System.out.println("No existe el cliente en la base de datos");
            existe = false;
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
        return existe;
    }

    /**
     *  Devuelve todos los datos de los clientes en la base de datos en un archivo txt
     */
    public static void escribirReservasArchivo(){
        Utils.abrirArchivo("Clientes.txt");
        String consulta = "SELECT * FROM CLIENTE";
        try{
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while(Utils.rs.next()){

                Utils.escribirLineaArchivo("Cliente nif: " + Utils.rs.getString(1) + " {");
                Utils.escribirLineaArchivo("    Nombre: " + Utils.rs.getString(2) + " " + Utils.rs.getString(3));
                Utils.escribirLineaArchivo("    Telefono: " + Utils.rs.getString(4));
                Utils.escribirLineaArchivo("    Domicilio:" + Utils.rs.getString(5)+" } ");

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
