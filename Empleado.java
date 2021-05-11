package patatafrita;

/**
 *
 * @author Karina
 */
public class Empleado extends Persona {
    private Nomina nomina;
    private String puestoTrabajo;

    /**
     * Constructor vacio
     */
    public Empleado() {    
    }
    
    /**
     * Constructor con todos los parametros
     * @param nomina Lista de las nominas
     * @param puestoTrabajo Puesto de trabajo
     * @param nombre Nombre del trabajador
     * @param apellidos Apellidos del trabajador
     * @param nif NIF del trabajador
     * @param telefono Telefono del trabajador
     * @param domicilio Domicilio del trabajador
     */
    public Empleado(Nomina nomina, String puestoTrabajo, String nombre, String apellidos, String nif, Integer telefono, String domicilio) {
        super(nombre, apellidos, nif, telefono, domicilio);
        this.nomina = nomina;
        this.puestoTrabajo = puestoTrabajo;
    }
    
    /**
     * Constructor copia de empleado
     * @param copia Empleado a copiar
     */
    public Empleado(Empleado copia) {
        super(copia.getNombre(), copia.getApellidos(), copia.getNif(), copia.getTelefono(), copia.getDomicilio());
        this.setNomina(copia.getNomina());
        this.setPuestoTrabajo(copia.getPuestoTrabajo());
    }

    // GETTERS Y SETTERS
    public Nomina getNomina() {
        return nomina;
    }

    public void setNomina(Nomina nomina) {
        this.nomina = nomina;
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    @Override
    public String toString(){
        return super.toString() + " puesto de trabajo: " + puestoTrabajo; //Sin el conjunto de nominas, eso vendra con la base de datos y serà una simple llamada
    }

    // TODO: Crear una funcion para crear una nueva nomina directamente (pide los datos al usuario y las pasa a la nomina, controlar errores!)
    public void crearNomina() {
        Nomina nom = new Nomina();
        nom.setFechaNomina(Utils.kString("Inserta la fecha"));
        nom.setHorasTrabajadas(Utils.kInt("Inserta las horas trabajadas"));
        nom.setPrecioPorHora(Utils.kInt("Inserta el precio por hora"));
        nom.setSueldoSinImpuestos(Utils.kInt("Inserta el sueldo sin impuestos"));
        nom.setSueldoTotal(Utils.kInt("Inserta el sueldo total"));
        setNomina(nom);
    }
}
