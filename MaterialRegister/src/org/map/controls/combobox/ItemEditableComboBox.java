package org.map.controls.combobox;

import java.util.Iterator;
import java.util.List;

import javafx.beans.property.StringProperty;
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
import org.map.hibernate.ddo.ItemMaster;
import org.map.hibernate.ddo.ItemProperty;
import org.map.utils.ViewLayout;

public class ItemEditableComboBox extends Region {

	private ItemProperty itemProperty;
	private TextField textBox;
	private Button errorButton;
	private Button editButton;
	private ContextMenu resultContextMenu = new ContextMenu();

	public ItemEditableComboBox() {

		itemProperty = new ItemProperty();
		initComponent();
	}

	public ItemEditableComboBox(String promptText) {

		itemProperty = new ItemProperty();
		initComponent();

		textBox.setPromptText(promptText);

		textBox.textProperty().bindBidirectional(
				itemProperty.get().itemNameProperty());
	}

	public ItemEditableComboBox(String promptText, ItemProperty item) {

		itemProperty = new ItemProperty();
		initComponent();

		textBox.setPromptText(promptText);
		itemProperty.bindBidirectional(item);
		
		textBox.textProperty().bindBidirectional(
				itemProperty.get().itemNameProperty());
	}

	public void bind(StringProperty propertyValue) {

		textBox.textProperty().bind(propertyValue);
	}

	public void bindBidirectional(StringProperty propertyValue) {

		textBox.textProperty().bindBidirectional(propertyValue);
	}

	private void initComponent() {

		setMinSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setPrefSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setMaxSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);

		textBox = new TextField();
		textBox.setPrefWidth(ViewLayout.TEXTBOX_WIDTH);
		textBox.setDisable(true);

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
						textBox.setDisable(true);
						editButton.setVisible(true);
						errorButton.setVisible(false);
					}
				}
			}
		});
		textBox.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {

				if (keyEvent.getCode() == KeyCode.ESCAPE
						|| keyEvent.getCode() == KeyCode.ENTER
						|| keyEvent.getCode() == KeyCode.TAB) {
					textBox.setDisable(true);
					editButton.setVisible(true);
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

		editButton = new Button();
		editButton.getStyleClass().add("edit-button");
		editButton.setVisible(true);
		editButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				textBox.setDisable(false);
				textBox.requestFocus();
				editButton.setVisible(false);
			}
		});

		getChildren().addAll(textBox, errorButton, editButton);
		showResults();
	}

	public void setText(String textValue) {

		textBox.setText(textValue);
	}

	public String getText() {

		return textBox.getText();
	}

	public StringProperty textProperty() {

		return textBox.textProperty();
	}

	@Override
	protected void layoutChildren() {

		textBox.resize(getWidth(), getHeight());
		errorButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
		editButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
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
				}
			}
		});

		textBox.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

				if (!textBox.isDisabled() && textBox.isFocused() == true) {
					if (textBox.getText().length() == 0) {
						if (resultContextMenu != null) {
							resultContextMenu.hide();
						}
					} else {
						List<ItemMaster> resultList = ValidationData
								.getItemList(textBox.getText().trim());

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
	
	private void populateMenu(List<ItemMaster> resultList) {

		resultContextMenu.getItems().clear();
		Iterator<ItemMaster> results = resultList.iterator();

		while (results.hasNext()) {
			final ItemMaster result = (ItemMaster) results.next();
			final HBox hBox = new HBox();
			hBox.setFillHeight(true);
			Label itemLabel = new Label(result.getItemName());
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

					itemProperty.set(result);
				}
			});
		}
	}

	public ItemProperty itemProperty() {

		return itemProperty;
	}
}
