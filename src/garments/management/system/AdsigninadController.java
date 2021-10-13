/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garments.management.system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohammad Rasel
 */
public class AdsigninadController implements Initializable {

    @FXML
    private TextField aduserid;
    private TextField adpassword;
    @FXML
    private PasswordField pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotohomepage(ActionEvent event) throws IOException {
        
         String id=aduserid.getText();
        String pass1= pass.getText();
        
        if(id.equals("") || pass1.equals("")){
            Alert a1=new Alert(Alert.AlertType.WARNING);
            a1.setContentText("username or password field can't be empty");
            a1.showAndWait();
                    
            
        
        
        
          }
        else if (id.equals("admin") && pass1.equals("admin")){
            
             
           Parent employee_page_parent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
            
        }
        
        
        else{
            Alert a2=new Alert(Alert.AlertType.WARNING);
            a2.setContentText("wrong password or user field");
            a2.showAndWait();
            
            
        }
    }
    
}
