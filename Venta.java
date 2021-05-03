package patatafrita;

import java.sql.SQLException;

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

    //TODO: Arreglar javadocs
    /**
     *
     * @return Objeto venta con sus datos.
     */
    public static Venta crearVenta() {
        Venta venta = new Venta();
        try {
            System.out.println("Escribe el horario en formato hh:mm-hh:mm : ");
            venta.setHorario(Utils.kString());
        } catch (Exception ex) {
            System.out.println("¡ERROR! el objeto no se pudo crear.");
        }
        return venta;
    }

    /**
     *
     * @param venta
     */
    public static void guardarDatosVenta(Venta venta) {
        String consulta = "INSERT INTO VENTA (HORARIO) VALUES (?)";
        try {
            Utils.connection = Utils.conectarBBDD();
            Utils.prst = Utils.connection.prepareStatement(consulta);
            Utils.prst.setString(1, venta.getHorario());
            Utils.prst.executeUpdate();
            System.out.println("Datos guardados con éxito.");
        } catch (SQLException ex) {
            System.out.println("¡ERROR! no se pudieron guardar los datos de la venta en la BBDD.");
        } finally {
            if (Utils.prst != null) {
                try {
                    Utils.prst.close();//cierra el objeto Statement llamado prst
                } catch (SQLException throwables) {
                    System.out.println("¡ERROR! no se ha podido cerrar la conexion.");
                }
            }
        }
    }
}
