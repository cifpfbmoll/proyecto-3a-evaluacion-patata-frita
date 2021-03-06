import java.sql.SQLException;

/**
 * TALLERES Esta clase almacena la informacion de los diversos talleres de cada
 * concesionario
 *
 * @author Jose Luis Cardona
 * @version 1 - 29/03/2021 (Fecha de inicio)
 */
public class Taller {


    private int id = -1;
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

    public void setId(int id) {
        this.id = id;
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
                this.horario=horario;
                
            } catch (Exception ex) {
                throw new IllegalArgumentException("Caracter/es inválido/s.");
            }
        }
    }

    @Override
    public String toString() {
        return "Taller { "
                + "id=" + id
                + ", espacios=" + espacios
                + ", horario='" + horario + '\''
                + '}';
    }

    /**
     * Metodo para crear talleres junto a sus datos.
     *
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
     *
     * @param taller
     */
    public static void guardarDatosTaller(Taller taller) {
        String consulta = "INSERT INTO TALLER (ESPACIOS, HORARIO) VALUES (?,?)";
        try {

            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, taller.getEspacios());
            Utils.prst.setString(2, taller.getHorario());
            Utils.prst.executeUpdate();
            System.out.println("Datos guardados con exito.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! no se pudieron guardar los datos del taller en la BBDD.");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Metodo para buscar talleres mediante la id.
     *
     * @param id
     * @return Objeto "taller" con sus datos guardados.
     */
    public static Taller buscarTaller(int id) {
        String consulta = "SELECT ESPACIOS, HORARIO FROM TALLER WHERE ID=?";
        if (!existTaller(id)) {
            return null;
        } else {
            Taller taller = new Taller();
            try {
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setInt(1, id);
                Utils.rs = Utils.prst.executeQuery();
                Utils.rs.next();

                taller.setEspacios(Utils.rs.getInt(1));
                taller.setHorario(Utils.rs.getString(2));
            } catch (SQLException ex) {
                System.out.println("¡ERROR! No se ha encontrado el taller.");
            } finally {
                try {
                    Utils.cerrarVariables();
                } catch (Exception e) {
                    System.out.println("Error al cerrar variables");
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

            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery(consulta);

            while (Utils.rs.next()) {
                System.out.println(
                        "ID: " + Utils.rs.getInt(1) + ", "
                        + "ESPACIOS : " + Utils.rs.getInt(2) + ", "
                        + "HORARIO : " + Utils.rs.getString(3));
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
     * Metodo para cargar los datos de los talleres de la BBDD.
     */
    public static Taller[] cargarTalleres() {
        String consulta = "SELECT * FROM TALLER ORDER BY ID";
        Taller[] tallerList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery("SELECT COUNT(*) FROM taller");
            Utils.rs.next();
            tallerList = new Taller[Utils.rs.getInt(1)];
            Utils.rs = Utils.prst.executeQuery(consulta);
            for(int i=0;Utils.rs.next();i++){
                tallerList[i] = new Taller(Utils.rs.getInt(1),Utils.rs.getInt(2),Utils.rs.getString(3));
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
        return tallerList;
    }

    /**
     * Metodo para borrar talleres de la BBDD.
     *
     * @param id
     */
    public static void borrarTaller(int id) {
        try {
            String consulta = "DELETE FROM TALLER WHERE ID=?";

            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("El taller " + id + " se ha borrado.");
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
     * Metodo para modificar los datos de los talleres de la BBDD.
     *
     * @param id
     * @param espacios
     * @param horario
     */
    public static void modificarTaller(int id, int espacios, String horario) {
        String consulta = "UPDATE TALLER SET ESPACIOS=?, HORARIO=?  WHERE ID=?";

        try {

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
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * 1º metodo exist que sirve para buscar talleres mediante la id.
     *
     * @param id
     * @return Booleano llamado "encontrado" el cual si sale true es que el
     * taller ha sido hallado, si sale false significa que no se ha localizado.
     */
    public static boolean existTaller(int id) {
        boolean encontrado = false;
        String consulta = "SELECT * FROM TALLER WHERE ID=?";
        try {

            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.rs = Utils.prst.executeQuery();
            if (Utils.rs.next()) {
                encontrado = true;
            }

        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se ha encontrado el taller.");
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
     * 2º método exist que sirve para buscar talleres mediante la id.
     *
     * @return Booleano llamado "encontrado" el cual si sale true es que el
     * taller ha sido hallado, si sale false significa que no se ha localizado.
     */
    public boolean existTaller() {
        boolean encontrado = false;
        String consulta = "SELECT * FROM TALLER WHERE ID=?";
        try {

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
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return encontrado;
    }

    /**
     *  Devuelve todos los datos de talleres en la base de datos en un archivo txt.
     */
    public static void escribirTalleresArchivo(){
        Utils.abrirArchivo("Taller.txt");
        String consulta = "SELECT * FROM TALLER";
        try{
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while(Utils.rs.next()){
                Utils.escribirLineaArchivo("Taller id: " + Integer.toString(Utils.rs.getInt(1)) + " {");
                Utils.escribirLineaArchivo("    Espacios: " + Integer.toString(Utils.rs.getInt(2)));
                Utils.escribirLineaArchivo("    Horario: " + Utils.rs.getInt(3) + " }");
                //Dejamos espacio para poder diferenciar facilmente entre talleres
                Utils.escribirLineaArchivo(" ");
            }
            Utils.cerrarArchivo();
            System.out.println("Datos escritos correctamente en el fichero.");
        }catch(Exception e){
            System.out.println("Problema al leer datos de la base de datos.");
        } finally{
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar las variables.");
            }
        }
    }
    /**
     * Devuelve los talleres filtrados de la base de datos en formato tabla.
     *
     * @param tallerId
     * @param espacios
     * @param horario
     * @return
     */
    public static Object[][] devolverTodosTalleresBBDD(int tallerId,int espacios,String horario) {
        boolean where = false;
        //SQL devuelve ID, Espacios y Horario Taller
        String consulta = "SELECT taller.*" +
                "FROM test.taller INNER JOIN concesionario c on taller.concesionarioid = c.id" +
                "LEFT JOIN empleado e on taller.empleadoid = e.id" +
                "LEFT JOIN reserva r on taller.reservaid = r.id"; //Cambiar test a concesionario
        if (tallerId > 0 && !where) {
            consulta += " WHERE id like \"" + tallerId + "\"";
            where = true;
        }else if(tallerId > 0){
            consulta += " AND id like \"" + tallerId + "\"";
        }
        if (espacios > 0 && !where) {
            consulta += " WHERE espacios like \"" + espacios + "\"";
            where = true;
        }else if(espacios > 0){
            consulta += " AND espacios like \"" + espacios + "\"";
        }
        if ( horario != null && !where) {
            consulta += " WHERE horario = \"" + horario + "\"";
            where = true;
        }else if(horario != null){
            consulta += " AND horario = \"" + horario + "\"";
        }
        consulta += " ORDER BY id";
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.last();
            objectList = new String[Utils.rs.getRow()][];
            int i = 0;
            Utils.rs.first();
            while (Utils.rs.next()) {
                //Columnas tiene que ser el numero de columnas que devuelva vuestro sql adaptado
                //Contar únicamente que columnas son importantes!
                Integer COLUMNAS = 8;
                /**
                 * ID Taller, espacios Taller, horario Taller, ID Concesionario, nombre Concesionario,
                 * ubicacion Concesionario, telefono Concesionario, nombre Empleado(Persona), apellidos Empleado(Persona),
                 * espacio-reservado Reserva, fecha Reserva.
                 */
                String[] list = new String[COLUMNAS];
                list[0] = Utils.rs.getString(1); //ID Taller
                list[1] = Utils.rs.getString(2); //Espacios Taller
                list[2] = Utils.rs.getString(3); //Horario Taller
                list[3] = Utils.rs.getString(5); //Ubicacion Concesionario
                list[4] = Utils.rs.getString(6); //Nombre Concesionario
                list[5] = Utils.rs.getString(7); //Telefono Concesionario
                list[6] = Utils.rs.getString(11) + Utils.rs.getString(12); //Nombre + apellidos Empleado(Persona)
                list[7] = Utils.rs.getString(20); //Espacio Reservado Reserva
                list[8] = Utils.rs.getString(21); //Fecha Reserva
                objectList[i] = list;
                i++;
            }
            return objectList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error mostrando todos los clientes");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return objectList;
    }
}
