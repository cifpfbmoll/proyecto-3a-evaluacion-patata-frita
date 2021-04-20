
/**
 * Clase Nomina
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
        return "Nomina {" + "horasTrabajadas=" + horasTrabajadas + ", sueldoSinImpuestos=" + sueldoSinImpuestos + ", sueldoTotal=" + sueldoTotal + ", fechaNomina=" + fechaNomina + '}';
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
}
