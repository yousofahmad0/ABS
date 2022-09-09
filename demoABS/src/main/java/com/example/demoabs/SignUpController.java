package com.example.demoabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button button_log_in;

    @FXML
    private Button button_signup;

    @FXML
    private ToggleGroup gend;

    @FXML
    private RadioButton rb_female;

    @FXML
    private RadioButton rb_male;

    @FXML
    private TextField tf_address;

    @FXML
    private TextField tf_age;

    @FXML
    private TextField tf_fullname;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_phone;

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

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toglgender = ((RadioButton) gend.getSelectedToggle()).getText();
                //int age = (Integer)Integer.parseInt(tf_age.getText());
                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() && !tf_address.getText().trim().isEmpty()
                        && !tf_fullname.getText().trim().isEmpty() && !tf_age.getText().trim().isEmpty() && !tf_phone.getText().trim().isEmpty()){

                    if(isInt(tf_age,tf_age.getText())==true) {
                        if((Integer)Integer.parseInt(tf_age.getText())<18){
                            System.out.println("User age less than 18!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Your age must be 18 or above.");
                            alert.show();
                        }
                        else {
                            if (isInt(tf_phone, tf_phone.getText())) {
                                User user = new User(tf_username.getText(),tf_password.getText(),tf_fullname.getText(),toglgender,tf_address.getText(),(Integer)Integer.parseInt(tf_age.getText()),0,Integer.parseInt(tf_phone.getText()));
                                DBUtils.signUpUser(event,user);
                            } else {
                                System.out.println("Please fill the phone with Number");
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("Please fill phone field with your number!");
                                alert.show();
                            }
                        }
                    }
                    else {
                        System.out.println("Please fill the age with Number");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please fill age field with number!");
                        alert.show();
                    }
                } else{
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"hello-view.fxml","Log in!",null);
            }
        });
    }
}
