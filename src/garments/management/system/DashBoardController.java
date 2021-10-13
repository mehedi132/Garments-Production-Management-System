package garments.management.system;

import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author User
 */
public class DashBoardController implements Initializable {

    private Label label1;

    private TextField text1;
    private Label label2;
    private Label label4;
    private Label label5;
    private Label label3;


    /**
     * Initializes the controller class.
     */

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        /*recentlyAdded = new ArrayList<>(recentlyAdded());
        try{
            for(int i=0;i<recentlyAdded.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(recentlyAdded.get(i));
             
                cardLayout.getChildren().add(cardBox);
                
            }
        }catch(IOException e){
        }*/
        
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
    private void gotouserprofilepage(ActionEvent event) throws IOException {
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    @FXML
    private void gotomycartpage(ActionEvent event) throws IOException {
         Parent employee_page_parent = FXMLLoader.load(getClass().getResource("MyCart.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }

    /*private List<Book> recentlyAdded(){
        List<Book> ls = new ArrayList<>();
        Book book = new Book();
        book.setName("Purle Full Sleeve Men T-shirt");
        book.setImageSrc("Purple_fullshirt.png");
        book.setPrice("730 taka");
        ls.add(book);
   
        book = new Book();
        book.setName("Red Men Polo T-shirt");
        book.setImageSrc("red_polo.jpg");
        book.setPrice("480 taka");
        ls.add(book);
        
        book = new Book();
        book.setName("White Half Sleeve Men shirt");
        book.setImageSrc("white_halfShirt.png");
        book.setPrice("620 taka");
        ls.add(book);
        
        return ls;
    }*/

    private void display1(ActionEvent event) {
        label1.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 80, 0.7), null,null)));
        label1.setText("Price : 750 tk");
    }

    private void display2(ActionEvent event) {
         label2.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 80, 0.7), null,null)));
         label2.setText("Price : 760 tk");
    }

    private void display3(ActionEvent event) {
        label3.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 80, 0.7), null,null)));
        label3.setText("Price : 570 tk");
    }

    private void display4(ActionEvent event) {
        label4.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 80, 0.7), null,null)));
        label4.setText("Price : 210 tk");
    }

    private void display5(ActionEvent event) {
        label5.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 80, 0.7), null,null)));
        label5.setText("Price : 700 tk");
    }

    @FXML
    private void gotoBestSellingPage(ActionEvent event) throws IOException {
        Parent employee_page_parent = FXMLLoader.load(getClass().getResource("BestSelling.fxml"));
        Scene  employee_page_scene = new Scene(employee_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(employee_page_scene);
        app_stage.show();
    }
    

    
}











