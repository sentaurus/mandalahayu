/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.kontroller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mandalahayu.koneksi.koneksi;
import mandalahayu.model.sekolah;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Dika
 */
public class sekolahController implements Initializable {

    @FXML
    private TextField cari;
    @FXML
    private TableView<sekolah> tabel;
    @FXML
    private TableColumn<sekolah, String> pend;
    @FXML
    private TableColumn<sekolah, Number> spp;
    @FXML
    private TableColumn<sekolah, Number> uang_pkl;
    @FXML
    private TableColumn<sekolah, Number> dp_kbm;
    @FXML
    private TableColumn<sekolah, Number> kbm;
    @FXML
    private TextField inPend;
    @FXML
    private TextField inSpp;
    @FXML
    private TextField inUang_pkl;
    @FXML
    private TextField inDp_kbm;
    @FXML
    private TextField inKbm;
    @FXML
    private Button simpan;
    @FXML
    private Button ubah;
    @FXML
    private Button hapus;
    @FXML
    private BubbleChart<Number, Number> bubblechart;

    private sekolah skl = new sekolah();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bubblechart.setData(koneksi.getInDash().getBbChart());
        pend.setCellValueFactory(p -> p.getValue().getPendProperty());

        spp.setCellValueFactory(p -> p.getValue().getHrg_sppProperty());

        uang_pkl.setCellValueFactory(p -> p.getValue().getHrg_upklProperty());

        dp_kbm.setCellValueFactory(p -> p.getValue().getHrg_dkbmProperty());

        kbm.setCellValueFactory(p -> p.getValue().getHrg_skbmProperty());

        cari.textProperty().addListener((ov, t, t1) -> aturTabel());

        aturTabel();
        mulai();
    }

    @FXML
    private void onklik() {
        if (tabel.getSelectionModel().getSelectedItem() != null) {
            inPend.setText(tabel.getSelectionModel().getSelectedItem().getPend());
            inSpp.setText(String.valueOf(tabel.getSelectionModel().getSelectedItem().getHrg_spp()));
            inUang_pkl.setText(String.valueOf(tabel.getSelectionModel().getSelectedItem().getHrg_upkl()));
            inDp_kbm.setText(String.valueOf(tabel.getSelectionModel().getSelectedItem().getHrg_dkbm()));
            inKbm.setText(String.valueOf(tabel.getSelectionModel().getSelectedItem().getHrg_skbm()));
            simpan.setDisable(true);
            ubah.setDisable(false);
            hapus.setDisable(false);
        }
    }

    @FXML
    private void onSimpan() {
        skl.setPend(inPend.getText());
        skl.setHrg_spp(Integer.parseInt(inSpp.getText()));
        skl.setHrg_upkl(Integer.parseInt(inUang_pkl.getText()));
        skl.setHrg_dkbm(Integer.parseInt(inDp_kbm.getText()));
        skl.setHrg_skbm(Integer.parseInt(inKbm.getText()));
        koneksi.getInSekolah().simpan(skl);
        aturTabel();
        mulai();
    }

    @FXML
    private void onUbah() {
        skl.setPend(inPend.getText());
        skl.setHrg_spp(Integer.parseInt(inSpp.getText()));
        skl.setHrg_upkl(Integer.parseInt(inUang_pkl.getText()));
        skl.setHrg_dkbm(Integer.parseInt(inDp_kbm.getText()));
        skl.setHrg_skbm(Integer.parseInt(inKbm.getText()));
        koneksi.getInSekolah().ubah(skl);
        aturTabel();
        mulai();
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
    }

    @FXML
    private void onHapus() {
        skl.setPend(inPend.getText());
        koneksi.getInSekolah().hapus(skl);
        aturTabel();
        mulai();
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
    }

    private void aturTabel() {
        ObservableList<sekolah> sekolahs;
        if (cari.getText().equals("")) {
            sekolahs = koneksi.getInSekolah().getAll();
        } else {
            sekolahs = koneksi.getInSekolah().getCari(cari.getText());
        }
        tabel.setItems(sekolahs);
    }

    private void mulai() {
        inPend.setText("");
        inSpp.setText("");
        inUang_pkl.setText("");
        inDp_kbm.setText("");
        inKbm.setText("");
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
        bubblechart.setData(koneksi.getInDash().getBbChart());
    }

}
