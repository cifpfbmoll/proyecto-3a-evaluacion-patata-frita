public class Main {

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
    }
}
