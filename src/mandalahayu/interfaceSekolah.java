/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu;

import javafx.collections.ObservableList;
import mandalahayu.model.sekolah;

/**
 *
 * @author Dika
 */
public interface interfaceSekolah {

    void simpan(sekolah sekolah);

    void ubah(sekolah sekolah);

    void hapus(sekolah sekolah);

    ObservableList<sekolah> getAll();

    void cetak();
    
    ObservableList<sekolah> getCari(String cari);
}
