package org.map.controls;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

import org.map.utils.Layout;

public class EditableBox extends Region {

	private TextField textBox;
	private Button editButton;

	public EditableBox() {

		initComponent("", "");
	}

	public EditableBox(String textValue, String promptText) {

		initComponent(textValue, promptText);
	}

	public EditableBox(String textValue, String promptText,
			StringProperty propertyValue) {

		if (textValue.trim().length() > 0) {
			initComponent(textValue, promptText);
		} else {
			initComponent(propertyValue.getValue(), promptText);
		}

		textBox.textProperty().bindBidirectional(propertyValue);
	}

	public void bind(StringProperty propertyValue) {

		textBox.textProperty().bind(propertyValue);
	}

	public void bindBidirectional(StringProperty propertyValue) {

		textBox.textProperty().bindBidirectional(propertyValue);
	}

	private void initComponent(String textValue, String promptText) {

		setMinSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setPrefSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setMaxSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);

		textBox = new TextField();
		textBox.setPrefWidth(Layout.TEXTBOX_WIDTH);
		textBox.setText(textValue);
		textBox.setDisable(true);
		textBox.setPromptText(promptText);

		textBox.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {

				if (oldValue == true && newValue == false) {
					textBox.setDisable(true);
					editButton.setVisible(true);
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

		getChildren().addAll(textBox, editButton);
	}

	public StringProperty textProperty() {

		return textBox.textProperty();
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
		editButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
	}
}
