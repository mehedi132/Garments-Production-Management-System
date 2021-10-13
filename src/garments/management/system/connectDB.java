/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garments.management.system;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mh200
 */
public class connectDB {
    Connection con;
     public Connection connectdb(){
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Garment_Production_Management;selectMethod=cursor", "sa", "123456");
         } catch (Exception e) {
            e.printStackTrace();
         }
         return con;
    }
    
}
