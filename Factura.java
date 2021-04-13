import java.util.Scanner;

/**
 * Clase Factura
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

    public void setCosteFactura(double costeFactura) throws IllegalArgumentException {

        this.costeFactura = costeFactura;
        if (costeFactura < 0) {
            throw new IllegalArgumentException("Valor coste factura no valido");
        }
    }

    public String getTrabajoRealizado() {
        return trabajoRealizado;
    }

    public void setTrabajoRealizado(String trabajoRealizado) {
        this.trabajoRealizado = trabajoRealizado;
    }

    // toString
    @Override
    public String toString() {
        return "Factura {" + "fechaFactura=" + fechaFactura + ", costeFactura=" + costeFactura + ", trabajoRealizado=" + trabajoRealizado + '}';
    }

    /**
     * metodo estatico para crear una factura
     *
     * @return objeto Factura
     */
    public static Factura crearFactura() throws IllegalArgumentException {
        Factura factura = new Factura();
        try {
            String fechaFactura = Utils.establecerFechaActual();
            factura.setFechaFactura(fechaFactura);

            System.out.println("Coste: ");
            // TODO: Cambiar el scanner por el scanner del utils, asi se comparte uno solo
            Scanner sc = new Scanner(System.in);
            factura.setCosteFactura(sc.nextDouble());
            sc.nextLine();

            System.out.println("Trabajos realizados: ");
            factura.setTrabajoRealizado(sc.nextLine());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return factura;
    }
}
