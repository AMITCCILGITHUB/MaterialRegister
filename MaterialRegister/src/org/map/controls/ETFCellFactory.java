package org.map.controls;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import org.map.hibernate.dao.MaterialData;
import org.map.hibernate.ddo.HeatChartSheets;
import org.map.view.AddHeatChart;
import org.map.view.EditHeatChart;

public class ETFCellFactory implements Callback<TableColumn<HeatChartSheets, String>, TableCell<HeatChartSheets, String>> {

    private String type;

    public ETFCellFactory(String type) {
        this.type = type;
    }

    @Override
    public TableCell<HeatChartSheets, String> call(TableColumn<HeatChartSheets, String> param) {
        TextFieldCell textFieldCell = new TextFieldCell(type);
        return textFieldCell;
    }

    public static class TextFieldCell extends TableCell<HeatChartSheets, String> {

        private String type;
        private TextField textField;
        private StringProperty boundToCurrently = null;

        public TextFieldCell(String type) {
            String strCss;
            this.type = type;

            strCss = "-fx-padding: 0;";

            setStyle(strCss);

            textField = new TextField();

            strCss = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    TextField tf = (TextField) getGraphic();
                    String strStyleGotFocus = "-fx-background-color: purple, -fx-text-box-border, -fx-control-inner-background; -fx-background-insets: -0.4, 1, 2; -fx-background-radius: 3.4, 2, 2;";
                    String strStyleLostFocus = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
                    if (newValue.booleanValue()) {
                        tf.setStyle(strStyleGotFocus);
                    } else {
                        tf.setStyle(strStyleLostFocus);
                    }
                }
            });
            textField.hoverProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    TextField tf = (TextField) getGraphic();
                    String strStyleGotHover = "-fx-background-color: derive(purple,90%), -fx-text-box-border, derive(-fx-control-inner-background, 10%); -fx-background-insets: 1, 2.8, 3.8; -fx-background-radius: 3.4, 2, 2;";
                    String strStyleLostHover = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
                    String strStyleHasFocus = "-fx-background-color: purple, -fx-text-box-border, -fx-control-inner-background; -fx-background-insets: -0.4, 1, 2; -fx-background-radius: 3.4, 2, 2;";
                    if (newValue.booleanValue()) {
                        tf.setStyle(strStyleGotHover);
                    } else {
                        if (!tf.focusedProperty().get()) {
                            tf.setStyle(strStyleLostHover);
                        } else {
                            tf.setStyle(strStyleHasFocus);
                        }
                    }

                }
            });
            textField.setStyle(strCss);
            this.setGraphic(textField);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                ObservableValue<String> ov = getTableColumn().getCellObservableValue(getIndex());
                SimpleStringProperty sp = (SimpleStringProperty) ov;

                if (this.boundToCurrently == null) {
                    this.boundToCurrently = sp;
                    this.textField.textProperty().bindBidirectional(sp);
                } else {
                    if (this.boundToCurrently != sp) {
                        this.textField.textProperty().unbindBidirectional(this.boundToCurrently);
                        this.boundToCurrently = sp;
                        this.textField.textProperty().bindBidirectional(this.boundToCurrently);
                    }
                }

                if (type.equalsIgnoreCase("CTNumberAdd") && item != null && item.length() > 5) {
                    AddHeatChart.getViewHeatChart().updateTable(MaterialData.searchMaterialDetailsByCtNumber(item), getIndex());
                }
                if (type.equalsIgnoreCase("CTNumberEdit") && item != null && item.length() > 5) {
                    EditHeatChart.getViewHeatChart().updateTable(MaterialData.searchMaterialDetailsByCtNumber(item), getIndex());
                }
            } else {
                this.setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }
}
