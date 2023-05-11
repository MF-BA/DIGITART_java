/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import Services.Auction_Services;
import entity.Auction;
import entity.Data;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Add_auction_Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField in_SB;
    @FXML
    private TextArea in_Desc;
    @FXML
    private DatePicker in_ED;
    @FXML
    private Spinner<Integer> in_BI;
    @FXML
    private ComboBox<String> in_I;
    @FXML
    private Button submit_add_auction;
    @FXML
    private Button btn_Add_Auction;
    @FXML
    private Button btn_Artworks_Auction;
    private Button auction_btn;
    @FXML
    private Button disconnect;
    @FXML
    private Button editprof_btn;
    @FXML
    private Button home_btn;
    @FXML
    private Button artwork_btn;
    @FXML
    private Button auction_btn1;
    @FXML
    private Button events_btn;
    @FXML
    private Circle circle_image;
    @FXML
    private ImageView avatar_image;
    @FXML
    private Label labelusername;
    @FXML
    private Button tickets_btn;
    @FXML
    private Button auction_btn11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelusername.setText(Data.user.getFirstname());
        try {
            if (Data.user.getImage() != null) {
                Image image = new Image("http://127.0.0.1:8000/Uploads/" + Data.user.getImage());
                //Image image = new Image("http://127.0.0.1:8000/Uploads/" + Data.user.getImage(), 350, 300, false, true);
                // Set the image in the avatar_image ImageView (if you want to display it elsewhere)
                //avatar_image.setImage(image);

                // Create an ImagePattern object from the Image object
                ImagePattern pattern = new ImagePattern(image);

                // Set the ImagePattern as the fill for the circle
                circle_image.setFill(new ImagePattern(image));

                // Print out some debug information (if you want)
                System.out.println("Loaded user image: " + image);
            } else {
                Image image = new Image("http://127.0.0.1:8000/Back/images/icon-img.png", 350, 300, false, true);
                //avatar_image.setImage(image);
                circle_image.setFill(new ImagePattern(image));
            }

        } catch (Exception e) {
            // handle the exception
            System.out.println("An error occurred: " + e.getMessage());
        }
        in_ED.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate tomorrow = LocalDate.now().plusDays(1);
                setDisable(empty || date.compareTo(tomorrow) < 0);
            }
        });

        auction_btn1.setStyle("-fx-background-color: #bd2a2e");
        btn_Add_Auction.setStyle("-fx-background-color:  #bd2a2e ");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        valueFactory.setValue(10);
        in_BI.setValueFactory(valueFactory);

        ObservableList<String> myObservableList = FXCollections.observableArrayList(Auction_Services.find_artworks(Data.user.getId()));

        in_I.setItems(myObservableList);

        // force the field to be numeric only
        in_SB.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    in_SB.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        submit_add_auction.setOnAction(this::submit_add_auction_clicked);

    }

    @FXML
    private void submit_add_auction_clicked(ActionEvent event) {
        try {
            if (in_SB.textProperty().getValue().isEmpty() || in_ED.getValue() == null || in_Desc.textProperty().getValue().isEmpty() || in_I.getValue() == null) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("WARNING !!!!");
                alert.setContentText("Some Fields are empty!");
                alert.showAndWait();
            } else {
                LocalDate date = in_ED.getValue();
                /*date */
                Auction auction = new Auction(Integer.parseInt(in_SB.textProperty().getValue()),
                        in_BI.getValue(), Auction_Services.find_artwork_id(in_I.getValue()),
                        date, in_Desc.textProperty().getValue());
                Auction_Services.add(auction);
                go_Home();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR !!!!");
            alert.setContentText("Invalid input for the starting bid!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR !!!!");
            alert.setContentText("An error occurred while adding the auction.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void go_Home() {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Display_auctions.fxml"));
            //stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage = (Stage) in_SB.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Artworks__Auction_cicked(ActionEvent event) {
        go_Home();
    }

    @FXML
    private void btn_Add_Auction_click(ActionEvent event) {
    }

    @FXML
    private void auction_btn_clicked(ActionEvent event) {
        go_auction(event);
    }

    private void go_auction(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/auction_front.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void disconnect_btn(ActionEvent event) {
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/signin_page.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DIGITART");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void home_btn(ActionEvent event) {
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/Home_page.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DIGITART");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void artwork_btn(ActionEvent event) {

        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/display_artwork_user.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DIGITART");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void auction_btn(ActionEvent event) {

    }

    @FXML
    private void events_btn(ActionEvent event) {
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/participate_event.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DIGITART");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tickets_btn(ActionEvent event) {
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/add_ticket_user.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DIGITART");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editprof_btn(ActionEvent event) {
        try {
            Parent parent2 = FXMLLoader
                    .load(getClass().getResource("/view/editprofileuser_front.fxml"));

            Scene scene = new Scene(parent2);
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("DIGITART");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Signin_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
