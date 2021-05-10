package eu.fp.concesionario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Karina
 *
 */
public class Utils {

    public static Scanner lector = new Scanner(System.in); // Establecer el objeto scanner
    public static final float IMPUESTO = (float) 0.21;
    public static Connection connection;
    public static ResultSet rs;
    public static PreparedStatement prst;
    public static Statement st;

    /**
     * metodo estatico para sacar fecha y hora del sistema
     *
     * @return String fecha
     */
    public static String establecerFechaActual() {
        LocalDateTime dateTime = LocalDateTime.now(); // fecha y hora del sistema
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); // formato
        String fecha = dateTime.format(formatter); // variable guarda fecha y hora como string
        return fecha;
    }

    /**
     *
     * @param texto Texto para imprimir y solicitar la informacion a escanear
     * @return Devuelve el valor que se ha solicitado escanear
     */
    public static String kString(String texto) {
        System.out.println(">>> " + texto + ": ");
        String valor = lector.nextLine();
        return valor;
    }

    /**
     * Espera input del usuario
     *
     * @return String del usuario escrito
     */
    public static String kString() {
        String valor = lector.nextLine();
        return valor;
    }

    /**
     *
     * @param texto Texto para imprimir y solicitar la informacion a escanear
     * @return Devuelve el valor que se ha solicitado escanear
     */
    public static Integer kInteger(String texto) {
        System.out.println(">>> " + texto + ": ");
        Integer valor = lector.nextInt();
        lector.nextLine(); // Limpiar buffer dentro del input
        return valor;
    }

    public static Integer kInteger() {
        Integer valor = lector.nextInt();
        lector.nextLine(); // Limpiar buffer dentro del input
        return valor;
    }

    /**
     *
     * @param texto Texto para imprimir y solicitar la informacion a escanear
     * @return Devuelve el valor que se ha solicitado escanear
     */
    public static int kInt(String texto) {
        System.out.println(">>> " + texto + ": ");
        int valor = lector.nextInt();
        lector.nextLine(); // Limpiar buffer dentro del input
        return valor;
    }

    public static int kInt() {
        int valor = lector.nextInt();
        lector.nextLine(); // Limpiar buffer dentro del input
        return valor;
    }

    /**
     *
     * @param texto Texto para imprimir y solicitar la informacion a escanear
     * @return Devuelve el valor que se ha solicitado escanear
     */
    public static double kDouble(String texto) {
        System.out.println(">>> " + texto + ": ");
        double valor = lector.nextDouble();
        lector.nextLine(); // Limpiar buffer dentro del input
        return valor;
    }

    public static double kDouble() {
        double valor = lector.nextDouble();
        lector.nextLine(); // Limpiar buffer dentro del input
        return valor;
    }

    /**
     *
     * @param texto Texto para imprimir y solicitar la informacion a escanear
     * @return Devuelve el valor que se ha solicitado escanear
     */
    public static Float kFloat(String texto) {
        System.out.println(">>> " + texto + ": ");
        Float valor = lector.nextFloat();
        lector.nextLine(); // Limpiar buffer dentro del input
        return valor;
    }

    public static Float kFloat() {
        Float valor = lector.nextFloat();
        lector.nextLine(); // Limpiar buffer dentro del input
        return valor;
    }

    /**
     * motodo de conectar a la BBDD y devolver un objeto de conexion
     *
     * @return
     */
    public static Connection conectarBBDD() {
        String url = "jdbc:mysql://51.178.152.221:3306/test";
        String user = "dam"; //Cambiar a un archivo externo y cargar desde ahi?
        String password = "ContraseñaDeLaOstia69";
//        String url = "jdbc:oracle:thin:@//localhost:1521/ORCLCDB.localdomain";
//        String user = "dummy";
//        String password = "dummy";

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("No hay conexion a la BBDD");
            ex.printStackTrace();
        }
        return connection;
    }

    /**
     * Metodo para conectar a la base de datos, no devuelve nada
     */
    public static void conectarBBDD2() {
        String url = "jdbc:mysql://51.178.152.221:3306/concesionario";
        String user = "dam"; //Cambiar a un archivo externo y cargar desde ahi?
        String password = "ContraseñaDeLaOstia69";
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("No hay conexion a la BBDD");
            ex.printStackTrace();
        }
    }

    /**
     * Un select general para todas las clases. Acrodarse de cerrar el ResultSet
     * después de leerlo.
     *
     * @param tabla la tabla que selecionamos
     * @param busqueda el where de SQL
     * @param selector datos de la tabla a devolver
     * @return
     */
    public static void selectGeneral(String selector, String tabla, String busqueda) {
        String consulta = "select " + selector + " from " + tabla + " WHERE " + busqueda;
        PreparedStatement prs = null;
        try {
            //Volver a probar con prepared statement
            prs = connection.prepareStatement(consulta);
            prs.setString(1, busqueda);
            rs = prs.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prs != null) {
                    prs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error cerrar conexion");
            }
        }
    }

    public static ResultSet getResults(){
        return rs;
    }

    /**
     * Cerrar la conexión a la base de datos
     * @throws SQLException 
     */
    public static void cerrarGeneral() throws SQLException {
        try {
            if (rs != null) rs.close();
            if (prst != null) prst.close();
            if (st != null) st.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Cerrar las variables a la base de datos manteniendo la conexión
     * @throws SQLException 
     */
    public static void cerrarVariables() throws SQLException {
        try {
            if (rs != null) rs.close();
            if (prst != null) prst.close();
            if (st != null) st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteGeneral(String tabla, int id) {
        PreparedStatement prst;
        try {
            String consulta = "DELETE FROM " + tabla + " WHERE ID=?";
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("Se ha borrado correctamente");
        } catch (SQLException ex) {
            System.out.println("¡ERROR!, no se ha podido borrar");
        }
    }
    /**
     * metodo para adaptar String a la fecha aceptable por MySQL
     * hay que crear objeto 'java.sql.Date sqlDate'   para  recibirlo
     * 
     * @param fecha
     * @return  objeto java.sql.Date sqlDate
     */
    public static Date adaptarFechaMYSQL(String fecha) {
        // adaptamos fecha a la fecha de mysql
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date myDate = null;
        try {
            myDate = formatter.parse(fecha);
        } catch (ParseException ex) {
            System.out.println("Error aplicar formato fecha");
        }
        // casting a mysql formato
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        return sqlDate;
    }
}
