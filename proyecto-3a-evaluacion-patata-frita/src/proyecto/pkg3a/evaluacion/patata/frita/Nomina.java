package proyecto.pkg3a.evaluacion.patata.frita;

import java.util.InputMismatchException;
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

    public void setHorasTrabajadas(int horasTrabajadas) throws InputMismatchException {
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getSueldoTotal() {
        return sueldoTotal;
    }

    public void setSueldoTotal(double sueldoTotal) {
        this.sueldoTotal = sueldoTotal;
    }

    public double getSueldoSinImpuestos() {
        return sueldoSinImpuestos;
    }

    public void setSueldoSinImpuestos(double sueldoSinImpuestos) {
        this.sueldoSinImpuestos = sueldoSinImpuestos;
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
        return "Nomina{" + "horasTrabajadas=" + horasTrabajadas + ", sueldoTotal=" + sueldoTotal + ", sueldoSinImpuestos=" + sueldoSinImpuestos + ", fechaNomina=" + fechaNomina + '}';
    }

    /**
     * metodo estatico para crear nueva nomina
     *
     * @return Object Nomina
     *
     */
    public static Nomina crearNomina() throws InputMismatchException {
        Nomina nomina = new Nomina();

        try {
            String fecha = Util.establecerFechaActual();
            nomina.setFechaNomina(fecha);

            System.out.println("horas trabajadas: ");
            int horasTrabajadas = sc.nextInt();
            if (horasTrabajadas < 0) {
                throw new InputMismatchException();
            }
            nomina.setHorasTrabajadas(horasTrabajadas);

            System.out.println("Sueldo sin impuestos: ");
            double sueldoSinImpuesto = sc.nextDouble();
            if (sueldoSinImpuesto < 0) {
                throw new InputMismatchException();
            }
            nomina.setSueldoSinImpuestos(sueldoSinImpuesto);

            System.out.println("Sueldo total: ");
            double sueldoTotal = sc.nextDouble();
            if (sueldoTotal < 0) {
                throw new InputMismatchException();
            }
            nomina.setSueldoTotal(sueldoTotal);

        } catch (InputMismatchException e) {
            System.out.println("Error al crear nomina");
        }
        System.out.println(nomina.toString());
        return nomina;
    }// fin metodo crearNomina

}//fin clase nomina
