package com.example.demoabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PayController implements Initializable {
    @FXML
    private Button button_Place;

    @FXML
    private Button button_whatisSec;

    @FXML
    private Button cancel;
    @FXML
    private Label label_ticketNum;
    @FXML
    private Label label_totalPrice;
    @FXML
    private Label label_Details;

    User user;
    User ouser;
    Reservation reservation;
    Flight flight;
    ActionEvent eventRes;

    public void setTicketNumber(int n){
        label_ticketNum.setText("Ticket :  "+n);
    }
    public void setDetails(int YT,int OU,int OUC){
        if(OU==0) {
            if(YT==0) {
                label_Details.setText("");
            }
            else{
                label_Details.setText("(You+Your children " +YT +")");
            }
        }
        else {
            if(YT==0) {
                if(OUC==0) {
                    label_Details.setText("(You+Other User)");
                }
                else{
                    label_Details.setText("(You+Other User+Other User Children "+OUC+")");
                }
            }
            else {
                if (OUC == 0) {
                    label_Details.setText("(You+Your Children "+YT+"+Other User)");
                } else {
                    label_Details.setText("(You+Your Children "+YT+"+Other User+Other User Children " + OUC + ")");
                }
            }
        }
    }
    public void setTotalPrice(Double p){
        label_totalPrice.setText("Total Price : "+p+"$");
    }

    public void setUser(User u){
        user = u;
    }
    public void setOuser(User u){
        ouser = u;
    }
    public void setReservation(Reservation r){
        reservation = r;
    }
    public void setFlight(Flight f){
        flight = f;
    }
    public void setEventRes(ActionEvent event){
        eventRes = event;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_Place.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.payAndres(event,user,reservation,ouser,flight);
                Stage stage = (Stage) button_Place.getScene().getWindow();
                stage.close();
                DBUtils.changeSceneU(eventRes,"logged-in1.fxml","Flights",user);
            }
        });
    }
    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}
