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
 * @author Dika
 */
public class sekolah {

    private final StringProperty pend = new SimpleStringProperty();
    private final IntegerProperty hrg_spp = new SimpleIntegerProperty();
    private final IntegerProperty hrg_upkl = new SimpleIntegerProperty();
    private final IntegerProperty hrg_dkbm = new SimpleIntegerProperty();
    private final IntegerProperty hrg_skbm = new SimpleIntegerProperty();

    public String getPend() {
        return pend.get();
    }

    public void setPend(String value) {
        pend.set(value);
    }
    
    public StringProperty getPendProperty() {
        return pend;
    }

    public int getHrg_spp() {
        return hrg_spp.get();
    }

    public void setHrg_spp(int value) {
        hrg_spp.set(value);
    }
    
    public IntegerProperty getHrg_sppProperty() {
        return hrg_spp;
    }

    public int getHrg_upkl() {
        return hrg_upkl.get();
    }

    public void setHrg_upkl(int value) {
        hrg_upkl.set(value);
    }
    
    public IntegerProperty getHrg_upklProperty() {
        return hrg_upkl;
    }

    public int getHrg_dkbm() {
        return hrg_dkbm.get();
    }

    public void setHrg_dkbm(int value) {
        hrg_dkbm.set(value);
    }
    
    public IntegerProperty getHrg_dkbmProperty() {
        return hrg_dkbm;
    }

    public int getHrg_skbm() {
        return hrg_skbm.get();
    }

    public void setHrg_skbm(int value) {
        hrg_skbm.set(value);
    }
    
    public IntegerProperty getHrg_skbmProperty() {
        return hrg_skbm;
    }

}
