package proyecto.pkg3a.evaluacion.patata.frita;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Marat Rafael
 */
public class Factura {

    //atributos
    private String fechaFactura;
    private double costeFactura;
    private String trabajoRealizado;

    //constructor vacio
    public Factura() {
    }

    //constructor con todos atributos
    public Factura(String fechaFactura, double costeFactura, String trabajoRealizado) {
        this.fechaFactura = fechaFactura;
        this.costeFactura = costeFactura;
        this.trabajoRealizado = trabajoRealizado;
    }

    //constructor copia
    public Factura(Factura factura) {
        this.fechaFactura = factura.getFechaFactura();
        this.costeFactura = factura.getCosteFactura();
        this.trabajoRealizado = factura.getTrabajoRealizado();
    }

    //setter/getter
    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getCosteFactura() {
        return costeFactura;
    }

    /**
     * setter setCosteFactura con control que no se introduce valor negativo o
     * zero
     *
     * @param costeFactura
     */
    public void setCosteFactura(double costeFactura) {
        Scanner sc = new Scanner(System.in);
        this.costeFactura = costeFactura;
        while (costeFactura <= 0) {
            System.out.println("no puede insertar coste negativo");
            costeFactura = sc.nextDouble();
        }
        this.costeFactura = costeFactura;
    }

    public String getTrabajoRealizado() {
        return trabajoRealizado;
    }

    public void setTrabajoRealizado(String trabajoRealizado) {
        this.trabajoRealizado = trabajoRealizado;
    }

    @Override
    public String toString() {
        return "Factura{" + "fechaFactura=" + fechaFactura + ", costeFactura=" + costeFactura + ", trabajoRealizado=" + trabajoRealizado + '}';
    }


    /**
     * metodo estatico para crear una factura
     *
     * @return objeto Factura
     */
    public static Factura crearFactura() throws InputMismatchException {
        Factura factura = new Factura();

        String fechaFactura = Util.establecerFechaActual();
        factura.setFechaFactura(fechaFactura);

        System.out.println("Coste: ");
        Scanner sc = new Scanner(System.in);
        double coste = sc.nextDouble();
        factura.setCosteFactura(coste);
        sc.nextLine();

        System.out.println("Trabajos realizados: ");
        String trabajos = sc.nextLine();
        factura.setTrabajoRealizado(trabajos);

        //toString para comprobar, se puede borrar
        System.out.println(factura.toString());

        return factura;
    }// fin metodo crearFactura

}// fin class factura
