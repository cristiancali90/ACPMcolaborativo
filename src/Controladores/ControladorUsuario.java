package Controladores;

import Conexion.UsuarioBD;
import Clases.Usuario;

import Interfaz.Modificar_Usu;
import Interfaz.Principal_Adm;
import Interfaz.Principal_Usu;
import Interfaz.Registrar_Usu;
import Interfaz.ventanaPrincipal;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ControladorUsuario {

    public static Usuario USER = null;

    public  boolean iniciarSesion(String usuario, String clave, ActionEvent ae) {
        Usuario user = new Usuario();
        user.setUsuario(usuario);
        System.out.println("valor obtenido de la vista usuario: "+usuario);
        user.setContrasena(clave);
        System.out.println("valor obtenido de la vista clave: "+clave);
        USER = user.crearLogin();
        if (USER == null) {
           
            
            return false;
        } else if (USER.getRol() == 2) {
            /*
            UsuarioLogeado.setUser(USER);
            Principal_Usu usu = new Principal_Usu();
            usu.setVisible(true);
                    */
            return true;
        } else if (USER.getRol() == 1) {
            
            /*
            UsuarioLogeado.setUser(USER);
            Principal_Adm adm = new Principal_Adm();
            adm.setVisible(true);
                    */
            
               try{
             // cargamos la scene
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(ventanaPrincipal.class.getResource("/VistasFx/PrincipalAdmin.fxml"));
                    AnchorPane Usuario = (AnchorPane) loader.load();

                    // agregamos a la ventana
                    Scene scene = new Scene(Usuario);
                    Node node = (Node) ae.getSource();
                    Stage primaryStage = (Stage) node.getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.setResizable(false);
                    primaryStage.show();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return true;
        } else {
            return false;
        }
    }

    public static void registrarUsu(int Rol, String Primer_Nombre, String Segundo_Nombre, String Primer_Apellido, String Segundo_Apellido, String Cedula, String Telefono, String CorreoElectronico, int Dependencia, int Cargo, String Usuario, String Contrasena, boolean Estado) {
        Usuario user1 = new Usuario(Rol, Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido, Cedula, Telefono, CorreoElectronico, Dependencia, Cargo,Usuario, Contrasena, Estado);
        UsuarioBD usb1 = new UsuarioBD();
        usb1.crearUsuario(user1);
    }
}
