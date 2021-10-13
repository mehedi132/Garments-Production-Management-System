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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
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

    private TextField name;
    private TextField address;
    private TextField country;
    private TextField email;
    private TextField phone;
    private PasswordField password;
    private TextField age;

     public Connection connection;
    @FXML
    private TextField user_email;
    @FXML
    private TextField user_password;
    @FXML
    private Button user_signin;
    String e,p;
    int z; 
    int y=14;
    Connection con;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    PreparedStatement pst = null;
    ResultSet rs = null;
    @FXML
    private void check_signin(ActionEvent event) {
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management ;selectMethod=cursor", "sa", "123456");
             String email=user_email.getText();
             String password=user_password.getText();
             
             String q="SELECT BuyerId, EmailId,Pass FROM Buyer Where EmailId = '"+email+"' AND Pass = '"+password+"' ";
            //  System.out.println(q);
             
               //PreparedStatement pst=con.prepareStatement(q);
                
                rs = con.createStatement().executeQuery(q);

                while(rs.next()){
                   System.out.println(rs.getString("EmailId"));
                     e=rs.getString("EmailId");
                     p=rs.getString("Pass");
                     z=rs.getInt("BuyerId");
                     
                }
                
                if(e.equals(email) && p.equals(password)){
                    connectDB cond= new connectDB();
                    con=cond.connectdb();
                    
                    String sql="insert into userInfo (u_id)values(?)";
                   PreparedStatement pst=con.prepareStatement(sql);
                       pst.setString(1,Integer.toString(z));
                        pst.executeUpdate();
             
                    
                     Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("Congratulation! You are logged in");
            a2.showAndWait();
                      
                      
                       Parent employee_page_parent = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
                     Scene  employee_page_scene = new Scene(employee_page_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(employee_page_scene);
                    app_stage.show();
                   
                    
                }
                else
                {
                   Alert a2=new Alert(Alert.AlertType.ERROR);
            a2.setContentText("logged in Failed!");
            a2.showAndWait();
                }
                
                
               
            }  catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Wrong");
        
        }
        
    } 
  @FXML
    private void goto_forgetPage(ActionEvent event) throws IOException {
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("ForgetPass.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }
         
    
}

