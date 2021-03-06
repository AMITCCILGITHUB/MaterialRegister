package org.map.controls.combobox;

import java.util.Iterator;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.CustomerMaster;
import org.map.hibernate.ddo.CustomerProperty;
import org.map.utils.ViewLayout;

public class CustomerComboBox extends Region {

	private CustomerProperty customerProperty;
	private TextField textBox;
	private Button errorButton;
	private ContextMenu resultContextMenu = new ContextMenu();

	public CustomerComboBox() {

		customerProperty = new CustomerProperty();
		initComponent();
	}

	public CustomerComboBox(String promptText) {

		customerProperty = new CustomerProperty();
		initComponent();

		textBox.setPromptText(promptText);

		textBox.textProperty().bindBidirectional(
				customerProperty.get().customerNameProperty());
	}

	public CustomerComboBox(String promptText, CustomerProperty customer) {

		customerProperty = new CustomerProperty();
		initComponent();

		textBox.setPromptText(promptText);
		customerProperty.bindBidirectional(customer);
		
		textBox.textProperty().bindBidirectional(
				customerProperty.get().customerNameProperty());
	}

	private void initComponent() {

		setId("ResultBox");

		setMinSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setPrefSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setMaxSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);

		textBox = new TextField();
		textBox.setPrefWidth(ViewLayout.TEXTBOX_WIDTH);

		errorButton = new Button();
		errorButton.getStyleClass().add("error-button");
		errorButton.setVisible(false);
		errorButton.setFocusTraversable(false);

		textBox.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {

				if (oldValue == true && newValue == false) {
					if (textBox.getText().length() == 0) {
						errorButton.setVisible(true);
					} else {
						errorButton.setVisible(false);
					}
				}
			}
		});

		textBox.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

				errorButton.setVisible(textBox.getText().length() == 0);
			}
		});

		getChildren().addAll(textBox, errorButton);
		resultContextMenu.setAutoFix(true);
		showResults();
	}

	public void setText(String textValue) {

		textBox.setText(textValue);
	}

	public String getText() {

		return textBox.getText();
	}

	@Override
	protected void layoutChildren() {

		textBox.resize(getWidth(), getHeight());
		errorButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
	}

	private void showResults() {

		textBox.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {

				if (oldValue == true && newValue == false) {
					resultContextMenu.hide();
				}
			}
		});

		textBox.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {

				if (keyEvent.getCode() == KeyCode.DOWN) {
					resultContextMenu.requestFocus();
				} else if (keyEvent.getCode() == KeyCode.ENTER) {
					resultContextMenu.hide();
				} else if (keyEvent.getCode() == KeyCode.ESCAPE) {
					resultContextMenu.hide();
				}
			}
		});

		textBox.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

				if (textBox.isFocused() == true) {
					if (textBox.getText().length() == 0) {
						if (resultContextMenu != null) {
							resultContextMenu.hide();
						}
					} else {
						List<CustomerMaster> resultList = ValidationData
								.getCustomerList(textBox.getText().trim());

						if (resultList.size() > 0) {
							populateMenu(resultList);
							if (!resultContextMenu.isShowing()) {
								resultContextMenu.show(textBox, Side.BOTTOM,
										10, -5);
							}
						} else {
							populateMenu("No matches");
							if (!resultContextMenu.isShowing()) {
								resultContextMenu.show(textBox, Side.BOTTOM,
										10, -5);
							}
						}
						resultContextMenu.requestFocus();
					}
				}
			}
		});
	}

	private void populateMenu(String errorMsg) {
		resultContextMenu.getItems().clear();

		final HBox hBox = new HBox();
		hBox.setFillHeight(true);
		Label itemLabel = new Label(errorMsg);
		itemLabel.getStyleClass().add("item-label");
		hBox.getChildren().addAll(itemLabel);

		final Region popRegion = new Region();
		popRegion.getStyleClass().add("result-menu-item-popup-region");
		popRegion.setPrefSize(10, 10);
		hBox.getChildren().add(popRegion);

		CustomMenuItem menuItem = new CustomMenuItem(hBox, true);
		menuItem.getStyleClass().add("result-menu-item");
		resultContextMenu.getItems().add(menuItem);
	}
	
	private void populateMenu(List<CustomerMaster> resultList) {

		resultContextMenu.getItems().clear();
		Iterator<CustomerMaster> results = resultList.iterator();

		while (results.hasNext()) {
			final CustomerMaster result = (CustomerMaster) results.next();
			final HBox hBox = new HBox();
			hBox.setFillHeight(true);
			Label itemLabel = new Label(result.getCustomerName());
			itemLabel.getStyleClass().add("item-label");
			hBox.getChildren().addAll(itemLabel);

			final Region popRegion = new Region();
			popRegion.getStyleClass().add("result-menu-item-popup-region");
			popRegion.setPrefSize(10, 10);
			hBox.getChildren().add(popRegion);

			CustomMenuItem menuItem = new CustomMenuItem(hBox, true);
			menuItem.getStyleClass().add("result-menu-item");
			resultContextMenu.getItems().add(menuItem);
			menuItem.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					customerProperty.set(result);
				}
			});
		}
	}

	public CustomerProperty customerProperty() {

		return customerProperty;
	}
}
