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
import entity.auction_display;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Display_auctionsController implements Initializable {

    @FXML
    private Button btn_add_auction;
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
    private TableView<auction_display> table_auction;
    @FXML
    private TableColumn<auction_display, String> artwork_Name;
    @FXML
    private TableColumn<auction_display, Integer> Starting_price;
    @FXML
    private TableColumn<auction_display, LocalDate> Ending_Date;
    @FXML
    private TableColumn<auction_display, String> Description;
    @FXML
    private TableColumn<auction_display, Integer> Increment;
    @FXML
    private TableColumn<auction_display, Integer> Current_bid;
    @FXML
    private AnchorPane main_anchor;
    @FXML
    private FontAwesomeIconView minimize;
    @FXML
    private Button btn_artwork_auctin;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowTicket();

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

    private ArrayList<auction_display> AuctionList;

    public void ShowTicket() {

        AuctionList = Auction_Services.Display_auction_details();

        artwork_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Starting_price.setCellValueFactory(new PropertyValueFactory<>("starting_price"));
        Ending_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Description.setCellValueFactory(new PropertyValueFactory<>("desc"));
        Increment.setCellValueFactory(new PropertyValueFactory<>("increment"));
        Current_bid.setCellValueFactory(new PropertyValueFactory<>("bid"));

        if (table_auction != null && table_auction instanceof TableView) {
            // Cast ticket_tableview to TableView<Ticket> and set its items     
            ((TableView<auction_display>) table_auction).setItems(FXCollections.observableArrayList(AuctionList));
        }
    }

    @FXML
    private void UpdateUpdate(ActionEvent event) {
    }

    @FXML
    private void AuctonDelete(ActionEvent event) {
        int t = select_auction(null);
        System.out.println(t);
        if (((t - 1) < -1)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR !!!!");
            alert.setContentText("SELECT A ROW TO BE ABLE TO DELETE!!!");
            alert.showAndWait();
        } else {
            Auction_Services.delete(t);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS!!!!");
            alert.setContentText("DELETION ACCOMPLISHED SUCCESSFULLY!!!");
            alert.showAndWait();
        }
        ShowTicket();

    }

    @FXML
    private void Showbid_click(ActionEvent event) {
    }

    @FXML
    private int select_auction(MouseEvent event) {
        auction_display t = ((TableView<auction_display>) table_auction).getSelectionModel().getSelectedItem();
        int num = table_auction.getSelectionModel().getSelectedIndex();
        if (((num - 1) < -1)) {
            return num;
        }
        return t.getId_auction();

    }

    @FXML
    private void btn_add_auction_clck(ActionEvent event) {
        go_Home(event);
    }

    @FXML
    private void btn_artwork_auctin_click(ActionEvent event) {
    }

    private void go_Home(ActionEvent event) {
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

}