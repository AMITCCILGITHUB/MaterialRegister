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

	public RoleMaster(RoleMaster vm) {

		this.roleCode = new SimpleIntegerProperty(vm.getRoleCode());
		this.roleName = new SimpleStringProperty(vm.getRoleName());
		this.remarks = new SimpleStringProperty(vm.getRemarks());

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

	public void resetTo(RoleMaster vm) {

		this.roleCode.set(vm.getRoleCode());
		this.roleName.set(vm.getRoleName());
		this.remarks.set(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void clean() {

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
