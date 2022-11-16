package com.quartermanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class AdminController implements Initializable {
    @FXML
    private AnchorPane basePane;
    @FXML
    private Button signUpUserButton, nhanKhauButton;

    //Save user role
    private Preferences userPreferences = Preferences.userRoot();
    private String userRole = userPreferences.get("role", "");
    private Utils utils = new Utils();

    public void switchToSignUp() throws IOException {
        utils.changeAnchorPane(basePane, "sign-up-user-view.fxml");
    }

    public void switchToNhanKhau() throws IOException {
       utils.changeAnchorPane(basePane, "nhankhau-view.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (userRole.equals("totruong")) {
            signUpUserButton.setVisible(true);
        } else {
            signUpUserButton.setVisible(false);
        }
    }
}
