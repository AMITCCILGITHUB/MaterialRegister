package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MaterialMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	private SimpleStringProperty ctNumber;
	private SimpleStringProperty inspectionAgency;
	private SimpleStringProperty item;
	private SimpleStringProperty size;
	private SimpleIntegerProperty testQuantity;
	private SimpleIntegerProperty offeredQuantity;
	private SimpleStringProperty heatNumber;
	private SimpleStringProperty plateNumber;
	private SimpleStringProperty specification;
	private SimpleStringProperty customer;
	private SimpleStringProperty equipments;
	private SimpleStringProperty laboratory;
	private SimpleStringProperty reportNumber;
	private SimpleStringProperty reportDate;
	private SimpleStringProperty result;
	private SimpleStringProperty remarks;
	private SimpleStringProperty witnessedBy;
	private SimpleStringProperty failureReason;
	private String status;
	private String createdBy;
	private Date createdDate;
	private Set<MaterialTestMap> tests = new TreeSet<>();

	@Override
	public String toString() {

		return "[1]" + size.get() + "[2]" + specification.get() + "[3]"
				+ ctNumber.get() + "[4]" + reportNumber.get() + "[5]"
				+ reportDate.get() + "[6]" + laboratory.get();
	}

	public MaterialMaster() {

		this.ctNumber = new SimpleStringProperty("");
		this.inspectionAgency = new SimpleStringProperty("");
		this.item = new SimpleStringProperty("");
		this.size = new SimpleStringProperty("");
		this.testQuantity = new SimpleIntegerProperty(1);
		this.offeredQuantity = new SimpleIntegerProperty(1);
		this.heatNumber = new SimpleStringProperty("");
		this.plateNumber = new SimpleStringProperty("");
		this.specification = new SimpleStringProperty("");
		this.customer = new SimpleStringProperty("");
		this.equipments = new SimpleStringProperty("");
		this.laboratory = new SimpleStringProperty("");
		this.reportNumber = new SimpleStringProperty("");
		this.reportDate = new SimpleStringProperty("");
		this.result = new SimpleStringProperty("");
		this.remarks = new SimpleStringProperty("");
		this.witnessedBy = new SimpleStringProperty("");
		this.failureReason = new SimpleStringProperty("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public MaterialMaster(String ctNumber, String inspectionAgency,
			String item, String size, int testQuantity, int offeredQuantity,
			String heatNumber, String plateNumber, String specification,
			String customer, String equipments, String laboratory,
			String reportNumber, String reportDate, String result,
			String remarks, String witnessedBy, String failureReason,
			String status, String createdBy, Date createdDate,
			Set<MaterialTestMap> tests) {

		this.ctNumber = new SimpleStringProperty(ctNumber);
		this.inspectionAgency = new SimpleStringProperty(inspectionAgency);
		this.item = new SimpleStringProperty(item);
		this.size = new SimpleStringProperty(size);
		this.testQuantity = new SimpleIntegerProperty(testQuantity);
		this.offeredQuantity = new SimpleIntegerProperty(offeredQuantity);
		this.heatNumber = new SimpleStringProperty(heatNumber);
		this.plateNumber = new SimpleStringProperty(plateNumber);
		this.specification = new SimpleStringProperty(specification);
		this.customer = new SimpleStringProperty(customer);
		this.equipments = new SimpleStringProperty(equipments);
		this.laboratory = new SimpleStringProperty(laboratory);
		this.reportNumber = new SimpleStringProperty(reportNumber);
		this.reportDate = new SimpleStringProperty(reportDate);
		this.result = new SimpleStringProperty(result);
		this.remarks = new SimpleStringProperty(remarks);
		this.witnessedBy = new SimpleStringProperty(witnessedBy);
		this.failureReason = new SimpleStringProperty(failureReason);

		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.tests = new TreeSet<>(tests);
	}

	public MaterialMaster(MaterialMaster material) {

		this.ctNumber = new SimpleStringProperty(material.getCtNumber());
		this.inspectionAgency = new SimpleStringProperty(
				material.getInspectionAgency());
		this.item = new SimpleStringProperty(material.getItem());
		this.size = new SimpleStringProperty(material.getSize());
		this.testQuantity = new SimpleIntegerProperty(
				material.getTestQuantity());
		this.offeredQuantity = new SimpleIntegerProperty(
				material.getOfferedQuantity());
		this.heatNumber = new SimpleStringProperty(material.getHeatNumber());
		this.plateNumber = new SimpleStringProperty(material.getPlateNumber());
		this.specification = new SimpleStringProperty(
				material.getSpecification());
		this.customer = new SimpleStringProperty(material.getCustomer());
		this.equipments = new SimpleStringProperty(material.getEquipments());
		this.laboratory = new SimpleStringProperty(material.getLaboratory());
		this.reportNumber = new SimpleStringProperty(material.getReportNumber());
		this.reportDate = new SimpleStringProperty(material.getReportDate());
		this.result = new SimpleStringProperty(material.getResult());
		this.remarks = new SimpleStringProperty(material.getRemarks());
		this.witnessedBy = new SimpleStringProperty(material.getWitnessedBy());
		this.failureReason = new SimpleStringProperty(
				material.getFailureReason());

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
		this.tests = new TreeSet<MaterialTestMap>(material.getTests());
	}

	public String getCtNumber() {

		return this.ctNumber.get();
	}

	public void setCtNumber(String ctNumber) {

		this.ctNumber.set(ctNumber);
	}

	public SimpleStringProperty ctNumberProperty() {

		return this.ctNumber;
	}

	public String getInspectionAgency() {

		return this.inspectionAgency.get();
	}

	public void setInspectionAgency(String inspectionAgency) {

		this.inspectionAgency.set(inspectionAgency);
	}

	public SimpleStringProperty inspectionAgencyProperty() {

		return this.inspectionAgency;
	}

	public String getItem() {

		return this.item.get();
	}

	public void setItem(String item) {

		this.item.set(item);
	}

	public SimpleStringProperty itemProperty() {

		return this.item;
	}

	public String getSize() {

		return this.size.get();
	}

	public void setSize(String size) {

		this.size.set(size);
	}

	public SimpleStringProperty sizeProperty() {

		return this.size;
	}

	public int getTestQuantity() {

		return this.testQuantity.get();
	}

	public void setTestQuantity(int quantity) {

		this.testQuantity.set(quantity);
	}

	public SimpleIntegerProperty testQuantityProperty() {

		return this.testQuantity;
	}

	public int getOfferedQuantity() {

		return this.offeredQuantity.get();
	}

	public void setOfferedQuantity(int quantity) {

		this.offeredQuantity.set(quantity);
	}

	public SimpleIntegerProperty offeredQuantityProperty() {

		return this.offeredQuantity;
	}

	public String getHeatNumber() {

		return this.heatNumber.get();
	}

	public void setHeatNumber(String heatNumber) {

		this.heatNumber.set(heatNumber);
	}

	public SimpleStringProperty heatNumberProperty() {

		return this.heatNumber;
	}

	public String getPlateNumber() {

		return this.plateNumber.get();
	}

	public void setPlateNumber(String plateNumber) {

		this.plateNumber.set(plateNumber);
	}

	public SimpleStringProperty plateNumberProperty() {

		return this.plateNumber;
	}

	public String getSpecification() {

		return this.specification.get();
	}

	public void setSpecification(String specification) {

		this.specification.set(specification);
	}

	public SimpleStringProperty specificationProperty() {

		return this.specification;
	}

	public String getCustomer() {

		return this.customer.get();
	}

	public void setCustomer(String customer) {

		this.customer.set(customer);
	}

	public SimpleStringProperty customerProperty() {

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

	public String getLaboratory() {

		return this.laboratory.get();
	}

	public void setLaboratory(String laboratory) {

		this.laboratory.set(laboratory);
	}

	public SimpleStringProperty laboratoryProperty() {

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

	public String getResult() {

		return this.result.get();
	}

	public void setResult(String result) {

		this.result.set(result);
	}

	public SimpleStringProperty resultProperty() {

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

	public Set<MaterialTestMap> getTests() {

		return this.tests;
	}

	public void setTests(Set<MaterialTestMap> tests) {

		this.tests = tests;
	}

	public void resetMaterialMaster(MaterialMaster material) {

		this.ctNumber.set(material.getCtNumber());
		this.inspectionAgency.set(material.getInspectionAgency());
		this.item.set(material.getItem());
		this.size.set(material.getSize());
		this.testQuantity.set(material.getTestQuantity());
		this.offeredQuantity.set(material.getOfferedQuantity());
		this.heatNumber.set(material.getHeatNumber());
		this.plateNumber.set(material.getPlateNumber());
		this.specification.set(material.getSpecification());
		this.customer.set(material.getCustomer());
		this.equipments.set(material.getEquipments());
		this.laboratory.set(material.getLaboratory());
		this.reportNumber.set(material.getReportNumber());
		this.reportDate.set(material.getReportDate());
		this.result.set(material.getResult());
		this.remarks.set(material.getRemarks());
		this.witnessedBy.set(material.getWitnessedBy());
		this.failureReason.set(material.getFailureReason());

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
		this.tests = material.getTests();
	}

	public void resetMaterialMaster() {

		this.inspectionAgency.set("");
		this.item.set("");
		this.size.set("");
		this.testQuantity.set(1);
		this.offeredQuantity.set(1);
		this.heatNumber.set("");
		this.plateNumber.set("");
		this.specification.set("");
		this.customer.set("");
		this.equipments.set("");
		this.laboratory.set("");
		this.reportNumber.set("");
		this.reportDate.set("");
		this.result.set("");
		this.remarks.set("");
		this.witnessedBy.set("");
		this.failureReason.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}
}
