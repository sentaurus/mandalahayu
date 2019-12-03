/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu.kontroller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import mandalahayu.koneksi.koneksi;
import mandalahayu.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Dika
 */
public class homeController implements Initializable {

    @FXML
    private AnchorPane login;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Label user;
    @FXML
    private ImageView kecil;
    @FXML
    private ImageView tutup;
    @FXML
    private AnchorPane parent;
    @FXML
    private TextField nip;
    @FXML
    private PasswordField sandi;
    @FXML
    private MenuBar menubar;

    private AnchorPane pane1;
    private double xOffSet = 0;
    private double yOffSet = 0;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menubar.setDisable(true);
        parent.setOnMousePressed((event) -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        parent.setOnMouseDragged((event) -> {
            main.stage.setX(event.getScreenX() - xOffSet);
            main.stage.setY(event.getScreenY() - yOffSet);
            main.stage.setOpacity(0.8f);
        });
        parent.setOnDragDone((event) -> main.stage.setOpacity(1.0f));
        parent.setOnMouseReleased((event) -> main.stage.setOpacity(1.0f));
        tutup.setOnMouseClicked((Event t) -> System.exit(0));
        kecil.setOnMouseClicked((Event t) -> main.stage.setIconified(true));
    }

    @FXML
    private void onDash() {
        try {
            pane1 = FXMLLoader.load(getClass().getResource("../fxml/dashboard.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane2.getChildren().clear();
        pane2.getChildren().setAll(pane1);
    }

    @FXML
    private void onMasuk() {
        koneksi.getInPetugas().getInfoLogin(nip.getText(), sandi.getText());
        if (koneksi.getInPetugas().getLogin().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pemberitahuan!");
            alert.setHeaderText("nip dan kata sandi tidak sesuai");
            alert.setContentText("harap masukkan nip dan kata sandi yang sesuai!");
            alert.showAndWait();
        } else {
            try {
                pane1 = FXMLLoader.load(getClass().getResource("../fxml/dashboard.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            pane2.getChildren().clear();
            pane2.getChildren().setAll(pane1);
            pane2.setVisible(true);
            login.setVisible(false);
            menubar.setDisable(false);
            user.setText(koneksi.getInPetugas().getLogin().get(0).getNama() + " : " +
                    koneksi.getInPetugas().getLogin().get(0).getStatus());
        }
    }

    @FXML
    private void onBatal() {
        System.exit(0);
    }

    @FXML
    private void onExit() {
        pane2.getChildren().clear();
        pane2.setVisible(false);
        login.setVisible(true);
        menubar.setDisable(true);
        nip.setText("");
        sandi.setText("");
        user.setText("");
    }

    @FXML
    private void onMurid() {
        try {
            pane1 = FXMLLoader.load(getClass().getResource("../fxml/murid.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane2.getChildren().clear();
        pane2.getChildren().setAll(pane1);
    }

    @FXML
    private void onAdmin() {
        try {
            pane1 = FXMLLoader.load(getClass().getResource("../fxml/administrasi.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane2.getChildren().clear();
        pane2.getChildren().setAll(pane1);
    }

    @FXML
    private void onBayar() {
        try {
            pane1 = FXMLLoader.load(getClass().getResource("../fxml/kasir.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane2.getChildren().clear();
        pane2.getChildren().setAll(pane1);
    }

    @FXML
    private void onPendidik() {
        try {
            pane1 = FXMLLoader.load(getClass().getResource("../fxml/sekolah.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane2.getChildren().clear();
        pane2.getChildren().setAll(pane1);
    }

    @FXML
    private void onPetugas() {
        try {
            pane1 = FXMLLoader.load(getClass().getResource("../fxml/petugas.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(homeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane2.getChildren().clear();
        pane2.getChildren().setAll(pane1);
    }

    @FXML
    private void onLappetugas() {
        koneksi.getInPetugas().cetak();
    }

    @FXML
    private void onLapmurid() {
        koneksi.getInMurid().cetak();
    }

    @FXML
    private void onLapadmin() {
        koneksi.getInAdmin().cetak();
    }

    @FXML
    private void onLapbayar() {
        koneksi.getInKasir().cetak();
    }

    @FXML
    private void onLappendidik() {
        koneksi.getInSekolah().cetak();
    }

}
