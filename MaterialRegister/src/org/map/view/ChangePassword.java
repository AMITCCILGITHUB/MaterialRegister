package org.map.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.map.MaterialRegister;
import org.map.controls.PasswordBox;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.login.Login;
import org.map.utils.Alert;
import org.map.utils.ViewLayout;
import org.map.validation.ValidationResult;
import org.map.validation.ValidationType;
import org.map.validation.Validator;

public class ChangePassword extends ScrollPane {

	public ChangePassword() {

		try {
			final VBox main = new VBox(ViewLayout.H_SPACE);
			main.getStyleClass().add("category-page");

			Label header = new Label("Change Password");
			header.getStyleClass().add("page-header");
			main.getChildren().add(header);

			Label detailCategoryHeader = new Label("Details");
			detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			detailCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(detailCategoryHeader);

			final UserMaster um = new UserMaster(Login.getLoginPanel()
					.getUserMaster().getUserName(), "", Login.getLoginPanel()
					.getUserMaster().getRole(), Login.getLoginPanel()
					.getUserMaster().getUserStatus());
			final VBox userDetailsVBox = new VBox(ViewLayout.V_SPACE);
			final HBox passwordHBox = new HBox(ViewLayout.H_SPACE);
			Label passwordLabel = new Label("Password");
			passwordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final PasswordBox passwordBox = new PasswordBox("", "Password",
					um.passwordProperty());
			final HBox confirmPasswordHBox = new HBox(ViewLayout.H_SPACE);
			Label confirmPasswordLabel = new Label("Confirm Password");
			confirmPasswordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final PasswordBox confirmPasswordBox = new PasswordBox("",
					"Confirm Password");
			passwordHBox.getChildren().addAll(passwordLabel, passwordBox);
			confirmPasswordHBox.getChildren().addAll(confirmPasswordLabel,
					confirmPasswordBox);
			userDetailsVBox.getChildren().addAll(passwordHBox,
					confirmPasswordHBox);

			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button updateButton = new Button("Update");
			updateButton.getStyleClass().add("submit-button");
			updateButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					if (Validator.validate(ValidationType.PASSWORD,
							passwordBox.getText(), confirmPasswordBox.getText()) == ValidationResult.SUCCESS) {
						UserData.updateUser(um);
						Alert.showAlert(MaterialRegister.getMaterialRegister()
								.getPrimaryStage(), "Alert", "Alert",
								"Password changed successfully.");
						Login.getLoginPanel().getUserMaster()
								.resetUserMaster(um);
					} else {
						passwordBox.setText("");
						confirmPasswordBox.setText("");
						Alert.showAlert(MaterialRegister.getMaterialRegister()
								.getPrimaryStage(), "Invalid Password",
								"Error", "Please enter valid password values.");
					}
				}
			});
			buttons.getChildren().addAll(updateButton);

			main.getChildren().addAll(userDetailsVBox, buttons);

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
