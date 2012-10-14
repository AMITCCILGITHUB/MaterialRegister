package org.map.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.map.MaterialRegister;
import org.map.controls.CustomComboBox;
import org.map.controls.PasswordBox;
import org.map.controls.TextBox;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;

public class AddUser {

	private double LABEL_WIDTH = 180;
	private double H_SPACE = 8;
	private double V_SPACE = 20;

	public Node createView() {

		try {
			final VBox main = new VBox(H_SPACE) {

				@Override
				protected double computePrefHeight(double width) {

					return Math.max(super.computePrefHeight(width), getParent()
							.getBoundsInLocal().getHeight());
				}
			};
			main.getStyleClass().add("category-page");

			Label header = new Label("Add User Details");
			header.getStyleClass().add("page-header");
			main.getChildren().add(header);

			Label detailCategoryHeader = new Label("Details");
			detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			detailCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(detailCategoryHeader);

			final UserMaster newUser = new UserMaster();
			final VBox userDetailsVBox = new VBox(V_SPACE);
			final HBox userNameHBox = new HBox(H_SPACE);
			Label userNameLabel = new Label("User Name");
			userNameLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox userNameTextBox = new TextBox("", "User Name",
					newUser.userNameProperty(), true);
			final HBox passwordHBox = new HBox(H_SPACE);
			Label passwordLabel = new Label("Password");
			passwordLabel.setPrefWidth(LABEL_WIDTH);
			final PasswordBox passwordBox = new PasswordBox("", "Password",
					newUser.passwordProperty(), true);
			final HBox confirmPasswordHBox = new HBox(H_SPACE);
			Label confirmPasswordLabel = new Label("Confirm Password");
			confirmPasswordLabel.setPrefWidth(LABEL_WIDTH);
			final PasswordBox confirmPasswordBox = new PasswordBox("",
					"Confirm Password");
			final HBox roleHBox = new HBox(H_SPACE);
			Label roleLabel = new Label("Role");
			roleLabel.setPrefWidth(LABEL_WIDTH);
			final CustomComboBox roleChoiceBox = new CustomComboBox("", "Role",
					"Role", newUser.roleProperty(), true);
			userNameHBox.getChildren().addAll(userNameLabel, userNameTextBox);
			passwordHBox.getChildren().addAll(passwordLabel, passwordBox);
			confirmPasswordHBox.getChildren().addAll(confirmPasswordLabel,
					confirmPasswordBox);
			roleHBox.getChildren().addAll(roleLabel, roleChoiceBox);
			userDetailsVBox.getChildren().addAll(userNameHBox, passwordHBox,
					confirmPasswordHBox, roleHBox);

			final HBox buttons = new HBox(H_SPACE);
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
									UserData.insertUser(newUser);
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

					newUser.resetUserMaster();
				}
			});
			buttons.getChildren().addAll(submitButton, resetButton);

			main.getChildren().addAll(userDetailsVBox, buttons);
			VBox.setVgrow(main, Priority.ALWAYS);

			ScrollPane scrollPane = new ScrollPane();
			scrollPane.getStyleClass().add("noborder-scroll-pane");
			scrollPane.setFitToWidth(true);
			scrollPane.setContent(main);

			return scrollPane;
		} catch (Exception e) {
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(MaterialRegister.getMaterialRegister()
					.getPrimaryStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());
			return new Text("Failed to create sample because of ["
					+ e.getMessage() + "]");
		}
	}
}
