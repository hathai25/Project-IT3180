package com.quartermanagement.Controller;

import com.quartermanagement.Utils.Utils;
import com.quartermanagement.Utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static com.quartermanagement.Constants.FXMLConstants.NHAN_KHAU_VIEW_FXML;
import static com.quartermanagement.Constants.FXMLConstants.SIGN_UP_USER_VIEW_FXML;

public class AdminController implements Initializable {
    @FXML
    private AnchorPane basePane;
    @FXML
    private Button signUpUserButton, nhanKhauButton;

    //Save user role
    private Preferences userPreferences = Preferences.userRoot();
    private String userRole = userPreferences.get("role", "");
    private ViewUtils viewUtils = new ViewUtils();

    public void switchToSignUp() throws IOException {
        viewUtils.changeAnchorPane(basePane, SIGN_UP_USER_VIEW_FXML);
    }

    public void switchToNhanKhau() throws IOException {
        viewUtils.changeAnchorPane(basePane, NHAN_KHAU_VIEW_FXML);
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
