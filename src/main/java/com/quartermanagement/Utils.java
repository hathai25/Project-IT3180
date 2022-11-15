package com.quartermanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Utils {
    public String hashPassword(String password) {
        String generatedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16));
            }
            generatedPassword = sb.toString();
        }   catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public void changeScene(ActionEvent event, String viewSource) throws IOException {
        Stage stage;
        Scene scene;
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewSource));
        root = loader.load();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void changeAnchorPane(AnchorPane currentPane, String viewSource) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource(viewSource));
        currentPane.getChildren().setAll(node);
    }

    public void createDialog(Alert.AlertType type, String title, String headerText, String contentText) {
        Alert warning = new Alert(type);
        warning.setTitle(title);
        warning.setHeaderText(headerText);
        warning.setContentText(contentText);
        warning.show();
    }

}

