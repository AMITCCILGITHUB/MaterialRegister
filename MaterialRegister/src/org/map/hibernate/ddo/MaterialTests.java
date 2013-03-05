package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MaterialTests implements Serializable, Comparable<MaterialTests> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty testCode;
	private SimpleStringProperty sampleId;
	private TestProperty test;
	private CustomerProperty customer;
	private SimpleStringProperty equipments;
	private LaboratoryProperty laboratory;
	private SimpleStringProperty reportDate;
	private SimpleStringProperty reportNumber;
	private ResultProperty result;
	private SimpleStringProperty remarks;
	private SimpleStringProperty witnessedBy;
	private SimpleStringProperty failureReason;
	private MaterialMaster materialMaster;
	private String status;
	private String createdBy;
	private Date createdDate;

	private StringBuilder validationMessage = new StringBuilder();

	public MaterialTests() {

		this.testCode = new SimpleIntegerProperty(0);
		this.sampleId = new SimpleStringProperty("");
		this.test = new TestProperty();
		this.customer = new CustomerProperty();
		this.equipments = new SimpleStringProperty("");
		this.laboratory = new LaboratoryProperty();
		this.reportNumber = new SimpleStringProperty("");
		this.reportDate = new SimpleStringProperty("");
		this.result = new ResultProperty();
		this.remarks = new SimpleStringProperty("");
		this.witnessedBy = new SimpleStringProperty("");
		this.failureReason = new SimpleStringProperty("");
		this.materialMaster = new MaterialMaster();

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public MaterialTests(int testCode, String sampleId, TestMaster test,
			CustomerMaster customer, String equipments,
			LaboratoryMaster laboratory, String reportNumber,
			String reportDate, ResultMaster result, String remarks,
			String witnessedBy, String failureReason,
			MaterialMaster materialMaster, String status, String createdBy,
			Date createdDate) {

		this.testCode = new SimpleIntegerProperty(testCode);
		this.sampleId = new SimpleStringProperty(sampleId);
		this.test = new TestProperty(test);
		this.customer = new CustomerProperty(customer);
		this.equipments = new SimpleStringProperty(equipments);
		this.laboratory = new LaboratoryProperty(laboratory);
		this.reportNumber = new SimpleStringProperty(reportNumber);
		this.reportDate = new SimpleStringProperty(reportDate);
		this.result = new ResultProperty(result);
		this.remarks = new SimpleStringProperty(remarks);
		this.witnessedBy = new SimpleStringProperty(witnessedBy);
		this.failureReason = new SimpleStringProperty(failureReason);

		this.materialMaster = new MaterialMaster(materialMaster);

		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public MaterialTests(MaterialTests material) {

		this.testCode = new SimpleIntegerProperty(material.getTestCode());
		this.sampleId = new SimpleStringProperty(material.getSampleId());
		this.test = new TestProperty(material.getTest());
		this.customer = new CustomerProperty(material.getCustomer());
		this.equipments = new SimpleStringProperty(material.getEquipments());
		this.laboratory = new LaboratoryProperty(material.getLaboratory());
		this.reportNumber = new SimpleStringProperty(material.getReportNumber());
		this.reportDate = new SimpleStringProperty(material.getReportDate());
		this.result = new ResultProperty(material.getResult());
		this.remarks = new SimpleStringProperty(material.getRemarks());
		this.witnessedBy = new SimpleStringProperty(material.getWitnessedBy());
		this.failureReason = new SimpleStringProperty(
				material.getFailureReason());

		this.materialMaster = new MaterialMaster(material.getMaterialMaster());

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
	}

	public int getTestCode() {

		return this.testCode.get();
	}

	public void setTestCode(int materialCode) {

		this.testCode.set(materialCode);
	}

	public SimpleIntegerProperty TestCodeProperty() {

		return this.testCode;
	}

	public String getSampleId() {

		return this.sampleId.get();
	}

	public void setSampleId(String sampleId) {

		this.sampleId.set(sampleId);
	}

	public SimpleStringProperty sampleIdProperty() {

		return this.sampleId;
	}

	public TestMaster getTest() {

		return this.test.get();
	}

	public void setTest(TestMaster test) {

		this.test.set(test);
	}

	public TestProperty testProperty() {

		return this.test;
	}

	public CustomerMaster getCustomer() {

		return this.customer.get();
	}

	public void setCustomer(CustomerMaster customer) {

		this.customer.set(customer);
	}

	public CustomerProperty customerProperty() {

		return this.customer;
	}

	public String getEquipments() {

		return this.equipments.get();
	}

	public void setEquipments(String equipments) {

		this.equipments.set(equipments);
	}

	public SimpleStringProperty equipmentsProperty() {

		return this.equipments;
	}

	public LaboratoryMaster getLaboratory() {

		return this.laboratory.get();
	}

	public void setLaboratory(LaboratoryMaster laboratory) {

		this.laboratory.set(laboratory);
	}

	public LaboratoryProperty laboratoryProperty() {

		return this.laboratory;
	}

	public String getReportNumber() {

		return this.reportNumber.get();
	}

	public void setReportNumber(String reportNumber) {

		this.reportNumber.set(reportNumber);
	}

	public SimpleStringProperty reportNumberProperty() {

		return this.reportNumber;
	}

	public String getReportDate() {

		return this.reportDate.get();
	}

	public void setReportDate(String reportDate) {

		this.reportDate.set(reportDate);
	}

	public SimpleStringProperty reportDateProperty() {

		return this.reportDate;
	}

	public ResultMaster getResult() {

		return this.result.get();
	}

	public void setResult(ResultMaster result) {

		this.result.set(result);
	}

	public ResultProperty resultProperty() {

		return this.result;
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

	public String getWitnessedBy() {

		return this.witnessedBy.get();
	}

	public void setWitnessedBy(String witnessedBy) {

		this.witnessedBy.set(witnessedBy);
	}

	public SimpleStringProperty witnessedByProperty() {

		return this.witnessedBy;
	}

	public String getFailureReason() {

		return this.failureReason.get();
	}

	public void setFailureReason(String failureReason) {

		this.failureReason.set(failureReason);
	}

	public SimpleStringProperty failureReasonProperty() {

		return this.failureReason;
	}

	public MaterialMaster getMaterialMaster() {

		return this.materialMaster;
	}

	public void setMaterialMaster(MaterialMaster materialMaster) {

		this.materialMaster.resetTo(materialMaster);
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

	public void resetTo(MaterialTests material) {

		this.testCode.set(material.getTestCode());
		this.sampleId.set(material.getSampleId());
		this.test.set(material.getTest());
		this.customer.set(material.getCustomer());
		this.equipments.set(material.getEquipments());
		this.laboratory.set(material.getLaboratory());
		this.reportNumber.set(material.getReportNumber());
		this.reportDate.set(material.getReportDate());
		this.result.set(material.getResult());
		this.remarks.set(material.getRemarks());
		this.witnessedBy.set(material.getWitnessedBy());
		this.failureReason.set(material.getFailureReason());

		this.materialMaster = material.getMaterialMaster();

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
	}

	public void clean() {

		this.testCode.set(0);
		this.sampleId.set("");
		this.test.get().clean();
		this.customer.get().clean();
		this.equipments.set("");
		this.laboratory.get().clean();
		this.reportNumber.set("");
		this.reportDate.set("");
		this.result.get().clean();
		this.remarks.set("");
		this.witnessedBy.set("");
		this.failureReason.set("");

		if (this.materialMaster.getMaterialCode() > 1000) {
			this.materialMaster.clean();
		}

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public boolean isInvalid() {

		validationMessage = new StringBuilder();

		if (this.sampleId.get().trim().length() <= 0) {

			validationMessage.append("+  Sample ID is empty." + "\n");
		}

		if (this.test.get().getTestName().trim().length() <= 0) {

			validationMessage.append("+  Test is empty." + "\n");
		}

		if (this.customer.get().getCustomerName().trim().length() <= 0) {

			validationMessage.append("+  Customer is empty." + "\n");
		}

		if (this.equipments.get().trim().length() <= 0) {

			validationMessage.append("+  Equipment is empty." + "\n");
		}

		if (this.laboratory.get().getLaboratoryName().trim().length() <= 0) {

			validationMessage.append("+  Laboratory is empty." + "\n");
		}

		if (this.reportDate.get().trim().length() <= 0) {

			validationMessage.append("+  Report Date is empty." + "\n");
		}

		if (this.reportNumber.get().trim().length() <= 0) {

			validationMessage.append("+  Report Number is empty." + "\n");
		}

		if (this.result.get().getResultName().trim().length() <= 0) {

			validationMessage.append("+  Result is empty." + "\n");
		}

		if (this.result.get().getResultName().equalsIgnoreCase("Rejected")
				&& this.failureReason.get().trim().length() <= 0) {

			validationMessage
					.append("+  Failure reason is required for rejected tests."
							+ "\n");
		}

		return (validationMessage.length() > 0);
	}

	public String getValidationMessage() {

		return validationMessage.toString();
	}

	@Override
	public int compareTo(MaterialTests o) {
		if (o instanceof MaterialTests && o != null) {
			return this.testCode.getValue().compareTo(o.getTestCode());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
