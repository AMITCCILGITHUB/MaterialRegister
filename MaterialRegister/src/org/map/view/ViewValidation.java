package org.map.view;

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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.map.MaterialRegister;
import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.ValidationMaster;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;

public class ViewValidation {

	private double H_SPACE = 8;
	private double V_SPACE = 20;

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

			Label header = new Label("View Validation");
			header.getStyleClass().add("page-header");
			main.getChildren().add(header);

			Label detailCategoryHeader = new Label("Details");
			detailCategoryHeader.setMaxWidth(Double.MAX_VALUE);
			detailCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
			detailCategoryHeader.getStyleClass().add("category-header");
			main.getChildren().add(detailCategoryHeader);

			final HBox validationBox = new HBox(V_SPACE);

			final VBox validationLeft = new VBox(V_SPACE);
			final ObservableList<String> validationTypes = FXCollections
					.observableArrayList(ValidationData.getValidationTypes());
			final ListView<String> validationTypeListView = new ListView<>(
					validationTypes);
			validationTypeListView.setPrefSize(300, 234);
			validationLeft.getChildren().addAll(validationTypeListView);

			final VBox validationRight = new VBox(V_SPACE);
			final ObservableList<String> validationResult = FXCollections
					.observableArrayList("");
			final ListView<String> validationResultListView = new ListView<>(
					validationResult);
			validationResultListView.setPrefSize(300, 234);
			validationRight.getChildren().addAll(validationResultListView);

			final ValidationMaster newValidation = new ValidationMaster();

			validationTypeListView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener() {

						@Override
						public void changed(ObservableValue observable,
								Object oldValue, Object newValue) {
							Object selectedValue = validationTypeListView
									.getSelectionModel().getSelectedItem();
							if (selectedValue != null) {
								validationResult.setAll(ValidationData
										.getValidationName(selectedValue
												.toString()));
							}
						}
					});

			validationResultListView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener() {

						@Override
						public void changed(ObservableValue observable,
								Object oldValue, Object newValue) {
							Object selectedValue = validationResultListView
									.getSelectionModel().getSelectedItem();
							if (selectedValue != null) {
								newValidation.resetDetails(ValidationData
										.getValidationDetail(
												validationTypeListView
														.getSelectionModel()
														.getSelectedItem(),
												selectedValue.toString()));
							}
						}
					});

			final HBox buttons = new HBox(H_SPACE);
			buttons.setTranslateY(32);

			final Button submitButton = new Button("Submit");
			submitButton.getStyleClass().add("submit-button");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
				}
			});

			buttons.getChildren().addAll(submitButton);

			validationBox.getChildren().addAll(validationLeft, validationRight);
			main.getChildren().addAll(validationBox);

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
