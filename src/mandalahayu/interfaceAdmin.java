/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu;

import javafx.collections.ObservableList;
import mandalahayu.model.admin;

/**
 *
 * @author Dika
 */
public interface interfaceAdmin {

    void simpan(admin admin);

    void ubah(admin admin);

    void hapus(admin admin);

    ObservableList<admin> getAll();

    void cetak();
    
    ObservableList<admin> getCari(String cari);
}
