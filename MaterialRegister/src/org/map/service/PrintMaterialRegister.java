package org.map.service;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.sf.jasperreports.engine.JRException;

import org.map.hibernate.dao.Reporter;
import org.map.hibernate.ddo.MaterialRegister;
import org.map.logger.LoggerUtil;

public class PrintMaterialRegister extends Service<String> {

	ObservableList<MaterialRegister> data;
	
	public PrintMaterialRegister(List<MaterialRegister> data){
		this.data = FXCollections
				.observableArrayList(data);
	}
	
	@Override
	protected Task<String> createTask() {

		return new Task<String>() {

			@Override
			protected String call() {

				String reportPath = null;
				try {
					reportPath = Reporter
							.printMaterialRegisterReport(data);
				} catch (JRException | IOException ex) {
					LoggerUtil.getLogger().debug(ex);
				}
				return reportPath;
			}
		};
	}
}