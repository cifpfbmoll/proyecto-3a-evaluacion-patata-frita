import java.lang.reflect.Array;

/**
 * TALLERES
 * Esta clase almacena la información de los diversos talleres de cada concesionario.
 * ¿DESCRIPCION DE LA INTERFICIE QUE INCLUYE?
 * ¿FUNCIONAMIENTO,LIMITACIONES,VARIABLES UTILIZADAS...? (Hasta que no hayan metodos y tal no puedo poner esa info.)
 * @author José Luis Cardona
 * @version 1 - 29/03/2021
 */
public class Taller {
    private int id;
    private int espacios;
    private String horario;

    public Taller() {
    }

    public Taller(int id, int espacios, String horario) {
        this.id = id;
        this.espacios = espacios;
        this.horario = horario;
    }

    public Taller(Taller copia) {
        this.id = copia.id;
        this.espacios = copia.espacios;
        this.horario = copia.horario;
    }

    public int getId() {
        return id;
    }

    public int getEspacios() {
        return espacios;
    }

    public void setEspacios(int espacios) throws IllegalArgumentException{
        if (espacios<=0){
            throw new IllegalArgumentException("Número de espacio inválido.");
        }else {
            this.espacios = espacios;
        }
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
        return "Taller{" +
                "id=" + id +
                ", espacios=" + espacios +
                ", horario='" + horario + '\'' +
                '}';
    }
}
