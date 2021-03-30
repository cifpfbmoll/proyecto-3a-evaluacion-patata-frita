
import java.util.Scanner;
import proyecto.pkg3a.evaluacion.patata.frita.Factura;
import proyecto.pkg3a.evaluacion.patata.frita.Nomina;
import proyecto.pkg3a.evaluacion.patata.frita.Reserva;

/**
 *
 * @author Marat Rafael
 */
public class Test_FacturaNominaReserva {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scByte = new Scanner(System.in);
        boolean salir = false;

        do {
            System.out.println("menu");
            System.out.println("1 crear factura");
            System.out.println("2 crear nomina");
            System.out.println("3 crear reserva");
            System.out.println("4 salir");
            byte opcionMenu;
            opcionMenu = scByte.nextByte();
            switch (opcionMenu) {
                case 1:
                    Factura.crearFactura();
                    break;
                case 2:
                    Nomina.crearNomina();
                    break;
                case 3:
                    Reserva.crearReserva();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("opciones disponibles de 1 a 4");
            }
        } while (!salir);

    }
}
