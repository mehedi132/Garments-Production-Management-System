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
import javafx.scene.control.DatePicker;
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
public class EmployeepageController implements Initializable {

    @FXML
    private Button backtohome;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField designation;
    @FXML
    private Button updateButton;
    @FXML
    private Button insertButton;
    @FXML
    private TextField age;
    @FXML
    private TextField date;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Employee> Employee;
    @FXML
    private TableColumn<Employee, Integer> col_id;
    @FXML
    private TableColumn<Employee, String> col_name;
    @FXML
    private TableColumn<Employee, Integer> col_age;
    @FXML
    private TableColumn<Employee, String> col_deg;
    @FXML
    private TableColumn<Employee, String> col_date;
    
    ObservableList<Employee>list=FXCollections.observableArrayList();
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
    private void updateAction(ActionEvent event) {
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=id.getText();
            String v2=name.getText();
             String v3=age.getText();
              String v4=designation.getText();
               String v5=date.getText();
              
               String sql="update Employee set Employee_Id= '"+v1+"',  Name='"+v2+"',Age='"+v3+"',"
                       + "Designation='"+v4+"',JoiningDate='"+v5+"' where Employee_Id='"+v1+"' ";
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
           
             String q="insert into Employee(Employee_Id,Name,Age,Designation,JoiningDate) values(?,?,?,?,?)";
            
            PreparedStatement pst=con.prepareStatement(q);
             pst.setString(1,id.getText());
          
            pst.setString(2,name.getText());
              pst.setString(3,age.getText());
              pst.setString(4,designation.getText());
              pst.setString(5,date.getText());
             
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
        
         index=Employee.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
            return;
        }
        id.setText(col_id.getCellData(index).toString());
         name.setText(col_name.getCellData(index));
          age.setText(col_age.getCellData(index).toString());
           designation.setText(col_deg.getCellData(index));
            date.setText(col_date.getCellData(index));
            
    }
    
    
      public void fetch()
    {
     list.clear();
         try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           
             ResultSet rs= con.createStatement().executeQuery("select *from Employee");
             while(rs.next()){
                 
                     
                list.add(new Employee( rs.getInt("Employee_Id"),rs.getString("Name"),rs.getInt("Age") ,rs.getString("Designation"),rs.getString("JoiningDate") ));
                      
             }
             
            
            
      }
           catch (SQLException e) {
            e.printStackTrace();
        }
      col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
          
         col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
          col_deg.setCellValueFactory(new PropertyValueFactory<>("designation"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
     
      Employee.setItems(list);
             id.clear();
             name.clear();
             age.clear();
              designation.clear();
             date.clear(); 
             
             
    }
      
      
      void search()
    {
         
        FilteredList<Employee> filteredData = new FilteredList<>(list, b -> true);
		
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
			
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (employee.getDesignation() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                 
                                  else if (employee.getDate() .toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                  
				else if (String.valueOf(employee.getId()).indexOf(lowerCaseFilter)!=-1)
                                {
                                    return true;
                                }
                                    else if (String.valueOf(employee.getAge()).indexOf(lowerCaseFilter)!=-1)
                                    {
                                        return true;
                                    }				                                                                    
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<Employee> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(Employee.comparatorProperty());
		
		
		Employee.setItems(sortedData);
    }
      
       void delete()
    {
           try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");                       
           String v1=id.getText();
           
               String sql="delete from Employee where Employee_Id=? ";
                       
               PreparedStatement pst=con.prepareStatement(sql);
               pst.setString(1, v1);
          
               pst.execute();
               
             
               
               
             
      }
           catch (SQLException e) {
        }
    }


}
