/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.koneksi;

import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import mandalahayu.interfaceAdmin;
import mandalahayu.interfaceDash;
import mandalahayu.interfaceKasir;
import mandalahayu.interfaceMurid;
import mandalahayu.interfacePetugas;
import mandalahayu.interfaceSekolah;
import mandalahayu.query.queryAdmin;
import mandalahayu.query.queryDash;
import mandalahayu.query.queryKasir;
import mandalahayu.query.queryMurid;
import mandalahayu.query.queryPetugas;
import mandalahayu.query.querySekolah;

/**
 *
 * @author Dika
 */
public class koneksi {

    private static interfaceAdmin ina;
    private static interfaceKasir ink;
    private static interfaceMurid inm;
    private static interfaceSekolah ins;
    private static interfaceDash ind;
    private static interfacePetugas inp;
    private static Connection conn;

    private koneksi() {
    }

    private static Connection getKoneksi() {
        if (conn == null) {
            try {
                DriverManager.registerDriver(new Driver());
                conn = DriverManager.
                        getConnection("jdbc:mysql://localhost:3306/mandalahayu", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

    public static Connection getCek() {
        return getKoneksi();
    }

    public static interfaceAdmin getInAdmin() {
        if (ina == null) {
            ina = new queryAdmin(getKoneksi());
        }
        return ina;
    }

    public static interfaceKasir getInKasir() {
        if (ink == null) {
            ink = new queryKasir(getKoneksi());
        }
        return ink;
    }

    public static interfaceMurid getInMurid() {
        if (inm == null) {
            inm = new queryMurid(getKoneksi());
        }
        return inm;
    }

    public static interfaceSekolah getInSekolah() {
        if (ins == null) {
            ins = new querySekolah(getKoneksi());
        }
        return ins;
    }

    public static interfaceDash getInDash() {
        if (ind == null) {
            ind = new queryDash(getKoneksi());
        }
        return ind;
    }

    public static interfacePetugas getInPetugas() {
        if (inp == null) {
            inp = new queryPetugas(getKoneksi());
        }
        return inp;
    }

}
