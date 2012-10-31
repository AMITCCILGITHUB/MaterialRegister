package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RoleMaster implements Serializable, Comparable<RoleMaster> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty roleCode;
	private SimpleStringProperty roleName;
	private SimpleStringProperty remarks;
	private String status;
	private String createdBy;
	private Date createdDate;

	public RoleMaster() {

		this.roleCode = new SimpleIntegerProperty(0);
		this.roleName = new SimpleStringProperty("");
		this.remarks = new SimpleStringProperty("");
		this.status = "TRUE";
		this.createdBy = "YSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public RoleMaster(int roleCode, String roleName,
			String remarks, String status, String createdBy, Date createdDate) {

		this.roleCode = new SimpleIntegerProperty(roleCode);
		this.roleName = new SimpleStringProperty(roleName);
		this.remarks = new SimpleStringProperty(remarks);
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public RoleMaster(RoleMaster vm) {

		setRoleCode(vm.getRoleCode());
		setRoleName(vm.getRoleName());
		setRemarks(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public int getRoleCode() {

		return this.roleCode.get();
	}

	public void setRoleCode(int roleCode) {

		this.roleCode.set(roleCode);
	}

	public SimpleIntegerProperty roleCodeProperty() {
		return roleCode;
	}

	public String getRemarks() {

		return this.remarks.get();
	}

	public void setRemarks(String remarks) {

		this.remarks.set(remarks);
	}

	public SimpleStringProperty remarksProperty() {

		return this.remarks;
	}

	public String getRoleName() {

		return this.roleName.get();
	}

	public void setRoleName(String roleName) {

		this.roleName.set(roleName);
	}

	public SimpleStringProperty roleNameProperty() {

		return this.roleName;
	}
	
	public String getStatus() {

		return this.status;
	}

	public void setStatus(String status) {

		this.status = status;
	}	
	
	public String getCreatedBy() {

		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {

		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {

		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

	public void resetDetails(RoleMaster vm) {

		setRoleCode(vm.getRoleCode());
		setRoleName(vm.getRoleName());
		setRemarks(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void reset() {

		this.roleCode.set(0);
		this.roleName.set("");
		this.remarks.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(RoleMaster o) {

		if (o instanceof RoleMaster && o != null) {
			return roleCode.getValue().compareTo(o.roleCodeProperty().getValue());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
