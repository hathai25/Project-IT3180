package com.quartermanagement.Controller.LichHoatDong;

import com.quartermanagement.Model.LichHoatDong;
import javafx.scene.control.Pagination;
import com.quartermanagement.Utils.ViewUtils;
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
import static com.quartermanagement.Constants.DBConstants.ROWS_PER_PAGE;
import static com.quartermanagement.Constants.FXMLConstants.*;
import static com.quartermanagement.Utils.Utils.*;

public class LichHoatDongController implements Initializable {
    @FXML
    private AnchorPane basePane;
    @FXML
    private TableView<LichHoatDong> tableView;
    @FXML private TableColumn indexColumn;
    @FXML
    private TableColumn<LichHoatDong, String> tenHoatDongColumn,
            startTimeColumn, endTimeColumn, statusColumn, madeTimeColumn;
    @FXML
    private TableColumn<LichHoatDong, Integer> maHoatDongColumn, maNguoiTaoColumn;
    @FXML
    private Pagination pagination;
    private ObservableList<LichHoatDong> lichHoatDongList = FXCollections.observableArrayList();
    private Connection conn;
    private PreparedStatement preparedStatement = null;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Connecting Database
            String SELECT_QUERY = "SELECT * FROM lichhoatdong";
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(SELECT_QUERY);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                lichHoatDongList.add(new LichHoatDong(result.getInt("MaHoatDong"),result.getString("TenHoatDong"),
                        convertTime(result.getString("ThoiGianBatDau")), convertTime(result.getString("ThoiGianKetThuc")),
                        result.getString("DuocDuyet"), convertTime(result.getString("ThoiGianTao")),
                                result.getInt("MaNguoiTao")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int soDu = lichHoatDongList.size() % ROWS_PER_PAGE;
        if (soDu != 0) pagination.setPageCount(lichHoatDongList.size() / ROWS_PER_PAGE + 1);
        else pagination.setPageCount(lichHoatDongList.size() / ROWS_PER_PAGE);
        pagination.setMaxPageIndicatorCount(5);
        pagination.setPageFactory(this::createTableView);
    }

    public void add(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(DETAIL_LICH_HOAT_DONG_VIEW_FXML));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        LichHoatDongDetailController controller = loader.getController();
        controller.hide_update_btn();
        controller.hide_statusPane();
        controller.hide_maHoatDongPane();
        stage.setScene(scene);
    }

    public void delete() {
        LichHoatDong selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) createDialog(Alert.AlertType.WARNING,
                "C???nh b??o",
                "", "?????ng ch?? vui l??ng ch???n 1 m???c ????? ti???p t???c");
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("X??c nh???n xo?? ");
            alert.setContentText("?????ng ch?? mu???n xo?? n???i dung n??y?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    try {
                        String DELETE_QUERY = "DELETE FROM lichhoatdong WHERE `MaHoatDong`= ?";
                        conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                        preparedStatement = conn.prepareStatement(DELETE_QUERY);
                        preparedStatement.setString(1, String.valueOf(selected.getMaHoatDong()));
                        int result = preparedStatement.executeUpdate();
                        if (result == 1)
                            createDialog(Alert.AlertType.INFORMATION, "Xo?? th??nh c??ng!", "", "Qu?? ????n gi???n ph???i kh??ng ?????ng ch???");
                        else createDialog(Alert.AlertType.WARNING, "Th??ng b??o", "", "Oops, m???i ?????ng ch?? th??? l???i!");
                        ViewUtils viewUtils = new ViewUtils();
                        viewUtils.changeAnchorPane(basePane, LICH_HOAT_DONG_VIEW_FXML);
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
        loader.setLocation(getClass().getResource(DETAIL_LICH_HOAT_DONG_VIEW_FXML));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        LichHoatDongDetailController controller = loader.getController();
        LichHoatDong selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) createDialog(Alert.AlertType.WARNING, "T??? t??? ???? ?????ng ch??", "", "Vui l??ng ch???n 1 m???c ????? ti???p t???c");
        else {
            controller.setLichHoatDong(selected);
            controller.hide_add_btn();
            controller.hide_maHoatDongPane();
            controller.setTitle("C???p nh???t l???ch hoat ?????ng");
            stage.setScene(scene);
        }
    }

    public Node createTableView(int pageIndex) {

        indexColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<LichHoatDong, LichHoatDong>, ObservableValue<LichHoatDong>>) p -> new ReadOnlyObjectWrapper(p.getValue()));
        indexColumn.setCellFactory(new Callback<TableColumn<LichHoatDong, LichHoatDong>, TableCell<LichHoatDong, LichHoatDong>>() {
            @Override
            public TableCell<LichHoatDong, LichHoatDong> call(TableColumn<LichHoatDong, LichHoatDong> param) {
                return new TableCell<LichHoatDong, LichHoatDong>() {
                    @Override
                    protected void updateItem(LichHoatDong item, boolean empty) {
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
        maHoatDongColumn.setCellValueFactory(new PropertyValueFactory<LichHoatDong, Integer>("maHoatDong"));
        tenHoatDongColumn.setCellValueFactory(new PropertyValueFactory<LichHoatDong, String>("tenHoatDong"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<LichHoatDong, String>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<LichHoatDong, String>("endTime"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<LichHoatDong, String>("status"));
        madeTimeColumn.setCellValueFactory(new PropertyValueFactory<LichHoatDong, String>("madeTime"));
        maNguoiTaoColumn.setCellValueFactory(new PropertyValueFactory<LichHoatDong, Integer>("maNguoiTao"));

        int lastIndex = 0;
        int displace = lichHoatDongList.size() % ROWS_PER_PAGE;
        if (displace > 0) {
            lastIndex = lichHoatDongList.size() / ROWS_PER_PAGE;
        } else {
            lastIndex = lichHoatDongList.size() / ROWS_PER_PAGE - 1;
        }
        if (lastIndex == pageIndex && displace > 0) {
            tableView.setItems(FXCollections.observableArrayList(lichHoatDongList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + displace)));
        } else {
            tableView.setItems(FXCollections.observableArrayList(lichHoatDongList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + ROWS_PER_PAGE)));
        }
        return tableView;
    }

}
