package org.map.controls;

import org.map.utils.Layout;
import java.util.Iterator;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.ValidationMaster;

public class ComboVBox extends Region {

    private String type;
    private TextField textBox;
    private Button errorButton;
    private ContextMenu resultContextMenu = new ContextMenu();
    private Tooltip searchErrorTooltip = new Tooltip();
    private Timeline searchErrorTooltipHidder = null;

    public ComboVBox() {
        initComponent("", "");
        textBox.setText("");
    }

    public ComboVBox(String textValue, String promptText, String type) {
        initComponent(promptText, type);
        textBox.setText(textValue);
    }

    public ComboVBox(String textValue, String promptText, String type, StringProperty propertyValue, boolean bidirectional) {
        initComponent(promptText, type);
        textBox.setText(textValue);
        if (bidirectional) {
            textBox.textProperty().bindBidirectional(propertyValue);
        } else {
            textBox.textProperty().bind(propertyValue);
        }
        textBox.setText(textValue);
    }

    public void bind(StringProperty propertyValue) {
        textBox.textProperty().bind(propertyValue);
    }

    public void bindBidirectional(StringProperty propertyValue) {
        textBox.textProperty().bindBidirectional(propertyValue);
    }

    private void initComponent(String promptText, final String type) {
        setId("ResultBox");
        setType(type);

        setMinHeight(Layout.getRegionHeight());
        setPrefSize(Layout.getRegionWidth(), Layout.getRegionHeight());
        setMaxHeight(Layout.getRegionHeight());

        textBox = new TextField();
        textBox.setPrefWidth(Layout.getTextBoxWidth());
        textBox.setPromptText(promptText);

        errorButton = new Button();
        errorButton.getStyleClass().add("error-button");
        errorButton.setVisible(false);
        errorButton.setTooltip(new Tooltip("this field can\nnot be empty"));
        errorButton.setFocusTraversable(false);

        textBox.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue == true && newValue == false) {
                    if (textBox.getText().length() == 0) {
                        errorButton.setVisible(true);
                    } else {
                        errorButton.setVisible(false);
                    }
                }
            }
        });

        textBox.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                errorButton.setVisible(textBox.getText().length() == 0);
            }
        });

        getChildren().addAll(textBox, errorButton);
        resultContextMenu.setAutoFix(true);
        showResults();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setText(String textValue) {
        textBox.setText(textValue);
    }

    public String getText() {
        return textBox.getText();
    }

    @Override
    protected void layoutChildren() {
        textBox.resize(getWidth(), getHeight());
        errorButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
    }

    private void showError(TextField textBox, String message) {
        searchErrorTooltip.setText(message);
        if (searchErrorTooltipHidder != null) {
            searchErrorTooltipHidder.stop();
        }
        if (message != null) {
            Point2D toolTipPos = textBox.localToScene(0, textBox.getLayoutBounds().getHeight());
            double x = toolTipPos.getX() + textBox.getScene().getX() + textBox.getScene().getWindow().getX();
            double y = toolTipPos.getY() + textBox.getScene().getY() + textBox.getScene().getWindow().getY();
            searchErrorTooltip.show(textBox.getScene().getWindow(), x, y);
            searchErrorTooltipHidder = new Timeline();
            searchErrorTooltipHidder.getKeyFrames().add(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    searchErrorTooltip.hide();
                    searchErrorTooltip.setText(null);
                }
            }));
            searchErrorTooltipHidder.play();
        } else {
            searchErrorTooltip.hide();
        }
    }

    private void showResults() {

        textBox.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue == true && newValue == false) {
                    resultContextMenu.hide();
                }
            }
        });

        textBox.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.DOWN) {
                    resultContextMenu.setFocused(true);
                } else if (keyEvent.getCode() == KeyCode.ENTER) {
                    resultContextMenu.hide();
                }
            }
        });

        textBox.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (textBox.isFocused() == true) {
                    if (textBox.getText().length() == 0) {
                        if (resultContextMenu != null) {
                            resultContextMenu.hide();
                        }
                        showError(null, null);
                    } else {

                        List<ValidationMaster> resultList = ValidationData.searchValidationDetails(type, textBox.getText().trim());
                        if (resultList.size() > 0) {
                            showError(null, null);
                            populateMenu(resultList);
                            if (!resultContextMenu.isShowing()) {
                                resultContextMenu.show(textBox, Side.BOTTOM, 10, -5);
                            }
                        } else {
                            if (searchErrorTooltip.getText() != null) {
                                showError(textBox, "No matches");
                            }
                            resultContextMenu.hide();
                        }
                        resultContextMenu.setFocused(true);
                    }
                }
            }
        });
    }

    private void populateMenu(List<ValidationMaster> resultList) {
        resultContextMenu.getItems().clear();
        Iterator<ValidationMaster> results = resultList.iterator();
        while (results.hasNext()) {
            final ValidationMaster result = (ValidationMaster) results.next();
            final HBox hBox = new HBox();
            hBox.setFillHeight(true);
            Label itemLabel = new Label(result.getValidationName());
            itemLabel.getStyleClass().add("item-label");
            hBox.getChildren().addAll(itemLabel);

            final Region popRegion = new Region();
            popRegion.getStyleClass().add("result-menu-item-popup-region");
            popRegion.setPrefSize(10, 10);
            hBox.getChildren().add(popRegion);

            CustomMenuItem menuItem = new CustomMenuItem(hBox, true);
            menuItem.getStyleClass().add("result-menu-item");
            resultContextMenu.getItems().add(menuItem);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    textBox.setText(result.getValidationName());
                }
            });
        }
    }

    public void addFocusListener(ChangeListener<Boolean> focusChangeListener) {
        textBox.focusedProperty().addListener(focusChangeListener);
    }
}
