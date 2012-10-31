package org.map.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.map.MaterialRegister;
import org.map.controls.TextBox;
import org.map.controls.ViewIntegerBox;
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

public class AddValidation extends TabPane {

	public AddValidation() {

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

	public void createAgencyTab() {

		Tab agencyTab = new Tab("Agency");
		final AgencyMaster agency = new AgencyMaster();

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("Add Agency");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		GridPane agencyForm = new GridPane();
		agencyForm.setHgap(ViewLayout.H_SPACE);
		agencyForm.setVgap(ViewLayout.V_SPACE);

		Label agencyCodeLabel = new Label("Agency Code");
		agencyCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox agencyCodeBox = new ViewIntegerBox(
				agency.getAgencyCode(), agency.agencyCodeProperty());

		agency.setAgencyCode(ValidationData.getNextValidationNumber("Agency"));

		Label agencyNameLabel = new Label("Agency Name");
		agencyNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox agencyNameBox = new TextBox("Agency Name",
				agency.agencyNameProperty());

		Label agencyRemarksLabel = new Label("Remarks");
		agencyRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox agencyRemarksTextBox = new TextBox("Remarks",
				agency.remarksProperty());

		Button agencyButton = new Button("Submit");
		agencyButton.getStyleClass().add("submit-button");
		agencyButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (agencyNameBox.getText().trim().length() > 0) {
					if (agencyRemarksTextBox.getText().trim().length() == 0) {
						agency.setRemarks(agency.getAgencyName());
					}

					ValidationData.insertAgency(agency);

					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Alert", "Alert",
							"Agency saved successfully.");

					agency.reset();
					agency.setAgencyCode(ValidationData
							.getNextValidationNumber("Agency"));
				} else {
					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Error", "Error",
							"Please enter agency name.");
				}
			}
		});

		agencyForm.add(agencyCodeLabel, 0, 0);
		agencyForm.add(agencyCodeBox, 1, 0);
		agencyForm.add(agencyNameLabel, 0, 1);
		agencyForm.add(agencyNameBox, 1, 1);
		agencyForm.add(agencyRemarksLabel, 0, 2);
		agencyForm.add(agencyRemarksTextBox, 1, 2);
		agencyForm.add(agencyButton, 1, 3);

		main.getChildren().add(agencyForm);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);

		agencyTab.setContent(scrollPane);
		agencyTab.setClosable(false);
		getTabs().add(agencyTab);
	}

	public void createCustomerTab() {

		Tab CustomerTab = new Tab("Customer");
		final CustomerMaster customer = new CustomerMaster();

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("Add Customer");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		GridPane customerForm = new GridPane();
		customerForm.setHgap(ViewLayout.H_SPACE);
		customerForm.setVgap(ViewLayout.V_SPACE);

		Label customerCodeLabel = new Label("Customer Code");
		customerCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox customerCodeBox = new ViewIntegerBox(
				customer.getCustomerCode(), customer.customerCodeProperty());

		customerCodeBox.setValue(ValidationData
				.getNextValidationNumber("Customer"));

		Label customerNameLabel = new Label("Customer Name");
		customerNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox customerNameBox = new TextBox("Customer Name",
				customer.customerNameProperty());

		Label customerRemarksLabel = new Label("Remarks");
		customerRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox customerRemarksTextBox = new TextBox("Remarks",
				customer.remarksProperty());

		Button customerButton = new Button("Submit");
		customerButton.getStyleClass().add("submit-button");
		customerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (customerNameBox.getText().trim().length() > 0) {
					if (customerRemarksTextBox.getText().trim().length() == 0) {
						customer.setRemarks(customer.getCustomerName());
					}

					ValidationData.insertCustomer(customer);

					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Alert", "Alert",
							"Customer saved successfully.");

					customer.reset();
					customer.setCustomerCode(ValidationData
							.getNextValidationNumber("Customer"));
				} else {
					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Error", "Error",
							"Please enter customer name.");
				}
			}
		});

		customerForm.add(customerCodeLabel, 0, 0);
		customerForm.add(customerCodeBox, 1, 0);
		customerForm.add(customerNameLabel, 0, 1);
		customerForm.add(customerNameBox, 1, 1);
		customerForm.add(customerRemarksLabel, 0, 2);
		customerForm.add(customerRemarksTextBox, 1, 2);
		customerForm.add(customerButton, 1, 3);

		main.getChildren().add(customerForm);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);

		CustomerTab.setContent(scrollPane);
		CustomerTab.setClosable(false);
		getTabs().add(CustomerTab);
	}

	public void createItemTab() {

		Tab itemTab = new Tab("Item");
		final ItemMaster item = new ItemMaster();

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("Add Item");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		GridPane itemForm = new GridPane();
		itemForm.setHgap(ViewLayout.H_SPACE);
		itemForm.setVgap(ViewLayout.V_SPACE);

		Label itemCodeLabel = new Label("Item Code");
		itemCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox itemCodeBox = new ViewIntegerBox(
				item.getItemCode(), item.itemCodeProperty());

		itemCodeBox.setValue(ValidationData.getNextValidationNumber("Item"));

		Label itemNameLabel = new Label("Item Name");
		itemNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox itemNameBox = new TextBox("Item Name",
				item.itemNameProperty());

		Label itemRemarksLabel = new Label("Remarks");
		itemRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox itemRemarksTextBox = new TextBox("Remarks",
				item.remarksProperty());

		Button itemButton = new Button("Submit");
		itemButton.getStyleClass().add("submit-button");
		itemButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (itemNameBox.getText().trim().length() > 0) {
					if (itemRemarksTextBox.getText().trim().length() == 0) {
						item.setRemarks(item.getItemName());
					}

					ValidationData.insertItem(item);

					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Alert", "Alert",
							"Item saved successfully.");

					item.reset();
					item.setItemCode(ValidationData
							.getNextValidationNumber("Item"));
				} else {
					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Error", "Error",
							"Please enter item name.");
				}
			}
		});

		itemForm.add(itemCodeLabel, 0, 0);
		itemForm.add(itemCodeBox, 1, 0);
		itemForm.add(itemNameLabel, 0, 1);
		itemForm.add(itemNameBox, 1, 1);
		itemForm.add(itemRemarksLabel, 0, 2);
		itemForm.add(itemRemarksTextBox, 1, 2);
		itemForm.add(itemButton, 1, 3);

		main.getChildren().add(itemForm);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);

		itemTab.setContent(scrollPane);
		itemTab.setClosable(false);
		getTabs().add(itemTab);
	}

	public void createLaboratoryTab() {

		Tab laboratoryTab = new Tab("Laboratory");
		final LaboratoryMaster laboratory = new LaboratoryMaster();

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("Add Laboratory");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		GridPane laboratoryForm = new GridPane();
		laboratoryForm.setHgap(ViewLayout.H_SPACE);
		laboratoryForm.setVgap(ViewLayout.V_SPACE);

		Label laboratoryCodeLabel = new Label("Laboratory Code");
		laboratoryCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox laboratoryCodeBox = new ViewIntegerBox(
				laboratory.getLaboratoryCode(),
				laboratory.laboratoryCodeProperty());

		laboratoryCodeBox.setValue(ValidationData
				.getNextValidationNumber("Laboratory"));

		Label laboratoryNameLabel = new Label("Laboratory Name");
		laboratoryNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox laboratoryNameBox = new TextBox("Laboratory Name",
				laboratory.laboratoryNameProperty());

		Label laboratoryRemarksLabel = new Label("Remarks");
		laboratoryRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox laboratoryRemarksTextBox = new TextBox("Remarks",
				laboratory.remarksProperty());

		Button laboratoryButton = new Button("Submit");
		laboratoryButton.getStyleClass().add("submit-button");
		laboratoryButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (laboratoryNameBox.getText().trim().length() > 0) {
					if (laboratoryRemarksTextBox.getText().trim().length() == 0) {
						laboratory.setRemarks(laboratory.getLaboratoryName());
					}

					ValidationData.insertLaboratory(laboratory);

					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Alert", "Alert",
							"Laboratory saved successfully.");

					laboratory.reset();
					laboratory.setLaboratoryCode(ValidationData
							.getNextValidationNumber("Laboratory"));
				} else {
					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Error", "Error",
							"Please enter laboratory name.");
				}
			}
		});

		laboratoryForm.add(laboratoryCodeLabel, 0, 0);
		laboratoryForm.add(laboratoryCodeBox, 1, 0);
		laboratoryForm.add(laboratoryNameLabel, 0, 1);
		laboratoryForm.add(laboratoryNameBox, 1, 1);
		laboratoryForm.add(laboratoryRemarksLabel, 0, 2);
		laboratoryForm.add(laboratoryRemarksTextBox, 1, 2);
		laboratoryForm.add(laboratoryButton, 1, 3);

		main.getChildren().add(laboratoryForm);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);

		laboratoryTab.setContent(scrollPane);
		laboratoryTab.setClosable(false);
		getTabs().add(laboratoryTab);
	}

	public void createResultTab() {

		Tab resultTab = new Tab("Result");
		final ResultMaster result = new ResultMaster();

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("Add Result");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		GridPane resultForm = new GridPane();
		resultForm.setHgap(ViewLayout.H_SPACE);
		resultForm.setVgap(ViewLayout.V_SPACE);

		Label resultCodeLabel = new Label("Result Code");
		resultCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox resultCodeBox = new ViewIntegerBox(
				result.getResultCode(), result.resultCodeProperty());

		resultCodeBox
				.setValue(ValidationData.getNextValidationNumber("Result"));

		Label resultNameLabel = new Label("Result Name");
		resultNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox resultNameBox = new TextBox("Result Name",
				result.resultNameProperty());

		Label resultRemarksLabel = new Label("Remarks");
		resultRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox resultRemarksTextBox = new TextBox("Remarks",
				result.remarksProperty());

		Button resultButton = new Button("Submit");
		resultButton.getStyleClass().add("submit-button");
		resultButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (resultNameBox.getText().trim().length() > 0) {
					if (resultRemarksTextBox.getText().trim().length() == 0) {
						result.setRemarks(result.getResultName());
					}

					ValidationData.insertResult(result);

					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Alert", "Alert",
							"Result saved successfully.");

					result.reset();
					result.setResultCode(ValidationData
							.getNextValidationNumber("Result"));
				} else {
					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Error", "Error",
							"Please enter result name.");
				}
			}
		});

		resultForm.add(resultCodeLabel, 0, 0);
		resultForm.add(resultCodeBox, 1, 0);
		resultForm.add(resultNameLabel, 0, 1);
		resultForm.add(resultNameBox, 1, 1);
		resultForm.add(resultRemarksLabel, 0, 2);
		resultForm.add(resultRemarksTextBox, 1, 2);
		resultForm.add(resultButton, 1, 3);

		main.getChildren().add(resultForm);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);

		resultTab.setContent(scrollPane);
		resultTab.setClosable(false);
		getTabs().add(resultTab);
	}

	public void createSpecificationTab() {

		Tab specificationTab = new Tab("Specification");
		final SpecificationMaster specification = new SpecificationMaster();

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("Add Specification");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		GridPane specificationForm = new GridPane();
		specificationForm.setHgap(ViewLayout.H_SPACE);
		specificationForm.setVgap(ViewLayout.V_SPACE);

		Label specificationCodeLabel = new Label("Specification Code");
		specificationCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox specificationCodeBox = new ViewIntegerBox(
				specification.getSpecificationCode(),
				specification.specificationCodeProperty());

		specificationCodeBox.setValue(ValidationData
				.getNextValidationNumber("Specification"));

		Label specificationNameLabel = new Label("Specification Name");
		specificationNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox specificationNameBox = new TextBox("Specification Name",
				specification.specificationNameProperty());

		Label specificationRemarksLabel = new Label("Remarks");
		specificationRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox specificationRemarksTextBox = new TextBox("Remarks",
				specification.remarksProperty());

		specificationForm.add(specificationCodeLabel, 0, 0);
		specificationForm.add(specificationCodeBox, 1, 0);
		specificationForm.add(specificationNameLabel, 0, 1);
		specificationForm.add(specificationNameBox, 1, 1);
		specificationForm.add(specificationRemarksLabel, 0, 2);
		specificationForm.add(specificationRemarksTextBox, 1, 2);

		main.getChildren().add(specificationForm);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);

		specificationTab.setContent(scrollPane);
		specificationTab.setClosable(false);
		getTabs().add(specificationTab);
	}

	public void createTestTab() {

		Tab testTab = new Tab("Test");
		final TestMaster test = new TestMaster();

		VBox main = new VBox(ViewLayout.H_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label("Add Test");
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailCategoryHeader = new Label("Details");
		detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailCategoryHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailCategoryHeader);

		GridPane testForm = new GridPane();
		testForm.setHgap(ViewLayout.H_SPACE);
		testForm.setVgap(ViewLayout.V_SPACE);

		Label testCodeLabel = new Label("Test Code");
		testCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox testCodeBox = new ViewIntegerBox(
				test.getTestCode(), test.testCodeProperty());

		testCodeBox.setValue(ValidationData.getNextValidationNumber("Test"));

		Label testNameLabel = new Label("Test Name");
		testNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox testNameBox = new TextBox("Test Name",
				test.testNameProperty());

		Label testRemarksLabel = new Label("Remarks");
		testRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox testRemarksTextBox = new TextBox("Remarks",
				test.remarksProperty());

		testForm.add(testCodeLabel, 0, 0);
		testForm.add(testCodeBox, 1, 0);
		testForm.add(testNameLabel, 0, 1);
		testForm.add(testNameBox, 1, 1);
		testForm.add(testRemarksLabel, 0, 2);
		testForm.add(testRemarksTextBox, 1, 2);

		main.getChildren().add(testForm);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(main);

		testTab.setContent(scrollPane);
		testTab.setClosable(false);
		getTabs().add(testTab);
	}
}
