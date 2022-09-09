package com.example.demoabs;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ReserveffController implements Initializable {

    @FXML
    private Button button_PayRes;

    @FXML
    private Button cance;

    @FXML
    private CheckBox cbox_OUC;

    @FXML
    private ChoiceBox<String> classs;

    @FXML
    private Label label_FlightAirline;

    @FXML
    private Label label_FlightDateTime;

    @FXML
    private Label label_updPrice;

    @FXML
    private Label label_updPrice1;

    @FXML
    private TextField tf_children;

    @FXML
    private TextField tf_username;

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

    public void setFlightDet(String distt,String airl){
        label_FlightAirline.setText("You choose our Flight to "+distt+ ", which follows"+airl+ ".");
    }
    public void setFlightDat(String date,String stime,String atime){
        label_FlightDateTime.setText("The Flight date is on "+date+ ". It will go of at "+atime+" and will arrive at "+stime+".");
    }

    public void setPrices(String pA,String pB,String pC){
        label_updPrice.setText("Class A price is "+pA+"$. Class B price is "+pB+"$. Class C price is "+pC+"$.");
    }

    public void setFlightDet1(String distt){
        label_FlightAirline.setText(distt);
    }
    public void setFlightDat1(String d){
        label_FlightDateTime.setText(d);
    }

    public void setPrices1(String p){
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        classs.getItems().addAll("A","B","C");
        classs.setValue("C");
        cance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                Connection connection = null;
//                PreparedStatement preparedStatement = null;
//                ResultSet resultSet = null;
//
//                try {
//                    String url = "jdbc:mysql://localhost:3306/abs";
//                    String uname = "root";
//                    String pass = "helloyzsf";
//                    connection = DriverManager.getConnection(url, uname, pass);
//
//                    preparedStatement = connection.prepareStatement("SELECT password, fullname, gender, address, age, children, phone FROM users WHERE username = ?");
//                    preparedStatement.setString(1,tf_username.getText());
//                    resultSet = preparedStatement.executeQuery();
//                    while (resultSet.next()){
//                        User user = new User(tf_username.getText(),resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7));
//                        DBUtils.changeSceneU(event,"logged-in1.fxml","Flights",user);
//                    }
//                } catch (SQLException e){
//                    e.printStackTrace();
//                } finally {
//                    if(resultSet != null){
//                        try {
//                            resultSet.close();
//                        } catch (SQLException e){
//                            e.printStackTrace();
//                        }
//                    }
//                    if(preparedStatement != null){
//                        try {
//                            preparedStatement.close();
//                        } catch (SQLException e){
//                            e.printStackTrace();
//                        }
//                    }
//                    if(connection != null){
//                        try {
//                            connection.close();
//                        } catch (SQLException e){
//                            e.printStackTrace();
//                        }
//                    }
//                }
                //user.setChildren(Integer.parseInt(tf_children.getText()));
                DBUtils.changeSceneS(event,"logged-in1.fxml","Flights",user);
            }
        });

        cbox_OUC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String choiceClass = classs.getValue();//getSelectionModel().getSelectedItem()
                DBUtils.changeSceneResff(event,"reservef.fxml","Pay and Reservation!",tf_username.getText(),label_FlightAirline.getText(),label_FlightDateTime.getText(),label_updPrice.getText(),tf_children.getText(),choiceClass,flight,user);
            }
        });

        button_PayRes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_username.getText().trim().isEmpty() && !tf_children.getText().trim().isEmpty()) {
                    if(isInt(tf_children,tf_children.getText())) {
                        if(Integer.parseInt(tf_children.getText()) >= 0) {
                            user.setChildren(Integer.parseInt(tf_children.getText()));
                            String choiceClass = classs.getValue();//getSelectionModel().getSelectedItem()
                            int tNum = Integer.parseInt(tf_children.getText())+1;
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
                            DBUtils.changeScenePay1(event, "alertPay.fxml", "Pay", user, flight, reservation);
                        } else{
                            System.out.println("Please fill the children with positive Number or 0");
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
