package patatafrita;

import java.sql.SQLException;

/**
 * CONCESIONARIOS
 * Esta clase  guarda la información sobre los diferentes concesionarios del proyecto.
 * @author José Luis Cardona
 * @version 1 - 29/03/2021
 */
public class Concesionario {
    private int id;
    private String ubicacion;
    private String nombre;
    private int telefono;

    public Concesionario() {
    }

    public Concesionario(int id, String ubicacion, String nombre, int telefono) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.telefono = telefono;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) throws IllegalArgumentException{
        if (ubicacion.length()==0){
            throw new IllegalArgumentException("Ubicación no introducida.");
        }else {
            this.ubicacion = ubicacion;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws IllegalArgumentException{
        if (nombre.length()==0){
            throw new IllegalArgumentException("Nombre no introducido.");
        }else {
            this.nombre = nombre;
        }
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) throws IllegalArgumentException {
        if(telefono<999){
            throw new IllegalArgumentException("Número de teléfono inválido/no completo.");
        }else {
            this.telefono = telefono;
        }
    }

    @Override
    public String toString() {
        return "Concesionario{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                '}';
    }
    
    /**
     * Método para crear concesionarios junto a sus datos.
     * @return Objeto concesionario.
     */
    public static Concesionario crearConcesionario() {
        Concesionario concesionario = new Concesionario();
        try {
            System.out.println("Escribe el nombre : ");
            concesionario.setNombre(Utils.kString());
            System.out.println("Escribe la ubicacion : ");
            concesionario.setUbicacion(Utils.kString());
            System.out.println("Escribe el telefono : ");
            concesionario.setTelefono(Utils.kInt());
        } catch (Exception ex) {
            System.out.println("¡ERROR! el objeto no se pudo crear.");
        }
        return concesionario;
    }

    /**
     * Método para guardar los datos de los concesionarios dentro de la base de datos.
     * @param concesionario
     */
    public static void guardarDatosConcesionario(Concesionario concesionario) {
        String consulta = "INSERT INTO CONCESIONARIO (UBICACION, NOMBRE, TELEFONO) VALUES (?,?,?)";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, concesionario.getNombre());
            Utils.prst.setString(2, concesionario.getUbicacion());
            Utils.prst.setInt(3,concesionario.getTelefono());
            Utils.prst.executeUpdate();
            System.out.println("Datos guardados con éxito.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! no se pudieron guardar los datos del concesionario en la BBDD.");
        } finally {
            if (Utils.prst != null) {
                try {
                    Utils.prst.close();//cierra el objeto Statement llamado prst
                } catch (SQLException throwables) {
                    System.out.println("¡ERROR! no se ha podido cerrar la conexion.");
                }
            }
        }
    }
    //TODO: Te faltan funciones, buscar, modificar, borrar, mostrar y los dos exists
    //No cierres la conexion cuando termines, la conexion se mantiene hasta que el usuario se va
}
