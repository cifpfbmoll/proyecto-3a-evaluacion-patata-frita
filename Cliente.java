package eu.fp.concesionario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.ResultSet;
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
    public Cliente(String nombre, String apellidos, String nif, Integer telefono, String domicilio) {
        super(nombre, apellidos, nif, telefono, domicilio);
    }

    /**
     * Constructor copia de un cliente
     *
     * @param copia Cliente a copiar
     */
    public Cliente(Cliente copia) {
        super(copia.getNombre(), copia.getApellidos(), copia.getNif(), copia.getTelefono(), copia.getDomicilio());
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
        } catch (Exception e) {
            System.out.println("Error al insertar los datos, intentelo otra vez");
        }
        return cliente;
    }

    /**
     * Insertar un cliente en la base de datos
     */
    public void insertarClienteBBDD() {
        String consulta = "INSERT INTO CLIENTE (NIF, NOMBRE, APELLIDOS, TELEFONO, DOMICLIO) VALUES (?,?,?,?,?)";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNif());
            Utils.prst.setString(2, this.getNombre());
            Utils.prst.setString(3, this.getApellidos());
            Utils.prst.setInt(4, this.getTelefono());
            Utils.prst.setString(5, this.getDomicilio());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctamente!");

        } catch (SQLException e) {
            System.out.println("Error al insertar datos del cliente a la BBDD");
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
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, nif);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            cliente.setNif(Utils.rs.getString(1));
            cliente.setNombre(Utils.rs.getString(2));
            cliente.setApellidos(Utils.rs.getString(3));
            cliente.setTelefono(Utils.rs.getInt(4));
            cliente.setDomicilio(Utils.rs.getString(5));
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente");
            cliente = null;
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
        String consulta = "UPDATE CLIENTE SET NOMBRE=?, APELLIDOS=?, TELEFONO=?, DOMICILIO=? WHERE NIF=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNombre());
            Utils.prst.setString(2, this.getApellidos());
            Utils.prst.setInt(3, this.getTelefono());
            Utils.prst.setString(4, this.getDomicilio());
            Utils.prst.setString(5, this.getNif());
            Utils.prst.executeUpdate();
            System.out.println("Datos actualizados correctamente!");
        } catch (SQLException e) {
            System.out.println("Error actualizar datos");
            ret = -1;
        }
        return ret;
    }

    /**
     * Borrar un cliente de la base de datos
     */
    public void borrarClienteBBDD() {
        String consulta = " DELETE FROM CLIENTE WHERE NIF LIKE ?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getNif());
            Utils.prst.executeUpdate();
            System.out.println("Cliente borrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error borrar datos");
        }
    }

    /**
     * Mostrar todos los clientes de la base de datos
     */
    public static void mostrarTodosClienteBBDD() {
        String consulta = "SELECT * FROM CLIENTE ORDER BY NIF";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery(consulta);
            ResultSet rs;
            while (Utils.rs.next()) {
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
        }
    }

    /**
     * Devolver todos los clientes de la base de datos
     * @return 
     */
    public static Object[][] devolverTodosClienteBBDD() {
        String consulta = "SELECT * FROM CLIENTE ORDER BY NIF";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery("SELECT count(*) FROM CLIENTE"); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs.next();
            String[][] objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.rs = Utils.st.executeQuery(consulta);
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
            return objectList;

        } catch (SQLException e) {
            System.out.println("Error mostrando todos los clientes");
        }
        return null;
    }

    /**
     * Comprobar si el cliente actual existe en la base de datos
     *
     * @return
     */
    public boolean existsInDB() {
        boolean ret = false;
        String consulta = "SELECT * FROM CLIENTE WHERE NIF LIKE ?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, getNif());
            if (Utils.rs != null) {
                ret = true;
            } else {
                ret = false;
            }
        } catch (SQLException e) {
            System.out.println("No existe el cliente en la base de datos");
            ret = false;
        }
        return ret;
    }

    /**
     * Buscar si existe un cliente en concreto en la base de datos
     *
     * @param nif NIF a buscar
     * @return
     */
    public static boolean existsInDB(String nif) {
        boolean ret = false;
        String consulta = "SELECT * FROM CLIENTE WHERE NIF LIKE ?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, nif);
            if (Utils.rs != null) {
                ret = true;
            } else {
                ret = false;
            }
        } catch (SQLException e) {
            System.out.println("No existe el cliente en la base de datos");
            ret = false;
        }
        return ret;
    }
}
