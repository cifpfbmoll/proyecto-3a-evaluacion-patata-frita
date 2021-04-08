

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Clase para los metodos que no entran en otros clases
 *
 * @author Marat Rafael
 */
public class Utils {
     public static final double IMPUESTO=0.21;
     public static Scanner sc = new Scanner(System.in);

    /**
     * metodo estatico para sacar fecha y hora del sistema
     *
     * @return String fecha
     */
    public static String establecerFechaActual() {
        LocalDateTime dateTime = LocalDateTime.now(); // fecha y hora del sistema
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); //formato
        String fecha = dateTime.format(formatter); //variable guarda fecha y hora como string       
        return fecha;
    }
}
