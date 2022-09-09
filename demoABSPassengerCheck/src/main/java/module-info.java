module com.example.demoabspassengercheck {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens com.example.demoabspassengercheck to javafx.fxml;
    exports com.example.demoabspassengercheck;
}