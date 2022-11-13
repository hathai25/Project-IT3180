module comz.quartermanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.prefs;


    opens com.quartermanagement to javafx.fxml;
    exports com.quartermanagement;
}