package com.example.demoabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FlightsController implements Initializable {

    @FXML
    private Button button_AIF;

    @FXML
    private Button button_Airlines;

    @FXML
    private Button button_Flights;
    @FXML
    private Button button_Passenger;


    @FXML
    private Button button_Makes;

    @FXML
    private Button button_Reservations;

    @FXML
    private Button button_WELR;

    @FXML
    private Button button_UTA;

    @FXML
    private Button button_TN1R;
    @FXML
    private Button button_TNPInF;

    @FXML
    private Button button_dash;

    @FXML
    private Button button_logout;

    @FXML
    private Button button_users;
    @FXML
    private Button add;
    @FXML
    private Button delete;

    @FXML
    private TextField tf_fno;
    @FXML
    private TextField tf_date;
    @FXML
    private TextField tf_ano;
    @FXML
    private TextField tf_dest;
    @FXML
    private TextField tf_start;
    @FXML
    private TextField tf_arrival;
    @FXML
    private TextField tf_cthrou;
    @FXML
    private TextField tf_seats;
    @FXML
    private TextField tf_df;


    @FXML
    private Label label_welcome;
    @FXML
    private TableView<Flight> flightsTable;
    @FXML
    private TableColumn<Flight,Integer> col_id;
    @FXML
    private TableColumn<Flight,String> col_date;
    @FXML
    private TableColumn<Flight, Integer> col_idA;
    @FXML
    private TableColumn<Flight,String> col_dest;
    @FXML
    private TableColumn<Flight,String> col_start;
    @FXML
    private TableColumn<Flight, String> col_arrival;
    @FXML
    private TableColumn<Flight, String> col_countryThro;
    @FXML
    private TableColumn<Flight, Integer> col_seat;
    @FXML
    private TableColumn<Flight, Double> col_price;

    static ObservableList<Flight> flightsList = FXCollections.observableArrayList();

    public ObservableList<Flight> getFlights() {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from FLIGHT");

            while (resultSet.next()){
                flights.add(new Flight(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4), resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getDouble(9)));
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return flights;
    }

    User user;
    void setUser(User u){
        user = u;
    }
    public void setUserName(String username){
        label_welcome.setText("Welcome admin "+username+"!");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_id.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("idFlight"));
        col_date.setCellValueFactory(new PropertyValueFactory<Flight,String>("Date"));
        col_idA.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("idAirline"));
        col_dest.setCellValueFactory(new PropertyValueFactory<Flight,String>("Destination"));
        col_start.setCellValueFactory(new PropertyValueFactory<Flight,String>("TimeStart"));
        col_arrival.setCellValueFactory(new PropertyValueFactory<Flight, String>("TimeArrive"));
        col_countryThro.setCellValueFactory(new PropertyValueFactory<Flight,String>("CountryThro"));
        col_seat.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("Seats"));
        col_price.setCellValueFactory(new PropertyValueFactory<Flight,Double>("DefaultPrice"));

        flightsList = getFlights();
        flightsTable.setItems(flightsList);

        button_dash.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeScene(event,"logged-in-admin.fxml","Dashboard",user);
            }
        });
        button_users.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneU(event,"users.fxml","Users",user);
            }
        });

        button_Airlines.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneA(event,"airlines.fxml","Airlines",user);
            }
        });

        button_Makes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneM(event,"makes.fxml","Makes",user);
            }
        });

        button_Reservations.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneR(event,"Reserv.fxml","Reservations",user);
            }
        });
        button_Passenger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneP(event,"passengers.fxml","Passengers",user);
            }
        });

        button_UTA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneUTA(event,"updateTicAm.fxml","Update Tic Amount",user);
            }
        });
        button_AIF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneAIF(event,"aif.fxml","Absents in Flight#",user);
            }
        });
        button_TN1R.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneTN1R(event,"tnor.fxml","Total Number for 1 Reservation",user);
            }
        });
        button_WELR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneWELR(event,"welr.fxml","Who Else Reserve",user);
            }
        });

        button_TNPInF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneTNPF(event,"tnpf.fxml","Total Number of Passengers in Flight#",user);
            }
        });

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeScene(event,"hello-view.fxml","Log in!",null);
            }
        });
    }
}
