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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BestSellingController implements Initializable {

    @FXML
    private Button ml;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String e,s;
    @FXML
    private ImageView ml1;
    @FXML
    private TextField text1;
    @FXML
    private ImageView ml2;
    @FXML
    private TextField text2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SignUpController s=new SignUpController();
       // String res=s.test();
      //  System.out.println(res);
        //text1.setText(res);
        
    }    
    
    @FXML
    private void gotodashboardpage(ActionEvent event) throws IOException {
          Parent employee_page_parent = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotomakeorderpage(ActionEvent event) throws IOException {
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("MakeOrder.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }
    @FXML
    private void gotomyorderspage(ActionEvent event) throws IOException {
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("MyOrders.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotouserprofilepage(ActionEvent event) throws IOException {
        
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotomycartpage(ActionEvent event) throws IOException {
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("MyCart.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }
    /*void set(String s){
            String res=s;
            text1.setText(res);
        }   */
    @FXML
    private void check_ml(ActionEvent event) {
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management ;selectMethod=cursor", "sa", "123456");
           // String email=user_email.getText();
             //String password=user_password.getText();
             
             String q="SELECT result,step FROM CONDITION Where Id=(SELECT MAX(Id) FROM CONDITION)";
              System.out.println(q);
              
               rs = con.createStatement().executeQuery(q);

                while(rs.next()){
                   System.out.println(rs.getString("result"));
                     e=rs.getString("result");
                     s=rs.getString("step");
                   // System.out.println(e+s);
                }
                
                if(e.equals("YES") ){
                    // JOptionPane.showMessageDialog(null,"okay");
                     Image img=new Image("/img/blue_fullshirt.png");
                     ml1.setImage(img);
                     text1.setText("Blue Full Sleeve Shirt");
                }
                else if(e.equals("NO") ){
                    // JOptionPane.showMessageDialog(null,"not okay");
                }
                if(s.equals("YES") ){
                    // JOptionPane.showMessageDialog(null,"okay");
                     Image img1=new Image("/img/bluepolofront.png");
                     ml2.setImage(img1);
                     text2.setText("Blue Polo T-Shirt");
                }
                else if(e.equals("NO") ){
                    // JOptionPane.showMessageDialog(null,"not okay2");
                }
                // text1.setText(e);
         } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Wrong");
        
        }          
            
            
    }
    
}
