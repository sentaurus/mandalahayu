/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu;

import java.time.LocalDate;
import javafx.collections.ObservableList;
import mandalahayu.model.kasir;

/**
 *
 * @author Dika
 */
public interface interfaceKasir {

    void simpan(kasir kasir);

    void ubah(kasir kasir);

    void hapus(kasir kasir);

    ObservableList<kasir> getAll();

    void cetak();

    void kwitansi(String idAdm, LocalDate tanggal);
    
    ObservableList<kasir> getCari(String cari);
}
