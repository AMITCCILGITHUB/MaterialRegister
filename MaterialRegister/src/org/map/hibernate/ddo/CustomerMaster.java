package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerMaster implements Serializable, Comparable<CustomerMaster> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty customerCode;
	private SimpleStringProperty customerName;
	private SimpleStringProperty remarks;
	private String status;
	private String createdBy;
	private Date createdDate;

	public CustomerMaster() {

		this.customerCode = new SimpleIntegerProperty(0);
		this.customerName = new SimpleStringProperty("");
		this.remarks = new SimpleStringProperty("");
		
		this.status = "TRUE";
		this.createdBy = "YSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public CustomerMaster(CustomerMaster vm) {

		this.customerCode = new SimpleIntegerProperty(vm.getCustomerCode());
		this.customerName = new SimpleStringProperty(vm.getCustomerName());
		this.remarks = new SimpleStringProperty(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public int getCustomerCode() {

		return this.customerCode.get();
	}

	public void setCustomerCode(int customerCode) {

		this.customerCode.set(customerCode);
	}

	public SimpleIntegerProperty customerCodeProperty() {

		return customerCode;
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

	public String getCustomerName() {

		return this.customerName.get();
	}

	public void setCustomerName(String customerName) {

		this.customerName.set(customerName);
	}

	public SimpleStringProperty customerNameProperty() {

		return this.customerName;
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

	public void resetTo(CustomerMaster vm) {

		this.customerCode.set(vm.getCustomerCode());
		this.customerName.set(vm.getCustomerName());
		this.remarks.set(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void clean() {

		this.customerCode.set(0);
		this.customerName.set("");
		this.remarks.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(CustomerMaster o) {

		if (o instanceof CustomerMaster && o != null) {
			return customerCode.getValue().compareTo(
					o.customerCodeProperty().getValue());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
