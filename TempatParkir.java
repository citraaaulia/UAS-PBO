import java.sql.*;

public interface TempatParkir {
    void jenisKendaraan() throws SQLException;

    void platKendaraan() throws SQLException;

    void namaPemilik() throws SQLException;

    void jamMasuk() throws SQLException;

    void jamKeluar() throws SQLException;

    void bayar() throws SQLException;
}
