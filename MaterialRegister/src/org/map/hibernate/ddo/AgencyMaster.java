package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AgencyMaster implements Serializable, Comparable<AgencyMaster> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty agencyCode;
	private SimpleStringProperty agencyName;
	private SimpleStringProperty remarks;
	private String status;
	private String createdBy;
	private Date createdDate;

	public AgencyMaster() {

		this.agencyCode = new SimpleIntegerProperty(0);
		this.agencyName = new SimpleStringProperty("");
		this.remarks = new SimpleStringProperty("");
		
		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public AgencyMaster(AgencyMaster vm) {

		this.agencyCode = new SimpleIntegerProperty(vm.getAgencyCode());
		this.agencyName = new SimpleStringProperty(vm.getAgencyName());
		this.remarks = new SimpleStringProperty(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public int getAgencyCode() {

		return this.agencyCode.get();
	}

	public void setAgencyCode(int agencyCode) {

		this.agencyCode.set(agencyCode);
	}

	public SimpleIntegerProperty agencyCodeProperty() {

		return agencyCode;
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

	public String getAgencyName() {

		return this.agencyName.get();
	}

	public void setAgencyName(String agencyName) {

		this.agencyName.set(agencyName);
	}

	public SimpleStringProperty agencyNameProperty() {

		return this.agencyName;
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

	public void resetTo(AgencyMaster vm) {

		this.agencyCode.set(vm.getAgencyCode());
		this.agencyName.set(vm.getAgencyName());
		this.remarks.set(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void clean() {

		this.agencyCode.set(0);
		this.agencyName.set("");
		this.remarks.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(AgencyMaster o) {

		if (o instanceof AgencyMaster && o != null) {
			return agencyCode.getValue().compareTo(
					o.agencyCodeProperty().getValue());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
