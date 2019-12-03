/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu;

import javafx.collections.ObservableList;
import mandalahayu.model.murid;

/**
 *
 * @author Dika
 */
public interface interfaceMurid {

    void simpan(murid murid);

    void ubah(murid murid);

    void hapus(murid murid);

    ObservableList<murid> getAll();

    void cetak();
    
    ObservableList<murid> getCari(String cari);
}
