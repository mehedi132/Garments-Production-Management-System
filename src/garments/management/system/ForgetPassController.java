/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garments.management.system;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
 

public class ForgetPassController implements Initializable {

    @FXML
    private TextField user_email;
    @FXML
    private TextField forgot_pass;
    @FXML
    private Button again;

    
    public Connection connection;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String e,p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

    @FXML
    private void show_pass(ActionEvent event) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management ;selectMethod=cursor", "sa", "123456");
              String email=user_email.getText();
             //String password=user_password.getText();
             
             String q="SELECT EmailId,Pass FROM Buyer Where EmailId = '"+email+"' ";
              System.out.println(q);
              
               rs = con.createStatement().executeQuery(q);

                while(rs.next()){
                   //System.out.println(rs.getString("result"));
                    e=rs.getString("EmailId");
                     p=rs.getString("Pass");
                    // System.out.println(e+p);
                }
                
                if(e.equals(email)){
                    // JOptionPane.showMessageDialog(null,"okay");
                     forgot_pass.setText(p);
                     again.setText("Login Again");
                }
                else {
                     Alert a2=new Alert(Alert.AlertType.ERROR);
            a2.setContentText("Wrong Email!");
            a2.showAndWait();
                }
                // text1.setText(e);
         } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Wrong");
        
        }          
    }

    @FXML
    private void goto_Login_again(ActionEvent event) throws IOException {
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }
    
    
    
}
