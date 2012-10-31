package org.map.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import org.map.MaterialRegister;
import org.map.controls.AgencyComboBox;
import org.map.controls.CustomComboBox;
import org.map.controls.CustomerCellFactory;
import org.map.controls.DatePickerCellFactory;
import org.map.controls.IntegerBox;
import org.map.controls.ItemComboBox;
import org.map.controls.LaboratoryCellFactory;
import org.map.controls.ResultCellFactory;
import org.map.controls.SpecificationComboBox;
import org.map.controls.TestCellFactory;
import org.map.controls.TextBox;
import org.map.controls.TextCellFactory;
import org.map.controls.ViewBox;
import org.map.hibernate.dao.MaterialData;
import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.CustomerMaster;
import org.map.hibernate.ddo.CustomerProperty;
import org.map.hibernate.ddo.LaboratoryMaster;
import org.map.hibernate.ddo.LaboratoryProperty;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.ResultMaster;
import org.map.hibernate.ddo.ResultProperty;
import org.map.hibernate.ddo.TestMaster;
import org.map.hibernate.ddo.TestProperty;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.AppProperties;
import org.map.utils.ViewLayout;

public class AddMaterial extends ScrollPane {

	public AddMaterial() {

		try {
			VBox main = new VBox(ViewLayout.H_SPACE);
			main.getStyleClass().add("category-page");

			final MaterialMaster material = new MaterialMaster();
			material.setCtNumber(MaterialData.getNextCtNumber(AppProperties
					.getValue("material.current.year")));

			Label header = new Label("Add Material");
			header.getStyleClass().add("page-header");
			main.getChildren().add(header);

			Label yearCategoryHeader = new Label("Year");
			yearCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			yearCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			yearCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(yearCategoryHeader);

			HBox yearBox = new HBox(ViewLayout.H_SPACE);
			Label yearLabel = new Label("Material for Year");
			yearLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final CustomComboBox yearChoiceBox = new CustomComboBox("",
					"Material", "Material");
			yearChoiceBox.setText(AppProperties
					.getValue("material.current.year"));
			Button yearButton = new Button("Set as Default");
			yearButton.getStyleClass().add("submit-button");
			yearButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					try {
						AppProperties.setValue("material.current.year",
								yearChoiceBox.getText());

						material.setCtNumber(MaterialData
								.getNextCtNumber(AppProperties
										.getValue("material.current.year")));
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

			/*--------------------------------------*/
			Label detailHeader = new Label("Details");
			detailHeader.setMaxWidth(Double.MAX_VALUE);
			detailHeader.setMinHeight(Control.USE_PREF_SIZE);
			detailHeader.getStyleClass().add("category-header");
			main.getChildren().add(detailHeader);

			GridPane form = new GridPane();
			form.setHgap(ViewLayout.H_SPACE);
			form.setVgap(ViewLayout.V_SPACE);

			Label ctNumberLabel = new Label("CT Number");
			ctNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final ViewBox ctNumberTextField = new ViewBox(
					MaterialData.getNextCtNumber(AppProperties
							.getValue("material.current.year")),
					material.ctNumberProperty());

			Label agencyLabel = new Label("Inspection Agency");
			agencyLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final AgencyComboBox agencyTextField = new AgencyComboBox(
					"Inspection Agency", material.inspectionAgencyProperty());
			material.setInspectionAgency(ValidationData.getAgencyList().get(0));

			Label specLabel = new Label("Specification");
			specLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final SpecificationComboBox specTextField = new SpecificationComboBox(
					"Specification", material.specificationProperty());
			material.setSpecification(ValidationData.getSpecificationList()
					.get(0));

			Label itemLabel = new Label("Item");
			itemLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final ItemComboBox itemTextField = new ItemComboBox("Item Name",
					material.itemProperty());
			material.setItem(ValidationData.getItemList().get(0));

			Label sizeLabel = new Label("Size");
			sizeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final TextBox sizeTextField = new TextBox("Size",
					material.sizeProperty());

			Label quantityLabel = new Label("Quantity");
			quantityLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final IntegerBox quantityTextField = new IntegerBox("Quantity",
					material.quantityProperty());

			Label heatNumberLabel = new Label("Heat / Lot Number");
			heatNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final TextBox heatNumberTextField = new TextBox(
					"Heat / Lot Number", material.heatNumberProperty());

			Label plateNumberLabel = new Label("Plate / Product Number");
			plateNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final TextBox plateNumberTextField = new TextBox(
					"Plate / Product Number", material.plateNumberProperty());

			final TableView<MaterialMaster> table = new TableView<>();

			TableColumn MCol1 = new TableColumn("Sample Id");
			MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol1.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"sampleId"));
			MCol1.setCellFactory(new TextCellFactory());

			TableColumn MCol2 = new TableColumn("Test");
			MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol2.setCellValueFactory(new Callback<CellDataFeatures<MaterialMaster, TestMaster>, TestProperty>() {

				@Override
				public TestProperty call(
						CellDataFeatures<MaterialMaster, TestMaster> p) {

					return p.getValue().testProperty();
				}
			});
			MCol2.setCellFactory(new TestCellFactory());
			material.setTest(ValidationData.getTestList().get(0));

			TableColumn MCol3 = new TableColumn("Customer");
			MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH_MAX);
			MCol3.setCellValueFactory(new Callback<CellDataFeatures<MaterialMaster, CustomerMaster>, CustomerProperty>() {

				@Override
				public CustomerProperty call(
						CellDataFeatures<MaterialMaster, CustomerMaster> p) {

					return p.getValue().customerProperty();
				}
			});
			MCol3.setCellFactory(new CustomerCellFactory());
			material.setCustomer(ValidationData.getCustomerList().get(0));

			TableColumn MCol4 = new TableColumn("Equipments");
			MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol4.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"equipments"));
			MCol4.setCellFactory(new TextCellFactory());

			TableColumn MCol5 = new TableColumn("Laboratory");
			MCol5.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol5.setCellValueFactory(new Callback<CellDataFeatures<MaterialMaster, LaboratoryMaster>, LaboratoryProperty>() {

				@Override
				public LaboratoryProperty call(
						CellDataFeatures<MaterialMaster, LaboratoryMaster> p) {

					return p.getValue().laboratoryProperty();
				}
			});
			MCol5.setCellFactory(new LaboratoryCellFactory());
			material.setLaboratory(ValidationData.getLaboratoryList().get(0));

			TableColumn MCol6 = new TableColumn("Report Date");
			MCol6.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol6.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"reportDate"));
			MCol6.setCellFactory(new DatePickerCellFactory());

			TableColumn MCol7 = new TableColumn("Report Number");
			MCol7.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol7.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"reportNumber"));
			MCol7.setCellFactory(new TextCellFactory());

			TableColumn MCol8 = new TableColumn("Result");
			MCol8.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol8.setCellValueFactory(new Callback<CellDataFeatures<MaterialMaster, ResultMaster>, ResultProperty>() {

				@Override
				public ResultProperty call(
						CellDataFeatures<MaterialMaster, ResultMaster> p) {

					return p.getValue().resultProperty();
				}
			});
			MCol8.setCellFactory(new ResultCellFactory());
			material.setResult(ValidationData.getResultList().get(0));

			TableColumn MCol9 = new TableColumn("Remarks");
			MCol9.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol9.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"remarks"));
			MCol9.setCellFactory(new TextCellFactory());

			TableColumn MCol10 = new TableColumn("Witnessed By");
			MCol10.setPrefWidth(ViewLayout.COLUMN_WIDTH);
			MCol10.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"witnessedBy"));
			MCol10.setCellFactory(new TextCellFactory());

			TableColumn MCol11 = new TableColumn("Failure Reason");
			MCol11.setPrefWidth(ViewLayout.COLUMN_WIDTH * 2);
			MCol11.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"failureReason"));
			MCol11.setCellFactory(new TextCellFactory());

			table.getColumns().addAll(MCol1, MCol2, MCol3, MCol4, MCol5, MCol6,
					MCol7, MCol8, MCol9, MCol10, MCol11);

			ObservableList<MaterialMaster> materialData = FXCollections
					.observableArrayList(material);
			table.setItems(materialData);

			form.add(ctNumberLabel, 0, 0);
			form.add(ctNumberTextField, 1, 0);
			form.add(agencyLabel, 2, 0);
			form.add(agencyTextField, 3, 0);
			form.add(specLabel, 4, 0);
			form.add(specTextField, 5, 0);
			form.add(itemLabel, 0, 1);
			form.add(itemTextField, 1, 1);
			form.add(sizeLabel, 2, 1);
			form.add(sizeTextField, 3, 1);
			form.add(quantityLabel, 4, 1);
			form.add(quantityTextField, 5, 1);
			form.add(heatNumberLabel, 0, 2);
			form.add(heatNumberTextField, 1, 2);
			form.add(plateNumberLabel, 2, 2);
			form.add(plateNumberTextField, 3, 2);

			main.getChildren().addAll(form, table);

			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button submitButton = new Button("Submit");
			submitButton.getStyleClass().add("submit-button");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					MaterialData.insertMaterialDetails(material);
				}
			});

			buttons.getChildren().addAll(submitButton);
			main.getChildren().add(buttons);

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
