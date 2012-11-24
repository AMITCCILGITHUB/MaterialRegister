package org.map.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.map.controls.combobox.CodeComboBox;
import org.map.hibernate.ddo.CodeProperty;
import org.map.logger.LoggerUtil;
import org.map.service.PersistType;
import org.map.service.ServiceManager;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ViewLayout;

public class Settings extends ScrollPane {

	public Settings() {

		try {
			VBox main = ViewLayout
					.getMainVBox("Application Setting", "Details");

			final CodeProperty code = new CodeProperty();

			CodeComboBox codeComboBox = new CodeComboBox("Code", code);

			main.getChildren().addAll(codeComboBox);

			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button submitButton = new Button("Submit");
			submitButton.getStyleClass().add("submit-button");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					if(code.get().getCodeNumber() > 1000 ){
						
						ServiceManager.getCodeDetailsService(code.get(),
							PersistType.UPDATE).restart();
					} else {
						
						Alert.showAlert(Context.getWindowStage(), "Info", "Info",
								"Please select proper value!");
					}
				}
			});

			buttons.getChildren().addAll(submitButton);

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
