/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SignInController implements Initializable {

    private TextField adpassword;
    private TextField aduserid;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField country;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private PasswordField password;
    @FXML
    private TextField age;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //fetch();
    }    

    private void gotohomepage(ActionEvent event) throws IOException {
        
        String id=aduserid.getText();
        String pass=adpassword.getText();
        
        if(id.equals("") || pass.equals("")){
            Alert a1=new Alert(Alert.AlertType.WARNING);
            a1.setContentText("username or password field can't be empty");
            a1.showAndWait();

          }
        else if (id.equals("admin") && pass.equals("admin")){
            
             
           Parent employee_page_parent = FXMLLoader.load(getClass().getResource("/garments/management/system/DashBoard.fxml"));
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

    /*@FXML
    private void register(ActionEvent event) {
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=demo;selectMethod=cursor", "sa", "123456");
           
             String q="insert into Buyer (Name,Address,Country,EmailId,PhoneNumber,Pass,Age,Gender) values(?,?,?,?,?,?,?,?)";
            
            PreparedStatement pst=con.prepareStatement(q);
              pst.setString(1,name.getText());
              pst.setString(2,address.getText());
              pst.setString(3,country.getText());
              pst.setString(4,email.getText());
              pst.setString(5,phone.getText());
              pst.setString(6,password.getText());
              pst.setString(7,age.getText());
              pst.setString(8,null);
              pst.executeUpdate();
             JOptionPane.showMessageDialog(null,"inserted");
            //fetch();                                      
            
            System.out.println(name.getText());
           
           
    
      } catch (Exception e) {
            e.printStackTrace();
       }
        
    }*/
}
