/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auction_Services;
import entity.Auction;
import entity.Data;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class Modify_auction_Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField in_SP;
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
    private AnchorPane main_anchor;
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> myObservableList = FXCollections.observableArrayList(Auction_Services.find_artworks(1));

        in_I.setItems(myObservableList);

        // force the field to be numeric only
        in_SP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    in_SP.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        in_I.setValue(Auction_Services.find_artwork_name(Data.auction_display.getId_artwork()));
        in_SP.setText(String.valueOf(Data.auction_display.getStarting_price()));
        in_Desc.setText(Data.auction_display.getDesc());

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        valueFactory.setValue(Data.auction_display.getIncrement());
        in_BI.setValueFactory(valueFactory);

        in_ED.setValue(Data.auction_display.getDate());

    }

    private void go_Home(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Display_auctions.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void submit_add_auction_clicked(ActionEvent event) {
        Alert alert;
        if (in_SP.textProperty().getValue().isEmpty() || in_ED.getValue() == null || in_Desc.textProperty().getValue().isEmpty() || in_I.getValue() == null) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING !!!!");
            alert.setContentText("Some Fields are empty!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to modify the auction?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                LocalDate date = in_ED.getValue();
                /*date */
                System.out.println(in_I.getValue());

                Auction auction = new Auction(Data.auction_display.getId_auction(), Integer.parseInt(in_SP.textProperty().getValue()),
                        in_BI.getValue(), Auction_Services.find_artwork_id(in_I.getValue()),
                        date, in_Desc.textProperty().getValue());
                Auction_Services.modify(auction);
                go_Home(event);
            }
        }
    }

    @FXML
    private void Close(ActionEvent event) {
    }

    @FXML
    private void Minimize(ActionEvent event) {
    }

}
