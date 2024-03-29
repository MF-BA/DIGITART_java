/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auction_Services;
import Services.Bid_Services;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Display_auctionsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

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
    private Button btn_Add_Auction;
    @FXML
    private Button btn_Artworks_Auction;
    @FXML
    private TextField search_in;
    @FXML
    private Button auction_btn;
    @FXML
    private FontAwesomeIconView clear;
    @FXML
    private Button display_archive;
    @FXML
    private Button disconnect;
    @FXML
    private Button editprof_btn;
    @FXML
    private Button home_btn;
    @FXML
    private Button artwork_btn;
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
    private AnchorPane homepage_anchorpane;
    @FXML
    private Button auction_btn1;

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
        btn_Artworks_Auction.setStyle("-fx-background-color:  #bd2a2e");
        auction_btn.setStyle("-fx-background-color: #bd2a2e");
        Showauction();

        Dslay_delete_button.setOnAction(this::AuctonDelete);
    }

    private ArrayList<Auction_display> AuctionList;

    public void Showauction() {

        AuctionList = Auction_Services.Display_auction_details(Auction_Services.Display_back_artist(Data.user.getId()));

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
    private Auction_display select_auction(MouseEvent event) {
        Auction_display t = ((TableView<Auction_display>) table_auction).getSelectionModel().getSelectedItem();
        int num = table_auction.getSelectionModel().getSelectedIndex();
        if (num == -1) {
            return null;
        }
        Data.auction_display = t;
        Data.back_auction = false;
        if (Bid_Services.Display(t.getId_auction()).isEmpty()) {
            Dslay_update_button.setDisable(false);
            Dslay_delete_button.setDisable(false);
        } else {
            Dslay_update_button.setDisable(true);
            Dslay_delete_button.setDisable(true);
        }
        return t;
    }

    @FXML
    private void AuctonDelete(ActionEvent event) {
        MouseEvent mouse = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, false, false, false, false, true, false, false, true, false, false, null);
        Auction_display t = select_auction(mouse);

        Alert alert;
        if (t == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR !!!!");
            alert.setContentText("SELECT AN AUCTION TO BE ABLE TO DELETE IT!!!");
            alert.showAndWait();

        } else {
            int x = t.getId_auction();
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the auction?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                Auction_Services.delete(x);
                alert.setTitle("SUCCESS!!!!");
                alert.setContentText("DELETION ACCOMPLISHED SUCCESSFULLY!!!");
                alert.showAndWait();
            }
            alert = new Alert(Alert.AlertType.INFORMATION);

            Showauction();

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
    private void Artworks__Auction_cicked(ActionEvent event) {
    }

    @FXML
    private void Auctonupdate(ActionEvent event) {
        MouseEvent mouse = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, false, false, false, false, true, false, false, true, false, false, null);
        Auction_display t = select_auction(mouse);

        Alert alert;
        if (t == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR !!!!");
            alert.setContentText("SELECT AN AUCTION TO BE ABLE TO MODIFY IT!!!");
            alert.showAndWait();

        } else {
            go_modify_auction(event);
        }
    }

    private void search_btn_clicked() {
        if (search_in.textProperty().getValue().isEmpty()) {
            Showauction();
        } else {
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
                ((TableView<Auction_display>) table_auction).setItems(FXCollections.observableArrayList(searchAuctions(AuctionList, search_in.textProperty().getValue())));
            }
        }

    }

    private ArrayList<Auction_display> searchAuctions(ArrayList<Auction_display> auctions, String searchStr) {
        ArrayList<Auction_display> results = new ArrayList<>();

        // Convert search string to lowercase for case-insensitive search
        searchStr = searchStr.toLowerCase();

        // Loop through each auction and check if it contains the search string
        for (Auction_display auction : auctions) {
            if (auction.getName().toLowerCase().contains(searchStr)
                    || auction.getDesc().toLowerCase().contains(searchStr)
                    || auction.getName_artist().toLowerCase().contains(searchStr)) {
                results.add(auction);
            }
        }

        return results;
    }

    @FXML
    private void clear_search(MouseEvent event) {
        search_in.setText("");
        Showauction();
    }

    @FXML
    private void btn_Add_Auction_click(ActionEvent event) {
        ArrayList<String> arr = Auction_Services.find_artworks(Data.user.getId());

        if (arr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING !!!!");
            alert.setContentText("You don't have any artwork to add!!!");
            alert.showAndWait();
        } else {
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

    @FXML
    private void auction_btn_clicked(ActionEvent event) {
        go_auction(event);
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
                newWindow.setTitle(t.getName() + " Bids");
                newWindow.setScene(new Scene(root));

                // Set the new window's owner and modality
                newWindow.initOwner((Stage) Dslay_update_button.getScene().getWindow());
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Show the new window
                newWindow.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void search_in_clck(KeyEvent event) {
        search_btn_clicked();
    }

    @FXML
    private void display_archive_click(ActionEvent event) {
        //ArchiveController

        try {
            // Load the FXML file

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Archive.fxml"));
            root = loader.load();

            // Create a new stage and set the FXML file as its scene
            Stage newWindow = new Stage();
            newWindow.setTitle(" Archive");
            newWindow.setScene(new Scene(root));

            // Set the new window's owner and modality
            newWindow.initOwner((Stage) Dslay_update_button.getScene().getWindow());
            newWindow.initModality(Modality.WINDOW_MODAL);

            // Show the new window
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
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
