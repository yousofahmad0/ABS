package com.example.demoabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ReservefController implements Initializable {

    @FXML
    private Button button_PayRes;

    @FXML
    private Button cance;

    @FXML
    private ChoiceBox<String> classs;

    @FXML
    private Label label_FlightAirline;

    @FXML
    private Label label_FlightDateTime;

    @FXML
    private Label label_updPrice;

    @FXML
    private TextField tf_children;

    @FXML
    private TextField tf_oUsername;

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_oUserChildren;
    @FXML
    private TextField tf_oUserPassword;
    @FXML
    private CheckBox cbox_OUC;

    Flight flight;
    User user;

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
    public void setUserName(String username){
        tf_username.setText(username);
    }

    public void setFlightDet(String distt){
        label_FlightAirline.setText(distt);
    }
    public void setFlightDat(String d){
        label_FlightDateTime.setText(d);
    }

    public void setPrices(String p){
        label_updPrice.setText(p);
    }
    public void setChildren(String c){
        tf_children.setText(c);
    }
    public void setRadio(String r){
        classs.setValue(r);
    }

    public void setFlight(Flight f){
        flight = f;
    }

    public void setUser(User u){
        user = u;
    }

    public User checkOtherU(String ousername){
        DBC dbc = new DBC();
        Connection connection = dbc.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT U_password, U_FULLNAME, U_GENDER, U_ADDRESS, U_AGE, U_CHILDREN, U_PHONE FROM _USER WHERE U_username = ?");
            preparedStatement.setString(1,ousername);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                return null;
            } else {
                while(resultSet.next()){
                    User user = new User(ousername,resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7));
                    return user;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classs.getItems().addAll("A","B","C");
        classs.setValue("C");
        cance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeSceneS(event,"logged-in1.fxml","Flights",user);
            }
        });

        cbox_OUC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    String choiceClass = classs.getValue();
                    DBUtils.changeSceneResfff(event,"reserveff.fxml","Pay and Reservation!",tf_username.getText(),label_FlightAirline.getText(),label_FlightDateTime.getText(),label_updPrice.getText(),tf_children.getText(),choiceClass,flight,user);
            }
        });
        button_PayRes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_username.getText().trim().isEmpty() && !tf_children.getText().trim().isEmpty() && !tf_oUsername.getText().trim().isEmpty() && !tf_oUserPassword.getText().trim().isEmpty() && !tf_oUserChildren.getText().trim().isEmpty()) {
                    if(isInt(tf_children,tf_children.getText())) {
                        if(Integer.parseInt(tf_children.getText()) >= 0) {
                            if(isInt(tf_oUserChildren,tf_oUserChildren.getText())) {
                                if (Integer.parseInt(tf_oUserChildren.getText()) >= 0){
                                    User ouser = new User(tf_oUsername.getText(),tf_oUserPassword.getText(),null,null,null,0,Integer.parseInt(tf_oUserChildren.getText()),0) ;//checkOtherU(tf_oUsername.getText());
                                    user.setChildren(Integer.parseInt(tf_children.getText()));
                                    String choiceClass = classs.getValue();
                                    int tNum = Integer.parseInt(tf_children.getText()) + Integer.parseInt(tf_oUserChildren.getText())+2;
                                    Double totP;
                                    if(choiceClass.equals("A")){ totP = flight.getDefaultPrice() * 2;
                                    } else if(choiceClass.equals("B")) { totP = flight.getDefaultPrice() + (flight.getDefaultPrice() / 2);
                                    } else { totP = flight.getDefaultPrice();
                                    }
                                    totP = totP * tNum;
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
                                    Date date = new Date(System.currentTimeMillis());
                                    String d = formatter.format(date);
                                    Reservation reservation = new Reservation(tNum,totP,d,choiceClass,flight.getDestination(),flight);
                                    DBUtils.changeScenePay2(event, "alertPay.fxml", "Pay",user,ouser, flight, reservation);

                                }
                                else{
                                    System.out.println("Please fill the other user children with positive number or 0!");
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setContentText("Please fill the other user children field with positive number or 0!");
                                    alert.show();
                                }
                            }
                            else{
                                System.out.println("Please fill the other user children with Number");
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("Please fill the other user children field with number!");
                                alert.show();
                            }
                        }
                        else {
                            System.out.println("Please fill the children with positive number or 0!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Please fill the children field with positive number or 0!");
                            alert.show();
                        }
                    } else{
                        System.out.println("Please fill the children with Number");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please fill the children field with number!");
                        alert.show();
                    }
                } else{
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to complete your registration!");
                    alert.show();
                }
            }
        });

    }
}
