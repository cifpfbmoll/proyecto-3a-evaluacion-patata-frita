/**
 * TALLERES
 * Esta clase almacena la información de los diversos talleres de cada concesionario
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

    // TODO: Pasar a getters
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

    // TODO: Revisar (funciona, solo entiende como va) y solucionar el checkeo de errores
    public void setHorario(String horario) throws IllegalArgumentException{
        //ENTENDER BIEN ESTO
        String[] listaHorarios=horario.split("-");
        String[] lista2Horarios=listaHorarios[0].split(":");
        String[] lista3Horarios=listaHorarios[1].split(":");
        int[] numHorarios = new int[3];
        int pointer = 0;
        for (int i=0;i<numHorarios.length/2;i++){
            numHorarios[pointer]=Integer.parseInt(lista2Horarios[i]);
            pointer++;
            numHorarios[pointer]=Integer.parseInt(lista3Horarios[i]);
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
