package patatafrita;

import patatafrita.Utils;

public class MainTest {
    public static void main(String[] args) {
        Utils.conectarBBDD();

        boolean salir = false;
        boolean volver = false;
        int opcion;

        try {
            do {

                System.out.println("MENU");
                System.out.println("1 abrir archivo");
                System.out.println("2 cerrar archivo");
                System.out.println("3 leer archivo");
                System.out.println("4 escribir archivo");
                System.out.println("0 Salir");
                opcion = Utils.kInt();
                switch (opcion) {
                    case 1:
                        Utils.abrirArchivo();
                        break;
                    case 2:
                        Utils.cerrarArchivo();
                        break;
                    case 3:
                        Utils.leerArchivo();
                        break;
                    case 4:
                        Utils.escribirArchivo(Utils.lector.nextLine());
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
            System.out.println("¡ERROR!");
        }
    }
}
