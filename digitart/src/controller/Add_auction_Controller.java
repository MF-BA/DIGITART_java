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
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane main_anchor;
    @FXML
    private Label labeladminname;
    @FXML
    private Label labeladminname1;
    @FXML
    private Label labeladminname2;
    @FXML
    private Button auction_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        in_ED.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });

        btn_Add_Auction.setStyle("-fx-background-color:  #470011 ");
        btn_Artworks_Auction.setStyle("-fx-background-color:transparent");
        auction_btn.setStyle("-fx-background-color:transparent ");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        valueFactory.setValue(10);
        in_BI.setValueFactory(valueFactory);

        /*ArrayList<String> arr = Auction_Services.find_artworks(Data.user.getId());
        
        if (arr.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("WARNING !!!!");
            alert.setContentText("You don't have any artwork to add!!!");
            alert.showAndWait();
            go_Home();
        } else {
            ObservableList<String> myObservableList = FXCollections.observableArrayList(arr);
            in_I.setItems(myObservableList);
        }*/
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
}
