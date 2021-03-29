/**
 * TALLERES
 * Esta clase almacena la información de los diversos talleres de cada concesionario.
 * ¿DESCRIPCION DE LA INTERFICIE QUE INCLUYE?
 * ¿FUNCIONAMIENTO,LIMITACIONES,VARIABLES UTILIZADAS...?
 * @author José Luis Cardona
 * @version 29/03/2021
 */
public class Taller {
    private int id;
    private int espacios;
    private int horario;

    public Taller() {
    }

    public Taller(int id, int espacios, int horario) {
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

    public void setEspacios(int espacios) {
        this.espacios = espacios;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Taller{" +
                "id=" + id +
                ", espacios=" + espacios +
                ", horario=" + horario +
                '}';
    }
}
