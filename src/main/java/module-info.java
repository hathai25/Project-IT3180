module com.quartermanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.quartermanagement to javafx.fxml;
    exports com.quartermanagement;
}