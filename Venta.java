/**
 * VENTAS
 * Esta clase contiene las ventas de cada uno de los concesionarios.
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

    // TODO: Pasar a getters
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

    // TODO: Revisar esto (funciona el for), no se checkea bien el error
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
        return "Venta{" +
                "id=" + id +
                ", horario='" + horario + '\'' +
                '}';
    }
}
