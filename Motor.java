package eu.fp.concesionario;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase motor donde se guardan los datos de los distintos motores Contiene el
 * conuunto de variables necesarias y un enum.
 *
 * @author Joan
 */
public class Motor {

    public enum tipoMotor {
        Gasolina95,
        Gasolina98,
        Diesel,
        Electrico
    }
    private int id = -1; //ID que empezará siempre en -1, se usa para guardar el id que recibe en la base de datos
    private float potencia;
    private float par;
    private float cilindrada;  //Únicamente usado en motores no eléctricos
    private int num_motores; //Únicamente usado en motores eléctricos
    private tipoMotor tipo;

    /**
     * Constructor vacío
     */
    public Motor() {

    }

    /**
     * Constructor borcon todos los datos por parámetro
     *
     * @param potencia
     * @param par
     * @param cilindrada
     * @param num_motores
     */
    public Motor(float potencia, float par, float cilindrada, int num_motores, tipoMotor motor) {
        this.potencia = potencia;
        this.par = par;
        this.cilindrada = cilindrada;
        this.num_motores = num_motores;
        this.tipo = motor;
    }

    /**
     * Constructor copia
     *
     * @param motor
     */
    public Motor(Motor motor) {
        this.potencia = motor.getPotencia();
        this.par = motor.getPar();
        this.cilindrada = motor.getCilindrada();
        this.num_motores = motor.getNum_motores();
        this.tipo = motor.getTipo();
    }

    public tipoMotor getTipo() {
        return tipo;
    }

    public void setTipo(tipoMotor tipo) {
        this.tipo = tipo;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public float getPotencia() {
        return potencia;
    }

    public void setPotencia(float potencia) throws IllegalArgumentException {
        if (potencia < 0) {
            throw new IllegalArgumentException("Un motor no puede tener potencia negativa");
        } else {
            this.potencia = potencia;
        }
    }

    public float getPar() {
        return par;
    }

    public void setPar(float par) throws IllegalArgumentException {
        if (par < 0) {
            throw new IllegalArgumentException("El par no puede ser negativo");
        } else {
            this.par = par;
        }
    }

    public float getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(float cilindrada) throws IllegalArgumentException {
        if (cilindrada < 0) {
            throw new IllegalArgumentException("El par no puede ser negativo");
        } else {
            this.cilindrada = cilindrada;
        }
    }

    public int getNum_motores() {
        return num_motores;
    }

    public void setNum_motores(int num_motores) throws IllegalArgumentException {
        if (num_motores < 1 || num_motores > 8) {
            throw new IllegalArgumentException("Debe haber entre 1 y 8 motores");
        } else {
            this.num_motores = num_motores;
        }
    }

    @Override
    public String toString() {
        return "Motor{"
                + "potencia=" + potencia
                + ", par=" + par
                + ", cilindrada=" + cilindrada
                + ", num_motores=" + num_motores
                + '}';
    }

    /**
     * crear objeto vehiculo
     *
     * @return Vehiculo
     */
    public static Motor crearMotor() {
        Motor motor = new Motor();
        try {
            motor.setTipo(tipoMotor.valueOf(Utils.kString("Que tipo de motor es? (Gas95, Gas98, Diesel o Electrico)")));
            //si el vehiculo es electrico comprobar la cantidad de los mismos
            if (motor.getTipo().toString().equals("Electrico")) {
                motor.setNum_motores(Utils.kInt("Cantidad de motores"));
                //sino solo habra un motor
            } else {
                motor.setNum_motores(1);
            }
            motor.setCilindrada(Utils.kInt("Cilindrada"));
            motor.setPar(Utils.kInt("Par"));
            motor.setPotencia(Utils.kInt("Potencia"));
        } catch (Exception e) {
            System.out.println("Error al crear el vehiculo, seguramente algun dato introducido fuera incorrecto, pruebe otra vez.");
        }
        return motor;
    }

    /**
     * Insertar datos de la clase actual a la base de datos
     *
     */
    public void insertarDatosMotorBBDD() {
        //INSERT de todos los datos excepto ventaid y clientenif, ya que se supone que el vehiculo aun no se ha vendido, para ello habra otro metodo
        String consulta = "INSERT INTO MOTOR (ID, TIPO, POTENCIA, CILINDRADA, NUM_MOTORES ) VALUES (?,?,?,?,?)";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.getId());
            Utils.prst.setString(2, this.getTipo().toString());
            Utils.prst.setFloat(3, this.getPotencia());
            Utils.prst.setFloat(4, this.getCilindrada());
            Utils.prst.setInt(5, this.getNum_motores());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctomnte!");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos a la BBDD");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * buscamos un motor segun id, si no lo ecuentra devuelve null, si lo
     * encuentra devuelve una clase motor con todos los datos
     *
     * @param id
     * @return Null of error, else motor
     */
    public static Motor buscarMotorBBDD(int id) {
        //Testeando con LIKE, es posible que tenga que ser =
        String consulta = "SELECT * FROM MOTOR WHERE id LIKE ?";
        Motor motor = new Motor();
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            motor.setId(Utils.rs.getInt(1));
            motor.setTipo(tipoMotor.valueOf(Utils.rs.getString(2)));
            motor.setPotencia(Utils.rs.getFloat(3));
            motor.setCilindrada(Utils.rs.getFloat(4));
            motor.setNum_motores(Utils.rs.getInt(5));
        } catch (SQLException e) {
            System.out.println("Error al buscar vehiculo");
            motor = null;
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return motor;
    }

    /**
     * Se modifica el concesionario actual en la base de datos con los datos
     * actuales de la clase
     *
     * @return Devuelve 0 si correcto, -1 si error
     */
    public int modificarMotorBBDD() {
        int ret = 0;
        String consulta = "UPDATE MOTOR SET TIPO=?, POTENCIA=?, CILINDRADA=?, NUM_MOTORES=? WHERE ID=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, this.getTipo().toString());
            Utils.prst.setFloat(2, this.getPotencia());
            Utils.prst.setFloat(3, this.getCilindrada());
            Utils.prst.setInt(4, this.getNum_motores());
            Utils.prst.setInt(5, this.getId());
            Utils.prst.executeUpdate();
            System.out.println("Datos actualizados correctamente!");
        } catch (SQLException e) {
            System.out.println("Error actualizar datos");
            ret = -1;
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return ret;
    }

    /**
     * Se borra el motor actual de la base de datos, puede dar errores si se
     * borra mientras un vehiculo apunta a el
     */
    public void borrarMotorBBDD() {
        String consulta = " DELETE FROM MOTOR WHERE ID=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.getId());
            Utils.prst.executeUpdate();
            System.out.println("Vehiculo borrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error borrar datos, asegurese de que ningun vehiculo use este motor");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Se muestran todos los motores en la base de datos
     */
    public static void mostrarTodosMotoresBBDD() {
        String consulta = "SELECT * FROM MOTOR ORDER BY ID";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();

            while (Utils.rs.next()) {
                System.out.println("ID: " + Utils.rs.getInt(1) + ","
                        + "TIPO: " + Utils.rs.getString(2) + ","
                        + "POTENCIA: " + Utils.rs.getInt(3) + ","
                        + "CILINDRADA: " + Utils.rs.getInt(4) + ","
                        + "NUM_MOTORES: " + Utils.rs.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println("Error mostrando todos los motores");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Comprueba si el vehiculo actual ya existe en la base de datos
     */
    public boolean existsInDB() {
        boolean ret = false;
        String consulta = "SELECT * FROM MOTOR WHERE ID=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.getId());
            if (Utils.rs != null) {
                ret = true;
            } else {
                ret = false;
            }
        } catch (SQLException e) {
            System.out.println("No se pudo encontrar el motor");
            ret = false;
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return ret;
    }

    /**
     * Comprueba si el motor con ese id existe en la base de datos
     */
    public static boolean existsInDB(int id) {
        boolean ret = false;
        String consulta = "SELECT * FROM MOTOR WHERE ID=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, id);
            if (Utils.rs != null) {
                ret = true;
            } else {
                ret = false;
            }
        } catch (SQLException e) {
            System.out.println("No se pudo encontrar el motor");
            ret = false;
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return ret;
    }

    /**
     * Devolver informacion de todos los motores para la interfaz gráfica
     * @return 
     */
    public static Object[][] devolverTodosMotoresBBDD() {
        String consulta = "SELECT * FROM MOTOR ORDER BY id";
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement("SELECT count(*) FROM VEHICULO"); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs = Utils.prst.executeQuery();
            Utils.rs.next();
            objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.rs = Utils.st.executeQuery(consulta);
            while (Utils.rs.next()) {
                Integer COLUMNAS = 5; // MODIFICAR LONGITUD DE LA LISTA EN OTRAS CLASES
                String[] list = new String[COLUMNAS]; 
                int x = 0;
                while (x < COLUMNAS) {
                    list[x] = (Utils.rs.getString(x + 1));
                    x++;
                }
                objectList[i] = list;
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error devolviendo todos los motores");
            e.getSQLState();
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
