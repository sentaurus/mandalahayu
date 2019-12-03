/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dika Pramudia
 */
public class admin {

    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty id_adm = new SimpleStringProperty();
    private final StringProperty id_mrd = new SimpleStringProperty();
    private final IntegerProperty spp = new SimpleIntegerProperty();
    private final IntegerProperty uang_pkl = new SimpleIntegerProperty();
    private final IntegerProperty dp_kbm = new SimpleIntegerProperty();
    private final IntegerProperty sisa_kbm = new SimpleIntegerProperty();

    public StringProperty getNamaProperty() {
        return nama;
    }
    
    public String getNama() {
        return nama.get();
    }

    public void setNama(String value) {
        nama.set(value);
    }
    
    public StringProperty getId_admProperty() {
        return id_adm;
    }
    
    public String getId_adm() {
        return id_adm.get();
    }

    public void setId_adm(String value) {
        id_adm.set(value);
    }

    public StringProperty getId_mrdProperty() {
        return id_mrd;
    }
    
    public String getId_mrd() {
        return id_mrd.get();
    }

    public void setId_mrd(String value) {
        id_mrd.set(value);
    }

    public IntegerProperty getSppProperty() {
        return spp;
    }
    
    public int getSpp() {
        return spp.get();
    }

    public void setSpp(int value) {
        spp.set(value);
    }

    public IntegerProperty getUang_pklProperty() {
        return uang_pkl;
    }
    
    public int getUang_pkl() {
        return uang_pkl.get();
    }

    public void setUang_pkl(int value) {
        uang_pkl.set(value);
    }
    
    public IntegerProperty getDp_kbmProperty() {
        return dp_kbm;
    }

    public int getDp_kbm() {
        return dp_kbm.get();
    }

    public void setDp_kbm(int value) {
        dp_kbm.set(value);
    }
    
    public IntegerProperty getSisa_kbmProperty() {
        return sisa_kbm;
    }
    
    public int getSisa_kbm() {
        return sisa_kbm.get();
    }

    public void setSisa_kbm(int value) {
        sisa_kbm.set(value);
    }

}
