/**
 * CONCESIONARIOS
 * Esta clase  guarda la información sobre los diferentes concesionarios del proyecto.
 * ¿DESCRIPCION DE LA INTERFICIE QUE INCLUYE? (Se refiere al tema de interficies que Rafa dijo que no daríamos? si es asi no pongo esa info.)
 * ¿FUNCIONAMIENTO,LIMITACIONES,VARIABLES UTILIZADAS...? (Hasta que no hayan metodos y tal no puedo poner esa info.)
 * @author José Luis Cardona
 * @version 29/03/2021
 */
public class Concesionario {
    private int id;
    private String ubicacion;
    private String nombre;
    private int telefono;

    public Concesionario() {
    }

    public Concesionario(int id, String ubicacion, String nombre, int telefono) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Concesionario(Concesionario copia) {
        this.id = copia.id;
        this.ubicacion = copia.ubicacion;
        this.nombre = copia.nombre;
        this.telefono = copia.telefono;
    }

    public int getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Concesionario{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
