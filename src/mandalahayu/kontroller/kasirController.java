/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.kontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import mandalahayu.koneksi.koneksi;
import mandalahayu.model.kasir;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Dika
 */
public class kasirController implements Initializable {

    @FXML
    private TextField cari;
    @FXML
    private TableView<kasir> tabel;
    @FXML
    private TableColumn<kasir, String> nama;
    @FXML
    private TableColumn<kasir, String> id_adm;
    @FXML
    private TableColumn<kasir, String> id_pet;
    @FXML
    private TableColumn<kasir, String> kategori;
    @FXML
    private TableColumn<kasir, Number> biaya;
    @FXML
    private TableColumn<kasir, LocalDate> tanggal;
    @FXML
    private TextField inAdm;
    @FXML
    private TextField inPet;
    @FXML
    private ComboBox<String> inKat;
    @FXML
    private TextField inBiaya;
    @FXML
    private DatePicker inTanggal;
    @FXML
    private Button simpan;
    @FXML
    private Button ubah;
    @FXML
    private Button hapus;
    @FXML
    private LineChart<String, Number> linechart;

    private kasir ksr = new kasir();
    private int no;
    private ObservableList<String> list = FXCollections.observableArrayList("- Pilih Kategori -", "SPP",
            "Uang Pangkal", "DP KBM", "KBM");
    @FXML
    private Button kwitansi;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inKat.setItems(list);
        nama.setCellValueFactory(p -> p.getValue().getNamaProperty());

        id_adm.setCellValueFactory(p -> p.getValue().getId_admProperty());

        id_pet.setCellValueFactory(p -> p.getValue().getId_petProperty());

        kategori.setCellValueFactory(p -> p.getValue().getKategoriProperty());

        biaya.setCellValueFactory(p -> p.getValue().getHargaProperty());

        tanggal.setCellValueFactory(p -> p.getValue().getTanggalProperty());

        cari.textProperty().addListener((ov, t, t1) -> aturTabel());

        aturTabel();
        mulai();
    }

    @FXML
    private void onklik() {
        if (tabel.getSelectionModel().getSelectedItem() != null) {
            no = tabel.getSelectionModel().getSelectedItem().getNo();
            inAdm.setText(tabel.getSelectionModel().getSelectedItem().getId_adm());
            inPet.setText(tabel.getSelectionModel().getSelectedItem().getId_pet());
            inKat.getSelectionModel().select(tabel.getSelectionModel().getSelectedItem().getKategori());
            inBiaya.setText(Integer.toString(tabel.getSelectionModel().getSelectedItem().getHarga()));
            inTanggal.setValue(tabel.getSelectionModel().getSelectedItem().getTanggal());
            simpan.setDisable(true);
            ubah.setDisable(false);
            hapus.setDisable(false);
            kwitansi.setDisable(false);
        }
    }

    @FXML
    private void onSimpan() {
        ksr.setId_adm(inAdm.getText());
        ksr.setId_pet(inPet.getText());
        ksr.setKategori(inKat.getSelectionModel().getSelectedItem());
        ksr.setHarga(Integer.parseInt(inBiaya.getText()));
        ksr.setTanggal(inTanggal.getValue());
        koneksi.getInKasir().simpan(ksr);
        aturTabel();
        mulai();
    }

    @FXML
    private void onUbah() {
        ksr.setNo(no);
        ksr.setId_adm(inAdm.getText());
        ksr.setId_pet(inPet.getText());
        ksr.setKategori(inKat.getSelectionModel().getSelectedItem());
        ksr.setHarga(Integer.parseInt(inBiaya.getText()));
        ksr.setTanggal(inTanggal.getValue());
        koneksi.getInKasir().ubah(ksr);
        aturTabel();
        mulai();
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
        kwitansi.setDisable(true);
    }

    @FXML
    private void onHapus() {
        ksr.setNo(no);
        koneksi.getInKasir().hapus(ksr);
        aturTabel();
        mulai();
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
        kwitansi.setDisable(true);
    }

    @FXML
    private void onKwitansi() {
        koneksi.getInKasir().kwitansi(inAdm.getText(), inTanggal.getValue());
    }

    private void aturTabel() {
        ObservableList<kasir> kasirs;
        if (cari.getText().equals("")) {
            kasirs = koneksi.getInKasir().getAll();
        } else {
            kasirs = koneksi.getInKasir().getCari(cari.getText());
        }
        tabel.setItems(kasirs);
    }

    private void mulai() {
        inAdm.setText("");
        inPet.setText("");
        inKat.getSelectionModel().selectFirst();
        inBiaya.setText("");
        inTanggal.setValue(LocalDate.now());
        simpan.setDisable(false);
        ubah.setDisable(true);
        hapus.setDisable(true);
        linechart.setData(koneksi.getInDash().getBChart());
    }

}
