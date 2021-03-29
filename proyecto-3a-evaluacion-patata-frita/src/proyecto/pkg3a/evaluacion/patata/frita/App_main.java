package proyecto.pkg3a.evaluacion.patata.frita;

import java.util.Scanner;

/**
 *
 * @author Marat Rafael
 */
public class App_main {

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
            System.out.println("3 salir");
            byte opcionMenu;
            opcionMenu = scByte.nextByte();
            switch (opcionMenu) {
                case 1:
                    Factura.crearFactura();
                    break;
                case 2:
                    try {
                    Nomina.crearNomina();
                } catch (Exception e) {
                }

                break;
                case 3:
                    salir = true;
                    break;

            }
        } while (!salir);
    }
}
