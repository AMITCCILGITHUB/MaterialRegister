package org.map;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import org.map.controls.SearchBox;
import org.map.controls.WindowButtons;
import org.map.logger.LoggerUtil;
import org.map.login.Login;
import org.map.utils.StatusBar;
import org.map.view.AddHeatChart;
import org.map.view.AddMaterial;
import org.map.view.AddUser;
import org.map.view.AddValidation;
import org.map.view.ChangePassword;
import org.map.view.EditHeatChart;
import org.map.view.EditMaterial;
import org.map.view.EditUser;
import org.map.view.EditValidation;
import org.map.view.ViewHeatChart;
import org.map.view.ViewMaterial;
import org.map.view.ViewReport;
import org.map.view.ViewUser;
import org.map.view.ViewValidation;

public class MaterialRegister {

	private static MaterialRegister fxInventoryManagement;
	private Stage primaryStage;
	private Scene scene;
	private BorderPane root;
	private ToolBar toolBar;
	private ToolBar pageToolBar;
	private Pane pageArea;
	private TreeView pageTree;
	private SplitPane splitPane;
	private double mouseDragOffsetX = 0;
	private double mouseDragOffsetY = 0;
	private StatusBar sb = new StatusBar(-29);
	private VBox bottom = new VBox();

	public static MaterialRegister getMaterialRegister() {

		return fxInventoryManagement;
	}

	public StatusBar getStatusBar() {

		return sb;
	}

	public Stage getPrimaryStage() {

		return primaryStage;
	}

	public void show() {

		if (primaryStage == null) {
			this.primaryStage = new Stage();
		}
		fxInventoryManagement = this;

		primaryStage.setTitle("Material Register");
		primaryStage.initStyle(StageStyle.UNDECORATED);

		root = new BorderPane();
		root.getStyleClass().add("applet");
		root.setId("root");

		StackPane layerPane = new StackPane();
		layerPane.setDepthTest(DepthTest.DISABLE);
		layerPane.getChildren().add(root);

		scene = new Scene(layerPane, 1020, 700, false);
		try {
			File mainStyle = new File("resources/style/style.css");
			scene.getStylesheets().addAll(
					mainStyle.toURI().toURL().toExternalForm());
			File calendarStyle = new File("resources/style/calendar.css");
			scene.getStylesheets().addAll(
					calendarStyle.toURI().toURL().toExternalForm());
			File controlStyle = new File("resources/style/controls.css");
			scene.getStylesheets().addAll(
					controlStyle.toURI().toURL().toExternalForm());
		} catch (Exception e) {
			LoggerUtil.getLogger().debug(e);
		}

		toolBar = new ToolBar();
		toolBar.setId("mainToolBar");
		ImageView logo = null;
		try {
			File logoFile = new File("resources/images/logo.png");
			logo = new ImageView(new Image(logoFile.toURI().toURL().toString()));
		} catch (Exception ex) {

		}

		HBox.setMargin(logo, new Insets(0, 0, 0, 5));
		toolBar.getItems().add(logo);
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		toolBar.getItems().add(spacer);

		Region spacer2 = new Region();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		toolBar.getItems().add(spacer2);

		final SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		VBox rightBar = new VBox(2);
		SearchBox searchBox = new SearchBox();
		final Text timeText = new Text(df.format(Calendar.getInstance()
				.getTime()));
		timeText.setFill(Color.WHITE);
		VBox.setMargin(timeText, new Insets(5, 0, 0, 12));
		rightBar.getChildren().addAll(searchBox, timeText);
		Timeline delayTimeline = new Timeline();
		delayTimeline.setCycleCount(Timeline.INDEFINITE);
		delayTimeline.getKeyFrames().add(
				new KeyFrame(Duration.ONE, new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						timeText.setText(df.format(Calendar.getInstance()
								.getTime()));
					}
				}));
		delayTimeline.play();

		HBox.setMargin(rightBar, new Insets(0, 5, 0, 0));
		toolBar.getItems().add(rightBar);

		toolBar.setPrefHeight(66);
		toolBar.setMinHeight(66);
		toolBar.setMaxHeight(66);
		GridPane.setConstraints(toolBar, 0, 0);
		// add close min max
		final WindowButtons windowButtons = new WindowButtons(primaryStage);
		toolBar.getItems().add(windowButtons);
		// add window header double clicking
		toolBar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {
					windowButtons.toogleMaximized();
				}
			}
		});
		// add window dragging
		toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				mouseDragOffsetX = event.getSceneX();
				mouseDragOffsetY = event.getSceneY();
			}
		});
		toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (!windowButtons.isMaximized()) {
					primaryStage.setX(event.getScreenX() - mouseDragOffsetX);
					primaryStage.setY(event.getScreenY() - mouseDragOffsetY);
				}
			}
		});

		ToolBar pageTreeToolBar = new ToolBar() {

			@Override
			public void requestLayout() {

				super.requestLayout();
				if (pageToolBar != null
						&& getHeight() != pageToolBar.prefHeight(-1)) {
					pageToolBar.setPrefHeight(getHeight());
				}
			}
		};

		final TreeItem tiRoot = new TreeItem();
		if (Login.getLoginPanel().getUserMaster().getRole()
				.equalsIgnoreCase("Admin")) {
			tiRoot.getChildren().add(new TreeItem<>("Add User"));
			tiRoot.getChildren().add(new TreeItem<>("View User"));
			tiRoot.getChildren().add(new TreeItem<>("Edit User"));

			tiRoot.getChildren().add(new TreeItem<>("Add Validation"));
			tiRoot.getChildren().add(new TreeItem<>("View Validation"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Validation"));

			tiRoot.getChildren().add(new TreeItem<>("Add Material"));
			tiRoot.getChildren().add(new TreeItem<>("View Material"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Material"));
			tiRoot.getChildren().add(new TreeItem<>("View Material Register"));

			tiRoot.getChildren().add(new TreeItem<>("Add Heat Chart"));
			tiRoot.getChildren().add(new TreeItem<>("View Heat Chart"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Heat Chart"));
		}
		if (Login.getLoginPanel().getUserMaster().getRole()
				.equalsIgnoreCase("Maker")) {
			tiRoot.getChildren().add(new TreeItem<>("Add Validation"));
			tiRoot.getChildren().addAll(new TreeItem<>("View Material"));

			tiRoot.getChildren().add(new TreeItem<>("Add Material"));
			tiRoot.getChildren().add(new TreeItem<>("View Material"));

			tiRoot.getChildren().add(new TreeItem<>("Add Heat Chart"));
			tiRoot.getChildren().add(new TreeItem<>("View Heat Chart"));
		}
		if (Login.getLoginPanel().getUserMaster().getRole()
				.equalsIgnoreCase("Normal")) {
			tiRoot.getChildren().add(new TreeItem<>("View Validation"));
			tiRoot.getChildren().add(new TreeItem<>("View Material"));
			tiRoot.getChildren().add(new TreeItem<>("View Material Register"));
			tiRoot.getChildren().add(new TreeItem<>("View Heat Chart"));
		}
		if (Login.getLoginPanel().getUserMaster().getRole()
				.equalsIgnoreCase("Checker")) {
			tiRoot.getChildren().add(new TreeItem<>("View Validation"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Validation"));

			tiRoot.getChildren().add(new TreeItem<>("View Material"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Material"));
			tiRoot.getChildren().add(new TreeItem<>("View Material Register"));

			tiRoot.getChildren().add(new TreeItem<>("View Heat Chart"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Heat Chart"));
		}
		tiRoot.getChildren().addAll(new TreeItem<>("Change Password"));

		pageTreeToolBar.setId("page-tree-toolbar");
		pageTreeToolBar.setMinHeight(29);
		pageTreeToolBar.setMaxWidth(Double.MAX_VALUE);
		pageTree = new TreeView<>(tiRoot);
		pageTree.setId("page-tree");
		pageTree.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		pageTree.setShowRoot(false);
		pageTree.setEditable(false);
		pageTree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		pageTree.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener() {

					@Override
					public void changed(ObservableValue observable,
							Object oldValue, Object newValue) {

						Object selectedPage = pageTree.getSelectionModel()
								.getSelectedItem();
						if (selectedPage != null) {
							goToPage(selectedPage.toString());
						}
					}
				});

		BorderPane leftSplitPane = new BorderPane();
		leftSplitPane.setTop(pageTreeToolBar);
		leftSplitPane.setCenter(pageTree);

		pageToolBar = new ToolBar();
		pageToolBar.setId("page-toolbar");
		pageToolBar.setMinHeight(29);
		pageToolBar.setMaxSize(Double.MAX_VALUE, Control.USE_PREF_SIZE);

		Button logoutButton = new Button();
		Region spacer3 = new Region();
		HBox.setHgrow(spacer3, Priority.ALWAYS);
		logoutButton.setId("LogoutButton");

		try {
			File logoutFile = new File("resources/images/logout.png");
			logoutButton.setGraphic(new ImageView(new Image(logoutFile.toURI()
					.toURL().toString())));
		} catch (Exception ex) {

		}
		logoutButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Login.getLoginPanel().getUserMaster().resetUserMaster();
				primaryStage.hide();
				Login.getLoginPanel().getPrimaryStage().show();
			}
		});
		logoutButton.setMaxHeight(Double.MAX_VALUE);
		pageToolBar.getItems().addAll(spacer3, logoutButton);

		pageArea = new Pane() {

			@Override
			protected void layoutChildren() {

				for (Node child : pageArea.getChildren()) {
					child.resizeRelocate(0, 0, pageArea.getWidth(),
							pageArea.getHeight());
				}
			}
		};
		pageArea.setId("page-area");
		// create right split pane
		BorderPane rightSplitPane = new BorderPane();
		rightSplitPane.setTop(pageToolBar);
		rightSplitPane.setCenter(pageArea);
		// create split pane
		splitPane = new SplitPane();
		splitPane.setId("page-splitpane");
		splitPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setConstraints(splitPane, 0, 1);
		splitPane.getItems().addAll(leftSplitPane, rightSplitPane);
		splitPane.setDividerPosition(0, 0.25);

		this.root.setTop(toolBar);
		this.root.setCenter(splitPane);

		bottom = VBoxBuilder
				.create()
				.minHeight(29)
				.prefHeight(29)
				.maxHeight(29)
				.styleClass("bottom-bar")
				.children(
						TextBuilder
								.create()
								.style("-fx-text-fill:white;-fx-fill: white; -fx-font-weight: bold;")
								.build()).build();
		this.root.setBottom(bottom);

		primaryStage.setScene(scene);
		primaryStage.show();

		sb.initComponents(primaryStage, "Loading...");
	}

	public VBox getBottom() {

		return bottom;
	}

	/**
	 * Take ensemble to the given page object, navigating there.
	 * 
	 * @param page
	 *            Page object to show
	 * @param addHistory
	 *            When true add current page to history before navigating
	 * @param force
	 *            When true reload page if page is current page
	 * @param swapViews
	 *            If view should be swapped to new page
	 */
	public void goToPage(String page) {
		Text statusText = (Text) bottom.getChildren().get(0);
		statusText.setText("");

		if (page.trim().equalsIgnoreCase("TreeItem [ value: Add User ]")) {
			AddUser addDetail = new AddUser();
			pageArea.getChildren().setAll(addDetail.createView());
		} else if (page.trim()
				.equalsIgnoreCase("TreeItem [ value: View User ]")) {
			ViewUser viewDetail = new ViewUser();
			pageArea.getChildren().setAll(viewDetail.createView());
		} else if (page.trim()
				.equalsIgnoreCase("TreeItem [ value: Edit User ]")) {
			EditUser editDetail = new EditUser();
			pageArea.getChildren().setAll(editDetail.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: Add Material ]")) {
			AddMaterial addDetail = new AddMaterial();
			pageArea.getChildren().setAll(addDetail.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: View Material ]")) {
			ViewMaterial viewDetail = new ViewMaterial();
			pageArea.getChildren().setAll(viewDetail.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: Edit Material ]")) {
			EditMaterial editDetail = new EditMaterial();
			pageArea.getChildren().setAll(editDetail.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: Add Validation ]")) {
			AddValidation addDetail = new AddValidation();
			pageArea.getChildren().setAll(addDetail.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: View Validation ]")) {
			ViewValidation viewDetail = new ViewValidation();
			pageArea.getChildren().setAll(viewDetail.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: Edit Validation ]")) {
			EditValidation editDetail = new EditValidation();
			pageArea.getChildren().setAll(editDetail.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: View Material Register ]")) {
			ViewReport viewReport = new ViewReport();
			pageArea.getChildren().setAll(viewReport.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: Add Heat Chart ]")) {
			AddHeatChart addDetail = new AddHeatChart();
			pageArea.getChildren().setAll(addDetail.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: View Heat Chart ]")) {
			ViewHeatChart viewDetail = new ViewHeatChart();
			pageArea.getChildren().setAll(viewDetail.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: Edit Heat Chart ]")) {
			EditHeatChart editDetail = new EditHeatChart();
			pageArea.getChildren().setAll(editDetail.createView());
		} else if (page.trim().equalsIgnoreCase(
				"TreeItem [ value: Change Password ]")) {
			ChangePassword changePassword = new ChangePassword();
			pageArea.getChildren().setAll(changePassword.createView());
		}
	}

	public void goToPage(String page, String ctNumber) {

		if (page.trim().equalsIgnoreCase("TreeItem [ value: View Material ]")) {
			Text statusText = (Text) bottom.getChildren().get(0);
			statusText.setText("");

			ViewMaterial viewDetail = new ViewMaterial();
			viewDetail.setCtNumber(ctNumber);
			pageArea.getChildren().setAll(viewDetail.createView());
		} else {
			goToPage(page);
		}
	}

	public void reloadPage(String pageName) {

		goToPage("TreeItem [ value: " + pageName + " ]");
	}
}
