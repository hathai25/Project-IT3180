package com.quartermanagement.Controller.LichHoatDong;

import com.quartermanagement.Model.LichHoatDong;
import com.quartermanagement.Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import static com.quartermanagement.Constants.DBConstants.*;
import static com.quartermanagement.Utils.Utils.*;
import static com.quartermanagement.Controller.AdminController.userRole;

public class LichHoatDongDetailController implements Initializable {
    @FXML
    private Button add_btn;
    @FXML
    private TextField endTimeTextField;
    @FXML
    private TextField startTimeTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Pane maHoatDongPane;
    @FXML
    private TextField maHoatDongTextField;
    @FXML
    private ChoiceBox<String> statusChoiceBox;
    @FXML
    private Text statusText;
    @FXML
    private TextField tenHoatDongTextField;
    @FXML
    private TextField maNguoiTaoTextField;
    @FXML
    private Pane statusPane;

    @FXML
    private Text title;
    @FXML
    private Text maNguoiTaoText;
    @FXML
    private Button update_btn;

    public void setLichHoatDong(LichHoatDong lichHoatDong) {
        maHoatDongTextField.setText(String.valueOf(lichHoatDong.getMaHoatDong()));
        tenHoatDongTextField.setText(lichHoatDong.getTenHoatDong());
        String startTime = lichHoatDong.getStartTime();
        String [] starttime = startTime.split(" ");
        startDatePicker.setValue(LOCAL_DATE(starttime[1]));
        startTimeTextField.setText(starttime[0]);
        String endTime = lichHoatDong.getEndTime();
        String [] endtime = endTime.split(" ");
        endDatePicker.setValue(LOCAL_DATE(endtime[1]));
        endTimeTextField.setText(endtime[0]);
        statusChoiceBox.setValue(String.valueOf(lichHoatDong.getStatus()));
        maNguoiTaoTextField.setText(String.valueOf(lichHoatDong.getMaNguoiTao()));
    }

    public void goBack (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.switchToLichHoatDong_Admin_view(event);
    }

    public void update (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maHoatDong = maHoatDongTextField.getText();
        String tenHoatDong = tenHoatDongTextField.getText();
        String startDateTime = startDatePicker.getValue().toString();
        String startTime = startTimeTextField.getText();
        String starttime = startDateTime + " " + startTime;
        String endDateTime = endDatePicker.getValue().toString();
        String endTime = endTimeTextField.getText();
        String endtime = endDateTime + " " + endTime;
        String status = statusChoiceBox.getValue();
        String maNguoiTao = maNguoiTaoTextField.getText();


        if (maHoatDong.trim().equals("") || tenHoatDong.trim().equals("") || startTime.trim().equals("") || endTime.trim().equals("") || maNguoiTao.trim().equals("")
                || startDateTime.trim().equals("") || endDateTime.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!"
            );
        } else {
            if (isValidTime(startTime) && isValidTime(endTime)) {
                createDialog(Alert.AlertType.WARNING, "Từ từ thôi đồng chí!", "Hãy chọn đúng định dạng", "");
            } else {
                try {
                    Connection conn;
                    PreparedStatement preparedStatement;
                    String UPDATE_QUERY = "UPDATE lichhoatdong SET `MaHoatDong`=?, `TenHoatDong`=?, `ThoiGianBatDau`=?, `ThoiGianKetThuc`=?, `DuocDuyet`=?, `MaNguoiTao`=? WHERE `MaHoatDong`=?";
                    conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                    preparedStatement = conn.prepareStatement((UPDATE_QUERY));
                    preparedStatement.setString(1, maHoatDong);
                    preparedStatement.setString(2, tenHoatDong);
                    preparedStatement.setString(3, starttime);
                    preparedStatement.setString(4, endtime);
                    preparedStatement.setString(5, status);
                    preparedStatement.setString(6, maNguoiTao);
                    preparedStatement.setString(7, maHoatDong);

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
    }

    public void addnew (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maHoatDong;
        String tenHoatDong = tenHoatDongTextField.getText();
        String startDateTime = startDatePicker.getValue().toString();
        String startTime = startTimeTextField.getText();
        String starttime = startDateTime + " " + startTime;
        String endDateTime = endDatePicker.getValue().toString();
        String endTime = endTimeTextField.getText();
        String endtime = endDateTime + " " + endTime;
        String status = "Chưa duyệt";
        String maNguoiTao = maNguoiTaoTextField.getText();
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String thoiGianTao = dtf.format(currentTime);
        if (tenHoatDong.trim().equals("") ||startTime.trim().equals("") || endTime.trim().equals("") || maNguoiTao.trim().equals("")
                || startDateTime.trim().equals("") || endDateTime.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!"
            );
        } else if(isValidTime(startTime) && isValidTime(endTime)){
            createDialog(Alert.AlertType.WARNING,"Từ từ thôi đồng chí!", "Hãy chọn đúng định dạng", "");
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

                String INSERT_QUERY = "INSERT INTO lichhoatdong VALUES(?,?,?,?,?,?,?)";
                preparedStatement = conn.prepareStatement((INSERT_QUERY));
                preparedStatement.setString(1, maHoatDong);
                preparedStatement.setString(2, tenHoatDong);
                preparedStatement.setString(3, starttime);
                preparedStatement.setString(4, endtime);
                preparedStatement.setString(5, status);
                preparedStatement.setString(6, maNguoiTao);
                preparedStatement.setString(7, thoiGianTao);

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

    public void hide_maHoatDongPane() {
        maHoatDongPane.setVisible(false);
    }

    public void hide_statusPane(){
        statusPane.setVisible(false);
    }

    public void setTitle(String title) {
       this.title.setText(title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusChoiceBox.getItems().add("Chưa duyệt");
        statusChoiceBox.getItems().add("Chấp nhận");
        statusChoiceBox.getItems().add("Từ chối");
        statusChoiceBox.setValue("Chưa duyệt");
        statusPane.setVisible(userRole.equals("totruong"));
    }


}