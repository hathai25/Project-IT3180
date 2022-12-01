package com.quartermanagement.Controller.SoHoKhau;


import com.quartermanagement.Model.SoHoKhau;
import com.quartermanagement.Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

import static com.quartermanagement.Constants.DBConstants.*;

import static com.quartermanagement.Utils.Utils.createDialog;

public class add_shk_controller {
    @FXML
    private TextField maChuHoTextField;

    @FXML
    private TextField diaChiTextField;

    @FXML
    private TextField maHoKhauTextField;
    @FXML
    private Button add_btn;
    @FXML
    private Button update_btn;
    @FXML
    private Text title;

    public void setSoHoKhau(SoHoKhau soHoKhau) {
        maChuHoTextField.setText(soHoKhau.getMaChuHo());
        diaChiTextField.setText(soHoKhau.getDiaChi());
        maHoKhauTextField.setText(String.valueOf(soHoKhau.getMaHoKhau()));
    }

    public void goBack(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.switchToSoHoKhau_Admin_view(event);
    }

    public void hide_add_btn() {
        add_btn.setVisible(false);
    }

    public void hide_update_btn() {
        update_btn.setVisible(false);
        add_btn.setTranslateX(100);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }


    public void addnew(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();

        String maChuHo = maChuHoTextField.getText();
        String diaChi = diaChiTextField.getText();
        String maHoKhau = maHoKhauTextField.getText();

        if (maChuHo.trim().equals("") || diaChi.trim().equals("") || maHoKhau.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!")
            ;
        } else {
            try {
                Connection conn;
                PreparedStatement preparedStatement = null;
                String INSERT_QUERY = "INSERT INTO sohokhau VALUES(?,?,?)";

                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement(INSERT_QUERY);
                preparedStatement.setString(1, maHoKhau);
                preparedStatement.setString(2, diaChi);
                preparedStatement.setString(3, maChuHo);
                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    createDialog(
                            Alert.AlertType.CONFIRMATION,
                            "Thành công",
                            "", "Đồng chí vất cả rồi!"
                    );
                    //          swtich to admin-sohokhau-view
                    viewUtils.switchToSoHoKhau_Admin_view(event);
                } else {
                    createDialog(
                            Alert.AlertType.ERROR,
                            "Thất bại",
                            "", "Oops, mời đồng chí nhập lại thông tin!"
                    );
                }

                conn.close();
            } catch (SQLException e) {
            }

        }
    }

    public void update(ActionEvent event) {
    }
   /* public void update(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String hoVaTen = hoVaTenTextField.getText();
        String biDanh = biDanhTextField.getText();
        String ngaySinh = ngaySinhDatePicker.getValue().toString();
        String cccd = cccdTextField.getText();
        String noiSinh = noiSinhTextField.getText();
        String gioiTinh = gioiTinhTextField.getText();
        String nguyenQuan = nguyenQuanTextField.getText();
        String danToc = danTocTextField.getText();
        String noiThuongTru = noiThuongTruTextField.getText();
        String tonGiao = tonGiaoTextField.getText();
        String quocTich = quocTichTextField.getText();
        String diaChiHienNay = diaChiHienNayTextField.getText();
        String ngheNghiep = ngheNghiepTextField.getText();
        String maHoKhau = maHoKhauTextField.getText();

        if (hoVaTen.trim().equals("") || ngaySinh.trim().equals("") || cccd.trim().equals("") ||
                noiSinh.trim().equals("") || gioiTinh.trim().equals("") || nguyenQuan.trim().equals("") || danToc.trim().equals("") ||
                noiThuongTru.trim().equals("") || diaChiHienNay.trim().equals("") || maHoKhau.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!")
            ;
        } else {
            try {
                Connection conn;
                PreparedStatement preparedStatement = null;
                String UPDATE_QUERY = "UPDATE `nhankhau` SET `HoTen`=?,`BiDanh`=?,`NgaySinh`=?,`CCCD`=?,`NoiSinh`=?," +
                        "`GioiTinh`=?,`NguyenQuan`=?,`DanToc`=?,`NoiThuongTru`=?,`TonGiao`=?,`QuocTich`=?,`DiaChiHienNay`=?," +
                        "`NgheNghiep`=?,`MaHoKhau`=? WHERE `CCCD`=?";
                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement(UPDATE_QUERY);
                preparedStatement.setString(1, hoVaTen);
                preparedStatement.setString(2, biDanh);
                preparedStatement.setString(3, ngaySinh);
                preparedStatement.setString(4, cccd);
                preparedStatement.setString(5, noiSinh);
                preparedStatement.setString(6, gioiTinh);
                preparedStatement.setString(7, nguyenQuan);
                preparedStatement.setString(8, danToc);
                preparedStatement.setString(9, noiThuongTru);
                preparedStatement.setString(10, tonGiao);
                preparedStatement.setString(11, quocTich);
                preparedStatement.setString(12, diaChiHienNay);
                preparedStatement.setString(13, ngheNghiep);
                preparedStatement.setString(14, maHoKhau);
                preparedStatement.setString(15, cccd);

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
                            "", "Oops, mời đồng chí nhập lại thông tin!"
                    );
                }

                conn.close();
            } catch (SQLException e) {
            }
//          swtich to admin-nhankhau-view
            viewUtils.switchToNhanKhau_Admin_view(event);
        }
    }
    */


    // Getter and setter methods for all

    public TextField getMaChuHoTextField() {
        return maChuHoTextField;
    }

    public void setMaChuHoTextField(TextField maChuHoTextField) {
        this.maChuHoTextField = maChuHoTextField;
    }

    public TextField getDiaChiTextField() {
        return diaChiTextField;
    }

    public void setDiaChiTextField(TextField diaChiTextField) {
        this.diaChiTextField = diaChiTextField;
    }

    public TextField getMaHoKhauTextField() {
        return maHoKhauTextField;
    }

    public void setMaHoKhauTextField(TextField maHoKhauTextField) {
        this.maHoKhauTextField = maHoKhauTextField;
    }

    public Button getAdd_btn() {
        return add_btn;
    }

    public void setAdd_btn(Button add_btn) {
        this.add_btn = add_btn;
    }

    public Button getUpdate_btn() {
        return update_btn;
    }

    public void setUpdate_btn(Button update_btn) {
        this.update_btn = update_btn;
    }

    public Text getTitle() {
        return title;
    }

    public void setTitle(Text title) {
        this.title = title;
    }


}
