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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    boolean isNumber;
    boolean numVacio;
    
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
            
            //*************************************************************
            
            if(!getUsuario().trim().isEmpty()){
            
            // variable booleana que recibe valor del metodo validarNumeros que evalua el string de txtusuario
           boolean numOk= validaNum.validarNumeros(txtUsuario.getText());
           
            if(!numOk){
                lblMensaje.setText("Ingrese solo números");
                isNumber=false;
            }else{
                isNumber=true;
            }
            //***********************************************************
            }else{
                // si el campo usuario esta vacio asignar true a variable numVacio
                numVacio=true;
                
            }// cierra else de usuario vacio
        }
    }
});
        
        //*******************************************************
        
        
        
        
        
    }    
    
    
    
    // metodo conectar para el boton de login
    public void conectar(ActionEvent ae){
        
        boolean passEmpty=false;
       String usu= this.getUsuario().trim();
       String pass=this.getContrasena().trim();
        
            if(pass.isEmpty()||usu.isEmpty()){
                
                if(pass.isEmpty()){
                passEmpty=true;
                }
            System.out.println("campos vacios");
            //llamar dialogo que advierta sobre los campos vacios
            //****************************************************
            camposVacios();
            
            }
            
            
            
            
        
        if(isNumber==true&&passEmpty==false){
        
            boolean a = controlauser.iniciarSesion(this.getUsuario(),this.getContrasena(),ae);
            
            // evalua consulta exitosa o no, la clase controladorUsuario llama la ventana segun el rol de usuario
              if (a) {
         
            
            
            
            
            System.out.println("conexion exitosa a la bd");
            
            
            
          
            
            
            
            
      
        }else{
                  // mostrar dialogo que indique que no existe el usuario buscado
            System.out.println("error de conexion");
            busquedaFail();
        }//cierra el
        
            
        }else{
            
            //aqui mostrar dialogo de javafx
            if(isNumber==false&&passEmpty==false){
            errorUsuario();
            }
            
            
        }// cierra else de isNumber
            
            
        

        
        //**************************************************************************************    
      
        
        
    }// cierra metodo conectar
    
    
    
    public void camposVacios(){
        
Alert alert = new Alert(AlertType.WARNING);
//***********************************************
// se agrega icono al dialogo con logo uv

// Get the Stage.
Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

// Add a custom icon.
stage.getIcons().add(new Image(this.getClass().getResource("/Imagenes/ico.png").toString()));



//***********************************************
alert.setTitle("Alerta");
alert.setHeaderText("¡Eeey!, ¡¡No se aceptan campos vacíos!!");
alert.setContentText("Uno o más campos vacíos, ¡Diligencia los campos!");

alert.showAndWait();
        
        
        
    }// cierra campos vacios
    
    
    
 
    public void busquedaFail(){
Alert alert = new Alert(AlertType.INFORMATION);
//***********************************************
// se agrega icono al dialogo con logo uv

// Get the Stage.
Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

// Add a custom icon.
stage.getIcons().add(new Image(this.getClass().getResource("/Imagenes/ico.png").toString()));



//***********************************************
alert.setTitle("Búsqueda sin éxito");
alert.setHeaderText(null);
// (se agrega icono propio a mensaje de busqueda no exitosa).
alert.setGraphic(new ImageView(this.getClass().getResource("/Imagenes/carita.jpg").toString()));
alert.setContentText("No se encontró ningún usuario en la base de datos!");

alert.showAndWait();
    }
    
    
    
    //metodo para mostrar ventana de validacion de usuario
    public void errorUsuario(){
        
Alert alert = new Alert(AlertType.ERROR);
//***********************************************
// se agrega icono al dialogo con logo uv

// Get the Stage.
Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

// Add a custom icon.
stage.getIcons().add(new Image(this.getClass().getResource("/Imagenes/ico.png").toString()));



//***********************************************
alert.setTitle("Error de Usuario");
alert.setHeaderText("ERROR, TIPO DE DATO NO ESPERADO EN USUARIO");
alert.setContentText("Ooops, tu usuario debe ser de solo numeros!");

alert.showAndWait();
        
        
        
        
        
    }// cierra metodo error usuario
    
    
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
