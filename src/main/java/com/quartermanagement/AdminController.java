package com.quartermanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class AdminController implements Initializable {
    @FXML
    private Button signUpUserButton;
    @FXML
    private AnchorPane basePane;
    private Preferences userPreferences = Preferences.userRoot();
    private String userRole = userPreferences.get("role", "");

    private Utils utils = new Utils();
    public void switchToSignUp() throws IOException {
        utils.changeAnchorPane(basePane, "sign-up-user-view.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(userRole);
        if (userRole.equals("totruong")) {
            signUpUserButton.setVisible(true);
        } else {
            signUpUserButton.setVisible(false);
        }
    }
}
