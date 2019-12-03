/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import mandalahayu.interfaceDash;
import mandalahayu.model.dash;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dika
 */
public class queryDash implements interfaceDash {

    private final Connection koneksi;
    private final LocalDate ld = LocalDate.now();
    private final int year = ld.getYear();

    public queryDash(Connection koneksi) {
        this.koneksi = koneksi;
    }

    @Override
    public ObservableList<XYChart.Series<String, Number>> getALChart(String n) {
        ObservableList<XYChart.Series<String, Number>> ols = FXCollections.observableArrayList();
        int awal;
        Series<String, Number> x = new Series<>();
        x.setName("X");
        Series<String, Number> xi = new Series<>();
        xi.setName("XI");
        Series<String, Number> xii = new Series<>();
        xii.setName("XII");
        if (n.equals("10 tahun")) {
            awal = year - 9;
        } else {
            awal = year - 3;
        }
        for (int i = awal; i <= year; i++) {
            String sql = "SELECT "
                    + "COUNT(if(pend LIKE 'X',1,NULL)) 'X',"
                    + "COUNT(if(pend LIKE 'XI',1,NULL)) 'XI',"
                    + "COUNT(if(pend LIKE 'XII',1,NULL)) 'XII'"
                    + "FROM murid WHERE id_mrd LIKE '%" + i + "'";
            try {
                ResultSet rs = koneksi.prepareStatement(sql).executeQuery();
                while (rs.next()) {
                    x.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(1)));
                    xi.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(2)));
                    xii.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(3)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(queryDash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ols.addAll(x,xi,xii);
        return ols;
    }

    @Override
    public ObservableList<dash> getTabelDash() {
        int no = 1;
        ObservableList<dash> ld = FXCollections.observableArrayList();
        String sql = "SELECT sekolah.pend, murid.id_mrd,murid.nama, "
                + "administrasi.id_adm,pembayaran.kategori,pembayaran.harga,"
                + "pembayaran.tanggal FROM sekolah INNER JOIN murid ON "
                + "sekolah.pend = murid.pend INNER JOIN administrasi ON "
                + "murid.id_mrd = administrasi.id_mrd INNER JOIN pembayaran ON "
                + "administrasi.id_adm = pembayaran.id_adm ORDER BY "
                + "pembayaran.tanggal DESC ";
        try {
            ResultSet rs = koneksi.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                dash dashbor = new dash();
                dashbor.setNo(no);
                dashbor.setPend(rs.getString(1));
                dashbor.setId_mrd(rs.getString(2));
                dashbor.setNama(rs.getString(3));
                dashbor.setId_adm(rs.getString(4));
                dashbor.setKat(rs.getString(5));
                dashbor.setHarga(rs.getInt(6));
                dashbor.setTgl(rs.getDate(7).toLocalDate());
                ld.add(dashbor);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryDash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ld;
    }

    @Override
    public ObservableList<String> getPChart() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        int x = 0, xi = 0, xii = 0;
        for (int k = year - 3; k <= year; k++) {
            String sql = "SELECT "
                    + "COUNT(if(pend LIKE 'X',1,NULL)) 'X', "
                    + "COUNT(if(pend LIKE 'XI',1,NULL)) 'XI',"
                    + "COUNT(if(pend LIKE 'XII',1,NULL)) 'XII' "
                    + "FROM murid WHERE id_mrd LIKE '%" + k + "'";
            try {
                ResultSet rs = koneksi.prepareStatement(sql).executeQuery();
                while (rs.next()) {
                    x += rs.getInt(1);
                    xi += rs.getInt(2);
                    xii += rs.getInt(3);
                }
            } catch (SQLException ex) {
                Logger.getLogger(queryDash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ols.addAll(Integer.toString(x),Integer.toString(xi),Integer.toString(xii));
        return ols;
    }

    @Override
    public ObservableList<Series<String, Number>> getSChart() {
        ObservableList<XYChart.Series<String, Number>> ols = FXCollections.observableArrayList();
        Series<String, Number> spp = new Series<>();
        spp.setName("SPP");
        Series<String, Number> uang_pkl = new Series<>();
        uang_pkl.setName("Uang Pangkal");
        Series<String, Number> dp_kbm = new Series<>();
        dp_kbm.setName("DP KBM");
        Series<String, Number> sisa_kbm = new Series<>();
        sisa_kbm.setName("KBM");
        for (int i = year - 3; i <= year; i++) {
            String sql = "SELECT COUNT(if(spp >600000 ,1,NULL)) 'SPP',"
                    + "COUNT(if(uang_pkl >0 ,1,NULL)) 'Uang Pangkal', "
                    + "COUNT(if(dp_kbm >0,1,NULL)) 'DP KBM',"
                    + "COUNT(if(sisa_kbm >0 ,1,NULL)) 'KBM'"
                    + "FROM administrasi WHERE id_mrd LIKE '%" + i + "'";
            try {
                ResultSet rs = koneksi.prepareStatement(sql).executeQuery();
                while (rs.next()) {
                    spp.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(1)));
                    uang_pkl.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(2)));
                    dp_kbm.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(3)));
                    sisa_kbm.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(4)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(queryDash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ols.addAll(spp, uang_pkl, dp_kbm, sisa_kbm);
        return ols;
    }

    @Override
    public ObservableList<Series<String, Number>> getBChart() {
        ObservableList<XYChart.Series<String, Number>> ols = FXCollections.observableArrayList();
        Series<String, Number> spp = new Series<>();
        spp.setName("SPP");
        Series<String, Number> uang_pkl = new Series<>();
        uang_pkl.setName("Uang Pangkal");
        Series<String, Number> dp_kbm = new Series<>();
        dp_kbm.setName("DP KBM");
        Series<String, Number> sisa_kbm = new Series<>();
        sisa_kbm.setName("KBM");
        for (int i = year - 3; i <= year; i++) {
            String sql = "SELECT COUNT(if(kategori = 'SPP' AND harga > 0,1,NULL)) 'SPP',"
                    + "COUNT(if(kategori = 'Uang Pangkal' AND harga > 0,1,NULL)) 'Uang Pangkal',"
                    + "COUNT(if(kategori = 'DP KBM' AND harga > 0,1,NULL)) 'DP KBM',"
                    + "COUNT(if(kategori = 'KBM' AND harga > 0,1,NULL)) 'KBM'"
                    + "FROM pembayaran WHERE YEAR(tanggal) LIKE '" + i + "%'";
            try {
                ResultSet rs = koneksi.prepareStatement(sql).executeQuery();
                while (rs.next()) {
                    spp.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(1)));
                    uang_pkl.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(2)));
                    dp_kbm.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(3)));
                    sisa_kbm.getData().add(new XYChart.Data(Integer.toString(i), rs.getInt(4)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(queryDash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ols.addAll(spp, uang_pkl, dp_kbm, sisa_kbm);
        return ols;
    }

    @Override
    public ObservableList<Series<Number, Number>> getBbChart() {
        ObservableList<XYChart.Series<Number, Number>> ols = FXCollections.observableArrayList();
        Series<Number, Number> x = new Series<>();
        x.setName("X");
        Series<Number, Number> xi = new Series<>();
        xi.setName("XI");
        Series<Number, Number> xii = new Series<>();
        xii.setName("XII");
        for (int i = 0; i <= 4; i++) {
            String sql = "SELECT "
                    + "SUM(case when pend LIKE 'X%' then "
                    + "(hrg_spp+hrg_upkl+hrg_dkbm+hrg_skbm) ELSE 0 END) TK,"
                    + "SUM(case when pend LIKE 'XI%' then "
                    + "(hrg_spp+hrg_upkl+hrg_dkbm+hrg_skbm) ELSE 0 END) SD,"
                    + "SUM(case when pend LIKE 'XII%' then "
                    + " FROM sekolah";
            try {
                ResultSet rs = koneksi.prepareStatement(sql).executeQuery();
                while (rs.next()) {
                    float persen = rs.getFloat(1) + rs.getFloat(2) + rs.getFloat(3) + rs.getFloat(4) + rs.getFloat(5);
                    x.getData().add(new XYChart.Data(rs.getInt(1), rs.getInt(1), rs.getInt(1) / 5));
                    xi.getData().add(new XYChart.Data(rs.getInt(2), rs.getInt(2), rs.getInt(2) / 5));
                    xii.getData().add(new XYChart.Data(rs.getInt(3), rs.getInt(3), rs.getInt(3) / 5));
                }
            } catch (SQLException ex) {
                Logger.getLogger(queryDash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ols.addAll(x,xi,xii);
        return ols;
    }

    @Override
    public ObservableList<dash> getCari(String cari) {
        int no = 1;
        ObservableList<dash> ld = FXCollections.observableArrayList();
        String sql = "SELECT sekolah.pend, murid.id_mrd,murid.nama, "
                + "administrasi.id_adm,pembayaran.kategori,pembayaran.harga,"
                + "pembayaran.tanggal FROM sekolah INNER JOIN murid ON "
                + "sekolah.pend = murid.pend INNER JOIN administrasi ON "
                + "murid.id_mrd = administrasi.id_mrd INNER JOIN pembayaran ON "
                + "administrasi.id_adm = pembayaran.id_adm WHERE murid.id_mrd LIKE '%"
                + cari + "%' OR murid.nama LIKE '%" + cari + "%'ORDER BY "
                + "pembayaran.tanggal DESC ";
        try {
            ResultSet rs = koneksi.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                dash dashbor = new dash();
                dashbor.setNo(no);
                dashbor.setPend(rs.getString(1));
                dashbor.setId_mrd(rs.getString(2));
                dashbor.setNama(rs.getString(3));
                dashbor.setId_adm(rs.getString(4));
                dashbor.setKat(rs.getString(5));
                dashbor.setHarga(rs.getInt(6));
                dashbor.setTgl(rs.getDate(7).toLocalDate());
                ld.add(dashbor);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(queryDash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ld;
    }

}
