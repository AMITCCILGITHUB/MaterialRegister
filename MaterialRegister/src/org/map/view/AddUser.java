package org.map.view;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.map.controls.PasswordBox;
import org.map.controls.TextBox;
import org.map.controls.combobox.RoleComboBox;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.service.PersistType;
import org.map.service.PersistUserDetails;
import org.map.service.ServiceManager;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ViewLayout;
import org.map.validation.Validator;

public class AddUser extends ScrollPane {

	public AddUser() {

		try {
			VBox main = ViewLayout.getMainVBox("Add User Details", "Details");

			final UserMaster user = new UserMaster();
			user.setUserCode(UserData.getNextUserCode());

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
			final PasswordBox confirmPasswordBox = new PasswordBox(
					"Confirm Password", user.confirmPasswordProperty());

			Label roleLabel = new Label("Role");
			roleLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final RoleComboBox roleChoiceBox = new RoleComboBox("Role", user.roleProperty());

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
			
			Button submitButton = new Button("Submit");
			submitButton.getStyleClass().add("submit-button");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					if (Validator.validateUserData(user)) {
						PersistUserDetails pud = ServiceManager
								.getUserDetailsService(user, PersistType.INSERT);

						pud.restart();

						pud.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
							@Override
							public void handle(WorkerStateEvent e) {
								
								user.clean();
							}
						});
					}
				}
			});
			
			Button resetButton = new Button("Reset");
			resetButton.getStyleClass().add("submit-button");
			resetButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					user.clean();
				}
			});
			buttons.getChildren().addAll(submitButton, resetButton);

			main.getChildren().add(buttons);

			getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
			setFitToWidth(true);
			setContent(main);
			
		} catch (Exception e) {
			
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());
		}
	}
}
