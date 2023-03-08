/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static Services.Room_Services.nbRooms;
import static Services.Room_Services.nbRoomsAvailable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class RoomChartController implements Initializable {

    @FXML
    private VBox chartbox;
    @FXML
    private Button BTN_return;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    
    
    
     private void go_room(ActionEvent event) {
        try {
             root = FXMLLoader.load(getClass().getResource("/view/display_room.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  public void chart()
  {
  // Récupérer les données de la base de données
    int totalRooms = nbRooms();
    int availableRooms = nbRoomsAvailable();

// Calculer les pourcentages
    double percentageAvailable = (double) availableRooms / totalRooms * 100;
    double percentageUnavailable = 100 - percentageAvailable;

// Créer le PieChart
    PieChart pieChart = new PieChart();
    pieChart.setTitle("Rooms Availability");

 // Ajouter les données
PieChart.Data availableData = new PieChart.Data("Available", percentageAvailable);
PieChart.Data unavailableData = new PieChart.Data("Unavailable", percentageUnavailable);
pieChart.getData().addAll(availableData, unavailableData);

// Ajouter les labels avec les pourcentages sur chaque section du chart
for (PieChart.Data data : pieChart.getData()) {
    double percentage = data.getPieValue() ;
    data.nameProperty().setValue(data.getName() + " " + String.format("%.1f%%", percentage));
}
  
  // Ajouter le PieChart à chartbox
chartbox.getChildren().add(pieChart);

  }
  
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart();
        
        
        
        // TODO
    }    

    @FXML
    private void BTN_return_clicked(ActionEvent event) {
    go_room(event);
    }
    
}
