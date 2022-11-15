package com.quartermanagement;

import com.quartermanagement.model.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

import static com.quartermanagement.DBConstants.*;

public class DetailViewController {
    @FXML
    private TextField hoVaTenTextField;
    @FXML
    private TextField biDanhTextField;
    @FXML
    private DatePicker ngaySinhDatePicker;
    @FXML
    private TextField cccdTextField;
    @FXML
    private TextField noiSinhTextField;
    @FXML
    private TextField gioiTinhTextField;
    @FXML
    private TextField nguyenQuanTextField;
    @FXML
    private TextField danTocTextField;
    @FXML
    private TextField noiThuongTruTextField;
    @FXML
    private TextField tonGiaoTextField;
    @FXML
    private TextField quocTichTextField;
    @FXML
    private TextField diaChiHienNayTextField;
    @FXML
    private TextField ngheNghiepTextField;
    @FXML
    private TextField maHoKhauTextField;
    @FXML
    private Button add_btn;
    @FXML
    private Button update_btn;
    @FXML
    private Text title;

    public void setNhanKhau(NhanKhau nhanKhau) {
        hoVaTenTextField.setText(nhanKhau.getHoTen());
        biDanhTextField.setText(nhanKhau.getBiDanh());
        ngaySinhDatePicker.setValue(Utils.LOCAL_DATE(nhanKhau.getNgaySinh()));
        cccdTextField.setText(nhanKhau.getCCCD());
        noiSinhTextField.setText(nhanKhau.getNoiSinh());
        gioiTinhTextField.setText(nhanKhau.getGioiTinh());
        nguyenQuanTextField.setText(nhanKhau.getNguyenQuan());
        danTocTextField.setText(nhanKhau.getDanToc());
        noiThuongTruTextField.setText(nhanKhau.getNoiThuongTru());
        tonGiaoTextField.setText(nhanKhau.getTonGiao());
        quocTichTextField.setText(nhanKhau.getQuocTich());
        diaChiHienNayTextField.setText(nhanKhau.getDiaChiHienNay());
        ngheNghiepTextField.setText(nhanKhau.getNgheNghiep());
        maHoKhauTextField.setText(String.valueOf(nhanKhau.getMaHoKhau()));
    }
    public void goBack(ActionEvent event) throws IOException {
        Utils utils = new Utils();
        utils.changeScene(event, "admin-view.fxml");
    }



    public void update(ActionEvent event) throws IOException {
        Utils utils = new Utils();
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

        if (hoVaTen.trim().equals("") || biDanh.trim().equals("") || ngaySinh.trim().equals("") || cccd.trim().equals("") ||
                noiSinh.trim().equals("") || gioiTinh.trim().equals("") || nguyenQuan.trim().equals("") || danToc.trim().equals("") ||
                noiThuongTru.trim().equals("") || tonGiao.trim().equals("") || quocTich.trim().equals("") || diaChiHienNay.trim().equals("") ||
                ngheNghiep.trim().equals("") || maHoKhau.trim().equals("")) {

            utils.createDialog(
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
                    utils.createDialog(
                            Alert.AlertType.CONFIRMATION,
                            "Thành công",
                            "", "Đồng chí vất vả rồi!"
                    );
                } else {
                    utils.createDialog(
                            Alert.AlertType.ERROR,
                            "Thất bại",
                            "", "Oops, mời đồng chí nhập lại thông tin!"
                    );
                }

                conn.close();
            } catch (SQLException e) {
            }
            utils.changeScene(event, "admin-view.fxml");
        }
    }

    public void addnew(ActionEvent event) throws IOException {
        Utils utils = new Utils();
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
        if (hoVaTen.trim().equals("") || biDanh.trim().equals("") || ngaySinh.trim().equals("") || cccd.trim().equals("") ||
                noiSinh.trim().equals("") || gioiTinh.trim().equals("") || nguyenQuan.trim().equals("") || danToc.trim().equals("") ||
                noiThuongTru.trim().equals("") || tonGiao.trim().equals("") || quocTich.trim().equals("") || diaChiHienNay.trim().equals("") ||
                ngheNghiep.trim().equals("") || maHoKhau.trim().equals("")) {

            utils.createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!")
            ;
        } else {
            try {
                Connection conn;
                PreparedStatement preparedStatement = null;
                String INSERT_QUERY = "INSERT INTO nhankhau VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement(INSERT_QUERY);
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
                int result = preparedStatement.executeUpdate();
                System.out.println(INSERT_QUERY);
                if (result == 1) {
                    utils.createDialog(
                            Alert.AlertType.CONFIRMATION,
                            "Thành công",
                            "", "Đồng chí vất cả rồi!"
                    );
                } else {
                    utils.createDialog(
                            Alert.AlertType.ERROR,
                            "Thất bại",
                            "", "Oops, mời đồng chí nhập lại thông tin!"
                    );
                }

                conn.close();
            } catch (SQLException e) {
            }
            utils.changeScene(event, "admin-view.fxml");
        }
    }

    public void hide_add_btn() {
        add_btn.setVisible(false);
    }

    public void hide_update_btn() {
        update_btn.setVisible(false);
        add_btn.setTranslateX(100);
    }

    // Getter and setter methods for all
    public TextField getHoVaTenTextField() {
        return hoVaTenTextField;
    }

    public void setHoVaTenTextField(TextField hoVaTenTextField) {
        this.hoVaTenTextField = hoVaTenTextField;
    }

    public TextField getBiDanhTextField() {
        return biDanhTextField;
    }

    public void setBiDanhTextField(TextField biDanhTextField) {
        this.biDanhTextField = biDanhTextField;
    }


    public TextField getCccdTextField() {
        return cccdTextField;
    }

    public void setCccdTextField(TextField cccdTextField) {
        this.cccdTextField = cccdTextField;
    }

    public TextField getNoiSinhTextField() {
        return noiSinhTextField;
    }

    public void setNoiSinhTextField(TextField noiSinhTextField) {
        this.noiSinhTextField = noiSinhTextField;
    }

    public TextField getGioiTinhTextField() {
        return gioiTinhTextField;
    }

    public void setGioiTinhTextField(TextField gioiTinhTextField) {
        this.gioiTinhTextField = gioiTinhTextField;
    }

    public TextField getNguyenQuanTextField() {
        return nguyenQuanTextField;
    }

    public void setNguyenQuanTextField(TextField nguyenQuanTextField) {
        this.nguyenQuanTextField = nguyenQuanTextField;
    }

    public TextField getDanTocTextField() {
        return danTocTextField;
    }

    public void setDanTocTextField(TextField danTocTextField) {
        this.danTocTextField = danTocTextField;
    }

    public TextField getNoiThuongTruTextField() {
        return noiThuongTruTextField;
    }

    public void setNoiThuongTruTextField(TextField noiThuongTruTextField) {
        this.noiThuongTruTextField = noiThuongTruTextField;
    }

    public TextField getTonGiaoTextField() {
        return tonGiaoTextField;
    }

    public void setTonGiaoTextField(TextField tonGiaoTextField) {
        this.tonGiaoTextField = tonGiaoTextField;
    }

    public TextField getQuocTichTextField() {
        return quocTichTextField;
    }

    public void setQuocTichTextField(TextField quocTichTextField) {
        this.quocTichTextField = quocTichTextField;
    }

    public TextField getDiaChiHienNayTextField() {
        return diaChiHienNayTextField;
    }

    public void setDiaChiHienNayTextField(TextField diaChiHienNayTextField) {
        this.diaChiHienNayTextField = diaChiHienNayTextField;
    }

    public TextField getNgheNghiepTextField() {
        return ngheNghiepTextField;
    }

    public void setNgheNghiepTextField(TextField ngheNghiepTextField) {
        this.ngheNghiepTextField = ngheNghiepTextField;
    }

    public TextField getMaHoKhauTextField() {
        return maHoKhauTextField;
    }

    public void setMaHoKhauTextField(TextField maHoKhauTextField) {
        this.maHoKhauTextField = maHoKhauTextField;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
