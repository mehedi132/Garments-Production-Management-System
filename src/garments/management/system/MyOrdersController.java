/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garments.management.system;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MyOrdersController implements Initializable {
ObservableList<myorder>list=FXCollections.observableArrayList();
    @FXML
    private TableView<myorder> table;
    @FXML
    private TableColumn<myorder, Integer> oid;
    @FXML
    private TableColumn<myorder, String> name;
    @FXML
    private TableColumn<myorder, String> color;
    @FXML
    private TableColumn<myorder, String> size;
    @FXML
    private TableColumn<myorder, Integer> q;
    @FXML
    private TableColumn<myorder, Integer> up;
    @FXML
    private TableColumn<myorder, Integer> tp;
    ResultSet rs;
    Connection con;
    @FXML
    private TableColumn<?, ?> s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        // TODO
        fetch();
    } catch (SQLException ex) {
        Logger.getLogger(MyOrdersController.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    private void gotoBestSellingPage(ActionEvent event) throws IOException {
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("BestSelling.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }
    public void fetch() throws SQLException
    {
    
     connectDB cond= new connectDB();
     con= cond.connectdb();
     
         
             ResultSet rs= con.createStatement().executeQuery("select *from ordered where BuyerId=(select u_id from userInfo where p_id=(select max(p_id)from userInfo))");
             while(rs.next()){
                 
                     
                list.add(new myorder(rs.getString("Product_Name"),rs.getString("Colour"),rs.getString("Size"),
                        rs.getInt("Quantity"),rs.getFloat("Unit_Price"),rs.getFloat("TotalAmount"),rs.getString("OrderDate"),rs.getString("OrderStatus")));
                
             }                                         
             oid.setCellValueFactory(new PropertyValueFactory<>("od"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
    size.setCellValueFactory(new PropertyValueFactory<>("size"));
     color.setCellValueFactory(new PropertyValueFactory<>("color"));
      q.setCellValueFactory(new PropertyValueFactory<>("q"));
     up.setCellValueFactory(new PropertyValueFactory<>("up"));
     tp.setCellValueFactory(new PropertyValueFactory<>("tp"));
     s.setCellValueFactory(new PropertyValueFactory<>("os"));
     table.setItems(list) ;                  
    }
    
}
