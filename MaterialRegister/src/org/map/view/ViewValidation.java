package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.AgencyMaster;
import org.map.hibernate.ddo.CustomerMaster;
import org.map.hibernate.ddo.ItemMaster;
import org.map.hibernate.ddo.LaboratoryMaster;
import org.map.hibernate.ddo.ResultMaster;
import org.map.hibernate.ddo.SpecificationMaster;
import org.map.hibernate.ddo.TestMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.ViewLayout;

public class ViewValidation extends TabPane {

	public ViewValidation() {

		try {
			createAgencyTab();

			createCustomerTab();

			createItemTab();

			createLaboratoryTab();

			createResultTab();

			createSpecificationTab();

			createTestTab();
		} catch (Exception e) {
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());
		}
	}

	public void createAgencyTab() {

		Tab agencyTab = new Tab("Agency");

		VBox main = ViewLayout.getMainVBox("Agency", "Details");

		TableView<AgencyMaster> tableAgency = new TableView<>();

		TableColumn MCol1 = new TableColumn("Agency Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<AgencyMaster, Integer>(
				"agencyCode"));
		MCol1.prefWidthProperty().bind(tableAgency.widthProperty().divide(4));

		TableColumn MCol2 = new TableColumn("Agency Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<AgencyMaster, String>(
				"agencyName"));
		MCol2.prefWidthProperty().bind(tableAgency.widthProperty().divide(4));

		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<AgencyMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(tableAgency.widthProperty().divide(4));

		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<AgencyMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(tableAgency.widthProperty().divide(4));

		tableAgency.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<AgencyMaster> agencyData = FXCollections
				.observableArrayList();
		agencyData.setAll(ValidationData.getAgencyList());
		tableAgency.setItems(agencyData);

		main.getChildren().add(tableAgency);

		agencyTab.setContent(ControlsUtil.makeScrollable(main));
		agencyTab.setClosable(false);
		getTabs().add(agencyTab);
	}

	public void createCustomerTab() {

		Tab CustomerTab = new Tab("Customer");

		VBox main = ViewLayout.getMainVBox("Customer", "Details");

		TableView<CustomerMaster> tableCustomer = new TableView<>();

		TableColumn MCol1 = new TableColumn("Customer Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<CustomerMaster, Integer>(
				"customerCode"));
		MCol1.prefWidthProperty().bind(tableCustomer.widthProperty().divide(4));

		TableColumn MCol2 = new TableColumn("Customer Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<CustomerMaster, String>(
				"customerName"));
		MCol2.prefWidthProperty().bind(tableCustomer.widthProperty().divide(4));

		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<CustomerMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(tableCustomer.widthProperty().divide(4));

		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<CustomerMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(tableCustomer.widthProperty().divide(4));

		tableCustomer.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<CustomerMaster> customerData = FXCollections
				.observableArrayList();
		customerData.setAll(ValidationData.getCustomerList());
		tableCustomer.setItems(customerData);

		main.getChildren().add(tableCustomer);

		CustomerTab.setContent(ControlsUtil.makeScrollable(main));
		CustomerTab.setClosable(false);
		getTabs().add(CustomerTab);
	}

	public void createItemTab() {

		Tab itemTab = new Tab("Item");

		VBox main = ViewLayout.getMainVBox("Item", "Details");

		TableView<ItemMaster> tableItem = new TableView<>();

		TableColumn MCol1 = new TableColumn("Item Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ItemMaster, Integer>(
				"itemCode"));
		MCol1.prefWidthProperty().bind(tableItem.widthProperty().divide(4));

		TableColumn MCol2 = new TableColumn("Item Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ItemMaster, String>(
				"itemName"));
		MCol2.prefWidthProperty().bind(tableItem.widthProperty().divide(4));

		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ItemMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(tableItem.widthProperty().divide(4));

		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ItemMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(tableItem.widthProperty().divide(4));

		tableItem.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<ItemMaster> itemData = FXCollections
				.observableArrayList();
		itemData.setAll(ValidationData.getItemList());
		tableItem.setItems(itemData);

		main.getChildren().add(tableItem);

		itemTab.setContent(ControlsUtil.makeScrollable(main));
		itemTab.setClosable(false);
		getTabs().add(itemTab);
	}

	public void createLaboratoryTab() {

		Tab laboratoryTab = new Tab("Laboratory");

		VBox main = ViewLayout.getMainVBox("Laboratory", "Details");

		TableView<LaboratoryMaster> tableLaboratory = new TableView<>();

		TableColumn MCol1 = new TableColumn("Laboratory Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<LaboratoryMaster, Integer>(
				"laboratoryCode"));
		MCol1.prefWidthProperty().bind(
				tableLaboratory.widthProperty().divide(4));

		TableColumn MCol2 = new TableColumn("Laboratory Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<LaboratoryMaster, String>(
				"laboratoryName"));
		MCol2.prefWidthProperty().bind(
				tableLaboratory.widthProperty().divide(4));

		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<LaboratoryMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(
				tableLaboratory.widthProperty().divide(4));

		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<LaboratoryMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(
				tableLaboratory.widthProperty().divide(4));

		tableLaboratory.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<LaboratoryMaster> laboratoryData = FXCollections
				.observableArrayList();
		laboratoryData.setAll(ValidationData.getLaboratoryList());
		tableLaboratory.setItems(laboratoryData);

		main.getChildren().add(tableLaboratory);

		laboratoryTab.setContent(ControlsUtil.makeScrollable(main));
		laboratoryTab.setClosable(false);
		getTabs().add(laboratoryTab);
	}

	public void createResultTab() {

		Tab resultTab = new Tab("Result");

		VBox main = ViewLayout.getMainVBox("Result", "Details");

		TableView<ResultMaster> tableResult = new TableView<>();

		TableColumn MCol1 = new TableColumn("Result Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ResultMaster, Integer>(
				"resultCode"));
		MCol1.prefWidthProperty().bind(tableResult.widthProperty().divide(4));

		TableColumn MCol2 = new TableColumn("Result Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ResultMaster, String>(
				"resultName"));
		MCol2.prefWidthProperty().bind(tableResult.widthProperty().divide(4));

		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ResultMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(tableResult.widthProperty().divide(4));

		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ResultMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(tableResult.widthProperty().divide(4));

		tableResult.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<ResultMaster> resultData = FXCollections
				.observableArrayList();
		resultData.setAll(ValidationData.getResultList());
		tableResult.setItems(resultData);

		main.getChildren().add(tableResult);

		resultTab.setContent(ControlsUtil.makeScrollable(main));
		resultTab.setClosable(false);
		getTabs().add(resultTab);
	}

	public void createSpecificationTab() {

		Tab specificationTab = new Tab("Specification");

		VBox main = ViewLayout.getMainVBox("Specification", "Details");

		TableView<SpecificationMaster> tableSpecification = new TableView<>();

		TableColumn MCol1 = new TableColumn("Specification Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<SpecificationMaster, Integer>(
				"specificationCode"));
		MCol1.prefWidthProperty().bind(
				tableSpecification.widthProperty().divide(4));

		TableColumn MCol2 = new TableColumn("Specification Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<SpecificationMaster, String>(
				"specificationName"));
		MCol2.prefWidthProperty().bind(
				tableSpecification.widthProperty().divide(4));

		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<SpecificationMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(
				tableSpecification.widthProperty().divide(4));

		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<SpecificationMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(
				tableSpecification.widthProperty().divide(4));

		tableSpecification.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<SpecificationMaster> specificationData = FXCollections
				.observableArrayList();
		specificationData.setAll(ValidationData.getSpecificationList());
		tableSpecification.setItems(specificationData);

		main.getChildren().add(tableSpecification);

		specificationTab.setContent(ControlsUtil.makeScrollable(main));
		specificationTab.setClosable(false);
		getTabs().add(specificationTab);
	}

	public void createTestTab() {

		Tab testTab = new Tab("Test");

		VBox main = ViewLayout.getMainVBox("Test", "Details");

		TableView<TestMaster> tableTest = new TableView<>();

		TableColumn MCol1 = new TableColumn("Test Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<TestMaster, Integer>(
				"testCode"));
		MCol1.prefWidthProperty().bind(tableTest.widthProperty().divide(4));

		TableColumn MCol2 = new TableColumn("Test Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<TestMaster, String>(
				"testName"));
		MCol2.prefWidthProperty().bind(tableTest.widthProperty().divide(4));

		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<TestMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(tableTest.widthProperty().divide(4));

		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<TestMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(tableTest.widthProperty().divide(4));

		tableTest.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<TestMaster> testData = FXCollections
				.observableArrayList();
		testData.setAll(ValidationData.getTestList());
		tableTest.setItems(testData);

		main.getChildren().add(tableTest);

		testTab.setContent(ControlsUtil.makeScrollable(main));
		testTab.setClosable(false);
		getTabs().add(testTab);
	}
}
