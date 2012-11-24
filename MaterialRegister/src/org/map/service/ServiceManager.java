package org.map.service;

import java.net.MalformedURLException;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;

import org.map.Home;
import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.AgencyMaster;
import org.map.hibernate.ddo.CodeMaster;
import org.map.hibernate.ddo.CustomerMaster;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.ddo.ItemMaster;
import org.map.hibernate.ddo.LaboratoryMaster;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.MaterialRegister;
import org.map.hibernate.ddo.ResultMaster;
import org.map.hibernate.ddo.SpecificationMaster;
import org.map.hibernate.ddo.TestMaster;
import org.map.hibernate.ddo.UserMaster;
import org.map.utils.Alert;
import org.map.utils.Context;

public class ServiceManager {

	public static UserValidation getUserValidationService(final UserMaster user) {

		final UserValidation uvs = new UserValidation(user);

		uvs.stateProperty().addListener(new ChangeListener<Worker.State>() {

			@Override
			public void changed(ObservableValue<? extends State> observable,
					State oldValue, Worker.State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					if (uvs.getValue()) {
						Context.getLoginSatusbar().hide();
						Context.setLoggedUser(UserData.getUserDetails(user
								.getUserName()));
						
						Home mr = new Home();
						try {
							mr.show();
						} catch (MalformedURLException e) {
							Alert.showAlert(Context.getLoginStage(),
									"Invalid Login", "Error", e.getMessage());
						}

						Context.getLoginStage().hide();
					} else {
						Alert.showAlert(Context.getLoginStage(),
								"Invalid Login", "Error",
								"Invalid user name or password.\nPlease try again.");
					}
				} else if (newState == Worker.State.FAILED) {
					Context.getLoginSatusbar().hide();

					Alert.showAlert(Context.getLoginStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ uvs.getException().getCause());
				}
			}
		});

		return uvs;
	}

	public static PrintMaterialRegister getMaterialRegisterService(
			List<MaterialRegister> data) {

		final PrintMaterialRegister pmr = new PrintMaterialRegister(data);

		pmr.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"The report has been saved as " + pmr.getValue());
					Context.getHostServices().showDocument(pmr.getValue());
				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ pmr.getException().getCause());
				}
			}
		});

		return pmr;
	}

	public static PrintHeatChart getHeatChartService(HeatChartMaster heatChart) {

		final PrintHeatChart phc = new PrintHeatChart(heatChart);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", phc.stateProperty()));

		phc.stateProperty().addListener(new ChangeListener<Worker.State>() {

			@Override
			public void changed(
					ObservableValue<? extends Worker.State> observable,
					Worker.State oldValue, Worker.State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"The report has been saved as " + phc.getValue());
					Context.getHostServices().showDocument(phc.getValue());
				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ phc.getException().getCause());
				}
			}
		});

		return phc;
	}

	public static PersistUserDetails getUserDetailsService(UserMaster user,
			PersistType persistType) {

		final PersistUserDetails sud = new PersistUserDetails(user, persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", sud.stateProperty()));

		sud.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"User details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ sud.getException().getCause());
				}
			}
		});

		return sud;
	}

	public static PersistMaterialDetails getMaterialDetailsService(
			MaterialMaster material, PersistType persistType) {

		final PersistMaterialDetails sud = new PersistMaterialDetails(material,
				persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", sud.stateProperty()));

		sud.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"Material details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ sud.getException().getCause());
				}
			}
		});

		return sud;
	}

	public static PersistCodeDetails getCodeDetailsService(CodeMaster code,
			PersistType persistType) {

		final PersistCodeDetails pcd = new PersistCodeDetails(code, persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", pcd.stateProperty()));

		pcd.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"Code details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ pcd.getException().getCause());
				}
			}
		});

		return pcd;
	}

	public static PersistValidationDetails getValidationDetailsService(
			String validationType, AgencyMaster agency, PersistType persistType) {

		final PersistValidationDetails pvd = new PersistValidationDetails(
				validationType, agency, persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", pvd.stateProperty()));

		pvd.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"Agency details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ pvd.getException().getCause());
				}
			}
		});

		return pvd;
	}

	public static PersistValidationDetails getValidationDetailsService(
			String validationType, CustomerMaster customer,
			PersistType persistType) {

		final PersistValidationDetails pvd = new PersistValidationDetails(
				validationType, customer, persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", pvd.stateProperty()));

		pvd.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"Customer details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ pvd.getException().getCause());
				}
			}
		});

		return pvd;
	}

	public static PersistValidationDetails getValidationDetailsService(
			String validationType, ItemMaster item, PersistType persistType) {

		final PersistValidationDetails pvd = new PersistValidationDetails(
				validationType, item, persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", pvd.stateProperty()));

		pvd.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"Item details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ pvd.getException().getCause());
				}
			}
		});

		return pvd;
	}

	public static PersistValidationDetails getValidationDetailsService(
			String validationType, LaboratoryMaster laboratory,
			PersistType persistType) {

		final PersistValidationDetails pvd = new PersistValidationDetails(
				validationType, laboratory, persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", pvd.stateProperty()));

		pvd.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"Laboratory details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ pvd.getException().getCause());
				}
			}
		});

		return pvd;
	}

	public static PersistValidationDetails getValidationDetailsService(
			String validationType, ResultMaster result, PersistType persistType) {

		final PersistValidationDetails pvd = new PersistValidationDetails(
				validationType, result, persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", pvd.stateProperty()));

		pvd.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"Result details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ pvd.getException().getCause());
				}
			}
		});

		return pvd;
	}

	public static PersistValidationDetails getValidationDetailsService(
			String validationType, SpecificationMaster specification,
			PersistType persistType) {

		final PersistValidationDetails pvd = new PersistValidationDetails(
				validationType, specification, persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", pvd.stateProperty()));

		pvd.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"Specification details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ pvd.getException().getCause());
				}
			}
		});

		return pvd;
	}

	public static PersistValidationDetails getValidationDetailsService(
			String validationType, TestMaster test, PersistType persistType) {

		final PersistValidationDetails pvd = new PersistValidationDetails(
				validationType, test, persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", pvd.stateProperty()));

		pvd.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"Test details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ pvd.getException().getCause());
				}
			}
		});

		return pvd;
	}

	public static ChangePageView getChangePageViewService(String pageName) {

		final ChangePageView cpv = new ChangePageView(pageName);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", cpv.stateProperty()));

		return cpv;
	}

	public static PersistHeatChartDetails getHeatChartDetailsService(
			HeatChartMaster heatChart, PersistType persistType) {

		final PersistHeatChartDetails phc = new PersistHeatChartDetails(
				heatChart, persistType);

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", phc.stateProperty()));

		phc.stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> arg0,
					State oldValue, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
							"Heat Chart details saved successfully.");

				} else if (newState == Worker.State.FAILED) {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Some error cooured. Details : "
									+ phc.getException().getCause());
				}
			}
		});

		return phc;
	}
}
