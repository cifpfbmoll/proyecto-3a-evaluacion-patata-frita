public class main_test {
    public static void main(String args[]){
        Utils.conectarBBDD();
        System.out.println(Vehiculo.devolverTodosVehiculoBBDD()[0][0].toString());
        System.out.println(Vehiculo.devolverTodosVehiculoBBDD()[0][0].toString());
    }
}
