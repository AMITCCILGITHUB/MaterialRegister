package org.map.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import org.map.MaterialRegister;
import org.map.controls.CustomComboBox;
import org.map.controls.PasswordBox;
import org.map.controls.TextBox;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.ViewLayout;

public class AddUser extends ScrollPane {

	public AddUser() {

		try {
			VBox main = new VBox(ViewLayout.H_SPACE);
			main.getStyleClass().add("category-page");

			Label header = new Label("Add User Details");
			header.getStyleClass().add("page-header");
			main.getChildren().add(header);

			Label detailHeader = new Label("Details");
			detailHeader.setMaxWidth(Double.MAX_VALUE);
			detailHeader.setMinHeight(Control.USE_PREF_SIZE);
			detailHeader.getStyleClass().add("category-header");
			main.getChildren().add(detailHeader);

			final UserMaster user = new UserMaster();

			GridPane form = new GridPane();
			form.setHgap(ViewLayout.H_SPACE);
			form.setVgap(ViewLayout.V_SPACE);

			Label userNameLabel = new Label("User Name");
			userNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final TextBox userNameTextBox = new TextBox("User Name",
					user.userNameProperty());

			Label passwordLabel = new Label("Password");
			passwordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final PasswordBox passwordBox = new PasswordBox("Password",
					user.passwordProperty());

			Label confirmPasswordLabel = new Label("Confirm Password");
			confirmPasswordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final PasswordBox confirmPasswordBox = new PasswordBox("",
					"Confirm Password");

			Label roleLabel = new Label("Role");
			roleLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final CustomComboBox roleChoiceBox = new CustomComboBox("Role",
					"Role", user.roleProperty());

			form.add(userNameLabel, 0, 0);
			form.add(userNameTextBox, 1, 0);
			form.add(passwordLabel, 0, 1);
			form.add(passwordBox, 1, 1);
			form.add(confirmPasswordLabel, 0, 2);
			form.add(confirmPasswordBox, 1, 2);
			form.add(roleLabel, 0, 3);
			form.add(roleChoiceBox, 1, 3);
			main.getChildren().add(form);

			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button submitButton = new Button("Submit");
			submitButton.getStyleClass().add("submit-button");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					if (userNameTextBox.getText().trim().length() > 0) {
						if (passwordBox.getText().trim().length() > 0
								&& confirmPasswordBox.getText().trim().length() > 0) {
							if (passwordBox.getText().equalsIgnoreCase(
									confirmPasswordBox.getText())) {
								if (roleChoiceBox.getText().trim().length() > 0) {
									UserData.insertUser(user);
									Alert.showAlert(MaterialRegister
											.getMaterialRegister()
											.getPrimaryStage(), "Alert",
											"Alert",
											"User details saved successfully.");
									MaterialRegister.getMaterialRegister()
											.reloadPage("Add User");
								} else {
									Alert.showAlert(MaterialRegister
											.getMaterialRegister()
											.getPrimaryStage(), "Error",
											"Error",
											"Please select a role for user.");
								}
							} else {
								Alert.showAlert(MaterialRegister
										.getMaterialRegister()
										.getPrimaryStage(), "Error", "Error",
										"Password and confirm password does not match.");
							}
						} else {
							Alert.showAlert(MaterialRegister
									.getMaterialRegister().getPrimaryStage(),
									"Error", "Error",
									"Either password or confirm password is empty.");
						}
					} else {
						Alert.showAlert(MaterialRegister.getMaterialRegister()
								.getPrimaryStage(), "Error", "Error",
								"User name is empty.");
					}
				}
			});
			final Button resetButton = new Button("Reset");
			resetButton.getStyleClass().add("submit-button");
			resetButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					user.resetUserMaster();
				}
			});
			buttons.getChildren().addAll(submitButton, resetButton);

			main.getChildren().add(buttons);
			VBox.setVgrow(main, Priority.ALWAYS);

			getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
			setFitToWidth(true);
			setContent(main);
		} catch (Exception e) {
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(MaterialRegister.getMaterialRegister()
					.getPrimaryStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());
		}
	}
}
