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
public class OrderpageController implements Initializable {

    @FXML
    private Button backtohome;
    @FXML
    private Button updateButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField o_id;
    @FXML
    private TextField p_id;
    @FXML
    private TextField o_date;
    @FXML
    private TextField quantity;
    @FXML
    private TextField b_id;
    @FXML
    private TextField d_date;
    @FXML
    private TextField o_status;
    @FXML
    private TextField tamount;
    @FXML
    private TableColumn<order, Integer> col_id;
    @FXML
    private TableColumn<order, Integer> col_b_id;
    @FXML
    private TableColumn<order, Integer> col_p_id;
    @FXML
    private TableColumn<order, Integer> col_quantity;
    @FXML
    private TableColumn<order, String> col_odata;
    @FXML
    private TableColumn<order, Float> col_tamount;
    @FXML
    private TableColumn<order, String> col_d_date;
    @FXML
    private TableColumn<order, String> col_o_status;
    @FXML
    private TableView<order> order;
    ObservableList<order>list=FXCollections.observableArrayList();
      int index=-1;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        o_id.setEditable(false);
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
    private void updateAction(ActionEvent event) {
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=o_id.getText();
            String v2=b_id.getText();
             String v3=p_id.getText();
              String v4=quantity.getText();
               String v5=o_date.getText();
               String v6=tamount.getText();
               String v7=d_date.getText();
               String v8= o_status.getText();
              
               String sql="update Ordered set  BuyerId='"+v2+"',Product_Id='"+v3+"',"
                       + "Quantity='"+v4+"',OrderDate='"+v5+"',TotalAmount='"+v6+"',"
                       + "DeliveryDate='"+v7+"',OrderStatus='"+v8+"' where  OrderId='"+v1+"' ";
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
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");
           
             String q="insert into Ordered(BuyerId,Product_Id,Quantity,OrderDate,TotalAmount,DeliveryDate,OrderStatus) values(?,?,?,?,?,?,?)";
            
            PreparedStatement pst=con.prepareStatement(q);
      
            pst.setString(1,b_id.getText());
              pst.setString(2,p_id.getText());
              pst.setString(3,quantity.getText());
              pst.setString(4,o_date.getText());
              pst.setString(5,tamount.getText());
              pst.setString(6,d_date.getText());
              pst.setString(7, o_status.getText());
             
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
             search();
    }

    @FXML
    private void MouseClick(MouseEvent event) {
         index=  order.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
        }
        o_id.setText(col_id.getCellData(index).toString());
         b_id.setText(col_b_id.getCellData(index).toString());
          p_id.setText(col_p_id.getCellData(index).toString());
           quantity.setText(col_quantity.getCellData(index).toString());
            o_date.setText(col_odata.getCellData(index));
             tamount.setText(col_tamount.getCellData(index).toString());
              d_date.setText(col_d_date.getCellData(index));
                o_status.setText(col_o_status.getCellData(index));
    }
    
    public void fetch()
    {
     list.clear();
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           
             ResultSet rs= con.createStatement().executeQuery("select *from Ordered");
             while(rs.next()){                                   
                list.add(new order( rs.getInt("OrderId"),rs.getInt("BuyerId"),rs.getInt("Product_Id"),rs.getInt("Quantity"),
                        rs.getString("OrderDate"),rs.getFloat("TotalAmount"),rs.getString("DeliveryDate"),rs.getString("OrderStatus") ));
                      
             }                       
            
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
      col_id.setCellValueFactory(new PropertyValueFactory<>("oid"));
        col_b_id.setCellValueFactory(new PropertyValueFactory<>("bid"));
          
         col_p_id.setCellValueFactory(new PropertyValueFactory<>("pid"));
         col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
     col_odata.setCellValueFactory(new PropertyValueFactory<>("o_date"));
  col_tamount.setCellValueFactory(new PropertyValueFactory<>("tamount"));
     col_d_date.setCellValueFactory(new PropertyValueFactory<>("d_date"));
  col_o_status.setCellValueFactory(new PropertyValueFactory<>("o_status"));
     order.setItems(list);
             o_id.clear();
             b_id.clear();
             p_id.clear();
              quantity.clear();
             o_date.clear();            
             tamount.clear();  
             d_date.clear();  
              o_status.clear();  
    }
   
    void search()
    {
         
        FilteredList<order> filteredData = new FilteredList<>(list, b -> true);
		
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(order -> {
			
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				 if(order.getO_date().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} 
                                 else if(order.getD_date().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} 
                                 else  if(order.getO_status().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} 
                                    else if (String.valueOf(order.getOid()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                    else if (String.valueOf(order.getPid()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                    else if (String.valueOf(order.getQuantity()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                    else if (String.valueOf(order.getBid()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                    else if (String.valueOf(order.getTamount()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                   
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<order> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(order.comparatorProperty());
		
		
		order.setItems(sortedData);
                
                
      
    }
    
    
    
    
      void delete()
    {
           try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=o_id.getText();
           
               String sql="delete from ordered where OrderId=? ";
                       
               PreparedStatement pst=con.prepareStatement(sql);
               pst.setString(1, v1);
          
               pst.execute();
               
             
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
    }

  


}
