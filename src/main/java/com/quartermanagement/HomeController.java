package com.quartermanagement;
//import libs
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.prefs.Preferences;

//import constants
import static com.quartermanagement.DBConstants.*;

public class HomeController {
    @FXML
    private TextField inputUsername, inputPassword;
    //Khai bao ket noi sql
    private Connection conn;
    private PreparedStatement preparedStatement = null;

    @FXML
    private Button loginButton;

    public void handleLogin(ActionEvent event) {
        String SELECT_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";
        String Username = inputUsername.getText();
        Utils utils = new Utils();
        String Password = utils.hashPassword(inputPassword.getText());
        if (Username.trim().equals("") || Password.trim().equals("")) {
            utils.createDialog(
                    Alert.AlertType.WARNING,
                    "Cảnh báo!",
                    "Khoan nào cán bộ!",
                    "Vui lòng nhập đầy đủ username và password!"
            );
        }   else {
            try {
                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement(SELECT_QUERY);
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    Preferences userPreferences = Preferences.userRoot();
                    userPreferences.put("role", result.getString(4));
                    utils.changeScene(event, "admin-view.fxml");
                }   else {
                    utils.createDialog(
                            Alert.AlertType.ERROR,
                            "Cảnh báo!",
                            "Khoan nào cán bộ!",
                            "Sai username hoặc password!"
                    );
                }
            }   catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}