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
import java.sql.*;
import java.util.ResourceBundle;

public class AirlinesController implements Initializable {

    @FXML
    private Button button_AIF;

    @FXML
    private Button button_UTA;

    @FXML
    private Button button_TN1R;
    @FXML
    private Button button_TNPInF;

    @FXML
    private Button button_Airlines;
    @FXML
    private Button button_Passenger;


    @FXML
    private Button button_Flights;

    @FXML
    private Button button_Makes;

    @FXML
    private Button button_Reservations;

    @FXML
    private Button button_WELR;

    @FXML
    private Button button_dash;

    @FXML
    private Button button_logout;

    @FXML
    private Button button_users;

    @FXML
    private TextField name;

    @FXML
    private TableView<Airline> airlineTable;
    @FXML
    private TableColumn<Airline,Integer> col_id;
    @FXML
    private TableColumn<Airline,String> col_Aname;

    @FXML
    private Label label_welcome;

    static ObservableList<Airline> airlineList = FXCollections.observableArrayList();

    public ObservableList<Airline> getAirlines() {
        ObservableList<Airline> airlines = FXCollections.observableArrayList();
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from AIRLINE");

            while (resultSet.next()){

                airlines.add(new Airline(resultSet.getInt(1),resultSet.getString(2)));
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
        return airlines;
    }

    @FXML
    void add() throws SQLException {
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();
        PreparedStatement statement = null;
        PreparedStatement getLastID = null;
        ResultSet resultSet = null;
        int i=0;
        Airline airline = new Airline(name.getText());
        try{
            statement = connection.prepareStatement("INSERT INTO AIRLINE VALUES (?)");
            statement.setString(1,airline.getName());

            statement.executeUpdate();

            getLastID = connection.prepareStatement("select TOP 1 A_ID from AIRLINE order by A_ID DESC");//select TOP 1 U_ID from order by U_ID DESC
            resultSet = getLastID.executeQuery();
            while (resultSet.next()){
                i = resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try{
                    statement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(getLastID != null){
                try{
                    getLastID.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        airline.setID(i);
        airlineList.add(airline);

        name.clear();
    }

    @FXML
    void delete() {
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();
        Airline selectedItem = (Airline) airlineTable.getSelectionModel().getSelectedItem();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("Delete from AIRLINE where A_ID = ?");
            stm.setInt(1,selectedItem.getID());
            stm.executeUpdate();

        } catch (SQLException x) {
            x.printStackTrace();
        } finally {
            if(stm != null){
                try{
                    stm.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        airlineTable.getItems().remove(selectedItem);
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

        col_id.setCellValueFactory(new PropertyValueFactory<Airline,Integer>("ID"));
        col_Aname.setCellValueFactory(new PropertyValueFactory<Airline,String>("Name"));

        airlineList = getAirlines();
        airlineTable.setItems(airlineList);

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

        button_Reservations.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneR(event,"Reserv.fxml","Reservations",user);
            }
        });
        button_TN1R.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneTN1R(event,"tnor.fxml","Total Number for 1 Reservation",user);
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

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeScene(event,"hello-view.fxml","Log in!",null);
            }
        });
    }
}
