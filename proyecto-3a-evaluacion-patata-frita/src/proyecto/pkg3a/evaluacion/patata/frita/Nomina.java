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

    public void setHorasTrabajadas(int horasTrabajadas) throws Exception {
        try {
            this.horasTrabajadas = horasTrabajadas;
            if (horasTrabajadas < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
        }
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
     * @return Object Nomina
     * @throws java.lang.Exception
     */
    public static Nomina crearNomina() throws Exception {
        Nomina nomina = new Nomina();
        System.out.println("horas trabajadas: ");
        nomina.setHorasTrabajadas(sc.nextInt());

        String fecha = Util.establecerFechaActual();
        nomina.setFechaNomina(fecha);

        System.out.println("Sueldo sin impuestos: ");
        nomina.setSueldoSinImpuestos(sc.nextDouble());

        System.out.println("Sueldo total: ");
        nomina.setSueldoTotal(sc.nextDouble());

        System.out.println(nomina.toString());
        return nomina;
    }// fin metodo crearNomina

}//fin clase nomina
