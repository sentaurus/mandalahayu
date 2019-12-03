/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.kontroller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mandalahayu.koneksi.koneksi;
import mandalahayu.model.murid;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Dika
 */
public class muridController implements Initializable {

    @FXML
    private BarChart<String, Number> barchart;
    @FXML
    private TextField cari;
    @FXML
    private TableView<murid> tabel;
    @FXML
    private TableColumn<murid, String> id_mrd;
    @FXML
    private TableColumn<murid, String> nama;
    @FXML
    private TableColumn<murid, String> pend;
    @FXML
    private TableColumn<murid, String> kelas;
    @FXML
    private TableColumn<murid, String> jurus;
    @FXML
    private TextField inNama;
    @FXML
    private TextField inId_mrd;
    @FXML
    private TextField inJenjang;
    @FXML
    private TextField inKelas;
    @FXML
    private TextField inJrsn;
    @FXML
    private Button simpan;
    @FXML
    private Button ubah;
    @FXML
    private Button hapus;

    private murid mrd = new murid();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_mrd.setCellValueFactory(p -> p.getValue().getId_mrdProperty());

        nama.setCellValueFactory(p -> p.getValue().getNamaProperty());

        pend.setCellValueFactory(p -> p.getValue().getPendProperty());

        kelas.setCellValueFactory(p -> p.getValue().getKelasProperty());

        jurus.setCellValueFactory(p -> p.getValue().getJurusanProperty());

        cari.textProperty().addListener((ov, t, t1) -> aturTabel());

        aturTabel();
        mulai();
    }

    @FXML
    private void onSimpan() {
        mrd.setId_mrd(inId_mrd.getText());
        mrd.setNama(inNama.getText());
        mrd.setPend(inJenjang.getText());
        mrd.setKelas(inKelas.getText());
        mrd.setJurusan(inJrsn.getText());
        koneksi.getInMurid().simpan(mrd);
        aturTabel();
        mulai();
    }

    @FXML
    private void onUbah() {
        mrd.setId_mrd(inId_mrd.getText());
        mrd.setNama(inNama.getText());
        mrd.setPend(inJenjang.getText());
        mrd.setKelas(inKelas.getText());
        mrd.setJurusan(inJrsn.getText());
        koneksi.getInMurid().ubah(mrd);
        aturTabel();
        mulai();
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
    }

    @FXML
    private void onHapus() {
        mrd.setId_mrd(inId_mrd.getText());
        koneksi.getInMurid().hapus(mrd);
        aturTabel();
        mulai();
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
    }

    @FXML
    private void onklik() {
        if (tabel.getSelectionModel().getSelectedItem() != null) {
            inId_mrd.setText(tabel.getSelectionModel().getSelectedItem().getId_mrd());
            inNama.setText(tabel.getSelectionModel().getSelectedItem().getNama());
            inJenjang.setText(tabel.getSelectionModel().getSelectedItem().getPend());
            inKelas.setText(tabel.getSelectionModel().getSelectedItem().getKelas());
            inJrsn.setText(tabel.getSelectionModel().getSelectedItem().getJurusan());
            simpan.setDisable(true);
            ubah.setDisable(false);
            hapus.setDisable(false);
        }
    }

    private void aturTabel() {
        ObservableList<murid> murids;
        if (cari.getText().equals("")) {
            murids = koneksi.getInMurid().getAll();
        } else {
            murids = koneksi.getInMurid().getCari(cari.getText());
        }
        tabel.setItems(murids);
    }

    private void mulai() {
        inId_mrd.setText("");
        inNama.setText("");
        inJenjang.setText("");
        inKelas.setText("");
        inJrsn.setText("");
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
        barchart.setData(koneksi.getInDash().getALChart("4 tahun"));
    }

}
