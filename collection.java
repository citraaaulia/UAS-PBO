import java.util.*;
import java.sql.*;

public class collection {

    static Connection conn;

    // method menu
    public static void menu() {

        Kendaraan kendaraan = new Kendaraan();

        //scanner dan exception try and catch
        try (Scanner input = new Scanner(System.in)) {
            String pil;
            boolean isLanjutkan = true;

            String url = "jdbc:mysql://localhost:3306/parkir";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, "root", "");
                System.out.println("Class Driver ditemukan");

                // perulangan while
                while (isLanjutkan) {
                    System.out.println("");
                    System.out.println("------------------------------------------");
                    System.out.println("                 PARKIR MUDAH             ");
                    System.out.println("------------------------------------------");
                    System.out.println("1. Daftarkan data kendaraan");
                    System.out.println("2. Edit data kendaraan");
                    System.out.println("3. List data kendaraan yang sedang parkir");
                    System.out.println("4. Remove data kendaraan");
                    System.out.println("5. Keluar");
                    System.out.println("\n_________________________________________");
                    System.out.println("");
                    System.out.print("Masukkan Pilihan : ");
                    pil = input.next();
                    System.out.println("_________________________________________\n");

                    // percabangan switch case
                    switch (pil) {
                        case "1":
                            kendaraan.jenisKendaraan();
                            kendaraan.platKendaraan();
                            kendaraan.namaPemilik();
                            kendaraan.jamMasuk();
                            kendaraan.insertData();
                            break;

                        case "2":
                            kendaraan.editData();
                            break;

                        case "3":
                            kendaraan.showData();
                            break;

                        case "4":
                            kendaraan.deleteData();

                            break;

                        case "5":
                            isLanjutkan = false;
                            System.out.println("Semoga Harimu Menyenangkan, Sampai Jumpa");
                            break;

                        default:
                            System.err.println("\nInput tidak ditemukan\n pilih [1-5]");
                    }

                    System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
                    pil = input.next();
                    isLanjutkan = pil.equalsIgnoreCase("y");
                }
                System.out.println("\nSenang Bekerja sama dengan anda ");

            }
            // exception koneksi database
            catch (ClassNotFoundException ex) {
                System.err.println("Driver Error");
                System.exit(0);
            }
            // sql exception
            catch (SQLException e) {
                System.err.println("Koneksi tidak berhasil");
            }
        }
    }

    // method penilaian
    public static void penilaian() {
        // collection hashmap
        HashMap<Integer, String> data = new HashMap<Integer, String>();

        // set data hashmap
        data.put(1, "Buruk");
        data.put(2, "Biasa saja");
        data.put(3, "Cukup baik");
        data.put(4, "Sangat baik dan memuaskan");

        // perulangan utnuk setiap set data
        for (Map.Entry nama : data.entrySet()) {
            System.out.println("kunci: " + nama.getKey() + " Nilai: " + nama.getValue());
        }

        System.out.println("Sampaikan Feedback anda melalui email kami parkircitra@gmail.com\n");
    }

}