
/**
 * CONCESIONARIOS
 * Esta clase  guarda la información sobre los diferentes concesionarios del proyecto.
 *
 * @author José Luis Cardona
 * @version 1 - 29/03/2021
 */
import java.sql.*;

public class Concesionario {

    private String ubicacion;
    private String nombre;
    private int telefono;

    public Concesionario() {
    }

    public Concesionario(String ubicacion, String nombre, int telefono) {

        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    //TODO: Cambiar a getters
    public Concesionario(Concesionario copia) {

        this.ubicacion = copia.ubicacion;
        this.nombre = copia.nombre;
        this.telefono = copia.telefono;
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
        } catch (Exception e) {
            System.out.println("Error al crear concesionario");
        }
        return concesionario;
    }

    /**
     * insertamos datos del concesionario a BBDD a partid de un objeto
     *
     * @param concesionario
     */
    public static void insertarDatosConcesionarioBBDD(Concesionario concesionario) {
        String consulta = "INSERT INTO CONCESIONARIO (UBICACION, NOMBRE, TELEFONO ) VALUES (?,?,?)";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, concesionario.getUbicacion());
            Utils.prst.setString(2, concesionario.getNombre());
            Utils.prst.setInt(3, concesionario.getTelefono());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctomnte señor!");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos a la BBDD");
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error cerrar conexion");
            }
        }
    }

    /**
     * buscamos un concesionario segun id, si nolo ecuentra devuelve -1
     *
     * @param id
     * @return
     */
    public static int buscarConcesionarioBBDD(int id) {
        String consulta = "SELECT * FROM CONCESIONARIO WHERE ID=?";
        int posicion = -1;
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.rs = Utils.prst.executeQuery();

            while (Utils.rs.next()) {
                posicion = Utils.rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error buscar concesionario");
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
                System.out.println("Error cerrar connexion");
            }
        }
        return posicion;
    }

    /**
     * modificamos concesionario ya existente
     *
     * @param ID
     * @param ubicacion
     * @param nombre
     * @param telefono
     */
    public static void modificarConcesionarioBBDD(int ID, String ubicacion, String nombre, int telefono) {
        String consulta = "UPDATE CONCESIONARIO SET UBICACION=?, NOMBRE=?, TELEFONO=? WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, ubicacion);
            Utils.prst.setString(2, nombre);
            Utils.prst.setInt(3, telefono);
            Utils.prst.executeUpdate();
            System.out.println("Datos actualizados correctamente señor !");
        } catch (SQLException e) {
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
                System.out.println("Error cerrar connexion");
            }
        }
    }

    public static void borrarConcesionarioBBDD(int id) {
        String consulta = " DELETE FROM CONCESIONARIO WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("Concesionario con id " + id + " borrado correctamente");

        } catch (SQLException e) {
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
                System.out.println("Error cerrar connexion");
            }
        }
    }

    public static void mostrarTodosConcesionariosBBDD() {
        String consulta = "SELECT * FROM CONCESIONARIO ORDER BY ID";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery(consulta);

            while (Utils.rs.next()) {
                System.out.println(
                        "ID: " + Utils.rs.getString(1) + ", "
                        + "UBICACION: " + Utils.rs.getString(2) + ", "
                        + "NOMBRE: " + Utils.rs.getString(3) + ", "
                        + "TELEFONO: " + Utils.rs.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println("Error mostrar todos concesionarios");
        }
    }
}
