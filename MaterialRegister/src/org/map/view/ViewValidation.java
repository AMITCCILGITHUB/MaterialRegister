package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import org.map.MaterialRegister;
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
			Alert.showAlert(MaterialRegister.getMaterialRegister()
					.getPrimaryStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());
		}
	}
	
	public void createAgencyTab(){
		Tab agencyTab = new Tab("Agency");

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("View Agency");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		TableView<AgencyMaster> tableAgency = new TableView<>();
		
		TableColumn MCol1 = new TableColumn("Agency Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<AgencyMaster, Integer>(
				"agencyCode"));
		TableColumn MCol2 = new TableColumn("Agency Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<AgencyMaster, String>(
				"agencyName"));
		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<AgencyMaster, String>(
				"remarks"));
		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<AgencyMaster, String>(
				"status"));
		
		tableAgency.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
		
		ObservableList<AgencyMaster> agencyData = FXCollections
				.observableArrayList();
		agencyData.setAll(ValidationData.getAgencyList());
		tableAgency.setItems(agencyData);
		
		main.getChildren().add(tableAgency);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);
		
		agencyTab.setContent(scrollPane);
		agencyTab.setClosable(false);
		getTabs().add(agencyTab);
	}

	public void createCustomerTab(){
		Tab CustomerTab = new Tab("Customer");

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("View Customer");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		TableView<CustomerMaster> tableCustomer = new TableView<>();
		
		TableColumn MCol1 = new TableColumn("Customer Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<CustomerMaster, Integer>(
				"customerCode"));
		TableColumn MCol2 = new TableColumn("Customer Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<CustomerMaster, String>(
				"customerName"));
		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<CustomerMaster, String>(
				"remarks"));
		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<CustomerMaster, String>(
				"status"));
		
		tableCustomer.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
		
		ObservableList<CustomerMaster> customerData = FXCollections
				.observableArrayList();
		customerData.setAll(ValidationData.getCustomerList());
		tableCustomer.setItems(customerData);
		
		main.getChildren().add(tableCustomer);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);
		
		CustomerTab.setContent(scrollPane);
		CustomerTab.setClosable(false);
		getTabs().add(CustomerTab);
	}

	public void createItemTab(){
		Tab itemTab = new Tab("Item");

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("View Item");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		TableView<ItemMaster> tableItem = new TableView<>();
		
		TableColumn MCol1 = new TableColumn("Item Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ItemMaster, Integer>(
				"itemCode"));
		TableColumn MCol2 = new TableColumn("Item Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ItemMaster, String>(
				"itemName"));
		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ItemMaster, String>(
				"remarks"));
		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ItemMaster, String>(
				"status"));
		
		tableItem.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
		
		ObservableList<ItemMaster> itemData = FXCollections
				.observableArrayList();
		itemData.setAll(ValidationData.getItemList());
		tableItem.setItems(itemData);
		
		main.getChildren().add(tableItem);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);
		
		itemTab.setContent(scrollPane);
		itemTab.setClosable(false);
		getTabs().add(itemTab);
	}

	public void createLaboratoryTab(){
		Tab laboratoryTab = new Tab("Laboratory");

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("View Laboratory");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		TableView<LaboratoryMaster> tableLaboratory = new TableView<>();
		
		TableColumn MCol1 = new TableColumn("Laboratory Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<LaboratoryMaster, Integer>(
				"laboratoryCode"));
		TableColumn MCol2 = new TableColumn("Laboratory Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<LaboratoryMaster, String>(
				"laboratoryName"));
		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<LaboratoryMaster, String>(
				"remarks"));
		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<LaboratoryMaster, String>(
				"status"));
		
		tableLaboratory.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
		
		ObservableList<LaboratoryMaster> laboratoryData = FXCollections
				.observableArrayList();
		laboratoryData.setAll(ValidationData.getLaboratoryList());
		tableLaboratory.setItems(laboratoryData);
		
		main.getChildren().add(tableLaboratory);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);
		
		laboratoryTab.setContent(scrollPane);
		laboratoryTab.setClosable(false);
		getTabs().add(laboratoryTab);
	}
	
	public void createResultTab(){
		Tab resultTab = new Tab("Result");

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("View Result");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		TableView<ResultMaster> tableResult = new TableView<>();
		
		TableColumn MCol1 = new TableColumn("Result Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ResultMaster, Integer>(
				"resultCode"));
		TableColumn MCol2 = new TableColumn("Result Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ResultMaster, String>(
				"resultName"));
		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ResultMaster, String>(
				"remarks"));
		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ResultMaster, String>(
				"status"));
		
		tableResult.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
		
		ObservableList<ResultMaster> resultData = FXCollections
				.observableArrayList();
		resultData.setAll(ValidationData.getResultList());
		tableResult.setItems(resultData);
		
		main.getChildren().add(tableResult);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);
		
		resultTab.setContent(scrollPane);
		resultTab.setClosable(false);
		getTabs().add(resultTab);
	}
	
	public void createSpecificationTab(){
		Tab specificationTab = new Tab("Specification");

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("View Specification");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		TableView<SpecificationMaster> tableSpecification = new TableView<>();
		
		TableColumn MCol1 = new TableColumn("Specification Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<SpecificationMaster, Integer>(
				"specificationCode"));
		TableColumn MCol2 = new TableColumn("Specification Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<SpecificationMaster, String>(
				"specificationName"));
		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<SpecificationMaster, String>(
				"remarks"));
		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<SpecificationMaster, String>(
				"status"));
		
		tableSpecification.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
		
		ObservableList<SpecificationMaster> specificationData = FXCollections
				.observableArrayList();
		specificationData.setAll(ValidationData.getSpecificationList());
		tableSpecification.setItems(specificationData);
		
		main.getChildren().add(tableSpecification);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);
		
		specificationTab.setContent(scrollPane);
		specificationTab.setClosable(false);
		getTabs().add(specificationTab);
	}
	
	public void createTestTab(){
		Tab testTab = new Tab("Test");

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("View Test");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		TableView<TestMaster> tableTest = new TableView<>();
		
		TableColumn MCol1 = new TableColumn("Test Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<TestMaster, Integer>(
				"testCode"));
		TableColumn MCol2 = new TableColumn("Test Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<TestMaster, String>(
				"testName"));
		TableColumn MCol3 = new TableColumn("Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<TestMaster, String>(
				"remarks"));
		TableColumn MCol4 = new TableColumn("Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<TestMaster, String>(
				"status"));
		
		tableTest.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
		
		ObservableList<TestMaster> testData = FXCollections
				.observableArrayList();
		testData.setAll(ValidationData.getTestList());
		tableTest.setItems(testData);
		
		main.getChildren().add(tableTest);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);
		
		testTab.setContent(scrollPane);
		testTab.setClosable(false);
		getTabs().add(testTab);
	}
}
