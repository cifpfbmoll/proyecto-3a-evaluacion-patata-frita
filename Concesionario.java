
/**
 * CONCESIONARIOS
 * Esta clase  guarda la información sobre los diferentes concesionarios del proyecto.
 *
 * @author José Luis Cardona
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

    //TODO: Cambiar a getters
    public Concesionario(Concesionario copia) {

        this.ubicacion = copia.ubicacion;
        this.nombre = copia.nombre;
        this.telefono = copia.telefono;
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
     * metodo de INSTANCIA para insertar datos de concesionario a la base de
     * datos
     */
    public void insertarDatosConcesionarioBBDD() {
        String consulta = "INSERT INTO CONCESIONARIO (UBICACION, NOMBRE, TELEFONO ) VALUES (?,?,?)";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getUbicacion());
            Utils.prst.setString(2, this.getNombre());
            Utils.prst.setInt(3, this.getTelefono());
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
     * Metodo estatico buscamos un concesionario segun id, devuelve true o false
     *
     * @param id
     * @return devuelve true si concesionario esta en la base de datos
     */
    public static boolean existConcesionarioBBDD(int id) {
        String consulta = "SELECT * FROM CONCESIONARIO WHERE ID=?";
        boolean encontrado = false;
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.rs = Utils.prst.executeQuery();
            if (Utils.rs.next()) {
                encontrado = true;
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
        return encontrado;
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
            Utils.prst.setInt(4, ID);
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

    /**
     * borramos un concesionario en BBDD
     *
     * @param id
     */
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

    /**
     * metodo para mostrar todos Concesionarios en la BBDD
     */
    public static void mostrarTodosConcesionariosBBDD() {
        String consulta = "SELECT * FROM CONCESIONARIO ORDER BY ID";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery(consulta);
            while (Utils.rs.next()) {
                System.out.println(
                        "ID:" + Utils.rs.getInt(1) + ", "
                        + "UBICACION: " + Utils.rs.getString(2) + ", "
                        + "NOMBRE: " + Utils.rs.getString(3) + ", "
                        + "TELEFONO: " + Utils.rs.getInt(4) + ", "
                        + "TALLER ID: " + Utils.rs.getInt(5) + ", "
                        + "VENTA ID: " + Utils.rs.getInt(6)
                );
            }
        } catch (SQLException e) {
            System.out.println("Error mostrar todos concesionarios");
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
    }

    /**
     * metodo para buscar un concesionario en BBDD y devuelve objeto
     * concesionario
     *
     * @param idConcesionario
     * @return
     */
    public static Concesionario buscarConcesionarioBBDD(int idConcesionario) {
        String consulta = "SELECT UBICACION, NOMBRE, TELEFONO FROM CONCESIONARIO WHERE ID=?";
        if (!existConcesionarioBBDD(idConcesionario)) {
            return null;
        } else {
            Concesionario c = new Concesionario();
            try {
                Utils.connection = Utils.conectarBBDD();
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setInt(1, idConcesionario);
                Utils.rs = Utils.prst.executeQuery();
                Utils.rs.next();
                c.setUbicacion(Utils.rs.getString(1));
                c.setNombre(Utils.rs.getString(2));
                c.setTelefono(Utils.rs.getInt(3));
                System.out.println("concesionario encontrado y creado " + c.toString());
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
            return c;
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
            Utils.connection = Utils.conectarBBDD();
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
                e.printStackTrace();
            }
        }

        return existe;
    }

    /**
     * relacionamos un concesionario con un taller
     *
     * @param idConcesionario
     * @param idTaller
     */
    public static void relacionarConcesionarioConTaller(int idConcesionario, int idTaller) {
        String consulta = "UPDATE CONCESIONARIO SET TALLERID=? WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, idTaller);
            Utils.prst.setInt(2, idConcesionario);
            Utils.prst.executeUpdate();
            System.out.println("Relacion concesionario " + idConcesionario + " con taller " + idTaller + " establecida correctamente");
        } catch (SQLException ex) {
            System.out.println("Error relacionar concesionario con taller");
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
                e.printStackTrace();
            }
        }
    }

    public static void relacionarConcesionarioConVenta(int idConcesionario, int idVenta) {
        String consulta = "UPDATE CONCESIONARIO SET VENTAID=? WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, idVenta);
            Utils.prst.setInt(2, idConcesionario);
            Utils.prst.executeUpdate();
            System.out.println("Relacion concesionario " + idConcesionario + " con venta " + idVenta + " establecida correctamente");
        } catch (SQLException ex) {
            System.out.println("Error relacionar concesionario  con venta");
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
                e.printStackTrace();
            }
        }
    }
}
