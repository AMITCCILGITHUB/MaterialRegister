package org.map.login;

import org.map.logger.LoggerUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.map.MaterialRegister;
import org.map.utils.Alert;
import org.map.controls.LoginTextBox;
import org.map.controls.PasswordBox;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;
import org.map.service.UserValidation;
import org.map.utils.StatusBar;

public class Login extends Application {

    private static Login login;
    private static Stage outerPrimaryStage;
    private double startDragX;
    private double startDragY;
    private UserMaster userMaster = new UserMaster();
    private StatusBar sb = new StatusBar();

    public static Login getLoginPanel() {
        return login;
    }

    public static Stage getPrimaryStage() {
        return outerPrimaryStage;
    }

    public static void main( String[] args ) {
        launch( args );
    }

    public UserMaster getUserMaster() {
        return userMaster;
    }

    public StatusBar getStatusBar() {
        return sb;
    }

    @Override
    public void start( final Stage primaryStage ) {
        LoggerUtil.getLogger().info( "Application Started" );
        outerPrimaryStage = primaryStage;
        login = this;

        primaryStage.setTitle( "Login: Material Register" );
        primaryStage.initStyle( StageStyle.TRANSPARENT );

        final StackPane root = new StackPane();
        root.getStyleClass().add( "login-pane" );

        Scene scene = new Scene( root, 500, 400, Color.TRANSPARENT );
        scene.getStylesheets().addAll( MaterialRegister.class.getResource( "/org/map/style/login.css" ).toExternalForm() );
        scene.getStylesheets().addAll( MaterialRegister.class.getResource( "/org/map/style/style.css" ).toExternalForm() );
        scene.getStylesheets().addAll( MaterialRegister.class.getResource( "/org/map/style/popup.css" ).toExternalForm() );

        VBox windowButtons = new VBox();
        Button closeBtn = new Button();
        closeBtn.getStyleClass().add( "close-button" );
        closeBtn.setPrefSize( 16, 16 );
        closeBtn.setMinSize( 16, 16 );
        closeBtn.setMaxSize( 16, 16 );
        closeBtn.setTranslateX( 468 );
        closeBtn.setTranslateY( 20 );
        windowButtons.getChildren().add( closeBtn );
        root.getChildren().addAll( windowButtons );

        VBox textFields = new VBox( 15 );
        final LoginTextBox userName = new LoginTextBox( "", "User Name", userMaster.userNameProperty(), true );
        userName.setMaxSize( 176, 20 );
        userName.setPrefSize( 176, 20 );
        userName.setMinSize( 176, 20 );
        final PasswordBox password = new PasswordBox( "", "Password", userMaster.passwordProperty(), true );
        password.setMaxSize( 176, 20 );
        password.setPrefSize( 176, 20 );
        password.setMinSize( 176, 20 );
        textFields.setTranslateX( 203 );
        textFields.setTranslateY( 171 );
        textFields.getChildren().addAll( userName, password );

        HBox buttonFields = new HBox( 10 );
        final Button submitBtn = new Button();
        submitBtn.getStyleClass().add( "submit-button" );
        submitBtn.setMaxSize( 94, 22 );
        submitBtn.setMinSize( 94, 22 );
        submitBtn.setPrefSize( 94, 22 );
        submitBtn.setText( "Login" );
        final Button resetBtn = new Button();
        resetBtn.getStyleClass().add( "submit-button" );
        resetBtn.setMaxSize( 94, 22 );
        resetBtn.setMinSize( 94, 22 );
        resetBtn.setPrefSize( 94, 22 );
        resetBtn.setText( "Reset" );
        buttonFields.setTranslateX( 188 );
        buttonFields.setTranslateY( 274 );
        buttonFields.getChildren().addAll( submitBtn, resetBtn );

        root.getChildren().addAll( textFields, buttonFields );

        final UserValidation service = new UserValidation();
        service.stateProperty().addListener( new ChangeListener<Worker.State>() {

            @Override
            public void changed( ObservableValue<? extends State> observable, State oldValue, Worker.State newState ) {
                if ( newState == Worker.State.SUCCEEDED ) {
                    sb.hide();
                    if ( service.getValue() ) {
                        LoggerUtil.getLogger().info( "User " + userMaster.getUserName() + " validated successfully." );
                        userMaster.resetUserMaster( UserData.getUserDetails( userMaster.getUserName() ) );
                        MaterialRegister mr = new MaterialRegister();
                        mr.start();
                        primaryStage.hide();
                    }
                    else {
                        LoggerUtil.getLogger().info( "User " + userMaster.getUserName() + " attempted with wrong password [" + userMaster.getPassword() + "]" );
                        Alert.showAlert( primaryStage, "Invalid Login", "Error", "Invalid user name or password.\nPlease try again." );
                    }
                }
            }
        } );

        closeBtn.setOnAction( new EventHandler<ActionEvent>() {

            @Override
            public void handle( ActionEvent actionEvent ) {
                Platform.exit();
            }
        } );
        submitBtn.setOnAction( new EventHandler<ActionEvent>() {

            @Override
            public void handle( ActionEvent event ) {
                if ( userMaster.getUserName().trim().length() > 0 && userMaster.getPassword().trim().length() > 0 ) {
                    sb.show();
                    service.restart();
                }
                else {
                    Alert.showAlert( primaryStage, "Invalid Login", "Error", "Either user name or password is empty.\nPlease try again." );
                }
            }
        } );
        userName.setOnKeyPressed( new EventHandler<KeyEvent>() {

            @Override
            public void handle( KeyEvent keyEvent ) {
                if ( keyEvent.getCode() == KeyCode.ENTER ) {
                    submitBtn.fire();
                }
            }
        } );
        password.setOnKeyPressed( new EventHandler<KeyEvent>() {

            @Override
            public void handle( KeyEvent keyEvent ) {
                if ( keyEvent.getCode() == KeyCode.ENTER ) {
                    submitBtn.fire();
                }
            }
        } );
        resetBtn.setOnAction( new EventHandler<ActionEvent>() {

            @Override
            public void handle( ActionEvent event ) {
                userMaster.resetUserMaster();
            }
        } );

        root.setOnMousePressed( new EventHandler<MouseEvent>() {

            @Override
            public void handle( MouseEvent me ) {
                startDragX = me.getSceneX();
                startDragY = me.getSceneY();
                root.setStyle( "-fx-cursor:move;" );
            }
        } );
        root.setOnMouseReleased( new EventHandler<MouseEvent>() {

            @Override
            public void handle( MouseEvent me ) {
                root.setStyle( "-fx-cursor:default;" );
            }
        } );
        root.setOnMouseDragged( new EventHandler<MouseEvent>() {

            @Override
            public void handle( MouseEvent me ) {
                primaryStage.setX( me.getScreenX() - startDragX );
                primaryStage.setY( me.getScreenY() - startDragY );
                root.setStyle( "-fx-cursor:move;" );
            }
        } );

        primaryStage.setScene( scene );
        primaryStage.show();

        sb.initComponents( primaryStage, "Loading..." );
    }
}
