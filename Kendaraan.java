import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//implements
public class Kendaraan implements TempatParkir{

    //koneksi database
    Connection conn;
    String url = "jdbc:mysql://localhost:3306/parkir";

    int jamm, jamk, waktu;

    Scanner input = new Scanner(System.in);
    int  tagihan, bayar, kembalian, jnsKendaraan;
    String namaPemilik, noPlat;

    //override method dari kelas interface
    @Override
    public void jenisKendaraan() {
        // TODO Auto-generated method stub
        System.out.println("Jenis Kendaraan: \n1. Mobil\n2. Motor");
        System.out.print("Apa kendaraan yang anda gunakan? (angka)\t: ");
        this.jnsKendaraan = input.nextInt();
        input.nextLine();

        //perulangan while
        while(jnsKendaraan<1 || jnsKendaraan>2){
            System.out.println("Inputkan data dengan benar");
            System.out.print("Apa kendaraan yang anda gunakan? (angka) : ");
            this.jnsKendaraan = input.nextInt();
            input.nextLine();
        }
        
    }

    //implements method platKendaraan
    @Override
    public void platKendaraan() {
        // TODO Auto-generated method stub
        System.out.println("Berapa No. Plat Kendaraan anda?\t:  ");
        this.noPlat = input.nextLine();
    }

    //implement method namaPemilik();
    @Override
    public void namaPemilik() {
        // TODO Auto-generated method stub
        System.out.println("Siapa nama pemilik kendaraan?\t:  ");
        this.namaPemilik = input.nextLine();
    }
    @Override
    public void jamMasuk() {
        // TODO Auto-generated method stub
        
        GregorianCalendar date = new GregorianCalendar();
        this.jamm =  date.get(Calendar.HOUR_OF_DAY);
        System.out.println("Kendaraan masuk pada pukul "+this.jamm);
    }
    
    @Override
    public void jamKeluar() {
        // TODO Auto-generated method stub

        GregorianCalendar date = new GregorianCalendar();
        this.jamk =  date.get(Calendar.HOUR_OF_DAY);
        System.out.println("Kendaraan keluar pada pukul "+this.jamk);
        
        
    }
    


    @Override
    public void bayar() {
        // TODO Auto-generated method stub
        if (this.jnsKendaraan==1){
            this.tagihan = this.waktu*10000;
        } else {
            this.tagihan = this.waktu*5000;
        }
    }
    
    public void insertData() throws SQLException{
        //insert database
        String sql = "INSERT INTO kendaraan (no_plat,jenis_kendaraan,nama_pemilik, jam_masuk) VALUES ('"+this.noPlat+"', '"+this.jnsKendaraan+"','"+this.namaPemilik+"','"+this.jamm+"' )";
        conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        statement.execute(sql);   
        System.out.println("\n Data telah berhasil dimasukkan"); 
    }

    public void showData() throws SQLException{
        int count = 1;
        String sql = "SELECT no_plat, nama_pemilik, jenis_kendaraan, jam_masuk FROM kendaraan";
        conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        //perulangan
        while(result.next()){
            System.out.println(count+". ");
            System.out.print("Nomor Plat Motor :");
            System.out.println(result.getString("no_plat"));
            System.out.print("Jenis Kendaraan\t :");
            System.out.println(result.getString("jenis_kendaraan"));
            System.out.print("Nama Pemilik\t:");
            System.out.println(result.getString("nama_pemilik"));
            System.out.print("Masuk Pada Jam\t:");
            System.out.println(result.getInt("jam_masuk"));
            count++;
        }
    }

    //method edit database
    public void editData()throws SQLException {
        try{
            System.out.println("\n==== Edit Data Kendaraan ====");
            showData();
            Integer pil;
            System.out.print("\n No. Plat kendaraan yang ingin di ubah : ");
            String edit = input.next();

            //mengakses database
            String sql = "SELECT * FROM kendaraan WHERE no_plat='"+edit+"'";
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            //percabangan if
            if (result.next()){
                System.out.println("Data yang ingin diubah\n1. Nomor Kendaraan\n2. Nama Pemilik\n3. Jenis Kendaraan");
                System.out.print("Pilihan : ");
                pil = input.nextInt();
                input.nextLine();

                //percabangan switch case
                    switch (pil){
                        case 1:
                            System.out.print("Nomor Kendaraan <"+result.getString("no_plat")+">\t: ");
                            String editPlat = input.next();
                            //update database
                            sql = "UPDATE kendaraan SET no_plat = '"+editPlat+"' WHERE no_plat ='"+edit+"'";
                            if(statement.executeUpdate(sql) > 0){
                                System.out.println("\n```Berhasil mengubah nomor kendaraan menjadi |"+editPlat+"|```\n");
                            }
                            break;
                            
                        case 2:
                            System.out.print("Nama Pemilik <"+result.getString("nama_pemilik")+"> menjadi\t: ");
                            String editNama = input.next();
                            //update database
                            sql = "UPDATE kendaraan SET nama_pemilik = '"+editNama+"' WHERE no_plat ='"+edit+"'";
                            if(statement.executeUpdate(sql) > 0){
                                System.out.println("\n```Berhasil mengubah Nama Pemilik menjadi |"+editNama+"|```\n");
                            }
                            break;

                        case 3:
                            System.out.print("Jenis Kendaraan <"+result.getString("jenis_kendaraan")+">\t: ");
                            String editJenis = input.next();
                            //update database
                            sql = "UPDATE kendaraan SET jenis_kendaraan = '"+editJenis+"' WHERE no_plat ='"+edit+"'";
                            if(statement.executeUpdate(sql) > 0){
                                System.out.println("\n```Berhasil mengubah Jenis Kendaraan menjadi |"+editJenis+"|```\n");
                            }
                            break;

                        

                        default :
                            System.out.println("Inputan harus berupa angka 1/2/3/4/5/6 !");
                            break;
                    }
                    
            }
            else{
                System.out.println("Nomor kendaraan yang diinputkan salah");
            }
        }
        //exeption SQL 
        catch (SQLException e) {
            System.err.println("Kesalahan update data");
        }

        //exception input angka
        catch (InputMismatchException e){
            System.err.println("Inputan harus berupa angka!");
        }
    }
    
    public void deleteData()throws SQLException{
        try{
            System.out.println("\n==== Hapus Data Kendaraan ====\n");
            showData();
            System.out.print("\n No Kendaraan yang ingin di hapus : ");
            String del = input.next();
            //menghapus database
            String sql = "DELETE FROM kendaraan WHERE no_plat LIKE'%"+del+"%'";
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            if(statement.executeUpdate(sql) > 0){
                System.out.println("Berhasil menghapus data no. kendaraan "+del);
                System.out.println("Pelanggan Dipersilahkan keluar");
            }

            
        }
		catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }

        catch(Exception e){
            System.out.println("Input data yang benar!");
        }

        finally{
            //date and time
            LocalDate currdate = LocalDate.now();
            LocalTime currtime = LocalTime.now();
            System.out.println("\n===================================");
            System.out.println("Waktu Akses :");
            System.out.println("Tanggal\t= "+currdate.toString());
            System.out.println("Waktu\t= "+currtime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            System.out.println("=====================================\n");
        }
    }
}
