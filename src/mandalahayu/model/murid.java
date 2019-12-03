/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dika
 */
public class murid {

    private final StringProperty id_mrd = new SimpleStringProperty();
    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty pend = new SimpleStringProperty();
    private final StringProperty kelas = new SimpleStringProperty();
    private final StringProperty jurusan = new SimpleStringProperty();

    public String getId_mrd() {
        return id_mrd.get();
    }

    public void setId_mrd(String value) {
        id_mrd.set(value);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String value) {
        nama.set(value);
    }

    public String getPend() {
        return pend.get();
    }

    public void setPend(String value) {
        pend.set(value);
    }

    public String getKelas() {
        return kelas.get();
    }

    public void setKelas(String value) {
        kelas.set(value);
    }

    public String getJurusan() {
        return jurusan.get();
    }

    public void setJurusan(String value) {
        jurusan.set(value);
    }

    public StringProperty getId_mrdProperty() {
        return id_mrd;
    }

    public StringProperty getNamaProperty() {
        return nama;
    }

    public StringProperty getPendProperty() {
        return pend;
    }

    public StringProperty getKelasProperty() {
        return kelas;
    }

    public StringProperty getJurusanProperty() {
        return jurusan;
    }
      

}
