/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pbo_2310010231;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author wijaya
 */
public class CRUD {

    private String database = "pbo_2310010231";
    private String username = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost/" + database;
    public Connection koneksiDB;

    public CRUD() {
        try {
            koneksiDB = DriverManager.getConnection(url, username, password);
            System.out.println("Koneksi Database Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
        }
    }

    public void loadData(JTable tabel, String sql) {
        try {
            Statement perintah = koneksiDB.createStatement();
            ResultSet ds = perintah.executeQuery(sql);
            ResultSetMetaData data = ds.getMetaData();
            int kolom = data.getColumnCount();
            DefaultTableModel model = new DefaultTableModel();

            for (int i = 1; i <= kolom; i++) {
                model.addColumn(data.getColumnName(i));
            }

            model.getDataVector().clear();
            model.fireTableDataChanged();

            while (ds.next()) {
                Object[] baris = new Object[kolom];
                for (int j = 0; j < kolom; j++) {
                    baris[j] = ds.getObject(j + 1);
                }
                model.addRow(baris);
            }
            tabel.setModel(model);
            ds.close();
            perintah.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data: " + e.getMessage());
        }
    }

    public void simpanUser(String username, String password, String role) throws SQLException {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, username);
        perintah.setString(2, password);
        perintah.setString(3, role);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data User Berhasil Disimpan");
        perintah.close();
    }

    public void ubahUser(String idUser, String username, String password, String role) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE id_user = ?";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, username);
        perintah.setString(2, password);
        perintah.setString(3, role);
        perintah.setString(4, idUser);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data User Berhasil Diubah");
        perintah.close();
    }

    public void hapusUser(String idUser) throws SQLException {
        String sql = "DELETE FROM users WHERE id_user = ?";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idUser);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data User Berhasil Dihapus");
        perintah.close();
    }

    public void simpanKaryawan(String idUser, String nama, String email, String alamat, String noTelpon) throws SQLException {
        String sql = "INSERT INTO karyawan (id_user, nama_karyawan, email, alamat_karyawan, no_telpon) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idUser);
        perintah.setString(2, nama);
        perintah.setString(3, email);
        perintah.setString(4, alamat);
        perintah.setString(5, noTelpon);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Karyawan Berhasil Disimpan");
        perintah.close();
    }

    public void ubahKaryawan(String idKaryawan, String idUser, String nama, String email, String alamat, String noTelpon) throws SQLException {
        String sql = "UPDATE karyawan SET id_user = ?, nama_karyawan = ?, email = ?, alamat_karyawan = ?, no_telpon = ? WHERE id_karyawan = ?";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idUser);
        perintah.setString(2, nama);
        perintah.setString(3, email);
        perintah.setString(4, alamat);
        perintah.setString(5, noTelpon);
        perintah.setString(6, idKaryawan);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Karyawan Berhasil Diubah");
        perintah.close();
    }

    public void hapusKaryawan(String idKaryawan) throws SQLException {
        String sql = "DELETE FROM karyawan WHERE id_karyawan = ?";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idKaryawan);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Karyawan Berhasil Dihapus");
        perintah.close();
    }

    public void simpanInventory(String idKaryawan, String namaBarang, String kategori, String jumlah) throws SQLException {
        String sql = "INSERT INTO inventory (id_karyawan, nama_barang, kategori_barang, jumlah) VALUES (?, ?, ?, ?)";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idKaryawan);
        perintah.setString(2, namaBarang);
        perintah.setString(3, kategori);
        perintah.setString(4, jumlah);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Inventory Berhasil Disimpan");
        perintah.close();
    }

    public void ubahInventory(String idInventory, String idKaryawan, String namaBarang, String kategori, String jumlah) throws SQLException {
        String sql = "UPDATE inventory SET id_karyawan = ?, nama_barang = ?, kategori_barang = ?, jumlah = ? WHERE id_inventory = ?";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idKaryawan);
        perintah.setString(2, namaBarang);
        perintah.setString(3, kategori);
        perintah.setString(4, jumlah);
        perintah.setString(5, idInventory);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Inventory Berhasil Diubah");
        perintah.close();
    }

    public void hapusInventory(String idInventory) throws SQLException {
        String sql = "DELETE FROM inventory WHERE id_inventory = ?";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idInventory);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Inventory Berhasil Dihapus");
        perintah.close();
    }

    public void simpanCuti(String idKaryawan, String tglMulai, String tglSelesai, String alasan) throws SQLException {
        String sql = "INSERT INTO cuti (id_karyawan, tanggal_mulai, tanggal_selesai, alasan) VALUES (?, ?, ?, ?)";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idKaryawan);
        perintah.setString(2, tglMulai); 
        perintah.setString(3, tglSelesai);
        perintah.setString(4, alasan);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Cuti Berhasil Disimpan");
        perintah.close();
    }

    public void ubahCuti(String idCuti, String idKaryawan, String tglMulai, String tglSelesai, String alasan) throws SQLException {
        String sql = "UPDATE cuti SET id_karyawan = ?, tanggal_mulai = ?, tanggal_selesai = ?, alasan = ? WHERE id_cuti = ?";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idKaryawan);
        perintah.setString(2, tglMulai);
        perintah.setString(3, tglSelesai);
        perintah.setString(4, alasan);
        perintah.setString(5, idCuti);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Cuti Berhasil Diubah");
        perintah.close();
    }

    public void hapusCuti(String idCuti) throws SQLException {
        String sql = "DELETE FROM cuti WHERE id_cuti = ?";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idCuti);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Cuti Berhasil Dihapus");
        perintah.close();
    }

    public void simpanPenghasilan(String idKaryawan, String jumlah) throws SQLException {
        String sql = "INSERT INTO penghasilan (id_karyawan, jumlah_penghasilan) VALUES (?, ?)";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idKaryawan);
        perintah.setString(2, jumlah);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Penghasilan Berhasil Disimpan");
        perintah.close();
    }

    public void ubahPenghasilan(String idPenghasilan, String idKaryawan, String jumlah) throws SQLException {
        String sql = "UPDATE penghasilan SET id_karyawan = ?, jumlah_penghasilan = ? WHERE id_penghasilan = ?";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idKaryawan);
        perintah.setString(2, jumlah);
        perintah.setString(3, idPenghasilan);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Penghasilan Berhasil Diubah");
        perintah.close();
    }

    public void hapusPenghasilan(String idPenghasilan) throws SQLException {
        String sql = "DELETE FROM penghasilan WHERE id_penghasilan = ?";
        PreparedStatement perintah = koneksiDB.prepareStatement(sql);
        perintah.setString(1, idPenghasilan);
        perintah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Penghasilan Berhasil Dihapus");
        perintah.close();
    }
}