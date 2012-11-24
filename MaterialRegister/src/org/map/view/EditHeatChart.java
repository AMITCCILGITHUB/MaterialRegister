package org.map.view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

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
import net.sf.jasperreports.engine.JRException;

import org.hibernate.HibernateException;
import org.map.controls.EditableBox;
import org.map.hibernate.dao.Reporter;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.ddo.HeatChartSheets;
import org.map.logger.LoggerUtil;
import org.map.service.PersistHeatChartDetails;
import org.map.service.PersistType;
import org.map.service.ServiceManager;
import org.map.utils.Alert;
import org.map.utils.Confirm;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.SearchBoxUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;

public class EditHeatChart extends TabPane {

	public EditHeatChart() {

		Tab tab = new Tab("Edit Heat CHart : Search");

		try {
			VBox main = ViewLayout.getMainVBox("Edit Heat Chart", "Details");

			final ObservableList<HeatChartMaster> mailboxData = FXCollections
					.observableArrayList();
			main.getChildren().addAll(
					SearchBoxUtil.getHeatChartSearchBox("Heat Chart Number",
							mailboxData));

			final TableView<HeatChartMaster> tableMailbox = TableUtil
					.createSearchHeatChartTable();

			main.getChildren()
					.addAll(ControlsUtil.makeScrollable(tableMailbox));

			tableMailbox.setItems(mailboxData);

			tableMailbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getClickCount() == 2) {

						HeatChartMaster heatChart = tableMailbox
								.getSelectionModel().getSelectedItem();
						if (heatChart != null) {

							try {
								createEditTab(heatChart);
							} catch (MalformedURLException e) {
							}
						}
					}

				}

			});

			EventHandler<ActionEvent> editEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					HeatChartMaster heatChart = tableMailbox
							.getSelectionModel().getSelectedItem();
					if (heatChart != null) {

						try {
							createEditTab(heatChart);
						} catch (MalformedURLException e) {
						}
					}
				}
			};

			EventHandler<ActionEvent> deleteEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					final HeatChartMaster heatChart = tableMailbox
							.getSelectionModel().getSelectedItem();

					EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {

							PersistHeatChartDetails phc = ServiceManager.getHeatChartDetailsService(
									heatChart, PersistType.DELETE);
							
							phc.restart();

							phc.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									mailboxData.remove(heatChart);
								}
							});
						}
					};
					
					Confirm.showConfirm(Context.getWindowStage(), "Confirm",
							"Confirm", "Delete " + heatChart.getChartNumber()
									+ ". Are you sure?", delUserEvent);
				}
			};

			tableMailbox.setContextMenu(TableContextMenu
					.getEditHeatChartContextMenu(editEventHandler,
							deleteEventHandler));

			tab.setContent(ControlsUtil.makeScrollable(main));
			tab.setClosable(false);
			getTabs().add(tab);
			setSide(Side.TOP);
			
		} catch (HibernateException | MalformedURLException e) {
			
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());
		}
	}

	private void createEditTab(final HeatChartMaster heatChart)
			throws MalformedURLException {

		for (Tab selTab : getTabs()) {
			if (selTab.getId() != null
					&& selTab.getId().equalsIgnoreCase(
							heatChart.getChartNumber())) {
				getSelectionModel().select(selTab);
				return;
			}
		}

		Tab tab = new Tab("Edit Heat Chart : " + heatChart.getChartNumber());
		tab.setId(heatChart.getChartNumber());

		VBox main = ViewLayout.getMainVBox("Heat Chart", "Details");

		GridPane form = new GridPane();
		form.setHgap(ViewLayout.H_SPACE);
		form.setVgap(ViewLayout.V_SPACE);

		Label equipmentLabel = new Label("Equipment");
		equipmentLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final EditableBox equipmentTextField = new EditableBox("Equipment",
				heatChart.equipmentProperty());

		Label customerLabel = new Label("Customer");
		customerLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final EditableBox customerTextField = new EditableBox("Customer",
				heatChart.customerProperty());

		Label poDetailsLabel = new Label("PO Details");
		poDetailsLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final EditableBox poDetailsTextField = new EditableBox("PO Details",
				heatChart.poDetailsProperty());

		Label drawingLabel = new Label("Drawing No.");
		drawingLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final EditableBox drawingTextField = new EditableBox("Drawing No.",
				heatChart.drawingNumberProperty());

		Label suryeyorLabel = new Label("Surveyor");
		suryeyorLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final EditableBox suryeyorTextField = new EditableBox("Suryeyor",
				heatChart.surveyorProperty());

		Label tagNumberLabel = new Label("Tag Number");
		suryeyorLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final EditableBox tagNumberTextField = new EditableBox("Tag Number",
				heatChart.tagNumberProperty());

		form.add(equipmentLabel, 0, 0);
		form.add(equipmentTextField, 1, 0);
		form.add(customerLabel, 2, 0);
		form.add(customerTextField, 3, 0);
		form.add(poDetailsLabel, 4, 0);
		form.add(poDetailsTextField, 5, 0);
		form.add(drawingLabel, 0, 1);
		form.add(drawingTextField, 1, 1);
		form.add(suryeyorLabel, 2, 1);
		form.add(suryeyorTextField, 3, 1);
		form.add(tagNumberLabel, 4, 1);
		form.add(tagNumberTextField, 5, 1);

		main.getChildren().add(form);

		final ObservableList<HeatChartSheets> data = FXCollections
				.observableArrayList();
		final TableView<HeatChartSheets> table = TableUtil
				.createEditHeatChartSheetTable(data);

		main.getChildren().addAll(ControlsUtil.makeScrollable(table));

		data.setAll(heatChart.getHeatChartSheets());
		table.setItems(data);

		final HBox buttons = new HBox(ViewLayout.H_SPACE);
		buttons.setTranslateY(32);
		final Button addRecordButton = new Button("Add Record");
		addRecordButton.getStyleClass().add("submit-button");
		addRecordButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				HeatChartSheets hs = new HeatChartSheets();

				if (!data.isEmpty()) {
					hs.setSequenceNumber(data.size() + 1);
					hs.setSheetNumber(data.get(data.size() - 1)
							.getSheetNumber());
				}

				heatChart.getHeatChartSheets().add(hs);
				data.add(hs);
			}
		});
		final Button addSheetButton = new Button("Add Sheet");
		addSheetButton.getStyleClass().add("submit-button");
		addSheetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				HeatChartSheets hs = new HeatChartSheets();

				if (!data.isEmpty()) {
					hs.setSequenceNumber(data.size() + 1);
					hs.setSheetNumber(data.get(data.size() - 1)
							.getSheetNumber() + 1);
				}

				heatChart.getHeatChartSheets().add(hs);
				data.add(hs);
			}
		});
		final Button updateButton = new Button("Update");
		updateButton.getStyleClass().add("submit-button");
		updateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				ServiceManager.getHeatChartDetailsService(heatChart,
						PersistType.INSERT).restart();
			}
		});
		final Button printButton = new Button("Print");
		printButton.getStyleClass().add("submit-button");
		printButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				try {
					Reporter.printHeatChartReport(heatChart);
				} catch (JRException | IOException | URISyntaxException ex) {
					LoggerUtil.getLogger().debug(ex);
				}
			}
		});

		EventHandler<ActionEvent> addRecordEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				HeatChartSheets hs = new HeatChartSheets();

				if (!data.isEmpty()) {
					hs.setSequenceNumber(data.size() + 1);
					hs.setSheetNumber(data.get(data.size() - 1)
							.getSheetNumber());
				}

				heatChart.getHeatChartSheets().add(hs);
				data.add(hs);
			}
		};

		EventHandler<ActionEvent> addSheetEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				HeatChartSheets hs = new HeatChartSheets();

				if (!data.isEmpty()) {
					hs.setSequenceNumber(data.size() + 1);
					hs.setSheetNumber(data.get(data.size() - 1)
							.getSheetNumber() + 1);
				}

				heatChart.getHeatChartSheets().add(hs);
				data.add(hs);
			}
		};

		EventHandler<ActionEvent> removeRecordEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				int index = table.getSelectionModel().getSelectedIndex();
				if (index >= 0) {
					
					heatChart.getHeatChartSheets().remove(data.get(index));
					data.remove(index);
				}
			}
		};

		table.setContextMenu(TableContextMenu.getAddHeatChartContextMenu(
				addRecordEventHandler, addSheetEventHandler,
				removeRecordEventHandler));

		buttons.getChildren().addAll(addRecordButton, addSheetButton,
				updateButton, printButton);

		main.getChildren().add(buttons);

		tab.setContent(ControlsUtil.makeScrollable(main));
		getTabs().add(tab);
	}
}
