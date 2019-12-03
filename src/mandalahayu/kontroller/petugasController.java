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
import javafx.scene.control.*;
import mandalahayu.keamanan.hashing;
import mandalahayu.koneksi.koneksi;
import mandalahayu.model.petugas;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Dika
 */
public class petugasController implements Initializable {

    @FXML
    private TableView<petugas> tabel;
    @FXML
    private TableColumn<petugas, Number> no;
    @FXML
    private TableColumn<petugas, String> idPet;
    @FXML
    private TableColumn<petugas, String> nama;
    @FXML
    private TableColumn<petugas, String> nip;
    @FXML
    private TableColumn<petugas, String> status;
    @FXML
    private TextField inIdpet;
    @FXML
    private TextField inNama;
    @FXML
    private TextField inNip;
    @FXML
    private ComboBox<String> inStatus;
    @FXML
    private PasswordField inSandi1;
    @FXML
    private PasswordField inSandi2;
    @FXML
    private Button simpan;
    @FXML
    private Button ubah;
    @FXML
    private Button hapus;
    @FXML
    private TextField cari;

    private String snd1;
    private int nomor;
    private petugas ptgs = new petugas();
    private hashing hash = new hashing();
    private ObservableList<String> list = FXCollections.observableArrayList("User", "Admin");

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inStatus.setItems(list);
        no.setCellValueFactory(p -> p.getValue().getNoProperty());

        idPet.setCellValueFactory(p -> p.getValue().getId_petProperty());

        nama.setCellValueFactory(p -> p.getValue().getNamaProperty());

        nip.setCellValueFactory(p -> p.getValue().getNipProperty());

        status.setCellValueFactory(p -> p.getValue().getStatusProperty());

        cari.textProperty().addListener((ov, t, t1) -> aturTabel());

        aturTabel();
        mulai();
    }

    @FXML
    private void onklik() {
        if (tabel.getSelectionModel().getSelectedItem() != null) {
            nomor = tabel.getSelectionModel().getSelectedItem().getNo();
            inIdpet.setText(tabel.getSelectionModel().getSelectedItem().getId_pet());
            inNama.setText(tabel.getSelectionModel().getSelectedItem().getNama());
            inNip.setText(tabel.getSelectionModel().getSelectedItem().getNip());
            inStatus.getSelectionModel().select(tabel.getSelectionModel().getSelectedItem().getStatus());
        }
    }

    @FXML
    private void onSimpan() {
        cekSandi();
        hash.setHash(snd1);
        if (!cekSandi().isEmpty()) {
            ptgs.setId_pet(inIdpet.getText());
            ptgs.setNama(inNama.getText());
            ptgs.setNip(inNip.getText());
            ptgs.setSandi(hash.getHash());
            ptgs.setStatus(inStatus.getSelectionModel().getSelectedItem());
            koneksi.getInPetugas().simpan(ptgs);
            aturTabel();
            mulai();
        }
    }

    @FXML
    private void onUbah() {
        cekSandi();
        hash.setHash(snd1);
        if (!cekSandi().isEmpty()) {
            ptgs.setNo(nomor);
            ptgs.setId_pet(inIdpet.getText());
            ptgs.setNama(inNama.getText());
            ptgs.setNip(inNip.getText());
            ptgs.setSandi(hash.getHash());
            ptgs.setStatus(inStatus.getSelectionModel().getSelectedItem());
            koneksi.getInPetugas().ubah(ptgs);
            aturTabel();
            mulai();
        }
    }

    @FXML
    private void onHapus() {
        ptgs.setNo(nomor);
        koneksi.getInPetugas().hapus(ptgs);
        aturTabel();
        mulai();
    }

    private void aturTabel() {
        ObservableList<petugas> petugass;
        if (cari.getText().equals("")) {
            petugass = koneksi.getInPetugas().getAll();
        } else {
            petugass = koneksi.getInPetugas().getCari(cari.getText());
        }
        tabel.setItems(petugass);
    }

    private void mulai() {
        if (koneksi.getInPetugas().getLogin().get(0).getStatus().equals("Admin")) {
            simpan.setDisable(false);
            ubah.setDisable(false);
            hapus.setDisable(false);
        } else {
            simpan.setDisable(true);
            ubah.setDisable(true);
            hapus.setDisable(true);

        }
        inIdpet.setText("");
        inNama.setText("");
        inNip.setText("");
        inStatus.getSelectionModel().selectFirst();
        inSandi1.setText("");
        inSandi2.setText("");
    }

    private String cekSandi() {
        if (inSandi1.getText().equals(inSandi2.getText())) {
            snd1 = inSandi1.getText();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan!");
            alert.setHeaderText("konfirmasi kata sandi tidak sesuai");
            alert.setContentText("harap masukkan konfirmasi kata sandi yang sesuai!");
            alert.showAndWait();
        }
        return snd1;
    }
}
