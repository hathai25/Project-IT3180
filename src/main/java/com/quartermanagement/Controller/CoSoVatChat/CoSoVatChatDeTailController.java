package com.quartermanagement.Controller.CoSoVatChat;

import com.quartermanagement.Model.CoSoVatChat;
import com.quartermanagement.Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ResourceBundle;

import static com.quartermanagement.Constants.DBConstants.*;
import static com.quartermanagement.Utils.Utils.createDialog;


public class CoSoVatChatDeTailController implements Initializable {
    @FXML
    private TextField maDoDungTextField;

    @FXML
    private TextField tenDoDungTextField;

    @FXML
    private TextField soLuongTextField;

    @FXML
    private TextField soLuongKhaDungTextField;

    @FXML
    private Button add_btn;

    @FXML
    private Button update_btn;

    @FXML
    private Text title;

    @FXML
    private Pane maDoDungPane, soLuongKhaDungPane;


    public void setCoSoVatChat(CoSoVatChat coSoVatChat) {
        maDoDungTextField.setText(String.valueOf(coSoVatChat.getMaDoDung()));
        tenDoDungTextField.setText(coSoVatChat.getTenDoDung());
        soLuongTextField.setText(String.valueOf(coSoVatChat.getSoLuong()));
        soLuongKhaDungTextField.setText(String.valueOf(coSoVatChat.getSoLuongKhaDung()));

    }
    public void goBack (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.switchToCoSoVatChat_Admin_view(event);
    }

    public void update (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maDoDung = maDoDungTextField.getText();
        String tenDoDung = tenDoDungTextField.getText();
        String soLuong = soLuongTextField.getText();
        String soLuongKhaDung = soLuongKhaDungTextField.getText();


        if (maDoDung.trim().equals("") || tenDoDung.trim().equals("") || soLuong.trim().equals("") || soLuongKhaDung.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "?????ng ch?? gi??? b??nh t??nh",
                    "", "Vui l??ng nh???p ????? th??ng tin!"
            );
        } else if (Integer.parseInt(soLuongKhaDung) > Integer.parseInt(soLuong)){
            createDialog(
                    Alert.AlertType.WARNING,
                    "M???i ?????ng ch?? nh???p l???i!",
                    "", "Mong ?????ng ch?? tham kh???o th??m c??c ?????nh lu???t b???o to??n v???t ch???t"
            );
        }

        else {
            try {
                Connection conn;
                PreparedStatement preparedStatement;
                String UPDATE_QUERY ="UPDATE cosovatchat SET `MaDoDung`=?, `TenDoDung`=?, `SoLuong`=?, `SoLuongKhaDung`=? WHERE `MaDoDung`=?";
                conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                preparedStatement = conn.prepareStatement((UPDATE_QUERY));
                preparedStatement.setString(1, maDoDung);
                preparedStatement.setString(2, tenDoDung);
                preparedStatement.setString(3, soLuong);
                preparedStatement.setString(4, soLuongKhaDung);
                preparedStatement.setString(5, maDoDung);

                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    createDialog(
                            Alert.AlertType.CONFIRMATION,
                            "Th??nh c??ng",
                            "", "?????ng ch?? v???t v??? r???i!"
                    );
                    viewUtils.switchToCoSoVatChat_Admin_view(event);
                } else {
                    createDialog(
                            Alert.AlertType.ERROR,
                            "Th???t b???i",
                            "", "Th???t b???i l?? m??? th??nh c??ng! Mong ?????ng ch?? th??? l???i"
                    );
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addnew (ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        String maDoDung;
        String tenDoDung = tenDoDungTextField.getText();
        String soLuong = soLuongTextField.getText();
        String soLuongKhaDung = soLuongTextField.getText();
        if (tenDoDung.trim().equals("") || soLuong.trim().equals("")) {

            createDialog(
                    Alert.AlertType.WARNING,
                    "?????ng ch?? gi??? b??nh t??nh",
                    "", "Vui l??ng nh???p ????? th??ng tin!"
            );
        } else {
            try {
                Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                PreparedStatement preparedStatement;
                ResultSet rs;
                do {
                    int rand = ThreadLocalRandom.current().nextInt(100000, 999999);
                    maDoDung = String.valueOf(rand);
                    PreparedStatement check = conn.prepareStatement("SELECT MaDoDung From cosovatchat WHERE `MADoDung` =?");
                    check.setInt(1, rand);
                    rs = check.executeQuery();
                } while (rs.next());

                String INSERT_QUERY = "INSERT INTO cosovatchat VALUES(?,?,?,?)";
                preparedStatement = conn.prepareStatement((INSERT_QUERY));
                preparedStatement.setString(1, maDoDung);
                preparedStatement.setString(2, tenDoDung);
                preparedStatement.setString(3, soLuong);
                preparedStatement.setString(4, soLuongKhaDung);

                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    createDialog(
                            Alert.AlertType.CONFIRMATION,
                            "Th??nh c??ng",
                            "", "?????ng ch?? v???t v??? r???i!"
                    );
                } else {
                    createDialog(
                            Alert.AlertType.ERROR,
                            "Th???t b???i",
                            "", "Th???t b???i l?? m??? th??nh c??ng! Mong ?????ng ch?? th??? l???i"
                    );
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            viewUtils.switchToCoSoVatChat_Admin_view(event);
        }
    }

    public void hide_add_btn() { add_btn.setVisible(false); }

    public void hide_update_btn() {
        update_btn.setVisible(false);
        add_btn.setTranslateX(100);
    }

    public void hide_Pane() {
        maDoDungPane.setVisible(false);
        soLuongKhaDungPane.setVisible(false);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
