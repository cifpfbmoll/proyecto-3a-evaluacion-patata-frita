import java.util.Date;

/**
 * Clase vehiculo donde se guardan los datos de todos los vehiculos
 * Contiene el conjunto de variables necesarias, 2 enums para la clase y el estado
 * y un Motor @see #Motor.java
 * @author Joan
 */
public class Vehiculo {

    public enum clase {
        SUV,
        Sedan,
        Sport,
        Coupe,
        Hatchback,
        Convertible,
        Minivan,
        Pickup
    }
    private Motor motor;
    public enum estado {
        Vendido,
        Venta,
        Alquilado
    }

    private Date fecha_fabricacion;
    private String bastidor;
    private int kilometraje;
    private int autonomia;
    private int puertas;
    private int asientos;
    private String extras;
    private String color;
    private String marca;
    private String modelo;

    public Vehiculo(){

    }

    public Vehiculo(Motor motor, Date fecha_fabricacion, String bastidor, int kilometraje, int autonomia, int puertas, int asientos, String extras, String color, String marca, String modelo) {
        this.motor = motor;
        this.fecha_fabricacion = fecha_fabricacion;
        this.bastidor = bastidor;
        this.kilometraje = kilometraje;
        this.autonomia = autonomia;
        this.puertas = puertas;
        this.asientos = asientos;
        this.extras = extras;
        this.color = color;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Vehiculo(Vehiculo vec){
        this.motor = vec.getMotor();
        this.fecha_fabricacion = vec.getFecha_fabricacion();
        this.bastidor = vec.getBastidor();
        this.kilometraje = vec.getKilometraje();
        this.autonomia = vec.getAutonomia();
        this.puertas = vec.getPuertas();
        this.asientos = vec.getAsientos();
        this.extras = vec.getExtras();
        this.color = vec.getColor();
        this.marca = vec.getMarca();
        this.modelo = vec.getModelo();
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Date getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(Date fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }

    public String getBastidor() {
        return bastidor;
    }

    public void setBastidor(String bastidor) {
        this.bastidor = bastidor;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) throws IllegalArgumentException {
        if(kilometraje<0){
            throw new IllegalArgumentException("El kilometraje no puede ser inferior a 0");
        }else{
            this.kilometraje = kilometraje;
        }
    }

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) throws IllegalArgumentException{
        if(autonomia<0){
            throw new IllegalArgumentException("La autonomia no puede ser inferior a 0");
        }else{
            this.autonomia = autonomia;
        }
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) throws IllegalArgumentException{
        if(puertas<=0){
            throw new IllegalArgumentException("No puede haber menos de 1 puerta");
        }else{
            this.puertas = puertas;
        }
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) throws IllegalArgumentException{
        if(puertas<=0){
            throw new IllegalArgumentException("No puede haber menos de 1 asiento");
        }else {
            this.asientos = asientos;
        }
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "motor=" + motor +
                ", fecha_fabricacion=" + fecha_fabricacion +
                ", bastidor='" + bastidor + '\'' +
                ", kilometraje=" + kilometraje +
                ", autonomia=" + autonomia +
                ", puertas=" + puertas +
                ", asientos=" + asientos +
                ", extras='" + extras + '\'' +
                ", color='" + color + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
