/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import Services.Artwork_Services;
import static Services.Artwork_Services.find_artwork;
import entity.Artwork;
import entity.Artwork_display;
import entity.Auction_display;
import entity.Data;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Display_artworkController implements Initializable {

    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_name;
    private TableColumn<?, ?> col_idartist;
    @FXML
    private TableColumn<?, ?> col_nameartist;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private Button BTN_add;
    @FXML
    private Button BTN_statistics;
    @FXML
    private Button BTN_modify;
    @FXML
    private Button BTN_delete;

    private ArrayList<Artwork> ArtworkList;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Image image1;
    @FXML
    private TableColumn<Artwork, String> col_idroom;
    @FXML
    private ImageView image;
    @FXML
    private TableView<?> table_Artwork;
    @FXML
    private TextField artwork_search;
    @FXML
    private Button btn_room;
    @FXML
    private Button btn_artwork;
    @FXML
    private Label labeladminname;
    @FXML
    private Label labeladminname1;
    @FXML
    private Label labeladminname2;
    @FXML
    private Pane avatar_icon;
    @FXML
    private Circle circle_image;
    @FXML
    private ImageView avatar_image;
    @FXML
    private Label labeladminname3;
    @FXML
    private Button return_dash_btn;
    @FXML
    private Button deconnect1;

    /**
     * Initializes the controller class.
     */

    private void go_room(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/display_room.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      private void go_signin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/signin_page.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void go_home(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/dashboard_homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void go_chart(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/ArtworkChart.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void go_add_artwork(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/add_artwork.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void go_modify_artwork(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/modify_artwork.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ShowArtwork() {

        ArtworkList = Artwork_Services.Display();

        col_name.setCellValueFactory(new PropertyValueFactory<>("artwork_name"));
        col_nameartist.setCellValueFactory(new PropertyValueFactory<>("artist_name"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_art"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_idroom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Artwork, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Artwork, String> cellData) {
                int id = cellData.getValue().getId_room();
                return new SimpleStringProperty(Artwork_Services.find_nameroom(id));
            }

        });

        if (table_Artwork != null && table_Artwork instanceof TableView) {
            // Cast room_tableview to TableView<Room> and set its items
            ((TableView<Artwork>) table_Artwork).setItems(FXCollections.observableArrayList(ArtworkList));
        }
    }

    @FXML
    public void showimage() throws MalformedURLException, IOException {
        selectedArtwork = ((TableView<Artwork>) table_Artwork).getSelectionModel().getSelectedItem();
        int num = table_Artwork.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }
        
          String url = selectedArtwork.getImage_art();
          if(selectedArtwork.getImage_art().isEmpty())
              url="pasdimage.jpg";
        Image image1 = new Image("http://127.0.0.1:8000/uploads/"+url, 367, 314, false, true);
        image.setImage(image1);

    }

    private Artwork selectedArtwork;

    public Artwork SelectArtwork() {
        selectedArtwork = ((TableView<Artwork>) table_Artwork).getSelectionModel().getSelectedItem();

        return selectedArtwork;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           return_dash_btn.setVisible(true);
        if (!Data.user.getRole().equals("Admin")) {
            return_dash_btn.setVisible(false);
        }
        labeladminname3.setText(Data.user.getFirstname());

        /*if (Data.user.getImage()!=null){
            String imagePath = Data.user.getImage();
        Image image = new Image(new File(imagePath).toURI().toString());
        circle_image.setFill(new ImagePattern(image));
        }*/
        if (Data.user.getImage() != null) {
            Image image = new Image(Data.user.getImage());
            circle_image.setFill(new ImagePattern(image));
        }
        
        ShowArtwork();

        btn_room.setStyle("-fx-background-color: transparent ");
        btn_artwork.setStyle("-fx-background-color: #470011 ");

    }

    @FXML
    private void btn_add_clicked(ActionEvent event) {

        go_add_artwork(event);
    }

    @FXML
    private void btn_modify_clicked(ActionEvent event) {
        Alert alert;
        Artwork a = SelectArtwork();
        if (table_Artwork.getSelectionModel().getSelectedIndex() == -1) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the item first");
            alert.showAndWait();
        } else {

            Data.artwork=(SelectArtwork());
            go_modify_artwork(event);
        }

    }

    @FXML
    private void btn_delete_clicked(ActionEvent event) {

        Alert alert;
        Artwork a = SelectArtwork();

        try {
            if (table_Artwork.getSelectionModel().getSelectedIndex() == -1) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the artwork with the name: " + a.getArtwork_name() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    Artwork_Services.delete(a.getId_art());
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    ShowArtwork();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btn_artwork_clicked(ActionEvent event) {
    }

    @FXML
    private void btn_room_clicked(ActionEvent event) {
        go_room(event);
    }

    @FXML
    private void serach_keytyped(KeyEvent event) {
        ArtworkList = Artwork_Services.search(artwork_search.getText());

        
        col_name.setCellValueFactory(new PropertyValueFactory<>("artwork_name"));
        
        col_nameartist.setCellValueFactory(new PropertyValueFactory<>("artist_name"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_art"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_idroom.setCellValueFactory(new PropertyValueFactory<>("id_room"));

        if (table_Artwork != null && table_Artwork instanceof TableView) {
            // Cast room_tableview to TableView<Room> and set its items
            ((TableView<Artwork>) table_Artwork).setItems(FXCollections.observableArrayList(ArtworkList));
        }

    }

//    private void btn_PDF_clicked(ActionEvent event) throws IOException {
//        
//       PDDocument document = new PDDocument();
//        PDPage page = new PDPage();
//        document.addPage(page);
//        PDPageContentStream contentStream = new PDPageContentStream(document, page);
//        float tableTopY = 700; // Set the initial y-coordinate of the table
//        float rowHeight = 20; // Set the height of each row in the table
//        float tableWidth = 500; // Set the width of the table
//        PDType1Font font = PDType1Font.HELVETICA_BOLD;
//        contentStream.setFont(font, 12);
//
//        // Define the column headers
//        String[] columnHeaders = {"Artwork_name", "Artist_name", "Date", "Description", "Room"};
//        float[] columnWidths = {100, 100, 100,200,50};
//
//        // Draw the column headers
//        float x = 50;
//        float y = tableTopY;
//
//       
//        for (int i = 0; i < columnHeaders.length; i++) {
//           
//            contentStream.beginText();
//            contentStream.newLineAtOffset(x, y);
//            contentStream.showText(columnHeaders[i]);
//            contentStream.endText();
//            x += columnWidths[i];
//        }
//
//        // Draw the table cells
//        y -= 10; // move the y-coordinate up one row
//      for (int i = 0; i < table_Artwork.getItems().size(); i++) {
//    String[] rowData = table_Artwork.getItems().get(i).toString().split(", ");
//    System.out.println(Arrays.toString(rowData));
//    x = 50;
//    for (int j = 0; j < rowData.length; j++) {
//        contentStream.addRect(x, y - rowHeight, columnWidths[j], rowHeight); // draw cell border
//        contentStream.stroke();
//        contentStream.beginText();
//        contentStream.newLineAtOffset(x + 2, y - rowHeight + 2);
//        contentStream.showText(rowData[j]); // use the rowData array to draw the cell content
//        contentStream.endText();
//        x += columnWidths[j];
//    }
//    y -= rowHeight;
//}
//
//
//        contentStream.close();
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save PDF File");
//        fileChooser.setInitialFileName("table_data.pdf");
//        File file = fileChooser.showSaveDialog((Stage) table_Artwork.getScene().getWindow()); // test
//        if (file != null) {
//            try {
//                document.save(file);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        document.close();
//
//
//    }
//    

    @FXML
    private void BTN_statistics_clicked(ActionEvent event) {
        
        go_chart(event);
        
    }

    @FXML
    private void return_dash_btn(ActionEvent event) {
        go_home(event);
    }

    @FXML
    private void deconnect_btn(ActionEvent event) {
        go_signin(event);
    }

}
