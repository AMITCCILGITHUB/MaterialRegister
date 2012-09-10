package org.map.controls;

import org.map.utils.Layout;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class CountBox extends Region {

    private String type;
    private IntField textBox;
    private Button addButton;
    private Button subButton;

    public CountBox() {
        initComponent( "", "" );
        textBox.setText( "" );
    }

    public CountBox( String type, String promptText ) {
        initComponent( type, promptText );
    }

    public CountBox( String type, String promptText, IntegerProperty propertyValue, boolean bidirectional ) {
        initComponent( type, promptText );
        if ( bidirectional ) {
            textBox.valueProperty().bindBidirectional( propertyValue );
        }
        else {
            textBox.valueProperty().bind( propertyValue );
        }
    }

    public void bind( StringProperty propertyValue ) {
        textBox.textProperty().bind( propertyValue );
    }

    public void bindBidirectional( StringProperty propertyValue ) {
        textBox.textProperty().bindBidirectional( propertyValue );
    }

    private void initComponent( String type, String promptText ) {
        setType( type );

        setMinSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        setPrefSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        setMaxSize( Layout.getRegionWidth(), Layout.getRegionHeight() );

        textBox = new IntField( 0 );
        textBox.setPrefWidth( Layout.getTextBoxWidth() );
        textBox.setDisable( true );
        textBox.setPromptText( promptText );

        addButton = new Button();
        addButton.getStyleClass().add( "next-button" );
        addButton.setFocusTraversable( true );

        subButton = new Button();
        subButton.getStyleClass().add( "previous-button" );
        subButton.setFocusTraversable( true );

        getChildren().addAll( textBox, subButton, addButton );
    }

    public void setOnAddButtonAction( EventHandler<ActionEvent> eh ) {
        addButton.setOnAction( eh );
    }

    public void setOnSubButtonAction( EventHandler<ActionEvent> eh ) {
        subButton.setOnAction( eh );
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setValue( int textValue ) {
        textBox.setValue( textValue );
    }

    public int getValue() {
        return textBox.getValue();
    }

    @Override
    protected void layoutChildren() {
        textBox.resizeRelocate( 0, 0, getWidth(), getHeight() );
        addButton.resizeRelocate( getWidth() - 18, 4, 16, 16 );
        subButton.resizeRelocate( getWidth() - 36, 4, 16, 16 );
    }
}
