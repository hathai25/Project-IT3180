package com.quartermanagement.Controller;

import com.quartermanagement.Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static com.quartermanagement.Constants.FXMLConstants.*;

public class AdminController implements Initializable {
    @FXML
    private AnchorPane basePane;
    @FXML
    private Button signUpUserButton;
    //Save user role
    private static final Preferences userPreferences = Preferences.userRoot();
    public static final String userRole = userPreferences.get("role", "");
    private final ViewUtils viewUtils = new ViewUtils();

    public void switchToSignUp() throws IOException {
        viewUtils.changeAnchorPane(basePane, SIGN_UP_USER_VIEW_FXML);
    }

    public void switchToNhanKhau() throws IOException {
        viewUtils.changeAnchorPane(basePane, NHAN_KHAU_VIEW_FXML);
    }

    public void switchToCoSoVatChat() throws IOException {
        viewUtils.changeAnchorPane(basePane, CO_SO_VAT_CHAT_VIEW_FXML);
    }
    public void switchToSoHoKhau() throws IOException {
        viewUtils.changeAnchorPane(basePane, SO_HO_KHAU_VIEW_FXML);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpUserButton.setVisible(userRole.equals("totruong"));
    }

    public void switchToLichHoatDong() throws IOException {
        viewUtils.changeAnchorPane(basePane, LICH_HOAT_DONG_VIEW_FXML);
    }
}
