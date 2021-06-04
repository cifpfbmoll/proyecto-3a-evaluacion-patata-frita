public class main_test {
    public static void main(String args[]) {
        Utils.conectarBBDD();
        Object[][] ah = Vehiculo.devolverTodosVehiculoBBDD();
        System.out.println(ah[0][0].toString());
        System.out.println(ah[0][1].toString());
        System.out.println(ah[0][2].toString());
        System.out.println(ah[0][3].toString());
        ah = Vehiculo.devolverTodosVehiculoBBDD();
        System.out.println(ah[0][0].toString());
        System.out.println(ah[0][1].toString());
        System.out.println(ah[0][2].toString());
        System.out.println(ah[0][3].toString());
    }
}
