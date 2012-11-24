package org.map.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.map.hibernate.dao.UserData;
import org.map.hibernate.ddo.UserMaster;

public class UserValidation extends Service<Boolean> {

	private UserMaster user;
	
	public UserValidation(UserMaster user){
		this.user = user;
	}
	
	@Override
	protected Task<Boolean> createTask() {

		return new Task<Boolean>() {

			@Override
			protected Boolean call() {

				return Boolean.valueOf(UserData.validateUser(user));
			}
		};
	}
}