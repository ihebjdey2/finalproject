/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficheAdminController implements Initializable {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> usernameColumn,emailColumn,roleColumn,prenomColumn,sexeColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
/***************************************************/
    public User u;
    public List<User> users;
    private UserService us=new UserService();
/******************************************************/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }    
        private void refreshTable(){
        
        ObservableList<User> userlist = FXCollections.observableArrayList();
        
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("roles"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        sexeColumn.setCellValueFactory(new PropertyValueFactory<User,String>("genre"));
        
        
        users=us.getAllUsers();
        userlist.addAll(users);
        userTable.setItems(userlist);
      
    }

    @FXML
    private void handleAdd(MouseEvent event) {
    }

    @FXML
    private void handleAdd(ActionEvent event) {
    }

    @FXML
    private void handleEdit(ActionEvent event) {
    }

    @FXML
    private void handleDelete(ActionEvent event) {
    }
    
}
