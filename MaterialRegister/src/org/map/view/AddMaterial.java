package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.map.controls.IntBox;
import org.map.controls.TextBox;
import org.map.controls.ViewBox;
import org.map.controls.combobox.AgencyComboBox;
import org.map.controls.combobox.ItemComboBox;
import org.map.controls.combobox.SpecificationComboBox;
import org.map.hibernate.dao.MaterialData;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.MaterialTests;
import org.map.logger.LoggerUtil;
import org.map.service.PersistMaterialDetails;
import org.map.service.PersistType;
import org.map.service.ServiceManager;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;
import org.map.validation.Validator;

public class AddMaterial extends ScrollPane {

	public AddMaterial() {

		try {
			VBox main = ViewLayout.getMainVBox("Add Material", "Details");

			final MaterialMaster material = new MaterialMaster();
			material.setCtNumber(MaterialData.getNextCtNumber());

			GridPane form = new GridPane();
			form.setHgap(ViewLayout.H_SPACE);
			form.setVgap(ViewLayout.V_SPACE);

			Label ctNumberLabel = new Label("CT Number");
			ctNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final ViewBox ctNumberTextField = new ViewBox(
					material.ctNumberProperty());

			Label agencyLabel = new Label("Inspection Agency");
			agencyLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final AgencyComboBox agencyTextField = new AgencyComboBox(
					"Inspection Agency", material.inspectionAgencyProperty());

			Label specLabel = new Label("Specification");
			specLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final SpecificationComboBox specTextField = new SpecificationComboBox(
					"Specification", material.specificationProperty());

			Label itemLabel = new Label("Item");
			itemLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final ItemComboBox itemTextField = new ItemComboBox("Item Name",
					material.itemProperty());

			Label sizeLabel = new Label("Size");
			sizeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final TextBox sizeTextField = new TextBox("Size",
					material.sizeProperty());

			Label quantityLabel = new Label("Quantity");
			quantityLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final IntBox quantityTextField = new IntBox("Quantity",
					material.quantityProperty());

			Label heatNumberLabel = new Label("Heat / Lot Number");
			heatNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final TextBox heatNumberTextField = new TextBox(
					"Heat / Lot Number", material.heatNumberProperty());

			Label plateNumberLabel = new Label("Plate / Product Number");
			plateNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
			final TextBox plateNumberTextField = new TextBox(
					"Plate / Product Number", material.plateNumberProperty());

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

			main.getChildren().addAll(form);
			
			final TableView<MaterialTests> table = TableUtil
					.createAddMaterialTable();

			MaterialTests mts = new MaterialTests();
			material.getMaterialTests().add(mts);
			final ObservableList<MaterialTests> materialTestsData = FXCollections
					.observableArrayList(mts);
			
			table.setItems(materialTestsData);
			
			main.getChildren().addAll(table);

			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button addTestButton = new Button("Add Test");
			addTestButton.getStyleClass().add("submit-button");
			addTestButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					MaterialTests mts = new MaterialTests();
					material.getMaterialTests().add(mts);
					materialTestsData.add(mts);

				}
			});
			final Button submitButton = new Button("Submit");
			submitButton.getStyleClass().add("submit-button");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					if (Validator.validateMaterialData(material)) {
						
						PersistMaterialDetails pmd = ServiceManager
								.getMaterialDetailsService(material,
										PersistType.INSERT);

						pmd.restart();

						pmd.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

							@Override
							public void handle(WorkerStateEvent e) {

								material.clean();
							}
						});
					}
				}
			});

			buttons.getChildren().addAll(addTestButton, submitButton);
			main.getChildren().add(buttons);

			EventHandler<ActionEvent> addTestEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					MaterialTests mts = new MaterialTests();
					material.getMaterialTests().add(mts);
					materialTestsData.add(mts);
				}
			};

			EventHandler<ActionEvent> removeTestEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					int index = table.getSelectionModel().getSelectedIndex();
					if (index >= 0) {
						material.getMaterialTests().remove(materialTestsData.get(index));
						materialTestsData.remove(index);
					}
				}
			};

			EventHandler<ActionEvent> duplicateTestEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					MaterialTests mtsDup = table.getSelectionModel()
							.getSelectedItem();

					if (mtsDup != null) {
						MaterialTests mts = new MaterialTests();
						mts.resetTo(mtsDup);
						mts.setTestCode(0);
						material.getMaterialTests().add(mts);

						materialTestsData.add(mts);
					}

				}
			};

			table.setContextMenu(TableContextMenu.getAddMaterialContextMenu(
					addTestEventHandler, removeTestEventHandler,
					duplicateTestEventHandler));

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
