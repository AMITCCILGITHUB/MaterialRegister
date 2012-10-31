package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MaterialMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	private SimpleStringProperty ctNumber;
	private AgencyProperty inspectionAgency;
	private SpecificationProperty specification;
	private ItemProperty item;
	private SimpleStringProperty size;
	private SimpleIntegerProperty quantity;
	private SimpleStringProperty heatNumber;
	private SimpleStringProperty plateNumber;
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
	private String status;
	private String createdBy;
	private Date createdDate;

	@Override
	public String toString() {

		return "[1]" + size.get() + "[2]"
				+ specification.get().getSpecificationName() + "[3]"
				+ ctNumber.get() + "[4]" + reportNumber.get() + "[5]"
				+ reportDate.get() + "[6]"
				+ laboratory.get().getLaboratoryName();
	}

	public MaterialMaster() {

		this.ctNumber = new SimpleStringProperty();
		this.inspectionAgency = new AgencyProperty();
		this.item = new ItemProperty();
		this.size = new SimpleStringProperty("");
		this.quantity = new SimpleIntegerProperty(1);
		this.heatNumber = new SimpleStringProperty("");
		this.plateNumber = new SimpleStringProperty("");
		this.sampleId = new SimpleStringProperty("");
		this.test = new TestProperty();
		this.specification = new SpecificationProperty();
		this.customer = new CustomerProperty();
		this.equipments = new SimpleStringProperty("");
		this.laboratory = new LaboratoryProperty();
		this.reportNumber = new SimpleStringProperty("");
		this.reportDate = new SimpleStringProperty("");
		this.result = new ResultProperty();
		this.remarks = new SimpleStringProperty("");
		this.witnessedBy = new SimpleStringProperty("");
		this.failureReason = new SimpleStringProperty("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public MaterialMaster(String ctNumber, AgencyMaster inspectionAgency,
			ItemMaster item, String size, int quantity, String heatNumber,
			String plateNumber, TestMaster test, String sampleId,
			SpecificationMaster specification, CustomerMaster customer,
			String equipments, LaboratoryMaster laboratory,
			String reportNumber, String reportDate, ResultMaster result,
			String remarks, String witnessedBy, String failureReason,
			String status, String createdBy, Date createdDate) {

		this.ctNumber = new SimpleStringProperty(ctNumber);
		this.inspectionAgency = new AgencyProperty(inspectionAgency);
		this.item = new ItemProperty(item);
		this.size = new SimpleStringProperty(size);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.heatNumber = new SimpleStringProperty(heatNumber);
		this.plateNumber = new SimpleStringProperty(plateNumber);
		this.sampleId = new SimpleStringProperty(sampleId);
		this.test = new TestProperty(test);
		this.specification = new SpecificationProperty(specification);
		this.customer = new CustomerProperty(customer);
		this.equipments = new SimpleStringProperty(equipments);
		this.laboratory = new LaboratoryProperty(laboratory);
		this.reportNumber = new SimpleStringProperty(reportNumber);
		this.reportDate = new SimpleStringProperty(reportDate);
		this.result = new ResultProperty(result);
		this.remarks = new SimpleStringProperty(remarks);
		this.witnessedBy = new SimpleStringProperty(witnessedBy);
		this.failureReason = new SimpleStringProperty(failureReason);

		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public MaterialMaster(MaterialMaster material) {

		this.ctNumber = new SimpleStringProperty(material.getCtNumber());
		this.inspectionAgency = new AgencyProperty(
				material.getInspectionAgency());
		this.item = new ItemProperty(material.getItem());
		this.size = new SimpleStringProperty(material.getSize());
		this.quantity = new SimpleIntegerProperty(material.getQuantity());
		this.heatNumber = new SimpleStringProperty(material.getHeatNumber());
		this.plateNumber = new SimpleStringProperty(material.getPlateNumber());
		this.specification = new SpecificationProperty(
				material.getSpecification());
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

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
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

	public AgencyMaster getInspectionAgency() {

		return this.inspectionAgency.get();
	}

	public void setInspectionAgency(AgencyMaster inspectionAgency) {

		this.inspectionAgency.set(inspectionAgency);
	}

	public AgencyProperty inspectionAgencyProperty() {

		return this.inspectionAgency;
	}

	public ItemMaster getItem() {

		return this.item.get();
	}

	public void setItem(ItemMaster item) {

		this.item.set(item);
	}

	public ItemProperty itemProperty() {

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

	public int getQuantity() {

		return this.quantity.get();
	}

	public void setQuantity(int quantity) {

		this.quantity.set(quantity);
	}

	public SimpleIntegerProperty quantityProperty() {

		return this.quantity;
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

	public SpecificationMaster getSpecification() {

		return this.specification.get();
	}

	public void setSpecification(SpecificationMaster specification) {

		this.specification.set(specification);
	}

	public SpecificationProperty specificationProperty() {

		return this.specification;
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

	public void resetMaterialMaster(MaterialMaster material) {

		this.ctNumber.set(material.getCtNumber());
		this.inspectionAgency.set(material.getInspectionAgency());
		this.item.set(material.getItem());
		this.size.set(material.getSize());
		this.quantity.set(material.getQuantity());
		this.heatNumber.set(material.getHeatNumber());
		this.plateNumber.set(material.getPlateNumber());
		this.sampleId.set(material.getSampleId());
		this.test.set(material.getTest());
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
	}

	public void resetMaterialMaster() {

		this.inspectionAgency = new AgencyProperty();
		this.item = new ItemProperty();
		this.size.set("");
		this.quantity.set(1);
		this.heatNumber.set("");
		this.plateNumber.set("");
		this.sampleId.set("");
		this.test = new TestProperty();
		this.specification = new SpecificationProperty();
		this.customer = new CustomerProperty();
		this.equipments.set("");
		this.laboratory = new LaboratoryProperty();
		this.reportNumber.set("");
		this.reportDate.set("");
		this.result = new ResultProperty();
		this.remarks.set("");
		this.witnessedBy.set("");
		this.failureReason.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}
}
