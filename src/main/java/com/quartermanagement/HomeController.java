package com.quartermanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    private TextField inputUsername, inputPassword;
    //Khai bao ket noi sql
    private Connection conn;
    private PreparedStatement preparedStatement = null;
    private final String DATABASE = "jdbc:mysql://localhost:3306/mysql_db",
                    USERNAME="root", PASSWORD = "";
    //Khai bao chuyen view
    private Stage stage;
    private Scene scene;
    private Parent root = null;

    @FXML
    private Button loginButton;
    public void handleLogin(ActionEvent event) {
        String SELECT_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";
        String Username = inputUsername.getText();
        Utils utils = new Utils();
        String Password = utils.hashPassword(inputPassword.getText());
        if (Username.trim().equals("") || Password.trim().equals("")) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Cảnh báo!");
            warning.setHeaderText("Khoan nào cán bộ!");
            warning.setContentText("Vui lòng nhập đầy đủ username và password!");
            warning.show();
        }   else {
            try {
                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement(SELECT_QUERY);
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    utils.changeScene(event, "admin-view.fxml");
                }   else {
                    Alert warning = new Alert(Alert.AlertType.ERROR);
                    warning.setHeaderText("Khoan nào cán bộ!");
                    warning.setContentText("Sai username hoặc password!");
                    warning.show();
                }
            }   catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}