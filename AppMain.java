

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
                System.out.println("1 factura");
                System.out.println("2 nomina");
                System.out.println("3 reserva");
                System.out.println("4 concesionario");
                System.out.println("5 motor");
                System.out.println("6 cliente");
                System.out.println("7 persona");
                System.out.println("8 empleado");
                System.out.println("9 taller");
                System.out.println("10 opcion");
                System.out.println("11 vehiculo");
                System.out.println("0 Salir");
                opcion = Utils.kInt();
                switch (opcion) {
                    case 1: // FACTURA
                        volver = false;
                        do {
                            System.out.println(hr);
                            System.out.println("1 Crear objeto de factura y insertar en BBDD");
                            System.out.println("2 Buscar factura en BBDD");
                            System.out.println("3 Modificar factura en BBDD");
                            System.out.println("4 Borrar factura en BBDD");
                            System.out.println("5 Mostrar todos facturas en BBDD");
                            System.out.println("6 Relacionar factura con reserva ID");
                            System.out.println("7 Relacionar factura con venta ID");
                            System.out.println("8 Relacionar factura con vehiculo ID");
                            System.out.println("0 Volver Menu Principal");
                            System.out.println(hr);
                            opcion = Utils.kInt();
                            switch (opcion) {
                                case 1:
                                    System.out.println("creamos objeto de factura y lo guardamos");
                                    Factura f = Factura.crearFactura();
                                    Factura.insertarObjetoFacturaBBDD(f);
                                    break;
                                case 2:
                                    System.out.println("Indica ID factura para comprobar si existe BBDD");
                                    int buscarFactura = Utils.kInt();
                                    boolean posicionFactura = Factura.buscarFacturaBBDD(buscarFactura);
                                    if (posicionFactura) {
                                        System.out.println("existe");
                                    } else {
                                        System.out.println("No existe");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Indica ID de la factura que desea modificar");
                                    int IDFacturaModificar = Utils.kInteger();
                                    System.out.println("nueva fecha, formato dd-MM-yyyy");
                                    String fechaModificar = Utils.kString();
                                    System.out.println("nuevo coste");
                                    float costeModificar = Utils.kFloat();
                                    System.out.println("nuevo trabajo realizado");
                                    String trabajosModificar = Utils.kString();
                                    Factura.modificarFacturaBBDD(IDFacturaModificar, trabajosModificar, costeModificar, fechaModificar);
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
                                case 6:
                                    System.out.println("Relacionamos una FACTURA con una RESERVA ");
                                    System.out.println("ID de la FACTURA");
                                    int facturaReserva = Utils.kInt();
                                    System.out.println("ID de la RESERVA");
                                    int ReservaID = Utils.kInt();
                                    Factura.relacionarFacturaConReserva(facturaReserva, ReservaID);
                                    break;


                                case 7:
                                    System.out.println("Relacionamos una FACTURA con una VENTA");
                                    System.out.println("ID de la FACTURA");
                                    int facturaVenta = Utils.kInt();
                                    System.out.println("ID de la VENTA");
                                    int VentaID = Utils.kInt();
                                    Factura.relacionarFacturaConVenta(facturaVenta, VentaID);
                                    break;
                                case 8:
                                    System.out.println("Relacionamos una FACTURA con un VEHICULO ");
                                    System.out.println("ID de la FACTURA");
                                    int facturaVehiculo = Utils.kInt();
                                    System.out.println("ID de la RESERVA");
                                    int vehiculoID = Utils.kInt();
                                    Factura.relacionarFacturaConVenta(facturaVehiculo, vehiculoID);
                                    break;
                                case 0:
                                    volver = true;
                                    break;
                            }
                        } while (!volver);
                        break;

                    case 2: // NOMINA
                        volver = false;
                        do {
                            System.out.println(hr);
                            System.out.println("1 Crear objeto de nomina y insertar en BBDD");
                            System.out.println("2 Buscar nomina en BBDD");
                            System.out.println("3 Modificar nomina en BBDD");
                            System.out.println("4 Borrar nomina en BBDD");
                            System.out.println("5 Mostrar todos nominas en BBDD");
                            System.out.println("6 Relacionar una NOMINA con un EMPLEADO");
                            System.out.println("0 Volver Menu Principal");
                            System.out.println(hr);
                            opcion = Utils.kInt();
                            switch (opcion) {
                                case 1:
                                    System.out.println("creamos objeto de nomina y lo guardamos");
                                    Nomina n = Nomina.crearNomina();
                                    Nomina.insertarDatosNominaBBDD(n);
                                    break;
                                case 2:
                                    System.out.println("Indica ID nomina para comprobar si existe BBDD");
                                    int buscarNomina = Utils.kInt();
                                    boolean posicionNomina = Nomina.buscarNominaBBDD(buscarNomina);
                                    if (posicionNomina) {
                                        System.out.println("existe");
                                    } else {
                                        System.out.println("No existe");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Indica ID de la nomina que desea modificar");
                                    int IdNominaModificar = Utils.kInt();
                                    System.out.println("Horas de trabajo: ");
                                    int horasTrabajo = Utils.kInt();
                                    System.out.println("Sueldo total , bruto: ");
                                    float sueldoTotal = Utils.kFloat();
                                    float sueldoSinImpuesto = sueldoTotal - (sueldoTotal * Utils.IMPUESTO);
                                    System.out.println("Fecha con formato DD-MM-YYYY");
                                    String fecha = Utils.kString();
                                    Nomina.modificarNominaBBDD(IdNominaModificar, horasTrabajo, sueldoTotal, sueldoSinImpuesto, fecha);
                                    break;
                                case 4:
                                    System.out.println("Indica ID de la nomina que desea borrar");
                                    int idNominaBorrar = Utils.kInteger();
                                    Nomina.borrarNomina(idNominaBorrar);
                                    break;
                                case 5:
                                    System.out.println("mostrar todos nominas");
                                    Nomina.mostrarTodasNominas();
                                    break;
                                case 6:
                                    System.out.println("ID DE LA NOMINA que desea añadir al empleado");
                                    int nominaEmpleado = Utils.kInt();
                                    System.out.println("NIF  del empleado ");
                                    String empleadoNIF = Utils.kString();
                                    Nomina.relacionarNominaConEmpleado(nominaEmpleado, empleadoNIF);
                                    break;
                                case 0:
                                    volver = true;
                                    break;
                            }
                        } while (!volver);
                        break;
                    case 3:// RESERVA
                        volver = false;
                        do {
                            System.out.println(hr);
                            System.out.println("1 Crear objeto de reserva y insertar en BBDD");
                            System.out.println("2 Buscar reserva en BBDD");
                            System.out.println("3 Modificar reserva en BBDD");
                            System.out.println("4 Borrar reserva en BBDD");
                            System.out.println("5 Mostrar todos reservas en BBDD");
                            System.out.println("6 Relacionar Reserva con Taller");
                            System.out.println("7 Relacionar Reserva con Cliente");
                            System.out.println("0 Volver Menu Principal");
                            System.out.println(hr);
                            opcion = Utils.kInt();
                            switch (opcion) {
                                case 1:
                                    System.out.println("creamos objeto de reserva y lo guardamos");
                                    Reserva r = Reserva.crearReserva();
                                    Reserva.insertarDatosReservaBBDD(r);
                                    break;
                                case 2:
                                    System.out.println("Indica ID reseva para comprobar si existe BBDD");
                                    int buscarReserva = Utils.kInt();
                                    boolean posicionReserva = Reserva.buscarReservaBBDD(buscarReserva);
                                    if (posicionReserva) {
                                        System.out.println("Existe");
                                    } else {
                                        System.out.println("No existe");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Indica ID de la reserva que desea modificar");
                                    int IdNominaModificar = Utils.kInt();
                                    System.out.println("Fecha con formato DD-MM-YYYY HH:MM:SS");
                                    String FechaHoraReserva = Utils.kString();
                                    System.out.println("Espacio reservado: ");
                                    int EspacioReservado = Utils.kInt();
                                    Reserva.modificarReservaBBDD(IdNominaModificar, FechaHoraReserva, EspacioReservado);
                                    break;
                                case 4:
                                    System.out.println("Indica ID de la reserva que desea borrar");
                                    int borrarReserva = Utils.kInt();
                                    Reserva.borrarReserva(borrarReserva);
                                    break;
                                case 5:
                                    System.out.println("mostrar todos reservas");
                                    Reserva.mostrarTodasReservas();
                                    break;
                                case 6:
                                    System.out.println("Relacionamos una Reserva con un Taller ");
                                    System.out.println("ID de la RESERVA: ");
                                    int reservaTaller = Utils.kInt();
                                    System.out.println("ID del TALLER: ");
                                    int TallerId = Utils.kInt();
                                    Reserva.relacionarReservaConTaller(reservaTaller, TallerId);
                                    break;
                                case 7:
                                    System.out.println("Relacionamos una Reserva con un Taller ");
                                    System.out.println("ID de la RESERVA: ");
                                    int reservaCliente = Utils.kInt();
                                    System.out.println("NIF del Cliente: ");
                                    String ClienteNIF = Utils.kString();
                                    Reserva.relacionarReservaConCliente(reservaCliente, ClienteNIF);
                                    break;
                                case 0:
                                    volver = true;
                                    break;
                            }
                        } while (!volver);
                        break;
                    case 4:// CONCESIONARIO
                        volver = false;
                        do {
                            System.out.println(hr);
                            System.out.println("1 Crear objeto de concesionario y insertar en BBDD");
                            System.out.println("2 Buscar concesionario en BBDD");
                            System.out.println("3 Modificar concesionario en BBDD");
                            System.out.println("4 Borrar concesionario en BBDD");
                            System.out.println("5 Mostrar todos concesionarios en BBDD");
                            System.out.println("0 Volver Menu Principal");
                            System.out.println(hr);
                            opcion = Utils.kInt();
                            switch (opcion) {
                                case 1:
                                    System.out.println("creamos objeto de concesionario y lo guardamos");
                                    Concesionario c = Concesionario.crearConcesionario();
                                    Concesionario.insertarDatosConcesionarioBBDD(c);
                                    break;
                                case 2:
                                    System.out.println("Indica ID concesionario para comprobar si existe BBDD");
                                    int buscarConcesionario = Utils.kInt();
                                    int posicionConcesionario = Concesionario.buscarConcesionarioBBDD(buscarConcesionario);
                                    if (posicionConcesionario != -1) {
                                        System.out.println("Existe");
                                    } else {
                                        System.out.println("No existe");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Indica ID del concesionario que desea modificar");
                                    int IDConcesionarioModificar = Utils.kInt();
                                    System.out.println("Ubicacion: ");
                                    String ubicacionConcesionario = Utils.kString();
                                    System.out.println("Nombre: ");
                                    String nombreConcesionario = Utils.kString();
                                    System.out.println("Telefono: ");
                                    int telefonoConcesionario = Utils.kInt();
                                    Concesionario.modificarConcesionarioBBDD(IDConcesionarioModificar, ubicacionConcesionario, nombreConcesionario, telefonoConcesionario);
                                    break;
                                case 4:
                                    System.out.println("Indica ID del concesionario que desea borrar");
                                    int borrarReserva = Utils.kInt();

                                    break;
                                case 5:
                                    System.out.println("mostrar todos concesionarios");

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
