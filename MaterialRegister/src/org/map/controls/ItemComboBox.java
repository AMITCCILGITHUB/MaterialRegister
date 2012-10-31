package org.map.controls;

import java.util.Iterator;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.ItemMaster;
import org.map.hibernate.ddo.ItemProperty;
import org.map.utils.Layout;

public class ItemComboBox extends Region {

	private ItemProperty itemProperty;
	private TextField textBox;
	private Button errorButton;
	private ContextMenu resultContextMenu = new ContextMenu();
	private Tooltip searchErrorTooltip = new Tooltip();
	private Timeline searchErrorTooltipHidder = null;

	public ItemComboBox() {

		itemProperty = new ItemProperty();
		initComponent();
	}

	public ItemComboBox(String promptText) {

		itemProperty = new ItemProperty();
		initComponent();

		textBox.setPromptText(promptText);
		itemProperty.get().itemNameProperty()
				.bindBidirectional(textBox.textProperty());
	}

	public ItemComboBox(String promptText, ItemProperty item) {

		itemProperty = new ItemProperty();
		initComponent();

		textBox.setPromptText(promptText);
		itemProperty.bindBidirectional(item);
		itemProperty.get().itemNameProperty()
				.bindBidirectional(textBox.textProperty());
	}

	private void initComponent() {

		setId("ResultBox");

		setMinSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setPrefSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setMaxSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);

		textBox = new TextField();
		textBox.setPrefWidth(Layout.TEXTBOX_WIDTH);

		errorButton = new Button();
		errorButton.getStyleClass().add("error-button");
		errorButton.setVisible(false);
		errorButton.setTooltip(new Tooltip("this field can\nnot be empty"));
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

	private void showError(TextField textBox, String message) {

		searchErrorTooltip.setText(message);
		if (searchErrorTooltipHidder != null) {
			searchErrorTooltipHidder.stop();
		}
		if (message != null) {
			Point2D toolTipPos = textBox.localToScene(0, textBox
					.getLayoutBounds().getHeight());
			double x = toolTipPos.getX() + textBox.getScene().getX()
					+ textBox.getScene().getWindow().getX();
			double y = toolTipPos.getY() + textBox.getScene().getY()
					+ textBox.getScene().getWindow().getY();
			searchErrorTooltip.show(textBox.getScene().getWindow(), x, y);
			searchErrorTooltipHidder = new Timeline();
			searchErrorTooltipHidder.getKeyFrames().add(
					new KeyFrame(Duration.seconds(3),
							new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent t) {

									searchErrorTooltip.hide();
									searchErrorTooltip.setText(null);
								}
							}));
			searchErrorTooltipHidder.play();
		} else {
			searchErrorTooltip.hide();
		}
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
						showError(null, null);
					} else {
						List<ItemMaster> resultList = ValidationData
								.getItemList();

						if (resultList.size() > 0) {
							showError(null, null);
							populateMenu(resultList);
							if (!resultContextMenu.isShowing()) {
								resultContextMenu.show(textBox, Side.BOTTOM,
										10, -5);
							}
						} else {
							if (searchErrorTooltip.getText() != null) {
								showError(textBox, "No matches");
							}
							resultContextMenu.hide();
						}
						resultContextMenu.requestFocus();
					}
				}
			}
		});
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
