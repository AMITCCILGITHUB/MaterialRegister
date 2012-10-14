package org.map.service;

import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.text.Text;

import org.map.MaterialRegister;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.logger.LoggerUtil;
import org.map.login.Login;
import org.map.utils.Alert;

public class ServiceManager {

	public static UserValidation getUserValidationService() {

		final UserValidation uvs = new UserValidation();

		uvs.stateProperty().addListener(new ChangeListener<Worker.State>() {

			@Override
			public void changed(ObservableValue<? extends State> observable,
					State oldValue, Worker.State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Login.getLoginPanel().getStatusBar().hide();
					if (uvs.getValue()) {
						LoggerUtil.getLogger().info(
								"User "
										+ Login.getLoginPanel().getUserMaster()
												.getUserName()
										+ " validated successfully.");
						Login.getLoginPanel()
								.getUserMaster()
								.resetUserMaster(
										UserData.getUserDetails(Login
												.getLoginPanel()
												.getUserMaster().getUserName()));
						MaterialRegister mr = new MaterialRegister();
						mr.show();
						Login.getLoginPanel().getPrimaryStage().hide();
					} else {
						LoggerUtil.getLogger().info(
								"User "
										+ Login.getLoginPanel().getUserMaster()
												.getUserName()
										+ " attempted with wrong password ["
										+ Login.getLoginPanel().getUserMaster()
												.getPassword() + "]");

						Alert.showAlert(
								Login.getLoginPanel().getPrimaryStage(),
								"Invalid Login", "Error",
								"Invalid user name or password.\nPlease try again.");
					}
				} else if (newState == Worker.State.FAILED) {
					Login.getLoginPanel().getStatusBar().hide();

					Alert.showAlert(Login.getLoginPanel().getPrimaryStage(),
							"Error", "Error", "Some error cooured. Details : "
									+ uvs.getException().getCause());
				}
			}
		});

		return uvs;
	}

	public static PrintMaterialRegister getMaterialRegisterService() {

		final HostServices hostServices = Login.getLoginPanel()
				.getHostServices();

		final PrintMaterialRegister pmr = new PrintMaterialRegister();

		pmr.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				Text statusText = (Text) MaterialRegister.getMaterialRegister()
						.getBottom().getChildren().get(0);
				statusText.setText(newState.toString());

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Alert", "Alert",
							"The report has been saved as " + pmr.getValue());
					hostServices.showDocument(pmr.getValue());
				}
			}
		});

		return pmr;
	}

	public static PrintHeatChart getHeatChartService(HeatChartMaster heatChart) {

		final HostServices hostServices = Login.getLoginPanel()
				.getHostServices();

		final PrintHeatChart phc = new PrintHeatChart(heatChart);

		phc.stateProperty().addListener(new ChangeListener<Worker.State>() {

			@Override
			public void changed(
					ObservableValue<? extends Worker.State> observable,
					Worker.State oldValue, Worker.State newState) {

				Text statusText = (Text) MaterialRegister.getMaterialRegister()
						.getBottom().getChildren().get(0);
				statusText.setText(newState.toString());

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(MaterialRegister.getMaterialRegister()
							.getPrimaryStage(), "Alert", "Alert",
							"The report has been saved as " + phc.getValue());
					hostServices.showDocument(phc.getValue());
				}
			}
		});

		return phc;

	}
}
