package proyecto.pkg3a.evaluacion.patata.frita;

import java.util.Scanner;

/**
 *
 * @author Marat Rafael
 */
public class Nomina {

    private static Scanner sc = new Scanner(System.in);
    //atributos
    private int horasTrabajadas;
    private double sueldoTotal;
    private double sueldoSinImpuestos;
    private String fechaNomina;

    // constructor vacio
    public Nomina() {
    }

    // constructor con todos atributos
    public Nomina(int horasTrabajadas, double sueldoTotal, double sueldoSinImpuestos, String fechaNomina) {
        this.horasTrabajadas = horasTrabajadas;
        this.sueldoTotal = sueldoTotal;
        this.sueldoSinImpuestos = sueldoSinImpuestos;
        this.fechaNomina = fechaNomina;
    }

    // constructor copia
    public Nomina(Nomina nomina) {
        this.horasTrabajadas = nomina.getHorasTrabajadas();
        this.sueldoTotal = nomina.getSueldoTotal();
        this.sueldoSinImpuestos = nomina.getSueldoSinImpuestos();
        this.fechaNomina = nomina.getFechaNomina();
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

    public double getSueldoTotal() {
        return sueldoTotal;
    }

    public void setSueldoTotal(double sueldoTotal) throws IllegalArgumentException {

        this.sueldoTotal = sueldoTotal;
        if (sueldoTotal < 0) {
            throw new IllegalArgumentException("Valor del sueldo no es valido");
        }
    }

    public double getSueldoSinImpuestos() {
        return sueldoSinImpuestos;
    }

    public void setSueldoSinImpuestos(double sueldoSinImpuestos) throws IllegalArgumentException {

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

    // toString
    @Override
    public String toString() {
        return "Nomina {" + "horasTrabajadas=" + horasTrabajadas +  ", sueldoSinImpuestos=" + sueldoSinImpuestos +", sueldoTotal=" + sueldoTotal + ", fechaNomina=" + fechaNomina + '}';
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
            String fecha = Util.establecerFechaActual();
            nomina.setFechaNomina(fecha);

            System.out.println("horas trabajadas: ");
            nomina.setHorasTrabajadas(sc.nextInt());

            System.out.println("Sueldo sin impuestos: ");
            nomina.setSueldoSinImpuestos(sc.nextDouble());

            System.out.println("Sueldo total: ");
            nomina.setSueldoTotal(sc.nextDouble());

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        System.out.println(nomina.toString());
        return nomina;
    }// fin metodo crearNomina

}//fin clase nomina
