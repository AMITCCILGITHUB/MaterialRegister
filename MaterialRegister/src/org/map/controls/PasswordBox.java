package org.map.controls;

import org.map.utils.Layout;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

public class PasswordBox extends Region {
    
    private PasswordField textBox;
    private Button errorButton;
    
    public PasswordBox() {
        initComponent( "", "" );
    }
    
    public PasswordBox( String textValue, String promptText ) {
        initComponent( textValue, promptText );
    }
    
    public PasswordBox( String textValue, String promptText, StringProperty propertyValue, boolean bidirectional ) {
        initComponent( textValue, promptText );
        if ( bidirectional ) {
            textBox.textProperty().bindBidirectional( propertyValue );
        }
        else {
            textBox.textProperty().bind( propertyValue );
        }
    }
    
    public void bind( StringProperty propertyValue ) {
        textBox.textProperty().bind( propertyValue );
    }
    
    public void bindBidirectional( StringProperty propertyValue ) {
        textBox.textProperty().bindBidirectional( propertyValue );
    }
    
    private void initComponent( String textValue, String promptText ) {
        setMinSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        setPrefSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        setMaxSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        
        textBox = new PasswordField();
        textBox.setPrefWidth( Layout.getTextBoxWidth() );
        textBox.setText( textValue );
        textBox.setPromptText( promptText );
        
        errorButton = new Button();
        errorButton.getStyleClass().add( "error-button" );
        errorButton.setVisible( false );
        errorButton.setTooltip( new Tooltip( "this field can\nnot be empty" ) );
        errorButton.setFocusTraversable( false );
        
        textBox.focusedProperty().addListener( new ChangeListener<Boolean>() {
            
            public void changed( ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue ) {
                if ( oldValue == true && newValue == false ) {
                    if ( textBox.getText().length() == 0 ) {
                        errorButton.setVisible( true );
                    }
                    else {
                        errorButton.setVisible( false );
                    }
                }
            }
        } );
        
        textBox.textProperty().addListener( new ChangeListener<String>() {
            
            @Override
            public void changed( ObservableValue<? extends String> observable, String oldValue, String newValue ) {
                errorButton.setVisible( textBox.getText().length() == 0 );
            }
        } );
        
        getChildren().addAll( textBox, errorButton );
    }
    
    public void reset() {
        textBox.setText( "" );
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
        errorButton.resizeRelocate( getWidth() - 18, 3, 12, 13 );
    }
}
