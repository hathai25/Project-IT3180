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
    private TableColumn<NhanKhau, String> biDanhColumn;
    @FXML
    private TableColumn<NhanKhau, String> ngaySinhColumn;
    @FXML
    private TableColumn<NhanKhau, String> cccdColumn;
    @FXML
    private TableColumn<NhanKhau, String> noiSinhColumn;
    @FXML
    private TableColumn<NhanKhau, String> gioiTinhColumn;
    @FXML
    private TableColumn<NhanKhau, String> nguyenQuanColumn;
    @FXML
    private TableColumn<NhanKhau, String> danTocColumn;
    @FXML
    private TableColumn<NhanKhau, String> noiThuongTruColumn;
    @FXML
    private TableColumn<NhanKhau, String> tonGiaoColumn;
    @FXML
    private TableColumn<NhanKhau, String> quocTichColumn;
    @FXML
    private TableColumn<NhanKhau, String> diaChiHienNayColumn;
    @FXML
    private TableColumn<NhanKhau, String> ngheNghiepColumn;
    @FXML
    private TableColumn<NhanKhau, Integer> maHoKhauColumn;

    private ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList();
    // Connect to database
    private Connection conn;
    private PreparedStatement preparedStatement = null;
    private final String DATABASE = "jdbc:mysql://localhost:3306/quan_ly_to",
            USERNAME="root", PASSWORD = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sttColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("STT"));
        hoVaTenColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("HoTen"));
        biDanhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("BiDanh"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("NgaySinh"));
        cccdColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("CCCD"));
        noiSinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("NoiSinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("GioiTinh"));
        nguyenQuanColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("NguyenQuan"));
        danTocColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("DanToc"));
        noiThuongTruColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("NoiThuongTru"));
        tonGiaoColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("TonGiao"));
        quocTichColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("QuocTich"));
        diaChiHienNayColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("DiaChiHienNay"));
        ngheNghiepColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("NgheNghiep"));
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

    public void add (ActionEvent event) throws IOException {
//        // change scene and get new information
//        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("detail-view.fxml"));
//        Parent studentViewParent = loader.load();
//        Scene scene = new Scene(studentViewParent);
//        DetailViewController controller = loader.getController();
//        stage.setScene(scene);
//
//        //
//        NhanKhau nhan_khau_moi = new NhanKhau();
//        nhan_khau_moi.setSTT(Integer.parseInt(controller.getSttTextField().getText()));
//        nhan_khau_moi.setHoTen(controller.getHoVaTenTextField().getText());
//        nhan_khau_moi.setBiDanh(controller.getBiDanhTextField().getText());
//        nhan_khau_moi.setNgaySinh(controller.getNgaySinhTextField().getText());
//        nhan_khau_moi.setCCCD(controller.getCccdTextField().getText());
//        nhan_khau_moi.setNoiSinh(controller.getNoiSinhTextField().getText());
//        nhan_khau_moi.setGioiTinh(controller.getGioiTinhTextField().getText());
//        nhan_khau_moi.setNguyenQuan(controller.getNguyenQuanTextField().getText());
//        nhan_khau_moi.setDanToc(controller.getDanTocTextField().getText());
//        nhan_khau_moi.setNoiThuongTru(controller.getNoiThuongTruTextField().getText());
//        nhan_khau_moi.setTonGiao(controller.getTonGiaoTextField().getText());
//        nhan_khau_moi.setQuocTich(controller.getQuocTichTextField().getText());
//        nhan_khau_moi.setDiaChiHienNay(controller.getDiaChiHienNayTextField().getText());
//        nhan_khau_moi.setNgheNghiep(controller.getNgheNghiepTextField().getText());
//        nhan_khau_moi.setMaHoKhau(Integer.parseInt(controller.getMaHoKhauTextField().getText()));
//        nhanKhauList.add(nhan_khau_moi);
//        // Add in Database
//        try {
//            String INSERT_QUERY = "INSERT INTO nhankhau VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//
//            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
//            preparedStatement = conn.prepareStatement(INSERT_QUERY);
//            ResultSet result = preparedStatement.executeQuery();
//        } catch (SQLException e) {}
        Utils utils = new Utils();
        utils.changeScene(event, "detail-view.fxml");
    }


    public void delete (ActionEvent event){
        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        nhanKhauList.remove(selected);
        // Delete in Database
        try {
            String DELETE_QUERY = "DELETE FROM nhankhau WHERE `CCCD`= ?";
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, selected.getCCCD());
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
