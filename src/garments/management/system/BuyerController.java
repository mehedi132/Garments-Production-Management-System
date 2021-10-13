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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Mohammad Rasel
 */
public class BuyerController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private TextField id;
   
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField country;
    @FXML
    private TextField email;
    @FXML
    private Button updatebutton;
    @FXML
    private Button insertButton;
    @FXML
    private TextField phone;
    @FXML
    private Button deleteButton;
    @FXML
    private TableColumn<Buyer, Integer> col_id;
    @FXML
    private TableColumn<Buyer, String> col_name;
    @FXML
    private TableColumn<Buyer, String> col_address;
    @FXML
    private TableColumn<Buyer, String> col_country;
    @FXML
    private TableColumn<Buyer, String> col_email;
    @FXML
    private TableColumn<Buyer, String> col_phone;
    @FXML
    private TableView<Buyer> Buyer;
     ObservableList<Buyer>list=FXCollections.observableArrayList();
      int index=-1;
    @FXML
    private TextField search;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         id.setEditable(false);
       fetch();
    }    

    
    
    @FXML
    private void gotohomepageOnaction(ActionEvent event) throws IOException {
        
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
             String v3=address.getText();
              String v4=country.getText();
               String v5=email.getText();
               String v6=phone.getText();
                id.setEditable(false);
               String sql="update Buyer set  Name='"+v2+"',Address='"+v3+"',"
                       + "Country='"+v4+"',EmailId='"+v5+"',PhoneNumber='"+v6+"' where BuyerId='"+v1+"' ";
               PreparedStatement pst=con.prepareStatement(sql);
               pst.execute();
               Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("Successfully Update the  Information");
            a2.showAndWait();
             fetch(); 
             search();
              
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void InsertAction(ActionEvent event) {
         if(name.getText().equals(""))
    {
      JOptionPane.showMessageDialog(null,"name is empty");
      
     }
         else
         {
        
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");
           
             String q="insert into Buyer (Name,Address,Country,EmailId,PhoneNumber) values(?,?,?,?,?)";
            
            PreparedStatement pst=con.prepareStatement(q);
         
            pst.setString(1,name.getText());
              pst.setString(2,address.getText());
              pst.setString(3,country.getText());
              pst.setString(4,email.getText());
              
                  pst.setString(5,phone.getText());
              pst.executeUpdate();
          Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("Successfully Data inserted");
            a2.showAndWait();
             fetch();    
             search();
            
           
           
    
      } catch (Exception e) {
            e.printStackTrace();
       }
         }
         
         
   
        
    }

    
    
    @FXML
    private void deleteAction(ActionEvent event) {
        
        
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
             search();
       
    }
    
    
    
      public void fetch()
    {
     list.clear();
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           
             ResultSet rs= con.createStatement().executeQuery("select *from Buyer");
             while(rs.next()){
                 
                     
                list.add(new Buyer(rs.getInt("BuyerId"),rs.getString("Name"),rs.getString("Address"),
                        rs.getString("Country"),rs.getString("EmailId"),rs.getString("PhoneNumber")));
                
             }
            
            
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
      col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
    col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
     col_country.setCellValueFactory(new PropertyValueFactory<>("country"));
      col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
      col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
      Buyer.setItems(list);
      
      name.clear();
             id.clear();
             country.clear();
             address.clear();
             email.clear(); 
             phone.clear();
             
    }

    @FXML
    private void MouseClick(MouseEvent event) {
         index=Buyer.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
        }
        id.setText(col_id.getCellData(index).toString());
         name.setText(col_name.getCellData(index));
          address.setText(col_address.getCellData(index));
           country.setText(col_country.getCellData(index));
            email.setText(col_email.getCellData(index));
            phone.setText(col_phone.getCellData(index));
    }

     void search()
    {
         
        FilteredList<Buyer> filteredData = new FilteredList<>(list, b -> true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(buyer -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (buyer.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} else if (buyer.getCountry() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                 else if (buyer.getAddress() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                  else if (buyer.getEmail() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                  else if (buyer.getPhone() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (String.valueOf(buyer.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
                                
                               
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<Buyer> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(Buyer.comparatorProperty());
		
		
		Buyer.setItems(sortedData);
    }
     
      void delete()
    {
           try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=id.getText();
           
               String sql="delete from Buyer where BuyerId=? ";
                       
               PreparedStatement pst=con.prepareStatement(sql);
               pst.setString(1, v1);
          
               pst.execute();
               
             
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    
}


