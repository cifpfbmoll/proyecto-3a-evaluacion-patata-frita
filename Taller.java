package patatafrita;

import java.sql.SQLException;

/**
 * TALLERES
 * Esta clase almacena la información de los diversos talleres de cada concesionario
 * @author José Luis Cardona
 * @version 1 - 29/03/2021
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
     * Método para crear talleres junto a sus datos.
     * @return Objeto taller.
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
     * Método para guardar los datos de los talleres dentro de la base de datos.
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
            System.out.println("Datos guardados con éxito.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! no se pudieron guardar los datos del taller en la BBDD.");
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
     * Método para buscar un taller dentro de la BBDD.
     * @param id
     * @return posicion.
     */
    public static int buscarTaller(int id) {
        String consulta = "SELECT * FROM TALLER WHERE ID=?";
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
            System.out.println("¡ERROR! Busqueda fallida.");
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

        return posicion;
    }
    //TODO: Te faltan funciones, buscar, modificar, borrar, mostrar y los dos exists
    //No cierres la conexion cuando termines, la conexion se mantiene hasta que el usuario se va
}

