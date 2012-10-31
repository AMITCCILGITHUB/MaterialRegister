package org.map.controls;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;

import org.map.hibernate.dao.ValidationData;
import org.map.utils.AppProperties;
import org.map.utils.Layout;

public class CustomComboBox extends Region {

	private String type;
	private ComboBox<String> comboBox;

	public CustomComboBox() {

		initComponent("", "");
	}

	public CustomComboBox(String textValue, String promptText, String type) {

		initComponent(promptText, type);
		comboBox.getSelectionModel().select(textValue);
	}

	public CustomComboBox(String promptText, String type,
			StringProperty propertyValue) {

		initComponent(promptText, type);
		comboBox.valueProperty().bindBidirectional(propertyValue);
	}

	public CustomComboBox(String textValue, String promptText, String type,
			StringProperty propertyValue) {

		initComponent(promptText, type);
		comboBox.getSelectionModel().select(textValue);
		comboBox.valueProperty().bindBidirectional(propertyValue);
	}

	public void bind(StringProperty propertyValue) {

		comboBox.valueProperty().bind(propertyValue);
	}

	public void bindBidirectional(StringProperty propertyValue) {

		comboBox.valueProperty().bindBidirectional(propertyValue);
	}

	private void initComponent(String promptText, final String type) {

		setType(type);

		setMinSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setPrefSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);
		setMaxSize(Layout.REGION_WIDTH, Layout.REGION_HEIGHT);

		comboBox = new ComboBox();
		comboBox.getStyleClass().add("result-combo-box");
		comboBox.setPrefWidth(Layout.TEXTBOX_WIDTH);

		if (type.equalsIgnoreCase("Material")) {
			comboBox.getItems().addAll(
					AppProperties.getValue("material.year.list").split(","));
		} else if (type.equalsIgnoreCase("HeatChart")) {
			comboBox.getItems().addAll(
					AppProperties.getValue("heatchart.year.list").split(","));

		} else if (type.equalsIgnoreCase("Agency")) {
			comboBox.getItems().addAll(
					ValidationData.getValidationNameList("Agency"));
		} else if (type.equalsIgnoreCase("Customer")) {
			comboBox.getItems().addAll(
					ValidationData.getValidationNameList("Customer"));
		} else if (type.equalsIgnoreCase("Item")) {
			comboBox.getItems().addAll(
					ValidationData.getValidationNameList("Item"));
		} else if (type.equalsIgnoreCase("Laboratory")) {
			comboBox.getItems().addAll(
					ValidationData.getValidationNameList("Laboratory"));
		} else if (type.equalsIgnoreCase("Result")) {
			comboBox.getItems().addAll(
					ValidationData.getValidationNameList("Result"));
		} else if (type.equalsIgnoreCase("Role")) {
			comboBox.getItems().addAll(
					ValidationData.getValidationNameList("Role"));
		} else if (type.equalsIgnoreCase("Specification")) {
			comboBox.getItems().addAll(
					ValidationData.getValidationNameList("Specification"));
		} else if (type.equalsIgnoreCase("Test")) {
			comboBox.getItems().addAll(
					ValidationData.getValidationNameList("Test"));
		} else if (type.equalsIgnoreCase("ValidationType")) {
			comboBox.getItems().addAll(ValidationData.getValidationTypes());
		}

		getChildren().addAll(comboBox);
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public void setText(String textValue) {

		comboBox.getSelectionModel().select(textValue);
	}

	public String getText() {

		return comboBox.getSelectionModel().getSelectedItem();
	}

	@Override
	protected void layoutChildren() {

		comboBox.resize(getWidth(), getHeight());
	}

	public void addChangeListener(
			ChangeListener<? super String> selectionChangeListener) {

		comboBox.getSelectionModel().selectedItemProperty()
				.addListener(selectionChangeListener);
	}
}
