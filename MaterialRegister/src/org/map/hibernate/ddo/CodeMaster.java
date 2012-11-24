package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CodeMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty codeNumber;
	private SimpleStringProperty codeName;
	private SimpleStringProperty codeValue;
	private SimpleStringProperty codeDatatype;
	private SimpleStringProperty codeDesc;
	private String status;
	private String createdBy;
	private Date createdDate;

	public CodeMaster() {

		this.codeNumber = new SimpleIntegerProperty(0);
		this.codeName = new SimpleStringProperty("");
		this.codeValue = new SimpleStringProperty("");
		this.codeDatatype = new SimpleStringProperty("");
		this.codeDesc = new SimpleStringProperty("");

		this.status = "TRUE";
		this.createdBy = "YSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public CodeMaster(CodeMaster vm) {

		this.codeNumber = new SimpleIntegerProperty(vm.getCodeNumber());
		this.codeName = new SimpleStringProperty(vm.getCodeName());
		this.codeValue = new SimpleStringProperty(vm.getCodeValue());
		this.codeDatatype = new SimpleStringProperty(vm.getCodeDatatype());
		this.codeDesc = new SimpleStringProperty(vm.getCodeDesc());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public int getCodeNumber() {

		return this.codeNumber.get();
	}

	public void setCodeNumber(int codeNumber) {

		this.codeNumber.set(codeNumber);
	}

	public SimpleIntegerProperty codeNumberProperty() {

		return codeNumber;
	}

	public String getCodeName() {

		return this.codeName.get();
	}

	public void setCodeName(String codeName) {

		this.codeName.set(codeName);
	}

	public SimpleStringProperty codeNameProperty() {

		return this.codeName;
	}

	public String getCodeValue() {

		return this.codeValue.get();
	}

	public void setCodeValue(String codeValue) {

		this.codeValue.set(codeValue);
	}

	public SimpleStringProperty codeValueProperty() {

		return this.codeValue;
	}

	public String getCodeDatatype() {

		return this.codeDatatype.get();
	}

	public void setCodeDatatype(String codeDatatype) {

		this.codeDatatype.set(codeDatatype);
	}

	public SimpleStringProperty codeDatatypeProperty() {

		return this.codeDatatype;
	}

	public String getCodeDesc() {

		return this.codeDesc.get();
	}

	public void setCodeDesc(String codeDesc) {

		this.codeDesc.set(codeDesc);
	}

	public SimpleStringProperty codeDescProperty() {

		return this.codeDesc;
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

	public void resetTo(CodeMaster vm) {

		this.codeNumber.set(vm.getCodeNumber());
		this.codeName.set(vm.getCodeName());
		this.codeValue.set(vm.getCodeValue());
		this.codeDatatype.set(vm.getCodeDatatype());
		this.codeDesc.set(vm.getCodeDesc());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void reset() {

		this.codeNumber.set(0);
		this.codeName.set("");
		this.codeValue.set("");
		this.codeDatatype.set("");
		this.codeDesc.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}
}
