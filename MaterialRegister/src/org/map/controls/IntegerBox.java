package org.map.controls;

import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.Region;

import org.map.utils.Layout;

public class IntegerBox extends Region {

	private IntField textBox;

	public IntegerBox() {

		initComponent("");
	}

	public IntegerBox(Integer textValue, String promptText) {

		initComponent(promptText);
		textBox.setValue(textValue);
	}

	public IntegerBox(String promptText, IntegerProperty propertyValue) {

		initComponent(promptText);
		textBox.valueProperty().bindBidirectional(propertyValue);
	}

	private void initComponent(String promptText) {

		setMinSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setPrefSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setMaxSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);

		textBox = new IntField(0);
		textBox.setPrefWidth(Layout.TEXTBOX_WIDTH);
		textBox.setPromptText(promptText);

		getChildren().addAll(textBox);
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
	}
}
