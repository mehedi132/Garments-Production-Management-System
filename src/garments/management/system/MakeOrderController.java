/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garments.management.system;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */

public class MakeOrderController implements Initializable {

    @FXML
    private ComboBox<String> combo1;

    @FXML
    private ComboBox<String> combo2;

    @FXML
    private ComboBox<String> combo3;
    Connection con;
    int r=10 ;
    ResultSet  rs ;
  
    String e,p;
    int z; 
       
            
    
    //private ComboBox<String> combo4;

    ObservableList<String> list1, list2,list3;
    //ObservableList<String> list4;
    

    /**
     * Initializes the controller class.
     * 
     */
    @FXML
    private ImageView imageview1;
   
    @FXML
    private TextField price;
    @FXML
    private TextField Quantity;
    private TextField email;
    private TextField pass;
    public MakeOrderController() {
        this.list1 = FXCollections.observableArrayList("Full-Shirt","Half-Shirt","Polo t-shirt");
        this.list2 = FXCollections.observableArrayList("S","M","L","XL");
        this.list3 = FXCollections.observableArrayList("Purple","Black","Red","Blue","Light-Blue","White");
      
    }
    

    
    @FXML
    void Select(ActionEvent event) {
        
        String s= combo1.getSelectionModel().getSelectedItem();
        
        if(s.equals("Full-Shirt"))
        {
             
            Image img = new Image("/img/bluecheck_fullshirt.png");
            imageview1.setImage(img);
            price.setText("7.55");
        }
        else if(s.equals("Half-Shirt"))
        {
             Image img = new Image("/img/pichalfshirt.png");
            imageview1.setImage(img);
             price.setText("5.75");
        }
        else
        {
            Image img = new Image("/img/bluepolofront.png");
            imageview1.setImage(img);
             price.setText("8.25");
        }
    }
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo1.setItems(list1);
        combo2.setItems(list2);
        combo3.setItems(list3);
         
           
            //System.out.println(r);
        //combo4.setItems(list4);
       
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

    @FXML
    private void gotodashboardpage(ActionEvent event) throws IOException {
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
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
    private void AddToCart(ActionEvent event) throws SQLException {
        connectDB cond= new connectDB();
        con=cond.connectdb();
        if(combo1.getSelectionModel().getSelectedItem()==null || combo2.getSelectionModel().getSelectedItem()==null || combo3.getSelectionModel().getSelectedItem()==null ||
                Quantity.getText()==null || price.getText()==null){
            Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("No feild can be empty ");
            a2.showAndWait();
            
        }
        else{
            
       
        String q2="select u_id from userInfo where p_id=(select max(p_id) from userInfo) ";
        ResultSet rs=con.createStatement().executeQuery(q2);
        while(rs.next()){
            z=rs.getInt("u_id");
        }
          
        String q1="insert into cart (u_id,ProductName,Size,Colour,Quantity,Unit_Price,Total_Price) values(?,?,?,?,?,?,?)";           
            
            
            PreparedStatement pst=con.prepareStatement(q1);
             pst.setString(1,Integer.toString(z));
            pst.setString(2,combo1.getSelectionModel().getSelectedItem());
            pst.setString(3,combo2.getSelectionModel().getSelectedItem());
              pst.setString(4,combo3.getSelectionModel().getSelectedItem());
              pst.setString(5,Quantity.getText());
              try {   
                 float b=Float.parseFloat(Quantity.getText()) ;
               float a=Float.parseFloat(price.getText());
               System.out.println(a+b);
               pst.setString(6,price.getText());
               float g=a*b;
               String f=Float.toString(g);
                pst.setString(7,f);
                 pst.executeUpdate();
                 Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText(" Product Added To Cart! ");
            a2.showAndWait();
                 
           
             
            } catch (NumberFormatException e) {
              System.out.println("not a number"); 
            }
      }
  }
                
       
    


    

    @FXML
    private void makeorderagain(ActionEvent event) throws IOException {
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("MakeOrder.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    
}

    
    
    
    
    
    

