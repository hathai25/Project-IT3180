package com.quartermanagement.Controller.LichHoatDong;

import com.quartermanagement.Model.LichHoatDong;
import com.quartermanagement.Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import static com.quartermanagement.Constants.DBConstants.*;
import static com.quartermanagement.Utils.Utils.createDialog;

public class LichHoatDongDetailController implements Initializable {
    @FXML
    private Button add_btn;

    @FXML
    private TextField endTimeTextField;

    @FXML
    private Pane maHoatDongPane;

    @FXML
    private TextField maHoatDongTextField;

    @FXML
    private TextField startTimeTextField;

    @FXML
    private TextField statusTextField;

    @FXML
    private TextField tenHoatDongTextField;

    @FXML
    private Text title;

    @FXML
    private Button update_btn;

    public void setLichHoatDong(LichHoatDong lichHoatDong) {
        maHoatDongTextField.setText(String.valueOf(lichHoatDong.getMaHoatDong()));
        tenHoatDongTextField.setText(lichHoatDong.getTenHoatDong());
        startTimeTextField.setText(String.valueOf(lichHoatDong.getStartTime()));
        endTimeTextField.setText(String.valueOf(lichHoatDong.getEndTime()));
        statusTextField.setText(String.valueOf(lichHoatDong.getStatus()));
    }

    public void goBack (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.switchToLichHoatDong_Admin_view(event);
    }

    public void update (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maHoatDong = maHoatDongTextField.getText();
        String tenHoatDong = tenHoatDongTextField.getText();
        String startTime = startTimeTextField.getText();
        String endTime = endTimeTextField.getText();
        String status = statusTextField.getText();


        if (maHoatDong.trim().equals("") || tenHoatDong.trim().equals("") || startTime.trim().equals("") || endTime.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!"
            );
        }
        else {
            try {
                Connection conn;
                PreparedStatement preparedStatement;
                String UPDATE_QUERY ="UPDATE lichhoatdong SET `MaHoatDong`=?, `TenHoatDong`=?, `ThoiGianBatDau`=?, `ThoiGianKetThuc`=?, `DuocDuyet`=? WHERE `MaHoatDong`=?";
                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement((UPDATE_QUERY));
                preparedStatement.setString(1, maHoatDong);
                preparedStatement.setString(2, tenHoatDong);
                preparedStatement.setString(3, startTime);
                preparedStatement.setString(4, endTime);
                preparedStatement.setString(5, status);

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
            viewUtils.switchToLichHoatDong_Admin_view(event);
        }
    }

    public void addnew (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maHoatDong;
        String tenHoatDong = tenHoatDongTextField.getText();
        String startTime = startTimeTextField.getText();
        String endTime = endTimeTextField.getText();
        String status = statusTextField.getText();
        if (tenHoatDong.trim().equals("") ||startTime.trim().equals("") || endTime.trim().equals("") || status.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!"
            );
        } else {
            try {
                Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                PreparedStatement preparedStatement;
                ResultSet rs;
                do {
                    int rand = ThreadLocalRandom.current().nextInt(100000, 999999);
                    maHoatDong = String.valueOf(rand);
                    PreparedStatement check = conn.prepareStatement("SELECT MaHoatDong From lichhoatdong WHERE `MaHoatDong` =?");
                    check.setInt(1, rand);
                    rs = check.executeQuery();
                } while (rs.next());

                String INSERT_QUERY = "INSERT INTO lichhoatdong VALUES(?,?,?,?,?)";
                preparedStatement = conn.prepareStatement((INSERT_QUERY));
                preparedStatement.setString(1, maHoatDong);
                preparedStatement.setString(2, tenHoatDong);
                preparedStatement.setString(3, startTime);
                preparedStatement.setString(4, endTime);
                preparedStatement.setString(5, status);

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
            viewUtils.switchToLichHoatDong_Admin_view(event);
        }
    }

    public void hide_add_btn() { add_btn.setVisible(false); }

    public void hide_update_btn() {
        update_btn.setVisible(false);
        add_btn.setTranslateX(100);
    }

    public void hide_Pane() {
        maHoatDongPane.setVisible(false);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
