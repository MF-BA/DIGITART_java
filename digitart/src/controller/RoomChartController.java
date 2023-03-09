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
import javafx.animation.FadeTransition;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import javafx.util.Duration;

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
    @FXML
    private Pane avatar_icon;
    @FXML
    private Circle circle_image;
    @FXML
    private ImageView avatar_image;
    @FXML
    private Label labeladminname2;
    @FXML
    private Label labeladminname3;
    @FXML
    private Button return_dash_btn;
    @FXML
    private Label labeladminname1;
    @FXML
    private Button btn_room;
    @FXML
    private Button btn_artwork;
    @FXML
    private Label labeladminname;
    @FXML
    private Button deconnect1;

        private void go_signin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/signin_page.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void go_home(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/dashboard_homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
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
      private void go_Display(ActionEvent event) {
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
    pieChart.setAnimated(true);
 // Add animation to the PieChart

pieChart.setStartAngle(0);

// Create the fade-in animation
FadeTransition ft = new FadeTransition(Duration.millis(2000), pieChart);
ft.setFromValue(0.0);
ft.setToValue(1.0);

// Add the animation to the chart
ft.play();
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
        
             return_dash_btn.setVisible(true);
        if (!entity.Data.user.getRole().equals("Admin")) {
            return_dash_btn.setVisible(false);
        }
        labeladminname3.setText(entity.Data.user.getFirstname());

        /*if (Data.user.getImage()!=null){
            String imagePath = Data.user.getImage();
        Image image = new Image(new File(imagePath).toURI().toString());
        circle_image.setFill(new ImagePattern(image));
        }*/
        if (entity.Data.user.getImage() != null) {
            Image image = new Image(entity.Data.user.getImage());
            circle_image.setFill(new ImagePattern(image));
        }
        
       btn_room.setStyle("-fx-background-color: #470011 ");
       btn_artwork.setStyle("-fx-background-color: transparent ");
        chart();
        
        
        
        // TODO
    }    

    @FXML
    private void BTN_return_clicked(ActionEvent event) {
    go_room(event);
    }

    @FXML
    private void btn_room_clicked(ActionEvent event) {
        go_Display(event);
    }

    @FXML
    private void btn_artwork_clicked(ActionEvent event) {
        go_artwork(event);
    }

    @FXML
    private void return_dash_btn(ActionEvent event) {
        go_home(event);
    }

    @FXML
    private void deconnect_btn(ActionEvent event) {
        go_signin(event);
    }
    
}
