package org.map.controls;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import org.map.utils.Layout;

public class ViewBox extends Region {

	private TextField textBox;

	public ViewBox() {

		initComponent("");
	}

	public ViewBox(String textValue) {

		initComponent(textValue);
	}

	public ViewBox(String textValue, StringProperty propertyValue,
			boolean bidirectional) {

		if (textValue.trim().length() > 0) {
			initComponent(textValue);
		} else {
			initComponent(propertyValue.getValue());
		}

		if (bidirectional) {
			textBox.textProperty().bindBidirectional(propertyValue);
		} else {
			textBox.textProperty().bind(propertyValue);
		}
	}

	public void bind(StringProperty propertyValue) {

		textBox.textProperty().bind(propertyValue);
	}

	public void bindBidirectional(StringProperty propertyValue) {

		textBox.textProperty().bindBidirectional(propertyValue);
	}

	private void initComponent(String textValue) {

		getStyleClass().add("view-text-box");

		setMinSize(Layout.getRegionWidth(), Layout.getRegionHeight());
		setPrefSize(Layout.getRegionWidth(), Layout.getRegionHeight());
		setMaxSize(Layout.getRegionWidth(), Layout.getRegionHeight());

		textBox = new TextField();
		textBox.setPrefWidth(Layout.getTextBoxWidth());
		textBox.setText(textValue);
		textBox.setDisable(true);

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

		textBox.resize(getWidth(), getHeight());
	}
}
