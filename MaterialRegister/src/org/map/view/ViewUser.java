package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.map.MaterialRegister;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.TableContextMenu;

public class ViewUser {

    private double COLUMN_WIDTH = 100;
    private double COLUMN_WIDTH_MAX = 120;
    private double LABEL_WIDTH = 100;
    private double H_SPACE = 8;
    private double V_SPACE = 20;

    public Node createView() {
        try {
            final VBox main = new VBox(H_SPACE) {

                @Override
                protected double computePrefHeight(double width) {
                    return Math.max(
                            super.computePrefHeight(width),
                            getParent().getBoundsInLocal().getHeight());
                }
            };
            main.getStyleClass().add("category-page");

            Label header = new Label("View User Details");
            header.getStyleClass().add("page-header");
            main.getChildren().add(header);

            Label detailCategoryHeader = new Label("Details");
            detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
            detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
            detailCategoryHeader.getStyleClass().add("category-header");
            main.getChildren().add(detailCategoryHeader);

            final TableView<UserMaster> tableMailbox = new TableView<>();
            TableColumn MCol1 = new TableColumn("User Name");
            MCol1.setPrefWidth(COLUMN_WIDTH);
            MCol1.setCellValueFactory(new PropertyValueFactory<UserMaster, String>("userName"));
            TableColumn MCol2 = new TableColumn("Password");
            MCol2.setPrefWidth(COLUMN_WIDTH);
            MCol2.setCellValueFactory(new PropertyValueFactory<UserMaster, String>("password"));
            MCol2.setCellFactory(new Callback<TableColumn<UserMaster, String>, TableCell<UserMaster, String>>() {

                @Override
                public TableCell<UserMaster, String> call(TableColumn<UserMaster, String> param) {
                    TableCell<UserMaster, String> cell = new TableCell<UserMaster, String>() {

                        @Override
                        public void updateItem(String item, boolean empty) {
                            setStyle("-fx-padding: 0;");
                            if (item != null) {

                                PasswordField ps = new PasswordField();
                                ps.setDisable(true);
                                ps.setText(item);
                                setGraphic(ps);
                            }
                        }
                    };
                    return cell;
                }
            });
            TableColumn MCol3 = new TableColumn("Role");
            MCol3.setPrefWidth(COLUMN_WIDTH_MAX);
            MCol3.setCellValueFactory(new PropertyValueFactory<UserMaster, String>("role"));
            TableColumn MCol4 = new TableColumn("User Status");
            MCol4.setPrefWidth(COLUMN_WIDTH_MAX);
            MCol4.setCellValueFactory(new PropertyValueFactory<UserMaster, String>("userStatus"));
            tableMailbox.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
            main.getChildren().add(tableMailbox);

            final ObservableList<UserMaster> mailboxData = FXCollections.observableArrayList(UserData.getUserList());
            tableMailbox.setItems(mailboxData);

            EventHandler printEventHandler = new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    final UserMaster selUser = tableMailbox.getSelectionModel().getSelectedItem();
                    Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Info", "Info", "Password : " + selUser.getPassword());
                }
            };
            tableMailbox.setContextMenu(new TableContextMenu(printEventHandler, "Password"));

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.getStyleClass().add("noborder-scroll-pane");
            scrollPane.setFitToWidth(true);
            scrollPane.setContent(main);

            return scrollPane;
        } catch (Exception e) {
            LoggerUtil.getLogger().debug(e);
            Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Error", "Error", "Some error occured. Details...\n" + e.getMessage());
            return new Text("Failed to create sample because of [" + e.getMessage() + "]");
        }
    }
}