package com.example.demoabs;
import java.sql.*;

public class DBC  {

    public Connection databaselink;
    public Connection getConnection()
    {
        String host = "localhost";
        int port = 1433;
        String db_name = "ABS";
        String url;
        url="jdbc:sqlserver://" + host + ":" + port + ";database=" + db_name + ";encrypt=true;trustServerCertificate=true;";
        try{
            String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            databaselink =DriverManager.getConnection(url,"fordemo","helloyzsf");
        } catch (SQLException e) {
            e.printStackTrace();
        };
        return databaselink;
    }

    public boolean closeConnection(Connection connection){
        try{
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean closeStatment(Statement statement){
        try {
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean closeStatment(PreparedStatement preparedStatement){
        try {
            preparedStatement.close();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean closeResultSet(ResultSet resultSet){
        try {
            resultSet.close();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
