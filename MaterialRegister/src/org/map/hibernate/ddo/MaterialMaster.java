package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MaterialMaster implements Serializable {

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
	private List<MaterialTests> materialTests;

	private StringBuilder validationMessage = new StringBuilder();

	public MaterialMaster() {

		this.materialCode = new SimpleIntegerProperty(1);
		this.ctNumber = new SimpleStringProperty("");
		this.inspectionAgency = new AgencyProperty();
		this.specification = new SpecificationProperty();
		this.item = new ItemProperty();
		this.size = new SimpleStringProperty("");
		this.quantity = new SimpleIntegerProperty(1);
		this.heatNumber = new SimpleStringProperty("");
		this.plateNumber = new SimpleStringProperty("");

		materialTests = new ArrayList<>();

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public MaterialMaster(MaterialMaster material) {

		this.materialCode = new SimpleIntegerProperty(
				material.getMaterialCode());
		this.ctNumber = new SimpleStringProperty(material.getCtNumber());
		this.inspectionAgency = new AgencyProperty(
				material.getInspectionAgency());
		this.specification = new SpecificationProperty(
				material.getSpecification());
		this.item = new ItemProperty(material.getItem());
		this.size = new SimpleStringProperty(material.getSize());
		this.quantity = new SimpleIntegerProperty(material.getQuantity());
		this.heatNumber = new SimpleStringProperty(material.getHeatNumber());
		this.plateNumber = new SimpleStringProperty(material.getPlateNumber());

		this.materialTests = new ArrayList<>(material.getMaterialTests());

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

	public List<MaterialTests> getMaterialTests() {

		return this.materialTests;
	}

	public void setMaterialTests(List<MaterialTests> materialTests) {

		this.materialTests = materialTests;
	}

	public void resetTo(MaterialMaster material) {

		this.materialCode.set(material.getMaterialCode());
		this.ctNumber.set(material.getCtNumber());
		this.inspectionAgency.set(material.getInspectionAgency());
		this.specification.set(material.getSpecification());
		this.item.set(material.getItem());
		this.size.set(material.getSize());
		this.quantity.set(material.getQuantity());
		this.heatNumber.set(material.getHeatNumber());
		this.plateNumber.set(material.getPlateNumber());

		this.materialTests = new ArrayList<>(material.getMaterialTests());

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
	}

	public void clean() {

		this.materialCode.set(0);
		this.inspectionAgency.get().clean();
		this.specification.get().clean();
		this.item.get().clean();
		this.size.set("");
		this.quantity.set(1);
		this.heatNumber.set("");
		this.plateNumber.set("");

		for (MaterialTests tests : this.materialTests) {

			tests.clean();
		}

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public boolean isInvalid() {

		validationMessage = new StringBuilder();

		if (this.inspectionAgency.get().getAgencyName().trim().length() <= 0) {

			validationMessage.append("* Inspection agency is empty." + "\n");
		}

		if (this.specification.get().getSpecificationName().trim().length() <= 0) {

			validationMessage.append("* Specification is empty." + "\n");
		}

		if (this.item.get().getItemName().trim().length() <= 0) {

			validationMessage.append("* Item is empty." + "\n");
		}

		if (this.quantity.get() == 0) {

			validationMessage.append("* Quantity is equal to zero." + "\n");
		}

		if (validationMessage.length() <= 0) {

			for (MaterialTests tests : this.materialTests) {

				if (tests.getSampleId().trim().length() > 0) {

					if (tests.isInvalid()) {

						validationMessage.append("Sample ID: "
								+ tests.getSampleId() + "\n"
								+ tests.getValidationMessage());
					}
				} else {

					validationMessage.append("* Sample ID is empty.");
				}
			}
		}

		return (validationMessage.length() > 0);
	}

	public String getValidationMessage() {

		return validationMessage.toString();
	}
}
