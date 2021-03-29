package proyecto.pkg3a.evaluacion.patata.frita;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Marat Rafael
 */
public class Nomina {
    private static Scanner scNum = new Scanner(System.in);
    private static Scanner scLine = new Scanner(System.in);
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

    public void setHorasTrabajadas(int horasTrabajadas) {
        
        this.horasTrabajadas = horasTrabajadas;
        while(horasTrabajadas<0){
            System.out.println("no puede establecer horas negativas");
            horasTrabajadas=scNum.nextInt();            
        }
        this.horasTrabajadas=horasTrabajadas;
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
     * metodo estatico para sacar fecha y hora del sistema 
     * @return String fecha
     */
    private static String establecerFechaActual() {
        LocalDateTime dateTime = LocalDateTime.now(); // fecha y hora del sistema
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); //formato
        String fecha = dateTime.format(formatter); //variable guarda fecha y hora como string       
        return fecha;
    }
    
    /**
     * metodo estatico para crear nueva nomina
     * @return Object Nomina
     */
    public static Nomina crearNomina(){
        Nomina nomina = new Nomina();
        System.out.println("horas trabajadas: ");
        nomina.setHorasTrabajadas(scNum.nextInt());
        
        String fecha = Nomina.establecerFechaActual();
        nomina.setFechaNomina(fecha);
        
        System.out.println("Sueldo sin impuestos: ");
        nomina.setSueldoSinImpuestos(scNum.nextDouble());
        
        System.out.println("Sueldo total: ");
        nomina.setSueldoTotal(scNum.nextDouble());
        
        System.out.println(nomina.toString());
        return nomina;
    }

}//fin clase nomina
