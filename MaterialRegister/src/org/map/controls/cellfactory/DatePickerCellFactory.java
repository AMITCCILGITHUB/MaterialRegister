package org.map.controls.cellfactory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import org.map.controls.calendar.DatePicker;
import org.map.hibernate.ddo.MaterialMaster;

public class DatePickerCellFactory
		implements
		Callback<TableColumn<MaterialMaster, String>, TableCell<MaterialMaster, String>> {

	@Override
	public TableCell<MaterialMaster, String> call(
			TableColumn<MaterialMaster, String> param) {

		DatePickerCell textFieldCell = new DatePickerCell();

		return textFieldCell;
	}

	public static class DatePickerCell extends
			TableCell<MaterialMaster, String> {

		private DatePicker datePickerBox;
		private StringProperty boundToCurrently = null;

		public DatePickerCell() {

			String strCss = "-fx-padding: 0;";

			setStyle(strCss);

			datePickerBox = new DatePicker();

			strCss = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";

			datePickerBox.focusedProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							DatePicker tf = (DatePicker) getGraphic();
							String strStyleGotFocus = "-fx-background-color: purple, -fx-text-box-border, -fx-control-inner-background; -fx-background-insets: -0.4, 1, 2; -fx-background-radius: 3.4, 2, 2;";
							String strStyleLostFocus = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
							if (newValue.booleanValue()) {
								tf.setStyle(strStyleGotFocus);
							} else {
								tf.setStyle(strStyleLostFocus);
							}
						}
					});

			datePickerBox.hoverProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							DatePicker tf = (DatePicker) getGraphic();
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

			datePickerBox.setStyle(strCss);
			this.setGraphic(datePickerBox);
		}

		@Override
		protected void updateItem(String item, boolean empty) {

			super.updateItem(item, empty);
			if (!empty) {
				this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				ObservableValue<String> ov = getTableColumn()
						.getCellObservableValue(getIndex());
				SimpleStringProperty sp = (SimpleStringProperty) ov;

				if (this.boundToCurrently == null) {
					this.boundToCurrently = sp;
					this.datePickerBox.textProperty().bindBidirectional(sp);
				} else {
					if (this.boundToCurrently != sp) {
						this.datePickerBox.textProperty().unbindBidirectional(
								this.boundToCurrently);
						this.boundToCurrently = sp;
						this.datePickerBox.textProperty().bindBidirectional(
								this.boundToCurrently);
					}
				}
			} else {
				this.setContentDisplay(ContentDisplay.TEXT_ONLY);
			}
		}
	}
}
