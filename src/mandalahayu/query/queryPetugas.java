/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import mandalahayu.interfacePetugas;
import mandalahayu.keamanan.hashing;
import mandalahayu.model.petugas;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dika
 */
public class queryPetugas implements interfacePetugas {

    private final Connection koneksi;
    private String nip;
    private hashing hash = new hashing();

    public queryPetugas(Connection koneksi) {
        this.koneksi = koneksi;
    }

    @Override
    public void simpan(petugas petugas) {
        String cek = "SELECT * FROM petugas WHERE id_pet='"
                + petugas.getId_pet() + "'";
        String sql = "INSERT INTO petugas(id_pet, nama, sandi, nip, status) VALUES (?,?,?,?,?)";
        try {
            ResultSet rs = koneksi.prepareStatement(cek).executeQuery();
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pemberitahuan!");
                alert.setHeaderText("ID petugas sudah ada");
                alert.setContentText("harap masukkan ID petugas yang berbeda!");
                alert.showAndWait();
            } else {
                PreparedStatement ps = koneksi.prepareStatement(sql);
                ps.setString(1, petugas.getId_pet());
                ps.setString(2, petugas.getNama());
                ps.setString(3, petugas.getSandi());
                ps.setString(4, petugas.getNip());
                ps.setString(5, petugas.getStatus());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryPetugas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ubah(petugas petugas) {
        String sql = "UPDATE petugas SET id_pet=?, nama=?, sandi=?, nip=?,status=? WHERE no=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, petugas.getId_pet());
            ps.setString(2, petugas.getNama());
            ps.setString(3, petugas.getSandi());
            ps.setString(4, petugas.getNip());
            ps.setString(5, petugas.getStatus());
            ps.setInt(6, petugas.getNo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(queryPetugas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void hapus(petugas petugas) {
        String sql = "DELETE * FROM petugas WHERE no=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setInt(1, petugas.getNo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(queryPetugas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cetak() {
        Map<String, Object> mso = new HashMap<>();
        try {
            File file = new File("src/mandalahayu/laporan/lapPetugas.jrxml");
            JasperDesign jd = JRXmlLoader.load(file);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, mso, koneksi);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(queryMurid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<petugas> getAll() {
        ObservableList<petugas> lp = FXCollections.observableArrayList();
        String sql = "SELECT * FROM petugas";
        try {
            ResultSet rs = koneksi.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                petugas pet = new petugas();
                pet.setNo(rs.getInt(1));
                pet.setId_pet(rs.getString(2));
                pet.setNama(rs.getString(3));
                pet.setSandi(rs.getString(4));
                pet.setNip(rs.getString(5));
                pet.setStatus(rs.getString(6));
                lp.add(pet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryPetugas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lp;
    }

    @Override
    public ObservableList<petugas> getLogin() {
        ObservableList<petugas> lp = FXCollections.observableArrayList();
        String sql = "SELECT * FROM petugas WHERE nip='" + nip + "' AND sandi='" + hash.getHash() + "'";
        try {
            ResultSet rs = koneksi.prepareStatement(sql).executeQuery();
            if (rs.next()) {
                petugas pet = new petugas();
                pet.setNo(rs.getInt(1));
                pet.setId_pet(rs.getString(2));
                pet.setNama(rs.getString(3));
                pet.setSandi(rs.getString(4));
                pet.setNip(rs.getString(5));
                pet.setStatus(rs.getString(6));
                lp.add(pet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryPetugas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lp;
    }

    @Override
    public ObservableList<petugas> getCari(String cari) {
        ObservableList<petugas> lp = FXCollections.observableArrayList();
        String sql = "SELECT * FROM petugas WHERE id_pet LIKE'%" + cari
                + "%' OR nama LIKE '%" + cari + "%' OR nip LIKE '%" + cari + "%'";
        try {
            ResultSet rs = koneksi.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                petugas pet = new petugas();
                pet.setNo(rs.getInt(1));
                pet.setId_pet(rs.getString(2));
                pet.setNama(rs.getString(3));
                pet.setSandi(rs.getString(4));
                pet.setNip(rs.getString(5));
                pet.setStatus(rs.getString(6));
                lp.add(pet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryPetugas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lp;
    }

    @Override
    public void getInfoLogin(String m, String n) {
        nip = m;
        hash.setHash(n);
    }
}
