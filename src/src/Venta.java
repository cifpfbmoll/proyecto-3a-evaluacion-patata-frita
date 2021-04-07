/**
 * VENTAS
 * Esta clase contiene las ventas de cada uno de los concesionarios.
 * ¿DESCRIPCION DE LA INTERFICIE QUE INCLUYE?
 * ¿FUNCIONAMIENTO,LIMITACIONES,VARIABLES UTILIZADAS...? (Hasta que no hayan metodos y tal no puedo poner esa info.)
 * @author José Luis Cardona
 * @version 1 - 29/03/2021
 */
public class Venta {
    private int id;
    private String horario;

    public Venta() {
    }

    public Venta(int id, String horario) {
        this.id = id;
        this.horario = horario;
    }

    public Venta(Venta copia) {
        this.id = copia.id;
        this.horario = copia.horario;
    }

    public int getId() {
        return id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) throws IllegalArgumentException{
        //ENTENDER BIEN ESTO
        String[] listaHorarios=horario.split("-");
        String[] lista2Horarios=listaHorarios[0].split(":");
        String[] lista3Horarios=listaHorarios[1].split(":");
        int[] numHorarios = new int[3];
        for (int i=0;i<numHorarios.length/2;i++){
            numHorarios[i]=Integer.parseInt(lista2Horarios[i]);
        }
        for (int i=0;i<numHorarios.length/2;i++){
            numHorarios[i]=Integer.parseInt(lista3Horarios[i]);
        }
        if (Integer.parseInt(lista2Horarios[0])==0){
            throw new IllegalArgumentException("Horario no válido.");
        }else{
            this.horario = horario;
        }
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", horario='" + horario + '\'' +
                '}';
    }
}