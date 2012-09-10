package org.map.controls;

import org.map.utils.Layout;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class TTSCheckBox extends Region {

    private TextBox textBox;
    private CheckBox checkBox;
    private String selectedTextValue = "YES";
    private String deselectedTextValue = "NO";
    private String indeterminateTextValue = "N/A";

    public TTSCheckBox() {
        initComponent("", "");
    }

    public TTSCheckBox(String textValue, String promptText) {
        initComponent(textValue, promptText);
    }

    public TTSCheckBox(String textValue, String promptText, StringProperty propertyName, boolean bidirectional) {
        initComponent(textValue, promptText);
        if (bidirectional) {
            textBox.bindBidirectional(propertyName);
        } else {
            textBox.bind(propertyName);
        }
    }
    
    public TTSCheckBox(String textValue, String promptText, StringProperty propertyName, boolean bidirectional, final SimpleIntegerProperty value, boolean bidirectionnalVal) {
        initComponent(textValue, promptText);
        if (bidirectional) {
            textBox.bindBidirectional(propertyName);
        } else {
            textBox.bind(propertyName);
        }

        value.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue == null) {
                    checkBox.setText(indeterminateTextValue);
                } else {
                    if (newValue.intValue() >= -1 && newValue.intValue() <= 1) {
                        checkBox.setText((newValue.intValue() == 0) ? indeterminateTextValue : (newValue.intValue() == 1) ? selectedTextValue : deselectedTextValue);
                    } else {
                        checkBox.setText(indeterminateTextValue);
                    }
                }
            }
        });
        if (bidirectionnalVal) {
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue ov, Boolean old_val, Boolean new_val) {
                    value.set(checkBox.isSelected() ? 1 : (checkBox.isIndeterminate() ? 0 : -1));
                }
            });
        }
    }    

    private void initComponent(final String textValue, final String promptText) {
        getStyleClass().add( "text-check-box" );

        setMinSize(Layout.getLabelTextBoxWidth() + Layout.getCheckBoxWidth(), Layout.getRegionHeight());
        setPrefSize(Layout.getLabelTextBoxWidth() + Layout.getCheckBoxWidth(), Layout.getRegionHeight());
        setMaxSize(Layout.getLabelTextBoxWidth() + Layout.getCheckBoxWidth(), Layout.getRegionHeight());

        textBox = new TextBox(textValue, promptText);
        textBox.setPrefWidth(Layout.getLabelTextBoxWidth());

        checkBox = CheckBoxBuilder.create().allowIndeterminate(true).prefHeight(Layout.getCheckBoxHeight()).prefWidth(Layout.getCheckBoxWidth()).build();
        checkBox.getStyleClass().add( "editable-check-box" );
        checkBox.setIndeterminate(true);
        checkBox.setText(indeterminateTextValue);
        checkBox.setOnMouseClicked(
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(final MouseEvent mouseEvent) {
                        final String newText = checkBox.isIndeterminate() ? indeterminateTextValue : checkBox.isSelected() ? selectedTextValue : deselectedTextValue;
                        checkBox.setText(newText);
                    }
                });

        getChildren().addAll(textBox, checkBox);
    }

    public String getLabelText() {
        return textBox.getText();
    }

    public void setLabelText(String textVal) {
        textBox.setText(textVal);
    }

    public int getValue() {
        return (checkBox.isSelected() ? 1 : (checkBox.isIndeterminate() ? 0 : -1));
    }

    public void setValue(int val) {
        checkBox.setText((val == 0) ? indeterminateTextValue : (val == 1) ? selectedTextValue : deselectedTextValue);
    }

    @Override
    protected void layoutChildren() {
        textBox.resizeRelocate(0, 0, textBox.getPrefWidth(), getHeight());
        checkBox.resizeRelocate(textBox.getPrefWidth() + 8, 0, checkBox.getPrefWidth(), getHeight());
    }
}
