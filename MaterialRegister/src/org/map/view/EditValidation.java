package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import org.map.MaterialRegister;
import org.map.hibernate.dao.ValidationData;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.ViewLayout;

public class EditValidation extends ScrollPane {

	public EditValidation() {

		try {
			VBox main = new VBox(ViewLayout.H_SPACE);
			main.getStyleClass().add("category-page");

			Label header = new Label("Edit Validation");
			header.getStyleClass().add("page-header");
			main.getChildren().add(header);

			Label detailCategoryHeader = new Label("Details");
			detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			detailCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(detailCategoryHeader);

			final VBox validationTop = new VBox(ViewLayout.V_SPACE);
			final ObservableList<String> validationTypes = FXCollections
					.observableArrayList(ValidationData.getValidationTypes());
			final ListView<String> validationTypeListView = new ListView<>(
					validationTypes);
			validationTypeListView.setPrefSize(300, 234);
			validationTop.getChildren().addAll(validationTypeListView);

			final VBox validationBottom = new VBox(ViewLayout.V_SPACE);
			final ObservableList<String> validationResult = FXCollections
					.observableArrayList();
			final ListView<String> validationResultListView = new ListView<>(
					validationResult);
			validationResultListView.setPrefSize(300, 234);
			validationBottom.getChildren().addAll(validationResultListView);

			/*
			 * final ValidationMaster validation = new ValidationMaster("");
			 * final VBox validationRight = new VBox(ViewLayout.V_SPACE); final
			 * HBox validationType = new HBox(ViewLayout.H_SPACE); Label
			 * validationTypeLabel = new Label("Validation Type");
			 * validationTypeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH); final
			 * ViewBox validationTypeBox = new ViewBox("", validation
			 * .getId().validationTypeProperty());
			 * validationType.getChildren().addAll(validationTypeLabel,
			 * validationTypeBox); final HBox validationCode = new
			 * HBox(ViewLayout.H_SPACE); Label validationCodeLabel = new
			 * Label("Validation Code");
			 * validationCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH); final
			 * ViewIntegerBox validationCodeBox = new ViewIntegerBox(0,
			 * validation.getId().validationCodeProperty());
			 * validationCode.getChildren().addAll(validationCodeLabel,
			 * validationCodeBox); final HBox validationName = new
			 * HBox(ViewLayout.H_SPACE); Label validationNameLabel = new
			 * Label("Validation Name");
			 * validationNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH); final
			 * EditableBox validationNameBox = new EditableBox("",
			 * "Validation Name", validation.validationNameProperty());
			 * validationName.getChildren().addAll(validationNameLabel,
			 * validationNameBox); final HBox validationDesc = new
			 * HBox(ViewLayout.H_SPACE); Label validationDescLabel = new
			 * Label("Validation Description");
			 * validationDescLabel.setPrefWidth(ViewLayout.LABEL_WIDTH); final
			 * EditableBox validationDescBox = new EditableBox("",
			 * "Validation Desc", validation.validationDescProperty());
			 * validationDesc.getChildren().addAll(validationDescLabel,
			 * validationDescBox);
			 * 
			 * validationTypeListView.getSelectionModel().selectedItemProperty()
			 * .addListener(new ChangeListener() {
			 * 
			 * @Override public void changed(ObservableValue observable, Object
			 * oldValue, Object newValue) {
			 * 
			 * Object selectedValue = validationTypeListView
			 * .getSelectionModel().getSelectedItem(); if (selectedValue !=
			 * null) { validationResult.setAll(ValidationData
			 * .getValidationName(selectedValue .toString())); } } });
			 * 
			 * validationResultListView.getSelectionModel().selectedItemProperty(
			 * ) .addListener(new ChangeListener() {
			 * 
			 * @Override public void changed(ObservableValue observable, Object
			 * oldValue, Object newValue) {
			 * 
			 * Object selectedValue = validationResultListView
			 * .getSelectionModel().getSelectedItem(); if (selectedValue !=
			 * null) { validation.resetDetails(ValidationData
			 * .getValidationDetail( validationTypeListView .getSelectionModel()
			 * .getSelectedItem(), selectedValue.toString())); } } });
			 * 
			 * final HBox buttons = new HBox(ViewLayout.H_SPACE);
			 * buttons.setTranslateY(32); final Button updateButton = new
			 * Button("Update");
			 * updateButton.getStyleClass().add("submit-button");
			 * updateButton.setOnAction(new EventHandler<ActionEvent>() {
			 * 
			 * @Override public void handle(ActionEvent e) { if
			 * (validationNameBox.getText().trim().length() > 0) { if
			 * (validationDescBox.getText().trim().length() > 0) {
			 * validation.setValidationDesc(validation .getValidationName()); }
			 * 
			 * ValidationData.updateValidationTypes(validation);
			 * 
			 * Alert.showAlert(MaterialRegister.getMaterialRegister()
			 * .getPrimaryStage(), "Alert", "Alert",
			 * "Validation updated successfully."); } else {
			 * Alert.showAlert(MaterialRegister.getMaterialRegister()
			 * .getPrimaryStage(), "Error", "Error",
			 * "Please enter validation name"); } } });
			 * buttons.getChildren().addAll(updateButton);
			 * 
			 * validationRight.getChildren().addAll(validationType,
			 * validationCode, validationName, validationDesc, buttons);
			 * validationBox.getChildren().addAll(validationTop,
			 * validationBottom, validationRight);
			 * main.getChildren().addAll(validationBox);
			 */

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
