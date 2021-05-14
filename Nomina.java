package eu.fp.concesionario;

import java.sql.SQLException;
import java.util.logging.*;

/**
 * Clase Nomina
 *
 * @author Marat Rafael
 */
public class Nomina {

    //atributos
    private int id=-1; //Se carga únicamente al leer de la base de datos
    private int horasTrabajadas;
    private float sueldoBruto;
    private float sueldoNeto;
    private String fechaNomina;
    private Empleado empleado;

    // constructor vacio
    public Nomina() {
    }

    // constructor con todos atributos
    public Nomina(int id, int horasTrabajadas, float sueldoBruto, float sueldoNeto, String fechaNomina, Empleado empleado) {
        this.id = id;
        this.horasTrabajadas = horasTrabajadas;
        this.sueldoBruto = sueldoBruto;
        this.sueldoNeto = sueldoNeto;
        this.fechaNomina = fechaNomina;
        this.empleado = empleado;
    }

    // constructor copia
    public Nomina(Nomina nomina) {
        this.id = nomina.getId();
        this.horasTrabajadas = nomina.getHorasTrabajadas();
        this.sueldoBruto = nomina.getSueldoBruto();
        this.sueldoNeto = nomina.getSueldoNeto();
        this.fechaNomina = nomina.getFechaNomina();
        this.empleado = nomina.getEmpleado();
    }

    // setter/getter
    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setHorasTrabajadas(int horasTrabajadas) throws IllegalArgumentException {
        this.horasTrabajadas = horasTrabajadas;
        if (horasTrabajadas < 0) {
            throw new IllegalArgumentException("Valor horas trabajadas no es valido");
        }
    }

    public float getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(float sueldoBruto) throws IllegalArgumentException {
        this.sueldoBruto = sueldoBruto;
        if (sueldoBruto < 0) {
            throw new IllegalArgumentException("Valor del sueldo no es valido");
        }
    }

    public float getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(float sueldoNeto) throws IllegalArgumentException {
        this.sueldoNeto = sueldoNeto;
        if (sueldoNeto < 0) {
            throw new IllegalArgumentException("Valor del sueldo no es valido");
        }
    }

    public void setId(int id){this.id = id;}

    public int getId(){return this.id;}

    public String getFechaNomina() {
        return fechaNomina;
    }

    public void setFechaNomina(String fechaNomina) {
        this.fechaNomina = fechaNomina;
    }

    // toString

    @Override
    public String toString() {
        return "Nomina { " + "id=" + id + 
                ", horasTrabajadas=" + horasTrabajadas + 
                ", sueldoBruto=" + sueldoBruto + 
                ", sueldoNeto=" + sueldoNeto + 
                ", fechaNomina=" + fechaNomina + 
                ", empleado=" + empleado + '}';
    }


    /**
     * metodo estatico para crear nueva nomina
     *
     * @return Object Nomina
     *
     */
    public static Nomina crearNomina() throws IllegalArgumentException {
        Nomina nomina = new Nomina();
        try {
            String fecha = Utils.establecerFechaActual();
            nomina.setFechaNomina(fecha);
            System.out.println("horas trabajadas: ");
            nomina.setHorasTrabajadas(Utils.kInt());
            System.out.println("Sueldo bruto");
            nomina.setSueldoBruto(Utils.kFloat());
            float impuestoSobreNomina = nomina.getSueldoBruto() * Utils.IMPUESTO;
            nomina.setSueldoNeto(nomina.getSueldoBruto() - impuestoSobreNomina);
            System.out.println("lista  de los empleados");
            mostrarNifNombreApelidoEmpleados();
            System.out.println("Elige NIF para nomina");
            String nifEmpleado = Utils.kString();
            
            //TODO: nomina.setEmpleado(buscarEmpleadoBBDD(nifEmpleado));
            
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        //System.out.println(nomina.toString());
        return nomina;
    }

    /**
     * metodoestatico para listar los empleados, usado en crearNomina()
     */
    public static void mostrarNifNombreApelidoEmpleados() {
        String consulta = "SELECT NIF,NOMBRE,APELLIDOS FROM EMPLEADO";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery(consulta);
            while (Utils.rs.next()) {
                System.out.println(" NIF: " + Utils.rs.getString(1)
                        + " , NOMBRE: " + Utils.rs.getString(2)
                        + " , APELLIDO " + Utils.rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Error !!!");
        }
    }

    /**
     * metodo para insertar datos de una nomina apartir de un objeto
     *
     * @param nomina
     */
    public static void insertarDatosNominaBBDD(Nomina nomina) {

        String consulta = "INSERT INTO NOMINA (HORAS, SUELDO_BRUTO, SUELDO_NETO, FECHA, EMPLEADONIF) VALUES (?,?,?,?,?)";
        java.sql.Date sqlDate = Utils.adaptarFechaMYSQL(nomina.getFechaNomina());
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, nomina.getHorasTrabajadas());
            Utils.prst.setFloat(2, nomina.getSueldoBruto());
            Utils.prst.setFloat(3, nomina.getSueldoNeto());
            Utils.prst.setDate(4, sqlDate);
            //TODO: nomina.setEmpleado(buscarEmpleadoBBDD(nifEmpleado));
            
            Utils.prst.setString(5, nomina.getEmpleado().getNif());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctamente señor");

        } catch (SQLException ex) {
            System.out.println("Error insertas datos");
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
    }

    /**
     * metodo de Instancia para insertar datos a la base de datos
     */
    public void insertarDatosNominaBBDD() {
        String consulta = "INSERT INTO NOMINA (HORAS, SUELDO_BRUTO, SUELDO_NETO, FECHA, EMPLEADONIF) VALUES (?,?,?,?,?)";
        java.sql.Date sqlDate = Utils.adaptarFechaMYSQL(this.getFechaNomina());
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.getHorasTrabajadas());
            Utils.prst.setFloat(2, this.getSueldoBruto());
            Utils.prst.setFloat(3, this.getSueldoNeto());
            Utils.prst.setDate(4, sqlDate);

            ////TODO: nomina.setEmpleado(buscarEmpleadoBBDD(nifEmpleado));
            Utils.prst.setString(5, this.getEmpleado().getNif());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctamente señor!");

        } catch (SQLException ex) {
            System.out.println("Error insertas datos");
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
    }

    /**
     * Metodo para mostrar todas las nominas de la BBDD
     */
    public static void mostrarTodasNominas() {
        String consulta = "SELECT * FROM NOMINA ORDER BY ID";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery(consulta);
            while (Utils.rs.next()) {
                System.out.println(
                        "ID:" + Utils.rs.getInt(1) + ", "
                        + "HORAS DE TRABAJO: " + Utils.rs.getInt(2) + ", "
                        + "PRECIO BRUTO: " + Utils.rs.getFloat(3) + ", "
                        + "SUELDO NETO: " + Utils.rs.getFloat(4) + ", "
                        + "FECHA: " + Utils.rs.getString(5) + ", "
                        + "NIF DEL EMPLEADO: " + Utils.rs.getString(6)
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error mostrar todas nominas");
        } finally {
            try {
                if (Utils.rs != null) {
                    Utils.rs.close();
                }
                if (Utils.st != null) {
                    Utils.st.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
    }

    /**
     * borrar una nomina indicando id
     *
     * @param idNomina
     */
    public static void borrarNomina(int idNomina) {
        String borrar = "DELETE FROM NOMINA WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(borrar);
            Utils.prst.setInt(1, idNomina);
            Utils.prst.executeUpdate();
            System.out.println("Nomina con id: " + idNomina + " borrada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al borar datos");
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
    }

    /**
     * comprobamos si una nomina esta en BBDD devuelve true o false
     *
     * @param idNomina
     * @return
     */
    public static boolean existNominaBBDD(int idNomina) {
        String buscar = "SELECT * FROM NOMINA WHERE ID=?";
        boolean encontrado = false;
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(buscar);
            Utils.prst.setInt(1, idNomina);
            Utils.rs = Utils.prst.executeQuery();         
            if (Utils.rs.next()) {
                encontrado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error a buscar nomina");
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
                System.out.println("Error al cerrar conexion");
            }
        }
        return encontrado;
    }

    /**
     * modificamos nomina
     *
     * @param IDNomina
     * @param HorasTrabajo
     * @param SueldoBruto
     * @param SueldoNeto
     * @param fecha
     */
    public static void modificarNominaBBDD(int IDNomina, int HorasTrabajo, float SueldoBruto, float SueldoNeto, String fecha, String EmpleadoNif) {
        String consulta = "UPDATE NOMINA SET HORAS=?, SUELDO_BRUTO=?, SUELDO_NETO=?, FECHA=?,  EMPLEADONIF=? WHERE ID=?";
        java.sql.Date sqlDate = Utils.adaptarFechaMYSQL(fecha);
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, HorasTrabajo);
            Utils.prst.setFloat(2, SueldoBruto);
            Utils.prst.setFloat(3, SueldoNeto);
            Utils.prst.setDate(4, sqlDate);
            Utils.prst.setString(5, EmpleadoNif);
            Utils.prst.setInt(6, IDNomina);
            Utils.prst.executeUpdate();
            System.out.println("Datos actualizados señor!");
        } catch (SQLException ex) {
            System.out.println("Error modificar nomina");
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
    }

    /**
     * relacionamos una nomina con un empleado usando ID de la nomina i NIF del
     * empleado
     *
     * @param NominaID
     * @param NifEmpleado
     */
    public static void relacionarNominaConEmpleado(int NominaID, String NifEmpleado) {
        String consulta = "UPDATE NOMINA SET EMPLEADONIF=? WHERE ID=?";

        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, NifEmpleado);
            Utils.prst.setInt(2, NominaID);
            Utils.prst.executeUpdate();
            System.out.println("Nomina con id " + NominaID + " se ha relacionado con empleado " + NifEmpleado + " correctamente");
        } catch (SQLException e) {
            System.out.println("Error relacionar Nomina " + NominaID + " con Empleado " + NifEmpleado);
        } finally {
            try {
                if (Utils.prst != null) {
                    Utils.prst.close();
                }
                if (Utils.connection != null) {
                    Utils.connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion");
            }
        }
    }

    /**
     * metodo para buscar una nomina en BBDD, devuelve objeto nomina
     *
     * @param idNomina
     * @return
     */
    public static Nomina buscarNominaBBDD(int idNomina) {
        String consulta = "SELECT HORAS, SUELDO_BRUTO, SUELDO_NETO, FECHA ,EMPLEADONIF FROM NOMINA WHERE ID=?";
        if (!existNominaBBDD(idNomina)) {
            return null;
        } else {

            Nomina n = new Nomina();
            Utils.connection = Utils.conectarBBDD();
            try {
                Utils.prst = Utils.connection.prepareStatement(consulta);
                Utils.prst.setInt(1, idNomina);
                Utils.rs = Utils.prst.executeQuery();
                Utils.rs.next();
                n.setId(idNomina);
                n.setHorasTrabajadas(Utils.rs.getInt(1));
                n.setSueldoBruto(Utils.rs.getFloat(2));
                n.setSueldoNeto(Utils.rs.getFloat(3));
                n.setFechaNomina(Utils.rs.getString(4));
                //TODO setEmpleado
                //n.setEmpleado(buscarEmpleadoBBDD(empleadoNif));
                System.out.println("Nomina encontrada y creada " + n.toString());
            } catch (SQLException ex) {
                System.out.println("Error buscar nomina");
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
                } catch (SQLException ex) {
                    System.out.println("Error cerrar conexion");
                }
            }
            return n;
        }
    }

    /**
     * Metodo de instancia para buscar la nomina actual en la BBDD con ID
     * (primary key) devuelve boolean
     *
     * @return devuelve true si existe en BBDD, devuelve false si no existe en
     * BBDD
     */
    public boolean existInDB() {
        boolean existe = false;
        String consulta = "SELECT * FROM NOMINA WHERE ID=?";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.getId());
            Utils.rs = Utils.prst.executeQuery();   
            if (Utils.rs.next()) {
                existe = false;
            } else {
                existe = true;
            }
        } catch (SQLException e) {
            System.out.println("Error consultar BBDD");
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
            } catch (SQLException ex) {
                System.out.println("Error cerrar conexion");
            }
        }
        return existe;
    }
}
