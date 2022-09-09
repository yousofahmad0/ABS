package com.example.demoabspassengercheck;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String  title, User user,Flight flight){
        Parent root = null;

        try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setwelcome(user.getUsername(),flight.getDestination());
                loggedInController.setchildren(String.valueOf(user.getChildren()));
                loggedInController.setUser(user);
                loggedInController.setFlight(flight);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.show();
    }
    public static void changeS(ActionEvent event, String fxmlFile, String  title){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);

        stage.setScene(new Scene(root));
        stage.setFullScreen(true);
        stage.show();
    }

    public static void logInUser(ActionEvent event, User user){
        DBC dbc = new DBC();
        Connection connection = dbc.getConnection();
        PreparedStatement preparedStatement = null;
        PreparedStatement MAKEexists = null;
        ResultSet resultSetU = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT U_password, U_FULLNAME, U_GENDER, U_ADDRESS, U_AGE, U_CHILDREN FROM _USER WHERE U_username = ?");
            preparedStatement.setString(1,user.getUsername());
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {

                while(resultSet.next()){
                    user.setFullname(resultSet.getString(2));
                    user.setGender(resultSet.getString(3));
                    user.setAddress(resultSet.getString(4));
                    user.setAge(resultSet.getInt(5));
                    user.setChildren(resultSet.getInt(6));

                    String retrievedPasswoed = resultSet.getString(1);
                    if(retrievedPasswoed.equals(user.getPassword())) {
                        MAKEexists = connection.prepareStatement("select m.U_ID, f.F_NO, f.F_DEST from MAKE m, _USER u, RESERVATION r, FLIGHT f where u.U_username = ? and u.U_ID = m.U_ID and m.RES_ID = r.RES_ID and f.F_NO = r.F_NO");//select m.U_ID, f.F_NO, f.F_DEST from MAKE m, RESERVATION r, FLIGHT f, _USER u where u.U_username = 'ysf' and u.U_ID = m.U_ID and m.RES_ID = r.RES_ID and f.F_NO = r.F_NO
                        MAKEexists.setString(1, user.getUsername());
                        resultSetU = MAKEexists.executeQuery();
                        if (!resultSetU.isBeforeFirst()) {
                            System.out.println("User but without Reservation.");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Provided credentials are incorrect!");
                            alert.show();
                        } else {
                            while (resultSetU.next()) {
                                int U = resultSetU.getInt(1);
                                int Fno = resultSetU.getInt(2);
                                String FDest = resultSetU.getString(3);
                                Flight flight = new Flight(Fno, null, FDest, null, null, null, null);
                                changeScene(event, "logged-in.fxml", "Checking", user, flight);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(resultSetU != null){
                try {
                    resultSetU.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(MAKEexists != null){
                try {
                    MAKEexists.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void DoneAndReturen(ActionEvent event,User user,Passenger passenger,Flight flight){
        DBC dbc = new DBC();
        Connection connection = dbc.getConnection();
        PreparedStatement insertPassenger = null;
        try{
            insertPassenger = connection.prepareStatement("Insert into PASSENGER VALUES ( ?, ?, ?, ?, ?, ?, ?)");
            insertPassenger.setString(1,passenger.getUsername());
            insertPassenger.setString(2,passenger.getFullname());
            insertPassenger.setInt(3,passenger.getFlight().getIdFlight());
            insertPassenger.setString(4,passenger.getGender());
            insertPassenger.setString(5,passenger.getAddress());
            insertPassenger.setInt(6,passenger.getAge());
            insertPassenger.setInt(7,passenger.getChildren());
            insertPassenger.executeUpdate();
            changeS(event,"hello-view.fxml","Passenger Checker!");
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(insertPassenger != null){
                try {
                    insertPassenger.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
