package org.map.controls;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Region;

import org.map.utils.Layout;

public class IntegerBox extends Region {

	private IntField textBox;

	public IntegerBox() {

		initComponent(0, "");
	}

	public IntegerBox(Integer textValue, String promptText) {

		initComponent(textValue, promptText);
	}

	public IntegerBox(Integer textValue, String promptText,
			IntegerProperty propertyValue, boolean bidirectional) {

		if (textValue.intValue() == 0) {
			initComponent(textValue, promptText);
		} else {
			initComponent(propertyValue.getValue(), promptText);
		}

		if (bidirectional) {
			textBox.valueProperty().bindBidirectional(propertyValue);
		} else {
			textBox.valueProperty().bind(propertyValue);
		}
	}

	public void bind(StringProperty propertyValue) {

		textBox.textProperty().bind(propertyValue);
	}

	public void bindBidirectional(StringProperty propertyValue) {

		textBox.textProperty().bindBidirectional(propertyValue);
	}

	private void initComponent(Integer textValue, String promptText) {

		setMinSize(Layout.getRegionWidth(), Layout.getRegionHeight());
		setPrefSize(Layout.getRegionWidth(), Layout.getRegionHeight());
		setMaxSize(Layout.getRegionWidth(), Layout.getRegionHeight());

		textBox = new IntField(0);
		textBox.setPrefWidth(Layout.getTextBoxWidth());
		textBox.setValue(textValue);
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
