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
		comboBox.getSelectionModel().clearSelection();
	}

	public CustomComboBox(String textValue, String promptText, String type) {

		initComponent(promptText, type);
		comboBox.getSelectionModel().select(textValue);
	}

	public CustomComboBox(String textValue, String promptText, String type,
			StringProperty propertyValue, boolean bidirectional) {

		initComponent(promptText, type);
		if (bidirectional) {
			comboBox.valueProperty().bindBidirectional(propertyValue);
		} else {
			comboBox.valueProperty().bind(propertyValue);
		}
		comboBox.getSelectionModel().select(textValue);
	}

	public void bind(StringProperty propertyValue) {

		comboBox.valueProperty().bind(propertyValue);
	}

	public void bindBidirectional(StringProperty propertyValue) {

		comboBox.valueProperty().bindBidirectional(propertyValue);
	}

	private void initComponent(String promptText, final String type) {

		setType(type);

		setMinSize(Layout.getRegionWidth(), Layout.getRegionHeight());
		setPrefSize(Layout.getRegionWidth(), Layout.getRegionHeight());
		setMaxSize(Layout.getRegionWidth(), Layout.getRegionHeight());

		comboBox = new ComboBox();
		comboBox.getStyleClass().add("result-combo-box");
		comboBox.setPrefWidth(Layout.getTextBoxWidth());
		if (type.equalsIgnoreCase("Material")) {
			comboBox.getItems().addAll(
					AppProperties.getValue("material.year.list").split(","));
		} else if (type.equalsIgnoreCase("HeatChart")) {
			comboBox.getItems().addAll(
					AppProperties.getValue("heatchart.year.list").split(","));
		} else if (type.equalsIgnoreCase("Test")) {
			comboBox.getItems().addAll(ValidationData.getValidationName(type));
		} else if (type.equalsIgnoreCase("ValidationType")) {
			comboBox.getItems().addAll(ValidationData.getValidationTypes());
		} else if (type.equalsIgnoreCase("Role")) {
			comboBox.getItems().addAll(ValidationData.getValidationName(type));
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
