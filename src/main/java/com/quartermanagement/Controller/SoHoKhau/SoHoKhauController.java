package com.quartermanagement.Controller.SoHoKhau;

import com.quartermanagement.Model.SoHoKhau;
import com.quartermanagement.Utils.ViewUtils;
import javafx.scene.control.Pagination;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.quartermanagement.Constants.DBConstants.*;
import static com.quartermanagement.Constants.FXMLConstants.SO_HO_KHAU_VIEW_FXML;
import static com.quartermanagement.Constants.FXMLConstants.DETAIL_SO_HO_KHAU_VIEW_FXML;
import static com.quartermanagement.Utils.Utils.createDialog;
import static com.quartermanagement.Utils.Utils.convertDate;


public class SoHoKhauController implements Initializable {

    @FXML
    private Pagination pagination;

    @FXML
    private AnchorPane basePane;

    @FXML
    private TableView<SoHoKhau> tableView;
    @FXML
    private TableColumn indexColumn;
    @FXML
    private TableColumn<SoHoKhau, Integer> maChuHoColumn;

    @FXML
    private TableColumn<SoHoKhau, String> diaChiColumn;
    @FXML
    private TableColumn<SoHoKhau, Integer> maHoKhauColumn;

    @FXML
    TableColumn<SoHoKhau, String> ngayLapColumn, ngayChuyenDiColumn, lyDoChuyenColumn;

    private ObservableList<SoHoKhau> soHoKhauList = FXCollections.observableArrayList();
    // Connect to database
    private Connection conn;
    private PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Connecting Database
            String SELECT_QUERY = "SELECT * FROM sohokhau";
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(SELECT_QUERY);
            ResultSet result = preparedStatement.executeQuery();

            // Loop the list of sohokhau
            while (result.next()) {
                soHoKhauList.add(new SoHoKhau(result.getInt("maHoKhau"),
                        result.getString("diaChi"), result.getInt("maChuHo"), convertDate(result.getString("ngayLap")),
                        convertDate(result.getString("ngayChuyenDi")), result.getString("liDoChuyen")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int soDu = soHoKhauList.size() % ROWS_PER_PAGE;
        if (soDu != 0) pagination.setPageCount(soHoKhauList.size() / ROWS_PER_PAGE + 1);
        else pagination.setPageCount(soHoKhauList.size() / ROWS_PER_PAGE);
        pagination.setMaxPageIndicatorCount(5);
        pagination.setPageFactory(this::createTableView);
    }


    public void add(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(DETAIL_SO_HO_KHAU_VIEW_FXML));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        SoHoKhauDetailViewController controller = loader.getController();
        controller.hide_update_btn();
        controller.hide_pane();
        stage.setScene(scene);
    }

    public void delete(ActionEvent event) {
        SoHoKhau selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) createDialog(Alert.AlertType.WARNING,
                "Cảnh báo",
                "Vui lòng chọn hộ khẩu để tiếp tục", "");
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa hộ khẩu");
            alert.setContentText("Đồng chí muốn xóa hộ khẩu này?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    try {
                        String DELETE_QUERY = "DELETE FROM sohokhau WHERE `MaHoKhau`= ?";
                        conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                        preparedStatement = conn.prepareStatement(DELETE_QUERY);
                        preparedStatement.setString(1, String.valueOf(selected.getMaHoKhau()));
                        int result = preparedStatement.executeUpdate();
                        if (result == 1)
                            createDialog(Alert.AlertType.INFORMATION, "Xoá thành công!", "", "Quá đơn giản phải không đồng chí?");
                        else createDialog(Alert.AlertType.WARNING, "Thông báo", "", "Oops, mời đồng chí thử lại!");
                        ViewUtils viewUtils = new ViewUtils();
                        viewUtils.changeAnchorPane(basePane, SO_HO_KHAU_VIEW_FXML);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    public void detail(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(DETAIL_SO_HO_KHAU_VIEW_FXML));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        SoHoKhauDetailViewController controller = loader.getController();
        SoHoKhau selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) createDialog(Alert.AlertType.WARNING, "Từ từ đã đồng chí", "", "Vui lòng chọn hộ khẩu");
        else {
            controller.setSoHoKhau(selected);
            controller.hide_add_btn();
            controller.setTitle("Cập nhật hộ khẩu mới");
            stage.setScene(scene);
        }
    }

    public Node createTableView(int pageIndex) {

        indexColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SoHoKhau, SoHoKhau>, ObservableValue<SoHoKhau>>) p -> new ReadOnlyObjectWrapper(p.getValue()));
        indexColumn.setCellFactory(new Callback<TableColumn<SoHoKhau, SoHoKhau>, TableCell<SoHoKhau, SoHoKhau>>() {
            @Override
            public TableCell<SoHoKhau, SoHoKhau> call(TableColumn<SoHoKhau, SoHoKhau> param) {
                return new TableCell<SoHoKhau, SoHoKhau>() {
                    @Override
                    protected void updateItem(SoHoKhau item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + 1 + pageIndex * ROWS_PER_PAGE + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

        indexColumn.setSortable(false);
        maHoKhauColumn.setCellValueFactory(new PropertyValueFactory<SoHoKhau, Integer>("maHoKhau"));
        diaChiColumn.setCellValueFactory((new PropertyValueFactory<SoHoKhau, String>("diaChi")));
        maChuHoColumn.setCellValueFactory(new PropertyValueFactory<SoHoKhau, Integer>("maChuHo"));
        ngayLapColumn.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("ngayLap"));
        ngayChuyenDiColumn.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("ngayChuyenDi"));
        lyDoChuyenColumn.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("lyDoChuyen"));
        int lastIndex = 0;
        int displace = soHoKhauList.size() % ROWS_PER_PAGE;
        if (displace > 0) {
            lastIndex = soHoKhauList.size() / ROWS_PER_PAGE;
        } else {
            lastIndex = soHoKhauList.size() / ROWS_PER_PAGE - 1;
        }
        if (lastIndex == pageIndex && displace > 0) {
            tableView.setItems(FXCollections.observableArrayList(soHoKhauList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + displace)));
        } else {
            tableView.setItems(FXCollections.observableArrayList(soHoKhauList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + ROWS_PER_PAGE)));
        }
        return tableView;
    }
}


