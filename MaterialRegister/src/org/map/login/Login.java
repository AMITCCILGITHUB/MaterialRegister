package org.map.login;

import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.map.controls.LoginTextBox;
import org.map.controls.PasswordBox;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.service.ServiceManager;
import org.map.utils.Context;
import org.map.utils.FileUtil;
import org.map.validation.Validator;

public class Login extends Application {

	private double startDragX;
	private double startDragY;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws MalformedURLException {

		Context.setHostServices(getHostServices());
		Context.setLoginStage(primaryStage);
		LoggerUtil.getLogger().info("Application Started");

		primaryStage.setTitle("Login: Material Register");
		primaryStage.initStyle(StageStyle.TRANSPARENT);

		final StackPane root = new StackPane();
		root.getStyleClass().add("login-pane");

		Scene scene = new Scene(root, 500, 400, Color.TRANSPARENT);
		scene.getStylesheets().addAll(FileUtil.getStyleAsUrl("style"),
				FileUtil.getStyleAsUrl("login"),
				FileUtil.getStyleAsUrl("popup"));

		VBox windowButtons = new VBox();
		Button closeBtn = new Button();
		closeBtn.getStyleClass().add("close-button");
		closeBtn.setPrefSize(16, 16);
		closeBtn.setMinSize(16, 16);
		closeBtn.setMaxSize(16, 16);
		closeBtn.setTranslateX(468);
		closeBtn.setTranslateY(20);
		windowButtons.getChildren().add(closeBtn);
		root.getChildren().addAll(windowButtons);

		final UserMaster user = new UserMaster();

		VBox textFields = new VBox(15);
		final LoginTextBox userName = new LoginTextBox("User Name",
				user.userNameProperty());
		userName.setMaxSize(176, 20);
		userName.setPrefSize(176, 20);
		userName.setMinSize(176, 20);
		final PasswordBox password = new PasswordBox("Password",
				user.passwordProperty());
		password.setMaxSize(176, 20);
		password.setPrefSize(176, 20);
		password.setMinSize(176, 20);
		textFields.setTranslateX(203);
		textFields.setTranslateY(171);
		textFields.getChildren().addAll(userName, password);

		HBox buttonFields = new HBox(10);
		final Button submitBtn = new Button();
		submitBtn.getStyleClass().add("submit-button");
		submitBtn.setMaxSize(94, 22);
		submitBtn.setMinSize(94, 22);
		submitBtn.setPrefSize(94, 22);
		submitBtn.setText("Login");
		final Button resetBtn = new Button();
		resetBtn.getStyleClass().add("submit-button");
		resetBtn.setMaxSize(94, 22);
		resetBtn.setMinSize(94, 22);
		resetBtn.setPrefSize(94, 22);
		resetBtn.setText("Reset");
		buttonFields.setTranslateX(188);
		buttonFields.setTranslateY(274);
		buttonFields.getChildren().addAll(submitBtn, resetBtn);

		root.getChildren().addAll(textFields, buttonFields);

		closeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				Platform.exit();
			}
		});
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (Validator.validateUserLogin(user)) {
					
					Context.getLoginSatusbar().show();
					ServiceManager.getUserValidationService(user).restart();
				}
			}
		});
		userName.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {

				if (keyEvent.getCode() == KeyCode.ENTER) {
					submitBtn.fire();
				}
			}
		});
		password.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {

				if (keyEvent.getCode() == KeyCode.ENTER) {
					submitBtn.fire();
				}
			}
		});
		resetBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Context.getLoggedUser().clean();
			}
		});

		root.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {

				startDragX = me.getSceneX();
				startDragY = me.getSceneY();
				root.setStyle("-fx-cursor:move;");
			}
		});
		root.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {

				root.setStyle("-fx-cursor:default;");
			}
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {

				primaryStage.setX(me.getScreenX() - startDragX);
				primaryStage.setY(me.getScreenY() - startDragY);
				root.setStyle("-fx-cursor:move;");
			}
		});

		Image[] icon = { FileUtil.getImageAsImage("mr_logo_16"),
				FileUtil.getImageAsImage("mr_logo_24"),
				FileUtil.getImageAsImage("mr_logo_32") };
		primaryStage.getIcons().addAll(icon);

		primaryStage.setScene(scene);
		primaryStage.show();

		Context.getLoginSatusbar().initComponents(primaryStage, "Loading...");
	}
}
