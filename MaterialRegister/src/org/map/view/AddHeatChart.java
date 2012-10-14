package org.map.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;

import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;

import org.map.MaterialRegister;
import org.map.controls.CustomComboBox;
import org.map.controls.ETFCellFactory;
import org.map.controls.TextBox;
import org.map.hibernate.dao.HeatChartData;
import org.map.hibernate.dao.Reporter;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.ddo.HeatChartSheets;
import org.map.hibernate.ddo.HeatChartSheetsId;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.AppProperties;

public class AddHeatChart {

	private static AddHeatChart viewHeatChart;
	private double COLUMN_WIDTH = 100;
	private double COLUMN_WIDTH_MAX = 120;
	private double LABEL_WIDTH = 100;
	private double H_SPACE = 8;
	private ObservableList<HeatChartSheets> data;

	public AddHeatChart() {

		viewHeatChart = this;
	}

	public static AddHeatChart getViewHeatChart() {

		return viewHeatChart;
	}

	public Node createView() {

		try {
			final VBox main = new VBox(H_SPACE) {

				@Override
				protected double computePrefHeight(double width) {

					return Math.max(super.computePrefHeight(width), getParent()
							.getBoundsInLocal().getHeight());
				}
			};
			VBox.setVgrow(main, Priority.ALWAYS);
			main.getStyleClass().add("category-page");

			final HeatChartMaster heatChart = new HeatChartMaster();
			heatChart.setChartNumber(HeatChartData
					.getNextChartNumber(AppProperties
							.getValue("heatchart.current.year")));

			Label header = new Label("Add Heat Chart");
			header.getStyleClass().add("page-header");
			main.getChildren().add(header);

			HBox yearBox = new HBox(H_SPACE * 4.5 - 20);
			Label yearLabel = new Label("Heat Chart for Year");
			yearLabel.setPrefWidth(LABEL_WIDTH + 20);
			final CustomComboBox yearChoiceBox = new CustomComboBox("",
					"HeatChart", "HeatChart");
			yearChoiceBox.setText(AppProperties
					.getValue("heatchart.current.year"));
			Button yearButton = new Button("Set as Default");
			yearButton.getStyleClass().add("submit-button");
			yearButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					try {
						AppProperties.setValue("heatchart.current.year",
								yearChoiceBox.getText());

						heatChart.setChartNumber(HeatChartData
								.getNextChartNumber(AppProperties
										.getValue("heatchart.current.year")));
					} catch (IOException ex) {
						LoggerUtil.getLogger().debug(ex);
						Alert.showAlert(
								MaterialRegister.getMaterialRegister()
										.getPrimaryStage(),
								"Error",
								"Error",
								"Some error occured. Details...\n"
										+ ex.getMessage());
					}
				}
			});
			yearBox.getChildren().addAll(yearLabel, yearChoiceBox, yearButton);
			main.getChildren().add(yearBox);

			HBox detailCategoryBox = new HBox();
			Label detailCategoryHeader1 = new Label("Details - ");
			detailCategoryHeader1.setMaxWidth(Double.MAX_VALUE);
			detailCategoryHeader1.setMinHeight(Control.USE_PREF_SIZE);
			Label detailCategoryHeader2 = new Label(heatChart.getChartNumber());
			detailCategoryHeader2.textProperty().bindBidirectional(
					heatChart.chartNumberProperty());
			detailCategoryHeader2.setMaxWidth(Double.MAX_VALUE);
			detailCategoryHeader2.setMinHeight(Control.USE_PREF_SIZE);
			detailCategoryBox.getStyleClass().add("category-header");
			detailCategoryBox.getChildren().addAll(detailCategoryHeader1,
					detailCategoryHeader2);
			main.getChildren().add(detailCategoryBox);

			final HBox detail1 = new HBox(H_SPACE * 4.5);
			Label equipmentLabel = new Label("Equipment");
			equipmentLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox equipmentTextField = new TextBox("", "Equipment",
					heatChart.equipmentProperty(), true);
			Label customerLabel = new Label("Customer");
			customerLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox customerTextField = new TextBox("", "Customer",
					heatChart.customerProperty(), true);
			Label poDetailsLabel = new Label("PO Details");
			poDetailsLabel.setPrefWidth(LABEL_WIDTH);
			detail1.getChildren().addAll(equipmentLabel, equipmentTextField,
					customerLabel, customerTextField, poDetailsLabel);
			main.getChildren().add(detail1);

			final HBox detail2 = new HBox(H_SPACE * 4.5);
			Label drawingLabel = new Label("Drawing No.");
			drawingLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox drawingTextField = new TextBox("", "Drawing No.",
					heatChart.drawingNumberProperty(), true);
			Label suryeyorLabel = new Label("Surveyor");
			suryeyorLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox suryeyorTextField = new TextBox("", "Suryeyor",
					heatChart.surveyorProperty(), true);
			final TextBox poDetailsTextField = new TextBox("", "PO Details",
					heatChart.poDetailsProperty(), true);
			detail2.getChildren().addAll(drawingLabel, drawingTextField,
					suryeyorLabel, suryeyorTextField, poDetailsTextField);
			main.getChildren().add(detail2);

			final TableView<HeatChartSheets> table = new TableView<>();
			TableColumn Col1 = new TableColumn("Sr. No.");
			Col1.setPrefWidth(COLUMN_WIDTH);
			Col1.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, Integer>(
					"sequenceNumber"));
			Col1.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, Integer>, ObservableIntegerValue>() {

				@Override
				public ObservableIntegerValue call(
						CellDataFeatures<HeatChartSheets, Integer> p) {

					return p.getValue().sequenceNumberProperty();
				}
			});
			TableColumn Col2 = new TableColumn("Sheet No.");
			Col2.setPrefWidth(COLUMN_WIDTH);
			Col2.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, HeatChartSheetsId>(
					"sheetNumber"));
			Col2.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, Integer>, ObservableIntegerValue>() {

				@Override
				public ObservableIntegerValue call(
						CellDataFeatures<HeatChartSheets, Integer> p) {

					return p.getValue().getId().sheetNumberProperty();
				}
			});
			TableColumn Col3 = new TableColumn("Part No");
			Col3.setPrefWidth(COLUMN_WIDTH);
			Col3.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
					"partNumber"));
			Col3.setCellFactory(new ETFCellFactory("PartNumber"));
			TableColumn Col4 = new TableColumn("Part Name(s)");
			Col4.setPrefWidth(COLUMN_WIDTH_MAX);
			Col4.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
					"partName"));
			Col4.setCellFactory(new ETFCellFactory("PartName"));
			TableColumn Col5 = new TableColumn("Material Specification");
			Col5.setPrefWidth(COLUMN_WIDTH);
			TableColumn Col51 = new TableColumn("Specified");
			Col51.setPrefWidth(COLUMN_WIDTH);
			TableColumn Col511 = new TableColumn("Size");
			Col511.setPrefWidth(COLUMN_WIDTH);
			Col511.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
					"speciedSize"));
			Col511.setCellFactory(new ETFCellFactory("SpeciedSize"));
			TableColumn Col512 = new TableColumn("Grade");
			Col512.setPrefWidth(COLUMN_WIDTH);
			Col512.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
					"speciedGrade"));
			Col512.setCellFactory(new ETFCellFactory("SpeciedGrade"));
			TableColumn Col52 = new TableColumn("Utilized");
			Col52.setPrefWidth(COLUMN_WIDTH);
			TableColumn Col521 = new TableColumn("Size");
			Col521.setPrefWidth(COLUMN_WIDTH);
			Col521.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, MaterialMaster>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(
						CellDataFeatures<HeatChartSheets, MaterialMaster> p) {

					return p.getValue().getMaterialmaster().sizeProperty();
				}
			});
			TableColumn Col522 = new TableColumn("Grade");
			Col522.setPrefWidth(COLUMN_WIDTH);
			Col522.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, MaterialMaster>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(
						CellDataFeatures<HeatChartSheets, MaterialMaster> p) {

					return p.getValue().getMaterialmaster()
							.specificationProperty();
				}
			});
			TableColumn Col53 = new TableColumn("Check / Testing");
			Col53.setPrefWidth(COLUMN_WIDTH_MAX);
			Col53.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, HeatChartSheetsId>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(
						CellDataFeatures<HeatChartSheets, HeatChartSheetsId> p) {

					return p.getValue().getId().ctNumberProperty();
				}
			});
			Col53.setCellFactory(new ETFCellFactory("CTNumberAdd"));
			Col51.getColumns().addAll(Col511, Col512);
			Col52.getColumns().addAll(Col521, Col522);
			Col5.getColumns().addAll(Col51, Col52, Col53);
			TableColumn Col6 = new TableColumn("Test Certificate");
			Col6.setPrefWidth(COLUMN_WIDTH);
			TableColumn Col61 = new TableColumn("Number");
			Col61.setPrefWidth(COLUMN_WIDTH);
			Col61.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, MaterialMaster>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(
						CellDataFeatures<HeatChartSheets, MaterialMaster> p) {

					return p.getValue().getMaterialmaster()
							.reportNumberProperty();
				}
			});
			TableColumn Col62 = new TableColumn("Date");
			Col62.setPrefWidth(COLUMN_WIDTH);
			Col62.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, MaterialMaster>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(
						CellDataFeatures<HeatChartSheets, MaterialMaster> p) {

					return p.getValue().getMaterialmaster()
							.reportDateProperty();
				}
			});
			TableColumn Col63 = new TableColumn("Laboratory");
			Col63.setPrefWidth(COLUMN_WIDTH);
			Col63.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, MaterialMaster>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(
						CellDataFeatures<HeatChartSheets, MaterialMaster> p) {

					return p.getValue().getMaterialmaster()
							.laboratoryProperty();
				}
			});
			Col6.getColumns().addAll(Col61, Col62, Col63);
			table.getColumns().addAll(Col1, Col2, Col3, Col4, Col5, Col6);

			HeatChartSheets hs = new HeatChartSheets();
			hs.getId().setChartNumber(heatChart.getChartNumber());
			data = FXCollections.observableArrayList(hs);
			table.setItems(data);

			final HBox buttons = new HBox(H_SPACE);
			buttons.setTranslateY(32);
			final Button addSheetButton = new Button("Add Sheet");
			addSheetButton.getStyleClass().add("submit-button");
			addSheetButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					HeatChartSheets hs = new HeatChartSheets();
					hs.setSequenceNumber(data.size() + 1);
					hs.getId().setChartNumber(heatChart.getChartNumber());
					hs.getId()
							.setSheetNumber(
									data.get(data.size() - 1).getId()
											.getSheetNumber() + 1);
					data.add(hs);
				}
			});
			final Button addRecordButton = new Button("Add Record");
			addRecordButton.getStyleClass().add("submit-button");
			addRecordButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					HeatChartSheets hs = new HeatChartSheets();
					hs.setSequenceNumber(data.size() + 1);
					hs.getId().setChartNumber(heatChart.getChartNumber());
					hs.getId().setSheetNumber(
							data.get(data.size() - 1).getId().getSheetNumber());
					data.add(hs);
				}
			});
			final Button saveRecordButton = new Button("Save Heat Chart");
			saveRecordButton.getStyleClass().add("submit-button");
			saveRecordButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					heatChart.setHeatchartsheets(new HashSet<>(data.subList(0,
							data.size())));
					HeatChartData.insertHeatChart(heatChart);

					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Alert", "Alert",
							"Heat Chart details saved successfully.");

					MaterialRegister.getMaterialRegister().reloadPage(
							"Add Validation");
				}
			});
			final Button printButton = new Button("Print");
			printButton.getStyleClass().add("submit-button");
			printButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					heatChart.setHeatchartsheets(new HashSet<>(data.subList(0,
							data.size())));
					try {
						Reporter.printHeatChartReport(heatChart);
					} catch (JRException | IOException | URISyntaxException ex) {
						LoggerUtil.getLogger().debug(ex);
					}
				}
			});

			buttons.getChildren().addAll(addSheetButton, addRecordButton,
					saveRecordButton, printButton);

			ScrollPane tableScrollpane = new ScrollPane();
			tableScrollpane.setPrefHeight(300);
			tableScrollpane.setContent(table);
			main.getChildren().addAll(tableScrollpane, buttons);

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

	public void updateTable(MaterialMaster material, int rowIndex) {

		HeatChartSheets heatChartSheet = data.get(rowIndex);
		heatChartSheet.getMaterialmaster().setSize(material.getSize());
		heatChartSheet.getMaterialmaster().setSpecification(
				material.getSpecification());
		heatChartSheet.getMaterialmaster().setReportNumber(
				material.getReportNumber());
		heatChartSheet.getMaterialmaster().setReportDate(
				material.getReportDate());
		heatChartSheet.getMaterialmaster().setLaboratory(
				material.getLaboratory());
	}
}
