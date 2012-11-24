package org.map.service;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.map.hibernate.dao.MaterialData;
import org.map.hibernate.ddo.MaterialMaster;

public class PersistMaterialDetails extends Service<Void> {

	MaterialMaster material;
	PersistType persistType;

	public PersistMaterialDetails(MaterialMaster material, PersistType persistType) {
		this.material = material;
		this.persistType = persistType;
	}

	@Override
	protected Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				switch (persistType) {
				case INSERT:
					MaterialData.insertMaterial(material);
					break;
				case UPDATE:
					MaterialData.updateMaterial(material);
					break;
				case DELETE:
					MaterialData.deleteMaterial(material);
					break;
				default:
					break;
				}

				return null;
			}
		};
	}

}