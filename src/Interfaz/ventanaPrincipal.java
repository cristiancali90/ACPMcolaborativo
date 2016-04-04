/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author admincris
 */
public class ventanaPrincipal extends Application {
    //creacion
    private Stage primaryStage;
    private AnchorPane login;
    
    
    @Override
    public void start(Stage primaryStage) {
       
        this.primaryStage = primaryStage;
        // bloque de codigo que le da nombre a ventana o frame en toda la aplicacion
        this.primaryStage.setTitle("ACPM UNIVALLE");
        // bloque de codigo para colocar icono a ventana o frame principal de toda la aplicacion
        this.primaryStage.getIcons().add(new Image("/Imagenes/ico.png"));
        login();
    }

     public void login() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
           loader.setLocation(ventanaPrincipal.class.getResource("/VistasFX/inicioSesion.fxml"));
          
            login = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(login);
            //codigo que carga archivo css a la ventana principal
            //scene.getStylesheets().add("Imagenes/estilos.css");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
        public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
