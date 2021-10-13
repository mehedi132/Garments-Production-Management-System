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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohammad Rasel
 */
public class HomepageController implements Initializable {

    @FXML
    private Button employee_table;
    @FXML
    private Button buyer_table;
    @FXML
    private Button order_table;
    @FXML
    private Button productlist_table;
    @FXML
    private Button production_table;
    @FXML
    private Button productStatus_table;
    @FXML
    private Button transaction_table;
    @FXML
    private Button shipment_table;
    private TextField torder;
    @FXML
    private TextField LO;
    @FXML
    private TextField LPS;
    @FXML
    private TextField LS;
    @FXML
    private TextField LP;
     Connection con;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    
    //private Button employee_table;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectDB cond = new connectDB();
       con=cond.connectdb();
           
             String q="select max(OrderId) as 'max' from Ordered";
            
        try {
            rs=con.createStatement().executeQuery(q);
             while(rs.next()){   
               //String a= Integer.toString(rs.getInt("OrderId"));
                LO.setText(rs.getString("max"));   
        }
        }catch (SQLException e) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, e);
        }
    
     String q1="select max(OrderId) as 'max' from ShipmentInfo";
            
        try {
            rs=con.createStatement().executeQuery(q1);
             while(rs.next()){   
               //String a= Integer.toString(rs.getInt("OrderId"));
                LS.setText(rs.getString("max"));   
        }
        }catch (SQLException e) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, e);
        }
        
         String q2="select BuyerId,Transaction_Date from Transaction_Information where "
                 + "Transaction_Date in (select max(Transaction_Date) from Transaction_Information)";
            System.out.println(q2);
        try {
            rs=con.createStatement().executeQuery(q2);
             while(rs.next()){   
               //String a= Integer.toString(rs.getInt("OrderId"));
               int w= rs.getInt("BuyerId");
               String a=rs.getString("Transaction_Date");
                LP.setText(Integer.toString(w)+" and "+a);  
                //LP.setText(rs.getString("Transaction_Date"));   
        }
        }catch (SQLException e) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        String q3="select OrderId from ProductionStatus where Production_Date in (select max(Production_Date) from ProductionStatus)";
            System.out.println(q2);
        try {
            rs=con.createStatement().executeQuery(q3);
             while(rs.next()){   
               //String a= Integer.toString(rs.getInt("OrderId"));
               int w= rs.getInt("OrderId");
                LPS.setText(Integer.toString(w)+"\t");  
                 
        }
        }catch (SQLException e) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, e);
        }
        
       
    }    

    @FXML
   private void gotoemployeepageOnAction(ActionEvent event) throws IOException{
        
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("employeepage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
    } 

    @FXML
    private void gotobuyerpageOnAction(ActionEvent event) throws IOException {
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("Buyer.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
    }

    @FXML
    private void gotoorderpageOnActionpage(ActionEvent event) throws IOException {
        
          Parent employee_page_parent = FXMLLoader.load(getClass().getResource("orderpage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
        
        
    }

    @FXML
    private void gotoproductlistpageOnAction(ActionEvent event) throws IOException {
          Parent employee_page_parent = FXMLLoader.load(getClass().getResource("productlistpage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
        
        
        
}

    @FXML
    private void gotoproductionpageOnAction(ActionEvent event) throws IOException {
        
          Parent employee_page_parent = FXMLLoader.load(getClass().getResource("productionpage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
        
    }

    @FXML
    private void gotoproductStatusOnAction(ActionEvent event) throws IOException {
        
             Parent employee_page_parent = FXMLLoader.load(getClass().getResource("productionstatuspage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
        
    }

    @FXML
    private void gotoTransactionpageOnAction(ActionEvent event) throws IOException {
        
             Parent employee_page_parent = FXMLLoader.load(getClass().getResource("transactionpage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
        
    }

    @FXML
    private void gotoshipmentpageaOnAction(ActionEvent event) throws IOException {
        
            Parent employee_page_parent = FXMLLoader.load(getClass().getResource("shipmentinfopage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
        
    }

    @FXML
    private void backtoadsignin(ActionEvent event) throws IOException {
        
          Parent employee_page_parent = FXMLLoader.load(getClass().getResource("adminoption.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
        
    }

    @FXML
    private void gotostatreport(ActionEvent event) throws IOException {
        
          Parent employee_page_parent = FXMLLoader.load(getClass().getResource("statreport.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
        
        
        
    }
    
    
    

    

   
    
}
