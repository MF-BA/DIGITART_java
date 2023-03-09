/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Room_Services;
import static Services.Room_Services.nbRooms;
import static Services.Room_Services.nbRoomsAvailable;
import entity.Data;
import entity.Room;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class Display_roomController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button BTN_add;
    @FXML
    private Button BTN_statistics;
    @FXML
    private TableView<?> table_room;
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_name;
    @FXML
    private TableColumn<?, ?> col_area;
    @FXML
    private TableColumn<?, ?> col_state;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private Button BTN_modify;
    @FXML
    private Button BTN_delete;

    private ArrayList<Room> RoomList;
    @FXML
    private TextField room_search;
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
    private Button BTN_PDF;

    public void ShowRoom() {

        RoomList = Room_Services.Display();

        col_name.setCellValueFactory(new PropertyValueFactory<>("name_room"));
        col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        col_state.setCellValueFactory(new PropertyValueFactory<>("state"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));

        if (table_room != null && table_room instanceof TableView) {
            // Cast room_tableview to TableView<Room> and set its items
            ((TableView<Room>) table_room).setItems(FXCollections.observableArrayList(RoomList));
        }
    }

    public Room SelectRoom() {

        Room t = ((TableView<Room>) table_room).getSelectionModel().getSelectedItem();
        return t;

    }

    @FXML
    private void btn_add_clicked(ActionEvent event) {

        go_ADD(event);
    }

    private void go_ADD(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/add_room.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void go_RoomChart(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/RoomChart.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void go_artwork(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/display_artwork.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void go_modify_room(ActionEvent event) {
        try {
            Alert alert;
            Room r = SelectRoom();
            if (table_room.getSelectionModel().getSelectedIndex() == -1) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
                alert.showAndWait();
            } else {
                root = FXMLLoader.load(getClass().getResource("/view/modify_room.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(Add_auction_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btn_modify_clicked(ActionEvent event) {
        Data.room = (SelectRoom());
        go_modify_room(event);
    }

    @FXML
    private void btn_delete_clicked(ActionEvent event) {
        Alert alert;
        Room r = SelectRoom();

        try {
            if (table_room.getSelectionModel().getSelectedIndex() == -1) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the room with name: " + r.getName_room()+ "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    Room_Services.delete(r.getId_room());
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    ShowRoom();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowRoom();

        btn_room.setStyle("-fx-background-color: #470011 ");
        btn_artwork.setStyle("-fx-background-color: transparent ");
    }

    @FXML
    private void btn_artwork_clicked(ActionEvent event) {

        go_artwork(event);

    }

    @FXML
    private void search_room_Keytyped(KeyEvent event) {
        RoomList = Room_Services.search(room_search.getText());

        col_name.setCellValueFactory(new PropertyValueFactory<>("name_room"));
        col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        col_state.setCellValueFactory(new PropertyValueFactory<>("state"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));

        if (table_room != null && table_room instanceof TableView) {
            // Cast room_tableview to TableView<Room> and set its items
            ((TableView<Room>) table_room).setItems(FXCollections.observableArrayList(RoomList));
        }
    }

    @FXML
    private void btn_room_clicked(ActionEvent event) {
    }

    @FXML
    private void btn_PDF_clicked(ActionEvent event) throws IOException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        float tableTopY = 700; // Set the initial y-coordinate of the table
        float rowHeight = 20; // Set the height of each row in the table
        float tableWidth = 500; // Set the width of the table
        PDType1Font font = PDType1Font.HELVETICA_BOLD;
        PDColor headerColor = new PDColor(new float[]{1f, 0f, 0f}, PDDeviceRGB.INSTANCE);
        PDColor contentColor = new PDColor(new float[]{0f, 0f, 0f}, PDDeviceRGB.INSTANCE);
        // Define the header rectangle
        float headerHeight = 60;
        float headerY = tableTopY + rowHeight; // Set the y-coordinate of the header

        // Draw the header rectangle
        contentStream.setNonStrokingColor(headerColor);
        contentStream.addRect(50, headerY, tableWidth, headerHeight);
        contentStream.fill();

        PDType1Font font1 = PDType1Font.HELVETICA_BOLD;
        // Draw the header text
        String headerText = "List of rooms";
        contentStream.setFont(font1, 18);
        float textWidth = font1.getStringWidth(headerText) / 1000 * 18;
        float textX = 50 + (tableWidth - textWidth) / 2;
        float textY = tableTopY + 20 + rowHeight / 2 - 6;
        contentStream.setNonStrokingColor(contentColor);
        contentStream.beginText();
        contentStream.newLineAtOffset(textX, textY);
        contentStream.showText(headerText);
        contentStream.endText();

        contentStream.setFont(font, 12);

        // Define the column headers
        String[] columnHeaders = {"name_room", "area", "state", "description"};
        float[] columnWidths = {100, 50, 100, 250};

        // Draw the column headers
        float x = 50;
        float y = tableTopY;

        for (int i = 0; i < columnHeaders.length; i++) {
            contentStream.setNonStrokingColor(headerColor);//
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(columnHeaders[i]);
            contentStream.endText();
            x += columnWidths[i];
        }

        // Draw the table cells
        y -= 10; // move the y-coordinate up one row
        contentStream.setNonStrokingColor(contentColor);
        for (int i = 0; i < table_room.getItems().size(); i++) {
            String[] rowData = table_room.getItems().get(i).toString().split(", ");
            x = 50;
            for (int j = 0; j < rowData.length; j++) {
                contentStream.addRect(x, y - rowHeight, columnWidths[j], rowHeight); // draw cell border
                contentStream.stroke();
                contentStream.beginText();
                contentStream.newLineAtOffset(x + 2, y - rowHeight + 2);
                contentStream.showText(rowData[j]); // use the rowData array to draw the cell content
                contentStream.endText();
                x += columnWidths[j];
            }
            y -= rowHeight;
        }

        contentStream.close();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF File");
        fileChooser.setInitialFileName("table_data.pdf");
        File file = fileChooser.showSaveDialog((Stage) table_room.getScene().getWindow()); // test
        if (file != null) {
            try {
                document.save(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        document.close();

    }

    @FXML
    private void BTN_statistics_clicked(ActionEvent event) {

        go_RoomChart(event);
    }

}
