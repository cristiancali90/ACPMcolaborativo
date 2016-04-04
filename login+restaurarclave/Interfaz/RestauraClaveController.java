/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.Usuario;
import Conexion.UsuarioBD;
import Mail.javaMail;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
        
       
        if(validaCorreo()==true&&progreso.getProgress()==-0.22){
      
        String mensaje= "Cordial saludo\n"+user.getPrimer_Nombre()+" esta es tu contraseña: "+user.getContrasena()+"\n \n No responder este mensaje \n \n Muchas gracias.";
        
        //inicializar objeto mailer con parametros
        mailer.setMensage(mensaje);
        mailer.setDestino(user.getCorreoElectronico());
       
            try {
                mailer.start();
                
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
       
        
        
        progreso.setProgress(1);
        lblMensaje.setTextFill(Color.GREEN);
        lblMensaje.setText("Contraseña enviada a tu correo, ¡Dale un vistazo!");
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
   
    
    
    
    
    
    
}// cierra clase
