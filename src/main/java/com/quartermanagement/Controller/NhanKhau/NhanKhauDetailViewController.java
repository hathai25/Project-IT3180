package com.quartermanagement.Controller.NhanKhau;

import com.quartermanagement.Model.NhanKhau;
import com.quartermanagement.Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.quartermanagement.Constants.DBConstants.*;
import static com.quartermanagement.Utils.Utils.*;
import static com.quartermanagement.Utils.Utils.isCccd;

public class NhanKhauDetailViewController implements Initializable {
    @FXML
    private TextField hoVaTenTextField;
    @FXML
    private TextField biDanhTextField;
    @FXML
    private DatePicker ngaySinhDatePicker;
    @FXML
    private TextField cccdTextField;
    @FXML
    private TextField noiSinhTextField;
    @FXML
    private ChoiceBox<String> gioiTinhChoiceBox;
    @FXML
    private TextField nguyenQuanTextField;
    @FXML
    private TextField danTocTextField;
    @FXML
    private TextField noiThuongTruTextField;
    @FXML
    private TextField tonGiaoTextField;
    @FXML
    private TextField quocTichTextField;
    @FXML
    private TextField diaChiHienNayTextField;
    @FXML
    private TextField ngheNghiepTextField;
    @FXML
    private Button add_btn;
    @FXML
    private Button update_btn;
    @FXML
    private Text title;

    private int ID;

    public void setNhanKhau(NhanKhau nhanKhau) {
        hoVaTenTextField.setText(nhanKhau.getHoTen());
        biDanhTextField.setText(nhanKhau.getBiDanh());
        ngaySinhDatePicker.setValue(LOCAL_DATE(nhanKhau.getNgaySinh()));
        cccdTextField.setText(nhanKhau.getCCCD());
        noiSinhTextField.setText(nhanKhau.getNoiSinh());
        gioiTinhChoiceBox.setValue(nhanKhau.getGioiTinh());
        nguyenQuanTextField.setText(nhanKhau.getNguyenQuan());
        danTocTextField.setText(nhanKhau.getDanToc());
        noiThuongTruTextField.setText(nhanKhau.getNoiThuongTru());
        tonGiaoTextField.setText(nhanKhau.getTonGiao());
        quocTichTextField.setText(nhanKhau.getQuocTich());
        diaChiHienNayTextField.setText(nhanKhau.getDiaChiHienNay());
        ngheNghiepTextField.setText(nhanKhau.getNgheNghiep());
    }
    public void goBack(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.switchToNhanKhau_Admin_view(event);
    }



    public void update(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String hoVaTen = hoVaTenTextField.getText();
        String biDanh = biDanhTextField.getText();
        String ngaySinh = ngaySinhDatePicker.getValue().toString();
        String cccd = cccdTextField.getText();
        String noiSinh = noiSinhTextField.getText();
        String gioiTinh = gioiTinhChoiceBox.getValue();
        String nguyenQuan = nguyenQuanTextField.getText();
        String danToc = danTocTextField.getText();
        String noiThuongTru = noiThuongTruTextField.getText();
        String tonGiao = tonGiaoTextField.getText();
        String quocTich = quocTichTextField.getText();
        String diaChiHienNay = diaChiHienNayTextField.getText();
        String ngheNghiep = ngheNghiepTextField.getText();

        if (hoVaTen.trim().equals("") || ngaySinh.trim().equals("") || cccd.trim().equals("") ||
                noiSinh.trim().equals("") || gioiTinh.trim().equals("") || nguyenQuan.trim().equals("") || danToc.trim().equals("") ||
                noiThuongTru.trim().equals("") || diaChiHienNay.trim().equals("") ) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!")
            ;
        } else {
            //regex
            if (isCccd(cccd)) {
                createDialog(Alert.AlertType.WARNING, "Từ từ thôi đồng chí!", "Hãy nhập đúng định dạng CCCD", "");
            }
            else {

                try {
                    Connection conn;
                    PreparedStatement preparedStatement1;
                    String UPDATE_QUERY_NHAN_KHAU = "UPDATE `nhankhau` SET `HoTen`=?,`BiDanh`=?,`NgaySinh`=?,`NoiSinh`=?," +
                            "`GioiTinh`=?,`NguyenQuan`=?,`DanToc`=?,`NoiThuongTru`=?,`TonGiao`=?,`QuocTich`=?,`DiaChiHienNay`=?," +
                            "`NgheNghiep`=? WHERE `ID`=?";
                    conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                    preparedStatement1 = conn.prepareStatement(UPDATE_QUERY_NHAN_KHAU);
                    preparedStatement1.setString(1, hoVaTen);
                    preparedStatement1.setString(2, biDanh);
                    preparedStatement1.setString(3, ngaySinh);
                    preparedStatement1.setString(4, noiSinh);
                    preparedStatement1.setString(5, gioiTinh);
                    preparedStatement1.setString(6, nguyenQuan);
                    preparedStatement1.setString(7, danToc);
                    preparedStatement1.setString(8, noiThuongTru);
                    preparedStatement1.setString(9, tonGiao);
                    preparedStatement1.setString(10, quocTich);
                    preparedStatement1.setString(11, diaChiHienNay);
                    preparedStatement1.setString(12, ngheNghiep);
                    preparedStatement1.setInt(13, ID);
                    preparedStatement1.execute();

                    PreparedStatement preparedStatement2 = null;
                    String UPDATE_QUERY_CCCD = "UPDATE `cccd` SET `CCCD`=? WHERE `idNhankhau`=?";
                    preparedStatement2 = conn.prepareStatement(UPDATE_QUERY_CCCD);
                    preparedStatement2.setString(1, cccd);
                    preparedStatement2.setInt(2, ID);
                    preparedStatement2.execute();

                    int result1 = preparedStatement1.executeUpdate();
                    int result2 = preparedStatement2.executeUpdate();
                    if (result1 == 1 && result2 == 1) {
                        createDialog(
                                Alert.AlertType.CONFIRMATION,
                                "Thành công",
                                "", "Đồng chí vất vả rồi!"
                        );
                        //          swtich to admin-nhankhau-view
                viewUtils.switchToNhanKhau_Admin_view(event);
                    } else {
                        createDialog(
                                Alert.AlertType.ERROR,
                                "Thất bại",
                                "", "Oops, mời đồng chí nhập lại thông tin!"
                        );
                    }

                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public void addnew(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String hoVaTen = hoVaTenTextField.getText();
        String biDanh = biDanhTextField.getText();
        String ngaySinh = ngaySinhDatePicker.getValue().toString();
        String cccd = cccdTextField.getText();
        Long CCCD = Long.parseLong(cccd);
        String noiSinh = noiSinhTextField.getText();
        String gioiTinh = gioiTinhChoiceBox.getValue();
        String nguyenQuan = nguyenQuanTextField.getText();
        String danToc = danTocTextField.getText();
        String noiThuongTru = noiThuongTruTextField.getText();
        String tonGiao = tonGiaoTextField.getText();
        String quocTich = quocTichTextField.getText();
        String diaChiHienNay = diaChiHienNayTextField.getText();
        String ngheNghiep = ngheNghiepTextField.getText();
        if (hoVaTen.trim().equals("") || ngaySinh.trim().equals("") || cccd.trim().equals("") ||
                noiSinh.trim().equals("") || gioiTinh.trim().equals("") || nguyenQuan.trim().equals("") || danToc.trim().equals("") ||
                noiThuongTru.trim().equals("") || diaChiHienNay.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "Đồng chí giữ bình tĩnh",
                    "", "Vui lòng nhập đủ thông tin!")
            ;
        } else {
            //regex
            if (isCccd(cccd)) {
                createDialog(Alert.AlertType.WARNING, "Từ từ thôi đồng chí!", "Hãy nhập đúng định dạng CCCD", "");
            } else {
                try {
                    //Add to nhankhau
                    Connection conn;
                    PreparedStatement preparedStatement = null;
                    String INSERT_QUERY = "INSERT INTO `nhankhau`(`HoTen`, `BiDanh`, `NgaySinh`, `NoiSinh`, `GioiTinh`, `NguyenQuan`, `DanToc`, `NoiThuongTru`, `TonGiao`, `QuocTich`, `DiaChiHienNay`, `NgheNghiep`) " +
                                          "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                    conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                    preparedStatement = conn.prepareStatement(INSERT_QUERY);
                    preparedStatement.setString(1, hoVaTen);
                    preparedStatement.setString(2, biDanh);
                    preparedStatement.setString(3, ngaySinh);
                    preparedStatement.setString(4, noiSinh);
                    preparedStatement.setString(5, gioiTinh);
                    preparedStatement.setString(6, nguyenQuan);
                    preparedStatement.setString(7, danToc);
                    preparedStatement.setString(8, noiThuongTru);
                    if (tonGiao == "") preparedStatement.setString(9, "Không");
                    else preparedStatement.setString(9, tonGiao);
                    if (quocTich == "") preparedStatement.setString(10, "Việt Nam");
                    else preparedStatement.setString(10, quocTich);
                    preparedStatement.setString(11, diaChiHienNay);
                    preparedStatement.setString(12, ngheNghiep);
                    preparedStatement.execute();

                    //Find to id
                    PreparedStatement preparedStatement1 = null;
                    String SELECT_QUERY = "SELECT MAX(ID) AS ID FROM nhankhau";
                    preparedStatement1 = conn.prepareStatement(SELECT_QUERY);
                    ResultSet result1 = preparedStatement1.executeQuery();
                    result1.next();
                    int ID = result1.getInt("ID") ;


                    //Add to cccd
                    PreparedStatement preparedStatement2 = null;
                    String INSERT_QUERY2 = "INSERT INTO `cccd`(`idNhankhau`,`CCCD`) VALUES (?,?)";
                    preparedStatement2 = conn.prepareStatement(INSERT_QUERY2);
                    preparedStatement2.setInt(1, ID);
                    preparedStatement2.setString(2, cccd);
                    preparedStatement2.execute();



                    int result = preparedStatement.executeUpdate();
                    int result2 = preparedStatement2.executeUpdate();
                    if (result == 1 && result2 == 1) {
                        createDialog(
                                Alert.AlertType.CONFIRMATION,
                                "Thành công",
                                "", "Đồng chí vất cả rồi!"
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
    }
    public void hide_add_btn() {
        add_btn.setVisible(false);
    }

    public void hide_update_btn() {
        update_btn.setVisible(false);
        add_btn.setTranslateX(100);
    }

    // Getter and setter methods for all
    public TextField getHoVaTenTextField() {
        return hoVaTenTextField;
    }

    public void setHoVaTenTextField(TextField hoVaTenTextField) {
        this.hoVaTenTextField = hoVaTenTextField;
    }

    public TextField getBiDanhTextField() {
        return biDanhTextField;
    }

    public void setBiDanhTextField(TextField biDanhTextField) {
        this.biDanhTextField = biDanhTextField;
    }


    public TextField getCccdTextField() {
        return cccdTextField;
    }

    public void setCccdTextField(TextField cccdTextField) {
        this.cccdTextField = cccdTextField;
    }

    public TextField getNoiSinhTextField() {
        return noiSinhTextField;
    }

    public void setNoiSinhTextField(TextField noiSinhTextField) {
        this.noiSinhTextField = noiSinhTextField;
    }

    public TextField getNguyenQuanTextField() {
        return nguyenQuanTextField;
    }

    public void setNguyenQuanTextField(TextField nguyenQuanTextField) {
        this.nguyenQuanTextField = nguyenQuanTextField;
    }

    public TextField getDanTocTextField() {
        return danTocTextField;
    }

    public void setDanTocTextField(TextField danTocTextField) {
        this.danTocTextField = danTocTextField;
    }

    public TextField getNoiThuongTruTextField() {
        return noiThuongTruTextField;
    }

    public void setNoiThuongTruTextField(TextField noiThuongTruTextField) {
        this.noiThuongTruTextField = noiThuongTruTextField;
    }

    public TextField getTonGiaoTextField() {
        return tonGiaoTextField;
    }

    public void setTonGiaoTextField(TextField tonGiaoTextField) {
        this.tonGiaoTextField = tonGiaoTextField;
    }

    public TextField getQuocTichTextField() {
        return quocTichTextField;
    }

    public void setQuocTichTextField(TextField quocTichTextField) {
        this.quocTichTextField = quocTichTextField;
    }

    public TextField getDiaChiHienNayTextField() {
        return diaChiHienNayTextField;
    }

    public void setDiaChiHienNayTextField(TextField diaChiHienNayTextField) {
        this.diaChiHienNayTextField = diaChiHienNayTextField;
    }

    public TextField getNgheNghiepTextField() {
        return ngheNghiepTextField;
    }

    public void setNgheNghiepTextField(TextField ngheNghiepTextField) {
        this.ngheNghiepTextField = ngheNghiepTextField;
    }
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gioiTinhChoiceBox.getItems().add("Nam");
        gioiTinhChoiceBox.getItems().add("Nữ");
        gioiTinhChoiceBox.setValue("Nam");
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
