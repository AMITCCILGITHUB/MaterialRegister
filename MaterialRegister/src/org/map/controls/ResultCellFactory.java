package org.map.controls;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.ResultMaster;
import org.map.hibernate.ddo.ResultProperty;

public class ResultCellFactory
		implements
		Callback<TableColumn<MaterialMaster, ResultMaster>, TableCell<MaterialMaster, ResultMaster>> {

	@Override
	public TableCell<MaterialMaster, ResultMaster> call(
			TableColumn<MaterialMaster, ResultMaster> param) {

		ResultComboBoxCell textFieldCell = new ResultComboBoxCell();

		return textFieldCell;
	}

	public static class ResultComboBoxCell extends
			TableCell<MaterialMaster, ResultMaster> {

		private ResultComboBox resultComboBox;
		private ResultProperty boundToCurrently = null;

		public ResultComboBoxCell() {

			String strCss = "-fx-padding: 0;";

			setStyle(strCss);

			resultComboBox = new ResultComboBox("Result");

			strCss = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";

			resultComboBox.focusedProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							ResultComboBox tf = (ResultComboBox) getGraphic();
							String strStyleGotFocus = "-fx-background-color: purple, -fx-text-box-border, -fx-control-inner-background; -fx-background-insets: -0.4, 1, 2; -fx-background-radius: 3.4, 2, 2;";
							String strStyleLostFocus = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
							if (newValue.booleanValue()) {
								tf.setStyle(strStyleGotFocus);
							} else {
								tf.setStyle(strStyleLostFocus);
							}
						}
					});

			resultComboBox.hoverProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							ResultComboBox tf = (ResultComboBox) getGraphic();
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

			resultComboBox.setStyle(strCss);
			this.setGraphic(resultComboBox);
		}

		@Override
		protected void updateItem(ResultMaster resultMaster, boolean empty) {

			super.updateItem(resultMaster, empty);
			if (!empty) {
				this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				ObservableValue<ResultMaster> ov = getTableColumn()
						.getCellObservableValue(getIndex());
				ResultProperty sp = (ResultProperty) ov;

				if (this.boundToCurrently == null) {
					this.boundToCurrently = sp;
					this.resultComboBox.resultProperty().bindBidirectional(sp);
				} else {
					if (this.boundToCurrently != sp) {
						this.resultComboBox.resultProperty()
								.unbindBidirectional(this.boundToCurrently);
						this.boundToCurrently = sp;
						this.resultComboBox.resultProperty().bindBidirectional(
								this.boundToCurrently);
					}
				}
			} else {
				this.setContentDisplay(ContentDisplay.TEXT_ONLY);
			}
		}
	}
}
