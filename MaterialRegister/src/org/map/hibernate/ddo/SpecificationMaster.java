package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SpecificationMaster implements Serializable,
		Comparable<SpecificationMaster> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty specificationCode;
	private SimpleStringProperty specificationName;
	private SimpleStringProperty remarks;
	
	private String status;
	private String createdBy;
	private Date createdDate;

	public SpecificationMaster() {

		this.specificationCode = new SimpleIntegerProperty(0);
		this.specificationName = new SimpleStringProperty("");
		this.remarks = new SimpleStringProperty("");
		
		this.status = "TRUE";
		this.createdBy = "YSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public SpecificationMaster(SpecificationMaster vm) {

		this.specificationCode = new SimpleIntegerProperty(vm.getSpecificationCode());
		this.specificationName = new SimpleStringProperty(vm.getSpecificationName());
		this.remarks = new SimpleStringProperty(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public int getSpecificationCode() {

		return this.specificationCode.get();
	}

	public void setSpecificationCode(int specificationCode) {

		this.specificationCode.set(specificationCode);
	}

	public SimpleIntegerProperty specificationCodeProperty() {

		return specificationCode;
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

	public String getSpecificationName() {

		return this.specificationName.get();
	}

	public void setSpecificationName(String specificationName) {

		this.specificationName.set(specificationName);
	}

	public SimpleStringProperty specificationNameProperty() {

		return this.specificationName;
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

	public void resetTo(SpecificationMaster vm) {

		this.specificationCode.set(vm.getSpecificationCode());
		this.specificationName.set(vm.getSpecificationName());
		this.remarks.set(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void clean() {

		this.specificationCode.set(0);
		this.specificationName.set("");
		this.remarks.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(SpecificationMaster o) {

		if (o instanceof SpecificationMaster && o != null) {
			return specificationCode.getValue().compareTo(
					o.specificationCodeProperty().getValue());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
