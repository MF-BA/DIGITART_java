/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AuctionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane main_anchor;
        
    @FXML
    private Button Close;

    @FXML
    private Button Minimize;

    @FXML
    private AnchorPane addticket_anchor;

    @FXML
    private Button btn_addticket;

    @FXML
    private AnchorPane dashboard_anchor;

    @FXML
    private Label dashboard_availabletickets;

    @FXML
    private LineChart<?, ?> dashboard_chart;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Label dashboard_todayincome;

    @FXML
    private Label dashboard_totalincome;

    @FXML
    private Button ticket_add_button;

    @FXML
    private DatePicker ticket_date;

    @FXML
    private Button ticket_delete_button;

    @FXML
    private TextArea ticket_desc;

    @FXML
    private TextField ticket_id;

    @FXML
    private TextField ticket_price;

    @FXML
    private Button ticket_reset_button;

    @FXML
    private TextField ticket_search;

    @FXML
    private TextField ticket_stock;

    @FXML
    private TableView<?> ticket_tableview;

    @FXML
    private TableColumn<?, ?> ticket_tv_date;

    @FXML
    private TableColumn<?, ?> ticket_tv_description;

    @FXML
    private TableColumn<?, ?> ticket_tv_id;

    @FXML
    private TableColumn<?, ?> ticket_tv_price;

    @FXML
    private TableColumn<?, ?> ticket_tv_stock;

    @FXML
    private TableColumn<?, ?> ticket_tv_type;

    @FXML
    private ComboBox<?> ticket_type;

    @FXML
    private Button ticket_update_button;
   
    
    public void Close(){
        System.exit(0);
    }
    
     public void Minimize(){
        Stage stage = (Stage)main_anchor.getScene().getWindow();
        stage.setIconified(true);
    }
    
     public void switchForm(ActionEvent event){
    if(event.getSource() == btn_dashboard){
        dashboard_anchor.setVisible(true);
        addticket_anchor.setVisible(false);
    } else if(event.getSource() == btn_addticket){
        addticket_anchor.setVisible(true);
        dashboard_anchor.setVisible(false);
    }
}

        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
