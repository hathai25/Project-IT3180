package com.quartermanagement;

import com.quartermanagement.model.NhanKhau;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import static com.quartermanagement.DBConstants.*;

public class AdminController implements Initializable {
    @FXML
    private TableView<NhanKhau> tableView;
    @FXML
    private TableColumn indexColumn;
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
    @FXML
    private Button signUpUserButton;
    @FXML
    private AnchorPane basePane;
    private ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList();
    // Connect to database
    private Connection conn;
    private PreparedStatement preparedStatement = null;
    //Save user role
    private Preferences userPreferences = Preferences.userRoot();
    private String userRole = userPreferences.get("role", "");
    private Utils utils = new Utils();

    public void switchToSignUp() throws IOException {
        utils.changeAnchorPane(basePane, "sign-up-user-view.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (userRole.equals("totruong")) {
            signUpUserButton.setVisible(true);
        } else {
            signUpUserButton.setVisible(false);
        }

        indexColumn.setCellValueFactory((Callback<CellDataFeatures<NhanKhau, NhanKhau>, ObservableValue<NhanKhau>>) p -> new ReadOnlyObjectWrapper(p.getValue()));

        indexColumn.setCellFactory(new Callback<TableColumn<NhanKhau, NhanKhau>, TableCell<NhanKhau, NhanKhau>>() {
            @Override public TableCell<NhanKhau, NhanKhau> call(TableColumn<NhanKhau, NhanKhau> param) {
                return new TableCell<NhanKhau, NhanKhau>() {
                    @Override protected void updateItem(NhanKhau item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex()+1+"");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        indexColumn.setSortable(false);
        hoVaTenColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("HoTen"));
        biDanhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("BiDanh"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("NgaySinh"));
        cccdColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("CCCD"));
        noiSinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("NoiSinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("GioiTinh"));
        nguyenQuanColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("NguyenQuan"));
        danTocColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("DanToc"));
        noiThuongTruColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("NoiThuongTru"));
        tonGiaoColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("TonGiao"));
        quocTichColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("QuocTich"));
        diaChiHienNayColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("DiaChiHienNay"));
        ngheNghiepColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("NgheNghiep"));
        maHoKhauColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("MaHoKhau"));
        try {
            // Connecting Database
            String SELECT_QUERY = "SELECT * FROM nhankhau";
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(SELECT_QUERY);
            ResultSet result = preparedStatement.executeQuery();

            // Loop the list of nhankhau
            while (result.next()) {
                nhanKhauList.add(new NhanKhau(result.getString("HoTen"), result.getString("BiDanh"),
                        Utils.convertDate(result.getString("NgaySinh")), result.getString("CCCD"), result.getString("NoiSinh"),
                        result.getString("GioiTinh"), result.getString("NguyenQuan"), result.getString("DanToc"),
                        result.getString("NoiThuongTru"), result.getString("TonGiao"), result.getString("QuocTich"),
                        result.getString("DiaChiHienNay"), result.getString("NgheNghiep"), result.getInt("MaHoKhau")
                ));
            }
            // Add nhankhau to table
            tableView.setItems(nhanKhauList);
        } catch (SQLException e) {
        }
    }

    public void add(ActionEvent event) throws IOException {
        Utils utils = new Utils();
        utils.changeScene(event, "detail-view.fxml");
    }


    public void delete(ActionEvent event) {
        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        nhanKhauList.remove(selected);
        // Delete in Database
        try {
            String DELETE_QUERY = "DELETE FROM nhankhau WHERE `CCCD`= ?";
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, selected.getCCCD());
            int result = preparedStatement.executeUpdate();
            if (result == 1) System.out.println("OKE");
            else System.out.println("KO OKE");
            System.out.println(DELETE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void detail(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("detail-view.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        DetailViewController controller = loader.getController();
        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        controller.setNhanKhau(selected);
        //utils.changeAnchorPane(basePane, "detail-view.fxml");
        stage.setScene(scene);
    }
}
