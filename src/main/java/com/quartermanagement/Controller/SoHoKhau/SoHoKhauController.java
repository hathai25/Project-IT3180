package com.quartermanagement.Controller.SoHoKhau;
import com.quartermanagement.Model.SoHoKhau;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import static com.quartermanagement.Constants.DBConstants.*;
import static com.quartermanagement.Constants.FXMLConstants.ADD_SOHOKHAU_VIEW_FXML;
import static com.quartermanagement.Utils.Utils.createDialog;


public class SoHoKhauController implements Initializable {
    @FXML
    private TableView<SoHoKhau> tableView;
    @FXML
    private TableColumn indexColumn;
    @FXML
    private TableColumn<SoHoKhau, String> maChuHoColumn;

    @FXML
    private TableColumn<SoHoKhau, String> diaChiColumn;
    @FXML
    private TableColumn<SoHoKhau, Integer> maHoKhauColumn;
    @FXML
    private Pagination pagination;

    private ObservableList<SoHoKhau> SoHoKhauList = FXCollections.observableArrayList();
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
                SoHoKhauList.add(new SoHoKhau(result.getString("MaChuHo"),
                        result.getString("DiaChi"), result.getInt("MaHoKhau")
                ));
            }
            // Add sohokhau to table
            tableView.setItems(SoHoKhauList);
        } catch (SQLException e) {
        }

        int soDu = SoHoKhauList.size() % ROWS_PER_PAGE;
        if (soDu != 0) pagination.setPageCount(SoHoKhauList.size() / ROWS_PER_PAGE + 1);
        else pagination.setPageCount(SoHoKhauList.size() / ROWS_PER_PAGE);
        pagination.setMaxPageIndicatorCount(5);
        pagination.setPageFactory(this::createTableView);

    }


    public void add(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ADD_SOHOKHAU_VIEW_FXML));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        add_shk_controller controller = loader.getController();
        controller.hide_update_btn();
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
                    SoHoKhauList.remove(selected);
                    // Delete in Database
                    try {
                        String DELETE_QUERY = "DELETE FROM nhankhau WHERE `CCCD`= ?";
                        conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                        preparedStatement = conn.prepareStatement(DELETE_QUERY);
                        preparedStatement.setString(1, selected.getMaChuHo());
                        int result = preparedStatement.executeUpdate();
                        if (result == 1) createDialog(Alert.AlertType.INFORMATION, "Thông báo", "Xóa thành công!", "");
                        else createDialog(Alert.AlertType.WARNING, "Thông báo", "Có lỗi, thử lại sau!", "");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if (type == noButton) {
                } else {
                }
            });
        }
    }

    public void detail(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ADD_SOHOKHAU_VIEW_FXML));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        add_shk_controller controller= loader.getController();
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
                            setText(this.getTableRow().getIndex() + 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        indexColumn.setSortable(false);

        maChuHoColumn.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("MaChuHo"));

        diaChiColumn.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("DiaChi"));

        maHoKhauColumn.setCellValueFactory(new PropertyValueFactory<SoHoKhau, Integer>("MaHoKhau"));

        int lastIndex = 0;
        int displace = SoHoKhauList.size() % ROWS_PER_PAGE;
        if (displace > 0) {
            lastIndex = SoHoKhauList.size() / ROWS_PER_PAGE;
        } else {
            lastIndex = SoHoKhauList.size() / ROWS_PER_PAGE - 1;
        }
        if (lastIndex == pageIndex && displace > 0) {
            tableView.setItems(FXCollections.observableArrayList(SoHoKhauList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + displace)));
        } else {
            tableView.setItems(FXCollections.observableArrayList(SoHoKhauList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + ROWS_PER_PAGE)));
        }
        return tableView;

    }

}



    /*
    public void add(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(DETAIL_NHAN_KHAU_VIEW_FXML));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        NhanKhauDetailViewController controller = loader.getController();
        controller.hide_update_btn();
        stage.setScene(scene);
    }


    public void delete(ActionEvent event) {
        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        if(selected == null) createDialog(Alert.AlertType.WARNING,
                "Cảnh báo",
                "Vui lòng chọn nhân khẩu để tiếp tục","");
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa nhân khẩu");
            alert.setContentText("Đồng chí muốn xóa nhân khẩu này?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    SoHoKhauList.remove(selected);
                    // Delete in Database
                    try {
                        String DELETE_QUERY = "DELETE FROM nhankhau WHERE `CCCD`= ?";
                        conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                        preparedStatement = conn.prepareStatement(DELETE_QUERY);
                        preparedStatement.setString(1, selected.getCCCD());
                        int result = preparedStatement.executeUpdate();
                        if(result ==1) createDialog(Alert.AlertType.INFORMATION,"Thông báo","Xóa thành công!","");
                        else createDialog(Alert.AlertType.WARNING,"Thông báo","Có lỗi, thử lại sau!","");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if (type == noButton) {
                } else {
                }
            });
        }
    }



    public void detail(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(DETAIL_NHAN_KHAU_VIEW_FXML));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        NhanKhauDetailViewController controller = loader.getController();
        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        if(selected == null) createDialog(Alert.AlertType.WARNING, "Từ từ đã đồng chí", "","Vui lòng chọn một nhân khẩu");
        else {
            controller.setNhanKhau(selected);
            controller.hide_add_btn();
            controller.setTitle("Cập nhật nhân khẩu mới");
            stage.setScene(scene);
        }
    }

     */

