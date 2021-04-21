package eu.fp.concesionario;


import java.sql.SQLException;

/**
 * Clase Nomina
 *
 *
 * @author Marat Rafael
 */
public class Nomina {

    //atributos
    private int horasTrabajadas;
    private float sueldoTotal;
    private float sueldoSinImpuestos;
    private String fechaNomina;
    private float precioPorHora;

    // constructor vacio
    public Nomina() {
    }

    // constructor con todos atributos
    public Nomina(int horasTrabajadas, float sueldoTotal, float sueldoSinImpuestos, String fechaNomina, float precioPorHora) {
        this.horasTrabajadas = horasTrabajadas;
        this.sueldoTotal = sueldoTotal;
        this.sueldoSinImpuestos = sueldoSinImpuestos;
        this.fechaNomina = fechaNomina;
        this.precioPorHora = precioPorHora;
    }

    // constructor copia
    public Nomina(Nomina nomina) {
        this.horasTrabajadas = nomina.getHorasTrabajadas();
        this.sueldoTotal = nomina.getSueldoTotal();
        this.sueldoSinImpuestos = nomina.getSueldoSinImpuestos();
        this.fechaNomina = nomina.getFechaNomina();
        this.precioPorHora = nomina.getPrecioPorHora();
    }

    // setter/getter
    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) throws IllegalArgumentException {
        this.horasTrabajadas = horasTrabajadas;
        if (horasTrabajadas < 0) {
            throw new IllegalArgumentException("Valor horas trabajadas no es valido");
        }
    }

    public float getSueldoTotal() {
        return sueldoTotal;
    }

    public void setSueldoTotal(float sueldoTotal) throws IllegalArgumentException {

        this.sueldoTotal = sueldoTotal;
        if (sueldoTotal < 0) {
            throw new IllegalArgumentException("Valor del sueldo no es valido");
        }
    }

    public float getSueldoSinImpuestos() {
        return sueldoSinImpuestos;
    }

    public void setSueldoSinImpuestos(float sueldoSinImpuestos) throws IllegalArgumentException {

        this.sueldoSinImpuestos = sueldoSinImpuestos;
        if (sueldoSinImpuestos < 0) {
            throw new IllegalArgumentException("Valor del sueldo no es valido");
        }
    }

    public String getFechaNomina() {
        return fechaNomina;
    }

    public void setFechaNomina(String fechaNomina) {
        this.fechaNomina = fechaNomina;
    }

    public float getPrecioPorHora() {
        return precioPorHora;
    }

    public void setPrecioPorHora(float precioPorHora) {
        this.precioPorHora = precioPorHora;
        if (precioPorHora < 0) {
            throw new IllegalArgumentException("Valor precio por hora no valido");
        }
    }

    // toString
    @Override
    public String toString() {
        return "Nomina{" + ", horasTrabajadas=" + horasTrabajadas + ", sueldoTotal=" + sueldoTotal + ", sueldoSinImpuestos=" + sueldoSinImpuestos + ", fechaNomina=" + fechaNomina + ", precioPorHora=" + precioPorHora + '}';
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

            System.out.println("Precio por hora: ");
            nomina.setPrecioPorHora(Utils.kFloat());

            nomina.setSueldoSinImpuestos(nomina.getHorasTrabajadas() * nomina.precioPorHora);

            float impuestoSobreNomina = nomina.getSueldoSinImpuestos() * Utils.IMPUESTO;
            nomina.setSueldoTotal(nomina.getSueldoSinImpuestos() - impuestoSobreNomina);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        System.out.println(nomina.toString());
        return nomina;
    }

    /**
     * metodo para insertar datos de una nomina apartir de un objeto
     *
     * @param nomina
     */
    public static void insertarDatosNominaBBDD(Nomina nomina) {
        String consulta = "INSERT INTO NOMINA (HORAS_TRABAJO, PRECIO_POR_HORA, SUELDO_TOTAL, SUELDO_SIN_IMPUESTO, FECHA_NOMINA) VALUES (?,?,?,?,?)";

        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);

            Utils.prst.setDouble(1, nomina.getHorasTrabajadas());
            Utils.prst.setDouble(2, nomina.getPrecioPorHora());
            Utils.prst.setDouble(3, nomina.getSueldoTotal());
            Utils.prst.setDouble(4, nomina.getSueldoSinImpuestos());
            Utils.prst.setString(5, nomina.getFechaNomina());
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
                        + "HORAS DE TRABAJO:" + Utils.rs.getInt(2) + ", "
                        + "PRECIO POR HORA:" + Utils.rs.getDouble(3) + ", "
                        + "SUELDO TOTAL:" + Utils.rs.getDouble(4) + ", "
                        + "SUELDO SIN IMPUESTO:" + Utils.rs.getDouble(5) + ", "
                        + "FECHA DE NOMINA:" + Utils.rs.getString(6)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Nomina.class.getName()).log(Level.SEVERE, null, ex);
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
     * comprobamos si una nomina esta en BBDD devuelve su ID o -1 si no esta
     * @param idNomina
     * @return 
     */
    public static int buscarNominaBBDD(int idNomina) {
        String buscar = "SELECT * FROM NOMINA WHERE ID=?";
        int posicion = -1;
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(buscar);
            Utils.prst.setInt(1, idNomina);
            Utils.rs = Utils.prst.executeQuery();

            while (Utils.rs.next()) {
                posicion = Utils.rs.getInt(1);
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
        return posicion;
    }
    
    public static void modificarNominaBBDD(int IDNomina, int HorasTrabajo,double PrecioPorHora,double SueldoTotal, double SueldoSinImpuesto, String fecha){
        String consulta="UPDATE NOMINA SET HORAS_TRABAJO=?, PRECIO_POR_HORA=?, SUELDO_TOTAL=?, SUELDO_SIN_IMPUESO=?, FECHA_NOMINA=?  WHERE ID=?";
        
        try {
            Utils.connection=Utils.conectarBBDD();
            Utils.prst=Utils.connection.prepareStatement(consulta);
            Utils.prst.setInt(1, HorasTrabajo);
            Utils.prst.setDouble(2, PrecioPorHora);
            Utils.prst.setDouble(3, SueldoTotal);
            Utils.prst.setDouble(4, SueldoSinImpuesto);
            Utils.prst.setDouble(5, SueldoSinImpuesto);
            Utils.prst.setString(6, fecha);
            
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
    
    
}
