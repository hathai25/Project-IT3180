package com.quartermanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
public class DetailViewController {
    @FXML
    private TextField sttTextField;
    @FXML
    private TextField hoVaTenTextField;
    @FXML
    private TextField cccdTextField;
    @FXML
    private TextField gioiTinhTextField;
    @FXML
    private TextField danTocTextField;
    @FXML
    private TextField nguyenQuanTextField;
    @FXML
    private TextField maHoKhauTextField;
    public void setNhanKhau(NhanKhau nhanKhau){
        sttTextField.setText(String.valueOf(nhanKhau.getSTT()));
        hoVaTenTextField.setText(nhanKhau.getHoTen());
        cccdTextField.setText(nhanKhau.getCCCD());
        gioiTinhTextField.setText(nhanKhau.getGioiTinh());
        danTocTextField.setText(nhanKhau.getDanToc());
        nguyenQuanTextField.setText(nhanKhau.getNguyenQuan());
        maHoKhauTextField.setText(String.valueOf(nhanKhau.getMaHoKhau()));
    }
    public void goBack(ActionEvent event) throws IOException {
        Utils utils = new Utils();
        utils.changeScene(event,"admin-view.fxml");
    }


    public TextField getSttTextField() {
        return sttTextField;
    }

    public void setSttTextField(TextField sttTextField) {
        this.sttTextField = sttTextField;
    }

    public TextField getHoVaTenTextField() {
        return hoVaTenTextField;
    }

    public void setHoVaTenTextField(TextField hoVaTenTextField) {
        this.hoVaTenTextField = hoVaTenTextField;
    }

    public TextField getCccdTextField() {
        return cccdTextField;
    }

    public void setCccdTextField(TextField cccdTextField) {
        this.cccdTextField = cccdTextField;
    }

    public TextField getGioiTinhTextField() {
        return gioiTinhTextField;
    }

    public void setGioiTinhTextField(TextField gioiTinhTextField) {
        this.gioiTinhTextField = gioiTinhTextField;
    }

    public TextField getDanTocTextField() {
        return danTocTextField;
    }

    public void setDanTocTextField(TextField danTocTextField) {
        this.danTocTextField = danTocTextField;
    }

    public TextField getNguyenQuanTextField() {
        return nguyenQuanTextField;
    }

    public void setNguyenQuanTextField(TextField nguyenQuanTextField) {
        this.nguyenQuanTextField = nguyenQuanTextField;
    }

    public TextField getMaHoKhauTextField() {
        return maHoKhauTextField;
    }

    public void setMaHoKhauTextField(TextField maHoKhauTextField) {
        this.maHoKhauTextField = maHoKhauTextField;
    }
}
