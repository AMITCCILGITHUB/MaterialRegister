package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.map.controls.ViewBox;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;

public class ViewUser extends TabPane {

	public ViewUser() {

		Tab tab = new Tab("View User : Details");

		try {
			VBox main = ViewLayout.getMainVBox("View User", "Details");

			final TableView<UserMaster> table = TableUtil.createViewUserTable();
			
			main.getChildren().add(table);

			final ObservableList<UserMaster> mailboxData = FXCollections
					.observableArrayList(UserData.getUserList());
			table.setItems(mailboxData);

			EventHandler showPasswordEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					UserMaster selUser = table.getSelectionModel()
							.getSelectedItem();
					
					Alert.showAlert(Context.getWindowStage(), "Info", "Info",
							"Password : " + selUser.getPassword());
				}
			};

			EventHandler showUserEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					UserMaster user = table.getSelectionModel()
							.getSelectedItem();
					if (user != null) {
						
						createViewTab(user);
					}
				}
			};

			table.setContextMenu(TableContextMenu
					.getViewUserContextMenu(showPasswordEventHandler,
							showUserEventHandler));

			table.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getClickCount() == 2) {

						UserMaster user = table.getSelectionModel()
								.getSelectedItem();
						if (user != null) {
							
							createViewTab(user);
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

		VBox main = ViewLayout.getMainVBox("User", "Details");

		GridPane form = new GridPane();
		form.setHgap(ViewLayout.H_SPACE);
		form.setVgap(ViewLayout.V_SPACE);
		
		Label userNameLabel = new Label("User Name");
		userNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		ViewBox userNameTextBox = new ViewBox(user.userNameProperty());
		
		Label passwordLabel = new Label("Password");
		passwordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		ViewBox passwordBox = new ViewBox(user.passwordProperty());
		
		Label roleLabel = new Label("Role");
		roleLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		ViewBox roleTextBox = new ViewBox(user.getRole().roleNameProperty());

		form.add(userNameLabel, 0, 0);
		form.add(userNameTextBox, 1, 0);
		form.add(passwordLabel, 0, 1);
		form.add(passwordBox, 1, 1);
		form.add(roleLabel, 0, 2);
		form.add(roleTextBox, 1, 2);

		main.getChildren().addAll(form);

		tab.setContent(ControlsUtil.makeScrollable(main));
		getTabs().add(tab);
	}
}
