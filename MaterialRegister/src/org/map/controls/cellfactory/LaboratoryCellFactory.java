package org.map.controls.cellfactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import org.map.controls.combobox.LaboratoryComboBox;
import org.map.hibernate.ddo.LaboratoryMaster;
import org.map.hibernate.ddo.LaboratoryProperty;
import org.map.hibernate.ddo.MaterialTests;

public class LaboratoryCellFactory
		implements
		Callback<TableColumn<MaterialTests, LaboratoryMaster>, TableCell<MaterialTests, LaboratoryMaster>> {

	@Override
	public TableCell<MaterialTests, LaboratoryMaster> call(
			TableColumn<MaterialTests, LaboratoryMaster> param) {

		LaboratoryComboBoxCell textFieldCell = new LaboratoryComboBoxCell();

		return textFieldCell;
	}

	public static class LaboratoryComboBoxCell extends
			TableCell<MaterialTests, LaboratoryMaster> {

		private LaboratoryComboBox laboratoryComboBox;
		private LaboratoryProperty boundToCurrently = null;

		public LaboratoryComboBoxCell() {

			String strCss = "-fx-padding: 0;";

			setStyle(strCss);

			laboratoryComboBox = new LaboratoryComboBox("Laboratory");

			strCss = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";

			laboratoryComboBox.focusedProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							LaboratoryComboBox tf = (LaboratoryComboBox) getGraphic();
							String strStyleGotFocus = "-fx-background-color: purple, -fx-text-box-border, -fx-control-inner-background; -fx-background-insets: -0.4, 1, 2; -fx-background-radius: 3.4, 2, 2;";
							String strStyleLostFocus = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
							if (newValue.booleanValue()) {
								tf.setStyle(strStyleGotFocus);
							} else {
								tf.setStyle(strStyleLostFocus);
							}
						}
					});

			laboratoryComboBox.hoverProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							LaboratoryComboBox tf = (LaboratoryComboBox) getGraphic();
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

			laboratoryComboBox.setStyle(strCss);
			this.setGraphic(laboratoryComboBox);
		}

		@Override
		protected void updateItem(LaboratoryMaster laboratoryMaster,
				boolean empty) {

			super.updateItem(laboratoryMaster, empty);
			if (!empty) {
				this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				ObservableValue<LaboratoryMaster> ov = getTableColumn()
						.getCellObservableValue(getIndex());
				LaboratoryProperty sp = (LaboratoryProperty) ov;

				if (this.boundToCurrently == null) {
					this.boundToCurrently = sp;
					this.laboratoryComboBox.laboratoryProperty()
							.bindBidirectional(sp);
				} else {
					if (this.boundToCurrently != sp) {
						this.laboratoryComboBox.laboratoryProperty()
								.unbindBidirectional(this.boundToCurrently);
						this.boundToCurrently = sp;
						this.laboratoryComboBox.laboratoryProperty()
								.bindBidirectional(this.boundToCurrently);
					}
				}
			} else {
				this.setContentDisplay(ContentDisplay.TEXT_ONLY);
			}
		}
	}
}
