/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class ArtworkChartController implements Initializable {

    @FXML
    private VBox chartbox;
    @FXML
    private Button BTN_return;

    
    private Stage stage;
    private Scene scene;
    private Parent root;

    
    
    
     private void go_artwork(ActionEvent event) {
        try {
             root = FXMLLoader.load(getClass().getResource("/view/display_artwork.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//  public void chart()
//  {
// for()
// {
//      double percentageAvailable[i] = (double) availableRooms / totalRooms * 100;
// }
//
//// Calculer les pourcentages
//    double percentageAvailable = (double) availableRooms / totalRooms * 100;
//    double percentageUnavailable = 100 - percentageAvailable;
//
//// Créer le PieChart
//    PieChart pieChart = new PieChart();
//    pieChart.setTitle("Rooms Availability");
//
// // Ajouter les données
//PieChart.Data availableData = new PieChart.Data("Available", percentageAvailable);
//PieChart.Data unavailableData = new PieChart.Data("Unavailable", percentageUnavailable);
//pieChart.getData().addAll(availableData, unavailableData);
//
//// Ajouter les labels avec les pourcentages sur chaque section du chart
//for (PieChart.Data data : pieChart.getData()) {
//    double percentage = data.getPieValue() ;
//    data.nameProperty().setValue(data.getName() + " " + String.format("%.1f%%", percentage));
//}
//  
//  // Ajouter le PieChart à chartbox
//chartbox.getChildren().add(pieChart);
//
//  }
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BTN_return_clicked(ActionEvent event) {
        go_artwork(event);
    }
    
}
