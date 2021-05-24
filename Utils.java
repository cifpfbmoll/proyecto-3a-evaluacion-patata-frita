package eu.fp.concesionario;

/*
 * Esta clase es la misma que usamos en el proyecto, pero al incluir todos los datos
 * necesarios para archivos, base de datos y lectura de teclado me parece correcto
 * reutilizarla para el examen.
 * De todas formas la he editado ligeramente.
 */
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class Utils {

    public static Scanner lector = new Scanner(System.in); // Establecer el objeto scanner
    public static final float IMPUESTO = (float) 0.21;
    public static Connection connection;
    public static ResultSet rs;
    public static PreparedStatement prst;
    public static Statement st;
    public static File archivo = null;
    public static BufferedReader lectorArchivo = null;
    public static BufferedWriter escritorArchivo = null;

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
     * Metodo para conectar a la base de datos, no devuelve nada
     */
    public static void conectarBBDD() {
        String url = "jdbc:mysql://localhost:3306/examen";
        String user = "examen"; //Cambiar a un archivo externo y cargar desde ahi?
        String password = "ContraseñaDeLaOstia69";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("No hay conexion a la BBDD");
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Un select general para todas las clases. Acrodarse de cerrar el ResultSet
     * después de leerlo.
     *
     * @param tabla    la tabla que selecionamos
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
                cerrarVariables();
            } catch (SQLException e) {
                System.out.println("Error cerrar conexion");
            }
        }
    }

    public static ResultSet getResults() {
        return rs;
    }

    /**
     * Cerrar la conexión a la base de datos
     *
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
     *
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
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.prst.executeUpdate();
            System.out.println("Se ha borrado correctamente");
        } catch (SQLException ex) {
            System.out.println("¡ERROR!, no se ha podido borrar");
        } finally {
            try {
                cerrarVariables();
            } catch (SQLException e) {
                System.out.println("Error cerrar conexion");
            }
        }
    }

    /**
     * metodo para adaptar String a la fecha aceptable por MySQL
     * hay que crear objeto 'java.sql.Date sqlDate'   para  recibirlo
     *
     * @param fecha
     * @return objeto java.sql.Date sqlDate
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

    /*
    -BufferedInputStream y BufferedOutputStream por byte streams
    -BufferedReader y BufferedWriter para streams de carácter.
    Para crear un buffered stream, a su constructor le debemos pasar un flujo sin buffer:
    -InputStream = new BufferedReader (new FileReader ( "entrada.txt"));
    -OutputStream = new BufferedWriter (new FileWriter ( "salida.txt"));
    */

    public static void abrirArchivo(String arc) {
        try {
            if (archivo == null) {
                //crea el archivo en caso de que no exista
                archivo = new File(arc);
                archivo.createNewFile();
                //else con throw exception
            } else {
                throw new IOException("Ya esta abierto otro fichero");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cerrarArchivo() throws IOException {
        if (lectorArchivo != null) {
            //si el buffer de lectura ya no es nulo se cerrará una vez se acabe de leer
            lectorArchivo.close();
            lectorArchivo = null;
        }
        if (escritorArchivo != null) {
            //si el buffer de escritura ya no es nulo se cerrará una vez se acabe de escribir
            escritorArchivo.flush();
            escritorArchivo.close();
            escritorArchivo = null;
        }
        if (archivo != null) {
            //si el archivo ya no es nulo se cerrará una vez acabado
            archivo = null;
        }
    }

    /**
     * Metodo que permite leer una linea entera de un fichero/archivo.
     * Una vez finalice dejara de leer y mostrara un mensaje indicando
     * que se ha llegado al final.
     * ¡OJO! Debes cerrar el archivo despues de escribir para que se guarde los datos introducidos.
     *
     * @throws IOException
     */

    public static void leerLineaArchivo() throws IOException {
        if (lectorArchivo == null) {
            //crea un Buffer de lectura en caso de que no se haya creado antes
            lectorArchivo = new BufferedReader(new FileReader(archivo));
            //Marcamos la posicion inicial con el objetivo de poder volver al principio del archivo sin tener que resetear
            //completamente el archivo o el buffer
            lectorArchivo.mark(0);
        }
        String lineaLeida = "";
        lineaLeida = lectorArchivo.readLine();
        if (lineaLeida != null) {
            System.out.println(lineaLeida);
        }
    }

    /**
     * Metodo que permite escribir dentro de un archivo/fichero.
     * Siempre pone la linea al final del archivo para no sobreescribir
     * @param linea
     * @throws IOException
     */
    public static void escribirLineaArchivo(String linea) throws IOException {
        if (escritorArchivo == null) {
            //crea un Buffer de escritura en caso de que no se haya creado antes
            escritorArchivo = new BufferedWriter(new FileWriter(archivo));
        }
        escritorArchivo.write(linea);
        escritorArchivo.newLine();
    }

    /**
     * Mueve el cursor de escritura y lectura al EOF
     * @throws IOException
     */
    public static void moverCursorEOF() throws IOException {
        if (lectorArchivo == null) {
            lectorArchivo = new BufferedReader(new FileReader(archivo));
        }
        String lectura = lectorArchivo.readLine();
        while (lectura != null) {
            //Volvemos a escribir los valores que leemos ya que sino se pierden por alguna razon
            escribirLineaArchivo(lectura);
            lectura = lectorArchivo.readLine();
        }
    }
    /**
     * Función para mover el cursor de lectura a una posicion deseada. Se supone que empieza a contar desde 0.
     * Si la posicion es demasiado elevada se devolvera en la posicion del EOF.
     * @param posicion
     * @throws IOException
     */
    public static void moverCursor(int posicion) throws IOException {
        if (lectorArchivo == null) {
            lectorArchivo = new BufferedReader(new FileReader(archivo));
        }
        lectorArchivo.reset();
        String lectura = lectorArchivo.readLine();
        for (int i = 0; i < posicion && lectura != null; i++) {
            escribirLineaArchivo(lectura);
            lectura = lectorArchivo.readLine();
        }
    }
}
