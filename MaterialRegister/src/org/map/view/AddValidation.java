package org.map.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.map.MaterialRegister;
import org.map.controls.CustomComboBox;
import org.map.controls.TextBox;
import org.map.controls.ViewIntegerBox;
import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.ValidationMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;

public class AddValidation {

	private double LABEL_WIDTH = 180;
	private double H_SPACE = 8;

	public Node createView() {

		try {
			final VBox main = new VBox(H_SPACE) {

				@Override
				protected double computePrefHeight(double width) {

					return Math.max(super.computePrefHeight(width), getParent()
							.getBoundsInLocal().getHeight());
				}
			};
			main.getStyleClass().add("category-page");

			Label header = new Label("Add Validation");
			header.getStyleClass().add("page-header");
			main.getChildren().add(header);

			Label detailCategoryHeader = new Label("Details");
			detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			detailCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(detailCategoryHeader);

			final ValidationMaster newValidation = new ValidationMaster();
			final HBox validationType = new HBox(H_SPACE);
			Label validationTypeLabel = new Label("Validation Type");
			validationTypeLabel.setPrefWidth(LABEL_WIDTH);
			final CustomComboBox validationTypeBox = new CustomComboBox("",
					"ValidationType", "ValidationType", newValidation.getId()
							.validationTypeProperty(), true);
			validationType.getChildren().addAll(validationTypeLabel,
					validationTypeBox);
			final HBox validationCode = new HBox(H_SPACE);
			Label validationCodeLabel = new Label("Validation Code");
			validationCodeLabel.setPrefWidth(LABEL_WIDTH);
			final ViewIntegerBox validationCodeBox = new ViewIntegerBox(
					newValidation.getId().getValidationCode(), newValidation
							.getId().validationCodeProperty(), true);
			validationCode.getChildren().addAll(validationCodeLabel,
					validationCodeBox);
			final HBox validationName = new HBox(H_SPACE);
			Label validationNameLabel = new Label("Validation Name");
			validationNameLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox validationNameBox = new TextBox("",
					"Validation Name", newValidation.validationNameProperty(),
					true);
			validationName.getChildren().addAll(validationNameLabel,
					validationNameBox);
			final HBox validationDesc = new HBox(H_SPACE);
			Label validationDescLabel = new Label("Validation Description");
			validationDescLabel.setPrefWidth(LABEL_WIDTH);
			final TextBox validationDescEditableBox = new TextBox("",
					"Validation Desc", newValidation.validationDescProperty(),
					true);
			validationDesc.getChildren().addAll(validationDescLabel,
					validationDescEditableBox);

			ChangeListener<String> selectionChangeListener = new ChangeListener<String>() {

				@Override
				public void changed(
						ObservableValue<? extends String> observable,
						String oldValue, String newValue) {

					validationCodeBox.setValue(ValidationData
							.getNextValidationNumber(newValue));

				}
			};
			validationTypeBox.addChangeListener(selectionChangeListener);

			final HBox buttons = new HBox(H_SPACE);
			buttons.setTranslateY(32);
			final Button submitButton = new Button("Submit");
			submitButton.getStyleClass().add("submit-button");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					if (validationTypeBox.getText().trim().length() > 0) {
						if (validationNameBox.getText().trim().length() > 0) {
							if (validationDescEditableBox.getText().trim()
									.length() == 0) {
								newValidation.setValidationDesc(newValidation
										.getValidationName());
							}

							ValidationData.insertValidationTypes(newValidation);

							Alert.showAlert(MaterialRegister
									.getMaterialRegister().getPrimaryStage(),
									"Alert", "Alert",
									"Validation saved successfully.");
						} else {
							Alert.showAlert(MaterialRegister
									.getMaterialRegister().getPrimaryStage(),
									"Error", "Error",
									"Please enter validation name.");
						}
					} else {
						Alert.showAlert(MaterialRegister.getMaterialRegister()
								.getPrimaryStage(), "Error", "Error",
								"Please enter validation type.");
					}
				}
			});
			buttons.getChildren().addAll(submitButton);

			main.getChildren().addAll(validationType, validationCode,
					validationName, validationDesc, buttons);

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
