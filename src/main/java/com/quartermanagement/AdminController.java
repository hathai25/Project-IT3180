package com.quartermanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    String SELECT_QUERY = "SELECT * FROM nhankhau";


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
}
