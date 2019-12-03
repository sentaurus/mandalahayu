/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import mandalahayu.interfaceMurid;
import mandalahayu.model.murid;
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
public class queryMurid implements interfaceMurid {

    private final Connection koneksi;

    public queryMurid(Connection koneksi) {
        this.koneksi = koneksi;
    }

    @Override
    public void simpan(murid murid) {
        String cek = "SELECT * FROM murid WHERE id_mrd='"
                + murid.getId_mrd() + "'";
        String SQL = "INSERT INTO murid(id_mrd,nama,pend,kelas,jurusan) VALUES "
                + "(?,?,?,?,?)";
        try {
            ResultSet rs = koneksi.prepareStatement(cek).executeQuery();
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pemberitahuan!");
                alert.setHeaderText("ID murid sudah ada");
                alert.setContentText("harap masukkan ID murid yang berbeda!");
                alert.showAndWait();
            } else {
                PreparedStatement ps = koneksi.prepareStatement(SQL);
                ps.setString(1, murid.getId_mrd());
                ps.setString(2, murid.getNama());
                ps.setString(3, murid.getPend());
                ps.setString(4, murid.getKelas());
                ps.setString(5, murid.getJurusan());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryMurid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ubah(murid murid) {
        String SQL = "UPDATE murid SET nama=?,pend=?,kelas=?,jurusan=? WHERE "
                + "id_mrd=? OR nama=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(SQL);
            ps.setString(1, murid.getNama());
            ps.setString(2, murid.getPend());
            ps.setString(3, murid.getKelas());
            ps.setString(4, murid.getJurusan());
            ps.setString(5, murid.getId_mrd());
            ps.setString(6, murid.getNama());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(queryMurid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void hapus(murid murid) {
        String SQL = "DELETE FROM murid WHERE id_mrd=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(SQL);
            ps.setString(1, murid.getId_mrd());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(queryMurid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<murid> getAll() {
        String SQL = "SELECT * FROM murid";
        ObservableList<murid> lm = FXCollections.observableArrayList();
        try {
            ResultSet rs = koneksi.prepareStatement(SQL).executeQuery();
            while (rs.next()) {
                murid mur = new murid();
                mur.setId_mrd(rs.getString(1));
                mur.setNama(rs.getString(2));
                mur.setPend(rs.getString(3));
                mur.setKelas(rs.getString(4));
                mur.setJurusan(rs.getString(5));
                lm.add(mur);
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryMurid.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lm;
    }

    @Override
    public void cetak() {
        Map<String, Object> mso = new HashMap<>();
        try {
            File file = new File("src/mandalahayu/laporan/lapMurid.jrxml");
            JasperDesign jd = JRXmlLoader.load(file);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, mso, koneksi);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(queryMurid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<murid> getCari(String cari) {
        String SQL = "SELECT * FROM murid WHERE id_mrd LIKE '%" + cari
                + "%' OR nama LIKE '%" + cari + "%'";
        ObservableList<murid> lm = FXCollections.observableArrayList();
        try {
            ResultSet rs = koneksi.prepareStatement(SQL).executeQuery();
            while (rs.next()) {
                murid mur = new murid();
                mur.setId_mrd(rs.getString(1));
                mur.setNama(rs.getString(2));
                mur.setPend(rs.getString(3));
                mur.setKelas(rs.getString(4));
                mur.setJurusan(rs.getString(5));
                lm.add(mur);
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryMurid.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lm;
    }

}
