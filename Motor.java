/**
 * Clase motor donde se guardan los datos de los distintos motores
 * Contiene el conuunto de variables necesarias y un enum.
 * @author Joan
 */
public class Motor {

    public enum tipoMotor{
        Gasolina95,
        Gasolina98,
        Diesel,
        Electrico
    }
    private float potencia;
    private float par;
    private float cilindrada;  //Únicamente usado en motores no eléctricos
    private float num_motores; //Únicamente usado en motores eléctricos

    /**
     * Constructor vacío
     */
    public Motor(){

    }

    /**
     * Constructor con todos los datos por parámetro
     * @param potencia
     * @param par
     * @param cilindrada
     * @param num_motores
     */
    public Motor(float potencia, float par, float cilindrada, float num_motores) {
        this.potencia = potencia;
        this.par = par;
        this.cilindrada = cilindrada;
        this.num_motores = num_motores;
    }

    /**
     * Constructor copia
     * @param motor
     */
    public Motor(Motor motor) {
        this.potencia = motor.getPotencia();
        this.par = motor.getPar();
        this.cilindrada = motor.getCilindrada();
        this.num_motores = motor.getNum_motores();
    }

    public float getPotencia() {
        return potencia;
    }

    public void setPotencia(float potencia) throws IllegalArgumentException{
        if(potencia<0){
            throw new IllegalArgumentException("Un motor no puede tener potencia negativa");
        }else {
            this.potencia = potencia;
        }
    }

    public float getPar() {
        return par;
    }

    public void setPar(float par) throws IllegalArgumentException {
        if(par<0){
            throw new IllegalArgumentException("El par no puede ser negativo");
        }else {
            this.par = par;
        }
    }

    public float getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(float cilindrada) throws IllegalArgumentException{
        if(cilindrada<0){
            throw new IllegalArgumentException("El par no puede ser negativo");
        }else {
            this.cilindrada = cilindrada;
        }
    }

    public float getNum_motores() {
        return num_motores;
    }

    public void setNum_motores(float num_motores) throws IllegalArgumentException{
        if(num_motores<1 || num_motores>8){
            throw new IllegalArgumentException("Debe haber entre 1 y 8 motores");
        }else {
            this.num_motores = num_motores;
        }
    }

    @Override
    public String toString() {
        return "Motor{" +
                "potencia=" + potencia +
                ", par=" + par +
                ", cilindrada=" + cilindrada +
                ", num_motores=" + num_motores +
                '}';
    }
}
