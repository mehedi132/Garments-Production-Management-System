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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MyCartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private TextArea bill;
    @FXML
    private TableView<MyCart> table;
    @FXML
    private TableColumn<MyCart, String> pname;
    @FXML
    private TableColumn<MyCart, String> size;
    @FXML
    private TableColumn<MyCart, String> color;
    @FXML
    private TableColumn<MyCart, Integer> q;
    @FXML
    private TableColumn<MyCart, Float> p;
    @FXML
    private Button P_order;
ObservableList<MyCart>list=FXCollections.observableArrayList();
    Connection con;
    @FXML
    private TextField gtotal;
    ResultSet rs,rs1;
    float y;
    private DirectoryChooser dc = new DirectoryChooser();
    String d1;
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                connectDB cond= new connectDB();
                con=cond.connectdb();
                
                String b1="select sum (Total_Price) as 'total' from cart";
                
                rs=con.createStatement().executeQuery(b1);
                
                
                while(rs.next()){
                    y=rs.getFloat("total");
                    gtotal.setText(Float.toString(y));
                }
                
                
                
                try {
                    
                    fetch();
                    System.out.println("done");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(MyCartController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(MyCartController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        
        
    }    

    @FXML
    private void gotodashboard(ActionEvent event) throws IOException {
        
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotomakeorderpage(ActionEvent event) throws IOException {
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("MakeOrder.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotomyorderspage(ActionEvent event) throws IOException {
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("MyOrders.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotouserprofilepage(ActionEvent event) throws IOException, SQLException {
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
        
        
    }
    @FXML
    private void gotoBestSellingPage(ActionEvent event) throws IOException {
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("BestSelling.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }
    String name,size1,color1;
    int a,q1,z,w;
     float u,t;
     
                 

    @FXML
    private void gotoprintingpage(ActionEvent event) throws SQLException {
          connectDB cond= new connectDB();
     con= cond.connectdb();
     String b="select *from cart";
            
         
          rs= con.createStatement().executeQuery(b);
             while(rs.next()){
                  a= rs.getInt("u_id");
                  name= rs.getString("ProductName");
                 size1= rs.getString("Size");
                  color1= rs.getString("Colour");
                   q1= rs.getInt("Quantity");
                u=rs.getFloat("Unit_Price");
               t=rs.getFloat("Total_Price");  
               
               
               
               
               String c="select Product_Id from ProductList where ProductName ='"+name+"'";
          rs1= con.createStatement().executeQuery(c);
             while(rs1.next()){
                 w = rs1.getInt("Product_Id");
                    
             }
             
             
           String c1="Insert into ordered (BuyerId,Product_Id,Product_Name,Size,Colour,Quantity,Unit_Price,TotalAmount) values(?,?,?,?,?,?,?,?)";
              PreparedStatement pst=con.prepareStatement(c1);
                       pst.setString(1,Integer.toString(a));
                       pst.setString(2,Integer.toString(w));
                       pst.setString(3,name);
                       pst.setString(4,size1);
                       pst.setString(5, color1);
                        pst.setString(6,Integer.toString(q1));
                        pst.setString(7,Float.toString(u));
                           pst.setString(8,Float.toString(t));
                       
                        pst.executeUpdate();
                       
                               
           }
              
            
             //printing
              try{
             Document document =new Document();
             
             File sf= dc.showDialog(null);
          
             String s1=sf.getAbsolutePath();
            
             
            
            PdfWriter.getInstance(document,new FileOutputStream(s1+"/User_Bill_recept.pdf"));
            String m1="select OrderDate from ordered where OrderId =(select max(OrderId) from ordered) ";
              ResultSet rs2= con.createStatement().executeQuery(m1);
             while(rs2.next()){
                 d1=rs2.getString("OrderDate");
             }
             
                 
             document.open();
             
             document.add(new Paragraph( "Date: "+d1 ));
             document.add(new Paragraph( "Order Details:.............\n\n"));
              PdfPTable table=new PdfPTable(5);
              
            PdfPCell c1=new PdfPCell(new Phrase("Product Name"));
            table.addCell(c1);
             c1=new PdfPCell(new Phrase("Size"));
            table.addCell(c1);
             c1=new PdfPCell(new Phrase("Colour"));
            table.addCell(c1);
            c1=new PdfPCell(new Phrase("Quantity"));
            table.addCell(c1);
            c1=new PdfPCell(new Phrase("Price"));
            table.addCell(c1);
            
            String q1 ="select *from cart ";
              
            
                      ResultSet rs= con.createStatement().executeQuery(q1);
                      while(rs.next()){       
                          
                           table.setHeaderRows(1);
                           String o= rs.getString("ProductName");  
                           String o1= rs.getString("Size");  
                           String o2= rs.getString("Colour");
                           int z= rs.getInt("Quantity");                           
                           String g= Integer.toString(z);
                           int z1= rs.getInt("Total_Price");                           
                           String g1= Integer.toString(z1);
                             table.addCell(o);
                               table.addCell(o1);
                                 table.addCell(o2);
                                   table.addCell(g);
                                     table.addCell(g1);
                           
                           
                      }
                        document.add(table);
            String b1="select sum (Total_Price) as 'total' from cart";
                
                rs=con.createStatement().executeQuery(b1);
                
                
                while(rs.next()){
                    y=rs.getFloat("total");
                   
                }
                 document.add(new Paragraph( "Grand Total: "+y ));
                
             
             
             document.close();
              Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("Successfully Downloaded");
            a2.showAndWait();
             
         }catch(Exception e){
             
             System.out.println(e);
             
             
         }
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             String sql="delete  from cart ";
                       
               PreparedStatement pst=con.prepareStatement(sql);
              
          
               pst.execute();
              System.out.println("done dele");
              fetch();
              
              
              
      }
        
          
        
        
    
     public void fetch() throws SQLException
    {
    
     connectDB cond= new connectDB();
     con= cond.connectdb();
     
         
             ResultSet rs2= con.createStatement().executeQuery("select *from cart");
             while(rs2.next()){
                 
                     
                list.add(new MyCart(rs2.getString("ProductName"),rs2.getString("Size"),
                        rs2.getString("Colour"),rs2.getInt("Quantity"),rs2.getFloat("Total_Price")));
                
             }
            
            
     
          
    
        pname.setCellValueFactory(new PropertyValueFactory<>("name"));
    size.setCellValueFactory(new PropertyValueFactory<>("size"));
     color.setCellValueFactory(new PropertyValueFactory<>("color"));
      q.setCellValueFactory(new PropertyValueFactory<>("quantity"));
     p.setCellValueFactory(new PropertyValueFactory<>("tp"));
     table.setItems(list) ;
         
      
             
    }

   


}
