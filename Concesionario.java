package eu.fp.concesionario;

import java.sql.SQLException;
/**
 * CONCESIONARIOS
 * Esta clase  guarda la informacion sobre los diferentes concesionarios del proyecto.
 * @author Jose Luis Cardona
 * @version 1 - 29/03/2021
 */
import java.sql.*;

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
                + "id=" + id
                + ", ubicacion='" + ubicacion + '\''
                + ", nombre='" + nombre + '\''
                + ", telefono=" + telefono
                + '}';
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
     * Metodo para crear concesionarios junto a sus datos.
     * @return Objeto "concesionario" una vez creado con sus datos introducidos.
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
     * Metodo para guardar los datos de los concesionarios dentro de la base de datos.
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
            System.out.println("Datos guardados con exito.");
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
            Utils.connection = Utils.conectarBBDD();
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
                } catch (SQLException ex) {
                    System.out.println("¡ERROR! no se ha podido cerrar la conexion.");
                }

            }
            return concesionario;
        }
    }

    /**
     * Metodo para ver los datos de los concesionarios de la BBDD.
     */
    public static void mostrarConcesionario() {
        String consulta = "SELECT * FROM CONCESIONARIO ORDER BY ID";
        try {
            Utils.connection = Utils.conectarBBDD();
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
                System.out.println("¡ERROR! no se ha podido cerrar la conexion.");
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
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("El concesionario " + id + " se ha borrado.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se han podido borrar los datos.");
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("¡ERROR! no se ha podido cerrar la conexion.");
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
            Utils.connection = Utils.conectarBBDD();
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
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("¡ERROR! no se ha podido cerrar la conexion.");
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
            Utils.connection = Utils.conectarBBDD();
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
                System.out.println("¡ERROR! no se ha podido cerrar la conexion.");
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
            Utils.connection = Utils.conectarBBDD();
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
                System.out.println("¡ERROR! no se ha podido cerrar la conexion.");
            }
        }
        return encontrado;
    }
    //No cierres la conexion cuando termines, la conexion se mantiene hasta que el usuario se va
}