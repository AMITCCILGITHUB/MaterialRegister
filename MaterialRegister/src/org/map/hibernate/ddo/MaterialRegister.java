package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MaterialRegister implements Serializable {

	private static final long serialVersionUID = 1L;
	private SimpleIntegerProperty materialCode;
	private SimpleStringProperty ctNumber;
	private AgencyProperty inspectionAgency;
	private SpecificationProperty specification;
	private ItemProperty item;
	private SimpleStringProperty size;
	private SimpleIntegerProperty quantity;
	private SimpleStringProperty heatNumber;
	private SimpleStringProperty plateNumber;
	private String status;
	private String createdBy;
	private Date createdDate;
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

	public MaterialRegister() {

		this.materialCode = new SimpleIntegerProperty(1);
		this.ctNumber = new SimpleStringProperty("");
		this.inspectionAgency = new AgencyProperty();
		this.specification = new SpecificationProperty();
		this.item = new ItemProperty();
		this.size = new SimpleStringProperty("");
		this.quantity = new SimpleIntegerProperty(1);
		this.heatNumber = new SimpleStringProperty("");
		this.plateNumber = new SimpleStringProperty("");

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

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public MaterialRegister(MaterialRegister material) {

		this.materialCode = new SimpleIntegerProperty(material.getMaterialCode());
		this.ctNumber = new SimpleStringProperty(material.getCtNumber());
		this.inspectionAgency = new AgencyProperty(material.getInspectionAgency());
		this.specification = new SpecificationProperty(material.getSpecification());
		this.item = new ItemProperty(material.getItem());
		this.size = new SimpleStringProperty(material.getSize());
		this.quantity = new SimpleIntegerProperty(material.getQuantity());
		this.heatNumber = new SimpleStringProperty(material.getHeatNumber());
		this.plateNumber = new SimpleStringProperty(material.getPlateNumber());

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
		this.failureReason = new SimpleStringProperty(material.getFailureReason());

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
	}	
	public int getMaterialCode() {

		return this.materialCode.get();
	}

	public void setMaterialCode(int materialCode) {

		this.materialCode.set(materialCode);
	}

	public SimpleIntegerProperty MaterialCodeProperty() {

		return this.materialCode;
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

	public SpecificationMaster getSpecification() {

		return this.specification.get();
	}

	public void setSpecification(SpecificationMaster specification) {

		this.specification.set(specification);
	}

	public SpecificationProperty specificationProperty() {

		return this.specification;
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

	public static List<MaterialRegister> getMaterialRegisterList(MaterialMaster master) {
		List<MaterialRegister> registerList = new ArrayList<>();

		for (MaterialTests test : master.getMaterialTests()) {
			MaterialRegister register = new MaterialRegister();

			register.setMaterialCode(master.getMaterialCode());
			register.setCtNumber(master.getCtNumber());
			register.setInspectionAgency(master.getInspectionAgency());
			register.setSpecification(master.getSpecification());
			register.setItem(master.getItem());
			register.setSize(master.getSize());
			register.setHeatNumber(master.getHeatNumber());
			register.setPlateNumber(master.getPlateNumber());

			register.setTestCode(test.getTestCode());
			register.setSampleId(test.getSampleId());
			register.setTest(test.getTest());
			register.setCustomer(test.getCustomer());
			register.setEquipments(test.getEquipments());
			register.setLaboratory(test.getLaboratory());
			register.setReportNumber(test.getReportNumber());
			register.setReportDate(test.getReportDate());
			register.setResult(test.getResult());
			register.setRemarks(test.getRemarks());
			register.setWitnessedBy(test.getWitnessedBy());
			register.setFailureReason(test.getFailureReason());

			registerList.add(register);
		}

		return registerList;
	}

	public static List<MaterialRegister> getMaterialRegisterList(
			List<MaterialMaster> masters) {
		List<MaterialRegister> registerList = new ArrayList<>();

		for (MaterialMaster master : masters) {

			registerList.addAll(getMaterialRegisterList(master));
		}

		return registerList;
	}
}
