package org.map.controls;

import org.map.utils.Layout;
import javafx.beans.property.StringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

public class ViewBox extends Region {
    
    private TextField textBox;
    
    public ViewBox() {
        initComponent();
        textBox.setText( "" );
    }
    
    public ViewBox( String textValue ) {
        initComponent();
        textBox.setText( textValue );
    }
    
    public ViewBox( String textValue, StringProperty propertyValue, boolean bidirectional ) {
        initComponent();
        if ( bidirectional ) {
            textBox.textProperty().bindBidirectional( propertyValue );
        }
        else {
            textBox.textProperty().bind( propertyValue );
        }
        textBox.setText( textValue );
    }
    
    public void bind( StringProperty propertyValue ) {
        textBox.textProperty().bind( propertyValue );
    }
    
    public void bindBidirectional( StringProperty propertyValue ) {
        textBox.textProperty().bindBidirectional( propertyValue );
    }
    
    private void initComponent() {
        getStyleClass().add( "view-text-box" );
        
        setMinSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        setPrefSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        setMaxSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        
        textBox = new TextField();
        textBox.setPrefWidth( Layout.getTextBoxWidth() );
        textBox.setDisable( true );
        
        getChildren().addAll( textBox );
    }
    
    public void setText( String textValue ) {
        textBox.setText( textValue );
    }
    
    public String getText() {
        return textBox.getText();
    }
    
    @Override
    protected void layoutChildren() {
        textBox.resize( getWidth(), getHeight() );
    }
}
