package org.map.utils;

import java.net.URISyntaxException;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import org.map.MaterialRegister;

public class TableContextMenu extends ContextMenu {

    public TableContextMenu(EventHandler eventHandler1, EventHandler eventHandler2, EventHandler eventHandler3) throws URISyntaxException {
        getItems().addAll(
                MenuItemBuilder.create()
                .text( "Restore" )
                .graphic( ImageViewBuilder.create().image( new Image(MaterialRegister.class.getResourceAsStream("/org/map/images/undo.png")) ) .build() )
                .onAction( eventHandler1 ).build(),
                MenuItemBuilder.create()
                .text( "Delete" )
                .onAction( eventHandler2 )
                .graphic( ImageViewBuilder.create().image( new Image(MaterialRegister.class.getResourceAsStream("/org/map/images/delete.png")) ) .build() )
                .build() ,
                SeparatorMenuItemBuilder.create().build(),
                MenuItemBuilder.create()
                .text( "Print" )
                .onAction( eventHandler3 )
                .graphic( ImageViewBuilder.create().image( new Image(MaterialRegister.class.getResourceAsStream("/org/map/images/print.png")) ) .build() )
                .build() 
           );
        setAutoFix( true );
    }
    
    public TableContextMenu(EventHandler eventHandler1, EventHandler eventHandler2) throws URISyntaxException {
        getItems().addAll(
                MenuItemBuilder.create()
                .text( "Restore" )
                .graphic( ImageViewBuilder.create().image( new Image(MaterialRegister.class.getResourceAsStream("/org/map/images/undo.png")) ) .build() )
                .onAction( eventHandler1 ).build(),
                MenuItemBuilder.create()
                .text( "Delete" )
                .onAction( eventHandler2 )
                .graphic( ImageViewBuilder.create().image( new Image(MaterialRegister.class.getResourceAsStream("/org/map/images/delete.png")) ) .build() )
                .build()
           );
        setAutoFix( true );
    }  
    
    public TableContextMenu(EventHandler eventHandler1, String type) throws URISyntaxException {
        switch(type){
            case "Print" :  getItems().addAll(
                                MenuItemBuilder.create()
                                .text( "Print" )
                                .onAction( eventHandler1 )
                                .graphic( ImageViewBuilder.create().image( new Image(MaterialRegister.class.getResourceAsStream("/org/map/images/print.png")) ) .build() )
                                .build() 
                            );
                            break;
            case "Password" :   getItems().addAll(
                                    MenuItemBuilder.create()
                                    .text( "Show Password" )
                                    .onAction( eventHandler1 )
                                    .graphic( ImageViewBuilder.create().image( new Image(MaterialRegister.class.getResourceAsStream("/org/map/images/search.png")) ) .build() )
                                    .build() 
                                );
                                break;
        }
        setAutoFix( true );
    }    
}
