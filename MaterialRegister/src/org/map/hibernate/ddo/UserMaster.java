package org.map.hibernate.ddo;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;

public class UserMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleStringProperty userName;
	private SimpleStringProperty password;
	private SimpleStringProperty role;
	private SimpleStringProperty userStatus;

	public UserMaster() {

		this.userName = new SimpleStringProperty("");
		this.password = new SimpleStringProperty("");
		this.role = new SimpleStringProperty("");
		this.userStatus = new SimpleStringProperty("TRUE");
	}

	public UserMaster(String userName, String password, String role,
			String userStatus) {

		this.userName = new SimpleStringProperty(userName);
		this.password = new SimpleStringProperty(password);
		this.role = new SimpleStringProperty(role);
		this.userStatus = new SimpleStringProperty(userStatus);
	}

	public String getUserName() {

		return this.userName.get();
	}

	public void setUserName(String userName) {

		this.userName.set(userName);
	}

	public SimpleStringProperty userNameProperty() {

		return this.userName;
	}

	public String getPassword() {

		return this.password.get();
	}

	public void setPassword(String password) {

		this.password.set(password);
	}

	public SimpleStringProperty passwordProperty() {

		return this.password;
	}

	public String getRole() {

		return this.role.get();
	}

	public void setRole(String role) {

		this.role.set(role);
	}

	public SimpleStringProperty roleProperty() {

		return this.role;
	}

	public String getUserStatus() {

		return this.userStatus.get();
	}

	public void setUserStatus(String userStatus) {

		this.userStatus.set(userStatus);
	}

	public SimpleStringProperty UserStatusProperty() {

		return this.userStatus;
	}

	public void resetUserMaster() {

		setUserName("");
		setPassword("");
		setRole("");
		setUserStatus("TRUE");
	}

	public void resetUserMaster(UserMaster um) {

		setUserName(um.getUserName());
		setPassword(um.getPassword());
		setRole(um.getRole());
		setUserStatus(um.getUserStatus());
	}
}
