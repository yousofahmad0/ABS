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

public class UsersController implements Initializable {

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
    private Button button_UTA;

    @FXML
    private Button button_TN1R;
    @FXML
    private Button button_TNPInF;

    @FXML
    private Button button_WELR;

    @FXML
    private Button button_dash;

    @FXML
    private Button button_logout;

    @FXML
    private Button button_users;
    @FXML
    private Button button_Passenger;

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_fullname;
    @FXML
    private TextField tf_gender;
    @FXML
    private TextField tf_address;
    @FXML
    private TextField tf_age;
    @FXML
    private TextField tf_children;
    @FXML
    private TextField tf_phone;


    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User,Integer> col_id;
    @FXML
    private TableColumn<User,String> col_usr;
    @FXML
    private TableColumn<User, String> col_FN;
    @FXML
    private TableColumn<User,String> col_Gender;
    @FXML
    private TableColumn<User,String> col_add;
    @FXML
    private TableColumn<User, Integer> col_Age;
    @FXML
    private TableColumn<User, Integer> col_Children;
    @FXML
    private TableColumn<User, Integer> col_Phone;

    @FXML
    private Label label_welcome;

    static ObservableList<User> usList = FXCollections.observableArrayList();

    public ObservableList<User> getUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select U_ID, U_username, U_FULLNAME, U_GENDER, U_ADDRESS, U_AGE, U_CHILDREN, U_PHONE from _USER");

            while (resultSet.next()){

                users.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5), resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8)));
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
        return users;
    }

    User user;

    void setUser(User u){
        user = u;
    }

    public void setUserName(String username){
        label_welcome.setText("Welcome admin "+username+"!");
    }

    @FXML
    void add() throws SQLException {
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();
        PreparedStatement statement = null;
        PreparedStatement getLastID = null;
        ResultSet resultSet = null;
        int i=0;
        User u= new User(tf_username.getText(),tf_password.getText(),tf_fullname.getText(),tf_gender.getText(),tf_address.getText(),Integer.parseInt(tf_age.getText()),Integer.parseInt(tf_children.getText()),Integer.parseInt(tf_phone.getText()));
        try{
            statement = connection.prepareStatement("INSERT INTO _USER VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1,u.getUsername());
            statement.setString(2,u.getPassword());
            statement.setString(3,u.getFullname());
            statement.setString(4,u.getGender());
            statement.setString(5,u.getAddress());
            statement.setInt(6,u.getAge());
            statement.setInt(7,u.getChildren());
            statement.setInt(8,u.getPhone());
            statement.executeUpdate();

            getLastID = connection.prepareStatement("select TOP 1 U_ID from _USER order by U_ID DESC");//select TOP 1 U_ID from _USER order by U_ID DESC
            resultSet = getLastID.executeQuery();
            while (resultSet.next()){
                i = resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        u.setID(i);
        usList.add(u);

        tf_username.clear();
        tf_password.clear();
        tf_fullname.clear();
        tf_gender.clear();
        tf_address.clear();
        tf_age.clear();
        tf_phone.clear();
        tf_children.clear();

    }

    @FXML
    void delete() {
        DBCAdmin dbcAdmin = new DBCAdmin();
        Connection connection = dbcAdmin.getConnection();
        User selectedItem = (User) userTable.getSelectionModel().getSelectedItem();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement("Delete from _USER where U_ID = ?");
            stm.setInt(1,selectedItem.getID());
            stm.executeUpdate();

        } catch (SQLException x) {
            x.printStackTrace();
        }
        userTable.getItems().remove(selectedItem);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_id.setCellValueFactory(new PropertyValueFactory<User,Integer>("ID"));
        col_usr.setCellValueFactory(new PropertyValueFactory<User,String>("Username"));
        col_FN.setCellValueFactory(new PropertyValueFactory<User,String>("Fullname"));
        col_Gender.setCellValueFactory(new PropertyValueFactory<User,String>("Gender"));
        col_add.setCellValueFactory(new PropertyValueFactory<User,String>("Address"));
        col_Age.setCellValueFactory(new PropertyValueFactory<User, Integer>("Age"));
        col_Children.setCellValueFactory(new PropertyValueFactory<User,Integer>("Children"));
        col_Phone.setCellValueFactory(new PropertyValueFactory<User,Integer>("Phone"));

//        DBCAdmin dbcAdmin = new DBCAdmin();
//        Connection connection = dbcAdmin.getConnection();
//
//        try{
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from _USER");
//
//            while (resultSet.next()){
//                Users user = new Users(resultSet.getInt("U_ID"),resultSet.getString("U_username"),resultSet.getString("U_FULLNAME"),resultSet.getString("U_GENDER"),resultSet.getString("U_ADDRESS"), resultSet.getInt("U_AGE"),resultSet.getInt("U_CHILDREN"),resultSet.getInt("U_PHONE"));
//                System.out.println(user);
//                usList.add(user);
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        } finally {
//            if(connection != null){
//                try {
//                    connection.close();
//                } catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//        }

        usList = getUsers();
        userTable.setItems(usList);



        button_dash.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeScene(event,"logged-in-admin.fxml","Dashboard",user);
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
