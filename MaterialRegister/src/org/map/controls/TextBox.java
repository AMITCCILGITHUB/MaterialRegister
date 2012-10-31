package org.map.controls;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Region;

import org.map.utils.Layout;

public class TextBox extends Region {

	private TextField textBox;
	private Button errorButton;

	public TextBox(String promptText) {

		initComponent(promptText);
	}

	public TextBox(String promptText, StringProperty propertyValue) {

		initComponent(promptText);
		textBox.textProperty().bindBidirectional(propertyValue);
	}

	private void initComponent(String promptText) {

		setMinSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setPrefSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setMaxSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);

		textBox = new TextField();
		textBox.setPrefWidth(Layout.TEXTBOX_WIDTH);
		textBox.setPromptText(promptText);

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
	}

	public void reset() {

		textBox.setText("");
	}

	public void setText(String textValue) {

		textBox.setText(textValue);
	}

	public String getText() {

		return textBox.getText();
	}

	@Override
	protected void layoutChildren() {

		textBox.resizeRelocate(0, 0, getWidth(), getHeight());
		errorButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
	}
}
