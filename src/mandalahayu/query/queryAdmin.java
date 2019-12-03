/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import mandalahayu.interfaceAdmin;
import mandalahayu.model.admin;
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
public class queryAdmin implements interfaceAdmin {

    private final Connection koneksi;

    public queryAdmin(Connection koneksi) {
        this.koneksi = koneksi;
    }

    @Override
    public void simpan(admin admin) {
        String cek = "SELECT * FROM administrasi WHERE id_adm='"
                + admin.getId_adm() + "' OR id_mrd = '" + admin.getId_mrd() + "'";
        String SQL = "INSERT INTO administrasi(id_adm,id_mrd,spp,uang_pkl,"
                + "dp_kbm,sisa_kbm) VALUES (?,?,?,?,?,?)";
        try {
            ResultSet rs = koneksi.prepareStatement(cek).executeQuery();
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pemberitahuan!");
                alert.setHeaderText("ID murid atau ID Administrasi sudah ada");
                alert.setContentText("harap masukkan ID murid atau ID Administrasi yang berbeda!");
                alert.showAndWait();
            } else {
                PreparedStatement ps = koneksi.prepareStatement(SQL);
                ps.setString(1, admin.getId_adm());
                ps.setString(2, admin.getId_mrd());
                ps.setInt(3, admin.getSpp());
                ps.setInt(4, admin.getUang_pkl());
                ps.setInt(5, admin.getDp_kbm());
                ps.setInt(6, admin.getSisa_kbm());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ubah(admin admin) {
        String SQL = "UPDATE administrasi SET id_adm=?,spp=?,uang_pkl=?,"
                + "dp_kbm=?,sisa_kbm=? WHERE id_adm=? OR id_mrd=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(SQL);
            ps.setString(1, admin.getId_adm());
            ps.setInt(2, admin.getSpp());
            ps.setInt(3, admin.getUang_pkl());
            ps.setInt(4, admin.getDp_kbm());
            ps.setInt(5, admin.getSisa_kbm());
            ps.setString(6, admin.getId_adm());
            ps.setString(7, admin.getId_mrd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(queryAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void hapus(admin admin) {
        String SQL = "DELETE FROM administrasi WHERE id_adm=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(SQL);
            ps.setString(1, admin.getId_adm());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(queryAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<admin> getAll() {
        String SQL = "SELECT murid.nama, administrasi.id_adm,administrasi.spp,"
                + "administrasi.uang_pkl,administrasi.dp_kbm,administrasi.sisa_kbm "
                + "FROM administrasi INNER JOIN murid ON administrasi.id_mrd = murid.id_mrd";
        ObservableList<admin> la = FXCollections.observableArrayList();
        try {
            ResultSet rs = koneksi.prepareStatement(SQL).executeQuery();
            while (rs.next()) {
                admin adm = new admin();
                adm.setNama(rs.getString(1));
                adm.setId_adm(rs.getString(2));
                adm.setSpp(rs.getInt(3));
                adm.setUang_pkl(rs.getInt(4));
                adm.setDp_kbm(rs.getInt(5));
                adm.setSisa_kbm(rs.getInt(6));
                la.add(adm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return la;
    }

    @Override
    public void cetak() {
        Map<String, Object> mso = new HashMap<>();
        try {
            File file = new File("src/mandalahayu/laporan/lapAdministrasi.jrxml");
            JasperDesign jd = JRXmlLoader.load(file);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, mso, koneksi);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(queryAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<admin> getCari(String cari) {
        String SQL = "SELECT murid.nama, administrasi.* FROM administrasi "
                + "INNER JOIN murid ON administrasi.id_mrd = murid.id_mrd WHERE "
                + "administrasi.id_adm LIKE '%" + cari + "%' OR administrasi.id_mrd LIKE '%"
                + cari + "%' OR murid.nama LIKE '%" + cari + "%'";
        ObservableList<admin> la = FXCollections.observableArrayList();
        try {
            ResultSet rs = koneksi.prepareStatement(SQL).executeQuery();
            while (rs.next()) {
                admin adm = new admin();
                adm.setNama(rs.getString(1));
                adm.setId_adm(rs.getString(2));
                adm.setId_mrd(rs.getString(3));
                adm.setSpp(rs.getInt(4));
                adm.setUang_pkl(rs.getInt(5));
                adm.setDp_kbm(rs.getInt(6));
                adm.setSisa_kbm(rs.getInt(7));
                la.add(adm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return la;
    }

}
