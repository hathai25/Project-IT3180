package com.quartermanagement.Controller.CoSoVatChat;

import com.quartermanagement.Model.CoSoVatChat;
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
import static com.quartermanagement.Constants.FXMLConstants.CO_SO_VAT_CHAT_VIEW_FXML;
import static com.quartermanagement.Utils.Utils.createDialog;
import static com.quartermanagement.Constants.FXMLConstants.DETAIL_CO_SO_VAT_CHAT_VIEW_FXML;
public class CoSoVatChatController implements Initializable {
    @FXML
    private AnchorPane basePane;

    @FXML
    private TableView<CoSoVatChat> tableView;

    @FXML
    private TableColumn indexColumn;

    @FXML
    private TableColumn<CoSoVatChat, String> tenDoDungColumn;

    @FXML
    private TableColumn<CoSoVatChat, Integer> maDoDungColumn, soLuongColumn, soLuongKhaDungColumn;

    @FXML
    private Pagination pagination;

    private ObservableList<CoSoVatChat> coSoVatChatList = FXCollections.observableArrayList();

    private Connection conn;

    private PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        try {
            String SELECT_QUERY = "SELECT * FROM cosovatchat";
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            preparedStatement = conn.prepareStatement(SELECT_QUERY);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()) {
                coSoVatChatList.add(new CoSoVatChat(result.getInt("maDoDung"), result.getString("tenDoDung"),
                        result.getInt("soLuong"), result.getInt("soLuongKhaDung")));

            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        int soDu = coSoVatChatList.size() % ROWS_PER_PAGE;
        if (soDu != 0) pagination.setPageCount(coSoVatChatList.size() / ROWS_PER_PAGE + 1);
        else pagination.setPageCount(coSoVatChatList.size() / ROWS_PER_PAGE);
        pagination.setMaxPageIndicatorCount(5);
        pagination.setPageFactory(this::createTableView);
    }


        public void add(ActionEvent event) throws IOException {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(DETAIL_CO_SO_VAT_CHAT_VIEW_FXML));
            Parent studentViewParent = loader.load();
            Scene scene = new Scene(studentViewParent);
            CoSoVatChatDeTailController controller = loader.getController();
            controller.hide_update_btn();
            controller.hide_Pane();
            stage.setScene(scene);
        }

        public void delete() {
            CoSoVatChat selected = tableView.getSelectionModel().getSelectedItem();
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
                            String DELETE_QUERY = "DELETE FROM cosovatchat WHERE `MaDoDung`= ?";
                            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                            preparedStatement = conn.prepareStatement(DELETE_QUERY);
                            preparedStatement.setString(1, String.valueOf(selected.getMaDoDung()));
                            int result = preparedStatement.executeUpdate();
                            if (result == 1)
                                createDialog(Alert.AlertType.INFORMATION, "Xo?? th??nh c??ng!", "", "Qu?? ????n gi???n ph???i kh??ng ?????ng ch???");
                            else createDialog(Alert.AlertType.WARNING, "Th??ng b??o", "", "Oops, m???i ?????ng ch?? th??? l???i!");
                            ViewUtils viewUtils = new ViewUtils();
                            viewUtils.changeAnchorPane(basePane, CO_SO_VAT_CHAT_VIEW_FXML);
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
        loader.setLocation(getClass().getResource(DETAIL_CO_SO_VAT_CHAT_VIEW_FXML));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        CoSoVatChatDeTailController controller = loader.getController();
        CoSoVatChat selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) createDialog(Alert.AlertType.WARNING, "T??? t??? ???? ?????ng ch??", "", "Vui l??ng ch???n 1 m???c ????? ti???p t???c");
        else {
            controller.setCoSoVatChat(selected);
            controller.hide_add_btn();
            controller.setTitle("C???p nh???t c?? s??? v???t ch???t");
            stage.setScene(scene);
        }

    }

    public Node createTableView(int pageIndex) {

        indexColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<CoSoVatChat, CoSoVatChat>, ObservableValue<CoSoVatChat>>) p -> new ReadOnlyObjectWrapper(p.getValue()));
        indexColumn.setCellFactory(new Callback<TableColumn<CoSoVatChat, CoSoVatChat>, TableCell<CoSoVatChat, CoSoVatChat>>() {
            @Override
            public TableCell<CoSoVatChat, CoSoVatChat> call(TableColumn<CoSoVatChat, CoSoVatChat> param) {
                return new TableCell<CoSoVatChat, CoSoVatChat>() {
                    @Override
                    protected void updateItem(CoSoVatChat item, boolean empty) {
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
        maDoDungColumn.setCellValueFactory(new PropertyValueFactory<CoSoVatChat, Integer>("maDoDung"));
        tenDoDungColumn.setCellValueFactory((new PropertyValueFactory<CoSoVatChat, String>("tenDoDung")));
        soLuongColumn.setCellValueFactory(new PropertyValueFactory<CoSoVatChat, Integer>("soLuong"));
        soLuongKhaDungColumn.setCellValueFactory(new PropertyValueFactory<CoSoVatChat, Integer>("soLuongKhaDung"));
        int lastIndex = 0;
        int displace = coSoVatChatList.size() % ROWS_PER_PAGE;
        if (displace > 0) {
        lastIndex = coSoVatChatList.size() / ROWS_PER_PAGE;
        } else {
        lastIndex = coSoVatChatList.size() / ROWS_PER_PAGE - 1;
        }
        if (lastIndex == pageIndex && displace > 0) {
        tableView.setItems(FXCollections.observableArrayList(coSoVatChatList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + displace)));
        } else {
            tableView.setItems(FXCollections.observableArrayList(coSoVatChatList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + ROWS_PER_PAGE)));
        }
        return tableView;
    }

}
