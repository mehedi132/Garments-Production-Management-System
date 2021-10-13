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
public class TransactionpageController implements Initializable {

    @FXML
    private Button backtohome;
    @FXML
    private TextField tid;
    @FXML
    private TextField ta;
    @FXML
    private TextField da;
    @FXML
    private TextField pa;
    @FXML
    private TextField oid;
    @FXML
    private TextField d;
    @FXML
    private TextField bid;
    @FXML
    private TextField ld;
    @FXML
    private TableView<transactionpage> table;
    @FXML
    private TableColumn<transactionpage, Integer> c_tid;
    @FXML
    private TableColumn<transactionpage, Integer> c_oid;
    @FXML
    private TableColumn<transactionpage, Integer> c_ta;
    @FXML
    private TableColumn<transactionpage, Integer> c_pa;
    @FXML
    private TableColumn<transactionpage, Integer> c_da;
    @FXML
    private TableColumn<transactionpage, String> c_ld;
    @FXML
    private TableColumn<transactionpage, String> c_d;
    @FXML
    private TableColumn<transactionpage, Integer> c_bid;
    ObservableList<transactionpage>list=FXCollections.observableArrayList();
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
    private void update(ActionEvent event) {
           try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=tid.getText();
            String v2=oid.getText();
             String v3=ta.getText();
              String v4=pa.getText();
               String v5=da.getText();
               String v7=ld.getText();
               String v6=d.getText();
               String v8=bid.getText();
               String sql="update Transaction_Information set Transaction_Id='"+v1+"', OrderId='"+v2+"',BuyerId='"+v8+"',"
                       + "TotalAmount='"+v3+"',PaidAmount='"+v4+"',DueAmount='"+v5+"',Transaction_Date='"+v6+"',LastDate='"+v7+"' where Transaction_Id='"+v1+"' ";
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
           
             String q="insert into Transaction_Information"
                     + " (Transaction_Id,OrderId,BuyerId,TotalAmount,PaidAmount,DueAmount,Transaction_Date,LastDate) values(?,?,?,?,?,?,?,?)";
            
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,tid.getText());
            pst.setString(2,oid.getText());
              pst.setString(4,ta.getText());
              pst.setString(5,pa.getText());
              pst.setString(6,da.getText());
              pst.setString(8,ld.getText());
                  pst.setString(7,d.getText());
                     pst.setString(3,bid.getText());
                       
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
    
    
    
      public void fetch()
    {
     list.clear();
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           
             ResultSet rs= con.createStatement().executeQuery("select *from Transaction_Information");
             while(rs.next()){
                 
                     
                list.add(new transactionpage(rs.getInt("Transaction_Id"),rs.getInt("OrderId"),rs.getInt("BuyerId"),
                        rs.getInt("TotalAmount"),rs.getInt("PaidAmount"),rs.getInt("DueAmount"),
                        rs.getString("Transaction_Date"),rs.getString("LastDate")));
                
             }
            
            
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
      c_tid.setCellValueFactory(new PropertyValueFactory<>("tid"));
       c_oid.setCellValueFactory(new PropertyValueFactory<>("oid"));
    c_bid.setCellValueFactory(new PropertyValueFactory<>("bid"));
     c_ta.setCellValueFactory(new PropertyValueFactory<>("ta"));
      c_pa.setCellValueFactory(new PropertyValueFactory<>("pa"));
     
      c_da.setCellValueFactory(new PropertyValueFactory<>("da"));
      c_d.setCellValueFactory(new PropertyValueFactory<>("td"));
       c_ld.setCellValueFactory(new PropertyValueFactory<>("ld"));
     table.setItems(list);
      
     ta.clear();
             tid.clear();
             oid.clear();
             pa.clear();
             da.clear(); 
             ld.clear();
              d.clear();
               bid.clear();
             
    }


    @FXML
    private void MouseClick(MouseEvent event) {
         index=table.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
        }
        tid.setText(c_tid.getCellData(index).toString());
         oid.setText(c_oid.getCellData(index).toString());
          ta.setText(c_ta.getCellData(index).toString());
           pa.setText(c_pa.getCellData(index).toString());
           da.setText(c_da.getCellData(index).toString());
            ld.setText(c_ld.getCellData(index));
             d.setText(c_d.getCellData(index));
              bid.setText(c_bid.getCellData(index).toString());
    }

     void search()
    {
         
        FilteredList<transactionpage> filteredData = new FilteredList<>(list, b -> true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(transactionpage -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(transactionpage.getTid()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} else if (String.valueOf(transactionpage.getOid()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                 else if (String.valueOf(transactionpage.getTa()).indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                  else if (String.valueOf(transactionpage.getPa()).indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                  else if (String.valueOf(transactionpage.getDa()).indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (String.valueOf(transactionpage.getBid()).indexOf(lowerCaseFilter)!=-1){
                                    return true;
                                }                                    	    
                                      else if (transactionpage.getTd() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                      else if (transactionpage.getLd() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                
                               
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<transactionpage> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		
		table.setItems(sortedData);
    }
 void delete()
    {
           try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=tid.getText();
           
             String sql="delete from Transaction_Information where Transaction_Id=? ";
               PreparedStatement pst=con.prepareStatement(sql);
               pst.setString(1, v1);
          
               pst.execute();
               
             
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    

   
    
}
