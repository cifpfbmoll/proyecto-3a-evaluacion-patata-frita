/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karina
 *
 */
public class Utils {

    public static Scanner lector = new Scanner(System.in); // Establecer el objeto scanner
    public static final double IMPUESTO = 0.21;
    private static Connection connection;
   

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

    public static void conectarBBDD() {
        String url = "jdbc:oracle:thin:@//localhost:1521/ORCLCDB.localdomain";
        String user = "dummy";
        String password = "dummy";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("No hay conexion a la BBDD");
        }  
    }

    /**
     * Un select general para todas las clases
     *
     * @param tabla la tabla que selecionamos
     * @param busqueda el where de SQL
     * @param selector datos de la tabla a devolver
     * @return
     */
    public static ResultSet selectGeneral(String selector, String tabla, String busqueda) {
        String consulta = "SELECT "+selector+" FROM "+tabla+" WHERE "+busqueda;
        PreparedStatement prs;
        ResultSet resultado = null;
        try {
            prs = connection.prepareStatement(consulta);
            
            /*
            prs.setString(1, selector);
            prs.setString(2, tabla);
            prs.setString(3, busqueda);
            */

            resultado = prs.executeQuery();
           
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return resultado;
    }
}
