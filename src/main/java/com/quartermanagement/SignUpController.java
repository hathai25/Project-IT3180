package com.quartermanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.quartermanagement.DBConstants.*;

public class SignUpController implements Initializable {
    @FXML
    private TextField signUpUsername, signUpPassword;
    @FXML
    private RadioButton isAdmin, isOfficer;
    private ToggleGroup toggleRole = new ToggleGroup();
    private Connection conn;
    private PreparedStatement preparedStatement = null;

    public void handleSignUp(ActionEvent event) {
        Utils utils = new Utils();
        String inputUsername = signUpUsername.getText();
        String inputPassword = signUpPassword.getText();
        String role = "";

        if (inputUsername.trim().equals("") || inputPassword.trim().equals("")) {
            utils.createDialog(
                    Alert.AlertType.WARNING,
                    "Khoan nào cán bộ",
                    "", "Vui lòng nhập đủ username và password!"
            );

        }   else {
            if (!isOfficer.isSelected() && !isAdmin.isSelected()) {
                utils.createDialog(
                        Alert.AlertType.WARNING,
                        "Khoan nào cán bộ",
                        "", "Vui lòng chọn role cho username!"
                );
            }   else {
                if (isOfficer.isSelected()) role = "canbo";
                if (isAdmin.isSelected()) role = "totruong";
                String CREATE_QUERY = "INSERT INTO user (username, password, role) VALUES (?,?,?)";
                try {
                    conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                    preparedStatement = conn.prepareStatement(CREATE_QUERY);
                    preparedStatement.setString(1, inputUsername);
                    preparedStatement.setString(2, utils.hashPassword(inputPassword));
                    preparedStatement.setString(3, role);
                    int result = preparedStatement.executeUpdate();
                    if (result == 1) {
                        signUpPassword.clear();
                        signUpUsername.clear();
                        isAdmin.setSelected(false);
                        isOfficer.setSelected(false);
                        utils.createDialog(
                                Alert.AlertType.CONFIRMATION,
                                "Thành công",
                                "", "Đăng ký người dùng mới thành công!"
                        );
                    }   else {
                        utils.createDialog(
                                Alert.AlertType.ERROR,
                                "Thất bại",
                                "", "Đăng ký người dùng mới thất bại!"
                        );
                    }
                }   catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isAdmin.setToggleGroup(toggleRole);
        isOfficer.setToggleGroup(toggleRole);
    }
}
