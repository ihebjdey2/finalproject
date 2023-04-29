/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.faces.config.WebConfiguration.DisableUnicodeEscaping.False;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;
import static tools.PasswordHashing.hashPassword;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InscriptionController implements Initializable {

  @FXML
private ComboBox<String> roleComboBox;
    @FXML
    private Button validerButton;
    @FXML
    private Button annulerButton;
 /*******************************************/
    private Stage stage;
    private Scene scene;
    private Parent root;
/***********************************************/
    
    ObservableList<String> typeList = FXCollections.observableArrayList("Admin", "Patient","Medecin");
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfgenre;
    @FXML
    private PasswordField tfmdp;
    @FXML
    private DatePicker tfdate;
    
    LocalDate localdate=null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        roleComboBox.setItems(typeList);
        UserService us = new UserService();
        
    }
/***************DatePicker**************************************************/
        @FXML
    private void getDate(ActionEvent event) {
        localdate=tfdate.getValue();
    }
/************************************************************************/
    @FXML
    private void validerAction(ActionEvent event) throws IOException {
//        UserService us = new UserService(); 
//        String email = emailTextField.getText();
//        
//        String roles = roleComboBox.getValue();
//
//        String password = pwdPasswordField.getText();
//        Date date_naissance = java.sql.Date.valueOf(dateNaissancePicker.getValue());  //LocalDate dateNaissance = dateNaissancePicker.getValue();
//        String nom = nomTextField.getText();
//        String prenom = prenomTextField.getText();
//        String genre = GenreTextField.getText();
//        
//
//        User u = new User(email, roles, password, (date_naissance), nom, prenom,genre);
//        us.createuser(u);

    UserService us = new UserService(); 
       if(valide1()) 
       {String nom=tfnom.getText();
        String prenom=tfprenom.getText();
        String mail=tfemail.getText();
        String genre=tfgenre.getText();
  
        String mdp=tfmdp.getText();
        mdp=hashPassword(mdp);
             
        mdp=hashPassword(tfmdp.getText());
        Date date=java.sql.Date.valueOf(tfdate.getValue()) ;
        String role=roleComboBox.getValue();
               

        User u=new User(mail, role, mdp,date,nom,prenom, genre);
        us.createuser(u);
        
        showAlert(Alert.AlertType.INFORMATION, "Success", "Inscription a été effectué avec succès!", "");
        us.sendEmail(mail,nom);
           
           try {
               root =FXMLLoader.load(getClass().getResource("Login.fxml"));
               stage=(Stage)((Node)event.getSource()).getScene().getWindow();
               Scene scene =new Scene(root);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               System.out.println(ex.getMessage());
           }
           
       }

    }

    @FXML
    private void annulerAction(ActionEvent event) throws IOException {
        // On retourne à la page de connexion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml")); // Code pour annuler l'inscription
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
/****************************************************************************************************************/
private boolean valide1() {
    if (tfnom.getText().isEmpty() || tfprenom.getText().isEmpty() || tfemail.getText().isEmpty() || tfgenre.getText().isEmpty()
            || tfmdp.getText().isEmpty() || tfdate.getValue() == null) {
                  
        showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien renseigner tous les champs !");        
        return false;}    
      return true;
}

 public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    } 
}
