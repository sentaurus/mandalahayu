/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import mandalahayu.model.dash;

/**
 *
 * @author Dika
 */
public interface interfaceDash {
    
    ObservableList<dash> getTabelDash();
    
    ObservableList<String> getPChart();
    
    ObservableList<dash> getCari(String cari);
    
    ObservableList<XYChart.Series<String, Number>> getSChart();
    
    ObservableList<XYChart.Series<String, Number>> getBChart();
    
    ObservableList<XYChart.Series<String, Number>> getALChart(String n);
    
    ObservableList<XYChart.Series<Number, Number>> getBbChart();
}
