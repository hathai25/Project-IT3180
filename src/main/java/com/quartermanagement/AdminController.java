package com.quartermanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Button buttonMenu;
    @FXML
    private TableView<NhanKhau> tableView;
    @FXML
    private TableColumn<NhanKhau, Integer> sttColumn;
    @FXML
    private TableColumn<NhanKhau, String> hoVaTenColumn;
    @FXML
    private TableColumn<NhanKhau, String> cccdColumn;
    @FXML
    private TableColumn<NhanKhau, String> gioiTinhColumn;
    @FXML
    private TableColumn<NhanKhau, String> danTocColumn;
    @FXML
    private TableColumn<NhanKhau, String> nguyenQuanColumn;
    @FXML
    private TableColumn<NhanKhau, Integer> maHoKhauColumn;

    private ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList();
    // Connect to database
    private Connection conn;
    private PreparedStatement preparedStatement = null;
    private final String DATABASE = "jdbc:mysql://localhost:3306/mysql_db",
            USERNAME="root", PASSWORD = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sttColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("STT"));
        hoVaTenColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("HoTen"));
        cccdColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("CCCD"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("GioiTinh"));
        danTocColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("DanToc"));
        nguyenQuanColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("NguyenQuan"));
        maHoKhauColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("MaHoKhau"));
    try {
        // Connecting Database
        String SELECT_QUERY = "SELECT * FROM nhankhau";
        conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
        preparedStatement = conn.prepareStatement(SELECT_QUERY);
        ResultSet result = preparedStatement.executeQuery();

        // Loop the list of nhankhau
        while (result.next()) {
            nhanKhauList.add(new NhanKhau(result.getInt("STT"), result.getString("HoTen"), result.getString("BiDanh"),
                    result.getString("NgaySinh"), result.getString("CCCD"), result.getString("NoiSinh"),
                    result.getString("GioiTinh"), result.getString("NguyenQuan"), result.getString("DanToc"),
                    result.getString("NoiThuongTru"), result.getString("TonGiao"), result.getString("QuocTich"),
                    result.getString("DiaChiHienNay"), result.getString("NgheNghiep"), result.getInt("MaHoKhau")
            ));
          }
        // Add nhankhau to table
        tableView.setItems(nhanKhauList);
        }   catch (SQLException e) {}
    }

//    public void add (ActionEvent event) throws IOException {
//        // change scene and get new information
//
//        NhanKhau nhan_khau_moi = new NhanKhau();
//        nhan_khau_moi.setSTT();
//        nhan_khau_moi.setHoTen();
//        nhan_khau_moi.setBiDanh();
//        nhan_khau_moi.setNgaySinh();
//        nhan_khau_moi.setCCCD();
//        nhan_khau_moi.setNoiSinh();
//        nhan_khau_moi.setGioiTinh();
//        nhan_khau_moi.setNguyenQuan();
//        nhan_khau_moi.setDanToc();
//        nhan_khau_moi.setNoiThuongTru();
//        nhan_khau_moi.setTonGiao();
//        nhan_khau_moi.setQuocTich();
//        nhan_khau_moi.setDiaChiHienNay();
//        nhan_khau_moi.setNgheNghiep();
//        nhan_khau_moi.setMaHoKhau()
//        nhanKhauList.add(nhan_khau_moi);
//        // Add in Database
//        try {
//            String INSERT_QUERY = "INSERT INTO nhankhau VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
//            preparedStatement = conn.prepareStatement(INSERT_QUERY);
//            ResultSet result = preparedStatement.executeQuery();
//        } catch (SQLException e) {}
//    }


    public void delete (ActionEvent event){
        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        nhanKhauList.remove(selected);
        // Delete in Database
        try {
            String DELETE_QUERY = "SET FOREIGN_KEY_CHECKS=0 ; DELETE FROM nhankhau WHERE STT = ? ; SET FOREIGN_KEY_CHECKS=1;";
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, selected.getSTT());
            int result = preparedStatement.executeUpdate();
            if (result==1) System.out.println("OKE");
            else System.out.println("KO OKE");
            System.out.println(DELETE_QUERY);
        }   catch (SQLException e){}


    }
    public void detail (ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("detail-view.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        DetailViewController controller = loader.getController();
        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        controller.setNhanKhau(selected);
        stage.setScene(scene);
    }
}
