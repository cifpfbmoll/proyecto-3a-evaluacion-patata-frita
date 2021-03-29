/**
 * VENTAS
 * Esta clase contiene las ventas de cada uno de los concesionarios.
 * ¿DESCRIPCION DE LA INTERFICIE QUE INCLUYE?
 * ¿FUNCIONAMIENTO,LIMITACIONES,VARIABLES UTILIZADAS...?
 * @author José Luis Cardona
 * @version 29/03/2021
 */
public class Venta {
    private int id;
    private int horario;

    public Venta() {
    }

    public Venta(int id, int horario) {
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

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", horario=" + horario +
                '}';
    }
}
