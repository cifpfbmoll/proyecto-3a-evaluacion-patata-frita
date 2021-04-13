/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    public static final double IMPUESTO = 0.21;

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
}
