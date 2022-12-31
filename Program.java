
//class program
public class Program extends collection {

    public static void main(String[] args) throws Exception {

        // penggunaan String
        String title = "=                 Program Parkir                 =";
        String head = "Selamat datang di program parkir citra";
        String kepala = head.replace("Selamat pagi", "\nstemukan parkir mudah bersama kami");
        System.out.println(kepala); // method replace()

        System.out.println("__________________________________________________");
        System.out.println(title.toUpperCase()); // method toUpperCase()
        System.out.println("__________________________________________________");

        // penggunaan method dari collection (is a)
        menu();

        penilaian();
    }

}
