package com.quartermanagement.Controller.CoSoVatChat;

import com.quartermanagement.Model.CoSoVatChat;
import com.quartermanagement.Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.quartermanagement.Constants.DBConstants.*;
import static com.quartermanagement.Utils.Utils.createDialog;


public class CoSoVatChatDeTailController implements Initializable {
    @FXML
    private TextField maDoDungTextField;

    @FXML
    private TextField tenDoDungTextField;

    @FXML
    private TextField soLuongTextField;

    @FXML
    private TextField soLuongKhaDungTextField;

    @FXML
    private Button add_btn;

    @FXML
    private Button update_btn;

    @FXML
    private Text title;

    public void setCoSoVatChat(CoSoVatChat coSoVatChat) {
        maDoDungTextField.setText(String.valueOf(coSoVatChat.getMaDoDung()));
        tenDoDungTextField.setText(coSoVatChat.getTenDoDung());
        soLuongTextField.setText(String.valueOf(coSoVatChat.getSoLuong()));
        soLuongKhaDungTextField.setText(String.valueOf(coSoVatChat.getSoLuongKhaDung()));

    }
    public void goBack (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.switchToCoSoVatChat_Admin_view(event);
    }

    public void update (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maDoDung = maDoDungTextField.getText();
        String tenDoDung = tenDoDungTextField.getText();
        String soLuong = soLuongTextField.getText();
        String soLuongKhaDung = soLuongKhaDungTextField.getText();
        if (maDoDung.trim().equals("") || tenDoDung.trim().equals("") || soLuong.trim().equals("") || soLuongKhaDung.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!"
            );
        } else {
            try {
                Connection conn;
                PreparedStatement preparedStatement;
                String UPDATE_QUERY ="UPDATE cosovatchat SET `MaDoDung`=?, `TenDoDung`=?, `SoLuong`=?, `SoLuongKhaDung`=? WHERE `MaDoDung`=?";
                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement((UPDATE_QUERY));
                preparedStatement.setString(1, maDoDung);
                preparedStatement.setString(2, tenDoDung);
                preparedStatement.setString(3, soLuong);
                preparedStatement.setString(4, soLuongKhaDung);
                preparedStatement.setString(5, maDoDung);

                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    createDialog(
                            Alert.AlertType.CONFIRMATION,
                            "Thành công",
                            "", "Đồng chí vất vả rồi!"
                    );
                } else {
                    createDialog(
                            Alert.AlertType.ERROR,
                            "Thất bại",
                            "", "Thất bại là mẹ thành công! Mong đồng chí thử lại"
                    );
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            viewUtils.switchToCoSoVatChat_Admin_view(event);
        }
    }

    public void addnew (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maDoDung = maDoDungTextField.getText();
        String tenDoDung = tenDoDungTextField.getText();
        String soLuong = soLuongTextField.getText();
        String soLuongKhaDung = soLuongKhaDungTextField.getText();
        if (maDoDung.trim().equals("") || tenDoDung.trim().equals("") || soLuong.trim().equals("") || soLuongKhaDung.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!"
            );
        } else {
            try {
                Connection conn;
                PreparedStatement preparedStatement;
                String UPDATE_QUERY ="INSERT INTO cosovatchat VALUES(?,?,?,?)";
                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement((UPDATE_QUERY));
                preparedStatement.setString(1, maDoDung);
                preparedStatement.setString(2, tenDoDung);
                preparedStatement.setString(3, soLuong);
                preparedStatement.setString(4, soLuongKhaDung);

                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    createDialog(
                            Alert.AlertType.CONFIRMATION,
                            "Thành công",
                            "", "Đồng chí vất vả rồi!"
                    );
                } else {
                    createDialog(
                            Alert.AlertType.ERROR,
                            "Thất bại",
                            "", "Thất bại là mẹ thành công! Mong đồng chí thử lại"
                    );
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            viewUtils.switchToCoSoVatChat_Admin_view(event);
        }
    }

    public void hide_add_btn() { add_btn.setVisible(false); }

    public void hide_update_btn() {
        update_btn.setVisible(false);
        add_btn.setTranslateX(100);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
