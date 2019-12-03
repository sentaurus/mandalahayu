/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu;

import javafx.collections.ObservableList;
import mandalahayu.model.petugas;

/**
 *
 * @author Dika
 */
public interface interfacePetugas {

    void simpan(petugas petugas);

    void ubah(petugas petugas);

    void hapus(petugas petugas);

    void cetak();

    void getInfoLogin(String m, String n);
    
    ObservableList<petugas> getAll();

    ObservableList<petugas> getLogin();
    
    ObservableList<petugas> getCari(String cari);
}
