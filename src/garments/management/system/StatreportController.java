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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohammad Rasel
 */
public class StatreportController implements Initializable {

    @FXML
      private LineChart<String, Number> lineChart;
    @FXML
    private DatePicker fromdate;
    @FXML
    private DatePicker todate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showmeline(ActionEvent event) {
        LocalDate    fd=fromdate.getValue();
    LocalDate tdate=todate.getValue();
     lineChart.getData().clear();
        XYChart.Series<String,Number> series=new XYChart.Series<>();
        XYChart.Series<String,Number> series1=new XYChart.Series<>();
       int b=fd.getMonthValue();
       int a = 0;
       String sql;
    
    try{
           Connection con = DriverManager.getConnection(
                     "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");
           String sql1="select DATEDIFF ( month , '"+fd+"' ,'"+tdate+"') as 'dif'";
            ResultSet rs1= con.createStatement().executeQuery(sql1);
             while(rs1.next()){
                 a=rs1.getInt("dif");
                 
                 
             }
             for(int i=0;i<=a;i++)
             {
                 int m=fd.getMonthValue()+i;
                 
                  
                 int y=fd.getYear();
                 if(m>12)
                 {
                      y=fd.getYear()+1;
                      m=m-12;
                      
                 }
                 if(m>9)
                 {
                    sql="select sum(quantity) as 'total' from ordered where "
                   + "orderDate like '"+y+"-"+m+"-"+"__"+"'";
                 }
                 else
                 {
                    sql="select sum(quantity) as 'total' from ordered where "
                   + "orderDate like '"+y+"-0"+m+"-"+"__"+"'";
                 }
                 
                 
                  
            ResultSet rs= con.createStatement().executeQuery(sql);
             while(rs.next()){
                
                 //System.out.println("'"+y+"-"+m+"-"+"__"+"'");
                 System.out.println(rs.getInt("total"));
                  series.getData().add(new XYChart.Data<>(fd.plusMonths(i).toString(),rs.getInt("total")));
                 
             }
                                                                                                             
            }   
             
               sql1="select DATEDIFF ( month , '"+fd+"' ,'"+tdate+"') as 'dif'";
             rs1= con.createStatement().executeQuery(sql1);
             while(rs1.next()){
                 a=rs1.getInt("dif");
                 
                 
             }
             
             
              for(int i=0;i<=a;i++)
             {
                 int m=fd.getMonthValue()+i;
                 
                  
                 int y=fd.getYear();
                 if(m>12)
                 {
                      y=fd.getYear()+1;
                      m=m-12;
                      
                 }
                 if(m>9)
                 {
                    sql="select sum(TodayCompeted) as 'total' from ProductionStatus where "
                   + "Production_Date like '"+y+"-"+m+"-"+"__"+"'";
                 }
                 else
                 {
                    sql="select sum(TodayCompeted) as 'total' from ProductionStatus where "
                   + "Production_Date like '"+y+"-0"+m+"-"+"__"+"'";
                 }
                 
                 
                  
            ResultSet rs= con.createStatement().executeQuery(sql);
             while(rs.next()){
                
                 System.out.println("'"+y+"-"+m+"-"+"__"+"'");
                 System.out.println(rs.getInt("total"));
                  series1.getData().add(new XYChart.Data<>(fd.plusMonths(i).toString(),rs.getInt("total")));
                 
             }
                                                                                                             
            }                  
             
             
           
    }  catch (SQLException e) {
            e.printStackTrace();
        }
       
        
          /*int c=12;
        System.out.println("lol="+ (fd.getMonthValue()+1));
        System.out.println(c+1);*/
    
       
       
       
     
        
           
        series.setName("Total Order in this time period ");
        series1.setName("Total Completed Product in this time period ");
        lineChart.getData().add(series);
         lineChart.getData().add(series1);
        
       
    }

    @FXML
    private void printthereport(ActionEvent event) throws IOException {
        
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("report.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotohomepage(ActionEvent event) throws IOException {
        
            Parent employee_page_parent = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }
    
}
