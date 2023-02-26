/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Auction_Services;
import Services.Bid_Services;
import entity.Bid;
import entity.Data;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

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
    @FXML
    private Button Export_table;

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

        BidList = Bid_Services.Display(Data.auction_display.getId_auction());
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

    @FXML
    private void Export_table_clicked(ActionEvent event) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        float tableTopY = 700; // Set the initial y-coordinate of the table
        float rowHeight = 20; // Set the height of each row in the table
        float tableWidth = 500; // Set the width of the table
        PDType1Font font = PDType1Font.HELVETICA_BOLD;
        contentStream.setFont(font, 12);

        // Define the column headers
        String[] columnHeaders = {"Offer", "date", "bidder`s name"};
        float[] columnWidths = {100, 200, 200};

        // Draw the column headers
        float x = 50;
        float y = tableTopY;
        for (int i = 0; i < columnHeaders.length; i++) {
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(columnHeaders[i]);
            contentStream.endText();
            x += columnWidths[i];
        }

        // Draw the table cells
        y -= rowHeight; // move the y-coordinate up one row
        for (int i = 0; i < table_view.getItems().size(); i++) {
            String[] rowData = table_view.getItems().get(i).toString().split(", ");
            x = 50;
            for (int j = 0; j < rowData.length; j++) {
                contentStream.addRect(x, y - rowHeight, columnWidths[j], rowHeight); // draw cell border
                contentStream.stroke();
                contentStream.beginText();
                contentStream.newLineAtOffset(x + 2, y - rowHeight + 2);
                contentStream.showText(rowData[j]);
                contentStream.endText();
                x += columnWidths[j];
            }
            y -= rowHeight;
        }

        contentStream.close();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF File");
        fileChooser.setInitialFileName("table_data.pdf");
        File file = fileChooser.showSaveDialog((Stage) Export_table.getScene().getWindow());
        if (file != null) {
            try {
                document.save(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        document.close();
    }

}
