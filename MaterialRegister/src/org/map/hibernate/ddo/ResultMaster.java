package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ResultMaster implements Serializable, Comparable<ResultMaster> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty resultCode;
	private SimpleStringProperty resultName;
	private SimpleStringProperty remarks;
	private String status;
	private String createdBy;
	private Date createdDate;

	public ResultMaster() {

		this.resultCode = new SimpleIntegerProperty(0);
		this.resultName = new SimpleStringProperty("");
		this.remarks = new SimpleStringProperty("");
		this.status = "TRUE";
		this.createdBy = "YSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public ResultMaster(int resultCode, String resultName, String remarks,
			String status, String createdBy, Date createdDate) {

		this.resultCode = new SimpleIntegerProperty(resultCode);
		this.resultName = new SimpleStringProperty(resultName);
		this.remarks = new SimpleStringProperty(remarks);
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public ResultMaster(ResultMaster vm) {

		setResultCode(vm.getResultCode());
		setResultName(vm.getResultName());
		setRemarks(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public int getResultCode() {

		return this.resultCode.get();
	}

	public void setResultCode(int resultCode) {

		this.resultCode.set(resultCode);
	}

	public SimpleIntegerProperty resultCodeProperty() {

		return resultCode;
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

	public String getResultName() {

		return this.resultName.get();
	}

	public void setResultName(String resultName) {

		this.resultName.set(resultName);
	}

	public SimpleStringProperty resultNameProperty() {

		return this.resultName;
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

	public void resetDetails(ResultMaster vm) {

		setResultCode(vm.getResultCode());
		setResultName(vm.getResultName());
		setRemarks(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void reset() {

		this.resultCode.set(0);
		this.resultName.set("");
		this.remarks.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(ResultMaster o) {

		if (o instanceof ResultMaster && o != null) {
			return resultCode.getValue().compareTo(
					o.resultCodeProperty().getValue());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
