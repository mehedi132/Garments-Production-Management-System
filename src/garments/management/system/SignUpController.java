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
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField country;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private PasswordField password;
    @FXML
    private TextField age;
   /// private RadioButton gender;
    @FXML
    private ToggleGroup Gender;
    String a,g,gender;
    @FXML
    private RadioButton gender_male;
    @FXML
    private RadioButton gender_female;
    
    public String result,result1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void male_select(ActionEvent event) {
        gender="male";
    }

    @FXML
    private void female_select(ActionEvent event) {
        gender="female";
    }
    @FXML
    private void register(ActionEvent event) {
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");
           
             String q="insert into Buyer (Name,Address,Country,EmailId,PhoneNumber,Pass,Age,Gender) values(?,?,?,?,?,?,?,?)";
              
             String v1=name.getText();
           String PATTERN="\\D*";
            Pattern patt = Pattern.compile(PATTERN);
            Matcher match = patt.matcher(name.getText());
            if(!match.matches()){
                     JOptionPane.showMessageDialog(null,"");
                                 Alert a2=new Alert(Alert.AlertType.ERROR);
            a2.setContentText("invalid name");
            a2.showAndWait();

            }
            else{
                    // JOptionPane.showMessageDialog(null,"successfully inserted");
            

             String v2=email.getText();
             
             String v3=phone.getText();
             String PATTERN2="\\d*";
            Pattern patt2 = Pattern.compile(PATTERN2);
            Matcher match2 = patt2.matcher(phone.getText());
            if(!match2.matches()){
                   JOptionPane.showMessageDialog(null,"");
                                 Alert a2=new Alert(Alert.AlertType.ERROR);
            a2.setContentText("invalid phone number");
             a2.showAndWait();
            }
            else{
                  //   JOptionPane.showMessageDialog(null,"successfully inserted");
            

             String v4=age.getText();
             String PATTERN3="\\d*";
            Pattern patt3 = Pattern.compile(PATTERN3);
            Matcher match3 = patt3.matcher(age.getText());
            if(!match3.matches()){
                     Alert a2=new Alert(Alert.AlertType.ERROR);
            a2.setContentText("invalid Age");
             a2.showAndWait();
            }
            else{
                     //JOptionPane.showMessageDialog(null,"successfully inserted");
            
            PreparedStatement pst=con.prepareStatement(q);
              pst.setString(1,name.getText());
              pst.setString(2,address.getText());
              pst.setString(3,country.getText());
              pst.setString(4,email.getText());
              pst.setString(5,phone.getText());
              pst.setString(6,password.getText());
              pst.setString(7,age.getText());
              if(gender_male.isSelected()){
                  pst.setString(8,"male");
              }
              if(gender_female.isSelected()){
                  pst.setString(8,"female");
              }
              
              pst.executeUpdate();
              Alert a2=new Alert(Alert.AlertType.INFORMATION);
            a2.setContentText("Success! Registration Completed");
             a2.showAndWait();
            //fetch(); 
                    
                    Parent employee_page_parent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
                    //test();
                   /* BestSellingController b=new BestSellingController();
                    b.set(result);*/
                     Scene  employee_page_scene = new Scene(employee_page_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(employee_page_scene);
                    app_stage.show();
            
            System.out.println(name.getText());
           a=age.getText();
           g=gender;
           //char ch = g.charAt(0);
           if(g.charAt(0)>='3' && g.charAt(1)>'0'){
               a=">30";
           }
           else{
               a="<=30";
           }
           
          
       // System.out.println("Nusrat Khan\n");
        double countYes=0,ageYes=0,genderYes=0;
        double countNo=0,ageNo=0,genderNo=0;
        double countTotal=0;
        String x,y;
        x=a;
        y=g;
        double pyes,pno,pAgeYes,pGenderYes,pAgeNo,pGenderNo,pXYes,pXNo,finalYes,finalNo;
        
        String[][] yesData= new String [10][3];
        String[][] noData= new String [10][3];
        String[][] tshirt=
        {{"age",     "gender",    "label"},
         {"<=30",     "male",      "yes"},
         {">30",      "female",    "yes"},
         {">30",      "male",       "no"},
         {"<=30",     "male",       "no"},
         {"<=30",     "female",     "yes"},
         {">30",      "male",       "no"},
         {">30",      "male",       "yes"},
         {"<=30",     "female",     "yes"},
         {"<30",      "female",      "no"},
         {">30",      "male",        "no"},
         {"<=30",     "male",       "yes"},
        };
        int rows=12,col=3;
        for(int i=0,k=0,m=0;i<rows;i++)
        {
            for(int j=0;j<col;j++)
            {
                if(tshirt[i][j].equals("yes"))
                {
                    countYes++;
                    k++;
                
                        for(int l=0,n=2;l<3;l++,n--)
                        {  
                            yesData[k][l]= tshirt[i][j-n];
                        }
                }
                else if(tshirt[i][j].equals("no"))
                {
                    countNo++;
                    m++;
                    
                        for(int l=0,n=2;l<3;l++,n--)
                        {  
                            noData[m][l]= tshirt[i][j-n];
                        }
                }
                System.out.print(tshirt[i][j]+"\t\t");
            }
            countTotal++;
            System.out.println("");
           
        }
        countTotal=countTotal-1;
        pyes=countYes/countTotal;
        pno=countNo/countTotal;
        System.out.println("P(YES) = "+countYes +"/" + countTotal+" = " +pyes);
        System.out.println("P(NO) = "+countNo +"/" + countTotal+" = " +pno);
        
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(yesData[i][j] + "\t\t");
            }
             System.out.println("");
        }
         System.out.println("");
         System.out.println("");
         System.out.println("");
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(noData[i][j] + "\t\t");
            }
             System.out.println("");
        }
        
        for(int i=1;i<=countYes;i++)
        {
             for(int j=0;j<3;j++)
             {
               if(yesData[i][j].equals(x))
               {
                   ageYes++;
               }
             }
        }
         for(int i=1;i<=countYes;i++)
        {
             for(int j=0;j<3;j++)
             {
               if(yesData[i][j].equals(y))
               {
                   genderYes++;
               }
             }
        }
         for(int i=1;i<=countNo;i++)
        {
             for(int j=0;j<3;j++)
             {
               if(noData[i][j].equals(x))
               {
                   ageNo++;
               }
             }
        }
         for(int i=1;i<=countNo;i++)
        {
             for(int j=0;j<3;j++)
             {
               if(noData[i][j].equals(y))
               {
                   genderNo++;
               }
             }
        }
       pAgeYes=ageYes/countYes;
       pGenderYes=genderYes/countYes;
       System.out.print("\n\n");
       System.out.println("P(condition|YES) = "+ageYes +"/" + countYes+" = " +pAgeYes);
       System.out.println("P(condition|YES) = "+genderYes +"/" + countYes+" = " +pGenderYes);  
       
       pAgeNo=ageNo/countNo;
       pGenderNo=genderNo/countNo;
       System.out.print("\n\n");
       System.out.println("P(condition|NO) = "+ageNo +"/" + countNo+" = " +pAgeNo);
       System.out.println("P(condition|NO) = "+genderNo +"/" + countNo+" = " +pGenderNo); 
       
       pXYes= pAgeYes*pGenderYes;
       pXNo= pAgeNo*pGenderNo;
       System.out.print("\n\n");
       System.out.println("P(X|YES) = "+pAgeYes +"*" + pGenderYes+" = " +pXYes);
       System.out.println("P(X|NO) = "+pAgeNo +"*" + pGenderNo+" = " +pXNo); 
       
       finalYes= pXYes*pyes;
       finalNo= pXNo*pno;
       System.out.print("\n\n");
       System.out.println("Final Yes = "+pXYes +"*" + pyes+" = " +finalYes);
       System.out.println("Final No = "+pXNo +"*" + pno+" = " +finalNo); 
       
       if(finalYes>finalNo)
       {
           System.out.print("\n\n");
           System.out.println("YES");
           result="YES";       
       }
       else if(finalYes<finalNo)
       {
           System.out.print("\n\n");
           System.out.println("NO");
           result="NO"; 
       }
       else if(finalYes==finalNo)
       {
           System.out.print("\n\n");
           System.out.println("YES");
           result="YES"; 
       }
       System.out.println(result);
       
       String q1="insert into condition (result) values (?)";
               // System.out.println(q1);
            
            PreparedStatement pst1=con.prepareStatement(q1);
              pst1.setString(1,result);
                System.out.println(result);
             // pst1.setString(2,"Working");
              pst1.executeUpdate();
             //JOptionPane.showMessageDialog(null,"good");     
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            
        // System.out.println("Nusrat Khan\n");
        double countYes1=0,ageYes1=0,genderYes1=0;
        double countNo1=0,ageNo1=0,genderNo1=0;
        double countTotal1=0;
        String x1,y1;
        x1=a;
        y1=g;
        double pyes1,pno1,pAgeYes1,pGenderYes1,pAgeNo1,pGenderNo1,pXYes1,pXNo1,finalYes1,finalNo1;
        
        String[][] yesData1= new String [10][3];
        String[][] noData1= new String [10][3];
        String[][] tshirt1=
        {{"age",     "gender",    "label"},
         {"<=30",     "male",      "no"},
         {">30",      "female",    "no"},
         {">30",      "male",       "yes"},
         {"<=30",     "male",       "yes"},
         {"<=30",     "female",     "no"},
         {">30",      "male",       "yes"},
         {">30",      "male",       "no"},
         {"<=30",     "female",     "no"},
         {"<30",      "female",      "yes"},
         {">30",      "male",        "yes"},
         {"<=30",     "male",       "no"},
        };
        int rows1=12,col1=3;
        for(int i=0,k=0,m=0;i<rows1;i++)
        {
            for(int j=0;j<col1;j++)
            {
                if(tshirt1[i][j].equals("yes"))
                {
                    countYes1++;
                    k++;
                
                        for(int l=0,n=2;l<3;l++,n--)
                        {  
                            yesData1[k][l]= tshirt1[i][j-n];
                        }
                }
                else if(tshirt1[i][j].equals("no"))
                {
                    countNo1++;
                    m++;
                    
                        for(int l=0,n=2;l<3;l++,n--)
                        {  
                            noData1[m][l]= tshirt1[i][j-n];
                        }
                }
                System.out.print(tshirt1[i][j]+"\t\t");
            }
            countTotal1++;
            System.out.println("");
           
        }
        countTotal1=countTotal1-1;
        pyes1=countYes1/countTotal1;
        pno1=countNo1/countTotal1;
        System.out.println("P(YES) = "+countYes1 +"/" + countTotal1+" = " +pyes1);
        System.out.println("P(NO) = "+countNo1 +"/" + countTotal1+" = " +pno1);
        
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(yesData1[i][j] + "\t\t");
            }
             System.out.println("");
        }
         System.out.println("");
         System.out.println("");
         System.out.println("");
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<3;j++)
            {
                System.out.print(noData1[i][j] + "\t\t");
            }
             System.out.println("");
        }
        
        for(int i=1;i<=countYes1;i++)
        {
             for(int j=0;j<3;j++)
             {
               if(yesData1[i][j].equals(x))
               {
                   ageYes1++;
               }
             }
        }
         for(int i=1;i<=countYes1;i++)
        {
             for(int j=0;j<3;j++)
             {
               if(yesData1[i][j].equals(y))
               {
                   genderYes1++;
               }
             }
        }
         for(int i=1;i<=countNo1;i++)
        {
             for(int j=0;j<3;j++)
             {
               if(noData1[i][j].equals(x))
               {
                   ageNo1++;
               }
             }
        }
         for(int i=1;i<=countNo1;i++)
        {
             for(int j=0;j<3;j++)
             {
               if(noData1[i][j].equals(y))
               {
                   genderNo1++;
               }
             }
        }
       pAgeYes1=ageYes1/countYes1;
       pGenderYes1=genderYes1/countYes1;
       System.out.print("\n\n");
       System.out.println("P(condition|YES) = "+ageYes1 +"/" + countYes1+" = " +pAgeYes1);
       System.out.println("P(condition|YES) = "+genderYes1 +"/" + countYes1+" = " +pGenderYes1);  
       
       pAgeNo1=ageNo1/countNo1;
       pGenderNo1=genderNo1/countNo1;
       System.out.print("\n\n");
       System.out.println("P(condition|NO) = "+ageNo1 +"/" + countNo1+" = " +pAgeNo1);
       System.out.println("P(condition|NO) = "+genderNo1 +"/" + countNo1+" = " +pGenderNo1); 
       
       pXYes1= pAgeYes1*pGenderYes1;
       pXNo1= pAgeNo1*pGenderNo1;
       System.out.print("\n\n");
       System.out.println("P(X|YES) = "+pAgeYes1 +"*" + pGenderYes1+" = " +pXYes1);
       System.out.println("P(X|NO) = "+pAgeNo1 +"*" + pGenderNo1+" = " +pXNo1); 
       
       finalYes1= pXYes1*pyes1;
       finalNo1= pXNo1*pno1;
       System.out.print("\n\n");
       System.out.println("Final Yes = "+pXYes1 +"*" + pyes1+" = " +finalYes1);
       System.out.println("Final No = "+pXNo1 +"*" + pno1+" = " +finalNo1); 
       
       if(finalYes1>finalNo1)
       {
           System.out.print("\n\n");
           System.out.println("YES");
           result1="YES";       
       }
       else if(finalYes1<finalNo1)
       {
           System.out.print("\n\n");
           System.out.println("NO");
           result1="NO"; 
       }
       else if(finalYes1==finalNo1)
       {
           System.out.print("\n\n");
           System.out.println("YES");
           result1="YES"; 
       }

       System.out.println(result1);
       String q2="update condition set step='"+result1+"' Where Id=(SELECT MAX(Id) FROM condition)";
            System.out.println(q2);
            PreparedStatement pst2=con.prepareStatement(q2);
             // pst2.setString(1,result1);
//              pst2.setString(1,"result1");
              pst2.executeUpdate();
            // JOptionPane.showMessageDialog(null,"good");
            }
            }
            }
            
           
    } catch (Exception e) {
                e.printStackTrace();
        }
         
                   
            
            
            
    }

       /* public String test() { 
           // result="zdfcdsz";
           System.out.println("dzgbvdxs");
           System.out.println(result);
            return this.result;
       }*/   
    
}
