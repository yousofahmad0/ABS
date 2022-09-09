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

public class DBUtilsAdmin {
    private static User duser;
    private static User douser;



    public static void changeScene(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        if(user != null){
            try {
                FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInAdminController loggedInAdminController = loader.getController();
                loggedInAdminController.setUserName(user.getUsername());
                loggedInAdminController.setUser(user);
            }catch(IOException e){
                e.printStackTrace();
            }
        } else{
            try {
                root = FXMLLoader.load(DBUtilsAdmin.class.getResource(fxmlFile));
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

        try {
                FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
                root = loader.load();
                UsersController usersController = loader.getController();
                usersController.setUserName(user.getUsername());
                usersController.setUser(user);
            }catch(IOException e){
                e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneF(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
            root = loader.load();
            FlightsController flightsController = loader.getController();
            flightsController.setUserName(user.getUsername());
            flightsController.setUser(user);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneA(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
            root = loader.load();
            AirlinesController airlinesController = loader.getController();
            airlinesController.setUserName(user.getUsername());
            airlinesController.setUser(user);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneM(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
            root = loader.load();
            MakesController makesController = loader.getController();
            makesController.setUserName(user.getUsername());
            makesController.setUser(user);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneR(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
            root = loader.load();
            ReservController reservController = loader.getController();
            reservController.setUserName(user.getUsername());
            reservController.setUser(user);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneP(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
            root = loader.load();
            PassengersController passengersController = loader.getController();
            passengersController.setUserName(user.getUsername());
            passengersController.setUser(user);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void changeSceneUTA(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
            root = loader.load();
            UpdateTicAmController updateTicAmController = loader.getController();
            updateTicAmController.setUserName(user.getUsername());
            updateTicAmController.setUser(user);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneAIF(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
            root = loader.load();
            AIFController aifController = loader.getController();
            aifController.setUserName(user.getUsername());
            aifController.setUser(user);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneTN1R(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
            root = loader.load();
            TNORController tnorController = loader.getController();
            tnorController.setUserName(user.getUsername());
            tnorController.setUser(user);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneTNPF(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
            root = loader.load();
            TNPFController tnpfController = loader.getController();
            tnpfController.setUserName(user.getUsername());
            tnpfController.setUser(user);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void changeSceneWELR(ActionEvent event, String fxmlFile, String  title, User user){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtilsAdmin.class.getResource(fxmlFile));
            root = loader.load();
            WELRController welrController = loader.getController();
            welrController.setUserName(user.getUsername());
            welrController.setUser(user);
        }catch(IOException e){
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static boolean procUTA(int Resid,int newAm){
        DBCAdmin dbc = new DBCAdmin();
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = null;
        try{
            callableStatement = connection.prepareCall("exec updateTic_Amount ?, ?");
            //callableStatement.registerOutParameter(1,);
            callableStatement.setInt(1,Resid);
            callableStatement.setInt(2,newAm);
            callableStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            if(callableStatement != null){
                try{
                    callableStatement.close();
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
        return true;
    }

    public static String functAIF(int Fno){
        String mess = null;
        DBCAdmin dbc = new DBCAdmin();
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = null;
        try{
            callableStatement = connection.prepareCall("{? = call AbsentsInFlight(?)}");
            callableStatement.registerOutParameter(1,Types.VARCHAR);
            callableStatement.setInt(2,Fno);
            callableStatement.execute();
            mess = callableStatement.getString(1);
        } catch (SQLException e){
            e.printStackTrace();
            return mess;
        } finally {
            if(callableStatement != null){
                try{
                    callableStatement.close();
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
        return mess;
    }

    public static String functTNOR(int Resid){
        String mess = null;
        DBCAdmin dbc = new DBCAdmin();
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = null;
        try{
            callableStatement = connection.prepareCall("{? = call totalMembersFor1Reserv(?)}");
            callableStatement.registerOutParameter(1,Types.INTEGER);
            callableStatement.setInt(2,Resid);
            callableStatement.execute();
            mess = "This Total number of members for this Reservation is "+callableStatement.getInt(1)+".";
        } catch (SQLException e){
            e.printStackTrace();
            return mess;
        } finally {
            if(callableStatement != null){
                try{
                    callableStatement.close();
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
        return mess;
    }
    public static String functWER(int Resid){
        String mess = null;
        DBCAdmin dbc = new DBCAdmin();
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = null;
        try{
            callableStatement = connection.prepareCall("{? = call whoElseReserv(?)}");
            callableStatement.registerOutParameter(1,Types.INTEGER);
            callableStatement.setInt(2,Resid);
            callableStatement.execute();
            mess = "This Reservation belong for "+callableStatement.getInt(1)+" User.";
        } catch (SQLException e){
            e.printStackTrace();
            return mess;
        } finally {
            if(callableStatement != null){
                try{
                    callableStatement.close();
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
        return mess;
    }

    public static String functTNPF(int Fno){
        String mess = null;
        DBCAdmin dbc = new DBCAdmin();
        Connection connection = dbc.getConnection();
        CallableStatement callableStatement = null;
        try{
            callableStatement = connection.prepareCall("{? = call totalNumberOfPassenger(?)}");
            callableStatement.registerOutParameter(1,Types.VARCHAR);
            callableStatement.setInt(2,Fno);
            callableStatement.execute();
            mess = callableStatement.getString(1);

        } catch (SQLException e){
            e.printStackTrace();
            return mess;
        } finally {
            if(callableStatement != null){
                try{
                    callableStatement.close();
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
        return mess;
    }


    public static void logInUser(ActionEvent event, String username, String password){
        DBCAdmin dbc = new DBCAdmin();
        Connection connection = dbc.getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT U_password, U_FULLNAME, U_GENDER, U_ADDRESS, U_AGE, U_CHILDREN, U_PHONE FROM _USER WHERE U_username = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("Admin not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while(resultSet.next()){
                    String retrievedPasswoed = resultSet.getString(1);

                    if(retrievedPasswoed.equals(password)){
                        User user = new User(username,retrievedPasswoed,resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7));
                        duser = user;
                        changeScene(event, "logged-in-admin.fxml","Dashboard",user);
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
