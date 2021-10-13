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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField country;
    @FXML
    private TextField email;
    @FXML
    private TextField number;
    @FXML
    private TextField age;
    @FXML
    private PasswordField c_pass;
    @FXML
    private PasswordField new_pass;
    @FXML
    private PasswordField mew_pass_again;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void gotoBestSellingPage(ActionEvent event) throws IOException {
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("BestSelling.fxml"));
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
    private void gotomycartpage(ActionEvent event) throws IOException {
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("MyCart.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();




    }
    @FXML
    private void edit_button(ActionEvent event) {
        
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           
             ResultSet rs= con.createStatement().executeQuery("select * from Buyer where BuyerId= (select u_id from userInfo where p_id=(select max(p_id) from userInfo)) ");
             while(rs.next()){
                 name.setText(rs.getString("Name"));
                 address.setText(rs.getString("Address"));
                 country.setText(rs.getString("Country"));
                 email.setText(rs.getString("EmailId"));
                 number.setText(rs.getString("PhoneNumber"));
                 age.setText(rs.getString("Age"));
                     
               // list.add(new Buyer(rs.getInt("BuyerId"),rs.getString("Name"),rs.getString("Address"),
                       // rs.getString("Country"),rs.getString("EmailId"),rs.getString("PhoneNumber")));
                
             }
            
            
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
        
       /*  try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=demo;selectMethod=cursor", "sa", "123456");                       
           
            String v1=name.getText();
             String v2=address.getText();
              String v3=country.getText();
               String v4=email.getText();
               String v5=number.getText();
               String sql="update Buyer Name='"+v1+"',Address='"+v2+"',"
                       + "Country='"+v3+"',EmailId='"+v4+"',PhoneNumber='"+v5+"' where BuyerId='3' ";
               PreparedStatement pst=con.prepareStatement(sql);
               pst.execute();
               JOptionPane.showMessageDialog(null,"Updated");
            fetch();  
              
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }*/
        
    }

    @FXML
    private void update_button(ActionEvent event) {
        try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management ; selectMethod=cursor", "sa", "123456");                       
           
            String v1=name.getText();
             String v2=address.getText();
              String v3=country.getText();
               String v4=email.getText();
               String v5=number.getText();
                String v6=age.getText();
               String sql="update Buyer set Name='"+v1+"',Address='"+v2+"',"
                       + "Country='"+v3+"',EmailId='"+v4+"',PhoneNumber='"+v5+"',Age='"+v6+"' where BuyerId=(select u_id from userInfo where p_id=(select max(p_id) from userInfo)) ";
               PreparedStatement pst=con.prepareStatement(sql);
               pst.execute();
               JOptionPane.showMessageDialog(null,"Updated");
            fetch();  
              
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
       public void fetch()
    {
     //list.clear();
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           
             ResultSet rs= con.createStatement().executeQuery("select * from Buyer where BuyerId=(select u_id from userInfo where p_id=(select max(p_id) from userInfo)) ");
             while(rs.next()){
                 name.setText(rs.getString("Name"));
                     
               // list.add(new Buyer(rs.getInt("BuyerId"),rs.getString("Name"),rs.getString("Address"),
                       // rs.getString("Country"),rs.getString("EmailId"),rs.getString("PhoneNumber")));
                
             }
            
            
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    @FXML
    private void update_pass(ActionEvent event) {
        
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management ; selectMethod=cursor", "sa", "123456");                       
           
            String v1=c_pass.getText();
             String v2=new_pass.getText();
              String v3=mew_pass_again.getText();
              if(v2.equals(v3)){
                   String sql="update Buyer set Pass='"+v2+"' where BuyerId=(select u_id from userInfo where p_id=(select max(p_id) from userInfo)) ";
               PreparedStatement pst=con.prepareStatement(sql);
               pst.execute();
               JOptionPane.showMessageDialog(null,"Updated");
            fetch();  
              
              }else{
                  JOptionPane.showMessageDialog(null,"Password doesnot match");
              }
              
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
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
