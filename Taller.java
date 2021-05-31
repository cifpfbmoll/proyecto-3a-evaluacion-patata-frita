package patatafrita;
import java.sql.SQLException;

/**
 * TALLERES
 * Esta clase almacena la informacion de los diversos talleres de cada concesionario
 * @author Jose Luis Cardona
 * @version 1 - 29/03/2021 (Fecha de inicio)
 */
public class Taller {
    private int id;
    private int espacios;
    private String horario;

    public Taller() {
    }

    public Taller(int id, int espacios, String horario) {
        this.id = id;
        this.espacios = espacios;
        this.horario = horario;
    }

    public Taller(Taller copia) {
        this.id = copia.getId();
        this.espacios = copia.getEspacios();
        this.horario = copia.getHorario();
    }

    public int getId() {
        return id;
    }

    public int getEspacios() {
        return espacios;
    }

    public void setEspacios(int espacios) throws IllegalArgumentException {
        if (espacios <= 0) {
            throw new IllegalArgumentException("Número de espacio inválido.");
        } else {
            this.espacios = espacios;
        }
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
        return "Taller{" +
                "id=" + id +
                ", espacios=" + espacios +
                ", horario='" + horario + '\'' +
                '}';
    }

    /**
     * Metodo para crear talleres junto a sus datos.
     * @return Objeto "taller" una vez creado con sus datos introducidos.
     */
    public static Taller crearTaller() {
        Taller taller = new Taller();
        try {
            System.out.println("Escribe los espacios : ");
            taller.setEspacios(Utils.kInt());
            System.out.println("Escribe el horario en formato hh:mm-hh:mm : ");
            taller.setHorario(Utils.kString());
        } catch (Exception ex) {
            System.out.println("¡ERROR! el objeto no se pudo crear.");
        }
        return taller;
    }

    /**
     * Metodo para guardar los datos de los talleres dentro de la base de datos.
     * @param taller
     */
    public static void guardarDatosTaller(Taller taller) {
        String consulta = "INSERT INTO TALLER (ESPACIOS, HORARIO) VALUES (?,?)";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, taller.getEspacios());
            Utils.prst.setString(2, taller.getHorario());
            Utils.prst.executeUpdate();
            System.out.println("Datos guardados con exito.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! no se pudieron guardar los datos del taller en la BBDD.");
        } finally {
            if (Utils.prst != null) {
                try {
                    Utils.prst.close();//cierra el objeto prepareStatement llamado prst
                } catch (SQLException throwables) {
                    System.out.println("¡ERROR! no se ha podido cerrar la conexion.");
                }
            }
        }
    }

    /**
     * Metodo para buscar talleres mediante la id.
     * @param id
     * @return Objeto "taller" con sus datos guardados.
     */
    public static Taller buscarTaller(int id) {
        String consulta = "SELECT ESPACIOS, HORARIO FROM TALLER WHERE ID=?";
        if (!existTaller(id)) {
            return null;
        } else {
            Taller taller = new Taller();
            Utils.connection = Utils.conectarBBDD();
            try {
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setInt(1, id);
                Utils.rs = Utils.prst.executeQuery();
                Utils.rs.next();
                taller.setEspacios(Utils.rs.getInt(1));
                taller.setHorario(Utils.rs.getString(2));
                System.out.println("El taller ha sido encontrado y creado " + taller.toString());

            } catch (SQLException ex) {
                System.out.println("¡ERROR! No se ha encontrado el taller.");
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
            return taller;
        }
    }

    /**
     * Metodo para ver los datos de los talleres de la BBDD.
     */
    public static void mostrarTaller() {
        String consulta = "SELECT * FROM TALLER ORDER BY ID";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery(consulta);

            while (Utils.rs.next()) {
                System.out.println(
                        "ID: "+Utils.rs.getInt(1) + ", " +
                                "ESPACIOS : "+Utils.rs.getInt(2) + ", " +
                                "HORARIO : "+Utils.rs.getString(3));
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
     * Metodo para borrar talleres de la BBDD.
     * @param id
     */
    public static void borrarTaller(int id) {
        try {
            String consulta = "DELETE FROM TALLER WHERE ID=?";
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("El taller " + id + " se ha borrado.");
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
     * Metodo para modificar los datos de los talleres de la BBDD.
     * @param id
     * @param espacios
     * @param horario
     */
    public static void modificarTaller(int id, int espacios, String horario) {
        String consulta = "UPDATE TALLER SET ESPACIOS=?, HORARIO=?  WHERE ID=?";

        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, espacios);
            Utils.prst.setString(2, horario);
            Utils.prst.setInt(3, id);

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
     * 1º metodo exist que sirve para buscar talleres mediante la id.
     * @param id
     * @return Booleano llamado "encontrado" el cual si sale true es que el taller ha sido
     * hallado, si sale false significa que no se ha localizado.
     */
    public static boolean existTaller(int id) {
        boolean encontrado = false;
        String consulta = "SELECT * FROM TALLER WHERE ID=?";
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
            System.out.println("¡ERROR! No se ha encontrado el taller.");
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
     * 2º método exist que sirve para buscar talleres mediante la id.
     * @return Booleano llamado "encontrado" el cual si sale true es que el taller ha sido
     * hallado, si sale false significa que no se ha localizado.
     */
    public boolean existTaller() {
        boolean encontrado = false;
        String consulta = "SELECT * FROM TALLER WHERE ID=?";
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
            System.out.println("¡ERROR! No se ha encontrado el taller.");
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
    //No cerrar la conexion cuando se termine, la conexion se mantiene hasta que el usuario se va
}

