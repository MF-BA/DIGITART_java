/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auction_Services;
import entity.Auction_display;
import entity.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class ArchiveController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<?> table_auction_archive;
    @FXML
    private TableColumn<?, ?> artwork_Name;
    @FXML
    private TableColumn<?, ?> Starting_price;
    @FXML
    private TableColumn<?, ?> Ending_Date;
    @FXML
    private TableColumn<?, ?> Description;
    @FXML
    private TableColumn<?, ?> Increment;
    @FXML
    private TableColumn<Auction_display, String> Current_bid;
    ArrayList<Auction_display> AuctionList;
    @FXML
    private Button display_show_bid_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Showauction();
    }


    @FXML
    private Auction_display select_auction(MouseEvent event) {
          Auction_display t = ((TableView<Auction_display>) table_auction_archive).getSelectionModel().getSelectedItem();
        int num = table_auction_archive.getSelectionModel().getSelectedIndex();
        if (num == -1) {
            return null;
        }
        Data.auction_display = t;
        return t;
    }

    public void Showauction() {

        AuctionList = Auction_Services.Display_auction_details(Auction_Services.Display_back_archive_artist(Data.user.getId()));
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

        if (table_auction_archive != null && table_auction_archive instanceof TableView) {
            // Cast ticket_tableview to TableView<Ticket> and set its items     
            ((TableView<Auction_display>) table_auction_archive).setItems(FXCollections.observableArrayList(AuctionList));
        }
    }

    @FXML
    private void display_show_bid_btn_click(ActionEvent event) {
        MouseEvent mouse = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, false, false, false, false, true, false, false, true, false, false, null);
        Auction_display t = select_auction(mouse);

        Alert alert;
        if (t == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR !!!!");
            alert.setContentText("SELECT AN AUCTION TO BE ABLE TO DISPLAY ITS BIDS!!!");
            alert.showAndWait();

        } else {

            try {
                // Load the FXML file

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Bids_display.fxml"));
                root = loader.load();

                // Create a new stage and set the FXML file as its scene
                Stage newWindow = new Stage();
                newWindow.setTitle(t.getName()+" Bids");
                newWindow.setScene(new Scene(root));

                // Set the new window's owner and modality
                newWindow.initOwner((Stage) display_show_bid_btn.getScene().getWindow());
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Show the new window
                newWindow.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
