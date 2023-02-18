/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auction_Services;
import Services.Bid_Services;
import entity.Auction;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Int1Controller implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        valueFactory.setValue(10);
        in_BI.setValueFactory(valueFactory);
        ObservableList<String> myObservableList = FXCollections.observableArrayList(Auction_Services.find_artworks(1));
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
        
        /*
        in_I.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> options, String oldValue, String newValue) {
                myObservableList.clear();
                myObservableList.addAll(Auction_Services.artwork(1));
            }
        });
         */
    }

    @FXML
    private void submit_add_auction_clicked(ActionEvent event) {

        LocalDate date = in_ED.getValue();
        /*date */
        System.out.println(in_I.getValue());

        Auction auction = new Auction(Integer.parseInt(in_SB.textProperty().getValue()),
                in_BI.getValue(), Auction_Services.find_artwork_id(in_I.getValue()),
                date, in_Desc.textProperty().getValue());
        Auction_Services.add(auction);
        

    }

    @FXML
    private void getDate(ActionEvent event) {
    }

}
