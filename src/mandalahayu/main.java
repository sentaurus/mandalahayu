/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandalahayu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Dika
 */
public class main extends Application {
    
    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/home.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("gambar/logo.png")));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        main.stage = stage;
        stage.show();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void play(String[] args) {
        // TODO code application logic here
        launch(args);
        
    }
    
}
