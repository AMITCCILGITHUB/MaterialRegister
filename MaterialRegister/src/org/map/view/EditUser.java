package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.map.controls.PasswordBox;
import org.map.controls.ViewBox;
import org.map.controls.combobox.RoleEditableComboBox;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.service.PersistType;
import org.map.service.PersistUserDetails;
import org.map.service.ServiceManager;
import org.map.utils.Alert;
import org.map.utils.Confirm;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;
import org.map.validation.Validator;

public class EditUser extends TabPane {

	public EditUser() {

		Tab tab = new Tab("Edit User : Details");

		try {
			VBox main = ViewLayout.getMainVBox("Edit User", "Details");

			final TableView<UserMaster> table = TableUtil.createEditUserTable();

			main.getChildren().add(table);

			final ObservableList<UserMaster> mailboxData = FXCollections
					.observableArrayList(UserData.getUserList());
			
			table.setItems(mailboxData);

			EventHandler<ActionEvent> editUserEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					UserMaster user = table.getSelectionModel()
							.getSelectedItem();
					if (user != null) {

						createEditTab(user);
					}
				}
			};

			EventHandler<ActionEvent> deleteUserEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					final UserMaster user = table.getSelectionModel()
							.getSelectedItem();

					EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {

							PersistUserDetails pud = ServiceManager
									.getUserDetailsService(user,
											PersistType.DELETE);

							pud.restart();

							pud.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									mailboxData.remove(user);
								}
							});
						}
					};

					Confirm.showConfirm(Context.getWindowStage(), "Confirm",
							"Confirm", "Delete User : " + user.getUserName()
									+ ". Are you sure?", delUserEvent);
				}
			};

			table.setContextMenu(TableContextMenu.getEditUserContextMenu(
					editUserEventHandler, deleteUserEventHandler));

			table.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getClickCount() == 2) {

						UserMaster user = table.getSelectionModel()
								.getSelectedItem();
						
						if (user != null) {
							createEditTab(user);
						}
					}

				}

			});

			tab.setContent(ControlsUtil.makeScrollable(main));
			tab.setClosable(false);
			getTabs().add(tab);
			setSide(Side.TOP);
		} catch (Exception e) {
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
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

		VBox main = ViewLayout.getMainVBox("User", "Details");

		GridPane form = new GridPane();
		form.setHgap(ViewLayout.H_SPACE);
		form.setVgap(ViewLayout.V_SPACE);

		Label userNameLabel = new Label("User Name");
		userNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		ViewBox userNameTextBox = new ViewBox(user.userNameProperty());

		Label passwordLabel = new Label("Password");
		passwordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final PasswordBox passwordBox = new PasswordBox("Password",
				user.passwordProperty());

		Label confirmPasswordLabel = new Label("Confirm Password");
		confirmPasswordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final PasswordBox confirmPasswordBox = new PasswordBox(
				"Confirm Password", user.confirmPasswordProperty());

		Label roleLabel = new Label("Role");
		roleLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final RoleEditableComboBox roleChoiceBox = new RoleEditableComboBox(
				"Role", user.roleProperty());

		form.add(userNameLabel, 0, 0);
		form.add(userNameTextBox, 1, 0);
		form.add(passwordLabel, 0, 1);
		form.add(passwordBox, 1, 1);
		form.add(confirmPasswordLabel, 0, 2);
		form.add(confirmPasswordBox, 1, 2);
		form.add(roleLabel, 0, 3);
		form.add(roleChoiceBox, 1, 3);

		main.getChildren().addAll(form);

		final HBox buttons = new HBox(ViewLayout.H_SPACE);
		buttons.setTranslateY(32);
		final Button updateButton = new Button("Update");
		updateButton.getStyleClass().add("submit-button");
		main.getChildren().addAll(buttons);

		updateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (Validator.validateUserData(user)) {
					
					ServiceManager.getUserDetailsService(user,
							PersistType.UPDATE).restart();
				}
			}
		});
		buttons.getChildren().addAll(updateButton);

		tab.setContent(ControlsUtil.makeScrollable(main));
		getTabs().add(tab);
	}
}
