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

public class PassengersController implements Initializable {

    @FXML
    private Button button_AIF;

    @FXML
    private Button button_Airlines;

    @FXML
    private Button button_Flights;

    @FXML
    private Button button_Makes;

    @FXML
    private Button button_Passenger;

    @FXML
    private Button button_Reservations;

    @FXML
    private Button button_TN1R;

    @FXML
    private Button button_TNPInF;

    @FXML
    private Button button_UTA;

    @FXML
    private Button button_WELR;

    @FXML
    private Button button_dash;

    @FXML
    private Button button_logout;

    @FXML
    private Button button_users;

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_fullname;
    @FXML
    private TextField tf_fno;
    @FXML
    private TextField tf_gender;
    @FXML
    private TextField tf_address;
    @FXML
    private TextField tf_age;
    @FXML
    private TextField tf_children;

    @FXML
    private TableColumn<Passengers, Integer> col_Age;

    @FXML
    private TableColumn<Passengers, Integer> col_Children;

    @FXML
    private TableColumn<Passengers, String> col_FN;

    @FXML
    private TableColumn<Passengers, String> col_Gender;

    @FXML
    private TableColumn<Passengers, String> col_add;

    @FXML
    private TableColumn<Passengers, Integer> col_id;

    @FXML
    private TableColumn<Passengers, String> col_usr;
    @FXML
    private TableColumn<Passengers, Integer> col_fno;

    @FXML
    private Label label_welcome;

    @FXML
    private TableView<Passengers> passTable;

    static ObservableList<Passengers> passList = FXCollections.observableArrayList();

    public ObservableList<Passengers> getPass() {
        ObservableList<Passengers> passengers = FXCollections.observableArrayList();
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from PASSENGER");

            while (resultSet.next()){
                passengers.add(new Passengers(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getInt(8)));
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
        return passengers;
    }

    @FXML
    void add() throws SQLException {
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();
        PreparedStatement statement = null;
        PreparedStatement getLastID = null;
        ResultSet resultSet = null;
        int i=0;
        Passengers p= new Passengers(0,tf_username.getText(),tf_fullname.getText(),Integer.parseInt(tf_fno.getText()),tf_gender.getText(),tf_address.getText(),Integer.parseInt(tf_age.getText()),Integer.parseInt(tf_children.getText()));
        try{
            statement = connection.prepareStatement("INSERT INTO PASSENGER VALUES ( ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1,p.getUsername());
            statement.setString(2,p.getFullname());
            statement.setInt(3,p.getFNO());
            statement.setString(4,p.getGender());
            statement.setString(5,p.getAddress());
            statement.setInt(6,p.getAge());
            statement.setInt(7,p.getChildren());

            statement.executeUpdate();

            getLastID = connection.prepareStatement("select TOP 1 P_ID from PASSENGER order by P_ID DESC");//select TOP 1 U_ID from _USER order by U_ID DESC
            resultSet = getLastID.executeQuery();
            while (resultSet.next()){
                i = resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        p.setPID(i);
        passList.add(p);

        tf_username.clear();
        tf_fno.clear();
        tf_fullname.clear();
        tf_gender.clear();
        tf_address.clear();
        tf_age.clear();
        tf_children.clear();

    }

    @FXML
    void delete() {
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();
        Passengers selectedItem = (Passengers) passTable.getSelectionModel().getSelectedItem();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("Delete from PASSENGER where P_ID = ?");
            stm.setInt(1,selectedItem.getPID());
            stm.executeUpdate();

        } catch (SQLException x) {
            x.printStackTrace();
        }
        passTable.getItems().remove(selectedItem);
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

        col_id.setCellValueFactory(new PropertyValueFactory<Passengers,Integer>("PID"));
        col_usr.setCellValueFactory(new PropertyValueFactory<Passengers,String>("Username"));
        col_FN.setCellValueFactory(new PropertyValueFactory<Passengers,String>("Fullname"));
        col_fno.setCellValueFactory(new PropertyValueFactory<Passengers,Integer>("FNO"));
        col_Gender.setCellValueFactory(new PropertyValueFactory<Passengers,String>("Gender"));
        col_add.setCellValueFactory(new PropertyValueFactory<Passengers, String>("Address"));
        col_Age.setCellValueFactory(new PropertyValueFactory<Passengers, Integer>("Age"));
        col_Children.setCellValueFactory(new PropertyValueFactory<Passengers, Integer>("Children"));

        passList = getPass();
        passTable.setItems(passList);

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

        button_Reservations.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneR(event,"Reserv.fxml","Reservations",user);
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

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeScene(event,"hello-view.fxml","Log in!",null);
            }
        });
    }
}
