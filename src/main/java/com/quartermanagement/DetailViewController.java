package com.quartermanagement;

import com.quartermanagement.model.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import static com.quartermanagement.DBConstants.*;

public class DetailViewController {
    @FXML
    private TextField hoVaTenTextField;
    @FXML
    private TextField biDanhTextField;
    @FXML
    private TextField ngaySinhTextField;
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
    private Button add_btn ;
    @FXML
    private Button update_btn;

    public void setNhanKhau(NhanKhau nhanKhau){
        hoVaTenTextField.setText(nhanKhau.getHoTen());
        biDanhTextField.setText(nhanKhau.getBiDanh());
        ngaySinhTextField.setText(nhanKhau.getNgaySinh());
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
        try {
            Connection conn;
            PreparedStatement preparedStatement = null;
            String UPDATE_QUERY = "UPDATE `nhankhau` SET `HoTen`=?,`BiDanh`=?,`NgaySinh`=?,`CCCD`=?,`NoiSinh`=?," +
                    "`GioiTinh`=?,`NguyenQuan`=?,`DanToc`=?,`NoiThuongTru`=?,`TonGiao`=?,`QuocTich`=?,`DiaChiHienNay`=?," +
                    "`NgheNghiep`=?,`MaHoKhau`=? WHERE `CCCD`=?";
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, hoVaTenTextField.getText());
            preparedStatement.setString(2, biDanhTextField.getText());
            preparedStatement.setString(3, ngaySinhTextField.getText());
            preparedStatement.setString(4, cccdTextField.getText());
            preparedStatement.setString(5, noiSinhTextField.getText());
            preparedStatement.setString(6, gioiTinhTextField.getText());
            preparedStatement.setString(7, nguyenQuanTextField.getText());
            preparedStatement.setString(8, danTocTextField.getText());
            preparedStatement.setString(9, noiThuongTruTextField.getText());
            preparedStatement.setString(10, tonGiaoTextField.getText());
            preparedStatement.setString(11, quocTichTextField.getText());
            preparedStatement.setString(12, diaChiHienNayTextField.getText());
            preparedStatement.setString(13, ngheNghiepTextField.getText());
            preparedStatement.setString(14, maHoKhauTextField.getText());
            preparedStatement.setString(15, cccdTextField.getText());
            int result = preparedStatement.executeUpdate();
            System.out.println(UPDATE_QUERY);



            if (result == 1) {
                System.out.println("OKE");
            }
            else System.out.println("KO OKE");

            conn.close();
        } catch (SQLException e) {}
        Utils utils = new Utils();
        utils.changeScene(event,"admin-view.fxml");
    }

    public void addnew(ActionEvent event) throws IOException {
        try {
            Connection conn;
            PreparedStatement preparedStatement = null;
            String INSERT_QUERY = "INSERT INTO nhankhau VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, hoVaTenTextField.getText());
            preparedStatement.setString(2, biDanhTextField.getText());
            preparedStatement.setString(3, ngaySinhTextField.getText());
            preparedStatement.setString(4, cccdTextField.getText());
            preparedStatement.setString(5, noiSinhTextField.getText());
            preparedStatement.setString(6, gioiTinhTextField.getText());
            preparedStatement.setString(7, nguyenQuanTextField.getText());
            preparedStatement.setString(8, danTocTextField.getText());
            preparedStatement.setString(9, noiThuongTruTextField.getText());
            preparedStatement.setString(10, tonGiaoTextField.getText());
            preparedStatement.setString(11, quocTichTextField.getText());
            preparedStatement.setString(12, diaChiHienNayTextField.getText());
            preparedStatement.setString(13, ngheNghiepTextField.getText());
            preparedStatement.setString(14, maHoKhauTextField.getText());
            int result = preparedStatement.executeUpdate();
            System.out.println(INSERT_QUERY);

            if (result == 1) {
                System.out.println("OKE");
            }
            else System.out.println("KO OKE");

            conn.close();
        } catch (SQLException e) {}
        Utils utils = new Utils();
        utils.changeScene(event,"admin-view.fxml");
    }

    public void hide_add_btn(){
        add_btn.setVisible(false);
        update_btn.setVisible(true);
    }

    public void hide_update_btn(){
        add_btn.setVisible(true);
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

    public TextField getNgaySinhTextField() {
        return ngaySinhTextField;
    }

    public void setNgaySinhTextField(TextField ngaySinhTextField) {
        this.ngaySinhTextField = ngaySinhTextField;
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
}
