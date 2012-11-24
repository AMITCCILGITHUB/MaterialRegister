package org.map.service;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;

public class PersistUserDetails extends Service<Void> {

	UserMaster user;
	PersistType persistType;

	public PersistUserDetails(UserMaster user, PersistType persistType) {
		this.user = user;
		this.persistType = persistType;
	}

	@Override
	protected Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				switch (persistType) {
				case INSERT:
					UserData.insertUser(user);
					break;
				case UPDATE:
					UserData.updateUser(user);
					break;
				case DELETE:
					UserData.deleteUser(user);
					break;
				default:
					break;
				}

				return null;
			}
		};
	}

}