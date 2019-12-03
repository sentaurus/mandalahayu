/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mandalahayu.interfaceKasir;
import mandalahayu.model.kasir;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dika
 */
public class queryKasir implements interfaceKasir {

    private final Connection koneksi;

    public queryKasir(Connection koneksi) {
        this.koneksi = koneksi;
    }

    @Override
    public void simpan(kasir kasir) {
        String SQL = "INSERT INTO pembayaran (id_adm,id_pet,kategori,harga,tanggal)"
                + " VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = koneksi.prepareStatement(SQL);
            ps.setString(1, kasir.getId_adm());
            ps.setString(2, kasir.getId_pet());
            ps.setString(3, kasir.getKategori());
            ps.setInt(4, kasir.getHarga());
            ps.setObject(5, kasir.getTanggal());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(queryKasir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ubah(kasir kasir) {
        String SQL = "UPDATE pembayaran SET id_adm=?,id_pet=?,kategori=?,harga=?,"
                + "tanggal=? WHERE no=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(SQL);
            ps.setString(1, kasir.getId_adm());
            ps.setString(2, kasir.getId_pet());
            ps.setString(3, kasir.getKategori());
            ps.setInt(4, kasir.getHarga());
            ps.setObject(5, kasir.getTanggal());
            ps.setInt(6, kasir.getNo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(queryKasir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void hapus(kasir kasir) {
        String SQL = "DELETE FROM pembayaran WHERE no=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(SQL);
            ps.setInt(1, kasir.getNo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(queryKasir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<kasir> getAll() {
        String SQL = "SELECT murid.nama, pembayaran.* FROM pembayaran INNER JOIN"
                + " administrasi ON pembayaran.id_adm = administrasi.id_adm "
                + "INNER JOIN murid ON administrasi.id_mrd = murid.id_mrd";
        ObservableList<kasir> lk = FXCollections.observableArrayList();
        try {
            ResultSet rs = koneksi.prepareStatement(SQL).executeQuery();
            while (rs.next()) {
                kasir kas = new kasir();
                kas.setNama(rs.getString(1));
                kas.setNo(rs.getInt(2));
                kas.setId_adm(rs.getString(3));
                kas.setId_pet(rs.getString(4));
                kas.setKategori(rs.getString(5));
                kas.setHarga(rs.getInt(6));
                kas.setTanggal(rs.getDate(7).toLocalDate());
                lk.add(kas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryKasir.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lk;
    }

    @Override
    public void cetak() {
        Map<String, Object> mso = new HashMap<>();
        try {
            File file = new File("src/mandalahayu/laporan/lapKasir.jrxml");
            JasperDesign jd = JRXmlLoader.load(file);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, mso, koneksi);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(queryKasir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void kwitansi(String idAdm, LocalDate tanggal) {
        Map<String, Object> mso = new HashMap<>(2);
        try {
            File file = new File("src/mandalahayu/laporan/kwitansi.jrxml");
            JasperDesign jd = JRXmlLoader.load(file);
            mso.put("idAdm", idAdm);
            mso.put("tgl", tanggal);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, mso, koneksi);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(queryKasir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<kasir> getCari(String cari) {
        String SQL = "SELECT murid.nama, pembayaran.* FROM pembayaran INNER JOIN"
                + " administrasi ON pembayaran.id_adm = administrasi.id_adm "
                + "INNER JOIN murid ON administrasi.id_mrd = murid.id_mrd WHERE "
                + "pembayaran.id_adm LIKE '%"+cari+"%' OR murid.nama LIKE '%"+cari+"%'";
        ObservableList<kasir> lk = FXCollections.observableArrayList();
        try {
            ResultSet rs = koneksi.prepareStatement(SQL).executeQuery();
            while (rs.next()) {
                kasir kas = new kasir();
                kas.setNama(rs.getString(1));
                kas.setNo(rs.getInt(2));
                kas.setId_adm(rs.getString(3));
                kas.setId_pet(rs.getString(4));
                kas.setKategori(rs.getString(5));
                kas.setHarga(rs.getInt(6));
                kas.setTanggal(rs.getDate(7).toLocalDate());
                lk.add(kas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryKasir.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lk;
    }

}
