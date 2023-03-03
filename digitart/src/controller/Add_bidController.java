/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Bid_Services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Bid;
import entity.Data;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import org.json.JSONObject;
import java.io.*;
import okhttp3.*;
import java.util.Iterator;

/**
 * FXML Controller class
 *
 * @author fedi1
 */
public class Add_bidController implements Initializable {

    private static final String API_KEY = "fBHGSQbaHC735dTQMppwn4QhzmuSeQcp";
    private static final String BASE_URL = "http://data.fixer.io/api/latest?access_key=" + API_KEY;

    private Map<String, Double> exchangeRates = new HashMap<>();
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
    @FXML
    private ComboBox<String> currency;
    @FXML
    private Label Starting_Bid_currency;

    private int current_bid = Bid_Services.highest_offer(Data.auction_display.getId_auction());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            fetchExchangeRates();
        } catch (Exception ex) {
            Logger.getLogger(Add_bidController.class.getName()).log(Level.SEVERE, null, ex);
        }
    Starting_Bid_currency.setVisible(false);
        int staringPrice;
        int next_offer = Bid_Services.next_offer(Data.auction_display.getId_auction());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(next_offer, 100000000);
        valueFactory.setValue(next_offer);
        bid.setValueFactory(valueFactory);
        if (current_bid == 0) {
            Starting_Bid.setText("NONE");
            dollar.setVisible(false);
        } else {
            Starting_Bid.setText(String.valueOf(current_bid));
        }

    }

    @FXML
    private void submit_clicked(ActionEvent event) {
        Bid_Services.add(new Bid(Data.user.getId(), Data.auction_display.getId_auction(), this.bid.getValue()));
        //Node source = (Node) event.getSource();
        stage = (Stage) Starting_Bid.getScene().getWindow();
        stage.close();
    }

//    public ComboBox<String> getCurrencyComboBox() {
//        ComboBox<String> comboBox = new ComboBox<>();
//        comboBox.setPromptText("Select currency");
//
//        ObservableList<String> items = FXCollections.observableArrayList(exchangeRates.keySet());
//        comboBox.setItems(items);
//
//        return comboBox;
//    }
    private void fetchExchangeRates() throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/fixer/symbols")
                .addHeader("apikey", "90bvVgraQiEn2QC6KuN8OOayRnaSqbIx")
                .method("GET", null)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        //System.out.println(responseBody);
        JSONObject json = new JSONObject(responseBody);
        JSONObject symbols = json.getJSONObject("symbols");
        String test = symbols.toString();

        // parse the response body into a map of currency symbols and names
        Map<String, String> currencySymbols = parseCurrencySymbols(test);

        // create an observable list of the currency symbols
        ObservableList<String> currencyList = FXCollections.observableArrayList(currencySymbols.keySet());

        // create a ComboBox and set its items to the currency list
        currency.setItems(currencyList);
        System.out.println(currencyList);

    }

    private Map<String, String> parseCurrencySymbols(String json) throws JsonProcessingException {
        // parse the JSON string into a map using a JSON parser library (e.g. Gson, Jackson, etc.)
        // here, we'll just return an empty map to keep things simple
        //    JSONObject json = new JSONObject(responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {
        });

        return map;
    }

    private void test() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/fixer/symbols")
                .addHeader("apikey", "90bvVgraQiEn2QC6KuN8OOayRnaSqbIx")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public double convertCurrency(double amount, String toCurrency) {
        double exchangeRate = exchangeRates.get(toCurrency) / exchangeRates.get("USD");
        //System.out.println(exchangeRates.get("USD"));
        return amount * exchangeRate;
    }

    @FXML
    private void currency_clicked(ActionEvent event) throws IOException {
        Starting_Bid_currency.setVisible(true);
////        System.out.println(currency.getValue());
//        String toCurrency = String.valueOf( convertCurrency(current_bid, currency.getValue() ) );
//        Starting_Bid_currency.setText(toCurrency);

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/fixer/convert?to=" + currency.getValue() + "&from=USD&amount=" + current_bid)
                .addHeader("apikey", "90bvVgraQiEn2QC6KuN8OOayRnaSqbIx")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);

        JSONObject json = new JSONObject(responseBody);
        double test = json.getDouble("result");

//        System.out.println(response.body().string());
//        JSONObject json = new JSONObject(response.body().string());
//        JSONObject result = json.getJSONObject("result");
//        String test = result.toString();

        Starting_Bid_currency.setText(String.valueOf(test));

    }
}
