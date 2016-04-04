/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.Usuario;
import Controladores.ControladorUsuario;
import Validadores.CampoNumerico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admincris
 */
public class InicioSesionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ControladorUsuario controlauser= new ControladorUsuario();
    
    // instanciamiento de los componentes
    
    @FXML
    private  TextField txtUsuario;
    @FXML
   private  PasswordField txtContrasena;
    
    @FXML
    private Button btnConectar;
    @FXML
    private Label lblMensaje;
    
    
    @FXML
            private Hyperlink link;
    
    //objeto de la clase CampoNumerico para validar numeros en txtUsuario
    CampoNumerico validaNum= new CampoNumerico();
    
    
    
    // metodo constructor del panel javafx
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        // TODO
        
        txtUsuario.setStyle("-fx-font-size: 12pt;");
        
         
       ///********************************************************
        // este bloque de codigo valida cuando se tiene el foco en el campo de texto txtUser y cuando se pierde
        txtUsuario.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            System.out.println("Textfield on focus");
            lblMensaje.setText("");
        }
        else
        {
            System.out.println("Textfield out focus");
            // variable booleana que recibe valor del metodo validarNumeros que evalua el string de txtusuario
           boolean numOk= validaNum.validarNumeros(txtUsuario.getText());
            if(!numOk){
                lblMensaje.setText("Ingrese solo números");
            }
        }
    }
});
        
        //*******************************************************
        
        
        
        
        
    }    
    
    
    
    // metodo conectar para el boton de login
    public void conectar(ActionEvent ae){
        
        
            boolean a = controlauser.iniciarSesion(this.getUsuario(),this.getContrasena());
        if (a) {
         
            System.out.println("conexion exitosa a la bd");
            
      
        }else{
            System.out.println("error de conexion");
        }//cierra el
        
        
        
    }// cierra metodo conectar
    
   
    
    public String getUsuario(){
        return txtUsuario.getText();
    }
    
    public String getContrasena(){
        return txtContrasena.getText();
    }
    
    
    public void setUsuario(String user){
        
    }
    
    
    //metodo para abrir ventana de recuperar contraseña
    
    public void olvidoContrasena(ActionEvent ae){
        
        
        
        
         try {
                 
                    // cargamos la scene
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(ventanaPrincipal.class.getResource("/VistasFX/restauraClave.fxml"));
                    AnchorPane Usuario = (AnchorPane) loader.load();

                    // agregamos a la ventana
                    Scene scene = new Scene(Usuario);
                    Node node = (Node) ae.getSource();
                    Stage primaryStage = (Stage) node.getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                } 
        
        
        
        
        
    }
    
    
   
    
    
}// cierra clase
