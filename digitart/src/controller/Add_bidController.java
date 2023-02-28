/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Bid_Services;
import entity.Bid;
import entity.Data;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Add_bidController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label Starting_Bid;
    @FXML
    private Spinner<Integer> bid;
    @FXML
    private Button submit;
    @FXML
    private Label dollar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int staringPrice;
        int next_offer = Bid_Services.next_offer(Data.auction_display.getId_auction());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(next_offer, 100000000);
        valueFactory.setValue(next_offer);
        bid.setValueFactory(valueFactory);
        if (Bid_Services.highest_offer(Data.auction_display.getId_auction()) == 0) {
            Starting_Bid.setText("NONE");
            dollar.setVisible(false);
        } else {
            Starting_Bid.setText(String.valueOf(Bid_Services.highest_offer(Data.auction_display.getId_auction()) ));
        }
            }

    @FXML
    private void submit_clicked(ActionEvent event) {
        Bid_Services.add(new Bid(Data.user.getId(), Data.auction_display.getId_auction(), this.bid.getValue()));
        //Node source = (Node) event.getSource();
        stage = (Stage) Starting_Bid.getScene().getWindow();
        stage.close();
    }
}
