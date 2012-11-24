package org.map.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.map.hibernate.dao.CodeData;
import org.map.hibernate.ddo.CodeMaster;

public class PersistCodeDetails extends Service<Void> {

	CodeMaster code;
	PersistType persistType;

	public PersistCodeDetails(CodeMaster code,
			PersistType persistType) {

		this.code = code;
		this.persistType = persistType;
	}

	@Override
	protected Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				switch (persistType) {
					case INSERT:
						CodeData.insertCode(code);
						break;
					case UPDATE:
						CodeData.updateCode(code);
						break;
					case DELETE:
						CodeData.deleteCode(code);
						break;
					default:
						break;
				}

				return null;
			}
		};
	}
}
