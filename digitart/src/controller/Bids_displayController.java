/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auction_Services;
import Services.Bid_Services;
import entity.Auction_display;
import entity.Bid;
import entity.Data;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Bids_displayController implements Initializable {

    @FXML
    private TableView<?> table_view;
    @FXML
    private TableColumn<?, ?> offer_table_view;
    @FXML
    private TableColumn<?, ?> date_table_view;
    @FXML
    private TableColumn<Bid, String> name_table_view;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Showauction();
    }    
    
    private ArrayList<Bid> BidList;

    public void Showauction() {
        //System.out.println(Auction_Services.find_artist_id(Data.auction_display.getName()));

       BidList= Bid_Services.Display(Data.auction_display.getId_auction());
        System.out.println(BidList);
        offer_table_view.setCellValueFactory(new PropertyValueFactory<>("offer"));
        date_table_view.setCellValueFactory(new PropertyValueFactory<>("date"));
        name_table_view.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bid, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Bid, String> cellData) {
                int id_artist = cellData.getValue().getId_user();
                return new SimpleStringProperty(Auction_Services.find_artist_name(id_artist));
            }
        });

        if (table_view != null && table_view instanceof TableView) {
            // Cast ticket_tableview to TableView<Ticket> and set its items     
            ((TableView<Bid>) table_view).setItems(FXCollections.observableArrayList(BidList));
        }
    }

    @FXML
    private void table_view(MouseEvent event) {
    }
    
}
