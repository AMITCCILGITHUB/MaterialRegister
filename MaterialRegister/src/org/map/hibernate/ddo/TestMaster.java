package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TestMaster implements Serializable, Comparable<TestMaster> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty testCode;
	private SimpleStringProperty testName;
	private SimpleStringProperty remarks;
	private String status;
	private String createdBy;
	private Date createdDate;

	public TestMaster() {

		this.testCode = new SimpleIntegerProperty(0);
		this.testName = new SimpleStringProperty("");
		this.remarks = new SimpleStringProperty("");
		
		this.status = "TRUE";
		this.createdBy = "YSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public TestMaster(TestMaster vm) {

		this.testCode = new SimpleIntegerProperty(vm.getTestCode());
		this.testName = new SimpleStringProperty(vm.getTestName());
		this.remarks = new SimpleStringProperty(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public int getTestCode() {

		return this.testCode.get();
	}

	public void setTestCode(int testCode) {

		this.testCode.set(testCode);
	}

	public SimpleIntegerProperty testCodeProperty() {

		return testCode;
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

	public String getTestName() {

		return this.testName.get();
	}

	public void setTestName(String testName) {

		this.testName.set(testName);
	}

	public SimpleStringProperty testNameProperty() {

		return this.testName;
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

	public void resetTo(TestMaster vm) {

		this.testCode.set(vm.getTestCode());
		this.testName.set(vm.getTestName());
		this.remarks.set(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void clean() {

		this.testCode.set(0);
		this.testName.set("");
		this.remarks.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(TestMaster o) {

		if (o instanceof TestMaster && o != null) {
			return testCode.getValue().compareTo(
					o.testCodeProperty().getValue());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
