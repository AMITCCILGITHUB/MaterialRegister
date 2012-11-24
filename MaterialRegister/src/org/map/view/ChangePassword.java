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
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.service.PersistType;
import org.map.service.PersistUserDetails;
import org.map.service.ServiceManager;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ViewLayout;

public class ChangePassword extends ScrollPane {

	public ChangePassword() {

		try {
			VBox main = ViewLayout.getMainVBox("Change Password", "Details");

			final UserMaster um = new UserMaster(Context.getLoggedUser());
			um.setPassword("");
			um.setConfirmPassword("");

			GridPane form = new GridPane();
			form.setHgap(ViewLayout.H_SPACE);
			form.setVgap(ViewLayout.V_SPACE);
			
			Label passwordLabel = new Label("Password");
			passwordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

			final PasswordBox passwordBox = new PasswordBox("Password",
					um.passwordProperty());
			
			Label confirmPasswordLabel = new Label("Confirm Password");
			confirmPasswordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

			final PasswordBox confirmPasswordBox = new PasswordBox(
					"Confirm Password", um.confirmPasswordProperty());
			
			form.add(passwordLabel, 0, 0);
			form.add(passwordBox, 1, 0);
			form.add(confirmPasswordLabel, 0, 1);
			form.add(confirmPasswordBox, 1, 1);
			
			main.getChildren().add(form);
			
			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button updateButton = new Button("Update");
			updateButton.getStyleClass().add("submit-button");
			updateButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					PersistUserDetails pud = ServiceManager.getUserDetailsService(um,
							PersistType.UPDATE);
					
					pud.restart();
					
					pud.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
						@Override
						public void handle(WorkerStateEvent e) {
							
							um.setPassword("");
							um.setConfirmPassword("");
						}
					});
				}
			});
			buttons.getChildren().addAll(updateButton);

			main.getChildren().addAll(buttons);

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
