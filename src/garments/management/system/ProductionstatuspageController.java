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
public class ProductionstatuspageController implements Initializable {

    @FXML
    private Button backtohome;
    @FXML
    private TextField psId;
    @FXML
    private TextField pid;
    @FXML
    private TextField tcompleted;
    @FXML
    private Button update;
    @FXML
    private Button insert;
    @FXML
    private TextField torder;
    @FXML
    private TextField oid;
    @FXML
    private TextField totalCompleted;
    @FXML
    private TextField uncompleted;
    @FXML
    private TextField l_date;
    @FXML
    private TextField p_date;
    @FXML
    private Button delete;
    @FXML
    private TableView<productionStatus> productionStatus;
    @FXML
    private TableColumn<productionStatus, Integer> col_psid;
    @FXML
    private TableColumn<productionStatus, Integer> col_oid;
    @FXML
    private TableColumn<productionStatus, Integer> col_torder;
    @FXML
    private TableColumn<productionStatus, Integer> col_todaycom;
    @FXML
    private TableColumn<productionStatus, String> col_pdate;
    @FXML
    private TableColumn<productionStatus, Integer> col_totalcom;
    @FXML
    private TableColumn<productionStatus, Integer> col_pid;
    @FXML
    private TableColumn<productionStatus, Integer> col_uncom;
    @FXML
    private TableColumn<productionStatus, String> col_lDate;
    
    ObservableList<productionStatus>list=FXCollections.observableArrayList();
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
    private void insert(ActionEvent event) {
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");
           
             String q="insert into ProductionStatus(ProductionStatus_ID,OrderId,ProductId,Production_Date,"
                     + "TotalOrder,TodayCompeted,TotalCompleted,uncompleted,LastDate) values(?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,psId.getText());
            pst.setString(2,oid.getText());
              pst.setString(3,pid.getText());
              pst.setString(4,p_date.getText());
              pst.setString(5,tcompleted.getText());
              pst.setString(6,tcompleted.getText());
              pst.setString(7,totalCompleted.getText());
              pst.setString(8, uncompleted.getText());
               pst.setString(9, l_date.getText());
             
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
    private void update(ActionEvent event) {
          try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=psId.getText();
            String v2=oid.getText();
             String v3=pid.getText();
              String v4=torder.getText();
               String v5=tcompleted.getText();
               String v6=p_date.getText();
               String v7=totalCompleted.getText();
               String v8= uncompleted.getText();
               String v9= l_date.getText();
              
               String sql="update ProductionStatus set ProductionStatus_ID='"+v1+"', OrderId='"+v2+"',ProductId='"+v3+"',"
                       + "Production_Date='"+v6+"',TotalOrder='"+v4+"',TodayCompeted='"+v5+"',"
                       + "TotalCompleted='"+v7+"',uncompleted='"+v8+"',LastDate='"+v9+"' where  ProductionStatus_ID='"+v1+"' ";
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
    private void MouseClick(MouseEvent event) {
        index=  productionStatus.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
        }
       psId.setText(col_psid.getCellData(index).toString());
         oid.setText(col_oid.getCellData(index).toString());
          pid.setText(col_pid.getCellData(index).toString());
           torder.setText(col_torder.getCellData(index).toString());
            tcompleted.setText(col_todaycom.getCellData(index).toString());
             p_date.setText(col_pdate.getCellData(index).toString());
              totalCompleted.setText(col_totalcom.getCellData(index).toString());
               uncompleted.setText(col_uncom.getCellData(index).toString());
                l_date.setText(col_lDate.getCellData(index));
    }
    public void fetch()
    {
     list.clear();
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           
             ResultSet rs= con.createStatement().executeQuery("select *from ProductionStatus");
             while(rs.next()){                                   
                list.add(new productionStatus( rs.getInt("ProductionStatus_ID"),rs.getInt("OrderId"),rs.getInt("ProductId"),rs.getString("Production_Date"),
                        rs.getInt("TotalOrder"),rs.getInt("TodayCompeted"),rs.getInt("TotalCompleted"),
                        rs.getInt("uncompleted"),rs.getString("LastDate") ));
                      
             }                       
            
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
      col_psid.setCellValueFactory(new PropertyValueFactory<>("psid"));
        col_oid.setCellValueFactory(new PropertyValueFactory<>("oid"));
          
         col_pid.setCellValueFactory(new PropertyValueFactory<>("pid"));
         col_torder.setCellValueFactory(new PropertyValueFactory<>("torder"));
     col_todaycom.setCellValueFactory(new PropertyValueFactory<>("todaycom"));
  col_pdate.setCellValueFactory(new PropertyValueFactory<>("pdate"));
     col_totalcom.setCellValueFactory(new PropertyValueFactory<>("totalcom"));
  col_uncom.setCellValueFactory(new PropertyValueFactory<>("uncom"));
 col_lDate.setCellValueFactory(new PropertyValueFactory<>("ldate"));
   productionStatus.setItems(list);
             psId.clear();
             oid.clear();
             pid.clear();
              torder.clear();
             tcompleted.clear();            
             p_date.clear();  
              totalCompleted.clear();  
              uncompleted.clear();  
              l_date.clear();  
    }
    
    void search()
    {
         
        FilteredList<productionStatus> filteredData = new FilteredList<>(list, b -> true);
		
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(productionStatus -> {
			
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (productionStatus.getLdate().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
                                }
                                else if (productionStatus.getPdate().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
                                }
				else if (String.valueOf(productionStatus. getPsid()).indexOf(lowerCaseFilter)!=-1)
                                {
                                    return true;
                                }
                                    else if (String.valueOf(productionStatus.getOid()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                    else if (String.valueOf(productionStatus.getPid()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                    else if (String.valueOf(productionStatus.getTodaycom()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                    else if (String.valueOf(productionStatus.getTorder()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                    else if (String.valueOf(productionStatus.getTotalcom()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                    else if (String.valueOf(productionStatus.getUncom()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }	
                                    
                                    
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<productionStatus> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(productionStatus.comparatorProperty());
		
		
		productionStatus.setItems(sortedData);
    }
    
      void delete()
    {
           try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=psId.getText();
           
               String sql="delete from ProductionStatus  where ProductionStatus_ID=? ";
                       
               PreparedStatement pst=con.prepareStatement(sql);
               pst.setString(1, v1);
          
               pst.execute();
               
             
               
               
             
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
    }

  


}
    

