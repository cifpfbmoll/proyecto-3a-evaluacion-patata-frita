package patatafrita;
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
        return "Taller{"
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
                taller.setId(id);
                taller.setEspacios(Utils.rs.getInt(1));
                taller.setHorario(Utils.rs.getString(2));
                taller.setId(id);
                System.out.println("El taller ha sido encontrado y creado " + taller.toString());

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
    //TODO : Entender y modificar
    /**
     * Devuelve los vehiculos filtrados de la base de datos en formato tabla
     *
     * @param bastidor
     * @param tipo
     * @param kilometraje
     * @param asientos
     * @param puertas
     * @param marca
     * @param modelo
     * @param precio
     * @return
     */
    public static Object[][] devolverTodosVehiculosBBDD(String bastidor, String tipo, int kilometraje, int asientos, int puertas,
                                                        String marca, String modelo, int precio) {
        boolean where = false;
        //SQL devuelve Reserva + Nombre cliente + apellidos cliente + tablas relacionadas
        String consulta = "SELECT vehiculo.*\n" +
                "FROM test.vehiculo INNER JOIN motor m on vehiculo.motorid = m.id\n" +
                "LEFT JOIN venta v on vehiculo.ventaid = v.id\n" +
                "LEFT JOIN cliente c on vehiculo.clientenif = c.nif"; //Cambiar test a concesionario
        if (bastidor != null && !where) {
            consulta += " WHERE bastidor like \"" + bastidor + "\"";
            where = true;
        }else if(bastidor != null){
            consulta += " AND bastidor like \"" + bastidor + "\"";
        }
        if (tipo != null && !where) {
            consulta += " WHERE tipo like \"" + tipo + "\"";
            where = true;
        }else if(tipo != null){
            consulta += " AND tipo like \"" + tipo + "\"";
        }
        if ( kilometraje > 0 && !where) {
            consulta += " WHERE kilometraje = \"" + kilometraje + "\"";
            where = true;
        }else if(kilometraje > 0){
            consulta += " AND kilometraje = \"" + kilometraje + "\"";
        }
        if (asientos > 0 && !where){
            consulta += " WHERE asientos = \"" + asientos + "\"";
            where = true;
        }else if ( asientos > 0) {
            consulta += " AND asientos = \"" + asientos + "\"";
        }
        if ( puertas > 0 && !where) {
            consulta += " WHERE puertas = \"" + puertas + "\"";
            where = true;
        }else if(puertas > 0){
            consulta += " AND puertas = \"" + puertas + "\"";
        }
        if (precio > 0 && !where){
            consulta += " WHERE precio = \"" + precio + "\"";
            where = true;
        }else if ( precio > 0) {
            consulta += " AND precio = \"" + precio + "\"";
        }
        if (marca != null && !where) {
            consulta += " WHERE marca like \"" + marca + "\"";
            where = true;
        }else if(marca != null){
            consulta += " AND marca like \"" + marca + "\"";
        }
        if (modelo != null && !where) {
            consulta += " WHERE modelo like \"" + modelo + "\"";
            where = true;
        }else if(modelo != null){
            consulta += " AND modelo like \"" + modelo + "\"";
        }
        consulta += " ORDER BY v.bastidor";
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery("SELECT COUNT(*) FROM Vehiculo"); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs.next();
            objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                //Columnas tiene que ser el numero de columnas que devuelva vuestro sql adaptado
                //Contar únicamente que columnas son importantes!
                Integer COLUMNAS = 19;
                /**
                 * Bastidor, tipo, clase, kilometraje, autonomia, puertas
                 * asientos, color, marca, modelo, precio, extras, potencia
                 * par, tipo, venta id, nombre, apellidos
                 */
                //ReservaID, Espacio_res, fecha, clientenif, espacios, horario, nombre,apellidos
                String[] list = new String[COLUMNAS];
                list[0] = Utils.rs.getString(1); //Bastidor
                list[1] = Utils.rs.getString(2); //Vehiculo tipo
                list[2] = Utils.rs.getString(3); //Estado vehiculo
                list[3] = Utils.rs.getString(5); //Kilometraje
                list[4] = Utils.rs.getString(7); //Autonomia
                list[5] = Utils.rs.getString(8); //Puertas
                list[6] = Utils.rs.getString(9); //Asientos
                list[7] = Utils.rs.getString(10); //Color
                list[8] = Utils.rs.getString(11); //Marca
                list[9] = Utils.rs.getString(12); //Modelo
                list[10] = Utils.rs.getString(13); //Precio
                list[11] = Utils.rs.getString(14); //Extras
                list[12] = Utils.rs.getString(18); //Potencia
                list[13] = Utils.rs.getString(19); //Cilindrada
                list[14] = Utils.rs.getString(20); //Potencia
                list[15] = Utils.rs.getString(21); //Par
                list[16] = Utils.rs.getString(22); //Tipo
                list[17] = Utils.rs.getString(23); //Id venta
                list[18] = Utils.rs.getString(24); //Horario venta
                list[19] = Utils.rs.getString(25) + Utils.rs.getString(26); //Nombre + apellidos
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
