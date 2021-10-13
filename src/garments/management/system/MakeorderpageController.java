/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garments.management.system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;


public class MakeorderpageController implements Initializable {
    
    ObservableList list=FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<?> itemchoicebox;

    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadData();
    }    

    @FXML
   
    private void loadData(){
        list.removeAll(list);
        String a=("Half Shirt");
        String b=("Full Shirt");
        String c=("Polo T shirt");
        list.addAll(a,b,c);
        itemchoicebox.getItems().addAll(list);
    }
    
}
