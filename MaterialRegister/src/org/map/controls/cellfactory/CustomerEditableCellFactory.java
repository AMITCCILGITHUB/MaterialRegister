package org.map.controls.cellfactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import org.map.controls.combobox.CustomerEditableComboBox;
import org.map.hibernate.ddo.CustomerMaster;
import org.map.hibernate.ddo.CustomerProperty;
import org.map.hibernate.ddo.MaterialTests;

public class CustomerEditableCellFactory
		implements
		Callback<TableColumn<MaterialTests, CustomerMaster>, TableCell<MaterialTests, CustomerMaster>> {

	@Override
	public TableCell<MaterialTests, CustomerMaster> call(
			TableColumn<MaterialTests, CustomerMaster> param) {

		CustomerEditableComboBoxCell textFieldCell = new CustomerEditableComboBoxCell();

		return textFieldCell;
	}

	public static class CustomerEditableComboBoxCell extends
			TableCell<MaterialTests, CustomerMaster> {

		private CustomerEditableComboBox customerComboBox;
		private CustomerProperty boundToCurrently = null;

		public CustomerEditableComboBoxCell() {

			String strCss = "-fx-padding: 0;";

			setStyle(strCss);

			customerComboBox = new CustomerEditableComboBox("Customer");

			strCss = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";

			customerComboBox.focusedProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							CustomerEditableComboBox tf = (CustomerEditableComboBox) getGraphic();
							String strStyleGotFocus = "-fx-background-color: purple, -fx-text-box-border, -fx-control-inner-background; -fx-background-insets: -0.4, 1, 2; -fx-background-radius: 3.4, 2, 2;";
							String strStyleLostFocus = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
							if (newValue.booleanValue()) {
								tf.setStyle(strStyleGotFocus);
							} else {
								tf.setStyle(strStyleLostFocus);
							}
						}
					});

			customerComboBox.hoverProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							CustomerEditableComboBox tf = (CustomerEditableComboBox) getGraphic();
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

			customerComboBox.setStyle(strCss);
			this.setGraphic(customerComboBox);
		}

		@Override
		protected void updateItem(CustomerMaster customerMaster, boolean empty) {

			super.updateItem(customerMaster, empty);
			if (!empty) {
				this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				ObservableValue<CustomerMaster> ov = getTableColumn()
						.getCellObservableValue(getIndex());
				CustomerProperty sp = (CustomerProperty) ov;

				if (this.boundToCurrently == null) {
					this.boundToCurrently = sp;
					this.customerComboBox.customerProperty().bindBidirectional(
							sp);
				} else {
					if (this.boundToCurrently != sp) {
						this.customerComboBox.customerProperty()
								.unbindBidirectional(this.boundToCurrently);
						this.boundToCurrently = sp;
						this.customerComboBox.customerProperty()
								.bindBidirectional(this.boundToCurrently);
					}
				}
			} else {
				this.setContentDisplay(ContentDisplay.TEXT_ONLY);
			}
		}
	}
}
