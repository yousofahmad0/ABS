package com.example.demoabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {
    private static User duser;
    private static User douser;

    private static boolean checkUMAKE(User user){
        DBC dbc = new DBC();
        Connection connection = dbc.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int f = 0;
        try {
            String d=null,dd=null,ddd=null;
            preparedStatement = connection.prepareStatement("select F_DEST, F_DATE, F_START from FLIGHT f, RESERVATION r, MAKE m, _USER u where u.U_username = ? and u.U_ID = m.U_ID and r.RES_ID = m.RES_ID and f.F_NO = r.F_NO");
            preparedStatement.setString(1,user.getUsername());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                d = resultSet.getString(1);
                dd = resultSet.getString(2);
                ddd = resultSet.getString(3);
            }
            if(d!=null && dd!=null && ddd!=null) {
                f = 1;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            dbc.closeResultSet(resultSet);
            dbc.closeStatment(preparedStatement);
            dbc.closeConnection(connection);
        }
        if(f==0){
            return false;
        }
        else {
            return true;
        }
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String  title, String username){
        Parent root = null;

        if(username != null){
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserName(username);
            }catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneS(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        String username =  user.getUsername();
        if(user != null){
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserName(username);
                loggedInController.setUser(user);
            }catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneU(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        String username =  user.getUsername();
        if(user != null){
            try {
                int uid = 0;
                String d=null,dd=null,ddd=null;
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserName(username);
                loggedInController.setUser(user);

                DBC dbc = new DBC();
                Connection connection = dbc.getConnection();
                PreparedStatement MAKEExists = null;
                PreparedStatement getUserId = null;
                ResultSet resultSetUId = null;
                ResultSet details = null;
                try{
                    getUserId = connection.prepareStatement("select U_ID from _USER where U_username = ?");
                    getUserId.setString(1, user.getUsername());
                    resultSetUId = getUserId.executeQuery();
                    while (resultSetUId.next()){
                        uid = resultSetUId.getInt(1);
                    }
                    MAKEExists = connection.prepareStatement("select F_DEST, F_DATE, F_START from FLIGHT f, RESERVATION r, MAKE m where m.U_ID = ? and r.RES_ID = m.RES_ID and f.F_NO = r.F_NO");//select * from FLIGHT f,RESERVATION r,MAKE m where  m.U_ID = ? and r.RES_ID = m.RES_ID and f.F_NO = r.F_NO
                    MAKEExists.setInt(1,uid);
                    details = MAKEExists.executeQuery();

                    while (details.next()){
                        d = details.getString(1);
                        dd = details.getString(2);
                        ddd = details.getString(3);
                    }
                    if(d!=null && dd!=null && ddd!=null) {
                        loggedInController.setFlightDetails(d, dd, ddd);
                    }

                } catch (SQLException e){
                    e.printStackTrace();
                } finally {
                    if(resultSetUId != null){
                        try {
                            resultSetUId.close();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                    if(details != null){
                        try {
                            details.close();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                    if(getUserId != null){
                        try{
                            getUserId.close();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                    if(MAKEExists != null){
                        try {
                            MAKEExists.close();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection != null){
                        try{
                            connection.close();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }

        } else{
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void changeSceneResf(ActionEvent event, String fxmlFile, String  title, String username,Flight flight,String FAir,User user){

        if(checkUMAKE(user)==true){
            System.out.println("User already registers for a Flight!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You already register for a Flight. You can't register for more than one at a time!");
            alert.show();
            return;
        }
        Parent root = null;
        Double p = flight.getDefaultPrice();
        Double pa = p*2;
        Double pb = p+(p/2);
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            ReserveffController reserveffController = loader.getController();
            reserveffController.setUserName(username);
            reserveffController.setFlightDet(flight.getDestination(),FAir);
            reserveffController.setFlightDat(flight.getDate(),flight.getTimeStart(),flight.getTimeArrive());
            reserveffController.setFlight(flight);
            reserveffController.setUser(user);

            reserveffController.setPrices(String.valueOf(pa),String.valueOf(pb),String.valueOf(p));

        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneResff(ActionEvent event, String fxmlFile, String  title, String username,String dis,String d,String p,String ch,String rd,Flight flight,User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            ReservefController reservefController = loader.getController();
            reservefController.setUserName(username);
            reservefController.setFlightDet(dis);
            reservefController.setFlightDat(d);
            reservefController.setPrices(p);
            reservefController.setFlight(flight);
            reservefController.setChildren(ch);
            reservefController.setRadio(rd);
            reservefController.setUser(user);

        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneResfff(ActionEvent event, String fxmlFile, String  title, String username,String dis,String d,String p,String ch,String rd,Flight flight,User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            ReserveffController reserveffController = loader.getController();
            reserveffController.setUserName(username);
            reserveffController.setFlightDet1(dis);
            reserveffController.setFlightDat1(d);
            reserveffController.setPrices1(p);
            reserveffController.setChildren(ch);
            reserveffController.setRadio(rd);
            reserveffController.setFlight(flight);
            reserveffController.setUser(user);


        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeScenePay1(ActionEvent event, String fxmlFile, String  title, User user,Flight flight,Reservation reservation){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            PayController payController = loader.getController();
            payController.setTicketNumber(reservation.getTicketNum());
            payController.setTotalPrice(reservation.getTotalPrice());
            payController.setDetails(user.getChildren(),0,0);
            payController.setUser(user);
            payController.setOuser(null);
            payController.setReservation(reservation);
            payController.setFlight(flight);
            payController.setEventRes(event);

        }catch(IOException e){
            e.printStackTrace();
        }


        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        Image icon = new Image(DBUtils.class.getResourceAsStream("images/airplane4.png"));
        stage.getIcons().add(icon);
        stage.showAndWait();
    }
    public static void changeScenePay2(ActionEvent event, String fxmlFile, String  title, User user, User ouser,Flight flight,Reservation reservation){
        Parent root = null;

        int OU=0;
        int OUC = 0;
        DBC dbc = new DBC();
        Connection connection = dbc.getConnection();
            PreparedStatement checkOUser = null;
            ResultSet resultSet = null;
            try {
                checkOUser = connection.prepareStatement("SELECT U_password, U_FULLNAME, U_GENDER, U_ADDRESS, U_AGE, U_CHILDREN, U_PHONE FROM _USER WHERE U_username = ?");
                checkOUser.setString(1,ouser.getUsername());
                resultSet = checkOUser.executeQuery();

                if(!resultSet.isBeforeFirst()){
                    System.out.println("Other User not found in the database!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials for Other User are incorrect!");
                    alert.show();
                }
                else {
                    while(resultSet.next()){
                        String retrievedPasswoed = resultSet.getString(1);
                        if(retrievedPasswoed.equals(ouser.getPassword())){
                            ouser.setFullname(resultSet.getString(2));
                            ouser.setGender(resultSet.getString(3));
                            ouser.setAddress(resultSet.getString(4));
                            ouser.setAge(resultSet.getInt(5));
                            ouser.setPhone(resultSet.getInt(7));
                            douser = ouser;
                            OU=1;
                            OUC = ouser.getChildren();
                            try {
                                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                                root = loader.load();
                                PayController payController = loader.getController();
                                payController.setTicketNumber(reservation.getTicketNum());
                                payController.setTotalPrice(reservation.getTotalPrice());
                                payController.setDetails(user.getChildren(),OU,OUC);
                                payController.setUser(user);
                                payController.setOuser(ouser);
                                payController.setReservation(reservation);
                                payController.setFlight(flight);
                                payController.setEventRes(event);

                            }catch(IOException e){
                                e.printStackTrace();
                            }

                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setResizable(false);
                            stage.setTitle(title);
                            stage.setScene(new Scene(root));
                            Image icon = new Image(DBUtils.class.getResourceAsStream("images/airplane4.png"));
                            stage.getIcons().add(icon);
                            stage.showAndWait();
                        } else {
                            System.out.println("Other User Password did not match!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Provided Other User credentials are incorrect!");
                            alert.show();
                        }
                    }
                }

            } catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                if(resultSet != null){
                    try{
                        resultSet.close();
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                if(checkOUser != null){
                    try{
                        checkOUser.close();
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                if(connection != null){
                    try{
                        connection.close();
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }

    }

    public static void payAndres(ActionEvent event,User user,Reservation reservation,User otheruser, Flight flight){
        DBC dbc = new DBC();
        Connection connection = dbc.getConnection();
        PreparedStatement getUserId = null;
        PreparedStatement resInsert = null;
        PreparedStatement makeInsert = null;
        PreparedStatement makeInsert2 = null;
        PreparedStatement getOUserId = null;
        PreparedStatement getResId = null;
        ResultSet resultSetOUId = null;
        ResultSet resultSetUId = null;
        ResultSet resultSetResId = null;
        PreparedStatement updUChildren = null;
        PreparedStatement updOUChildren = null;

        int uid = 0;
        int rid = 0;

        try {
            updUChildren = connection.prepareStatement("Update _USER set U_CHILDREN = ? where U_username = ?");
            updUChildren.setInt(1,user.getChildren());
            updUChildren.setString(2,user.getUsername());
            updUChildren.executeUpdate();

            resInsert = connection.prepareStatement("Insert Into RESERVATION VALUES (?, ?, ?, ?, ?, ?)");
            resInsert.setInt(1,reservation.getTicketNum());
            resInsert.setDouble(2,reservation.getTotalPrice());
            resInsert.setString(3,reservation.getDate());
            resInsert.setInt(4,flight.getIdFlight());
            resInsert.setString(5,reservation.getCl());
            resInsert.setString(6,reservation.getDestination());
            resInsert.executeUpdate();

            getUserId = connection.prepareStatement("select U_ID from _USER where U_username = ?");
            getUserId.setString(1, user.getUsername());
            resultSetUId = getUserId.executeQuery();
            while (resultSetUId.next()){
                uid = resultSetUId.getInt(1);
            }

            getResId = connection.prepareStatement("select TOP 1 RES_ID from RESERVATION order by RES_ID DESC");
            resultSetResId = getResId.executeQuery();
            while (resultSetResId.next()){
                rid = resultSetResId.getInt(1);
            }

            makeInsert = connection.prepareStatement("Insert Into MAKE VALUES (?, ?)");
            makeInsert.setInt(1,uid);
            makeInsert.setInt(2,rid);
            makeInsert.executeUpdate();

            if(otheruser!=null){
                updOUChildren = connection.prepareStatement("Update _USER set U_CHILDREN = ? where U_username = ?");
                updOUChildren.setInt(1,otheruser.getChildren());
                updOUChildren.setString(2,otheruser.getUsername());
                updOUChildren.executeUpdate();

                int ouid = 0;
                getOUserId = connection.prepareStatement("select U_ID from _USER where U_username = ?");
                getOUserId.setString(1, otheruser.getUsername());
                resultSetOUId = getOUserId.executeQuery();
                while (resultSetOUId.next()){
                    ouid = resultSetOUId.getInt(1);
                }
                makeInsert2 = connection.prepareStatement("Insert Into MAKE VALUES (?, ?)");
                makeInsert2.setInt(1,ouid);
                makeInsert2.setInt(2,rid);
                makeInsert2.executeUpdate();
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSetUId != null) {
                try {
                    resultSetUId.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(resultSetOUId != null){
                try {
                    resultSetOUId.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(getUserId != null){
                try {
                    getUserId.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(getOUserId != null){
                try {
                    getOUserId.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(makeInsert != null){
                try {
                    makeInsert.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(makeInsert2 != null){
                try {
                    makeInsert2.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(updUChildren != null){
                try{
                    updUChildren.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(updOUChildren != null){
                try{
                    updOUChildren.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(resInsert != null){
                try {
                    resInsert.close();
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
    public static void signUpUser(ActionEvent event, User user){
        DBC dbc = new DBC();
        Connection connection = dbc.getConnection();
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;


        try {

            psCheckUserExists = connection.prepareStatement("SELECT * FROM _USER WHERE U_username = ?");
            psCheckUserExists.setString(1,user.getUsername());
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else{
                duser = user;
                psInsert = connection.prepareStatement("INSERT INTO _USER VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                psInsert.setString(1,user.getUsername());
                psInsert.setString(2,user.getPassword());
                psInsert.setString(3,user.getFullname());
                psInsert.setString(4,user.getGender());
                psInsert.setString(5,user.getAddress());
                psInsert.setInt(6,user.getAge());
                psInsert.setInt(7,user.getChildren());
                psInsert.setInt(8,user.getPhone());
                psInsert.executeUpdate();

                changeSceneS(event,"logged-in1.fxml","Flights", user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
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

    public static void logInUser(ActionEvent event, String username, String password){
        DBC dbc = new DBC();
        Connection connection = dbc.getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT U_password, U_FULLNAME, U_GENDER, U_ADDRESS, U_AGE, U_CHILDREN, U_PHONE FROM _USER WHERE U_username = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while(resultSet.next()){
                    String retrievedPasswoed = resultSet.getString(1);

                    if(retrievedPasswoed.equals(password)){
                        User user = new User(username,retrievedPasswoed,resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7));
                        duser = user;
                        changeSceneU(event, "logged-in1.fxml","Flights",user);
                    } else {
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
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
}
