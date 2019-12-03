/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.model;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dika
 */
public class kasir {
    
    private final StringProperty nama = new SimpleStringProperty();
    private final IntegerProperty no = new SimpleIntegerProperty();
    private final StringProperty id_adm = new SimpleStringProperty();
    private final StringProperty id_pet = new SimpleStringProperty();
    private final StringProperty kategori = new SimpleStringProperty();
    private final IntegerProperty harga = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> tanggal = new SimpleObjectProperty<>();

    public String getNama() {
        return nama.get();
    }

    public void setNama(String value) {
        nama.set(value);
    }

    public StringProperty getNamaProperty() {
        return nama;
    }
    public int getNo() {
        return no.get();
    }

    public void setNo(int value) {
        no.set(value);
    }
    
    public IntegerProperty getNoProperty() {
        return no;
    }

    public String getId_adm() {
        return id_adm.get();
    }

    public void setId_adm(String value) {
        id_adm.set(value);
    }

    public StringProperty getId_admProperty() {
        return id_adm;
    }

    public String getId_pet() {
        return id_pet.get();
    }

    public void setId_pet(String value) {
        id_pet.set(value);
    }

    public StringProperty getId_petProperty() {
        return id_pet;
    }

    public String getKategori() {
        return kategori.get();
    }

    public void setKategori(String value) {
        kategori.set(value);
    }

    public StringProperty getKategoriProperty() {
        return kategori;
    }

    public int getHarga() {
        return harga.get();
    }

    public void setHarga(int value) {
        harga.set(value);
    }

    public IntegerProperty getHargaProperty() {
        return harga;
    }

    public LocalDate getTanggal() {
        return tanggal.get();
    }

    public void setTanggal(LocalDate value) {
        tanggal.set(value);
    }

    public ObjectProperty<LocalDate> getTanggalProperty() {
        return tanggal;
    }

}
