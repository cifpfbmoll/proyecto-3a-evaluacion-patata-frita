/**
 * CONCESIONARIOS
 * Esta clase  guarda la información sobre los diferentes concesionarios del proyecto.
 * @author José Luis Cardona
 * @version 1 - 29/03/2021
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

    //TODO: Cambiar a getters
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

    public void setUbicacion(String ubicacion) throws IllegalArgumentException{
        if (ubicacion.length()==0){
            throw new IllegalArgumentException("Ubicación no introducida.");
        }else {
            this.ubicacion = ubicacion;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws IllegalArgumentException{
        if (nombre.length()==0){
            throw new IllegalArgumentException("Nombre no introducido.");
        }else {
            this.nombre = nombre;
        }
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) throws IllegalArgumentException {
        if(telefono<999){
            throw new IllegalArgumentException("Número de teléfono inválido o no completo.");
        }else {
            this.telefono = telefono;
        }
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
