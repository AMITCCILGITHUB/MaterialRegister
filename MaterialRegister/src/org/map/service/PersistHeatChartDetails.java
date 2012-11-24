package org.map.service;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.map.hibernate.dao.HeatChartData;
import org.map.hibernate.ddo.HeatChartMaster;

public class PersistHeatChartDetails extends Service<Void> {

	HeatChartMaster heatChart;
	PersistType persistType;

	public PersistHeatChartDetails(HeatChartMaster heatChart, PersistType persistType) {
		this.heatChart = heatChart;
		this.persistType = persistType;
	}

	@Override
	protected Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				switch (persistType) {
				case INSERT:
					HeatChartData.insertHeatChart(heatChart);
					break;
				case UPDATE:
					HeatChartData.updateHeatChart(heatChart);
					break;
				case DELETE:
					HeatChartData.deleteHeatChart(heatChart);
					break;
				default:
					break;
				}

				return null;
			}
		};
	}

}