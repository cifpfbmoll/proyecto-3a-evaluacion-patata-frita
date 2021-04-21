

/*
Crear un main, un menú simple por consola que permita:
1- Crear nuevos objetos (nuevas instancias de las clases)
2- Llamar a las funciones de las clases (especialmente la posibilidad de hacer selects o guardar los datos)
3- Posibilidad de borrar datos de la base de datos (usar la funcion DELETE que hara Jose Luis)
 */
import java.sql.*;

/**
 *
 * @author Marat Rafael
 */
public class AppMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Utils.conectarBBDD();

        //Factura.borrarFacturaBBDD(1);
        //Factura.insertarDatosFactura(3, "21/04/2021", 995, "sdsdsdsd");
        //Factura.mostrarTablaFacturaCompleta();
        //Factura.modificarFacturaBBDD(1, "20/04/2021", 255, "probando modificar");
        boolean salir = false;
        boolean volver = false;
        int opcion;
        String hr = "***********************************************************";

        try {
            do {

                System.out.println("MENU");
                System.out.println("1 cliente");
                System.out.println("2 concesionario");
                System.out.println("3 empleado");
                System.out.println("4 factura");
                System.out.println("5 motor");
                System.out.println("6 nomina");
                System.out.println("7 persona");
                System.out.println("8 reserva");
                System.out.println("9 taller");
                System.out.println("10 opcion");
                System.out.println("11 vehiculo");
                System.out.println("0 Salir");
                opcion = Utils.kInt();
                switch (opcion) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        volver = false;
                        do {
                            System.out.println(hr);
                            System.out.println("1 Crear objeto");
                            System.out.println("2 Buscar objeto en BBDD");
                            System.out.println("3 Modificar objeto en BBDD");
                            System.out.println("4 Borrar objeto en BBDD");
                            System.out.println("5 Mostrar todos en BBDD");
                            System.out.println("0 Volver Menu Principal");
                            System.out.println(hr);
                            opcion = Utils.kInt();
                            switch (opcion) {
                                case 1:
                                    System.out.println("crear objeto");
                                    Factura f = Factura.crearFactura();
                                    Factura.insertarObjetoFacturaBBDD(f);
                                    break;
                                case 2:
                                    System.out.println("Indica ID factura para comprobar si hay BBDD");
                                    int buscar = Utils.kInt();
                                    int pos = Factura.buscarFacturaBBDD(buscar);
                                    if (pos != -1) {
                                        System.out.println("existe");
                                    } else {
                                        System.out.println("No existe");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Indica ID de la factura que desea modificar");
                                    int IDFacturaModificar = Utils.kInteger();
                                    String fechaModificar = Utils.kString();
                                    double costeModificar = Utils.kDouble();
                                    String trabajosModificar=Utils.kString();
                                    Factura.modificarFacturaBBDD(IDFacturaModificar, fechaModificar, costeModificar, trabajosModificar);
                                    break;
                                case 4:
                                    System.out.println("Indica ID de la factura que desea borrar");
                                    int IDFacturaBorrar = Utils.kInteger();
                                    Factura.borrarFacturaBBDD(IDFacturaBorrar);
                                    break;
                                case 5:
                                    System.out.println("mostrar todos");
                                    Factura.mostrarTablaFacturaCompleta();
                                    break;
                                case 0:
                                    volver = true;
                            }
                        } while (!volver);
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 0:
                        salir = true;
                }
            } while (!salir);
        } catch (Exception e) {
            System.out.println("Error en menu");
        }

    }
}
