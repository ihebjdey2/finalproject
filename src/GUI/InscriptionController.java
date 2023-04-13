/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField pwdPasswordField;
    @FXML
    private DatePicker dateNaissancePicker;
  @FXML
private ComboBox<String> roleComboBox;
    @FXML
    private Button validerButton;
    @FXML
    private Button annulerButton;
    
    @FXML
    private TextField GenreTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void validerAction(ActionEvent event) {
        UserService us = new UserService();
        String email = emailTextField.getText();
       String roles = roleComboBox.getValue();

        String password = pwdPasswordField.getText();
       Date date_naissance = java.sql.Date.valueOf(dateNaissancePicker.getValue());  //LocalDate dateNaissance = dateNaissancePicker.getValue();
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String genre = GenreTextField.getText();
        

        User u = new User(email, roles, password, (date_naissance), nom, prenom,genre);
        us.createuser(u);

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

}
