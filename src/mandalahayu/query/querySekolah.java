/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import mandalahayu.interfaceSekolah;
import mandalahayu.model.sekolah;
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
public class querySekolah implements interfaceSekolah {

    private final Connection koneksi;

    public querySekolah(Connection koneksi) {
        this.koneksi = koneksi;
    }

    @Override
    public void simpan(sekolah sekolah) {
        String cek = "SELECT * FROM sekolah WHERE pend='"
                + sekolah.getPend() + "'";
        String SQL = "INSERT INTO sekolah(pend,hrg_spp,hrg_upkl,hrg_dkbm,hrg_skbm) "
                + "VALUES (?,?,?,?,?)";
        try {
            ResultSet rs = koneksi.prepareStatement(cek).executeQuery();
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pemberitahuan!");
                alert.setHeaderText("Data sekolah sudah ada");
                alert.setContentText("harap masukkan Data sekolah yang berbeda!");
                alert.showAndWait();
            } else {
                PreparedStatement ps = koneksi.prepareStatement(SQL);
                ps.setString(1, sekolah.getPend());
                ps.setInt(2, sekolah.getHrg_spp());
                ps.setInt(3, sekolah.getHrg_upkl());
                ps.setInt(4, sekolah.getHrg_dkbm());
                ps.setInt(5, sekolah.getHrg_skbm());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(querySekolah.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ubah(sekolah sekolah) {
        String SQL = "UPDATE sekolah SET hrg_spp=?,hrg_upkl=?,hrg_dkbm=?,"
                + "hrg_skbm=? WHERE pend=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(SQL);
            ps.setInt(1, sekolah.getHrg_spp());
            ps.setInt(2, sekolah.getHrg_upkl());
            ps.setInt(3, sekolah.getHrg_dkbm());
            ps.setInt(4, sekolah.getHrg_skbm());
            ps.setString(5, sekolah.getPend());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(querySekolah.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void hapus(sekolah sekolah) {
        String SQL = "DELETE FROM sekolah WHERE pend=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(SQL);
            ps.setString(1, sekolah.getPend());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(querySekolah.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<sekolah> getAll() {
        String SQL = "SELECT * FROM sekolah";
        ObservableList<sekolah> ls = FXCollections.observableArrayList();
        try {
            ResultSet rs = koneksi.prepareStatement(SQL).executeQuery();
            while (rs.next()) {
                sekolah sek = new sekolah();
                sek.setPend(rs.getString(1));
                sek.setHrg_spp(rs.getInt(2));
                sek.setHrg_upkl(rs.getInt(3));
                sek.setHrg_dkbm(rs.getInt(4));
                sek.setHrg_skbm(rs.getInt(5));
                ls.add(sek);
            }
        } catch (SQLException ex) {
            Logger.getLogger(querySekolah.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    @Override
    public void cetak() {
        Map<String, Object> mso = new HashMap<>();
        try {
            File file = new File("src/mandalahayu/laporan/lapSekolah.jrxml");
            JasperDesign jd = JRXmlLoader.load(file);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, mso, koneksi);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(querySekolah.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<sekolah> getCari(String cari) {
        String SQL = "SELECT * FROM sekolah WHERE pend LIKE '%" + cari + "%'";
        ObservableList<sekolah> ls = FXCollections.observableArrayList();
        try {
            ResultSet rs = koneksi.prepareStatement(SQL).executeQuery();
            while (rs.next()) {
                sekolah sek = new sekolah();
                sek.setPend(rs.getString(1));
                sek.setHrg_spp(rs.getInt(2));
                sek.setHrg_upkl(rs.getInt(3));
                sek.setHrg_dkbm(rs.getInt(4));
                sek.setHrg_skbm(rs.getInt(5));
                ls.add(sek);
            }
        } catch (SQLException ex) {
            Logger.getLogger(querySekolah.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

}
