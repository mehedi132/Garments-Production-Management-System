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
public class ProductionpageController implements Initializable {

    @FXML
    private Button backtohome;
    @FXML
    private TextField production_id;
    @FXML
    private TextField p_id;
    @FXML
    private TextField c_product;
    @FXML
    private Button update;
    @FXML
    private Button insert;
    @FXML
    private TextField e_id;
    @FXML
    private TextField o_id;
    @FXML
    private TextField date;
    @FXML
    private TextField tsalary;
    @FXML
    private Button delete;
    @FXML
    private TableView<production> production;
    @FXML
    private TableColumn<production, Integer> col_productionId;
    @FXML
    private TableColumn<production, Integer> col_oid;
    @FXML
    private TableColumn<production, Integer> col_pid;
    @FXML
    private TableColumn<production, Integer> col_eid;
    @FXML
    private TableColumn<production, Integer> col_cProduct;
    @FXML
    private TableColumn<production, Float> col_tsalary;
    @FXML
    private TableColumn<production, String> col_date;
    ObservableList<production>list=FXCollections.observableArrayList();
      int index=-1;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fetch();
        search();
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
    private void insertAction(ActionEvent event) {
        
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");
           
             String q="insert into Production(Production_Id,OrderId ,ProductId,Employee_Id,CompletedProduct,TodaySalary,Date) values(?,?,?,?,?,?,?)";
            
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,production_id.getText());
            pst.setString(2,o_id.getText());
              pst.setString(3,p_id.getText());
              pst.setString(4,e_id.getText());
              pst.setString(5,c_product.getText());
              pst.setString(6,tsalary.getText());
              pst.setString(7,date.getText());
             
             
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
    
    @FXML
    private void updateAction(ActionEvent event) {
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=production_id.getText();
            String v2=o_id.getText();
             String v3=p_id.getText();
              String v4=e_id.getText();
               String v5=c_product.getText();
               String v6=tsalary.getText();
               String v7=date.getText();
              
              
               String sql="update Production set Production_Id='"+v1+"', OrderId ='"+v2+"',ProductId='"+v3+"',"
                       + "Employee_Id='"+v4+"',CompletedProduct='"+v5+"',TodaySalary='"+v6+"',"
                       + "Date='"+v7+"' where  Production_Id='"+v1+"' ";
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

    @FXML
    private void MouseClick(MouseEvent event) {
        index=  production.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
        }
        production_id.setText(col_productionId.getCellData(index).toString());
         o_id.setText(col_oid.getCellData(index).toString());
          p_id.setText(col_pid.getCellData(index).toString());
           e_id.setText(col_eid.getCellData(index).toString());
            c_product.setText(col_cProduct.getCellData(index).toString());
             tsalary.setText(col_tsalary.getCellData(index).toString());
              date.setText(col_date.getCellData(index));
                
    }
    
     public void fetch()
    {
     list.clear();
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           
             ResultSet rs= con.createStatement().executeQuery("select *from Production");
             while(rs.next()){                                   
                list.add(new production( rs.getInt("Production_Id"),rs.getInt("OrderId"),rs.getInt("ProductId"),rs.getInt("Employee_Id"),
                        rs.getInt("CompletedProduct"),rs.getFloat("TodaySalary"),rs.getString("Date") ));
                      
             }                       
            
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
      col_productionId.setCellValueFactory(new PropertyValueFactory<>("productionId"));
        col_oid.setCellValueFactory(new PropertyValueFactory<>("oid"));
          
         col_pid.setCellValueFactory(new PropertyValueFactory<>("pid"));
         col_eid.setCellValueFactory(new PropertyValueFactory<>("eid"));
     col_cProduct.setCellValueFactory(new PropertyValueFactory<>("cproduct"));
  col_tsalary.setCellValueFactory(new PropertyValueFactory<>("tsalary"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
  
  
     production.setItems(list);
            production_id.clear();
             o_id.clear();
             p_id.clear();
              e_id.clear();
             c_product.clear();            
             tsalary.clear();  
             date.clear();  
             
    }
     
    @FXML
     void search()
    {
         
        FilteredList<production> filteredData = new FilteredList<>(list, b -> true);
		
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(production -> {
			
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (production.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} 
                                    else if (String.valueOf(production.getTsalary()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                     else if (String.valueOf(production.getEid()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }		
                                      else if (String.valueOf(production.getOid()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }		
                                       else if (String.valueOf(production.getPid()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }		
                                        else if (String.valueOf(production.getProductionId()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                        else if (String.valueOf(production.getCproduct()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }		
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<production> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(production.comparatorProperty());
		
		
		production.setItems(sortedData);
    }

     void delete()
    {
           try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=production_id.getText();
           
               String sql="delete from  Production where Production_Id =? ";
                       
               PreparedStatement pst=con.prepareStatement(sql);
               pst.setString(1, v1);
          
               pst.execute();
               
             
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
    }

  


}
    
    
    
    

