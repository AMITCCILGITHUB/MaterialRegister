package org.map.controls;

import org.map.utils.Layout;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Region;

public class ViewIntegerBox extends Region {

    private IntField textBox;

    public ViewIntegerBox() {
        initComponent();
        textBox.setValue(1);
    }

    public ViewIntegerBox(int value) {
        initComponent();
        textBox.setValue(value);
    }

    public ViewIntegerBox(int value, IntegerProperty propertyValue, boolean bidirectional) {
        initComponent();
        if (bidirectional) {
            textBox.valueProperty().bindBidirectional(propertyValue);
        } else {
            textBox.valueProperty().bind(propertyValue);
        }
        textBox.setValue(value);
    }

    public void bind(StringProperty propertyValue) {
        textBox.textProperty().bind(propertyValue);
    }

    public void bindBidirectional(StringProperty propertyValue) {
        textBox.textProperty().bindBidirectional(propertyValue);
    }

    private void initComponent() {
        getStyleClass().add( "view-text-box" );

        setMinSize(Layout.getRegionWidth(), Layout.getRegionHeight());
        setPrefSize(Layout.getRegionWidth(), Layout.getRegionHeight());
        setMaxSize(Layout.getRegionWidth(), Layout.getRegionHeight());

        textBox = new IntField(0);
        textBox.setPrefWidth(Layout.getTextBoxWidth());

        textBox.setDisable(true);

        getChildren().addAll(textBox);
    }

    public void setValue(int value) {
        textBox.setValue(value);
    }

    public int getValue() {
        return textBox.getValue();
    }

    @Override
    protected void layoutChildren() {
        textBox.resizeRelocate(0, 0, getWidth(), getHeight());
    }
}
