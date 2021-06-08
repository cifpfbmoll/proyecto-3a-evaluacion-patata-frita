public class MainMain {

    private static Cliente[] clienteList;
    private static Concesionario[] concesionarioList;
    private static Empleado[] empleadoList;
    private static Factura[] facturaList;
    private static Motor[] motorList;
    private static Nomina[] nominaList;
    private static Reserva[] reservaList;
    private static Taller[] tallerList;
    private static Vehiculo[] vehiculoList;
    private static Venta[] ventaList;

    private static void menu_clientes(String chosen_nif, String[] str_finder){
        boolean menu = true;
        int index;
        while(menu) {
            switch (Utils.kInt("1- Nuevo cliente\n2- Editar uno anterior\n3- Salir")){
                case 1:
                    Cliente cliente = Cliente.crearCliente();
                    if(!cliente.existsInDB()){
                        cliente.insertarClienteBBDD();
                        Cliente[] copia = clienteList;
                        for(int i=0;i< copia.length;i++){
                            copia[i]=clienteList[i];
                        }
                        clienteList = new Cliente[clienteList.length+1];
                        for(int i=0;i< copia.length;i++){
                            clienteList[i]=copia[i];
                        }
                        clienteList[clienteList.length-1] = cliente;
                        clienteList = Cliente.cargarTodosClientes();
                    }else{
                        System.out.println("Ese cliente ya existe");
                    }
                    break;
                case 2:
                    mostrarClientes();
                    chosen_nif = Utils.kString("Escribe el nif que quieras");
                    str_finder = new String[clienteList.length];
                    for (int i = 0; i < clienteList.length; i++) {
                        str_finder[i] = clienteList[i].getNif();
                    }
                    index = getStringIndex(str_finder, chosen_nif);
                    System.out.println("Que quieres hacer?\n" +
                            "1- Editar datos\n" +
                            "2- Borrar cliente\n" +
                            "3- Salir");
                    switch(Utils.kInt()){
                        case 1:
                            Cliente.modificarClienteBBDD(Utils.kString("Nombre"),Utils.kString("Apellidos"),Utils.kInt("Telefono"),Utils.kString("Domicilio"),clienteList[index].getPassword(),clienteList[index].getNif());
                            clienteList = Cliente.cargarTodosClientes();
                            break;
                        case 2:
                            clienteList[index].borrarClienteBBDD();
                            clienteList = Cliente.cargarTodosClientes();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    menu=false;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }

        }
    }

    private static void menu_empleados(String chosen_nif, String[] str_finder){
        boolean menu = true;
        int index;
        while(menu) {
            switch (Utils.kInt("1- Nuevo empleado\n2- Editar uno anterior\n3- Salir")){
                case 1:
                    Empleado empleado = Empleado.crearEmpleado();
                    if(!empleado.existsInDB()){
                        System.out.println("Creado correctamente");
                        empleado.insertarDatosEmpleadoBBDD();
                        Empleado[] copia = empleadoList;
                        for(int i=0;i< copia.length;i++){
                            copia[i]=empleadoList[i];
                        }
                        empleadoList = new Empleado[empleadoList.length+1];
                        for(int i=0;i< copia.length;i++){
                            empleadoList[i]=copia[i];
                        }
                        empleadoList[empleadoList.length-1] = empleado;
                        empleadoList = Empleado.cargarEmpleados();
                    }else{
                        System.out.println("Ese empleado ya existe");
                    }
                    break;
                case 2:
                    mostrarEmpleados();
                    chosen_nif = Utils.kString("Escribe el nif que quieras");
                    str_finder = new String[empleadoList.length];
                    for (int i = 0; i < empleadoList.length; i++) {
                        str_finder[i] = empleadoList[i].getNif();
                    }
                    index = getStringIndex(str_finder, chosen_nif);
                    System.out.println("Que quieres hacer?\n" +
                            "1- Editar datos\n" +
                            "2- Borrar empleado\n" +
                            "3- Salir");
                    switch(Utils.kInt()){
                        case 1:
                            Empleado.modificarEmpleadoBBDD(Utils.kString("Nombre"),Utils.kString("Apellidos"),Utils.kInt("Telefono"),Utils.kString("Domicilio"),Utils.kString("Puesto"),Utils.kInt("id venta"),Utils.kInt("id taller"),clienteList[index].getPassword(),clienteList[index].getNif());
                            empleadoList = Empleado.cargarEmpleados();
                            break;
                        case 2:
                            empleadoList[index].borrarEmpleadoBBDD();
                            empleadoList = Empleado.cargarEmpleados();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    menu=false;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }

        }
    }

    private static void menu_concesionarios(int chosen_id, int[] finder){
        boolean menu = true;
        int index;
        while(menu) {
            switch (Utils.kInt("1- Nuevo concesionario\n2- Editar uno anterior\n3- Salir")){
                case 1:
                    Concesionario con = Concesionario.crearConcesionario();
                    System.out.println("Creado correctamente");
                    con.insertarDatosConcesionarioBBDD();
                    Concesionario[] copia = concesionarioList;
                    for(int i=0;i< copia.length;i++){
                        copia[i]=concesionarioList[i];
                    }
                    concesionarioList = new Concesionario[concesionarioList.length+1];
                    for(int i=0;i< copia.length;i++){
                        concesionarioList[i]=copia[i];
                    }
                    concesionarioList[concesionarioList.length-1] = con;
                    concesionarioList = Concesionario.cargarConcesionarios();
                    break;
                case 2:
                    mostrarConcesionarios();
                    chosen_id = Utils.kInt("Escribe el id que quieras");
                    finder = new int[concesionarioList.length];
                    for (int i = 0; i < concesionarioList.length; i++) {
                        finder[i] = concesionarioList[i].getId();
                    }
                    index = getIndex(finder, chosen_id);
                    System.out.println("Que quieres hacer?\n" +
                            "1- Editar datos\n" +
                            "2- Borrar concesionario\n" +
                            "3- Salir");
                    switch(Utils.kInt()){
                        case 1:
                            Concesionario.modificarConcesionario(concesionarioList[index].getId(),Utils.kString("Nombre"),Utils.kString("Ubicacion"),Utils.kInt("Telefono"));
                            concesionarioList = Concesionario.cargarConcesionarios();
                            break;
                        case 2:
                            Concesionario.borrarConcesionario(concesionarioList[index].getId());
                            concesionarioList = Concesionario.cargarConcesionarios();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    menu=false;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }

        }
    }

    private static void menu_facturas(int chosen_id, int[] finder){
        boolean menu = true;
        int index;
        while(menu) {
            switch (Utils.kInt("1- Nueva factura\n2- Editar una anterior\n3- Salir")){
                case 1:
                    Factura fact = null;
                    if(Utils.kInt("1-Reserva\n2-Venta") == 1){
                        fact = Factura.crearFacturaConReserva();
                    }else{
                         fact = Factura.crearFacturaConVentaVehiculo();
                    }
                    System.out.println("Creado correctamente");
                    fact.insertarObjetoFacturaBBDD();
                    Factura[] copia = facturaList;
                    for(int i=0;i< copia.length;i++){
                        copia[i]=facturaList[i];
                    }
                    facturaList = new Factura[facturaList.length+1];
                    for(int i=0;i< copia.length;i++){
                        facturaList[i]=copia[i];
                    }
                    facturaList[facturaList.length-1] = fact;
                    facturaList = Factura.cargarFacturas();
                    break;
                case 2:
                    mostrarFacturas();
                    chosen_id = Utils.kInt("Escribe el id que quieras");
                    finder = new int[facturaList.length];
                    for (int i = 0; i < facturaList.length; i++) {
                        finder[i] = facturaList[i].getId();
                    }
                    index = getIndex(finder, chosen_id);
                    System.out.println("Que quieres hacer?\n" +
                            "1- Editar datos\n" +
                            "2- Borrar factura\n" +
                            "3- Salir");
                    switch(Utils.kInt()){
                        case 1:
                            Factura.modificarFacturaBBDD(facturaList[index].getId(),Utils.kString("Trabajos"),Utils.kFloat("Coste"),Utils.kString("Fecha"));
                            facturaList = Factura.cargarFacturas();
                            break;
                        case 2:
                            Factura.borrarFacturaBBDD(facturaList[index].getId());
                            facturaList = Factura.cargarFacturas();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    menu=false;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }

        }
    }

    private static void menu_motores(int chosen_id, int[] finder){
        boolean menu = true;
        int index;
        while(menu) {
            switch (Utils.kInt("1- Nuevo motor\n2- Editar uno anterior\n3- Salir")){
                case 1:
                    Motor mot = Motor.crearMotor();
                    System.out.println("Creado correctamente");
                    mot.insertarDatosMotorBBDD();
                    Motor[] copia = motorList;
                    for(int i=0;i< copia.length;i++){
                        copia[i]=motorList[i];
                    }
                    motorList = new Motor[motorList.length+1];
                    for(int i=0;i< copia.length;i++){
                        motorList[i]=copia[i];
                    }
                    motorList[motorList.length-1] = mot;
                    motorList = Motor.cargarMotores();
                    break;
                case 2:
                    mostrarMotores();
                    chosen_id = Utils.kInt("Escribe el id que quieras");
                    finder = new int[motorList.length];
                    for (int i = 0; i < motorList.length; i++) {
                        finder[i] = motorList[i].getId();
                    }
                    index = getIndex(finder, chosen_id);
                    System.out.println("Que quieres hacer?\n" +
                            "1- Editar datos\n" +
                            "2- Borrar motor\n" +
                            "3- Salir");
                    switch(Utils.kInt()){
                        case 1:
                            Motor.modificarMotorBBDD(Utils.kString("Electrico, Gasolina 95/95 o Diesel"), Utils.kFloat("Potencia"),Utils.kFloat("Cilindrada"),Utils.kInt("Numeo de motores"),Utils.kFloat("Par"),facturaList[index].getId());
                            motorList = Motor.cargarMotores();
                            break;
                        case 2:
                            Motor.borrarMotorBBDD(facturaList[index].getId());
                            motorList = Motor.cargarMotores();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    menu=false;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }

        }
    }

    private static void menu_nominas(int chosen_id, int[] finder){
        boolean menu = true;
        int index;
        while(menu) {
            switch (Utils.kInt("1- Nueva nomina\n2- Editar una anterior\n3- Salir")){
                case 1:
                    Nomina nom = Nomina.crearNomina();
                    System.out.println("Creado correctamente");
                    nom.insertarDatosNominaBBDD();
                    Nomina[] copia = nominaList;
                    for(int i=0;i< copia.length;i++){
                        copia[i]=nominaList[i];
                    }
                    nominaList = new Nomina[nominaList.length+1];
                    for(int i=0;i< copia.length;i++){
                        nominaList[i]=copia[i];
                    }
                    nominaList[nominaList.length-1] = nom;
                    nominaList = Nomina.cargarNominas();
                    break;
                case 2:
                    mostrarNominas();
                    chosen_id = Utils.kInt("Escribe el id que quieras");
                    finder = new int[nominaList.length];
                    for (int i = 0; i < nominaList.length; i++) {
                        finder[i] = nominaList[i].getId();
                    }
                    index = getIndex(finder, chosen_id);
                    System.out.println("Que quieres hacer?\n" +
                            "1- Editar datos\n" +
                            "2- Borrar nomina\n" +
                            "3- Salir");
                    switch(Utils.kInt()){
                        case 1:
                            Nomina.modificarNominaBBDD(nominaList[index].getId(),Utils.kInt("Horas trabajadas"),Utils.kFloat("Sueldo bruto"),Utils.kFloat("Sueldo neto"),Utils.kString("Fecha"),Utils.kString("Nif"));
                            nominaList = Nomina.cargarNominas();
                            break;
                        case 2:
                            Nomina.borrarNominaBBDD(nominaList[index].getId());
                            nominaList = Nomina.cargarNominas();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    menu=false;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }
        }
    }

    private static void menu_reservas(int chosen_id, int[] finder){
        boolean menu = true;
        int index;
        while(menu) {
            switch (Utils.kInt("1- Nueva reserva\n2- Editar una anterior\n3- Salir")){
                case 1:
                    Reserva nom = Reserva.crearReserva();
                    System.out.println("Creado correctamente");
                    nom.insertarDatosReservaBBDD();
                    Reserva[] copia = reservaList;
                    for(int i=0;i< copia.length;i++){
                        copia[i]=reservaList[i];
                    }
                    reservaList = new Reserva[reservaList.length+1];
                    for(int i=0;i< copia.length;i++){
                        reservaList[i]=copia[i];
                    }
                    reservaList[reservaList.length-1] = nom;
                    reservaList = Reserva.cargarReservas();
                    break;
                case 2:
                    mostrarReservas();
                    chosen_id = Utils.kInt("Escribe el id que quieras");
                    finder = new int[reservaList.length];
                    for (int i = 0; i < reservaList.length; i++) {
                        finder[i] = reservaList[i].getId();
                    }
                    index = getIndex(finder, chosen_id);
                    System.out.println("Que quieres hacer?\n" +
                            "1- Editar datos\n" +
                            "2- Borrar reserva\n" +
                            "3- Salir");
                    switch(Utils.kInt()){
                        case 1:
                            Reserva.modificarReservaBBDD(reservaList[index].getId(),Utils.kString("Fecha"),Utils.kInt("Espacio reservado"));
                            reservaList = Reserva.cargarReservas();
                            break;
                        case 2:
                            Reserva.borrarReserva(reservaList[index].getId());
                            reservaList = Reserva.cargarReservas();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    menu=false;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }
        }
    }

    private static void menu_talleres(int chosen_id, int[] finder){
        boolean menu = true;
        int index;
        while(menu) {
            switch (Utils.kInt("1- Nuevo taller\n2- Editar uno anterior\n3- Salir")){
                case 1:
                    Taller tall = Taller.crearTaller();
                    System.out.println("Creado correctamente");
                    Taller.guardarDatosTaller(tall);
                    Taller[] copia = tallerList;
                    for(int i=0;i< copia.length;i++){
                        copia[i]=tallerList[i];
                    }
                    tallerList = new Taller[tallerList.length+1];
                    for(int i=0;i< copia.length;i++){
                        tallerList[i]=copia[i];
                    }
                    tallerList[tallerList.length-1] = tall;
                    tallerList = Taller.cargarTalleres();
                    break;
                case 2:
                    mostrarTalleres();
                    chosen_id = Utils.kInt("Escribe el id que quieras");
                    finder = new int[tallerList.length];
                    for (int i = 0; i < tallerList.length; i++) {
                        finder[i] = tallerList[i].getId();
                    }
                    index = getIndex(finder, chosen_id);
                    System.out.println("Que quieres hacer?\n" +
                            "1- Editar datos\n" +
                            "2- Borrar taller\n" +
                            "3- Salir");
                    switch(Utils.kInt()){
                        case 1:
                            Taller.modificarTaller(tallerList[index].getId(),Utils.kInt("Espacios"),Utils.kString("Horario"));
                            tallerList = Taller.cargarTalleres();
                            break;
                        case 2:
                            Reserva.borrarReserva(tallerList[index].getId());
                            tallerList = Taller.cargarTalleres();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    menu=false;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }
        }
    }


    private static void menu_ventas(int chosen_id, int[] finder){
        boolean menu = true;
        int index;
        while(menu) {
            switch (Utils.kInt("1- Nueva venta\n2- Editar una anterior\n3- Salir")){
                case 1:
                    Venta ven = Venta.crearVenta();
                    System.out.println("Creado correctamente");
                    Venta.guardarDatosVenta(ven);
                    Venta[] copia = ventaList;
                    for(int i=0;i< copia.length;i++){
                        copia[i]=ventaList[i];
                    }
                    ventaList = new Venta[ventaList.length+1];
                    for(int i=0;i< copia.length;i++){
                        ventaList[i]=copia[i];
                    }
                    ventaList[ventaList.length-1] = ven;
                    ventaList = Venta.cargarVenta();
                    break;
                case 2:
                    mostrarVentas();
                    chosen_id = Utils.kInt("Escribe el id que quieras");
                    finder = new int[ventaList.length];
                    for (int i = 0; i < ventaList.length; i++) {
                        finder[i] = ventaList[i].getId();
                    }
                    index = getIndex(finder, chosen_id);
                    System.out.println("Que quieres hacer?\n" +
                            "1- Editar datos\n" +
                            "2- Borrar ventas\n" +
                            "3- Salir");
                    switch(Utils.kInt()){
                        case 1:
                            Venta.modificarVenta(ventaList[index].getId(),Utils.kString("Horario"));
                            ventaList = Venta.cargarVenta();
                            break;
                        case 2:
                            Venta.borrarVenta(ventaList[index].getId());
                            ventaList = Venta.cargarVenta();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    menu=false;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }
        }
    }

    private static void menu_vehiculos(String chosen_id, String[] finder){
        boolean menu = true;
        int index;
        while(menu) {
            switch (Utils.kInt("1- Nuevo vehiculo\n2- Editar uno anterior\n3- Salir")){
                case 1:
                    Motor.mostrarTodosMotoresBBDD();
                    Vehiculo veh = Vehiculo.crearVehiculo(Motor.buscarMotorBBDD(Utils.kInt("Id del motor")));
                    System.out.println("Creado correctamente");
                    veh.insertarDatosVehiculoBBDD();
                    Vehiculo[] copia = vehiculoList;
                    for(int i=0;i< copia.length;i++){
                        copia[i]=vehiculoList[i];
                    }
                    vehiculoList = new Vehiculo[vehiculoList.length+1];
                    for(int i=0;i< copia.length;i++){
                        vehiculoList[i]=copia[i];
                    }
                    vehiculoList[vehiculoList.length-1] = veh;
                    vehiculoList = Vehiculo.cargarVehiculos();
                    break;
                case 2:
                    mostrarVehiculos();
                    chosen_id = Utils.kString("Escribe el bastidor que quieras");
                    finder = new String[vehiculoList.length];
                    for (int i = 0; i < vehiculoList.length; i++) {
                        finder[i] = vehiculoList[i].getBastidor();
                    }
                    index = getStringIndex(finder, chosen_id);
                    System.out.println("Que quieres hacer?\n" +
                            "1- Editar datos\n" +
                            "2- Borrar vehiculo\n" +
                            "3- Salir");
                    switch(Utils.kInt()){
                        case 1:
                            Vehiculo.modificarVehiculoBBDD(Utils.kString("Tipo"),Utils.kString("Estado"),Utils.kInt("Kilometraje"),Utils.kInt("Autonomia"),Utils.kInt("Puertas"),Utils.kInt("Asientos"),Utils.kString("Color"),Utils.kString("Marca"),Utils.kString("Modelos"),Utils.kFloat("Precio"),Utils.kString("Extras"),vehiculoList[index].getMotor().getId(),Utils.kInt("Venta"),Utils.kString("Nif"),vehiculoList[index].getBastidor());
                            vehiculoList = Vehiculo.cargarVehiculos();
                            break;
                        case 2:
                            vehiculoList[index].borrarVehiculoBBDD();
                            vehiculoList = Vehiculo.cargarVehiculos();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    menu=false;
                    break;
                default:
                    System.out.println("try again");
                    break;
            }
        }
    }

    private static int getIndex(int[] ids, int id){
        int ret = -1;
        for(int i=0;i<ids.length;i++){
            if(ids[i]==id){
                ret = i;
            }
        }
        return ret;
    }

    private static int getStringIndex(String[] ids, String id){
        int ret = -1;
        for(int i=0;i<ids.length;i++){
            if(ids[i].equals(id)){
                ret = i;
            }
        }
        return ret;
    }

    private static void mostrarConcesionarios(){
        for(int i=0;i<concesionarioList.length;i++){
            System.out.println(concesionarioList[i].toString());
        }
    }

    private static void mostrarClientes(){
        for(int i=0;i<clienteList.length;i++){
            System.out.println(clienteList[i].toString());
        }
    }

    private static void mostrarEmpleados(){
        for(int i=0;i<empleadoList.length;i++){
            System.out.println(empleadoList[i].toString());
        }
    }

    private static void mostrarFacturas(){
        for(int i=0;i<facturaList.length;i++){
            System.out.println(facturaList[i].toString());
        }
    }

    private static void mostrarMotores(){
        for(int i=0;i<motorList.length;i++){
            System.out.println(motorList[i].toString());
        }
    }

    private static void mostrarNominas(){
        for(int i=0;i<nominaList.length;i++){
            System.out.println(nominaList[i].toString());
        }
    }

    private static void mostrarReservas(){
        for(int i=0;i<reservaList.length;i++){
            System.out.println(reservaList[i].toString());
        }
    }

    private static void mostrarTalleres(){
        for(int i=0;i<tallerList.length;i++){
            System.out.println(tallerList[i].toString());
        }
    }

    private static void mostrarVehiculos(){
        for(int i=0;i<vehiculoList.length;i++){
            System.out.println(vehiculoList[i].toString());
        }
    }

    private static void mostrarVentas(){
        for(int i=0;i<ventaList.length;i++){
            System.out.println(ventaList[i].toString());
        }
    }

    public static void main(String[] args){
        Utils.conectarBBDD();
        clienteList = Cliente.cargarTodosClientes();
        System.out.println("Clientes ok");
        concesionarioList = Concesionario.cargarConcesionarios();
        System.out.println("Concesionario ok");
        empleadoList = Empleado.cargarEmpleados();
        System.out.println("Empleado ok");
        facturaList = Factura.cargarFacturas();
        System.out.println("Facturas ok");
        motorList = Motor.cargarMotores();
        System.out.println("Motores ok");
        nominaList = Nomina.cargarNominas();
        System.out.println("Nominas ok");
        reservaList = Reserva.cargarReservas();
        System.out.println("Reservas ok");
        tallerList = Taller.cargarTalleres();
        System.out.println("Talleres ok");
        vehiculoList = Vehiculo.cargarVehiculos();
        System.out.println("Vehiculos ok");
        ventaList = Venta.cargarVenta();
        System.out.println("Venta ok");
        int[] finder = null;
        String[] str_finder = null;
        boolean menu = true;
        int chosen_id = -1;
        String chosen_nif = null;
        while (menu){
            System.out.println("Que quieres controlar?");
            System.out.println("1- Clientes\n" +
                    "2- Concesionario\n" +
                    "3- Empleados\n" +
                    "4- Facturas\n" +
                    "5- Motores\n" +
                    "6- Nominas\n" +
                    "7- Reservas\n" +
                    "8- Talleres\n" +
                    "9- Vehiculos\n" +
                    "10- Ventas\n" +
                    "11- Salir");
            switch(Utils.kInt()){
                case 1:
                    menu_clientes(chosen_nif,str_finder);
                    menu=true;
                    break;
                case 2:
                    menu_concesionarios(chosen_id,finder);
                    break;
                case 3:
                    menu_empleados(chosen_nif,str_finder);
                    break;
                case 4:
                    menu_facturas(chosen_id,finder);
                    break;
                case 5:
                    menu_motores(chosen_id,finder);
                    break;
                case 6:
                    menu_nominas(chosen_id,finder);
                    break;
                case 7:
                    menu_reservas(chosen_id,finder);
                    break;
                case 8:
                    menu_talleres(chosen_id,finder);
                    break;
                case 9:
                    menu_vehiculos(chosen_nif,str_finder);
                    break;
                case 10:
                    menu_ventas(chosen_id,finder);
                    break;
                case 11:
                    menu=false;
                    break;
                default:
                    System.out.println("Prueba otra vez");
                    break;
            }
        }
    }
}
