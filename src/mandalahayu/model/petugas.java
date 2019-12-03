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
public class petugas {

    private final IntegerProperty no = new SimpleIntegerProperty();
    private final StringProperty id_pet = new SimpleStringProperty();
    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty sandi = new SimpleStringProperty();
    private final StringProperty nip = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public IntegerProperty getNoProperty() {
        return no;
    }
    
    public int getNo() {
        return no.get();
    }

    public void setNo(int value) {
        no.set(value);
    }
    
    public StringProperty getId_petProperty() {
        return id_pet;
    }
    
    public String getId_pet() {
        return id_pet.get();
    }

    public void setId_pet(String value) {
        id_pet.set(value);
    }
    
    public StringProperty getNamaProperty() {
        return nama;
    }
    
    public String getNama() {
        return nama.get();
    }

    public void setNama(String value) {
        nama.set(value);
    }

    public StringProperty getSandiProperty() {
        return sandi;
    }
    
    public String getSandi() {
        return sandi.get();
    }

    public void setSandi(String value) {
        sandi.set(value);
    }

    public StringProperty getNipProperty() {
        return nip;
    }
    
    public String getNip() {
        return nip.get();
    }

    public void setNip(String value) {
        nip.set(value);
    }

    public StringProperty getStatusProperty() {
        return status;
    }
    
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }
    
}
