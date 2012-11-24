package org.map.view;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.map.controls.TextBox;
import org.map.controls.ViewIntegerBox;
import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.AgencyProperty;
import org.map.hibernate.ddo.CustomerProperty;
import org.map.hibernate.ddo.ItemProperty;
import org.map.hibernate.ddo.LaboratoryProperty;
import org.map.hibernate.ddo.ResultProperty;
import org.map.hibernate.ddo.SpecificationProperty;
import org.map.hibernate.ddo.TestProperty;
import org.map.logger.LoggerUtil;
import org.map.service.PersistType;
import org.map.service.PersistValidationDetails;
import org.map.service.ServiceManager;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
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
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());
		}
	}

	public void createAgencyTab() {

		Tab agencyTab = new Tab("Agency");
		final AgencyProperty agency = new AgencyProperty();

		VBox main = ViewLayout.getMainVBox("Add Agency", "Details");

		GridPane agencyForm = new GridPane();
		agencyForm.setHgap(ViewLayout.H_SPACE);
		agencyForm.setVgap(ViewLayout.V_SPACE);

		Label agencyCodeLabel = new Label("Agency Code");
		agencyCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox agencyCodeBox = new ViewIntegerBox(agency.get()
				.getAgencyCode(), agency.get().agencyCodeProperty());

		agency.get().setAgencyCode(
				ValidationData.getNextValidationNumber("Agency"));

		Label agencyNameLabel = new Label("Agency Name");
		agencyNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox agencyNameBox = new TextBox("Agency Name", agency.get()
				.agencyNameProperty());

		Label agencyRemarksLabel = new Label("Remarks");
		agencyRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox agencyRemarksTextBox = new TextBox("Remarks", agency
				.get().remarksProperty());

		Button agencyButton = new Button("Submit");
		agencyButton.getStyleClass().add("submit-button");
		agencyButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (agencyNameBox.getText().trim().length() > 0) {
					if (agencyRemarksTextBox.getText().trim().length() == 0) {
						agency.get().setRemarks(agency.get().getAgencyName());
					}

					PersistValidationDetails pvd = ServiceManager
							.getValidationDetailsService("Agency",
									agency.get(), PersistType.INSERT);

					pvd.restart();

					pvd.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

						@Override
						public void handle(WorkerStateEvent e) {

							agency.get().clean();
							agency.get().setAgencyCode(
									ValidationData
											.getNextValidationNumber("Agency"));
						}
					});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
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

		agencyTab.setContent(ControlsUtil.makeScrollable(main));
		agencyTab.setClosable(false);
		getTabs().add(agencyTab);
	}

	public void createCustomerTab() {

		Tab CustomerTab = new Tab("Customer");
		final CustomerProperty customer = new CustomerProperty();

		VBox main = ViewLayout.getMainVBox("Add Customer", "Details");

		GridPane customerForm = new GridPane();
		customerForm.setHgap(ViewLayout.H_SPACE);
		customerForm.setVgap(ViewLayout.V_SPACE);

		Label customerCodeLabel = new Label("Customer Code");
		customerCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox customerCodeBox = new ViewIntegerBox(customer
				.get().getCustomerCode(), customer.get().customerCodeProperty());

		customerCodeBox.setValue(ValidationData
				.getNextValidationNumber("Customer"));

		Label customerNameLabel = new Label("Customer Name");
		customerNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox customerNameBox = new TextBox("Customer Name", customer
				.get().customerNameProperty());

		Label customerRemarksLabel = new Label("Remarks");
		customerRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox customerRemarksTextBox = new TextBox("Remarks", customer
				.get().remarksProperty());

		Button customerButton = new Button("Submit");
		customerButton.getStyleClass().add("submit-button");
		customerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (customerNameBox.getText().trim().length() > 0) {
					if (customerRemarksTextBox.getText().trim().length() == 0) {
						customer.get().setRemarks(
								customer.get().getCustomerName());
					}

					PersistValidationDetails pvd = ServiceManager
							.getValidationDetailsService("Customer",
									customer.get(), PersistType.INSERT);

					pvd.restart();

					pvd.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

						@Override
						public void handle(WorkerStateEvent e) {

							customer.get().clean();
							customer.get()
									.setCustomerCode(
											ValidationData
													.getNextValidationNumber("Customer"));
						}
					});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
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

		CustomerTab.setContent(ControlsUtil.makeScrollable(main));
		CustomerTab.setClosable(false);
		getTabs().add(CustomerTab);
	}

	public void createItemTab() {

		Tab itemTab = new Tab("Item");
		final ItemProperty item = new ItemProperty();

		VBox main = ViewLayout.getMainVBox("Add Item", "Details");

		GridPane itemForm = new GridPane();
		itemForm.setHgap(ViewLayout.H_SPACE);
		itemForm.setVgap(ViewLayout.V_SPACE);

		Label itemCodeLabel = new Label("Item Code");
		itemCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox itemCodeBox = new ViewIntegerBox(item.get()
				.getItemCode(), item.get().itemCodeProperty());

		itemCodeBox.setValue(ValidationData.getNextValidationNumber("Item"));

		Label itemNameLabel = new Label("Item Name");
		itemNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox itemNameBox = new TextBox("Item Name", item.get()
				.itemNameProperty());

		Label itemRemarksLabel = new Label("Remarks");
		itemRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox itemRemarksTextBox = new TextBox("Remarks", item.get()
				.remarksProperty());

		Button itemButton = new Button("Submit");
		itemButton.getStyleClass().add("submit-button");
		itemButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (itemNameBox.getText().trim().length() > 0) {
					if (itemRemarksTextBox.getText().trim().length() == 0) {
						item.get().setRemarks(item.get().getItemName());
					}

					PersistValidationDetails pvd = ServiceManager
							.getValidationDetailsService("Item", item.get(),
									PersistType.INSERT);

					pvd.restart();

					pvd.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

						@Override
						public void handle(WorkerStateEvent e) {

							item.get().clean();
							item.get().setItemCode(
									ValidationData
											.getNextValidationNumber("Item"));
						}
					});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
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

		itemTab.setContent(ControlsUtil.makeScrollable(main));
		itemTab.setClosable(false);
		getTabs().add(itemTab);
	}

	public void createLaboratoryTab() {

		Tab laboratoryTab = new Tab("Laboratory");
		final LaboratoryProperty laboratory = new LaboratoryProperty();

		VBox main = ViewLayout.getMainVBox("Add Laboratory", "Details");

		GridPane laboratoryForm = new GridPane();
		laboratoryForm.setHgap(ViewLayout.H_SPACE);
		laboratoryForm.setVgap(ViewLayout.V_SPACE);

		Label laboratoryCodeLabel = new Label("Laboratory Code");
		laboratoryCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox laboratoryCodeBox = new ViewIntegerBox(laboratory
				.get().getLaboratoryCode(), laboratory.get()
				.laboratoryCodeProperty());

		laboratoryCodeBox.setValue(ValidationData
				.getNextValidationNumber("Laboratory"));

		Label laboratoryNameLabel = new Label("Laboratory Name");
		laboratoryNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox laboratoryNameBox = new TextBox("Laboratory Name",
				laboratory.get().laboratoryNameProperty());

		Label laboratoryRemarksLabel = new Label("Remarks");
		laboratoryRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox laboratoryRemarksTextBox = new TextBox("Remarks",
				laboratory.get().remarksProperty());

		Button laboratoryButton = new Button("Submit");
		laboratoryButton.getStyleClass().add("submit-button");
		laboratoryButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (laboratoryNameBox.getText().trim().length() > 0) {
					if (laboratoryRemarksTextBox.getText().trim().length() == 0) {
						laboratory.get().setRemarks(
								laboratory.get().getLaboratoryName());
					}

					PersistValidationDetails pvd = ServiceManager
							.getValidationDetailsService("Laboratory",
									laboratory.get(), PersistType.INSERT);

					pvd.restart();

					pvd.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

						@Override
						public void handle(WorkerStateEvent e) {

							laboratory.get().clean();
							laboratory
									.get()
									.setLaboratoryCode(
											ValidationData
													.getNextValidationNumber("Laboratory"));
						}
					});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
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

		laboratoryTab.setContent(ControlsUtil.makeScrollable(main));
		laboratoryTab.setClosable(false);
		getTabs().add(laboratoryTab);
	}

	public void createResultTab() {

		Tab resultTab = new Tab("Result");
		final ResultProperty result = new ResultProperty();

		VBox main = ViewLayout.getMainVBox("Add Result", "Details");

		GridPane resultForm = new GridPane();
		resultForm.setHgap(ViewLayout.H_SPACE);
		resultForm.setVgap(ViewLayout.V_SPACE);

		Label resultCodeLabel = new Label("Result Code");
		resultCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox resultCodeBox = new ViewIntegerBox(result.get()
				.getResultCode(), result.get().resultCodeProperty());

		resultCodeBox
				.setValue(ValidationData.getNextValidationNumber("Result"));

		Label resultNameLabel = new Label("Result Name");
		resultNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox resultNameBox = new TextBox("Result Name", result.get()
				.resultNameProperty());

		Label resultRemarksLabel = new Label("Remarks");
		resultRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox resultRemarksTextBox = new TextBox("Remarks", result
				.get().remarksProperty());

		Button resultButton = new Button("Submit");
		resultButton.getStyleClass().add("submit-button");
		resultButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (resultNameBox.getText().trim().length() > 0) {
					if (resultRemarksTextBox.getText().trim().length() == 0) {
						result.get().setRemarks(result.get().getResultName());
					}

					PersistValidationDetails pvd = ServiceManager
							.getValidationDetailsService("Result",
									result.get(), PersistType.INSERT);

					pvd.restart();

					pvd.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

						@Override
						public void handle(WorkerStateEvent e) {

							result.get().clean();
							result.get().setResultCode(
									ValidationData
											.getNextValidationNumber("Result"));
						}
					});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
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

		resultTab.setContent(ControlsUtil.makeScrollable(main));
		resultTab.setClosable(false);
		getTabs().add(resultTab);
	}

	public void createSpecificationTab() {

		Tab specificationTab = new Tab("Specification");
		final SpecificationProperty specification = new SpecificationProperty();

		VBox main = ViewLayout.getMainVBox("Add Specification", "Details");

		GridPane specificationForm = new GridPane();
		specificationForm.setHgap(ViewLayout.H_SPACE);
		specificationForm.setVgap(ViewLayout.V_SPACE);

		Label specificationCodeLabel = new Label("Specification Code");
		specificationCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox specificationCodeBox = new ViewIntegerBox(
				specification.get().getSpecificationCode(), specification.get()
						.specificationCodeProperty());

		specificationCodeBox.setValue(ValidationData
				.getNextValidationNumber("Specification"));

		Label specificationNameLabel = new Label("Specification Name");
		specificationNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox specificationNameBox = new TextBox("Specification Name",
				specification.get().specificationNameProperty());

		Label specificationRemarksLabel = new Label("Remarks");
		specificationRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox specificationRemarksTextBox = new TextBox("Remarks",
				specification.get().remarksProperty());

		Button specificationButton = new Button("Submit");
		specificationButton.getStyleClass().add("submit-button");
		specificationButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (specificationNameBox.getText().trim().length() > 0) {
					if (specificationRemarksTextBox.getText().trim().length() == 0) {
						specification.get().setRemarks(
								specification.get().getSpecificationName());
					}

					PersistValidationDetails pvd = ServiceManager
							.getValidationDetailsService("Specification",
									specification.get(), PersistType.INSERT);

					pvd.restart();

					pvd.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

						@Override
						public void handle(WorkerStateEvent e) {

							specification.get().clean();
							specification
									.get()
									.setSpecificationCode(
											ValidationData
													.getNextValidationNumber("Specification"));
						}
					});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Please enter specification name.");
				}
			}
		});

		specificationForm.add(specificationCodeLabel, 0, 0);
		specificationForm.add(specificationCodeBox, 1, 0);
		specificationForm.add(specificationNameLabel, 0, 1);
		specificationForm.add(specificationNameBox, 1, 1);
		specificationForm.add(specificationRemarksLabel, 0, 2);
		specificationForm.add(specificationRemarksTextBox, 1, 2);
		specificationForm.add(specificationButton, 1, 3);

		main.getChildren().add(specificationForm);

		specificationTab.setContent(ControlsUtil.makeScrollable(main));
		specificationTab.setClosable(false);
		getTabs().add(specificationTab);
	}

	public void createTestTab() {

		Tab testTab = new Tab("Test");
		final TestProperty test = new TestProperty();

		VBox main = ViewLayout.getMainVBox("Add Test", "Details");

		GridPane testForm = new GridPane();
		testForm.setHgap(ViewLayout.H_SPACE);
		testForm.setVgap(ViewLayout.V_SPACE);

		Label testCodeLabel = new Label("Test Code");
		testCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox testCodeBox = new ViewIntegerBox(test.get()
				.getTestCode(), test.get().testCodeProperty());

		testCodeBox.setValue(ValidationData.getNextValidationNumber("Test"));

		Label testNameLabel = new Label("Test Name");
		testNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox testNameBox = new TextBox("Test Name", test.get()
				.testNameProperty());

		Label testRemarksLabel = new Label("Remarks");
		testRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox testRemarksTextBox = new TextBox("Remarks", test.get()
				.remarksProperty());

		Button testButton = new Button("Submit");
		testButton.getStyleClass().add("submit-button");
		testButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (testNameBox.getText().trim().length() > 0) {
					if (testRemarksTextBox.getText().trim().length() == 0) {
						test.get().setRemarks(test.get().getTestName());
					}

					PersistValidationDetails pvd = ServiceManager
							.getValidationDetailsService("Test", test.get(),
									PersistType.INSERT);

					pvd.restart();

					pvd.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

						@Override
						public void handle(WorkerStateEvent e) {

							test.get().clean();
							test.get().setTestCode(
									ValidationData
											.getNextValidationNumber("Test"));
						}
					});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Please enter test name.");
				}
			}
		});

		testForm.add(testCodeLabel, 0, 0);
		testForm.add(testCodeBox, 1, 0);
		testForm.add(testNameLabel, 0, 1);
		testForm.add(testNameBox, 1, 1);
		testForm.add(testRemarksLabel, 0, 2);
		testForm.add(testRemarksTextBox, 1, 2);
		testForm.add(testButton, 1, 3);

		main.getChildren().add(testForm);

		testTab.setContent(ControlsUtil.makeScrollable(main));
		testTab.setClosable(false);
		getTabs().add(testTab);
	}
}
