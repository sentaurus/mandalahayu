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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mandalahayu.koneksi.koneksi;
import mandalahayu.model.dash;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Dika
 */
public class dashboardController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private AreaChart<String, Number> areachart;
    @FXML
    private TableView<dash> tabel;
    @FXML
    private TableColumn<dash, Number> no;
    @FXML
    private TableColumn<dash, String> pend;
    @FXML
    private TableColumn<dash, String> id_mrd;
    @FXML
    private TableColumn<dash, String> nama;
    @FXML
    private TableColumn<dash, String> id_adm;
    @FXML
    private TableColumn<dash, String> kat;
    @FXML
    private TableColumn<dash, Number> harga;
    @FXML
    private TableColumn<dash, LocalDate> tgl;
    @FXML
    private TextField cari;

    private ObservableList<PieChart.Data> opd = FXCollections.observableArrayList(
            new PieChart.Data("Kelas X", Double.parseDouble(koneksi.getInDash().getPChart().get(0))),
            new PieChart.Data("Kelas XI", Double.parseDouble(koneksi.getInDash().getPChart().get(1))),
            new PieChart.Data("Kelas XII", Double.parseDouble(koneksi.getInDash().getPChart().get(2))));

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        areachart.setData(koneksi.getInDash().getALChart("10 tahun"));

        piechart.setData(opd);
        piechart.setClockwise(true);
        piechart.setLabelLineLength(50);
        piechart.setLabelsVisible(true);
        piechart.setStartAngle(180);

        no.setCellValueFactory(p -> p.getValue().getNoProperty());

        pend.setCellValueFactory(p -> p.getValue().getPendProperty());

        id_mrd.setCellValueFactory(p -> p.getValue().getId_mrdProperty());

        nama.setCellValueFactory(p -> p.getValue().getNamaProperty());

        id_adm.setCellValueFactory(p -> p.getValue().getId_admProperty());

        kat.setCellValueFactory(p -> p.getValue().getKatProperty());

        harga.setCellValueFactory(p -> p.getValue().getHargaProperty());

        tgl.setCellValueFactory(p -> p.getValue().getTglProperty());

        cari.textProperty().addListener((ov, t, t1) -> aturTabel());

        aturTabel();
    }

    private void aturTabel() {
        ObservableList<dash> dashs;
        if (cari.getText().equals("")) {
            dashs = koneksi.getInDash().getTabelDash();
        } else {
            dashs = koneksi.getInDash().getCari(cari.getText());
        }
        tabel.setItems(dashs);
    }
}
