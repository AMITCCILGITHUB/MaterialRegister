package org.map.controls;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

import org.map.utils.Layout;

public class CountBox extends Region {

	private IntField textBox;
	private Button addButton;
	private Button subButton;

	public CountBox(IntegerProperty propertyValue) {

		initComponent();
		textBox.valueProperty().bindBidirectional(propertyValue);
	}

	public void bind(StringProperty propertyValue) {

		textBox.textProperty().bind(propertyValue);
	}

	public void bindBidirectional(StringProperty propertyValue) {

		textBox.textProperty().bindBidirectional(propertyValue);
	}

	private void initComponent() {

		setMinSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setPrefSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setMaxSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);

		textBox = new IntField(0);
		textBox.setPrefWidth(Layout.TEXTBOX_WIDTH);
		textBox.setDisable(true);

		addButton = new Button();
		addButton.getStyleClass().add("next-button");
		addButton.setFocusTraversable(true);

		subButton = new Button();
		subButton.getStyleClass().add("previous-button");
		subButton.setFocusTraversable(true);

		getChildren().addAll(textBox, subButton, addButton);
	}

	public void setOnAddButtonAction(EventHandler<ActionEvent> eh) {

		addButton.setOnAction(eh);
	}

	public void setOnSubButtonAction(EventHandler<ActionEvent> eh) {

		subButton.setOnAction(eh);
	}

	public void setValue(int textValue) {

		textBox.setValue(textValue);
	}

	public int getValue() {

		return textBox.getValue();
	}

	@Override
	protected void layoutChildren() {

		textBox.resizeRelocate(0, 0, getWidth(), getHeight());
		addButton.resizeRelocate(getWidth() - 18, 4, 16, 16);
		subButton.resizeRelocate(getWidth() - 36, 4, 16, 16);
	}
}
