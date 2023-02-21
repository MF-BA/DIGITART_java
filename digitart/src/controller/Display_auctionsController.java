/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auction_Services;
import Services.ServiceTicket;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Auction;
import entity.Ticket;
import entity.Auction_display;
import entity.Data;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Display_auctionsController implements Initializable {

    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Dslay_update_button;
    @FXML
    private Button Dslay_delete_button;
    @FXML
    private Button display_show_bid_btn;
    @FXML
    private TableView<Auction_display> table_auction;
    @FXML
    private TableColumn<Auction_display, String> artwork_Name;
    @FXML
    private TableColumn<Auction_display, Integer> Starting_price;
    @FXML
    private TableColumn<Auction_display, LocalDate> Ending_Date;
    @FXML
    private TableColumn<Auction_display, String> Description;
    @FXML
    private TableColumn<Auction_display, Integer> Increment;
    @FXML
    private TableColumn<Auction_display, String> Current_bid;
    @FXML
    private AnchorPane main_anchor;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btn_Add_Auction;
    @FXML
    private Button btn_Artworks_Auction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Showauction();

        Dslay_delete_button.setOnAction(this::AuctonDelete);
    }

    @FXML
    private void Close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void Minimize(ActionEvent event) {
        Stage stage = (Stage) main_anchor.getScene().getWindow();
        stage.setIconified(true);
    }

    private ArrayList<Auction_display> AuctionList;

    public void Showauction() {

        AuctionList = Auction_Services.Display_auction_details();

        artwork_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Starting_price.setCellValueFactory(new PropertyValueFactory<>("starting_price"));
        Ending_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Description.setCellValueFactory(new PropertyValueFactory<>("desc"));
        Increment.setCellValueFactory(new PropertyValueFactory<>("increment"));
        Current_bid.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Auction_display, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Auction_display, String> cellData) {
                int bid = cellData.getValue().getBid();
                if (bid == 0) {
                    return new SimpleStringProperty("None");
                } else {
                    return new SimpleStringProperty(String.valueOf(bid));
                }
            }
        });

        if (table_auction != null && table_auction instanceof TableView) {
            // Cast ticket_tableview to TableView<Ticket> and set its items     
            ((TableView<Auction_display>) table_auction).setItems(FXCollections.observableArrayList(AuctionList));
        }
    }

    @FXML
    private void AuctonDelete(ActionEvent event) {
        Auction_display t = select_auction(null);
        int x = t.getId_auction();
        Alert alert;
        if (((x - 1) < -1)) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR !!!!");
            alert.setContentText("SELECT A ROW TO BE ABLE TO DELETE!!!");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the auction?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                Auction_Services.delete(x);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESS!!!!");
                alert.setContentText("DELETION ACCOMPLISHED SUCCESSFULLY!!!");
                alert.showAndWait();
            }
            Showauction();
        }
    }

    @FXML
    private Auction_display select_auction(MouseEvent event) {
        Auction_display t = ((TableView<Auction_display>) table_auction).getSelectionModel().getSelectedItem();
        int num = table_auction.getSelectionModel().getSelectedIndex();
        if (((num - 1) < -1)) {
            return null;
        }
        Data.auction_display = t;
        return t;

    }

    @FXML
    private void btn_add_auction_clck(ActionEvent event) {
        go_ADD_auction(event);
    }

    private void go_ADD_auction(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/add_auction.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void go_modify_auction(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/modify_auction.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Artworks__Auction_cicked(ActionEvent event) {
    }

    @FXML
    private void Auctonupdate(ActionEvent event) {

        go_modify_auction(event);
    }

}
