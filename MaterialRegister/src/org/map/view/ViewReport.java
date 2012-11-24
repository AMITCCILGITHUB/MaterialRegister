package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.map.hibernate.dao.MaterialData;
import org.map.hibernate.ddo.MaterialRegister;
import org.map.logger.LoggerUtil;
import org.map.service.ServiceManager;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.SearchBoxUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;

public class ViewReport extends ScrollPane {

	public ViewReport() {

		try {
			VBox main = ViewLayout.getMainVBox("Material Register", "Details");

			final ObservableList<MaterialRegister> data = FXCollections
					.observableArrayList(MaterialRegister
							.getMaterialRegisterList(MaterialData
									.getMaterialList()));

			main.getChildren().addAll(
					SearchBoxUtil.getRegisterSearchBox("CT Number", data));

			TableView<MaterialRegister> table = TableUtil
					.createViewMaterialRegisterTable();

			table.setItems(data);

			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button printButton = new Button("Print");
			printButton.getStyleClass().add("submit-button");
			printButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					ServiceManager.getMaterialRegisterService(data).restart();
				}
			});
			buttons.getChildren().addAll(printButton);
			main.getChildren().addAll(table, buttons);

			EventHandler<ActionEvent> printEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					printButton.fire();
				}
			};

			table.setContextMenu(TableContextMenu
					.getPrintMaterialContextMenu(printEventHandler));

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
