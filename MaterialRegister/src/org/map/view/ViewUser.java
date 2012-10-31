package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import org.map.MaterialRegister;
import org.map.controls.ViewBox;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.TableContextMenu;
import org.map.utils.ViewLayout;

public class ViewUser extends TabPane {

	public ViewUser() {

		Tab tab = new Tab("View User : Search");

		try {
			VBox main = new VBox(ViewLayout.H_SPACE);
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
			MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol1.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
					"userName"));
			TableColumn MCol2 = new TableColumn("Password");
			MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol2.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
					"password"));
			MCol2.setCellFactory(new Callback<TableColumn<UserMaster, String>, TableCell<UserMaster, String>>() {

				@Override
				public TableCell<UserMaster, String> call(
						TableColumn<UserMaster, String> param) {

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
			MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH_MAX);
			MCol3.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
					"role"));
			TableColumn MCol4 = new TableColumn("User Status");
			MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH_MAX);
			MCol4.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
					"userStatus"));
			tableMailbox.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
			main.getChildren().add(tableMailbox);

			final ObservableList<UserMaster> mailboxData = FXCollections
					.observableArrayList(UserData.getUserList());
			tableMailbox.setItems(mailboxData);

			EventHandler showPasswordEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					UserMaster selUser = tableMailbox.getSelectionModel()
							.getSelectedItem();
					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Info", "Info", "Password : "
							+ selUser.getPassword());
				}
			};

			EventHandler showUserEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					UserMaster selUSer = tableMailbox.getSelectionModel()
							.getSelectedItem();
					if (selUSer != null) {
						createViewTab(selUSer);
					}
				}
			};

			tableMailbox.setContextMenu(TableContextMenu
					.getViewUserContextMenu(showPasswordEventHandler,
							showUserEventHandler));

			tableMailbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getClickCount() == 2) {

						UserMaster selUSer = tableMailbox.getSelectionModel()
								.getSelectedItem();
						if (selUSer != null) {
							createViewTab(selUSer);
						}
					}

				}

			});

			ScrollPane scrollPane = new ScrollPane();
			scrollPane.getStyleClass().addAll("noborder-scroll-pane",
					"texture-bg");
			scrollPane.setFitToWidth(true);
			scrollPane.setContent(main);

			tab.setContent(scrollPane);
			tab.setClosable(false);
			getTabs().add(tab);
			setSide(Side.TOP);
		} catch (Exception e) {
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(MaterialRegister.getMaterialRegister()
					.getPrimaryStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());
		}
	}

	private void createViewTab(final UserMaster user) {

		for (Tab selTab : getTabs()) {
			if (selTab.getId() != null
					&& selTab.getId().equalsIgnoreCase(user.getUserName())) {
				getSelectionModel().select(selTab);
				return;
			}
		}

		Tab tab = new Tab("View User : " + user.getUserName());
		tab.setId(user.getUserName());

		VBox main = new VBox(ViewLayout.H_SPACE);
		VBox.setVgrow(main, Priority.ALWAYS);
		main.getStyleClass().add("category-page");

		Label header = new Label("View User");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		final VBox userDetailsVBox = new VBox(ViewLayout.V_SPACE);
		final HBox userNameHBox = new HBox(ViewLayout.H_SPACE);
		Label userNameLabel = new Label("User Name");
		userNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		ViewBox userNameTextBox = new ViewBox(user.userNameProperty());
		final HBox passwordHBox = new HBox(ViewLayout.H_SPACE);
		Label passwordLabel = new Label("Password");
		passwordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		ViewBox passwordBox = new ViewBox(user.passwordProperty());
		final HBox roleHBox = new HBox(ViewLayout.H_SPACE);
		Label roleLabel = new Label("Role");
		roleLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		ViewBox roleTextBox = new ViewBox(user.roleProperty());
		userNameHBox.getChildren().addAll(userNameLabel, userNameTextBox);
		passwordHBox.getChildren().addAll(passwordLabel, passwordBox);
		roleHBox.getChildren().addAll(roleLabel, roleTextBox);
		userDetailsVBox.getChildren().addAll(userNameHBox, passwordHBox,
				roleHBox);

		main.getChildren().addAll(userDetailsVBox);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);

		tab.setContent(scrollPane);
		getTabs().add(tab);
	}
}
