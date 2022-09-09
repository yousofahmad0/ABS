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

public class ReservController implements Initializable {

    @FXML
    private Button button_AIF;

    @FXML
    private Button button_Airlines;

    @FXML
    private Button button_Flights;

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
    private Button add;
    @FXML
    private Button delete;

    @FXML
    private Button button_users;
    @FXML
    private Button button_Passenger;

    @FXML
    private TextField tf_resno;
    @FXML
    private TextField tf_ticAmount;
    @FXML
    private TextField tf_totalPrice;
    @FXML
    private TextField tf_resdate;
    @FXML
    private TextField tf_Flno;
    @FXML
    private TextField tf_class;
    @FXML
    private TextField tf_distination;

    @FXML
    private TableView<Reservations> reservsTable;
    @FXML
    private TableColumn<Reservations, Integer> col_rid;
    @FXML
    private TableColumn<Reservations, Integer> col_ticAm;
    @FXML
    private TableColumn<Reservations, Double> col_totP;
    @FXML
    private TableColumn<Reservations,String> col_date;
    @FXML
    private TableColumn<Reservations,Integer> col_fno;
    @FXML
    private TableColumn<Reservations, String> col_class;
    @FXML
    private TableColumn<Reservations, String> col_dist;


    static ObservableList<Reservations> resList = FXCollections.observableArrayList();

    public ObservableList<Reservations> getRes() {
        ObservableList<Reservations> reservations = FXCollections.observableArrayList();
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from RESERVATION");

            while (resultSet.next()){
                reservations.add(new Reservations(resultSet.getInt(1),resultSet.getInt(2),resultSet.getDouble(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7)));
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
        return reservations;
    }

    @FXML
    private Label label_welcome;

    User user;
    void setUser(User u){
        user = u;
    }
    public void setUserName(String username){
        label_welcome.setText("Welcome admin "+username+"!");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_rid.setCellValueFactory(new PropertyValueFactory<Reservations, Integer>("ResId"));
        col_ticAm.setCellValueFactory(new PropertyValueFactory<Reservations, Integer>("TicketNum"));
        col_totP.setCellValueFactory(new PropertyValueFactory<Reservations, Double>("TotalPrice"));
        col_date.setCellValueFactory(new PropertyValueFactory<Reservations, String>("Date"));
        col_fno.setCellValueFactory(new PropertyValueFactory<Reservations, Integer>("Fno"));
        col_class.setCellValueFactory(new PropertyValueFactory<Reservations, String>("Cl"));
        col_dist.setCellValueFactory(new PropertyValueFactory<Reservations, String>("Destination"));

        resList = getRes();
        reservsTable.setItems(resList);

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

        button_Flights.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneF(event,"flights.fxml","Flights",user);
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
        button_Passenger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneP(event,"passengers.fxml","Passengers",user);
            }
        });

        button_UTA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneUTA(event,"updateTicAm.fxml","Updete Tic Amount",user);
            }
        });

        button_AIF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneAIF(event,"aif.fxml","Absents in Flight#",user);
            }
        });
        button_TNPInF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneTNPF(event,"tnpf.fxml","Total Number of Passengers in Flight#",user);
            }
        });
        button_WELR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneWELR(event,"welr.fxml","Who Else Reserve",user);
            }
        });


        button_TN1R.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneTN1R(event,"tnor.fxml","Total Number for 1 Reservation",user);
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
