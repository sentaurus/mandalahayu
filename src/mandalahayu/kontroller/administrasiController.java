/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.kontroller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mandalahayu.koneksi.koneksi;
import mandalahayu.model.admin;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Dika
 */
public class administrasiController implements Initializable {

    @FXML
    private ScatterChart<String, Number> scatchart;
    @FXML
    private TextField cari;
    @FXML
    private TableView<admin> tabel;
    @FXML
    private TableColumn<admin, String> nama;
    @FXML
    private TableColumn<admin, String> id_adm;
    @FXML
    private TableColumn<admin, Number> spp;
    @FXML
    private TableColumn<admin, Number> uang_pkl;
    @FXML
    private TableColumn<admin, Number> dp_kbm;
    @FXML
    private TableColumn<admin, Number> sisa_kbm;
    @FXML
    private TextField inId_adm;
    @FXML
    private TextField inSpp;
    @FXML
    private TextField inUang_pkl;
    @FXML
    private TextField inDp_kbm;
    @FXML
    private TextField inSisa_kbm;
    @FXML
    private Button simpan;
    @FXML
    private Button ubah;
    @FXML
    private Button hapus;

    private String inId_mrd;
    private admin adm = new admin();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nama.setCellValueFactory(p -> p.getValue().getNamaProperty());

        id_adm.setCellValueFactory(p -> p.getValue().getId_admProperty());

        spp.setCellValueFactory(p -> p.getValue().getSppProperty());

        uang_pkl.setCellValueFactory(p -> p.getValue().getUang_pklProperty());

        dp_kbm.setCellValueFactory(p -> p.getValue().getDp_kbmProperty());

        sisa_kbm.setCellValueFactory(p -> p.getValue().getSisa_kbmProperty());

        cari.textProperty().addListener((ov, t, t1) -> aturTabel());

        aturTabel();
        mulai();
    }

    @FXML
    private void onklik() {
        if (tabel.getSelectionModel().getSelectedItem() != null) {
            inId_adm.setText(tabel.getSelectionModel().getSelectedItem().getId_adm());
            inId_mrd = tabel.getSelectionModel().getSelectedItem().getId_adm();
            inSpp.setText(Integer.toString(tabel.getSelectionModel().getSelectedItem().getSpp()));
            inUang_pkl.setText(Integer.toString(tabel.getSelectionModel().getSelectedItem().getDp_kbm()));
            inDp_kbm.setText(Integer.toString(tabel.getSelectionModel().getSelectedItem().getDp_kbm()));
            inSisa_kbm.setText(Integer.toString(tabel.getSelectionModel().getSelectedItem().getSisa_kbm()));
            simpan.setDisable(true);
            ubah.setDisable(false);
            hapus.setDisable(false);
        }
    }

    @FXML
    private void onSimpan() {
        adm.setId_adm(inId_adm.getText());
        adm.setId_mrd(inId_mrd);
        adm.setSpp(Integer.parseInt(inSpp.getText()));
        adm.setUang_pkl(Integer.parseInt(inUang_pkl.getText()));
        adm.setDp_kbm(Integer.parseInt(inDp_kbm.getText()));
        adm.setSisa_kbm(Integer.parseInt(inSisa_kbm.getText()));
        koneksi.getInAdmin().simpan(adm);
        aturTabel();
        mulai();
    }

    @FXML
    private void onUbah() {
        adm.setId_adm(inId_adm.getText());
        adm.setId_mrd(inId_mrd);
        adm.setSpp(Integer.parseInt(inSpp.getText()));
        adm.setUang_pkl(Integer.parseInt(inUang_pkl.getText()));
        adm.setDp_kbm(Integer.parseInt(inDp_kbm.getText()));
        adm.setSisa_kbm(Integer.parseInt(inSisa_kbm.getText()));
        koneksi.getInAdmin().ubah(adm);
        aturTabel();
        mulai();
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
    }

    @FXML
    private void onHapus() {
        adm.setId_adm(inId_adm.getText());
        koneksi.getInAdmin().hapus(adm);
        aturTabel();
        mulai();
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
    }

    private void aturTabel() {
        ObservableList<admin> admins;
        if (cari.getText().equals("")) {
            admins = koneksi.getInAdmin().getAll();
        } else {
            admins = koneksi.getInAdmin().getCari(cari.getText());
        }
        tabel.setItems(admins);
    }

    private void mulai() {
        inId_adm.setText("");
        inSpp.setText("");
        inUang_pkl.setText("");
        inDp_kbm.setText("");
        inSisa_kbm.setText("");
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
        scatchart.setData(koneksi.getInDash().getSChart());
    }

}
