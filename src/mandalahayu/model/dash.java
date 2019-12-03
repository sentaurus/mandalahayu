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
public class dash {
    
    private final IntegerProperty no = new SimpleIntegerProperty();
    private final StringProperty pend = new SimpleStringProperty();
    private final StringProperty id_mrd = new SimpleStringProperty();
    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty id_adm = new SimpleStringProperty();
    private final StringProperty kat = new SimpleStringProperty();
    private final IntegerProperty harga = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> tgl = new SimpleObjectProperty<>();

    public IntegerProperty getNoProperty() {
        return no;
    }
    
    public void setNo(int value){
        no.set(value);
    }
    
    public int getNo(){
        return no.get();
    }

    public StringProperty getPendProperty() {
        return pend;
    }
    
    public void setPend(String value){
        pend.set(value);
    }
    
    public String getPend(){
        return pend.get();
    }

    public StringProperty getId_mrdProperty() {
        return id_mrd;
    }
    
    public void setId_mrd(String value){
        id_mrd.set(value);
    }
    
    public String getId_mrd(){
        return id_mrd.get();
    }

    public StringProperty getNamaProperty() {
        return nama;
    }
    
    public void setNama(String value){
        nama.set(value);
    }
    
    public String getNama(){
        return nama.get();
    }

    public StringProperty getId_admProperty() {
        return id_adm;
    }
    
    public void setId_adm(String value){
        id_adm.set(value);
    }
    
    public String getId_adm(){
        return id_adm.get();
    }

    public StringProperty getKatProperty() {
        return kat;
    }
    
    public void setKat(String value){
        kat.set(value);
    }
    
    public String getKat(){
        return kat.get();
    }

    public IntegerProperty getHargaProperty() {
        return harga;
    }
    
    public void setHarga(int value){
        harga.set(value);
    }
    
    public int getHarga(){
        return harga.get();
    }

    public ObjectProperty<LocalDate> getTglProperty() {
        return tgl;
    }
    
    public void setTgl(LocalDate value){
        tgl.set(value);
    }
    
    public LocalDate getTgl(){
        return tgl.get();
    }
}
