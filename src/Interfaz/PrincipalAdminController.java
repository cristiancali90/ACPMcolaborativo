/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.Usuario;
import Conexion.Conexion;
import Conexion.UsuarioBD;
import Controladores.ControladorUsuario;
import Interfaz.ventanaPrincipal;
import java.awt.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;
import javax.swing.table.DefaultTableModel;


/**
 * FXML Controller class
 *
 * @author KetsiaSilvana
 */
public class PrincipalAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     Conexion conectar = new Conexion();
    Connection con = conectar.getConnection();
    
    
    
    @FXML
    private Button btnAyuda;
    
    @FXML
    private Button btnCerrar;
    
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnlistar;
    
    @FXML
    private Button btnRegistrar;
    
    @FXML
    private Button btnModificar;
    
    @FXML
    private Button btnDesactivar;
    
    @FXML
    private TextField txtConsultar;
    
    @FXML
    private TableView tabla;
    
    @FXML
    private TableColumn columna;
    
    
    //*********************************************************
    
      // componentes del dialogo
 TextField nomb1= new TextField();

TextField nomb2= new TextField();

TextField apellido1= new TextField();

TextField apellido2= new TextField();


TextField cedula= new TextField();

TextField tel= new TextField();

TextField correo= new TextField();

TextField usuario= new TextField();

TextField contrasena= new TextField();

ComboBox comboCargo= new ComboBox();
ComboBox combodependencia= new ComboBox();
    
    
    
    
    
    //**********************************************************
    
    
    
    
    
  //*************************************************  
    // clases base

UsuarioBD a = new UsuarioBD();
    Usuario user;
    
   //***************************************************** 
    
    
    
    
    
    
    
    private String IdSeleccionado;
    
    ObservableList<ObservableList> listaObservable;
    ObservableList<ObservableList> listaObservable2;
    ObservableList<ObservableList> listaObservable3;
    
    public void llamarLogin(ActionEvent ae){
         try{
             // cargamos la scene
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(ventanaPrincipal.class.getResource("/VistasFx/inicioSesion.fxml"));
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
    
    public void llamarRegistrar(ActionEvent ae){
        try{
             // cargamos la scene
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(ventanaPrincipal.class.getResource("Registrar Usuarios.fxml"));
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // desactivar de entrada los botones de modificar y desactivar si no se selecciona registro
         btnModificar.setDisable(true);
        btnDesactivar.setDisable(true);
        
    }    
    
    
    
    @FXML
    public void mostrarUsuarios(ActionEvent ae) {
        
         // LIMPIA LA TABLA PARA QUE NO SE ANIDEN REGISTROS SINO QUE SE MUESTREN NUEVOS
         tabla.getColumns().clear();
    
        
        
        String[] Titulos = {"ID", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Cedula", "Telefono", "Correo", "Cargo", "Dependencia", "Estado"};
     
        

//************************************************************************************
        
        //bloque para armar tabla con cabeceras
          for (int i = 0; i < Titulos.length; i++) {
            final int j = i;
            this.columna= new TableColumn(Titulos[i]);
            this.columna.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> parametro) {
                    return new SimpleStringProperty((String) parametro.getValue().get(j));
                }
            });
           tabla.getColumns().addAll(columna);
           
            // Asignamos un tamaño a ls columnnas
         //   columna.setMinWidth(120);

            // Centrar los datos de la tabla
            columna.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
                @Override
                public TableCell<String, String> call(TableColumn<String, String> p) {
                    TableCell cell = new TableCell() {
                        @Override
                        protected void updateItem(Object t, boolean bln) {
                            if (t != null) {
                                super.updateItem(t, bln);
                                setText(t.toString());
                                setAlignment(Pos.CENTER); //Setting the Alignment
                            }
                        }
                    };
                    return cell;
                }
            });
        } // CIERRA FOR
        
        
        
        
 //*************************************************************************************       


//objeto usuario auxiliar para cargar objetos en cada iteracion
        String[] Usuario = new String[11];
        String busqueda = "";
        
        String dato="";
        if (dato.equals("")) {
            busqueda = "select a.idusuario, a.primer_nombre, a.segundo_nombre, a.primer_apellido, a.segundo_apellido, a.cedula, a.telefono, a.correo, a.estado, b.cargos, c.dependencia from tbusuario a inner join tbcargo b on(a.cargo=b.idcargo) inner join tbdependencia c on(a.dependencia=c.iddependencia) order by a.idUsuario";
        } else {
            busqueda = "select a.idusuario, a.primer_nombre, a.segundo_nombre, a.primer_apellido, a.segundo_apellido, a.cedula,a.telefono, a.correo, a.estado, b.cargos, c.dependencia from tbusuario a inner join tbcargo b on(a.cargo=b.idcargo) inner join tbdependencia c on(a.dependencia=c.iddependencia) WHERE b.cargos LIKE '" + dato + "%'";
        }

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(busqueda);
            
           //inicializacion de la lista observable
        
        listaObservable= FXCollections.observableArrayList();
            
            while (rs.next()) {
                Usuario[0] = rs.getString("idUsuario");
                Usuario[1] = rs.getString("Primer_Nombre").trim();
                Usuario[2] = rs.getString("Segundo_Nombre").trim();
                Usuario[3] = rs.getString("Primer_Apellido").trim();
                Usuario[4] = rs.getString("Segundo_Apellido").trim();
                Usuario[5] = rs.getString("Cedula").trim();
                Usuario[6] = rs.getString("Telefono").trim();
                Usuario[7] = rs.getString("Correo").trim();
                Usuario[8] = rs.getString("Cargos").trim();
                Usuario[9] = rs.getString("Dependencia").trim();
                Usuario[10] = rs.getString("Estado");
                
               // tablaUsuario.addRow(Usuario);
                
                //agregar cada registro a tabla
                
                
          
        
        // se crea una fila generica para cargarla de datos
        
         ObservableList<String> row = FXCollections.observableArrayList();
         
                   row.add( Usuario[0]);
                   row.add( Usuario[1]);
                   row.add( Usuario[2]);
                   row.add( Usuario[3]);
                   row.add( Usuario[4]);
                   row.add( Usuario[5]);
                   row.add( Usuario[6]);
                   row.add( Usuario[7]);
                   row.add( Usuario[8]);
                   row.add( Usuario[9]);
                   row.add( Usuario[10]);
                   
                   // se carga registro a lista observable
                   listaObservable.addAll(row);
                tabla.setItems(listaObservable);
            }
            
            // se carga a la tabla la lista observable generada
            
            
            
        } catch (SQLException ex) {
        }
        
        
        
       }// cierra metodo mostrarUsuarios

    
    
    
    public void confirmaDesactivar(ActionEvent ae){
        
 Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmación de desactivacion usuario");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("¿Estás seguro de desactivar el usuario?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
} else {
    // ... user chose CANCEL or closed the dialog
}
        
        
        
        
        
        
        
    }// cierra metodo confirmaDesactivar
    
    
    
    
    
    
    
    
    
    
    public void mostrarDialogoReg(ActionEvent ae){
        
      
        //inicializar los comboBox
        
        // codigo para cargar listado de cargos a comboBox
        
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from tbCargo");
            while (rs.next()) {
                ObservableList<String> listaCargos = FXCollections.observableArrayList();
                listaCargos.add(rs.getString("Cargos").trim());
                listaObservable2.addAll(listaCargos);
                
            }
            comboCargo.setItems(listaObservable2);
        } catch (SQLException ex) {
        }
//*********************************************************************
        
        // codigo para cargar listado de dependencias a comboBox
       
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from tbDependencia");
            while (rs.next()) {
                 ObservableList<String> listaDependencia = FXCollections.observableArrayList();
                listaDependencia.add(rs.getString("Dependencia").trim());
                listaObservable3.addAll(listaDependencia);
              
            }
            combodependencia.setItems(listaObservable3);
        } catch (SQLException ex) {
        }
        
   //***********************************************************************   
        
    //***********************************************************************
         try {
             //inicializar campos con item seleccionado de la tabla
             
             user=a.mostrar(IdSeleccionado);
             
             
             //se pasan los datos del objeto a los campos de texto
             System.out.println(user.getPrimer_Nombre());
             nomb1.setText(user.getPrimer_Nombre());
             nomb2.setText(user.getSegundo_Nombre());
             apellido1.setText(user.getPrimer_Apellido());
             apellido2.setText(user.getSegundo_Apellido());
             cedula.setText(user.getCedula());
             tel.setText(user.getTelefono());
             correo.setText(user.getCorreoElectronico());
             
             //********************************************************************
         } catch (SQLException ex) {
             Logger.getLogger(PrincipalAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        
        
        
        
        // Create the custom dialog.
Dialog<Pair<String, String>> dialog = new Dialog<>();
dialog.setTitle("Modificar Usuario");
dialog.setHeaderText("Look, a Custom Login Dialog");

// Set the icon (must be included in the project).
//dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

// Set the button types.
ButtonType loginButtonType = new ButtonType("Modificar", ButtonData.OK_DONE);
dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
GridPane grid = new GridPane();
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(20, 150, 10, 10));

//TextField nomb1= new TextField();
nomb1.setPromptText("Primer Nombre");
//TextField nomb2= new TextField();
nomb2.setPromptText("Segundo Nombre");
//TextField apellido1= new TextField();
apellido1.setPromptText("Primer Apellido");
//TextField apellido2= new TextField();
apellido2.setPromptText("Segundo Apellido");
//TextField cedula= new TextField();
cedula.setPromptText("Cedula");
//TextField tel= new TextField();
tel.setPromptText("telefono");
//TextField correo= new TextField();
correo.setPromptText("Email");
//ComboBox comboCargo= new ComboBox();
//ComboBox combodependencia= new ComboBox();

grid.add(new Label("Primer Nombre:"), 0, 0);
grid.add(nomb1, 1, 0);
grid.add(new Label("Segundo Nombre:"), 2, 0);
grid.add(nomb2, 3, 0);
grid.add(new Label("Primer Apellido:"), 0, 1);
grid.add(apellido1, 1, 1);
grid.add(new Label("Segundo Apellido:"), 2, 1);
grid.add(apellido2, 3, 1);
grid.add(new Label("Cedula:"), 0, 2);
grid.add(cedula, 1, 2);
grid.add(new Label("Telefono:"), 2, 2);
grid.add(tel, 3, 2);
grid.add(new Label("Correo:"), 0, 3);
grid.add(correo, 1, 3);
grid.add(new Label("Cargo:"), 0, 4);
grid.add(comboCargo, 1, 4);
grid.add(new Label("Dependencia:"), 0, 5);
grid.add(combodependencia, 1, 5);
grid.add(new Label("Nombre de Usuario:"), 0, 6);
grid.add(usuario, 1, 6);
grid.add(new Label("Contraseña:"), 2, 6);
grid.add(combodependencia, 3, 6);
// Enable/Disable login button depending on whether a username was entered.
//Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
//loginButton.setDisable(true);
/*
// Do some validation (using the Java 8 lambda syntax).
username.textProperty().addListener((observable, oldValue, newValue) -> {
    loginButton.setDisable(newValue.trim().isEmpty());
});
*/
dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
//Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
dialog.setResultConverter(dialogButton -> {
    if (dialogButton == loginButtonType) {
      //  return new Pair<>(username.getText(), password.getText());
       System.out.println("boton modificar presionado");
       
       
       //*****************************************************
       //codigo para registrar usuario aquí
       
       //**********************************************************
       
       int rol = 2;
        String primer_nombre = nomb1.getText();
        String segundo_nombre = nomb2.getText();
        String cedula = this.cedula.getText();
        String primer_apellido = apellido1.getText();
        String segundo_apellido = apellido2.getText();
        String correoelectronico = correo.getText();
        
        
        
         String auxDependencia="";
       try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from tbDependencia where dependencia='"+dividirCadena(combodependencia.getValue().toString())+"'");
            while (rs.next()) {
                auxDependencia=rs.getString("IdDependencia");
                
            }
            st.close();
        } catch (SQLException ex) {
         System.out.println("excepcion capturada con dependencia: "+auxDependencia);
        }
       
       // para cargo
       String auxCargo="";
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from tbCargo where cargos='"+dividirCadena(comboCargo.getValue().toString())+"'");
            while (rs.next()) {
               auxCargo= rs.getString("IdCargo");
                
               
                
            }
            st.close();
        } catch (SQLException ex) {
            System.out.println("excepcion capturada con cargo: "+auxCargo);
        }
       
       
       
       //********************************************************
        
        
        
        
        int dependencia = Integer.parseInt(auxDependencia);
        int cargo = Integer.parseInt(auxCargo);
        String telefono = tel.getText();
       
        String usuario = this.usuario.getText();
        String contrasena = this.contrasena.getText();
        boolean estado = true;
        ControladorUsuario.registrarUsu(rol, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, cedula, telefono, correoelectronico, dependencia, cargo,usuario, contrasena, estado);
       
       
       
       
       //***********************************************************
       
      
        
        
    }
    return null;
});

Optional<Pair<String, String>> result = dialog.showAndWait();

result.ifPresent(usernamePassword -> {
    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
});
        
        
        
        
        
        
    }// cierra metodo mostrarDialogoReg
    
    
    
    
    
    public void mostrarDialogoMod(ActionEvent ae){
        
        listaObservable2=FXCollections.observableArrayList();
        listaObservable3=FXCollections.observableArrayList();
        //inicializar los comboBox
        
        // codigo para cargar listado de cargos a comboBox
        
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from tbCargo");
            while (rs.next()) {
                ObservableList<String> listaCargos = FXCollections.observableArrayList();
                listaCargos.add(rs.getString("Cargos").trim());
                listaObservable2.addAll(listaCargos);
                
            }
            comboCargo.setItems(listaObservable2);
        } catch (SQLException ex) {
        }
//*********************************************************************
        
        // codigo para cargar listado de dependencias a comboBox
       
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from tbDependencia");
            while (rs.next()) {
                 ObservableList<String> listaDependencia = FXCollections.observableArrayList();
                listaDependencia.add(rs.getString("Dependencia").trim());
                listaObservable3.addAll(listaDependencia);
              
            }
            combodependencia.setItems(listaObservable3);
        } catch (SQLException ex) {
        }
        
   //***********************************************************************   
        
    //***********************************************************************
         try {
             //inicializar campos con item seleccionado de la tabla
             
             user=a.mostrar(IdSeleccionado);
             
             
             //se pasan los datos del objeto a los campos de texto
             System.out.println(user.getPrimer_Nombre());
             nomb1.setText(user.getPrimer_Nombre());
             nomb2.setText(user.getSegundo_Nombre());
             apellido1.setText(user.getPrimer_Apellido());
             apellido2.setText(user.getSegundo_Apellido());
             cedula.setText(user.getCedula());
             tel.setText(user.getTelefono());
             correo.setText(user.getCorreoElectronico());
             
             //********************************************************************
         } catch (SQLException ex) {
             Logger.getLogger(PrincipalAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        
        
        
        
        // Create the custom dialog.
Dialog<Pair<String, String>> dialog = new Dialog<>();
dialog.setTitle("Modificar Usuario");
dialog.setHeaderText("Modificar Usuario");
// (se agrega icono propio a mensaje de busqueda no exitosa).
dialog.setGraphic(new ImageView(this.getClass().getResource("/Imagenes/Logo 2.png").toString()));

dialog.setContentText("Look, a Custom Login Dialog");
//***********************************************
// se agrega icono al dialogo con logo uv

// Get the Stage.
Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();

// Add a custom icon.
stage.getIcons().add(new Image(this.getClass().getResource("/Imagenes/ico.png").toString()));


//***********************************************


// Set the icon (must be included in the project).
//dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

// Set the button types.
ButtonType loginButtonType = new ButtonType("Modificar", ButtonData.OK_DONE);
dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
GridPane grid = new GridPane();
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(20, 150, 10, 10));

//TextField nomb1= new TextField();
nomb1.setPromptText("Primer Nombre");
//TextField nomb2= new TextField();
nomb2.setPromptText("Segundo Nombre");
//TextField apellido1= new TextField();
apellido1.setPromptText("Primer Apellido");
//TextField apellido2= new TextField();
apellido2.setPromptText("Segundo Apellido");
//TextField cedula= new TextField();
cedula.setPromptText("Cedula");
//TextField tel= new TextField();
tel.setPromptText("telefono");
//TextField correo= new TextField();
correo.setPromptText("Email");
//ComboBox comboCargo= new ComboBox();
//ComboBox combodependencia= new ComboBox();

grid.add(new Label("Primer Nombre:"), 0, 0);
grid.add(nomb1, 1, 0);
grid.add(new Label("Segundo Nombre:"), 2, 0);
grid.add(nomb2, 3, 0);
grid.add(new Label("Primer Apellido:"), 0, 1);
grid.add(apellido1, 1, 1);
grid.add(new Label("Segundo Apellido:"), 2, 1);
grid.add(apellido2, 3, 1);
grid.add(new Label("Cedula:"), 0, 2);
grid.add(cedula, 1, 2);
grid.add(new Label("Telefono:"), 2, 2);
grid.add(tel, 3, 2);
grid.add(new Label("Correo:"), 0, 3);
grid.add(correo, 1, 3);
grid.add(new Label("Cargo:"), 0, 4);
grid.add(comboCargo, 1, 4);
grid.add(new Label("Dependencia:"), 0, 5);
grid.add(combodependencia, 1, 5);
// Enable/Disable login button depending on whether a username was entered.
//Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
//loginButton.setDisable(true);
/*
// Do some validation (using the Java 8 lambda syntax).
username.textProperty().addListener((observable, oldValue, newValue) -> {
    loginButton.setDisable(newValue.trim().isEmpty());
});
*/
dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
//Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
dialog.setResultConverter(dialogButton -> {
    if (dialogButton == loginButtonType) {
      //  return new Pair<>(username.getText(), password.getText());
       System.out.println("boton modificar presionado");
       
       
       //*****************************************************
       //codigo para modificar usuario aquí
       
       user.setPrimer_Nombre(nomb1.getText());
       user.setSegundo_Nombre(nomb2.getText());
       user.setPrimer_Apellido(apellido1.getText());
       user.setSegundo_Apellido(apellido2.getText());
       user.setCedula(cedula.getText());
       user.setTelefono(tel.getText());
       user.setCorreoElectronico(correo.getText());
       if(comboCargo.getValue()!=null&&combodependencia.getValue()!=null){
       System.out.println(dividirCadena(combodependencia.getValue().toString()));
       /// para dependencia
       String aux1="";
       try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from tbDependencia where dependencia='"+dividirCadena(combodependencia.getValue().toString())+"'");
            while (rs.next()) {
                aux1=rs.getString("IdDependencia");
                user.setDependencia(Integer.parseInt(rs.getString("IdDependencia")));
            }
            st.close();
        } catch (SQLException ex) {
         System.out.println("excepcion capturada con dependencia: "+aux1);
        }
       
       // para cargo
       String aux="";
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from tbCargo where cargos='"+dividirCadena(comboCargo.getValue().toString())+"'");
            while (rs.next()) {
               aux= rs.getString("IdCargo");
                
                user.setCargo(Integer.parseInt(rs.getString("IdCargo")));
                
            }
            st.close();
        } catch (SQLException ex) {
            System.out.println("excepcion capturada con cargo: "+aux);
        }
       
       
       
       //********************************************************
        
        
         // se le pasa objeto a la base de datos para modificar
        
        a.modificarUsuario(user, IdSeleccionado);
        
        System.out.println("usuario modificado");
        grid.add(new Label("USUARIO MODIFICADO"), 3, 7);
       }else{
       
        grid.add(new Label("USUARIO NO MODIFICADO"), 3, 7);
        
       }// cierra else
        
        
        
        
    }
    return null;
});

Optional<Pair<String, String>> result = dialog.showAndWait();

result.ifPresent(usernamePassword -> {
    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
});
        
        
    }// cierra mostrar dialogo
    
    
    
@FXML
    private void getselectTabla(MouseEvent event) {
        
       
        
        tabla.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            
            
            @Override
            public void handle(MouseEvent e) {
                
                if (tabla != null) {
                   try{
                    String[] seleccionado = tabla.getSelectionModel().getSelectedItems().get(0).toString().split(",");
                    System.out.println(seleccionado[0].substring(1));
                    
              
                    IdSeleccionado = seleccionado[0].substring(1);
                   
                    
                             //activar botones de modificar y desactivar una vez se ha seleccionado algun registro
                         btnModificar.setDisable(false);
                           btnDesactivar.setDisable(false);
        //************************************************************************************
                    
                    
              //    txtCodigo.setText(IdDeSeleccionado);
                //  txtCodigo.setEditable(false);
                  
                
                  
                  //  EntityManager em = docenteBD.getEntityManager();
              //      String sql="select * from estudiante where iddocente="+IdDeSeleccionado+"";
              //      Query query= em.createNativeQuery(sql, Docente.class);
                //    Docente result= (Docente)query.getSingleResult();
                  
               /*
                    txtNombres.setText(result.getNombres());
                   txtApellidos.setText(result.getApellidos());
                   txtTel1.setText(result.getTelefono1().toString());
                   txtTel2.setText(result.getTelefono2().toString());
                   
                   txtCorreo.setText(result.getCorreo());
               
                  */
               
                  
                 
                   
                    
                    
                 }catch(NullPointerException npe){
                       System.out.println("no selecciono nada :O");
                      
                   }
                } //CIERRA IF NULL
            }// CIERRA HANDLE
            
       
        }); // CIERRA SET ONMOUSECLICKED
        
        
    } // CIERRA METODO GETSELECTED
     
    

    
    
    
    
    // DIVIDIR CADENA
    public String dividirCadena(String cadena){
        //se comprimen los espacios en blanco
       // String texto= cadena.trim();
        //************************************
        String cadenaConvertida="";
       StringTokenizer tokenizer = new StringTokenizer(cadena,"[]");
       
       while(tokenizer.hasMoreTokens()){
           
      cadenaConvertida+=tokenizer.nextToken();
       
       
       }// cierra while
       
       
      
       
       
       return cadenaConvertida;
    }
    
    
    
    
    
    
    
    
    
}// cierra clase
