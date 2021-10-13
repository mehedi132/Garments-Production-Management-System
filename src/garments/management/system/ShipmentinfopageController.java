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
public class ShipmentinfopageController implements Initializable {

    @FXML
    private Button backtohome;
    @FXML
    private TextField oid;
    @FXML
    private TextField sd;
    @FXML
    private TextField sc;
    @FXML
    private TextField sch;
    @FXML
    private TextField da;
    @FXML
    private TextField dd;
    @FXML
    private TextField pi;
    @FXML
    private TextField ds;
    @FXML
    private TableView<shipment> table;
    @FXML
    private TableColumn<shipment, Integer> c_oid;
    @FXML
    private TableColumn<shipment, String> c_da;
    @FXML
    private TableColumn<shipment, String> c_sd;
    @FXML
    private TableColumn<shipment, String> c_sch;
    @FXML
    private TableColumn<shipment, Integer> c_sc;
    @FXML
    private TableColumn<shipment, String> c_ds;
    @FXML
    private TableColumn<shipment, String> c_dd;
    @FXML
    private TableColumn<shipment, Integer> c_pi;
    @FXML
    private TextField search;
    
     ObservableList<shipment>list=FXCollections.observableArrayList();
      int index=-1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void update(ActionEvent event) {
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=oid.getText();
            String v2=pi.getText();
             String v3=da.getText();
              String v4=sd.getText();
               String v5=sch.getText();
               String v6=sc.getText();
               String v7=ds.getText();
               String v8=dd.getText();
               String sql="update ShipmentInfo set OrderId='"+v1+"', ProductionStatus_ID='"+v2+"',"
                       + "DeliveryAddress='"+v3+"',"
                       + "Shipment_Date='"+v4+"',ShipmentChannel='"+v5+"',ShipmentCost='"+v6+"'"
                       + " ,DeliveryStatus='"+v7+"',DeliveryDate='"+v8+"' where OrderId='"+v1+"' ";
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
    private void insert(ActionEvent event) {
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");
           
             String q="insert into ShipmentInfo (OrderId,ProductionStatus_ID,DeliveryAddress"
                     + ",Shipment_Date,ShipmentChannel,ShipmentCost,DeliveryStatus,DeliveryDate) values(?,?,?,?,?,?,?,?)";
            
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,oid.getText());
            pst.setString(2,pi.getText());
              pst.setString(3,da.getText());
              pst.setString(4,sd.getText());
              pst.setString(5,sch.getText());
              pst.setString(6,sc.getText());
                  pst.setString(7,ds.getText());
                    pst.setString(8,dd.getText());
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
    private void delete(ActionEvent event) {
        
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
    private void MouceClick(MouseEvent event) {
        index=table.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
        }
        oid.setText(c_oid.getCellData(index).toString());
         pi.setText(c_pi.getCellData(index).toString());
          da.setText(c_da.getCellData(index));
           sd.setText(c_sd.getCellData(index));
            sch.setText(c_sch.getCellData(index));
            sc.setText(c_sc.getCellData(index).toString());
           
             ds.setText(c_ds.getCellData(index));
              dd.setText(c_dd.getCellData(index));
    
    }
    
      public void fetch()
    {
     list.clear();
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           
             ResultSet rs= con.createStatement().executeQuery("select *from ShipmentInfo");
             while(rs.next()){
                 
                     
                list.add(new shipment(rs.getInt("OrderId"),rs.getInt("ProductionStatus_ID")
                        
               ,rs.getString("DeliveryAddress"),
                        rs.getString("Shipment_Date"),rs.getString("ShipmentChannel"),
                        rs.getInt("ShipmentCost"),  rs.getString("DeliveryStatus"),  rs.getString("DeliveryDate")));
                
             }
            
            
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
      c_oid.setCellValueFactory(new PropertyValueFactory<>("oid"));
        c_pi.setCellValueFactory(new PropertyValueFactory<>("pi"));
    c_da.setCellValueFactory(new PropertyValueFactory<>("da"));
     c_sd.setCellValueFactory(new PropertyValueFactory<>("sd"));
      c_sch.setCellValueFactory(new PropertyValueFactory<>("sch"));
      c_sc.setCellValueFactory(new PropertyValueFactory<>("sc"));
       c_ds.setCellValueFactory(new PropertyValueFactory<>("ds"));
        c_dd.setCellValueFactory(new PropertyValueFactory<>("dd"));
      table.setItems(list);
      
      pi.clear();
             oid.clear();
             da.clear();
             dd.clear();
             ds.clear(); 
             sd.clear();
              sc.clear();
               sch.clear();
             
    }
      
      void search()
    {
         
        FilteredList<shipment> filteredData = new FilteredList<>(list, b -> true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(shipment -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (shipment.getDa() .toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} else if (shipment.getDd() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                 else if (shipment.getDs() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                  else if (shipment.getSd() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                  else if (shipment.getSch() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (String.valueOf(shipment.getSc()).indexOf(lowerCaseFilter)!=-1)
                                {
                                     return true;
                                }
				    
                                else if (String.valueOf(shipment.getOid()).indexOf(lowerCaseFilter)!=-1)
				     return true;
                                else if (String.valueOf(shipment.getPi()).indexOf(lowerCaseFilter)!=-1)
				     return true;
                                
                               
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<shipment> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		
		table.setItems(sortedData);
    }

     void delete()
    {
           try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=oid.getText();
           
                  String sql="delete from ShipmentInfo where OrderId=? ";
                       
               PreparedStatement pst=con.prepareStatement(sql);
               pst.setString(1, v1);
          
               pst.execute();
               
             
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
}
