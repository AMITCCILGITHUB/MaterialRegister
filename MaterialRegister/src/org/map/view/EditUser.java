package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Button;
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
import org.map.controls.EComboBox;
import org.map.controls.PasswordBox;
import org.map.controls.ViewBox;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.Confirm;
import org.map.utils.TableContextMenu;

public class EditUser extends TabPane {

	private double COLUMN_WIDTH = 100;
	private double COLUMN_WIDTH_MAX = 120;
	private double LABEL_WIDTH = 100;
	private double H_SPACE = 8;
	private double V_SPACE = 20;

	public EditUser() {
		Tab tab = new Tab("Edit User : Search");

		try {
			VBox main = new VBox(H_SPACE);
			main.getStyleClass().add("category-page");

			Label header = new Label("Edit User Details");
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
			MCol1.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
					"userName"));
			TableColumn MCol2 = new TableColumn("Password");
			MCol2.setPrefWidth(COLUMN_WIDTH);
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
			MCol3.setPrefWidth(COLUMN_WIDTH_MAX);
			MCol3.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
					"role"));
			TableColumn MCol4 = new TableColumn("User Status");
			MCol4.setPrefWidth(COLUMN_WIDTH_MAX);
			MCol4.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
					"userStatus"));
			tableMailbox.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
			main.getChildren().add(tableMailbox);
			VBox.setVgrow(main, Priority.ALWAYS);

			final ObservableList<UserMaster> mailboxData = FXCollections
					.observableArrayList(UserData.getUserList());
			tableMailbox.setItems(mailboxData);

			EventHandler<ActionEvent> editUserEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					UserMaster selUSer = tableMailbox.getSelectionModel()
							.getSelectedItem();
					if (selUSer != null) {
						createEditTab(selUSer);
					}
				}
			};

			EventHandler<ActionEvent> undoUserEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					final UserMaster delUser = tableMailbox.getSelectionModel()
							.getSelectedItem();

					EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							delUser.setUserStatus("TRUE");
							UserData.updateUser(delUser);

							mailboxData.clear();
							mailboxData.addAll(UserData.getUserList());
						}
					};

					Confirm.showConfirm(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Confirm", "Confirm",
							"Restore User : " + delUser.getUserName()
									+ ". Are you sure?", delUserEvent);
				}
			};

			EventHandler<ActionEvent> deleteUserEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					final UserMaster delUser = tableMailbox.getSelectionModel()
							.getSelectedItem();

					EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							delUser.setUserStatus("FALSE");
							UserData.updateUser(delUser);

							mailboxData.clear();
							mailboxData.addAll(UserData.getUserList());
						}
					};

					Confirm.showConfirm(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Confirm", "Confirm",
							"Delete User : " + delUser.getUserName()
									+ ". Are you sure?", delUserEvent);
				}
			};

			tableMailbox.setContextMenu(TableContextMenu
					.getEditUserContextMenu(editUserEventHandler,
							undoUserEventHandler, deleteUserEventHandler));

			tableMailbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {
					if (mouseEvent.getClickCount() == 2) {

						UserMaster selUSer = tableMailbox.getSelectionModel()
								.getSelectedItem();
						if (selUSer != null) {
							createEditTab(selUSer);
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

	private void createEditTab(final UserMaster user) {
		for (Tab selTab : getTabs()) {
			if (selTab.getId() != null
					&& selTab.getId().equalsIgnoreCase(user.getUserName())) {
				getSelectionModel().select(selTab);
				return;
			}
		}

		Tab tab = new Tab("Edit User : " + user.getUserName());
		tab.setId(user.getUserName());

		final VBox main = new VBox(H_SPACE) {

			@Override
			protected double computePrefHeight(double width) {

				return Math.max(super.computePrefHeight(width), getParent()
						.getBoundsInLocal().getHeight());
			}
		};
		main.getStyleClass().add("category-page");

		Label header = new Label("Edit User");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		final VBox userDetailsVBox = new VBox(V_SPACE);
		final HBox userNameHBox = new HBox(H_SPACE);
		Label userNameLabel = new Label("User Name");
		userNameLabel.setPrefWidth(LABEL_WIDTH);
		ViewBox userNameTextBox = new ViewBox("", user.userNameProperty(), true);
		final HBox passwordHBox = new HBox(H_SPACE);
		Label passwordLabel = new Label("Password");
		passwordLabel.setPrefWidth(LABEL_WIDTH);
		final PasswordBox passwordBox = new PasswordBox("", "Password",
				user.passwordProperty(), true);
		final HBox confirmPasswordHBox = new HBox(H_SPACE);
		Label confirmPasswordLabel = new Label("Confirm Password");
		confirmPasswordLabel.setPrefWidth(LABEL_WIDTH);
		final PasswordBox confirmPasswordBox = new PasswordBox("",
				"Confirm Password", user.passwordProperty(), true);
		final HBox roleHBox = new HBox(H_SPACE);
		Label roleLabel = new Label("Role");
		roleLabel.setPrefWidth(LABEL_WIDTH);
		final EComboBox roleChoiceBox = new EComboBox("", "Role", "Role",
				user.roleProperty(), true);
		userNameHBox.getChildren().addAll(userNameLabel, userNameTextBox);
		passwordHBox.getChildren().addAll(passwordLabel, passwordBox);
		confirmPasswordHBox.getChildren().addAll(confirmPasswordLabel,
				confirmPasswordBox);
		roleHBox.getChildren().addAll(roleLabel, roleChoiceBox);
		userDetailsVBox.getChildren().addAll(userNameHBox, passwordHBox,
				confirmPasswordHBox, roleHBox);
		main.getChildren().addAll(userDetailsVBox);

		final HBox buttons = new HBox(H_SPACE);
		buttons.setTranslateY(32);
		final Button updateButton = new Button("Update");
		updateButton.getStyleClass().add("submit-button");
		main.getChildren().addAll(buttons);

		updateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (passwordBox.getText().trim().length() > 0
						&& confirmPasswordBox.getText().trim().length() > 0) {
					if (passwordBox.getText().equalsIgnoreCase(
							confirmPasswordBox.getText())) {
						if (roleChoiceBox.getText().trim().length() > 0) {
							UserData.updateUser(user);

							Alert.showAlert(MaterialRegister
									.getMaterialRegister().getPrimaryStage(),
									"Alert", "Alert",
									"User details updated successfully.");

							MaterialRegister.getMaterialRegister().reloadPage(
									"Edit User");
						} else {
							Alert.showAlert(MaterialRegister
									.getMaterialRegister().getPrimaryStage(),
									"Error", "Error",
									"Please select a role for user.");
						}
					} else {
						Alert.showAlert(MaterialRegister.getMaterialRegister()
								.getPrimaryStage(), "Error", "Error",
								"Password and confirm password does not match.");
					}
				} else {
					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Error", "Error",
							"Either password or confirm password is empty.");
				}
			}
		});
		buttons.getChildren().addAll(updateButton);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);

		tab.setContent(scrollPane);
		getTabs().add(tab);
	}
}
