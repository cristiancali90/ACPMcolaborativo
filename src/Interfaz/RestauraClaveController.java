/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.Usuario;
import Conexion.UsuarioBD;
import Mail.javaMail;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author admincris
 */
public class RestauraClaveController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    //***********************************************************
    
    @FXML
    private TextField txtCorreo;
    @FXML
    private Button btnEnviar;
    
    @FXML
       private ProgressIndicator progreso;     
    
    @FXML
            private Label lblMensaje;
    
    @FXML
            private Label lblMensaje2;
    
    @FXML
            private Button btnVolver;
    //************************************************************
    
    
    
    
    
    
    
    
    // objeto de la clase javaMail
    javaMail mailer= new javaMail();
    
    // objeto de la clase UsuarioBd
    UsuarioBD userBD= new UsuarioBD();
    
   // objeto de la clase usuario
    Usuario user= new Usuario();
    
    
    //metodo para validar que el usuario con su respectivo correo existan en bd
    
    public boolean validaCorreo(){
        
        boolean estado=false;
        
        
        System.out.println("clickaste para enviar mail");
      
        try {
            user= userBD.encuentraMail(txtCorreo.getText());
            
            //*************************************************************
              if(user.getCorreoElectronico().equals(txtCorreo.getText())){
            estado=true;
        }
          //***************************************************************
        } catch (SQLException ex) {
            Logger.getLogger(RestauraClaveController.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException npe){
        
       //aqui se captura cuando el correo digitado al buscarlo no existe en la base de datos
            
      
        }//cierra catch de null
        
        
        
        return estado;
    }// cierra metodo valida correo
    
    
    public void enviarMail(ActionEvent ae){
        
           // se hace visible el indicador de progreso   
        progreso.setVisible(true);
        progreso.setProgress(-0.22);
        lblMensaje.setText("");
        //*****************************************
        
       boolean estadoConexion=false;
        if(validaCorreo()==true&&progreso.getProgress()==-0.22){
      
        String mensaje= "Cordial saludo\n"+user.getPrimer_Nombre()+" esta es tu contraseña: "+user.getContrasena()+"\n \n No responder este mensaje \n \n Muchas gracias.";
        
        //inicializar objeto mailer con parametros
        mailer.setMensage(mensaje);
        mailer.setDestino(user.getCorreoElectronico());
       
            try {
                mailer.start();
                estadoConexion=mailer.getStconexion();
                //**********************************
                synchronized(mailer){
                mailer.wait(3000);
                
                
                
                
               
        
                }
                //**********************************
                
                
                
                
                //**********************************
                synchronized(mailer){
                    
                       
                    
                     mailer.notifyAll();
                }
                 //*********************************   
           
                
            } catch (InterruptedException ex) {
                Logger.getLogger(RestauraClaveController.class.getName()).log(Level.SEVERE, null, ex);
            }
       //**************************************************************************************
        if(estadoConexion==true){
        
        progreso.setProgress(1);
        lblMensaje.setTextFill(Color.GREEN);
        lblMensaje.setText("Contraseña enviada a tu correo, ¡Dale un vistazo!");
        }else{
            
            lblMensaje2.setTextFill(Color.RED);
            lblMensaje2.setText("correo no enviado, revisa tu conexión a internet");
            //se hace invisible el indicador de progreso
            progreso.setVisible(false);
            
        }
        //**************************************************************************************
        
        }else{
             
            lblMensaje.setTextFill(Color.RED);
            lblMensaje.setText("correo no enviado, verifique que el mail este correcto, al parecer no existe");
            //se hace invisible el indicador de progreso
            progreso.setVisible(false);
        }
        
    }
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        progreso.setVisible(false);
    }    
   
    
    
      //metodo para volver a ventana de login
    
    public void volver(ActionEvent ae){
        
        
        
        
         try {
                 
                    // cargamos la scene
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(ventanaPrincipal.class.getResource("/VistasFX/inicioSesion.fxml"));
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
