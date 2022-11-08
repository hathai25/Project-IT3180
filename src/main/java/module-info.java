module com.quartermanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.quartermanagement to javafx.fxml;
    exports com.quartermanagement;
}