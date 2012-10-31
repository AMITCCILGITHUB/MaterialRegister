package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LaboratoryMaster implements Serializable,
		Comparable<LaboratoryMaster> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty laboratoryCode;
	private SimpleStringProperty laboratoryName;
	private SimpleStringProperty remarks;
	private String status;
	private String createdBy;
	private Date createdDate;

	public LaboratoryMaster() {

		this.laboratoryCode = new SimpleIntegerProperty(0);
		this.laboratoryName = new SimpleStringProperty("");
		this.remarks = new SimpleStringProperty("");
		this.status = "TRUE";
		this.createdBy = "YSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public LaboratoryMaster(int laboratoryCode, String laboratoryName,
			String remarks, String status, String createdBy, Date createdDate) {

		this.laboratoryCode = new SimpleIntegerProperty(laboratoryCode);
		this.laboratoryName = new SimpleStringProperty(laboratoryName);
		this.remarks = new SimpleStringProperty(remarks);
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public LaboratoryMaster(LaboratoryMaster vm) {

		setLaboratoryCode(vm.getLaboratoryCode());
		setLaboratoryName(vm.getLaboratoryName());
		setRemarks(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public int getLaboratoryCode() {

		return this.laboratoryCode.get();
	}

	public void setLaboratoryCode(int laboratoryCode) {

		this.laboratoryCode.set(laboratoryCode);
	}

	public SimpleIntegerProperty laboratoryCodeProperty() {

		return laboratoryCode;
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

	public String getLaboratoryName() {

		return this.laboratoryName.get();
	}

	public void setLaboratoryName(String laboratoryName) {

		this.laboratoryName.set(laboratoryName);
	}

	public SimpleStringProperty laboratoryNameProperty() {

		return this.laboratoryName;
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

	public void resetDetails(LaboratoryMaster vm) {

		setLaboratoryCode(vm.getLaboratoryCode());
		setLaboratoryName(vm.getLaboratoryName());
		setRemarks(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void reset() {

		this.laboratoryCode.set(0);
		this.laboratoryName.set("");
		this.remarks.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(LaboratoryMaster o) {

		if (o instanceof LaboratoryMaster && o != null) {
			return laboratoryCode.getValue().compareTo(
					o.laboratoryCodeProperty().getValue());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
