package org.map.view;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.map.MaterialRegister;
import org.map.calendar.DatePicker;
import org.map.controls.EComboBox;
import org.map.controls.EditTestGroup;
import org.map.controls.EditableBox;
import org.map.controls.TextBox;
import org.map.controls.ViewBox;
import org.map.controls.ViewIntegerBox;
import org.map.hibernate.dao.MaterialData;
import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.ValidationMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.Confirm;
import org.map.utils.TableContextMenu;

public class EditMaterial {

	private double COLUMN_WIDTH = 100;
	private double COLUMN_WIDTH_MAX = 120;
	private double LABEL_WIDTH = 100;
	private double H_SPACE = 8;
	private double V_SPACE = 20;
	private String ctNumber;
	private EditTestGroup etg;

	public void setCtNumber(String ctNumber) {
		this.ctNumber = ctNumber;
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

			Label header = new Label("Edit Details");
			header.getStyleClass().add("page-header");
			main.getChildren().add(header);

			Label mailboxCategoryHeader = new Label("Search");
			mailboxCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			mailboxCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			mailboxCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(mailboxCategoryHeader);

			final HBox search1 = new HBox(H_SPACE);
			Label ctNumberFromLabel = new Label("CT No From");
			ctNumberFromLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox ctNumberFromTextField = new TextBox("",
					"CT Number From");
			Label ctNumberToLabel = new Label("CT No To");
			ctNumberToLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox ctNumberToTextField = new TextBox("", "CT Number To");
			final Button searchRecordButton1 = new Button("Search");
			searchRecordButton1.getStyleClass().add("submit-button");
			search1.getChildren().addAll(ctNumberFromLabel,
					ctNumberFromTextField, ctNumberToLabel,
					ctNumberToTextField, searchRecordButton1);

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
			main.getChildren().addAll(search1, search2);

			Label searchCategoryHeader = new Label("List");
			searchCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			searchCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			searchCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(searchCategoryHeader);

			final TableView<MaterialMaster> tableMailbox = new TableView<>();
			TableColumn MCol1 = new TableColumn("CT Number");
			MCol1.setPrefWidth(COLUMN_WIDTH);
			MCol1.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"ctNumber"));
			TableColumn MCol2 = new TableColumn("Inspection Agency");
			MCol2.setPrefWidth(COLUMN_WIDTH);
			MCol2.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"inspectionAgency"));
			TableColumn MCol3 = new TableColumn("Item");
			MCol3.setPrefWidth(COLUMN_WIDTH_MAX);
			MCol3.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"item"));
			TableColumn MCol4 = new TableColumn("Size");
			MCol4.setPrefWidth(COLUMN_WIDTH);
			MCol4.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"size"));
			TableColumn MCol5 = new TableColumn("Heat Number");
			MCol5.setPrefWidth(COLUMN_WIDTH);
			MCol5.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"heatNumber"));
			TableColumn MCol6 = new TableColumn("Plate Number");
			MCol6.setPrefWidth(COLUMN_WIDTH);
			MCol6.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"plateNumber"));
			TableColumn MCol7 = new TableColumn("Specification");
			MCol7.setPrefWidth(COLUMN_WIDTH);
			MCol7.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
					"specification"));
			tableMailbox.getColumns().addAll(MCol1, MCol2, MCol3, MCol4, MCol5,
					MCol6, MCol7);
			main.getChildren().add(tableMailbox);

			final ObservableList<MaterialMaster> mailboxData = FXCollections
					.emptyObservableList();
			tableMailbox.setItems(mailboxData);

			Label detailCategoryHeader = new Label("Details");
			detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			detailCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(detailCategoryHeader);

			final MaterialMaster material = MaterialData
					.getMaterialDetails(ctNumber);
			final HBox detail = new HBox(H_SPACE);
			Label ctNumberLabel = new Label("CT Number");
			ctNumberLabel.setPrefWidth(LABEL_WIDTH);
			final ViewBox ctNumberTextField = new ViewBox("",
					material.ctNumberProperty(), true);
			Label agencyLabel = new Label("Inspection Agency");
			agencyLabel.setPrefWidth(LABEL_WIDTH);
			final EComboBox agencyTextField = new EComboBox("",
					"Inspection Agency", "Agency",
					material.inspectionAgencyProperty(), true);
			Label specLabel = new Label("Specification");
			specLabel.setPrefWidth(LABEL_WIDTH);
			final EComboBox specTextField = new EComboBox("", "Specification",
					"Specification", material.specificationProperty(), true);
			detail.getChildren().addAll(ctNumberLabel, ctNumberTextField,
					agencyLabel, agencyTextField, specLabel, specTextField);
			main.getChildren().add(detail);

			Label descriptionCategoryHeader = new Label("Description");
			descriptionCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			descriptionCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			descriptionCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(descriptionCategoryHeader);

			final VBox description = new VBox(V_SPACE);
			final HBox descriptionLine1 = new HBox(H_SPACE);
			final HBox descriptionLine2 = new HBox(H_SPACE);
			Label itemLabel = new Label("Item");
			itemLabel.setPrefWidth(LABEL_WIDTH);
			final EComboBox itemTextField = new EComboBox("", "Item Name",
					"Item", material.itemProperty(), true);
			Label sizeLabel = new Label("Size");
			sizeLabel.setPrefWidth(LABEL_WIDTH);
			final EditableBox sizeTextField = new EditableBox("", "Size",
					material.sizeProperty(), true);
			Label quantityLabel = new Label("Quantity");
			quantityLabel.setPrefWidth(LABEL_WIDTH);
			final ViewIntegerBox quantityTextField = new ViewIntegerBox(1,
					material.quantityProperty(), true);
			Label heatNumberLabel = new Label("Heat / Lot Number");
			heatNumberLabel.setPrefWidth(LABEL_WIDTH);
			final EditableBox heatNumberTextField = new EditableBox("",
					"Heat / Lot Number", material.heatNumberProperty(), true);
			Label plateNumberLabel = new Label("Plate / Product Number");
			plateNumberLabel.setPrefWidth(LABEL_WIDTH);
			final EditableBox plateNumberTextField = new EditableBox("",
					"Plate / Product Number", material.plateNumberProperty(),
					true);
			descriptionLine1.getChildren().addAll(itemLabel, itemTextField,
					sizeLabel, sizeTextField, quantityLabel, quantityTextField);
			descriptionLine2.getChildren()
					.addAll(heatNumberLabel, heatNumberTextField,
							plateNumberLabel, plateNumberTextField);
			description.getChildren()
					.addAll(descriptionLine1, descriptionLine2);
			main.getChildren().add(description);

			final VBox testBox = new VBox();
			etg = new EditTestGroup(material.getCtNumber(), material.getTests()
					.iterator());
			testBox.getChildren().add(etg.getView());
			main.getChildren().add(testBox);

			Label otherCategoryHeader = new Label("Other Details");
			otherCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			otherCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			otherCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(otherCategoryHeader);

			final VBox otherDetails = new VBox(V_SPACE);
			final HBox otherDetailsLine1 = new HBox(H_SPACE);
			final HBox otherDetailsLine2 = new HBox(H_SPACE);
			final HBox otherDetailsLine3 = new HBox(H_SPACE);
			Label custLabel = new Label("Customer");
			custLabel.setPrefWidth(LABEL_WIDTH);
			final EComboBox custTextField = new EComboBox("", "Customer Name",
					"Customer", material.customerProperty(), true);
			Label equipLabel = new Label("Equipments");
			equipLabel.setPrefWidth(LABEL_WIDTH);
			final EditableBox equipTextField = new EditableBox("",
					"Equipments", material.equipmentsProperty(), true);
			Label labLabel = new Label("Laboratory");
			labLabel.setPrefWidth(LABEL_WIDTH);
			final EComboBox labTextField = new EComboBox("", "Laboratory",
					"Laboratory", material.laboratoryProperty(), true);
			Label repDateLabel = new Label("Report Date");
			repDateLabel.setPrefWidth(LABEL_WIDTH);
			final EditableBox repDateCalendarBox = new EditableBox("",
					"dd-MM-yyyy", material.reportDateProperty(), true);
			Label repNumberLabel = new Label("Report Number");
			repNumberLabel.setPrefWidth(LABEL_WIDTH);
			final EditableBox repNumberTextField = new EditableBox("",
					"Report Number", material.reportNumberProperty(), true);
			Label remarksLabel = new Label("Remarks");
			remarksLabel.setPrefWidth(LABEL_WIDTH);
			final EComboBox remarksTextField = new EComboBox("", "Remarks",
					"Remarks", material.remarksProperty(), true);
			Label resulLabel = new Label("Result");
			resulLabel.setPrefWidth(LABEL_WIDTH);
			final EditableBox resultTextField = new EditableBox("", "Result",
					material.resultProperty(), true);
			Label witnessedByLabel = new Label("Witnessed By");
			witnessedByLabel.setPrefWidth(LABEL_WIDTH);
			final EditableBox witnessedByTextField = new EditableBox("",
					"Witnessed By", material.witnessedByProperty(), true);
			otherDetailsLine1.getChildren().addAll(custLabel, custTextField,
					equipLabel, equipTextField, labLabel, labTextField);
			otherDetailsLine2.getChildren().addAll(repDateLabel,
					repDateCalendarBox, repNumberLabel, repNumberTextField,
					resulLabel, resultTextField);
			otherDetailsLine3.getChildren().addAll(remarksLabel,
					remarksTextField, witnessedByLabel, witnessedByTextField);
			otherDetails.getChildren().addAll(otherDetailsLine1,
					otherDetailsLine2, otherDetailsLine3);
			main.getChildren().add(otherDetails);

			final VBox resonBox = new VBox();
			Label reasonLabel = new Label(
					"Reason of Failure (In case of rejected remarks.)");
			final TextArea reasonOfFailure = new TextArea("");
			reasonOfFailure.textProperty().bindBidirectional(
					material.failureReasonProperty());
			reasonOfFailure.setDisable(true);
			resonBox.getChildren().addAll(reasonLabel, reasonOfFailure);
			main.getChildren().add(resonBox);

			final HBox buttons = new HBox(H_SPACE);
			buttons.setTranslateY(32);
			final Button updateButton = new Button("Update");
			updateButton.getStyleClass().add("submit-button");
			updateButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					if (resultTextField.getText().equalsIgnoreCase("Rejected")
							&& reasonOfFailure.getText().trim().length() == 0) {
						Alert.showAlert(MaterialRegister.getMaterialRegister()
								.getPrimaryStage(), "Error", "Error",
								"Please enter reason of Failure.");
					} else {
						ArrayList<ValidationMaster> vms = etg.getNewTestList();
						ValidationData.insertTests(vms.iterator());

						MaterialData.updateMaterialTestMap(material, etg
								.getNewTestList().iterator());
					}
				}
			});

			searchRecordButton1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					try {
						tableMailbox.getItems().clear();

						List<MaterialMaster> mailboxCtMasters = MaterialData
								.searchMaterialDetailsCt(
										ctNumberFromTextField.getText(),
										ctNumberToTextField.getText(), false);
						mailboxData.setAll(mailboxCtMasters);
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
						tableMailbox.getItems().clear();

						List<MaterialMaster> mailboxCtMasters = MaterialData
								.searchMaterialDetailsDt(
										fromDateTextField.getSelectedDate(),
										toDateTextField.getSelectedDate(),
										false);
						mailboxData.setAll(mailboxCtMasters);
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

			tableMailbox.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener() {

						@Override
						public void changed(ObservableValue observable,
								Object oldValue, Object newValue) {
							Object selectedValue = tableMailbox
									.getSelectionModel().getSelectedItem();
							if (selectedValue != null) {
								MaterialMaster material2 = (MaterialMaster) selectedValue;
								material.resetMaterialMaster(material2);

								etg = new EditTestGroup(
										material2.getCtNumber(), material2
												.getTests().iterator());
								testBox.getChildren().clear();
								testBox.getChildren().add(etg.getView());
							}
						}
					});

			buttons.getChildren().addAll(updateButton);
			main.getChildren().add(buttons);

			EventHandler undoEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {
					final MaterialMaster delMaterial = tableMailbox
							.getSelectionModel().getSelectedItem();
					EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							delMaterial.setStatus("TRUE");

						}
					};
					Confirm.showConfirm(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Confirm", "Confirm",
							"Restore " + delMaterial.getCtNumber()
									+ ". Are you sure?", delUserEvent);
				}
			};

			EventHandler deleteEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {
					final MaterialMaster delMaterial = tableMailbox
							.getSelectionModel().getSelectedItem();
					EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							delMaterial.setStatus("FALSE");
						}
					};
					Confirm.showConfirm(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Confirm", "Confirm", "Delete "
							+ delMaterial.getCtNumber() + ". Are you sure?",
							delUserEvent);
				}
			};
			tableMailbox.setContextMenu(new TableContextMenu(undoEventHandler,
					deleteEventHandler));

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
