module com.example.demoabs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires com.microsoft.sqlserver.jdbc;


    opens com.example.demoabs to javafx.fxml;
    exports com.example.demoabs;
}