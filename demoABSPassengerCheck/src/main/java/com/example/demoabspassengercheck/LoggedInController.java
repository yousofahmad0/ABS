package com.example.demoabspassengercheck;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    @FXML
    private Button bt_close;

    @FXML
    private Button button_done;

    @FXML
    private Label label_welcome;

    @FXML
    private TextField tf_CameChildren;

    User user;
    Flight flight;
    void setUser(User u){
        user = u;
    }
    void setFlight(Flight f){
        flight = f;
    }

    private boolean isInt(TextField input,String message){
        try{
            int age = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e){
            //input.setC();
            System.out.println("Error: input is not integer.");
            return false;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                javafx.application.Platform.exit();
            }
        });

        button_done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_CameChildren.getText().trim().isEmpty()){
                    if(isInt(tf_CameChildren,tf_CameChildren.getText())){
                        if(Integer.parseInt(tf_CameChildren.getText())>=0){
                            if(Integer.parseInt(tf_CameChildren.getText()) <= user.getChildren()){
                                Passenger passenger = new Passenger(user.getUsername(),user.getFullname(), user.getGender(), user.getAddress(),user.getAge(),Integer.parseInt(tf_CameChildren.getText()), flight);
                                DBUtils.DoneAndReturen(event,user,passenger,flight);
                            }
                            else{
                                System.out.println("Please fill the children with number less than or equal to " + user.getChildren()+".");
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("Please fill the children field with number less than or equal to "+ user.getChildren() + ".");
                                alert.show();
                            }
                        }else{
                            System.out.println("Please fill the children with positive number or 0!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Please fill the children field with positive number or 0!");
                            alert.show();
                        }
                    }else {
                        System.out.println("Please fill the children with Number");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please fill the children field with number!");
                        alert.show();
                    }
                }
                else{
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information!");
                    alert.show();
                }
            }
        });
    }
    public void setwelcome(String username,String Dest){
        label_welcome.setText("Welcome "+username+", in your Flight to "+Dest+".");
    }
    public void setchildren(String c){
        tf_CameChildren.setText(c);
    }


}
