package com.quartermanagement.Controller.LichHoatDong;

import com.quartermanagement.Model.LichHoatDong;
import com.quartermanagement.Model.NhanKhau;
import com.quartermanagement.Services.NhanKhauServices;
import com.quartermanagement.Utils.ViewUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import static com.quartermanagement.Constants.DBConstants.*;
import static com.quartermanagement.Utils.Utils.*;
import static com.quartermanagement.Controller.AdminController.userRole;

public class LichHoatDongDetailController implements Initializable {
    @FXML
    private Button add_btn, update_btn;
    @FXML
    private TextField endTimeTextField, startTimeTextField, maHoatDongTextField, tenHoatDongTextField;
    @FXML
    private DatePicker startDatePicker, endDatePicker;
    @FXML
    private ChoiceBox<String> statusChoiceBox;
    @FXML
    private Pane maHoatDongPane;
    @FXML
    private Pane statusPane;
    @FXML
    private Text title;



    @FXML
    private TableView<NhanKhau> tableView;
    @FXML
    private TableColumn indexColumn;
    @FXML
    private TableColumn<NhanKhau, String> hoVaTenColumn, biDanhColumn, ngaySinhColumn, cccdColumn, noiSinhColumn, gioiTinhColumn,
            nguyenQuanColumn, danTocColumn, noiThuongTruColumn, tonGiaoColumn, quocTichColumn, diaChiHienNayColumn, ngheNghiepColumn;
    @FXML
    private Pagination pagination;
    private ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList();
    // Connect to database
    private Connection conn;
    private PreparedStatement preparedStatement = null;

    public void setLichHoatDong(LichHoatDong lichHoatDong) {
        maHoatDongTextField.setText(String.valueOf(lichHoatDong.getMaHoatDong()));
        tenHoatDongTextField.setText(lichHoatDong.getTenHoatDong());
        String startTime = lichHoatDong.getStartTime();
        String [] starttime = startTime.split(" ");
        startDatePicker.setValue(LOCAL_DATE(starttime[1]));
        startTimeTextField.setText(starttime[0]);
        String endTime = lichHoatDong.getEndTime();
        String [] endtime = endTime.split(" ");
        endDatePicker.setValue(LOCAL_DATE(endtime[1]));
        endTimeTextField.setText(endtime[0]);
        statusChoiceBox.setValue(String.valueOf(lichHoatDong.getStatus()));
//        maNguoiTaoChoiceBox.setValue(String.valueOf(lichHoatDong.getMaNguoiTao()));
    }

    public void goBack (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.switchToLichHoatDong_Admin_view(event);
    }

    public void update (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maHoatDong = maHoatDongTextField.getText();
        String tenHoatDong = tenHoatDongTextField.getText();
        String startDateTime = startDatePicker.getValue().toString();
        String startTime = startTimeTextField.getText();
        String starttime = startDateTime + " " + startTime;
        String endDateTime = endDatePicker.getValue().toString();
        String endTime = endTimeTextField.getText();
        String endtime = endDateTime + " " + endTime;
        String status = statusChoiceBox.getValue();

        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        String maNguoiTao = String.valueOf(selected.getID());


        if (maHoatDong.trim().equals("") || tenHoatDong.trim().equals("") || startTime.trim().equals("") || endTime.trim().equals("") || maNguoiTao.trim().equals("")
                || startDateTime.trim().equals("") || endDateTime.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!"
            );
        } else {
            if (!isValidTime(startTime) || !isValidTime(endTime)) {
                createDialog(Alert.AlertType.WARNING, "Từ từ thôi đồng chí!", "Hãy chọn đúng định dạng hh:mm:ss", "");
            } else if(!greaterTime(startDateTime, startTime, endDateTime, endTime)){
                createDialog(Alert.AlertType.WARNING,"Từ từ thôi đồng chí!", "Thời gian kết thúc phải lớn hơn thời gian bắt đầu!", "");
            }else {
                try {
                    Connection conn;
                    PreparedStatement preparedStatement;
                    String UPDATE_QUERY = "UPDATE lichhoatdong SET `MaHoatDong`=?, `TenHoatDong`=?, `ThoiGianBatDau`=?, `ThoiGianKetThuc`=?, `DuocDuyet`=?, `MaNguoiTao`=? WHERE `MaHoatDong`=?";
                    conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                    preparedStatement = conn.prepareStatement((UPDATE_QUERY));
                    preparedStatement.setString(1, maHoatDong);
                    preparedStatement.setString(2, tenHoatDong);
                    preparedStatement.setString(3, starttime);
                    preparedStatement.setString(4, endtime);
                    preparedStatement.setString(5, status);
                    preparedStatement.setString(6, maNguoiTao);
                    preparedStatement.setString(7, maHoatDong);

                    int result = preparedStatement.executeUpdate();
                    if (result == 1) {
                        createDialog(
                                Alert.AlertType.CONFIRMATION,
                                "Thành công",
                                "", "Đồng chí vất vả rồi!"
                        );
                    } else {
                        createDialog(
                                Alert.AlertType.ERROR,
                                "Thất bại",
                                "", "Thất bại là mẹ thành công! Mong đồng chí thử lại"
                        );
                    }
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                viewUtils.switchToLichHoatDong_Admin_view(event);
            }
        }
    }

    public void addnew (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maHoatDong;
        String tenHoatDong = tenHoatDongTextField.getText();
        String startDateTime = startDatePicker.getValue().toString();
        String startTime = startTimeTextField.getText();
        String starttime = startDateTime + " " + startTime;
        String endDateTime = endDatePicker.getValue().toString();
        String endTime = endTimeTextField.getText();
        String endtime = endDateTime + " " + endTime;
        String status = "Chưa duyệt";
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String thoiGianTao = dtf.format(currentTime);

        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        String maNguoiTao = String.valueOf(selected.getID());
        if (selected == null) createDialog(Alert.AlertType.WARNING, "Từ từ đã đồng chí", "", "Vui lòng chọn nhân khẩu");
            else if (tenHoatDong.trim().equals("") ||startTime.trim().equals("") || endTime.trim().equals("") || maNguoiTao.trim().equals("")
                || startDateTime.trim().equals("") || endDateTime.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!"
            );
        } else if(!isValidTime(startTime) || !isValidTime(endTime)){
            createDialog(Alert.AlertType.WARNING,"Từ từ thôi đồng chí!", "Hãy chọn đúng định dạng hh:mm:ss", "");
        } else if(!greaterTime(startDateTime, startTime, endDateTime, endTime)){
            createDialog(Alert.AlertType.WARNING,"Từ từ thôi đồng chí!", "Thời gian kết thúc phải lớn hơn thời gian bắt đầu!", "");
        } else{
                try {
                    Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                    PreparedStatement preparedStatement;
                    ResultSet rs;
                    do {
                        int rand = ThreadLocalRandom.current().nextInt(100000, 999999);
                        maHoatDong = String.valueOf(rand);
                        PreparedStatement check = conn.prepareStatement("SELECT MaHoatDong From lichhoatdong WHERE `MaHoatDong` =?");
                        check.setInt(1, rand);
                        rs = check.executeQuery();
                    } while (rs.next());

                    String INSERT_QUERY = "INSERT INTO lichhoatdong VALUES(?,?,?,?,?,?,?)";
                    preparedStatement = conn.prepareStatement((INSERT_QUERY));
                    preparedStatement.setString(1, maHoatDong);
                    preparedStatement.setString(2, tenHoatDong);
                    preparedStatement.setString(3, starttime);
                    preparedStatement.setString(4, endtime);
                    preparedStatement.setString(5, status);
                    preparedStatement.setString(6, thoiGianTao);
                    preparedStatement.setString(7, String.valueOf(selected.getID()));

                    int result = preparedStatement.executeUpdate();
                    if (result == 1) {
                        createDialog(
                                Alert.AlertType.CONFIRMATION,
                                "Thành công",
                                "", "Đồng chí vất vả rồi!"
                        );
                    } else {
                        createDialog(
                                Alert.AlertType.ERROR,
                                "Thất bại",
                                "", "Thất bại là mẹ thành công! Mong đồng chí thử lại"
                        );
                    }
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                viewUtils.switchToLichHoatDong_Admin_view(event);
        }
    }

    public void hide_add_btn() { add_btn.setVisible(false); }

    public void hide_update_btn() {
        update_btn.setVisible(false);
        add_btn.setTranslateX(100);
    }

    public void hide_maHoatDongPane() {
        maHoatDongPane.setVisible(false);
    }

    public void hide_statusPane(){
        statusPane.setVisible(false);
    }

    public void setTitle(String title) {
       this.title.setText(title);
    }

    public void setRowSelected(LichHoatDong lichHoatDong) {
        int maNguoiTao = lichHoatDong.getMaNguoiTao();
        int index = -1;
        for (int i = 0; i < nhanKhauList.size(); i++) {
            if (nhanKhauList.get(i).getID() == maNguoiTao) {
                index = i;
                break;
            }
        }
        tableView.getSelectionModel().select(index);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusChoiceBox.getItems().add("Chưa duyệt");
        statusChoiceBox.getItems().add("Chấp nhận");
        statusChoiceBox.getItems().add("Từ chối");
        statusChoiceBox.setValue("Chưa duyệt");
        statusPane.setVisible(userRole.equals("totruong"));

        try {
            ResultSet result = NhanKhauServices.getAllNhanKhau();
            while (result.next()) {
                nhanKhauList.add(new NhanKhau(result.getInt("ID"),result.getString("HoTen"), result.getString("BiDanh"),
                        convertDate(result.getString("NgaySinh")), result.getString("CCCD"), result.getString("NoiSinh"),
                        result.getString("GioiTinh"), result.getString("NguyenQuan"), result.getString("DanToc"),
                        result.getString("NoiThuongTru"), result.getString("TonGiao"), result.getString("QuocTich"),
                        result.getString("DiaChiHienNay"), result.getString("NgheNghiep")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        indexColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<NhanKhau, NhanKhau>, ObservableValue<NhanKhau>>) p -> new ReadOnlyObjectWrapper(p.getValue()));

        indexColumn.setCellFactory(new Callback<TableColumn<NhanKhau, NhanKhau>, TableCell<NhanKhau, NhanKhau>>() {
            @Override
            public TableCell<NhanKhau, NhanKhau> call(TableColumn<NhanKhau, NhanKhau> param) {
                return new TableCell<NhanKhau, NhanKhau>() {
                    @Override
                    protected void updateItem(NhanKhau item, boolean empty) {
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
        tableView.setItems(FXCollections.observableArrayList(nhanKhauList));

//        try {
//            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
//            PreparedStatement preparedStatement;
//            // Connecting Database
//            String SELECT_QUERY = "SELECT `ID` FROM nhankhau";
//            preparedStatement = conn.prepareStatement(SELECT_QUERY);
//            ResultSet result = preparedStatement.executeQuery();
//            while (result.next()) {
//                maNguoiTaoChoiceBox.getItems().add(result.getString("ID"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }






}
