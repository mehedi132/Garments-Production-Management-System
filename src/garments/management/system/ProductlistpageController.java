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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohammad Rasel
 */
public class ProductlistpageController implements Initializable {

    @FXML
    private Button backtohome;
    @FXML
    private Button updateButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private TextField id;
    @FXML
    private TextField description;
    @FXML
    private TextField accessoris;
    @FXML
    private TextField size;
    @FXML
    private TextField name;
    @FXML
    private TextField uprice;
    @FXML
    private TextField mcost;
    
     ObservableList<productList>list=FXCollections.observableArrayList();
      int index=-1;
      
    @FXML
    private TableColumn<productList, Integer> col_id;
    @FXML
    private TableColumn<productList, String> col_name;
    @FXML
    private TableColumn<productList, String> col_des;
    @FXML
    private TableColumn<productList, String> col_size;
    @FXML
    private TableColumn<productList, String> col_acce;
    @FXML
    private TableColumn<productList, Float> col_mcost;
    @FXML
    private TableColumn<productList, Float> col_uprice;
    @FXML
    private TableView<productList> productList;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fetch();
    }    

    @FXML
    private void gotohomepageOnAction(ActionEvent event) throws IOException {
        
           Parent employee_page_parent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    @FXML
    private void updateAction(ActionEvent event) {
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=id.getText();
            String v2=name.getText();
             String v3=description.getText();
              String v4=size.getText();
               String v5=accessoris.getText();
                String v6=mcost.getText();
                 String v7=uprice.getText();
              
               String sql="update ProductList set Product_Id='"+v1+"', ProductName='"+v2+"',Description='"+v3+"',"
                       + "Size='"+v4+"',Accessories='"+v5+"',MakingCost='"+v6+"',UnitPrice='"+v7+"' where Product_Id='"+v1+"' ";
               PreparedStatement pst=con.prepareStatement(sql);
               pst.execute();
              Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("Successfully Update the  Information");
            a2.showAndWait();
             fetch();  
              
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void insertAction(ActionEvent event) {
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");
           
             String q="insert into ProductList(Product_Id,ProductName,Description,Size,Accessories,MakingCost,UnitPrice ) values(?,?,?,?,?,?,?)";
            
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,id.getText());
            pst.setString(2,name.getText());
              pst.setString(3,description.getText());
              pst.setString(4,size.getText());
              pst.setString(5,accessoris.getText());
               pst.setString(6,mcost.getText());
                pst.setString(7,uprice.getText());
             
              pst.executeUpdate();
            Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("Successfully Data inserted");
            a2.showAndWait();
             fetch();                                      
            
           
           
    
      } catch (Exception e) {
            e.printStackTrace();
       }
    }

    @FXML
    private void DeleteAction(ActionEvent event) {
          Alert a2=new Alert(Alert.AlertType.CONFIRMATION);
            a2.setContentText("Want to Delete??");
            
            Optional<ButtonType>res=a2.showAndWait();
            
            if(res.get()==ButtonType.OK)
                    {
                        delete();
                    }
            else
            {
                fetch();
            }
            
            
             fetch();  
           
    }

    @FXML
    private void MouseClick(MouseEvent event) {
        
         index=productList.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
        }
        id.setText(col_id.getCellData(index).toString());
         name.setText(col_name.getCellData(index));
          description.setText(col_des.getCellData(index));
          size.setText(col_size.getCellData(index));
          
          accessoris.setText(col_acce.getCellData(index));
           mcost.setText(col_mcost.getCellData(index).toString());
            uprice.setText(col_uprice.getCellData(index).toString());
           
            
    }
    
     public void fetch()
    {
     list.clear();
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           
             ResultSet rs= con.createStatement().executeQuery("select *from ProductList");
             while(rs.next()){
                 
                     
                list.add(new productList( rs.getInt("Product_Id"),rs.getString("ProductName"),rs.getString("Description"),
                        rs.getString("Size"),rs.getString("Accessories"),rs.getFloat("MakingCost"),rs.getFloat("UnitPrice") ));
                      
             }
             
            
            
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
      col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
          
         col_des.setCellValueFactory(new PropertyValueFactory<>("description"));
          col_size.setCellValueFactory(new PropertyValueFactory<>("size"));
     col_acce.setCellValueFactory(new PropertyValueFactory<>("accessories"));
     col_mcost.setCellValueFactory(new PropertyValueFactory<>("mcost"));
     col_uprice.setCellValueFactory(new PropertyValueFactory<>("uprice"));
     
      productList.setItems(list);
             id.clear();
             name.clear();
             description.clear();
              size.clear();
             accessoris.clear(); 
             mcost.clear();
              uprice.clear();
             
             
    }
     
      void delete()
    {
           try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=id.getText();
           
                String sql="delete from ProductList where Product_Id=? ";
               PreparedStatement pst=con.prepareStatement(sql);
               pst.setString(1, v1);
          
               pst.execute();
               
             
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
     
   
    
}
