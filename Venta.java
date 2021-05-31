package patatafrita;
import java.sql.SQLException;

/**
 * VENTAS Esta clase contiene las ventas de cada uno de los concesionarios.
 *
 * @author Jose Luis Cardona
 * @version 1 - 29/03/2021 (Fecha de inicio)
 */
public class Venta {
    private int id=-1;
    private String horario;

    public Venta() {
    }

    public Venta(int id, String horario) {
        this.id = id;
        this.horario = horario;
    }

    public Venta(Venta copia) {
        this.id = copia.getId();
        this.horario = copia.getHorario();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) throws IllegalArgumentException {
        //ENTENDER BIEN ESTO
        String[] listaHorarios = horario.split("-");
        String[] lista2Horarios = listaHorarios[0].split(":");
        String[] lista3Horarios = listaHorarios[1].split(":");
        if (lista2Horarios.length + lista3Horarios.length != 4) {
            throw new IllegalArgumentException("Demasiados numeros.");
        } else {
            try {
                int[] numHorarios = new int[3];
                int pointer = 0;
                for (int i = 0; i < numHorarios.length / 2; i++) {
                    numHorarios[pointer] = Integer.parseInt(lista2Horarios[i]);
                    pointer++;
                    numHorarios[pointer] = Integer.parseInt(lista3Horarios[i]);
                    pointer++;
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException("Caracter/es inválido/s.");
            }
        }
    }

    @Override
    public String toString() {
        return "Venta{"
                + "id=" + id
                + ", horario='" + horario + '\''
                + '}';
    }

    /**
     * Metodo para crear ventas junto a sus datos.
     *
     * @return Objeto "venta" una vez creado con sus datos introducidos.
     */
    public static Venta crearVenta() {
        Venta venta = new Venta();
        try {
            System.out.println("Escribe el horario en formato hh:mm-hh:mm : ");
            venta.setHorario(Utils.kString());
        } catch (Exception ex) {
            System.out.println("¡ERROR! el objeto no se pudo crear.");
        }
        return venta;
    }

    /**
     * Metodo para guardar los datos de las ventas dentro de la base de datos.
     *
     * @param venta
     */
    public static void guardarDatosVenta(Venta venta) {
        String consulta = "INSERT INTO VENTA (HORARIO) VALUES (?)";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, venta.getHorario());
            Utils.prst.executeUpdate();
            System.out.println("Datos guardados con exito.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! no se pudieron guardar los datos de la venta en la BBDD.");
        } finally { 
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Metodo para buscar ventas mediante la id.
     *
     * @param id
     * @return Objeto "venta" con sus datos guardados.
     */
    public static Venta buscarVenta(int id) {
        String consulta = "SELECT HORARIO FROM VENTA WHERE ID=?";
        if (!existVenta(id)) {
            return null;
        } else {
            Venta venta = new Venta();
            try {
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setInt(1, id);
                Utils.rs = Utils.prst.executeQuery();
                Utils.rs.next();
                venta.setId(id);
                venta.setHorario(Utils.rs.getString(1));
                System.out.println("La venta ha sido encontrada y creada " + venta.toString());

            } catch (SQLException ex) {
                System.out.println("¡ERROR! No se ha encontrado la venta.");
            } finally {
                try {
                    Utils.cerrarVariables();
                } catch (Exception e) {
                    System.out.println("Error al cerrar variables");
                }
            }
            return venta;
        }
    }

    /**
     * Metodo para ver los datos de las ventas de la BBDD.
     */
    public static void mostrarVenta() {
        String consulta = "SELECT * FROM VENTA ORDER BY ID";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery(consulta);

            while (Utils.rs.next()) {
                System.out.println(
                        "ID: " + Utils.rs.getInt(1) + ", "
                        + "HORARIO : " + Utils.rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se han podido mostrar los datos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Metodo para borrar ventas de la BBDD.
     *
     * @param id
     */
    public static void borrarVenta(int id) {
        try {
            String consulta = "DELETE FROM VENTA WHERE ID=?";

            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("La venta " + id + " se ha borrado.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se han podido borrar los datos.");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Metodo para modificar los datos de las ventas de la BBDD.
     *
     * @param id
     * @param espacios
     * @param horario
     */
    public static void modificarVenta(int id, int espacios, String horario) {
        String consulta = "UPDATE VENTA SET HORARIO=?  WHERE ID=?";

        try {

            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, horario);
            Utils.prst.setInt(2, id);

            Utils.prst.executeUpdate();
            System.out.println("Los datos han sido modificados con exito.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se han podido modificar los datos.");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * 1º metodo exist que sirve para buscar ventas mediante la id.
     *
     * @param id
     * @return Booleano llamado "encontrado" el cual si sale true es que la
     * venta ha sido hallada, si sale false significa que no se ha localizado.
     */
    public static boolean existVenta(int id) {
        boolean encontrado = false;
        String consulta = "SELECT * FROM VENTA WHERE ID=?";
        try {

            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            if (Utils.rs != null) {
                encontrado = true;
            }

        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se ha encontrado la venta.");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return encontrado;
    }

    /**
     * 2º método exist que sirve para buscar ventas mediante la id.
     *
     * @return Booleano llamado "encontrado" el cual si sale true es que el
     * taller ha sido hallado, si sale false significa que no se ha localizado.
     */
    public boolean existVenta() {
        boolean encontrado = false;
        String consulta = "SELECT * FROM VENTA WHERE ID=?";
        try {

            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.id);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            if (Utils.rs != null) {
                encontrado = true;
            }

        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se ha encontrado la venta.");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return encontrado;
    }
    //No cerrar la conexion cuando se termine, la conexion se mantiene hasta que el usuario se va
}
