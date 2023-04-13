/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
//    String name = usernameTextField.getText();
//    String password = passwordTextField.getText();
//    User user=new User();
//    
//    UserService userService = new UserService();
//    if (user != null) {
//    String roles = user.getRoles();
//    if (roles.equals("Admin")) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheAdmin.fxml"));
//                Parent root = loader.load();
//                
//                AfficheAdminController controller = loader.getController();
//               // controller.setUtilisateur(utilisateur);
//                
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//        // Rediriger l'utilisateur vers la page d'administration
//    } else if (roles.equals("Medecin")) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
//                Parent root = loader.load();
//                
//                HomeController controller = loader.getController();
//               // controller.setUtilisateur(utilisateur);
//                
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//        // Rediriger l'utilisateur vers la page utilisateur
//    } else if (!roles.equals("Patient")) {
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
//                Parent root = loader.load();
//                
//                HomeController controller = loader.getController();
//               // controller.setUtilisateur(utilisateur);
//                
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//        
//    }
//   
//} else {
//    errorLabel.setText("Nom d'utilisateur ou mot de passe incorrect");
//}
try {
        // Charger le fichier FXML de la page d'inscription
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        // Créer un nouveau stage pour la fenêtre d'inscription
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
         stage.setTitle("Page d'accueil");
        
        stage.show();
       
    } catch (IOException e) {
    }
    
    }

   
   @FXML
private void handleSignupAction(ActionEvent event) {
    
    
    System.out.print("hi");
    try {
        // Charger le fichier FXML de la page d'inscription
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
        Parent root = loader.load();
        // Créer un nouveau stage pour la fenêtre d'inscription
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inscription");
        stage.show();
       
    } catch (IOException e) {
    }
}
    
}
