package patatafrita;
import java.sql.SQLException;

/**
 * CONCESIONARIOS
 * Esta clase  guarda la informacion sobre los diferentes concesionarios del proyecto.
 * @author Jose Luis Cardona
 * @version 1 - 29/03/2021 (Fecha de inicio)
 */

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
     * Metodo para crear el objeto concesionario.
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
            concesionario.setTaller(Taller.buscarTaller(tallerId));
        } catch (Exception e) {
            System.out.println("¡ERROR! No se ha creado el concesionario correctamente.");
        }
        return concesionario;
    }
    /**
     * Metodo para insertar los datos del concesionario a la BBDD a partir de un objeto.
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
     * Método para insertar los datos del concesionario actual a la base de datos.
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
    public static void mostrarConcesionario() {
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
     * Devolver todos los concesionarios de la base de datos, útil para
     * la gui
     * @return Concesionario[][]
     */
    public static Object[][] devolverTodosConcesionarioBBDD() {
        String consulta = "SELECT * FROM Concesionario ORDER BY id";
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement("SELECT count(*) FROM Concesionario"); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.rs = Utils.st.executeQuery(consulta);
            while (Utils.rs.next()) {
                String[] list = new String[6]; // MODIFICAR LONGITUD DE LA LISTA EN OTRAS CLASES
                list[0] = Integer.toString(Utils.rs.getInt(1));
                list[1] = (Utils.rs.getString(2));
                list[2] = (Utils.rs.getString(3));
                list[3] = Integer.toString(Utils.rs.getInt(4));
                list[4] = Integer.toString(Utils.rs.getInt(5));
                list[5] = Integer.toString(Utils.rs.getInt(6));
                objectList[i] = list;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error mostrando todos los clientes");
        } finally {
            try{
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return objectList;
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
    //Los dos metodos siguientes son redundantes, usados para tests, pueden ser borrados en un futuro
    /**
     * Metodo para relacionar en la base de datos un concesionario con su taller.
     * @param id_concesionario
     * @param id_taller
     */
    public static void relacionarConcesionarioConTaller(int id_concesionario, int id_taller){
        String consulta = "UPDATE concesionario SET tallerid = ? WHERE id=?";
        try {
            //La conexión se irà cuando el main este completo
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id_taller);
            Utils.prst.setInt(2, id_concesionario);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se ha encontrado el concesionario.");
        } finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Metodo para relacionar en la base de datos un concesionario con su venta.
     * @param id_concesionario
     * @param id_venta
     */
    public static void relacionarConcesionarioConVenta(int id_concesionario, int id_venta){
        String consulta = "UPDATE concesionario SET ventaid = ? WHERE id=?";
        try {
            //La conexión se irà cuando el main este completo
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id_venta);
            Utils.prst.setInt(2, id_concesionario);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
        } catch (SQLException ex) {
            System.out.println("¡ERROR! No se ha encontrado el concesionario.");
        }  finally {
            try{
                Utils.cerrarVariables();
            }catch (Exception e){
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     *  Devuelve todos los datos de concesionarios en la base de datos en un archivo txt.
     */
    public static void escribirConcesionariosArchivo(){
        Utils.abrirArchivo("Concesionario.txt");
        String consulta = "SELECT * FROM CONCESIONARIO";
        try{
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while(Utils.rs.next()){
                Utils.escribirLineaArchivo("Concesionario id: " + Integer.toString(Utils.rs.getInt(1)) + " {");
                Utils.escribirLineaArchivo("    Ubicacion: " + Utils.rs.getString(2));
                Utils.escribirLineaArchivo("    Nombre: " + Utils.rs.getString(3));
                Utils.escribirLineaArchivo("    Telefono: " + Integer.toString(Utils.rs.getInt(4)));
                Utils.escribirLineaArchivo("    Taller id: " + Integer.toString(Utils.rs.getInt(5)));
                Utils.escribirLineaArchivo("    Venta id: " + Integer.toString(Utils.rs.getInt(6))+ " }");
                //Dejamos espacio para poder diferenciar facilmente entre concesionarios
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
     * Devuelve los concesionarios filtrados de la base de datos en formato tabla.
     *
     * @param concesionarioId
     * @param ubicacion
     * @param nombre
     * @param telefono
     * @param tallerId
     * @param ventaId
     * @return
     */
    public static Object[][] devolverTodosConcesionariosBBDD(int concesionarioId,String ubicacion,String nombre,int telefono,int tallerId,int ventaId) {
        boolean where = false;
        //SQL devuelve ID, Ubicacion, Nombre y Telefono Concesionario + ID Taller + ID Venta
        String consulta = "SELECT concesionario.*\n"; //Cambiar test a concesionario
        if (concesionarioId > 0 && !where) {
            consulta += " WHERE id like \"" + concesionarioId + "\"";
            where = true;
        }else if(concesionarioId > 0){
            consulta += " AND id like \"" + concesionarioId + "\"";
        }
        if (ubicacion != null && !where) {
            consulta += " WHERE ubicacion like \"" + ubicacion + "\"";
            where = true;
        }else if(ubicacion != null){
            consulta += " AND ubicacion like \"" + ubicacion + "\"";
        }
        if ( nombre != null && !where) {
            consulta += " WHERE nombre = \"" + nombre + "\"";
            where = true;
        }else if(nombre != null){
            consulta += " AND nombre = \"" + nombre + "\"";
        }
        if (telefono > 0 && !where){
            consulta += " WHERE telefono = \"" + telefono + "\"";
            where = true;
        }else if ( telefono > 0) {
            consulta += " AND telefono = \"" + telefono + "\"";
        }
        if (tallerId > 0 && !where) {
            consulta += " WHERE tallerid like \"" + tallerId + "\"";
            where = true;
        }else if(tallerId > 0){
            consulta += " AND tallerid like \"" + tallerId + "\"";
        }
        if (ventaId > 0 && !where) {
            consulta += " WHERE ventaid like \"" + ventaId + "\"";
            where = true;
        }else if(ventaId > 0){
            consulta += " AND ventaid like \"" + ventaId + "\"";
        }
        consulta += " ORDER BY id";
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery("SELECT COUNT(*) FROM Concesionario"); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs.next();
            objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                //Columnas tiene que ser el numero de columnas que devuelva vuestro sql adaptado
                //Contar únicamente que columnas son importantes!
                Integer COLUMNAS = 5;
                /**
                 * ID Concesionario, ubicacion Concesionario, nombre Concesionario, telefono Concesionario,
                 * ID Taller y ID Venta.
                 */
                String[] list = new String[COLUMNAS];
                list[0] = Utils.rs.getString(1); //ID Concesionario
                list[1] = Utils.rs.getString(2); //Ubicacion Concesionario
                list[2] = Utils.rs.getString(3); //Nombre Concesionario
                list[3] = Utils.rs.getString(4); //Telefono Concesionario
                list[4] = Utils.rs.getString(5); //ID Taller
                list[5] = Utils.rs.getString(6); //ID Venta
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