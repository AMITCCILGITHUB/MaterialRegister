package org.map.controls;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Region;

import org.map.utils.ViewLayout;

public class PasswordBox extends Region {

	private PasswordField textBox;
	private Button errorButton;

	public PasswordBox() {

		initComponent("");
	}

	public PasswordBox(String promptText) {

		initComponent(promptText);
	}
	
	public PasswordBox(String promptText, String textValue) {

		initComponent(promptText);
		textBox.setText(textValue);
	}

	public PasswordBox(String promptText, StringProperty propertyValue) {

		initComponent(promptText);
		textBox.textProperty().bindBidirectional(propertyValue);
	}

	public void bind(StringProperty propertyValue) {

		textBox.textProperty().bind(propertyValue);
	}

	public void bindBidirectional(StringProperty propertyValue) {

		textBox.textProperty().bindBidirectional(propertyValue);
	}

	private void initComponent(String promptText) {

		setMinSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setPrefSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setMaxSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);

		textBox = new PasswordField();
		textBox.setPrefWidth(ViewLayout.TEXTBOX_WIDTH);
		textBox.setPromptText(promptText);

		errorButton = new Button();
		errorButton.getStyleClass().add("error-button");
		errorButton.setVisible(false);
		errorButton.setFocusTraversable(false);

		textBox.focusedProperty().addListener(new ChangeListener<Boolean>() {

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
		errorButton.resizeRelocate(getWidth() - 18, 3, 12, 13);
	}
}
