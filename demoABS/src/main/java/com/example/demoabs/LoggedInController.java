package com.example.demoabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    String name;
    @FXML
    private Label LabelCountiesThrough;

    @FXML
    private Button button_1France;

    @FXML
    private Button button_logout;

    @FXML
    private Label label_FDate;

    @FXML
    private Label label_FAir;

    @FXML
    private Label label_FPrice;

    @FXML
    private Label label_FTimearrival;

    @FXML
    private Label label_FTimestart;

    @FXML
    private Label label_FlightReminder;

    @FXML
    private Label label_welcome;
    @FXML
    private Label Label_FlightNo;
    User user;

    public void setUser(User u){
        user = u;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"hello-view.fxml","Log in!",null);
            }
        });
        button_1France.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id = Integer.parseInt(Label_FlightNo.getText());
                Double pr = Double.parseDouble(label_FPrice.getText());

                Airline airline = new Airline(label_FAir.getText());
                Flight flight = new Flight(id,label_FDate.getText(),button_1France.getText(),label_FTimestart.getText(),label_FTimearrival.getText(),LabelCountiesThrough.getText(),pr);
                DBUtils.changeSceneResf(event,"reserveff.fxml","Pay and Reservation!",name,flight,airline.getName(),user);
            }
        });
    }
    //Dont Forget the date of your flight to France (17/12/2001) and the go of time (12:00am).
    public void setUserName(String username){
        name = username;
        label_welcome.setText("Welcome "+username+",choose a Flight!");
    }

    public void setFlightDetails(String dist,String date,String time) {
        label_FlightReminder.setText("Don't Forget the date of your flight to "+dist+"("+date+") and the go of time("+time+").");
    }
}
