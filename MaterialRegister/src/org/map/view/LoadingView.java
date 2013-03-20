package org.map.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.FileUtil;

public class LoadingView extends ScrollPane{

	public LoadingView() {
		try {
			StackPane main = new StackPane();
			main.getStyleClass().add("category-page");

			main.getChildren().add(FileUtil.getImageAsImageView("loading", ".gif"));

			this.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
			this.setFitToWidth(true);
			this.setFitToHeight(true);
			this.setContent(main);

		} catch (Exception e) {

			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());
		}
	}
}
