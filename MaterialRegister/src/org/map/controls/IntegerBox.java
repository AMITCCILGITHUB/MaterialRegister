package org.map.controls;

import org.map.utils.Layout;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Region;

public class IntegerBox extends Region {

    private String type;
    private IntField textBox;

    public IntegerBox() {
        initComponent( "", "" );
        textBox.setText( "" );
    }

    public IntegerBox( String type, String promptText ) {
        initComponent( type, promptText );
    }

    public IntegerBox( String type, String promptText, IntegerProperty propertyValue, boolean bidirectional ) {
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

        getChildren().addAll( textBox );
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setText( String textValue ) {
        textBox.setText( textValue );
    }

    public String getText() {
        return textBox.getText();
    }

    @Override
    protected void layoutChildren() {
        textBox.resizeRelocate( 0, 0, getWidth(), getHeight() );
    }
}
