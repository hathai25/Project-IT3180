package com.quartermanagement.Controller.NhanKhau;

import com.quartermanagement.Controller.AdminController;
import com.quartermanagement.Utils.Utils;
import com.quartermanagement.Utils.ViewUtils;
import javafx.scene.control.Pagination;
import com.quartermanagement.Model.NhanKhau;
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
import static com.quartermanagement.Constants.FXMLConstants.NHAN_KHAU_VIEW_FXML;
import static com.quartermanagement.Utils.Utils.convertDate;
import static com.quartermanagement.Utils.Utils.createDialog;
import static com.quartermanagement.Constants.FXMLConstants.DETAIL_NHAN_KHAU_VIEW_FXML;

public class NhanKhauController implements Initializable {
    @FXML
    private AnchorPane basePane;
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
    private Pagination pagination;

    private ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList();
    // Connect to database
    private Connection conn;
    private PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Connecting Database
            String SELECT_QUERY = "SELECT * FROM nhankhau";
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(SELECT_QUERY);
            ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            nhanKhauList.add(new NhanKhau(result.getString("HoTen"), result.getString("BiDanh"),
                    convertDate(result.getString("NgaySinh")), result.getString("CCCD"), result.getString("NoiSinh"),
                    result.getString("GioiTinh"), result.getString("NguyenQuan"), result.getString("DanToc"),
                    result.getString("NoiThuongTru"), result.getString("TonGiao"), result.getString("QuocTich"),
                    result.getString("DiaChiHienNay"), result.getString("NgheNghiep"), result.getInt("MaHoKhau")
            ));
        }
        } catch (SQLException e) {
        }

        int soDu = nhanKhauList.size()% ROWS_PER_PAGE;
        if (soDu != 0) pagination.setPageCount(nhanKhauList.size()/ROWS_PER_PAGE+1);
        else pagination.setPageCount(nhanKhauList.size()/ROWS_PER_PAGE);
        pagination.setMaxPageIndicatorCount(5);
        pagination.setPageFactory(this::createTableView);
    }


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


    public void delete(ActionEvent event)  {
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
//                    nhanKhauList.remove(selected);
                    // Delete in Database
                    try {
                        String DELETE_QUERY = "DELETE FROM nhankhau WHERE `CCCD`= ?";
                        conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                        preparedStatement = conn.prepareStatement(DELETE_QUERY);
                        preparedStatement.setString(1, selected.getCCCD());
                        int result = preparedStatement.executeUpdate();
                        if(result ==1) createDialog(Alert.AlertType.INFORMATION,"Thông báo","Xóa thành công!","");
                        else createDialog(Alert.AlertType.WARNING,"Thông báo","Có lỗi, thử lại sau!","");
                        ViewUtils viewUtils = new ViewUtils();
                        viewUtils.changeAnchorPane(basePane,NHAN_KHAU_VIEW_FXML);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
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

    public Node createTableView(int pageIndex){

        indexColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<NhanKhau, NhanKhau>, ObservableValue<NhanKhau>>) p -> new ReadOnlyObjectWrapper(p.getValue()));

        indexColumn.setCellFactory(new Callback<TableColumn<NhanKhau, NhanKhau>, TableCell<NhanKhau, NhanKhau>>() {
            @Override public TableCell<NhanKhau, NhanKhau> call(TableColumn<NhanKhau, NhanKhau> param) {
                return new TableCell<NhanKhau, NhanKhau>() {
                    @Override protected void updateItem(NhanKhau item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex()+1+pageIndex*ROWS_PER_PAGE+"");
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
            int lastIndex = 0;
            int displace = nhanKhauList.size() % ROWS_PER_PAGE;
            if (displace > 0) {
                lastIndex = nhanKhauList.size() / ROWS_PER_PAGE;
            } else {
                lastIndex = nhanKhauList.size() / ROWS_PER_PAGE - 1;
            }
            System.out.println("Displace:"+ displace);
            System.out.println("Last Index:"+ lastIndex);
            System.out.println("Page Index: " + pageIndex);


            // Add nhankhau to table
            if (lastIndex == pageIndex && displace > 0) {
                tableView.setItems(FXCollections.observableArrayList(nhanKhauList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + displace)));
            } else {
                tableView.setItems(FXCollections.observableArrayList(nhanKhauList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + ROWS_PER_PAGE)));
            }


        return tableView;

    }
}
