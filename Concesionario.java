import java.sql.SQLException;

/**
 * CONCESIONARIOS
 * Esta clase  guarda la informacion sobre los diferentes concesionarios del proyecto.
 * @author Jose Luis Cardona
 * @version 1 - 29/03/2021
 */
import java.sql.*;

public class Concesionario {

    private int id = -1;
    private String ubicacion;
    private String nombre;
    private int telefono;
    private Taller taller;
    private Venta venta;

    public Concesionario() {
    }

    public Concesionario(String ubicacion, String nombre, int telefono, Taller taller, Venta venta) {
        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.taller = taller;
        this.venta = venta;
    }

    public Concesionario(Concesionario copia) {
        this.id = copia.getId();
        this.ubicacion = copia.getUbicacion();
        this.nombre = copia.getNombre();
        this.telefono = copia.getTelefono();
    }

    public int getId() {
        return id;
    }

    public Taller getTaller() {
        return taller;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) throws IllegalArgumentException {
        if (ubicacion.length() == 0) {
            throw new IllegalArgumentException("Ubicación no introducida.");
        } else {
            this.ubicacion = ubicacion;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws IllegalArgumentException {
        if (nombre.length() == 0) {
            throw new IllegalArgumentException("Nombre no introducido.");
        } else {
            this.nombre = nombre;
        }
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) throws IllegalArgumentException {
        if (telefono < 999) {
            throw new IllegalArgumentException("Número de teléfono inválido o no completo.");
        } else {
            this.telefono = telefono;
        }
    }

    @Override
    public String toString() {
        return "Concesionario{"
                + ", ubicacion='" + ubicacion + '\''
                + ", nombre='" + nombre + '\''
                + ", telefono=" + telefono
                + '}';
    }

    /**
     * crear objeto concesionario
     *
     * @return
     */
    public static Concesionario crearConcesionario() {
        Concesionario concesionario = new Concesionario();
        try {
            System.out.println("Ubicacion: ");
            concesionario.setUbicacion(Utils.kString());
            System.out.println("Nombre: ");
            concesionario.setNombre(Utils.kString());
            System.out.println("Telefono: ");
            concesionario.setTelefono(Utils.kInt());
            System.out.println("Taller id: ");
            int tallerId = Utils.kInt();
            // TODO concesionario.setTaller(buscarTallerBBDD(tallerId));
        } catch (Exception e) {
            System.out.println("Error al crear concesionario");
        }
        return concesionario;
    }
    
    /**
     * insertamos datos del concesionario a BBDD a partir de un objeto
     *
     * @param concesionario
     */
    public static void insertarDatosConcesionarioBBDD(Concesionario concesionario) {
        String consulta = "INSERT INTO CONCESIONARIO (UBICACION, NOMBRE, TELEFONO ) VALUES (?,?,?)";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, concesionario.getUbicacion());
            Utils.prst.setString(2, concesionario.getNombre());
            Utils.prst.setInt(3, concesionario.getTelefono());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctomnte señor!");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos a la BBDD");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Método para insertar los datos del concesionario actual a la base de datos
     */
    public void insertarDatosConcesionarioBBDD() {
        String consulta = "INSERT INTO CONCESIONARIO (UBICACION, NOMBRE, TELEFONO ) VALUES (?,?,?)";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getUbicacion());
            Utils.prst.setString(2, this.getNombre());
            Utils.prst.setInt(3, this.getTelefono());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctomnte señor!");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos a la BBDD");

        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }


    }

    /**
     * metodo de instancia para comprobar si concesionario esta en BBDD
     *
     * @return
     */
    public boolean existBD() {
        String consulta = "SELECT * FROM CONCESIONARIO WHERE ID=?";
        boolean existe = false;
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.getId());
            Utils.rs = Utils.prst.executeQuery();
            if (Utils.rs.next()) {
                existe = true;
            }

        } catch (SQLException e) {
            System.out.println("Error consultar BBDD");
            e.printStackTrace();
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
     * Metodo para buscar concesionarios mediante la id.
     * @param id
     * @return Objeto "concesionario" con sus datos guardados.
     */
    public static Concesionario buscarConcesionario(int id) {
        String consulta = "SELECT NOMBRE, UBICACION, TELEFONO FROM CONCESIONARIO WHERE ID=?";
        if (!existConcesionario(id)) {
            return null;
        } else {
            Concesionario concesionario = new Concesionario();
            try {
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setInt(1, id);
                Utils.rs = Utils.prst.executeQuery();
                Utils.rs.next();
                concesionario.setNombre(Utils.rs.getString(1));
                concesionario.setUbicacion(Utils.rs.getString(2));
                concesionario.setTelefono(Utils.rs.getInt(3));
                System.out.println("El concesionario ha sido encontrado y creado " + concesionario.toString());

            } catch (SQLException ex) {
                System.out.println("¡ERROR! No se ha encontrado el concesionario.");
            } finally {
                try{
                    Utils.cerrarVariables();
                }catch (Exception e){
                    System.out.println("Error al cerrar variables");
                }
            }
            return concesionario;
        }
    }

    /**
     * Metodo para ver los datos de los concesionarios de la BBDD.
     */
    public static void mostrarConcesionarios() {
        String consulta = "SELECT * FROM CONCESIONARIO ORDER BY ID";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery(consulta);

            while (Utils.rs.next()) {
                System.out.println(
                        "ID: "+Utils.rs.getInt(1) + ", " +
                                "NOMBRE : "+Utils.rs.getString(2) + ", " +
                                "UBICACION : "+Utils.rs.getString(3) + ", " +
                                "TELEFONO : "+Utils.rs.getInt(4));
            }
        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se han podido mostrar los datos");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Metodo para borrar concesionarios de la BBDD.
     * @param id
     */
    public static void borrarConcesionario(int id) {
        try {
            String consulta = "DELETE FROM CONCESIONARIO WHERE ID=?";
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("El concesionario " + id + " se ha borrado.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se han podido borrar los datos.");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Metodo para modificar los datos de los concesionarios de la BBDD.
     * @param id
     * @param nombre
     * @param ubicacion
     * @param telefono
     */
    public static void modificarConcesionario(int id, String nombre, String ubicacion, int telefono) {
        String consulta = "UPDATE CONCESIONARIO SET NOMBRE=?, UBICACION=? , TELEFONO=?  WHERE ID=?";

        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, nombre);
            Utils.prst.setString(2, ubicacion);
            Utils.prst.setInt(3, telefono);
            Utils.prst.setInt(4, id);

            Utils.prst.executeUpdate();
            System.out.println("Los datos han sido modificados con exito.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se han podido modificar los datos.");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * 1º metodo exist que sirve para buscar concesionarios mediante la id.
     * @param id
     * @return Booleano llamado "encontrado" el cual si sale true es que el concesionario ha sido
     * hallado, si sale false significa que no se ha localizado.
     */
    public static boolean existConcesionario(int id) {
        boolean encontrado = false;
        String consulta = "SELECT * FROM CONCESIONARIO WHERE ID=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            if (Utils.rs != null) {
                encontrado = true;
            }

        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se ha encontrado el concesionario.");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
        return encontrado;
    }

    /**
     * 2º metodo exist que sirve para buscar concesionarios mediante la id.
     * @return Booleano llamado "encontrado" el cual si sale true es que el concesionario ha sido
     * hallado, si sale false significa que no se ha localizado.
     */
    public boolean existConcesionario() {
        boolean encontrado = false;
        String consulta = "SELECT * FROM CONCESIONARIO WHERE ID=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.id);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            if (Utils.rs != null) {
                encontrado = true;
            }

        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se ha encontrado el concesionario.");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
        return encontrado;
    }
}