package com.quartermanagement.Controller.SoHoKhau;

import com.quartermanagement.Model.SoHoKhau;
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
import java.util.concurrent.ThreadLocalRandom;
import java.util.ResourceBundle;

import static com.quartermanagement.Constants.DBConstants.*;
import static com.quartermanagement.Utils.Utils.LOCAL_DATE;
import static com.quartermanagement.Utils.Utils.createDialog;

public class SoHoKhauDetailViewController implements Initializable {
    @FXML
    private TextField maHoKhauTextField;

    @FXML
    private TextField diaChiTextField;

    @FXML
    private TextField maChuHoTextField;

    @FXML
    private DatePicker ngayLapDatePicker;

    @FXML
    private DatePicker ngayChuyenDiDatePicker;

    @FXML
    private TextField lyDoChuyenTextField;

    @FXML
    private Button add_btn;
    @FXML
    private Button update_btn;
    @FXML
    private Text title;

    @FXML
    private Pane maHoKhauPane;

    public void setSoHoKhau(SoHoKhau soHoKhau) {
        maHoKhauTextField.setText(String.valueOf(soHoKhau.getMaHoKhau()));
        diaChiTextField.setText(soHoKhau.getDiaChi());
        maChuHoTextField.setText(String.valueOf(soHoKhau.getMaChuHo()));
        ngayLapDatePicker.setValue(LOCAL_DATE(soHoKhau.getNgayLap()));
        ngayChuyenDiDatePicker.setValue(LOCAL_DATE(soHoKhau.getNgayChuyenDi()));
        lyDoChuyenTextField.setText(soHoKhau.getLyDoChuyen());

    }

    public void goBack(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.switchToSoHoKhau_Admin_view(event);
    }

    public void update (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maHoKhau = maHoKhauTextField.getText();
        String diaChi = diaChiTextField.getText();
        String maChuHo = maChuHoTextField.getText();
        String ngayLap = ngayLapDatePicker.getValue().toString();
        String ngayChuyenDi = ngayChuyenDiDatePicker.getValue().toString();
        String lyDoChuyen = lyDoChuyenTextField.getText();


        if (maHoKhau.trim().equals("") || diaChi.trim().equals("") || maChuHo.trim().equals("") || ngayLap.trim().equals("") ||
        ngayChuyenDi.trim().equals("") || lyDoChuyen.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!"
            );
        } else {
            try {
                Connection conn;
                PreparedStatement preparedStatement;
                String UPDATE_QUERY ="UPDATE sohokhau SET `MaHoKhau`=?, `DiaChi`=?, `MaChuHo`=?, `NgayLap`=? , `NgayChuyenDi`=?, `LyDoChuyen` =? WHERE `MaHoKhau`=?";
                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement((UPDATE_QUERY));
                preparedStatement.setString(1, maHoKhau);
                preparedStatement.setString(2, diaChi);
                preparedStatement.setString(3, maChuHo);
                preparedStatement.setString(4, ngayLap);
                preparedStatement.setString(5, ngayChuyenDi);
                preparedStatement.setString(6, lyDoChuyen);
                preparedStatement.setString(7, maHoKhau);

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
            viewUtils.switchToSoHoKhau_Admin_view(event);
        }
    }

    public void addnew (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maHoKhau;
        String diaChi = diaChiTextField.getText();
        String maChuHo = maChuHoTextField.getText();
        String ngayLap = ngayLapDatePicker.getValue().toString();
        String ngayChuyenDi = ngayChuyenDiDatePicker.getValue().toString();
        String lyDoChuyen = lyDoChuyenTextField.getText();

        if (diaChi.trim().equals("") || maChuHo.trim().equals("")
                || ngayLap.trim().equals("") || ngayChuyenDi.trim().equals("") || lyDoChuyen.trim().equals("")) {

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
                    maHoKhau = String.valueOf(rand);
                    PreparedStatement check = conn.prepareStatement("SELECT MaHoKhau From sohokhau WHERE `MAHoKhau` =?");
                    check.setInt(1, rand);
                    rs = check.executeQuery();
                } while (rs.next());

                String INSERT_QUERY = "INSERT INTO sohokhau VALUES(?,?,?,?,?,?)";
                preparedStatement = conn.prepareStatement((INSERT_QUERY));
                preparedStatement.setString(1, maHoKhau);
                preparedStatement.setString(2, diaChi);
                preparedStatement.setString(3, maHoKhau);
                preparedStatement.setString(4, ngayLap);
                preparedStatement.setString(5, ngayChuyenDi);
                preparedStatement.setString(6, lyDoChuyen);

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
            viewUtils.switchToSoHoKhau_Admin_view(event);
        }
    }

    public void hide_add_btn() {
        add_btn.setVisible(false);
    }

    public void hide_pane() {
        maHoKhauPane.setVisible(false);
    }


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

