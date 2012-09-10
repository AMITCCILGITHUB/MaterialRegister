package org.map.controls;

import org.map.utils.Layout;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

public class EIntegerBox extends Region {
    
    private IntField textBox;
    private Button editButton;
    
    public EIntegerBox() {
        initComponent( "" );
        textBox.setText( "" );
    }
    
    public EIntegerBox( String textValue, String promptText ) {
        initComponent( promptText );
        textBox.setText( textValue );
    }
    
    public EIntegerBox( String textValue, String promptText, IntegerProperty propertyValue, boolean bidirectional ) {
        initComponent( promptText );
        if ( bidirectional ) {
            textBox.valueProperty().bindBidirectional( propertyValue );
        }
        else {
            textBox.valueProperty().bind( propertyValue );
        }
        textBox.setText( textValue );
    }
    
    public void bind( IntegerProperty propertyValue ) {
        textBox.valueProperty().bind( propertyValue );
    }
    
    public void bindBidirectional( IntegerProperty propertyValue ) {
        textBox.valueProperty().bindBidirectional( propertyValue );
    }
    
    private void initComponent( String promptText ) {
        
        setMinSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        setPrefSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        setMaxSize( Layout.getRegionWidth(), Layout.getRegionHeight() );
        
        textBox = new IntField( 0 );
        textBox.setPrefWidth( Layout.getTextBoxWidth() );
        textBox.setDisable( true );
        textBox.setPromptText( promptText );
        
        textBox.focusedProperty().addListener( new ChangeListener<Boolean>() {
            
            public void changed( ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue ) {
                if ( oldValue == true && newValue == false ) {
                    textBox.setDisable( true );
                    editButton.setVisible( true );
                }
            }
        } );
        textBox.setOnKeyPressed( new EventHandler<KeyEvent>() {
            
            @Override
            public void handle( KeyEvent keyEvent ) {
                if ( keyEvent.getCode() == KeyCode.ESCAPE || keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.TAB ) {
                    textBox.setDisable( true );
                    editButton.setVisible( true );
                }
            }
        } );
        
        editButton = new Button();
        editButton.getStyleClass().add( "edit-button" );
        editButton.setVisible( true );
        editButton.setOnAction( new EventHandler<ActionEvent>() {
            
            @Override
            public void handle( ActionEvent actionEvent ) {
                textBox.setDisable( false );
                textBox.requestFocus();
                editButton.setVisible( false );
            }
        } );
        
        getChildren().addAll( textBox, editButton );
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
        editButton.resizeRelocate( getWidth() - 18, 6, 12, 13 );
    }
}
