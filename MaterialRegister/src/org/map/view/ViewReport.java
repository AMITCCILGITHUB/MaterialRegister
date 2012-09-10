package org.map.view;

import java.util.List;

import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.map.MaterialRegister;
import org.map.calendar.DatePicker;
import org.map.controls.TextBox;
import org.map.hibernate.dao.MaterialData;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.MaterialTestMap;
import org.map.logger.LoggerUtil;
import org.map.login.Login;
import org.map.service.PrintMaterialRegister;
import org.map.utils.Alert;
import org.map.utils.TableContextMenu;

public class ViewReport {

	private static ViewReport viewReport;
	private double LABEL_WIDTH = 100;
	private double H_SPACE = 8;
	private double COLUMN_WIDTH = 100;
	private double COLUMN_WIDTH_MAX = 120;
	private ObservableList<MaterialMaster> data = FXCollections
			.observableArrayList(MaterialData.getMaterialList());
	private PrintMaterialRegister pmr = new PrintMaterialRegister();
	private HostServices hostServices;

	public ViewReport() {
		hostServices = Login.getLoginPanel().getHostServices();
	}

	public static ViewReport getViewReport() {
		return viewReport;
	}

	public ObservableList<MaterialMaster> getData() {
		return data;
	}

	public Node createView() {
		viewReport = this;
		try {
			final VBox main = new VBox(H_SPACE) {

				@Override
				protected double computePrefHeight(double width) {
					return Math.max(super.computePrefHeight(width), getParent()
							.getBoundsInLocal().getHeight());
				}
			};
			main.getStyleClass().add("category-page");

			Label header = new Label("View Report");
			header.getStyleClass().add("page-header");
			main.getChildren().add(header);

			Label detailCategoryHeader = new Label("Material Register");
			detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			detailCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(detailCategoryHeader);

			final HBox search1 = new HBox(H_SPACE);
			Label ctNumberFromLabel = new Label("CT Number From");
			ctNumberFromLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox ctNumberFromTextField = new TextBox("",
					"CT Number From");
			Label ctNumberToLabel = new Label("CT Number To");
			ctNumberToLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox ctNumberToTextField = new TextBox("", "CT Number To");
			final Button searchRecordButton1 = new Button("Search");
			searchRecordButton1.getStyleClass().add("submit-button");
			search1.getChildren().addAll(ctNumberFromLabel,
					ctNumberFromTextField, ctNumberToLabel,
					ctNumberToTextField, searchRecordButton1);
			main.getChildren().add(search1);

			final HBox search2 = new HBox(H_SPACE);
			Label fromDateLabel = new Label("Date From");
			fromDateLabel.setPrefWidth(LABEL_WIDTH);
			final DatePicker fromDateTextField = new DatePicker();
			Label toDateLabel = new Label("Date");
			toDateLabel.setPrefWidth(LABEL_WIDTH);
			final DatePicker toDateTextField = new DatePicker();
			final Button searchRecordButton2 = new Button("Search");
			searchRecordButton2.getStyleClass().add("submit-button");
			search2.getChildren().addAll(fromDateLabel, fromDateTextField,
					toDateLabel, toDateTextField, searchRecordButton2);
			main.getChildren().add(search2);

			final TableView<MaterialMaster> table = new TableView<>();
			TableColumn Col1 = new TableColumn("CT Number");
			Col1.setPrefWidth(COLUMN_WIDTH);
			Col1.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"ctNumber"));
			TableColumn Col2 = new TableColumn("Inspection Agency");
			Col2.setPrefWidth(COLUMN_WIDTH_MAX);
			Col2.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"inspectionAgency"));
			TableColumn Col3 = new TableColumn("Description");
			TableColumn Col31 = new TableColumn("Item");
			Col31.setPrefWidth(COLUMN_WIDTH);
			Col31.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"item"));
			TableColumn Col32 = new TableColumn("Size");
			Col32.setPrefWidth(COLUMN_WIDTH);
			Col32.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"size"));
			TableColumn Col33 = new TableColumn("Quantity");
			Col33.setPrefWidth(COLUMN_WIDTH);
			Col33.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"quantity"));
			TableColumn Col34 = new TableColumn("Heat / Lot Number");
			Col34.setPrefWidth(COLUMN_WIDTH_MAX);
			Col34.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"heatNumber"));
			TableColumn Col35 = new TableColumn("Plate / Product Number");
			Col35.setPrefWidth(COLUMN_WIDTH_MAX);
			Col35.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"plateNumber"));
			Col3.getColumns().addAll(Col31, Col32, Col33, Col34, Col35);
			TableColumn Col4 = new TableColumn("Specification");
			Col4.setPrefWidth(COLUMN_WIDTH);
			Col4.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"specification"));
			TableColumn Col5 = new TableColumn("Tests");
			Col5.setPrefWidth(COLUMN_WIDTH);
			Col5.setCellValueFactory(new PropertyValueFactory<MaterialMaster, MaterialTestMap>(
					"tests"));
			TableColumn Col6 = new TableColumn("Customer");
			Col6.setPrefWidth(COLUMN_WIDTH);
			Col6.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"customer"));
			TableColumn Col7 = new TableColumn("Equipments");
			Col7.setPrefWidth(COLUMN_WIDTH);
			Col7.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"equipments"));
			TableColumn Col8 = new TableColumn("Laboratory");
			Col8.setPrefWidth(COLUMN_WIDTH);
			Col8.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"laboratory"));
			TableColumn Col9 = new TableColumn("Report");
			TableColumn Col91 = new TableColumn("Number");
			Col91.setPrefWidth(COLUMN_WIDTH);
			Col91.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"reportNumber"));
			TableColumn Col92 = new TableColumn("Date");
			Col92.setPrefWidth(COLUMN_WIDTH);
			Col92.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"reportDate"));
			Col9.getColumns().addAll(Col91, Col92);
			TableColumn Col10 = new TableColumn("Remarks");
			Col10.setPrefWidth(COLUMN_WIDTH);
			Col10.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"remarks"));
			TableColumn Col11 = new TableColumn("Witnessed By");
			Col11.setPrefWidth(COLUMN_WIDTH);
			Col11.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"witnessedBy"));
			table.getColumns().addAll(Col1, Col2, Col3, Col4, Col5, Col6, Col7,
					Col8, Col9, Col10, Col11);

			table.setItems(data);

			searchRecordButton1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					try {
						List<MaterialMaster> materials = MaterialData
								.searchMaterialDetailsCt(
										ctNumberFromTextField.getText(),
										ctNumberToTextField.getText(), true);
						data.setAll(materials);
					} catch (Exception ex) {
						Alert.showAlert(
								MaterialRegister.getMaterialRegister()
										.getPrimaryStage(),
								"Error",
								"Error",
								"Some error occured. Details...\n"
										+ ex.getMessage());
						LoggerUtil.getLogger().debug(ex);
					}
				}
			});

			searchRecordButton2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					try {
						List<MaterialMaster> materials = MaterialData.searchMaterialDetailsDt(
								fromDateTextField.getSelectedDate(),
								toDateTextField.getSelectedDate(), true);
						data.setAll(materials);
					} catch (Exception ex) {
						Alert.showAlert(
								MaterialRegister.getMaterialRegister()
										.getPrimaryStage(),
								"Error",
								"Error",
								"Some error occured. Details...\n"
										+ ex.getMessage());
						LoggerUtil.getLogger().debug(ex);
					}
				}
			});

			final HBox buttons = new HBox(H_SPACE);
			buttons.setTranslateY(32);
			final Button printButton = new Button("Print");
			printButton.getStyleClass().add("submit-button");
			printButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					MaterialRegister.getMaterialRegister().getStatusBar()
							.show();
					pmr.restart();
				}
			});
			buttons.getChildren().addAll(printButton);
			main.getChildren().addAll(table, buttons);

			EventHandler printEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					pmr.restart();
				}
			};
			table.setContextMenu(new TableContextMenu(printEventHandler,
					"Print"));

			pmr.stateProperty().addListener(new ChangeListener<Worker.State>() {

				@Override
				public void changed(
						ObservableValue<? extends Worker.State> observable,
						Worker.State oldValue, Worker.State newState) {
					if (newState == Worker.State.SUCCEEDED) {
						MaterialRegister.getMaterialRegister().getStatusBar()
								.hide();
						Alert.showAlert(
								MaterialRegister.getMaterialRegister()
										.getPrimaryStage(),
								"Alert",
								"Alert",
								"The report has been saved as "
										+ pmr.getValue());
						hostServices.showDocument(pmr.getValue());
					}
				}
			});

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
