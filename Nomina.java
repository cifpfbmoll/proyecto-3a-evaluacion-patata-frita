package eu.fp.concesionario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;

/**
 * Clase Nomina
 *
 * @author Marat Rafael
 */
public class Nomina {

    //atributos
    private int id = -1; //Se carga únicamente al leer de la base de datos
    private int horasTrabajadas;
    private float sueldoBruto;
    private float sueldoNeto;
    private String fechaNomina;
    private Empleado empleado;

    /**
     * Constructor vacio
     */
    public Nomina() {
    }

    /**
     * Constructor con todos los atributos
     *
     * @param id
     * @param horasTrabajadas
     * @param sueldoBruto
     * @param sueldoNeto
     * @param fechaNomina
     * @param empleado
     */
    public Nomina(int id, int horasTrabajadas, float sueldoBruto, float sueldoNeto, String fechaNomina, Empleado empleado) {
        this.id = id;
        this.horasTrabajadas = horasTrabajadas;
        this.sueldoBruto = sueldoBruto;
        this.sueldoNeto = sueldoNeto;
        this.fechaNomina = fechaNomina;
        this.empleado = empleado;
    }

    /**
     * Constructor copia
     *
     * @param nomina una nomina pasa como parametro
     */
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getFechaNomina() {
        return fechaNomina;
    }

    public void setFechaNomina(String fechaNomina) {
        this.fechaNomina = fechaNomina;
    }

    // toString
    @Override
    public String toString() {
        return "Nomina { " + "id=" + id
                + ", horasTrabajadas=" + horasTrabajadas
                + ", sueldoBruto=" + sueldoBruto
                + ", sueldoNeto=" + sueldoNeto
                + ", fechaNomina=" + fechaNomina
                + ", empleado=" + empleado + '}';
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

            nomina.setEmpleado(Empleado.buscarEmpleadoBBDD(nifEmpleado));

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
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                System.out.println(" NIF: " + Utils.rs.getString(1)
                        + " , NOMBRE: " + Utils.rs.getString(2)
                        + " , APELLIDO " + Utils.rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Error !!!");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * metodo estatico para insertar datos de una nomina apartir de un objeto
     *
     * @param nomina
     */
    public static void insertarDatosNominaBBDD(Nomina nomina) {

        String consulta = "INSERT INTO NOMINA (HORAS, SUELDO_BRUTO, SUELDO_NETO, FECHA, EMPLEADONIF) VALUES (?,?,?,?,?)";
        java.sql.Date sqlDate = Utils.adaptarFechaMYSQL(nomina.getFechaNomina());
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, nomina.getHorasTrabajadas());
            Utils.prst.setFloat(2, nomina.getSueldoBruto());
            Utils.prst.setFloat(3, nomina.getSueldoNeto());
            Utils.prst.setDate(4, sqlDate);
            Utils.prst.setString(5, nomina.getEmpleado().getNif());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctamente señor");

        } catch (SQLException ex) {
            System.out.println("Error insertas datos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
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
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.getHorasTrabajadas());
            Utils.prst.setFloat(2, this.getSueldoBruto());
            Utils.prst.setFloat(3, this.getSueldoNeto());
            Utils.prst.setDate(4, sqlDate);
            Utils.prst.setString(5, this.getEmpleado().getNif());
            Utils.prst.executeUpdate();
            System.out.println("Datos insertados correctamente señor!");

        } catch (SQLException ex) {
            System.out.println("Error insertas datos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Metodo para mostrar todas las nominas de la BBDD
     */
    public static void mostrarTodasNominas() {
        String consulta = "SELECT * FROM NOMINA ORDER BY ID";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                System.out.println(
                        "ID:" + Utils.rs.getInt(1) + ", "
                        + "HORAS DE TRABAJO: " + Utils.rs.getInt(2) + ", "
                        + "SUELDO BRUTO: " + Utils.rs.getFloat(3) + ", "
                        + "SUELDO NETO: " + Utils.rs.getFloat(4) + ", "
                        + "FECHA: " + Utils.rs.getString(5) + ", "
                        + "NIF DEL EMPLEADO: " + Utils.rs.getString(6)
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error mostrar todas nominas");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * borrar una nomina indicando id
     *
     * @param idNomina
     */
    public static void borrarNominaBBDD(int idNomina) {
        String borrar = "DELETE FROM NOMINA WHERE ID=?";
        try {
            Utils.prst = Utils.connection.prepareStatement(borrar);
            Utils.prst.setInt(1, idNomina);
            Utils.prst.executeUpdate();
            System.out.println("Nomina con id: " + idNomina + " borrada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al borar datos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
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
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
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
     * @param EmpleadoNif
     */
    public static void modificarNominaBBDD(int IDNomina, int HorasTrabajo, float SueldoBruto, float SueldoNeto, String fecha, String EmpleadoNif) {
        String consulta = "UPDATE NOMINA SET HORAS=?, SUELDO_BRUTO=?, SUELDO_NETO=?, FECHA=?, EMPLEADONIF=? WHERE ID=?";
        java.sql.Date sqlDate = Utils.adaptarFechaMYSQL(fecha);
        try {
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
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
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
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, NifEmpleado);
            Utils.prst.setInt(2, NominaID);
            Utils.prst.executeUpdate();
            System.out.println("Nomina con id " + NominaID + " se ha relacionado con empleado " + NifEmpleado + " correctamente");
        } catch (SQLException e) {
            System.out.println("Error relacionar Nomina " + NominaID + " con Empleado " + NifEmpleado);
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * metodo para buscar una nomina en BBDD, devuelve objeto nomina
     *
     * @param idNomina
     * @return Objeto "nomina" con sus datos guardados.
     */
    public static Nomina buscarNominaBBDD(int idNomina) {
        String consulta = "SELECT HORAS, SUELDO_BRUTO, SUELDO_NETO, FECHA ,EMPLEADONIF FROM NOMINA WHERE ID=?";
        if (!existNominaBBDD(idNomina)) {
            return null;
        } else {

            Nomina n = new Nomina();
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
                String empleadoNif = Utils.rs.getString(5);//nos devuelve nifEmpleado             
                n.setEmpleado(Empleado.buscarEmpleadoBBDD(empleadoNif));// establecemos empleado

                System.out.println("Nomina encontrada y creada " + n.toString());
            } catch (SQLException ex) {
                System.out.println("Error buscar nomina");
            } finally {
                try {
                    Utils.cerrarVariables();
                } catch (Exception e) {
                    System.out.println("Error al cerrar variables");
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
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, this.getId());
            Utils.rs = Utils.prst.executeQuery();
            if (Utils.rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            System.out.println("Error consultar BBDD");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
        return existe;
    }

    /**
     * Devolver todos las nominas de la base de datos
     *
     * @return objectList
     */
    public static Object[][] devolverTodasNominasBBDD() {
        String consulta = "SELECT `NOMINA`.*,`EMPLEADO`.`NOMBRE`,`EMPLEADO`.`APELLIDOS` FROM NOMINA,EMPLEADO WHERE `NOMINA`.`EMPLEADONIF` like `EMPLEADO`.`NIF` ORDER BY ID";
        String[][] objectList = null;
        try {

            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery("SELECT COUNT(*) FROM NOMINA"); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs.next();
            objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.rs = Utils.st.executeQuery(consulta);
            while (Utils.rs.next()) {
                String[] list = new String[7]; // MODIFICAR LONGITUD DE LA LISTA EN OTRAS CLASES

                list[0] = (Utils.rs.getString(1));
                list[1] = (Utils.rs.getString(2));
                list[2] = (Utils.rs.getString(3));
                list[3] = (Utils.rs.getString(4));
                list[4] = (Utils.rs.getString(5));
                list[5] = (Utils.rs.getString(6));
                list[6] = (Utils.rs.getString(7) + " " + Utils.rs.getString(8));

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

    /**
     * Devolver todas las nominas de un empleado en concreto
     *
     * @param nif NIF del empleado
     * @return
     */
    public static Object[][] devolverTodasNominasBBDD(String nif) {
        String consulta = "SELECT `NOMINA`.*,`EMPLEADO`.`NOMBRE`,`EMPLEADO`.`APELLIDOS` FROM NOMINA,EMPLEADO "
                + "WHERE `NOMINA`.`EMPLEADONIF` like `EMPLEADO`.`NIF` AND `NOMINA`.`EMPLEADONIF` like \"" + nif + "\" ORDER BY ID";
        try {

            Utils.st = Utils.connection.createStatement();
            Utils.rs = Utils.st.executeQuery("SELECT COUNT(*) FROM NOMINA"); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs.next();
            String[][] objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.rs = Utils.st.executeQuery(consulta);
            while (Utils.rs.next()) {
                String[] list = new String[7]; // MODIFICAR LONGITUD DE LA LISTA EN OTRAS CLASES

                list[0] = (Utils.rs.getString(1));
                list[1] = (Utils.rs.getString(2));
                list[2] = (Utils.rs.getString(3));
                list[3] = (Utils.rs.getString(4));
                list[4] = (Utils.rs.getString(5));
                list[5] = (Utils.rs.getString(6));
                list[6] = (Utils.rs.getString(7) + " " + Utils.rs.getString(8));

                objectList[i] = list;
                i++;
            }
            return objectList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error mostrando todos los clientes");
        }
        return null;
    }

    /**
     * Devuelve todos los datos de nomina en la base de datos en un archivo txt
     */
    public static void escribirNominaArchivo() {
        Utils.abrirArchivo("Nomina.txt");
        String consulta = "SELECT * FROM NOMINA";
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {

                Utils.escribirLineaArchivo("Nomina id: " + Utils.rs.getString(1) + " {");
                Utils.escribirLineaArchivo("    Horas de trabajo: " + Utils.rs.getString(2));
                Utils.escribirLineaArchivo("    Sueldo bruto: " + Utils.rs.getString(3));
                Utils.escribirLineaArchivo("    Sueldo neto:" + Utils.rs.getString(4));
                Utils.escribirLineaArchivo("    Fecha: " + Utils.rs.getString(5));
                Utils.escribirLineaArchivo("    NIF empleado: " + Utils.rs.getString(6) + " } ");

                //Dejamos espacio para poder diferenciar facilmente entre vehiculos
                Utils.escribirLineaArchivo(" ");
            }
            Utils.cerrarArchivo();
            System.out.println("Datos escritos correctamente en fichero");
        } catch (Exception e) {
            System.out.println("Problema al leer datos de la base de datos");
        } finally {
            try {
                Utils.cerrarVariables();
            } catch (Exception e) {
                System.out.println("Error al cerrar variables");
            }
        }
    }

    /**
     * Descargar de forma local la información de una nómina data[0] = ID,
     * data[1] = Horas, data[2] = Sueldo Bruto, data[3] = Sueldo Neto, data[4] =
     * Fecha, data[5] = NIF Empleado, data[6] = Nombre
     *
     * @param data Información de la nómina
     */
    public static void descargarNomina(String[] data) throws Exception {
        File txt = new File("nomina_" + data[5] + "_" + data[0] + ".txt");
        BufferedWriter escritor = new BufferedWriter(new FileWriter(txt));

        String separador = "##################################################";

        escritor.newLine();
        escritor.write(separador);
        escritor.newLine();
        escritor.newLine();
        escritor.write("   NIF: " + data[5] + "      Nombre: " + data[6]);
        escritor.newLine();
        escritor.newLine();
        escritor.write(separador);
        escritor.newLine();
        escritor.newLine();
        escritor.write("     ID de nómina: " + data[0]);
        escritor.newLine();
        escritor.write("            Fecha: " + data[4]);
        escritor.newLine();
        escritor.newLine();
        escritor.write("   Horas fichadas: " + data[1]);
        escritor.newLine();
        escritor.write("     Sueldo bruto: " + data[2]);
        escritor.newLine();
        escritor.write("      Sueldo neto: " + data[3]);
        escritor.newLine();
        escritor.newLine();
        escritor.write(separador);
        escritor.newLine();
        escritor.close();
    }

    

     /**
     * Devolver nominas de la base de datos compatible con el filtro
     *
     * @param horasTrabajadas
     * @param sueldoBruto
     * @param sueldoNeto
     * @param fechaNomina
     * @return
     */
    public static Object[][] devolverTodosNominasBBDD(int horasTrabajadas, float sueldoBruto, float sueldoNeto, String fechaNomina) {
        boolean where = false;
        //SQL devuelve Reserva + Nombre cliente + apellidos cliente + tablas relacionadas
        String consulta = "select * from nomina n join empleado e on n.empleadonif = e.nif";
        
        if (horasTrabajadas > 0 && !where) {
            consulta += " WHERE n.horas = \"" + horasTrabajadas + "\"";
            where = true;
        }else if(horasTrabajadas > 0){
            consulta += " AND n.horas = \"" + horasTrabajadas + "\"";
        }
        
        if ( sueldoBruto > 0 && !where) {
            consulta += " WHERE n.sueldo_bruto = \"" + sueldoBruto + "\"";
            where = true;
        }else if(sueldoBruto > 0){
            consulta += " AND n.sueldo_bruto = \"" + sueldoBruto + "\"";
        }
        
        if (sueldoNeto > 0 && !where){
            consulta += " WHERE n.sueldo_neto = \"" + sueldoNeto + "\"";
            where = true;
        }else if ( sueldoNeto > 0) {
            consulta += " AND n.sueldo_neto = \"" + sueldoNeto + "\"";
        }
        
        if (fechaNomina !=null && !where){
            consulta += " WHERE n.fecha = \"" + fechaNomina + "\"";
            where = true;
        }else if ( fechaNomina != null) {
            consulta += " AND n.fecha = \"" + fechaNomina + "\"";
        }
        
        consulta += " ORDER BY ID";
        //Borrar prints, solo para testeo
        System.out.println(consulta);
        String[][] objectList = null;
        try {
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.rs = Utils.prst.executeQuery("SELECT COUNT(*) FROM NOMINA"); // MODIFICAR TABLA EN LAS OTRAS CLASES
            Utils.rs.next();
            
            objectList = new String[Utils.rs.getInt(1)][];
            int i = 0;
            Utils.rs = Utils.prst.executeQuery();
            while (Utils.rs.next()) {
                //Columnas tiene que ser el numero de columnas que devuelva vuestro sql adaptado
                //Contar únicamente que columnas son importantes!
                Integer COLUMNAS = 11;
                //1-horas, 2-sueldoBruto, 3-sueldoNeto, 4-fecha, 5-nif, 6-nombre+apellido, 7-telefono, 8-domicilio, 9-puesto, 10-taller_id, 11-venta_id
                String[] list = new String[COLUMNAS];
                list[0] = Utils.rs.getString(2); // horas
                list[1] = Utils.rs.getString(3); // sueldoBruto
                list[2] = Utils.rs.getString(4); // sueldoNeto
                list[3] = Utils.rs.getString(5); // fecha
                list[4] = Utils.rs.getString(7); // nif
                list[5] = Utils.rs.getString(8) + Utils.rs.getString(9); //Nombre + apellidos
                list[6] = Utils.rs.getString(10); // telefono
                list[7] = Utils.rs.getString(11); // domicilio
                list[8] = Utils.rs.getString(12); // puesto
                list[9] = Utils.rs.getString(13); // taller_id
                list[10] = Utils.rs.getString(14); // venta_id

                objectList[i] = list;
                i++;
            }
            return objectList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error mostrando todos los motores");
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
