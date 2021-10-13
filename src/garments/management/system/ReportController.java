/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garments.management.system;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mh200
 */
public class ReportController implements Initializable {

    @FXML
    private DatePicker fromdate;
    @FXML
    private DatePicker todate;
    @FXML
    private ComboBox<String> reportCombo;
     ObservableList<String> list1;
     Connection con;
    private DirectoryChooser dc = new DirectoryChooser();
    LocalDate    fd;
     LocalDate tdate;
     ResultSet rs;
     int a,b,c,w;

    /**
     * Initializes the controller class.
     * 
     * 
     */
     
      public ReportController() {
     
        this.list1 = FXCollections.observableArrayList("Sales Report","Employees Report","Shipment Report");
     }
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reportCombo.setItems(list1);
       connectDB cond= new connectDB();
       con=cond.connectdb();
    }    

    @FXML
    private void Select(ActionEvent event) {
        String s=  reportCombo.getSelectionModel().getSelectedItem();
        if(s.equals("Sales Report"))
        {
               fd=fromdate.getValue();
     tdate=todate.getValue();
   
            try{
              
             Document document =new Document();
             File sf= dc.showDialog(null);
          
             String s1=sf.getAbsolutePath();
             
            
            PdfWriter.getInstance(document,new FileOutputStream(s1+"/Bill_recept.pdf"));
             
             document.open();
             document.add(new Paragraph( "Date: from "+fd+" to "+tdate));
             document.add(new Paragraph( "Sales REport:.............\n\n"));
            
              PdfPTable table=new PdfPTable(8);
            PdfPCell c1=new PdfPCell(new Phrase("OrderId"));
            table.addCell(c1);
            
           c1=new PdfPCell(new Phrase("BuyerId"));
            table.addCell(c1);
            
            c1=new PdfPCell(new Phrase("ProductId"));
            table.addCell(c1);
            
             c1=new PdfPCell(new Phrase("Quantity"));
            table.addCell(c1);
            
            c1=new PdfPCell(new Phrase("OrderDate"));
            table.addCell(c1);
            c1=new PdfPCell(new Phrase("Total Amount"));
            table.addCell(c1);
            c1=new PdfPCell(new Phrase("Delivery Date"));
            table.addCell(c1);
            c1=new PdfPCell(new Phrase("Status"));
             table.addCell(c1);
             
             
            //connectdb();
            String q1 ="select *from Ordered where OrderDate between '"+fd+"' and '"+tdate+"'";
              
            
                      ResultSet rs= con.createStatement().executeQuery(q1);
                      while(rs.next()){       
                          System.out.println("lol\n");
                           table.setHeaderRows(1);
                           
                           int a= rs.getInt("OrderId");                           
                           String c= Integer.toString(a);
                           int b= rs.getInt("BuyerId");                           
                           String d= Integer.toString(b);
                           int x= rs.getInt("Product_Id");                           
                           String e= Integer.toString(x);
                           int y= rs.getInt("Quantity");                           
                           String f= Integer.toString(y);
                            String o= rs.getString("OrderDate");                                                     
                           
                           int z= rs.getInt("TotalAmount");                           
                           String g= Integer.toString(z);
                           String p= rs.getString("DeliveryDate");  
                            String w = rs.getString("OrderStatus");  
                           
                           
          table.addCell(c);
            table.addCell(d);
              table.addCell(e);
                table.addCell(f); 
                table.addCell(o); 
                table.addCell(g);  
                table.addCell(p);
                  table.addCell(w);
                      }
                      document.add(table);
                       
                       String q3 = "select sum(Quantity) as 'ths' from Ordered where Product_Id=1 and OrderDate between '"+fd+"' and '"+tdate+"'";
                      rs= con.createStatement().executeQuery(q3);
                      while(rs.next()){  
                      document.add(new Paragraph(  "\n\nTottal ordered product (Half Shirt):"+rs.getInt("ths")));
                      
                      }                                                           
                      String q4 = "select sum(Quantity) as 'ths' from Ordered where Product_Id=2 and OrderDate between '"+fd+"' and '"+tdate+"'";
                      rs= con.createStatement().executeQuery(q4);
                      while(rs.next()){  
                      document.add(new Paragraph(  "\n\nTottal ordered product (Full Shirt):"+rs.getInt("ths")));
                      
                      }    
                      String q5 = "select sum(Quantity) as 'ths' from Ordered where Product_Id=3 and OrderDate between '"+fd+"' and '"+tdate+"'";
                      rs= con.createStatement().executeQuery(q5);
                      while(rs.next()){  
                      document.add(new Paragraph(  "\n\nTottal ordered product (Polo T-Shirt):"+rs.getInt("ths")));
                      
                      }    
                      String q7 ="select sum(TodayCompeted) as 'totalC' from ProductionStatus"
                                + " where  Production_Date between '"+fd+"' and '"+tdate+"' and OrderId in (select OrderId from Ordered where OrderDate between '"+fd+"' and '"+tdate+"')";
              
            
                       rs= con.createStatement().executeQuery(q7);
                      while(rs.next()){  
                      document.add(new Paragraph(  "\n\nTotal completed Product:"+rs.getInt("totalC")));
                      } 
                      
                      
                        String q8 ="select sum(TodayCompeted) as 'totalC' from ProductionStatus"
                                + " where  ProductId=1 and Production_Date between '"+fd+"' and '"+tdate+"' and OrderId in (select OrderId from Ordered where OrderDate between '"+fd+"' and '"+tdate+"')";
              
            
                       rs= con.createStatement().executeQuery(q8);
                      while(rs.next()){  
                          
                           a= rs.getInt("totalC");
                      document.add(new Paragraph(  "\n\nTotal completed(Half Shirt):"+rs.getInt("totalC")));
                      }   
                       String q9 ="select sum(TodayCompeted) as 'totalC' from ProductionStatus"
                                + " where  ProductId=2 and Production_Date between '"+fd+"' and '"+tdate+"' and OrderId in (select OrderId from Ordered where OrderDate between '"+fd+"' and '"+tdate+"')";
              
            
                       rs= con.createStatement().executeQuery(q9);
                      while(rs.next()){  
                           b= rs.getInt("totalC");
                      document.add(new Paragraph(  "\n\nTotal completed(Full Shirt):"+rs.getInt("totalC")));
                      }   
                       String q10 ="select sum(TodayCompeted) as 'totalC' from ProductionStatus"
                                + " where  ProductId=3 and Production_Date between '"+fd+"' and '"+tdate+"' and OrderId in (select OrderId from Ordered where OrderDate between '"+fd+"' and '"+tdate+"')";
              
            
                       rs= con.createStatement().executeQuery(q10);
                      while(rs.next()){  
                          c= rs.getInt("totalC");
                      document.add(new Paragraph(  "\nTotal completed(Polo T-Shirt):\t"+rs.getInt("totalC")));
                      }   
                      
                      String q2 =
                                "select sum(TotalAmount) as 'total' from Ordered where OrderDate between '"+fd+"' and '"+tdate+"'";
              
            
                       rs= con.createStatement().executeQuery(q2);
                      while(rs.next()){  
                      document.add(new Paragraph(  "\n\nTotal ordered Amount :"+rs.getInt("total")+"Taka"));
                      }
                      
                      String q11 ="select sum(PaidAmount) as 'totalC' from Transaction_Information"
                                + " where  OrderId in  (select OrderId from Ordered where OrderDate between '"+fd+"' and '"+tdate+"')";
              
            
                       rs= con.createStatement().executeQuery(q11);
                      while(rs.next()){  
                      document.add(new Paragraph(  "\nTotal Paid Amount:\t"+rs.getInt("totalC")+" Taka"));
                      }   
                      
                       String q12 ="select sum(DueAmount) as 'totalC' from Transaction_Information"
                                + " where  OrderId in  (select OrderId from Ordered where OrderDate between '"+fd+"' and '"+tdate+"')";
              
            
                       rs= con.createStatement().executeQuery(q12);
                      while(rs.next()){  
                      document.add(new Paragraph(  "\nTotal Due Amount:\t"+rs.getInt("totalC")+" Taka"));
                      document.add(new Paragraph(  "\nTotal Cost:\t"+(a*3.50+b*4.50+c*4.75)+" Taka"));
                      }  
                      
                      
                      
                      
                      
                      
                      
                      
                      
              document.close();
             Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("Successfully Sales Report Downloaded");
            a2.showAndWait();
             
             
         }catch(Exception e){
             
             System.out.println(e);
             
             
         }
        
    }
        else if(s.equals("Employees Report")){
            
            fd=fromdate.getValue();
     tdate=todate.getValue();
     try{
              
             Document document =new Document();
             File sf= dc.showDialog(null);
          
             String s1=sf.getAbsolutePath();
             
            
            PdfWriter.getInstance(document,new FileOutputStream(s1+"/Bill_recept.pdf"));
             
             document.open();
             document.add(new Paragraph( "Date: from "+fd+" to "+tdate));
             document.add(new Paragraph( "Employees REport:.............\n\n"));
             
              PdfPTable table=new PdfPTable(5);
            PdfPCell c1=new PdfPCell(new Phrase("EmployeeId"));
            table.addCell(c1);
            
           c1=new PdfPCell(new Phrase("Name"));
            table.addCell(c1);
            
            c1=new PdfPCell(new Phrase("Age"));
            table.addCell(c1);
            
             c1=new PdfPCell(new Phrase("Designation"));
            table.addCell(c1);
            
            c1=new PdfPCell(new Phrase("Joining Date"));
            table.addCell(c1);
            
            String q1 = "select * from employee"
                                + " where  Employee_Id in  (select Employee_Id from Production where Date between '"+fd+"' and '"+tdate+"')";
              
            
                      ResultSet rs= con.createStatement().executeQuery(q1);
                      while(rs.next()){       
                          
                           table.setHeaderRows(1);
                           
                           int a= rs.getInt("Employee_Id");                           
                           String c= Integer.toString(a);
                           String b= rs.getString("Name");                           
                           
                           int x= rs.getInt("Age");                           
                           String e= Integer.toString(x);
                          String y= rs.getString("Designation");                           
                           
                            String o= rs.getString("JoiningDate");                                                     
                           
                            
                           
                           
          table.addCell(c);
            table.addCell(b);
              table.addCell(e);
                table.addCell(y); 
                table.addCell(o); 
                
                      }
                      
                      document.add(table);
                      document.add(new Paragraph( "\n\nEmployee Production Table\n\n"));
                      PdfPTable table1=new PdfPTable(3);
            PdfPCell c2=new PdfPCell(new Phrase("EmployeeId"));
            table1.addCell(c2);
            
           c2=new PdfPCell(new Phrase("Total Completed"));
            table1.addCell(c2);
            c2=new PdfPCell(new Phrase("Total Salary"));
            table1.addCell(c2);
            
            
            
           
                            String q2 =  " select Employee_Id, sum(CompletedProduct) as 'com', sum(TodaySalary) as 'coms' from Production group by Employee_Id having Employee_Id in  (select Employee_Id from Production where Date between '"+fd+"' and '"+tdate+"')";
                            System.out.println(q2);
                             rs= con.createStatement().executeQuery(q2);
                      while(rs.next()){
                          table1.setHeaderRows(1);
                          int a= rs.getInt("Employee_Id");                           
                           String c= Integer.toString(a);
                           int b= rs.getInt("com");                           
                           String d= Integer.toString(b);
                           int q= rs.getInt("coms");                           
                           String p= Integer.toString(q);
                           table1.addCell(c);
            table1.addCell(d);
             table1.addCell(p);
                          
                      
                      }
            
            document.add(table1);
            
                       
             
             
             document.close();
             Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("Successfully Employees Report Downloaded");
            a2.showAndWait();
             
             
     }catch(Exception e){
             
             System.out.println(e);
             
             
         }
        
    
            
        }
        
        else if(s.equals("Shipment Report")){
            fd=fromdate.getValue();
     tdate=todate.getValue();
     try{
              
             Document document =new Document();
             File sf= dc.showDialog(null);
          
             String s1=sf.getAbsolutePath();
             
            
            PdfWriter.getInstance(document,new FileOutputStream(s1+"/Bill_recept.pdf"));
             
             document.open();
             document.add(new Paragraph( "Date: from "+fd+" to "+tdate));
             document.add(new Paragraph( "Shipment Report:.............\n\n"));
              PdfPTable table=new PdfPTable(8);
            PdfPCell c1=new PdfPCell(new Phrase("OrderId"));
            table.addCell(c1);
             c1=new PdfPCell(new Phrase("ProductionStatus_ID"));
            table.addCell(c1);
             c1=new PdfPCell(new Phrase("DeliveryAddress"));
            table.addCell(c1);
             c1=new PdfPCell(new Phrase("Shipment_Date"));
            table.addCell(c1);
             c1=new PdfPCell(new Phrase("ShipmentChannel"));
            table.addCell(c1);
           c1=new PdfPCell(new Phrase("ShipmentCost"));
            table.addCell(c1);
            c1=new PdfPCell(new Phrase("DeliveryStatus"));
            table.addCell(c1);
             c1=new PdfPCell(new Phrase("DeliveryDate"));
            table.addCell(c1);
            String q2 = "select *from ShipmentInfo where  Shipment_Date between '"+fd+"' and '"+tdate+"'";
                            System.out.println(q2);
                             rs= con.createStatement().executeQuery(q2);
                             
                      while(rs.next()){
                          table.setHeaderRows(1);
                          int a= rs.getInt("OrderId");                           
                           String c= Integer.toString(a);
                           
                           int p= rs.getInt("ProductionStatus_ID");                           
                           String x= Integer.toString(p);
                           int s2= rs.getInt("ShipmentCost");                           
                           String c2= Integer.toString(s2);
                                                                                                                              
                           String d= rs.getString("DeliveryAddress");
                           String d1= rs.getString("Shipment_Date");
                           String d2= rs.getString("ShipmentChannel");
                           String d3= rs.getString("DeliveryStatus");
                            String d4= rs.getString("DeliveryDate");
             table.addCell(c);
            table.addCell(x);
             table.addCell(d);
             table.addCell(d1);
             table.addCell(d2);
             table.addCell(c2);
             table.addCell(d3);
             table.addCell(d4);
                    
                 }
            
            document.add(table);
             document.close();
             Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("Successfully Shipment Report Downloaded");
            a2.showAndWait();
            
            
            
             
             
             
             
     }catch(Exception e){
             
             System.out.println(e);
             
             
         }
            
        }
    }

    @FXML
    private void homepage(ActionEvent event)  throws IOException {
    }
 
        
    }
    

