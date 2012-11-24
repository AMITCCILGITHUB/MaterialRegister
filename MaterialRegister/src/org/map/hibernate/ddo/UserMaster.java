package org.map.hibernate.ddo;

import java.io.Serializable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty userCode;
	private SimpleStringProperty userName;
	private SimpleStringProperty password;
	private SimpleStringProperty confirmPassword;
	private RoleProperty role;
	private SimpleStringProperty userStatus;
	private StringBuilder validationMessage = new StringBuilder();

	public UserMaster() {

		this.userCode = new SimpleIntegerProperty(0);
		this.userName = new SimpleStringProperty("");
		this.password = new SimpleStringProperty("");
		this.confirmPassword = new SimpleStringProperty("");
		this.role = new RoleProperty();
		this.userStatus = new SimpleStringProperty("TRUE");
	}

	public UserMaster(UserMaster master) {

		this.userCode = new SimpleIntegerProperty(master.getUserCode());
		this.userName = new SimpleStringProperty(master.getUserName());
		this.password = new SimpleStringProperty(master.getPassword());
		this.confirmPassword = new SimpleStringProperty(master.getPassword());
		this.role = new RoleProperty(master.getRole());
		this.userStatus = new SimpleStringProperty(master.getUserStatus());
	}

	public int getUserCode() {

		return this.userCode.get();
	}

	public void setUserCode(int userCode) {

		this.userCode.set(userCode);
	}

	public SimpleIntegerProperty userCodeProperty() {

		return this.userCode;
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

	public String getConfirmPassword() {
		return confirmPassword.get();
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword.set(confirmPassword);
	}

	public SimpleStringProperty confirmPasswordProperty() {

		return this.confirmPassword;
	}

	public RoleMaster getRole() {

		return this.role.get();
	}

	public void setRole(RoleMaster role) {

		this.role.set(role);
	}

	public RoleProperty roleProperty() {

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

	public void resetTo(UserMaster um) {
		this.userName.set(um.getUserName());
		this.password.set(um.getPassword());
		this.confirmPassword.set(um.getConfirmPassword());
		this.role = new RoleProperty(um.getRole());
		this.userStatus.set(um.getUserStatus());
	}

	public void clean() {

		this.userCode.set(0);
		this.userName.set("");
		this.password.set("");
		this.confirmPassword.set("");
		this.role.get().clean();
		this.userStatus.set("TRUE");
	}

	public boolean isInvalid() {
		
		validationMessage = new StringBuilder();

		if (this.userName.get().trim().length() <= 0) {

			validationMessage.append("* User name is empty." + "\n");
		}

		if (this.password.get().trim().length() <= 0) {

			validationMessage.append("* Password is empty." + "\n");
		}

		if (this.confirmPassword.get().trim().length() <= 0) {

			validationMessage.append("* Confirm password is empty." + "\n");
		}

		if (this.password.get().trim().length() > 0
				&& this.confirmPassword.get().trim().length() > 0
				&& !this.password.get().trim()
						.equals(this.confirmPassword.get().trim())) {

			validationMessage
					.append("* Password and confirm password does not match."
							+ "\n");
		}

		if (this.role.get().getRoleName().trim().length() <= 0) {
			validationMessage.append("* Role is empty." + "\n");
		}

		return (validationMessage.length() > 0);
	}

	public String getValidationMessage() {

		return validationMessage.toString();
	}
}
