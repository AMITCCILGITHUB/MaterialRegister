package org.map.controls.cellfactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import org.map.controls.combobox.TestEditableComboBox;
import org.map.hibernate.ddo.MaterialTests;
import org.map.hibernate.ddo.TestMaster;
import org.map.hibernate.ddo.TestProperty;

public class TestEditableCellFactory
		implements
		Callback<TableColumn<MaterialTests, TestMaster>, TableCell<MaterialTests, TestMaster>> {

	@Override
	public TableCell<MaterialTests, TestMaster> call(
			TableColumn<MaterialTests, TestMaster> param) {

		TestEditableComboBoxCell textFieldCell = new TestEditableComboBoxCell();

		return textFieldCell;
	}

	public static class TestEditableComboBoxCell extends
			TableCell<MaterialTests, TestMaster> {

		private TestEditableComboBox testComboBox;
		private TestProperty boundToCurrently = null;

		public TestEditableComboBoxCell() {

			String strCss = "-fx-padding: 0;";

			setStyle(strCss);

			testComboBox = new TestEditableComboBox("Test");

			strCss = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";

			testComboBox.focusedProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							TestEditableComboBox tf = (TestEditableComboBox) getGraphic();
							String strStyleGotFocus = "-fx-background-color: purple, -fx-text-box-border, -fx-control-inner-background; -fx-background-insets: -0.4, 1, 2; -fx-background-radius: 3.4, 2, 2;";
							String strStyleLostFocus = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
							if (newValue.booleanValue()) {
								tf.setStyle(strStyleGotFocus);
							} else {
								tf.setStyle(strStyleLostFocus);
							}
						}
					});

			testComboBox.hoverProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							TestEditableComboBox tf = (TestEditableComboBox) getGraphic();
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

			testComboBox.setStyle(strCss);
			this.setGraphic(testComboBox);
		}

		@Override
		protected void updateItem(TestMaster testMaster, boolean empty) {

			super.updateItem(testMaster, empty);
			if (!empty) {
				this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				ObservableValue<TestMaster> ov = getTableColumn()
						.getCellObservableValue(getIndex());
				TestProperty sp = (TestProperty) ov;

				if (this.boundToCurrently == null) {
					this.boundToCurrently = sp;
					this.testComboBox.testProperty().bindBidirectional(sp);
				} else {
					if (this.boundToCurrently != sp) {
						this.testComboBox.testProperty().unbindBidirectional(
								this.boundToCurrently);
						this.boundToCurrently = sp;
						this.testComboBox.testProperty().bindBidirectional(
								this.boundToCurrently);
					}
				}
			} else {
				this.setContentDisplay(ContentDisplay.TEXT_ONLY);
			}
		}
	}
}
