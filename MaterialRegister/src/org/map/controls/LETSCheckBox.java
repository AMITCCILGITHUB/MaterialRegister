package org.map.controls;

import org.map.utils.Layout;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class LETSCheckBox extends Region {

	private Label label;
	private CheckBox checkBox;
	private Button editButton;
	private String selectedTextValue = "YES";
	private String deselectedTextValue = "NO";
	private String indeterminateTextValue = "N/A";

	public LETSCheckBox() {

		initComponent("");
		checkBox.setText(deselectedTextValue);
		checkBox.setSelected(false);
	}

	public LETSCheckBox(String labelText, int defValue) {

		initComponent(labelText);
		checkBox.setText((defValue == 0) ? indeterminateTextValue
				: (defValue == 1) ? selectedTextValue : deselectedTextValue);
		if (defValue == 0) {
			checkBox.setIndeterminate(true);
		} else if (defValue < 0) {
			checkBox.setSelected(false);
		} else {
			checkBox.setSelected(true);
		}
		checkBox.setText(labelText);
	}

	public LETSCheckBox(String labelText, int defValue,
			final SimpleIntegerProperty value, boolean bidirectionnal) {

		initComponent(labelText);
		value.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldValue, Number newValue) {

				if (newValue == null) {
					checkBox.setText(indeterminateTextValue);
				} else {
					if (newValue.intValue() >= -1 && newValue.intValue() <= 1) {
						checkBox.setText((newValue.intValue() == 0) ? indeterminateTextValue
								: (newValue.intValue() == 1) ? selectedTextValue
										: deselectedTextValue);
					} else {
						checkBox.setText(indeterminateTextValue);
					}
				}
			}
		});
		if (bidirectionnal) {
			checkBox.selectedProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(ObservableValue ov,
								Boolean old_val, Boolean new_val) {

							value.set(checkBox.isSelected() ? 1 : (checkBox
									.isIndeterminate() ? 0 : -1));
						}
					});
		}

		checkBox.setText((defValue == 0) ? indeterminateTextValue
				: (defValue == 1) ? selectedTextValue : deselectedTextValue);
	}

	private void initComponent(final String labelText) {

		getStyleClass().add("text-check-box");

		setMinSize(Layout.getLabelWidth() + Layout.getCheckBoxWidth(),
				Layout.getRegionHeight());
		setPrefSize(Layout.getLabelWidth() + Layout.getCheckBoxWidth(),
				Layout.getRegionHeight());
		setMaxSize(Layout.getLabelWidth() + Layout.getCheckBoxWidth(),
				Layout.getRegionHeight());

		label = new Label(labelText);
		label.setPrefWidth(Layout.getLabelWidth());

		checkBox = CheckBoxBuilder.create().allowIndeterminate(true)
				.disable(true).prefHeight(Layout.getCheckBoxHeight())
				.prefWidth(Layout.getCheckBoxWidth()).build();
		checkBox.getStyleClass().add("editable-check-box");
		checkBox.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {

				if (oldValue == true && newValue == false) {
					checkBox.setDisable(true);
					editButton.setVisible(true);
				}
			}
		});
		checkBox.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {

				if (keyEvent.getCode() == KeyCode.ESCAPE
						|| keyEvent.getCode() == KeyCode.ENTER
						|| keyEvent.getCode() == KeyCode.TAB) {
					checkBox.setDisable(true);
					editButton.setVisible(true);
				}
			}
		});
		checkBox.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(final MouseEvent mouseEvent) {

				final String newText = checkBox.isIndeterminate() ? indeterminateTextValue
						: checkBox.isSelected() ? selectedTextValue
								: deselectedTextValue;
				checkBox.setText(newText);
			}
		});
		checkBox.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String oldValue, String newValue) {

				if (newValue.equals(indeterminateTextValue)) {
					checkBox.setIndeterminate(true);
				} else if (newValue.equals(selectedTextValue)) {
					checkBox.setSelected(true);
				} else {
					checkBox.setSelected(false);
				}
			}
		});

		editButton = new Button();
		editButton.getStyleClass().add("edit-button");
		editButton.setVisible(true);
		editButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				checkBox.setDisable(false);
				checkBox.requestFocus();
				editButton.setVisible(false);
			}
		});

		getChildren().addAll(label, checkBox, editButton);
	}

	public String getLabelText() {

		return label.getText();
	}

	public void setLabelText(String textVal) {

		label.setText(textVal);
	}

	public int getValue() {

		return (checkBox.isSelected() ? 1 : (checkBox.isIndeterminate() ? 0
				: -1));
	}

	public void setValue(int val) {

		checkBox.setText((val == 0) ? indeterminateTextValue
				: (val == 1) ? selectedTextValue : deselectedTextValue);
	}

	@Override
	protected void layoutChildren() {

		label.resizeRelocate(0, 0, label.getPrefWidth(), getHeight());
		checkBox.resizeRelocate(label.getPrefWidth() + 8, 0,
				checkBox.getPrefWidth(), getHeight());
		editButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
	}
}
