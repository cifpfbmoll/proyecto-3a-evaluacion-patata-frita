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

    public Venta(Venta copia) {
        this.id = copia.getId();
        this.horario = copia.getHorario();
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
        if (lista2Horarios.length + lista3Horarios.length != 4){
            throw new IllegalArgumentException("Demasiados numeros.");
        }else{
            try{
                int[] numHorarios = new int[3];
                int pointer = 0;
                for (int i=0;i<numHorarios.length/2;i++){
                    numHorarios[pointer]=Integer.parseInt(lista2Horarios[i]);
                    pointer++;
                    numHorarios[pointer]=Integer.parseInt(lista3Horarios[i]);
                    pointer++;
                }
            }catch (Exception ex){
                throw new IllegalArgumentException("Caracter/es inválido/s.");
            }
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
