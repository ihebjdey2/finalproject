/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.itextpdf.text.pdf.XfaXpathConstructor.XdpPackage.Pdf;
import entity.Pdf;
import entity.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import services.UserService;
import tools.MaConnection;

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
    @FXML
    private Button xl;
    @FXML
    private ComboBox<String> ExporterListe;
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
  /**********************API Exporter to XL***********************************************************************/
    @FXML
    private void exporterToXlxs(MouseEvent event) {
        Connection cnx = MaConnection.getInstance().getCnx();
                try {
            String query = "select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Liste des utilisateurs");

            HSSFRow header = sheet.createRow(0);

            header.createCell(0).setCellValue("nom");
            header.createCell(1).setCellValue("email");
            header.createCell(2).setCellValue("roles");
            header.createCell(3).setCellValue("prenom");
            header.createCell(4).setCellValue("genre");

            int index = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString(1));
                row.createCell(1).setCellValue(rs.getString(2));
                row.createCell(2).setCellValue(rs.getString(3));
                row.createCell(3).setCellValue(rs.getString(4));
                row.createCell(4).setCellValue(rs.getString(6));
               
                index++;

            }

            FileOutputStream fileOut = new FileOutputStream("users.xls");

            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("La table est bien été importé en xls ❗ ");

            alert.showAndWait();

        } catch (SQLException ex) {
            Logger.getLogger(AfficheAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficheAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficheAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
/******************************************/
        @FXML
    private void ExporterListe(ActionEvent event) throws IOException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, SQLException {


            User f = userTable.getSelectionModel().getSelectedItem();
            Pdf pd = new Pdf();
            try {
                pd.GeneratePdf("" + f.getNom() + "", f, f.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PDF");
                alert.setHeaderText(null);
                alert.setContentText("!!!PDF exported!!!");
                alert.showAndWait();
                System.out.println("impression done");
            } catch (Exception ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        
    }

    private void PDF(MouseEvent event) {
        User f = userTable.getSelectionModel().getSelectedItem();

        Pdf pd = new Pdf();
        try {
            pd.GeneratePdf("MesInformations", f, f.getId());
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        javafx.print.PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);

        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();

            }
        }
        node.getTransforms().remove(scale);

    }
}
