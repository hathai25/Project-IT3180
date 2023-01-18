package com.quartermanagement.Controller.SoHoKhau;


import com.quartermanagement.Controller.AddThanhVienController;
import com.quartermanagement.Controller.AdminController;
import com.quartermanagement.Model.NhanKhau;
import com.quartermanagement.Model.SoHoKhau;
import com.quartermanagement.Model.ThanhVien;
import com.quartermanagement.Utils.ViewUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.quartermanagement.Constants.DBConstants.*;

import static com.quartermanagement.Constants.FXMLConstants.ADD_SOHOKHAU_VIEW_FXML;
import static com.quartermanagement.Constants.FXMLConstants.NHAN_KHAU_VIEW_FXML;
import static com.quartermanagement.Utils.Utils.*;

public class add_shk_controller {
    @FXML
    AnchorPane basePane;
    @FXML
    private TextField maChuHoTextField;

    @FXML
    private TextField diaChiTextField;

    @FXML
    private TextField maHoKhauTextField;
    @FXML
    private Button add_btn;
    @FXML
    private Button update_btn;
    @FXML
    private Text title;
    @FXML
    private TextField tenChuHoTextField;
    @FXML
    TableView<ThanhVien> thanhVienTable;
    @FXML
    TableColumn<ThanhVien, String> hoTenColumn;
    @FXML
    TableColumn<ThanhVien, String> cccdColumn;
    @FXML
    TableColumn<ThanhVien, String> quanHeColumn;
    private ObservableList<ThanhVien> thanhVienList = FXCollections.observableArrayList();
    @FXML
    private Button addThanhVienBtn;
    @FXML
    private Button deleteThanhVienBtn;
    @FXML
    private Button updateThanhVienBtn;
    @FXML
    private TextField ngayTaoTextField;
    private SoHoKhau soHoKhau;
    private int idHoKhau;

    public void setSoHoKhau(SoHoKhau soHoKhau) throws SQLException {
        this.soHoKhau = soHoKhau;
        Connection conn;
        PreparedStatement preparedStatement = null;
        conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
        String query = "SELECT * FROM sohokhau, nhankhau, cccd where sohokhau.MaChuHo = nhankhau.ID and nhankhau.id = cccd.idNhankhau and sohokhau.MaHoKhau = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,soHoKhau.getMaHoKhau());
        ResultSet result = preparedStatement.executeQuery();
        if(result.next()){
            tenChuHoTextField.setText(result.getString("HoTen"));
            maChuHoTextField.setText(result.getString("CCCD"));
            ngayTaoTextField.setText(result.getString("NgayLap"));
            idHoKhau = result.getInt(1);
        }

        diaChiTextField.setText(soHoKhau.getDiaChi());
        maHoKhauTextField.setText(String.valueOf(soHoKhau.getMaHoKhau()));
        System.out.println("Helo");


        String query2 = "select nhankhau.HoTen, cccd.CCCD, thanhviencuaho.quanHeVoiChuHo from thanhviencuaho, nhankhau, sohokhau, cccd\n" +
                "where thanhviencuaho.idNhanKhau = nhankhau.ID and thanhviencuaho.idHoKhau = sohokhau.ID and cccd.idNhankhau = nhankhau.ID\n" +
                "and sohokhau.MaHoKhau = ?;";
        preparedStatement = conn.prepareStatement(query2);
        preparedStatement.setString(1,soHoKhau.getMaHoKhau());
        ResultSet result2 = preparedStatement.executeQuery();
        while(result2.next()){
            thanhVienList.add(new ThanhVien(result2.getString("HoTen"), result2.getString("CCCD"), result2.getString("quanHeVoiChuHo")));
        }
        thanhVienTable.setItems(FXCollections.observableArrayList(thanhVienList));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("HoTen"));
        cccdColumn.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("CCCD"));
        quanHeColumn.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("quanHeVoiChuHo"));

    }


//    them chu ho
    @FXML
    private TableView<NhanKhau> tableView;
    @FXML
    private TableColumn indexColumn;
    @FXML
    private TableColumn<NhanKhau, String> hoVaTenColumn, biDanhColumn, ngaySinhColumn, cccd2Column, noiSinhColumn, gioiTinhColumn,
            nguyenQuanColumn, danTocColumn, noiThuongTruColumn, tonGiaoColumn, quocTichColumn, diaChiHienNayColumn, ngheNghiepColumn;
    private ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList();
    public void khoiTaoBangChuHo() throws SQLException {
        System.out.println("initTable");
        Connection conn;
        PreparedStatement preparedStatement = null;
        conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
        String SELECT_QUERY = "SELECT nhankhau.*, cccd.CCCD\n" +
                "FROM nhankhau\n" +
                "JOIN cccd\n" +
                "ON nhankhau.ID = cccd.idNhankhau\n" +
                "WHERE nhankhau.ID not in (SELECT idNhanKhau FROM thanhviencuaho) " +
                "and nhankhau.ID not in (SELECT MaChuHo FROM sohokhau)";
        preparedStatement = conn.prepareStatement(SELECT_QUERY);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            nhanKhauList.add(new NhanKhau(result.getInt("ID"),result.getString("HoTen"), result.getString("BiDanh"),
                    convertDate(result.getString("NgaySinh")), result.getString("CCCD"), result.getString("NoiSinh"),
                    result.getString("GioiTinh"), result.getString("NguyenQuan"), result.getString("DanToc"),
                    result.getString("NoiThuongTru"), result.getString("TonGiao"), result.getString("QuocTich"),
                    result.getString("DiaChiHienNay"), result.getString("NgheNghiep")
            ));
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
//        idColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("ID"));
        hoVaTenColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("HoTen"));
        biDanhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("BiDanh"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("NgaySinh"));
        cccd2Column.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("CCCD"));
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
    }



    public void goBack(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.switchToSoHoKhau_Admin_view(event);
    }

    public void hide_add_btn() {
        add_btn.setVisible(false);
    }

    public void hide_update_btn() {
        update_btn.setVisible(false);
        add_btn.setTranslateX(100);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }


    public void addnew(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) createDialog(Alert.AlertType.WARNING, "Từ từ đã đồng chí", "", "Vui lòng chọn hộ khẩu");
        else {
        String diaChi = diaChiTextField.getText();
        String maHoKhau = generateRandomNumber(9);

        if (diaChi.trim().equals("") || maHoKhau
                .trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!")
            ;
        } else {
            try {
                Connection conn;
                PreparedStatement preparedStatement = null;
                String INSERT_QUERY = "INSERT INTO `sohokhau`(`MaHoKhau`, `DiaChi`, `MaChuHo`,`NgayLap`) VALUES (?,?,?,?)";


                System.out.println(maHoKhau + diaChi + selected.getID());

                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement(INSERT_QUERY,Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, maHoKhau);
                preparedStatement.setString(2, diaChi);
                preparedStatement.setInt(3, selected.getID());
                preparedStatement.setString(4, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                System.out.println("thuc hien thanh cong 1");
                System.out.println(preparedStatement);
                int result = preparedStatement.executeUpdate();
                ResultSet keys = preparedStatement.getGeneratedKeys();
                if (keys.next()) {
                    idHoKhau = keys.getInt(1);

                }
                System.out.println("thuc hien thanh cong");

                if (result == 1) {

                    //          swtich to admin-sohokhau-view
                    createDialog(
                            Alert.AlertType.CONFIRMATION,
                            "Thành công",
                            "", "Đồng chí vất cả rồi!"
                    );


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Hãy đưa ra lựa chọn");
                    alert.setHeaderText("Đồng chí có muốn thêm thành viên vào trong sổ hộ khẩu luôn không?");
                    alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                    Optional<ButtonType> ketqua = alert.showAndWait();
                    if (ketqua.get() == ButtonType.YES){
                        // Code for Option 1
                        soHoKhau = new SoHoKhau(selected.getHoTen(), diaChi,maHoKhau,1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/quartermanagement/views/addthanhvien-view.fxml"));
                        Parent studentViewParent = loader.load();
                        Scene scene = new Scene(studentViewParent);
                        AddThanhVienController controller = loader.getController();
                        controller.setIdHoKhau(idHoKhau);
                        controller.setSoHoKhau(soHoKhau);
                        controller.initTable(soHoKhau);
                        stage.setScene(scene);
                    } else {
                        viewUtils.switchToSoHoKhau_Admin_view(event);
                    }

                } else {
                    createDialog(
                            Alert.AlertType.ERROR,
                            "Thất bại",
                            "", "Oops, mời đồng chí nhập lại thông tin!"
                    );
                }

                conn.close();
            } catch (SQLException e) {
            }
        }
        }
    }

    public void update(ActionEvent event) throws IOException, SQLException {
        System.out.println("Nut update");
        setDisableForAdd();
        maHoKhauLabel.setVisible(true);
        maHoKhauTextField.setVisible(true);
        ngayTaoLabel.setVisible(true);
        ngayTaoTextField.setVisible(true);
        tableView.setVisible(true);
        luaChonLabel.setVisible(true);
        doiChuHoBtn.setVisible(true);
        khoiTaoBangChuHo();
    }
    public void doiChuHo(ActionEvent event) throws SQLException, IOException {
        ViewUtils viewUtils = new ViewUtils();
        NhanKhau selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) createDialog(Alert.AlertType.WARNING, "Từ từ đã đồng chí", "", "Vui lòng chọn hộ khẩu");
        else {
            Connection conn;
            PreparedStatement preparedStatement = null;
            String UPDATE_QUERY = "UPDATE sohokhau SET MaChuHo = ? WHERE ID = ?";




            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(UPDATE_QUERY);
            preparedStatement.setInt(1, selected.getID());
            preparedStatement.setInt(2, idHoKhau);

            System.out.println(preparedStatement);
            int result = preparedStatement.executeUpdate();

            if (result == 1) {
                //          swtich to admin-sohokhau-view
                createDialog(
                        Alert.AlertType.CONFIRMATION,
                        "Thành công",
                        "", "Đồng chí vất cả rồi!"
                );
                viewUtils.switchToSoHoKhau_Admin_view(event);
        }

        }
    }

    public void addthanhvien(ActionEvent event) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/quartermanagement/views/addthanhvien-view.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        AddThanhVienController controller = loader.getController();
        controller.setIdHoKhau(idHoKhau);
        controller.setSoHoKhau(soHoKhau);
        controller.initTable(soHoKhau);
        stage.setScene(scene);
    }

    public void deletethanhvien(ActionEvent event) {
        ThanhVien selected = thanhVienTable.getSelectionModel().getSelectedItem();
        if (selected == null) createDialog(Alert.AlertType.WARNING,
                "Cảnh báo",
                "Vui lòng chọn nhân khẩu để tiếp tục", "");
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa nhân khẩu");
            alert.setContentText("Đồng chí muốn xóa nhân khẩu này khỏi sổ hộ khẩu?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    thanhVienList.remove(selected);
                    // Delete in Database
                    try {
                        Connection conn;
                        PreparedStatement preparedStatement = null;
                        conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                        String DELETE_QUERY = "DELETE thanhviencuaho\n" +
                                "FROM thanhviencuaho\n" +
                                "JOIN nhankhau\n" +
                                "ON thanhviencuaho.idNhanKhau = nhankhau.ID\n" +
                                "JOIN cccd\n" +
                                "ON nhankhau.ID = cccd.idNhankhau\n" +
                                "WHERE cccd.CCCD = ?\n";
                        preparedStatement = conn.prepareStatement(DELETE_QUERY);
                        preparedStatement.setString(1, selected.getCCCD());
                        int result = preparedStatement.executeUpdate();
                        if (result==1) createDialog(Alert.AlertType.INFORMATION, "Thông báo", "Xóa thành công!", "");
                        else createDialog(Alert.AlertType.WARNING, "Thông báo", "Có lỗi, thử lại sau!", "");
                        thanhVienList.clear();
                        setSoHoKhau(soHoKhau);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void updatethanhvien(ActionEvent event) {
        ThanhVien selected = thanhVienTable.getSelectionModel().getSelectedItem();
        if (selected == null) createDialog(Alert.AlertType.WARNING,
                "Cảnh báo",
                "Vui lòng chọn nhân khẩu để tiếp tục", "");
        else {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Cập nhật Quan hệ với chủ hộ");
            dialog.setHeaderText("Quan hệ với chủ hộ:");
            dialog.setContentText("Quan hệ với chủ hộ:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(quanHe -> {
                System.out.println(quanHe);
               try {
                   Connection conn;
                   PreparedStatement preparedStatement = null;
                   conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                   String UPDATE_QUERY = "UPDATE thanhviencuaho\n" +
                           "SET quanHeVoiChuHo = ?\n" +
                           "WHERE idNhanKhau in (SELECT nhankhau.ID FROM nhankhau JOIN cccd ON nhankhau.ID = cccd.idNhankhau WHERE cccd.CCCD = ?)\n";
                   preparedStatement = conn.prepareStatement(UPDATE_QUERY);
                   preparedStatement.setString(1,quanHe);
                   preparedStatement.setString(2,selected.getCCCD());
                   System.out.println(preparedStatement);
                   int res = preparedStatement.executeUpdate();
                   if (res==1) createDialog(Alert.AlertType.INFORMATION, "Thông báo", "Cập nhật thành công!", "");
                   else createDialog(Alert.AlertType.WARNING, "Thông báo", "Có lỗi, thử lại sau!", "");
                   thanhVienList.clear();
                   setSoHoKhau(soHoKhau);
               }
               catch (SQLException e) {
                   e.printStackTrace();
               }
        });
    }
    }
    @FXML
    private Text ngayTaoLabel, cccdLabel, tenChuHoLabel, maHoKhauLabel;
    @FXML
    private Button doiChuHoBtn;

    public void setDisableForAdd(){
        ngayTaoLabel.setVisible(false);
        cccdLabel.setVisible(false);
        tenChuHoLabel.setVisible(false);
        ngayTaoTextField.setVisible(false);;
        maChuHoTextField.setVisible(false);;
        tenChuHoTextField.setVisible(false);;
        thanhVienTable.setVisible(false);
        addThanhVienBtn.setVisible(false);
        deleteThanhVienBtn.setVisible(false);
        updateThanhVienBtn.setVisible(false);
        maHoKhauLabel.setVisible(false);
        maHoKhauTextField.setVisible(false);
        doiChuHoBtn.setVisible(false);
    }
    @FXML
    Text luaChonLabel;
    public void setDisableForDetail() throws SQLException {
        doiChuHoBtn.setVisible(false);
        luaChonLabel.setVisible(false);
        tableView.setVisible(false);

    }

   /* public void update(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String hoVaTen = hoVaTenTextField.getText();
        String biDanh = biDanhTextField.getText();
        String ngaySinh = ngaySinhDatePicker.getValue().toString();
        String cccd = cccdTextField.getText();
        String noiSinh = noiSinhTextField.getText();
        String gioiTinh = gioiTinhTextField.getText();
        String nguyenQuan = nguyenQuanTextField.getText();
        String danToc = danTocTextField.getText();
        String noiThuongTru = noiThuongTruTextField.getText();
        String tonGiao = tonGiaoTextField.getText();
        String quocTich = quocTichTextField.getText();
        String diaChiHienNay = diaChiHienNayTextField.getText();
        String ngheNghiep = ngheNghiepTextField.getText();
        String maHoKhau = maHoKhauTextField.getText();

        if (hoVaTen.trim().equals("") || ngaySinh.trim().equals("") || cccd.trim().equals("") ||
                noiSinh.trim().equals("") || gioiTinh.trim().equals("") || nguyenQuan.trim().equals("") || danToc.trim().equals("") ||
                noiThuongTru.trim().equals("") || diaChiHienNay.trim().equals("") || maHoKhau.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!")
            ;
        } else {
            try {
                Connection conn;
                PreparedStatement preparedStatement = null;
                String UPDATE_QUERY = "UPDATE `nhankhau` SET `HoTen`=?,`BiDanh`=?,`NgaySinh`=?,`CCCD`=?,`NoiSinh`=?," +
                        "`GioiTinh`=?,`NguyenQuan`=?,`DanToc`=?,`NoiThuongTru`=?,`TonGiao`=?,`QuocTich`=?,`DiaChiHienNay`=?," +
                        "`NgheNghiep`=?,`MaHoKhau`=? WHERE `CCCD`=?";
                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement(UPDATE_QUERY);
                preparedStatement.setString(1, hoVaTen);
                preparedStatement.setString(2, biDanh);
                preparedStatement.setString(3, ngaySinh);
                preparedStatement.setString(4, cccd);
                preparedStatement.setString(5, noiSinh);
                preparedStatement.setString(6, gioiTinh);
                preparedStatement.setString(7, nguyenQuan);
                preparedStatement.setString(8, danToc);
                preparedStatement.setString(9, noiThuongTru);
                preparedStatement.setString(10, tonGiao);
                preparedStatement.setString(11, quocTich);
                preparedStatement.setString(12, diaChiHienNay);
                preparedStatement.setString(13, ngheNghiep);
                preparedStatement.setString(14, maHoKhau);
                preparedStatement.setString(15, cccd);

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
                            "", "Oops, mời đồng chí nhập lại thông tin!"
                    );
                }

                conn.close();
            } catch (SQLException e) {
            }
//          swtich to admin-nhankhau-view
            viewUtils.switchToNhanKhau_Admin_view(event);
        }
    }
    */



    // Getter and setter methods for all

    public TextField getMaChuHoTextField() {
        return maChuHoTextField;
    }

    public void setMaChuHoTextField(TextField maChuHoTextField) {
        this.maChuHoTextField = maChuHoTextField;
    }

    public TextField getDiaChiTextField() {
        return diaChiTextField;
    }

    public void setDiaChiTextField(TextField diaChiTextField) {
        this.diaChiTextField = diaChiTextField;
    }

    public TextField getMaHoKhauTextField() {
        return maHoKhauTextField;
    }

    public void setMaHoKhauTextField(TextField maHoKhauTextField) {
        this.maHoKhauTextField = maHoKhauTextField;
    }

    public Button getAdd_btn() {
        return add_btn;
    }

    public void setAdd_btn(Button add_btn) {
        this.add_btn = add_btn;
    }

    public Button getUpdate_btn() {
        return update_btn;
    }

    public void setUpdate_btn(Button update_btn) {
        this.update_btn = update_btn;
    }

    public Text getTitle() {
        return title;
    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public SoHoKhau getSoHoKhau() {
        return soHoKhau;
    }
}
