
/*
Crear un main, un menú simple por consola que permita:
1- Crear nuevos objetos (nuevas instancias de las clases)
2- Llamar a las funciones de las clases (especialmente la posibilidad de hacer selects o guardar los datos)
3- Posibilidad de borrar datos de la base de datos (usar la funcion DELETE que hara Jose Luis)
 */
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

        boolean salir = false;
        boolean volver = false;
        int opcion;
        
        //Login.initGui();

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
                    case 1:
                        manageFactura();
                        break;
                    case 2:
                        manageNomina();
                        break;
                    case 3:
                        manageReserva();
                        break;
                    case 4:
                        manageConcesionario();
                        break;
                    case 5:
                        Reserva r = Reserva.buscarReservaBBDD(1);
                        System.out.println(r.toString());
                        break;
                    case 6:
                        //probando cliente
                        boolean e = Cliente.existsInDB("22222222x");
                        if(e) {
                            System.out.println("si");
                        }else{
                            System.out.println("NO");
                        }
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

    /**
     * metodo estatico para manejar concesionario
     */
    private static void manageConcesionario() {
        boolean volver;
        int opcion;
        volver = false;
        String hr = "***********************************************************";
        do {
            System.out.println(hr);
            System.out.println("1 Crear objeto de concesionario y insertar en BBDD");
            System.out.println("2 comprobar si concesionario existe en BBDD");
            System.out.println("3 Modificar concesionario en BBDD");
            System.out.println("4 Borrar concesionario en BBDD");
            System.out.println("5 Mostrar todos concesionarios en BBDD");
            System.out.println("6 Relacionar un concesionario con un taller");
            System.out.println("7 Relacionar un concesionario con una venta");
            System.out.println("8 Buscar concesionario en BBDD");
            System.out.println("0 Volver Menu Principal");
            System.out.println(hr);
            opcion = Utils.kInt();
            switch (opcion) {
                case 1:
                    System.out.println("creamos objeto de concesionario y lo guardamos");
                    Concesionario c = Concesionario.crearConcesionario();
                    c.insertarDatosConcesionarioBBDD();
                    break;
                case 2:
                    System.out.println("Indica ID concesionario para comprobar si existe BBDD");
                    int existConcesionario = Utils.kInt();
                    boolean confirmarConcesionario = Concesionario.existConcesionario(existConcesionario);
                    if (confirmarConcesionario) {
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
                    Concesionario.modificarConcesionario(IDConcesionarioModificar, ubicacionConcesionario, nombreConcesionario, telefonoConcesionario);
                    break;
                case 4:
                    System.out.println("Indica ID del concesionario que desea borrar");
                    int borrarConcesionario = Utils.kInt();
                    Concesionario.borrarConcesionario(borrarConcesionario);
                    break;
                case 5:
                    System.out.println("mostrar todos concesionarios");
                    Concesionario.mostrarConcesionario();
                    break;
                case 6:
                    System.out.println("Id del concesionario: ");
                    int relacionConcesionarioTaller = Utils.kInt();
                    System.out.println("Id del Taller: ");
                    int TallerId = Utils.kInt();
                    //Concesionario.relacionarConcesionarioConTaller(relacionConcesionarioTaller, TallerId);
                    break;
                case 7:
                    System.out.println("Id del concesionario: ");
                    int relacionConcesionarioVenta = Utils.kInt();
                    System.out.println("Id del Venta: ");
                    int VentaId = Utils.kInt();
                    //Concesionario.relacionarConcesionarioConVenta(relacionConcesionarioVenta, VentaId);
                    break;
                case 8:
                    System.out.println("Id  concsionario: ");
                    int buscarConcesionario = Utils.kInt();
                    Concesionario.buscarConcesionario(buscarConcesionario);
                    break;
                case 0:
                    volver = true;
                    break;
            }
        } while (!volver);
    }

    private static void manageReserva() throws IllegalArgumentException {
        boolean volver;
        int opcion;
        // RESERVA

        volver = false;
        String hr = "***********************************************************";
        do {

            System.out.println(hr);
            System.out.println("1 Crear objeto de reserva y insertar en BBDD");
            System.out.println("2 Comprobar si existe reserva en BBDD");
            System.out.println("3 Modificar reserva en BBDD");
            System.out.println("4 Borrar reserva en BBDD");
            System.out.println("5 Mostrar todos reservas en BBDD");
            System.out.println("6 Relacionar Reserva con Taller");
            System.out.println("7 Relacionar Reserva con Cliente");
            System.out.println("8 Buscar reserva en BBDD");
            System.out.println("0 Volver Menu Principal");
            System.out.println(hr);
            opcion = Utils.kInt();
            switch (opcion) {
                case 1:
                    System.out.println("creamos objeto de reserva y lo guardamos");
                    Reserva r = Reserva.crearReserva();
                    r.insertarDatosReservaBBDD();
                    break;
                case 2:
                    System.out.println("Indica ID reseva para comprobar si existe BBDD");
                    int existReserva = Utils.kInt();
                    boolean confirmarReserva = Reserva.existReservaBBDD(existReserva);
                    if (confirmarReserva) {
                        System.out.println("Existe");
                    } else {
                        System.out.println("No existe");
                    }
                    break;

                case 3:
                    System.out.println("Indica ID de la reserva que desea modificar");
                    int IdNominaModificar = Utils.kInt();
                    System.out.println("Fecha con formato DD-MM-YYYY HH:MM");
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
                    System.out.println("Relacionamos una Reserva con un Cliente ");
                    System.out.println("ID de la RESERVA: ");
                    int reservaCliente = Utils.kInt();
                    System.out.println("NIF del Cliente: ");
                    String ClienteNIF = Utils.kString();
                    Reserva.relacionarReservaConCliente(reservaCliente, ClienteNIF);
                    break;
                case 8:
                    System.out.println("Id de la reserva: ");
                    int buscarReserva = Utils.kInt();
                    Reserva.buscarReservaBBDD(buscarReserva);
                    break;
                case 0:
                    volver = true;
                    break;
            }
        } while (!volver);
    }

    private static void manageNomina() throws IllegalArgumentException {
        boolean volver;
        int opcion;
        // NOMINA

        volver = false;
        String hr = "***********************************************************";
        do {
            System.out.println(hr);
            System.out.println("1 Crear objeto de nomina y insertar en BBDD");
            System.out.println("2 Comprobar si existe nomina en BBDD");
            System.out.println("3 Modificar nomina en BBDD");
            System.out.println("4 Borrar nomina en BBDD");
            System.out.println("5 Mostrar todos nominas en BBDD");
            System.out.println("6 Relacionar una NOMINA con un EMPLEADO");
            System.out.println("7 Buscar Nomina en BBDD");
            System.out.println("0 Volver Menu Principal");
            System.out.println(hr);
            opcion = Utils.kInt();
            switch (opcion) {
                case 1:
                    System.out.println("creamos objeto de nomina y lo guardamos");
                    Nomina n = Nomina.crearNomina();
                    n.insertarDatosNominaBBDD();
                    break;
                case 2: 
                    System.out.println("Indica ID nomina para comprobar si existe BBDD");
                    int existNomina = Utils.kInt();
                    boolean confirmarNomina = Nomina.existNominaBBDD(existNomina);
                    if (confirmarNomina) {
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
                    System.out.println("Nif del empleado : ");
                    String EmpleadoNif=Utils.kString();
                    Nomina.modificarNominaBBDD(IdNominaModificar, horasTrabajo, sueldoTotal, sueldoSinImpuesto, fecha, EmpleadoNif);
                    break;
                case 4:
                    System.out.println("Indica ID de la nomina que desea borrar");
                    int idNominaBorrar = Utils.kInteger();
                    Nomina.borrarNominaBBDD(idNominaBorrar);
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
                case 7:
                    System.out.println("ID de la nomina: ");
                    int buscarNomina = Utils.kInt();
                    Nomina.buscarNominaBBDD(buscarNomina);
                    break;
                case 0:
                    volver = true;
                    break;
            }
        } while (!volver);
    }

    private static void manageFactura() throws IllegalArgumentException {
        boolean volver;
        int opcion;
        // FACTURA

        volver = false;
        String hr = "***********************************************************";
        do {
            System.out.println(hr);
            System.out.println("1 Crear objeto de factura de reserva y insertar en BBDD");
            System.out.println("11 Crear objeto de factura de venta - vehiculo y insertar en BBDD");
            System.out.println("2 Comprobar si existe factura en BBDD");
            System.out.println("3 Modificar factura en BBDD");
            System.out.println("4 Borrar factura en BBDD");
            System.out.println("5 Mostrar todos facturas en BBDD");
            System.out.println("6 Relacionar factura con reserva ID");
            System.out.println("7 Relacionar factura con venta ID");
            System.out.println("8 Relacionar factura con vehiculo ID");
            System.out.println("9 Buscar factura en BBDD");
            System.out.println("0 Volver Menu Principal");
            System.out.println(hr);
            opcion = Utils.kInt();
            switch (opcion) {
                case 1:
                    System.out.println("creamos objeto de factura de RESERVA ,y lo guardamos");
                    Factura f = Factura.crearFacturaConReserva();
                    Factura.insertarObjetoFacturaBBDD(f);
                    break;
                case 11:
                    System.out.println("creamos objeto de factura de VENTA-VEHICULO ,y lo guardamos");
                    Factura ff = Factura.crearFacturaConVentaVehiculo();
                    Factura.insertarObjetoFacturaBBDD(ff);
                    break;
                case 2:
                    System.out.println("Indica ID factura para comprobar si existe BBDD");
                    int existFactura = Utils.kInt();
                    boolean confirmarFactura = Factura.existFacturaBBDD(existFactura);
                    if (confirmarFactura) {
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
                case 9:
                    System.out.println("Id de la factura: ");
                    int buscarFactura = Utils.kInt();
                    Factura.buscarFacturaBBDD(buscarFactura);
                    break;
                case 0:
                    volver = true;
                    break;
            }
        } while (!volver);
    }
}
