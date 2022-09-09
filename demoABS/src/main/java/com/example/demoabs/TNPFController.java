package com.example.demoabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class TNPFController implements Initializable {

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
    private Button button_go;

    @FXML
    private Button button_logout;

    @FXML
    private Button button_users;

    @FXML
    private Button button_Passenger;

    @FXML
    private Label label_tn;

    @FXML
    private Label label_welcome;

    @FXML
    private TextField tf_flightn;

    private boolean isInt(TextField input,String message){
        try{
            int age = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e){
            System.out.println("Error: input is not integer.");
            return false;
        }
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

        button_go.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_flightn.getText().trim().isEmpty()){
                    if(isInt(tf_flightn,tf_flightn.getText())){
                        String mess = DBUtilsAdmin.functTNPF(Integer.parseInt(tf_flightn.getText()));
                        if(mess != null){
                            label_tn.setText(mess);
                        } else {
                            label_tn.setText("Error");
                            label_tn.setTextFill(Paint.valueOf("RED"));
                        }
                    }else {
                        System.out.println("Please fill the Flight with Number");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please fill Flight with number!");
                        alert.show();
                    }
                }else {
                    System.out.println("Please fill Flight field");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill Flight Number to execute!");
                    alert.show();
                }
            }
        });

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

        button_AIF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtilsAdmin.changeSceneAIF(event,"aif.fxml","Absents in Flight#",user);
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
                DBUtilsAdmin.changeSceneUTA(event,"updateTicAm.fxml","Update Tic Amount",user);
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
