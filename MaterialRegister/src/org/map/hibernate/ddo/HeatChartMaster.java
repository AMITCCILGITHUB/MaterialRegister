package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.SimpleStringProperty;

public class HeatChartMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	private SimpleStringProperty chartNumber;
	private SimpleStringProperty equipment;
	private SimpleStringProperty customer;
	private SimpleStringProperty poDetails;
	private SimpleStringProperty drawingNumber;
	private SimpleStringProperty surveyor;
	private String status;
	private String createdBy;
	private Date createdDate;
	private Set<HeatChartSheets> heatchartsheets = new HashSet<>();

	public HeatChartMaster() {

		this.chartNumber = new SimpleStringProperty("");
		this.equipment = new SimpleStringProperty("");
		this.customer = new SimpleStringProperty("");
		this.poDetails = new SimpleStringProperty("");
		this.drawingNumber = new SimpleStringProperty("");
		this.surveyor = new SimpleStringProperty("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public HeatChartMaster(String chartNumber, String equipment,
			String customer, String poDetails, String drawingNumber,
			String surveyor, String status, String createdBy, Date createdDate) {

		this.chartNumber = new SimpleStringProperty(chartNumber);
		this.equipment = new SimpleStringProperty(equipment);
		this.customer = new SimpleStringProperty(customer);
		this.poDetails = new SimpleStringProperty(poDetails);
		this.drawingNumber = new SimpleStringProperty(drawingNumber);
		this.surveyor = new SimpleStringProperty(surveyor);

		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public HeatChartMaster(String chartNumber, String equipment,
			String customer, String poDetails, String drawingNumber,
			String surveyor, String status, String createdBy, Date createdDate,
			Set<HeatChartSheets> heatchartsheets) {

		this.chartNumber = new SimpleStringProperty(chartNumber);
		this.equipment = new SimpleStringProperty(equipment);
		this.customer = new SimpleStringProperty(customer);
		this.poDetails = new SimpleStringProperty(poDetails);
		this.drawingNumber = new SimpleStringProperty(drawingNumber);
		this.surveyor = new SimpleStringProperty(surveyor);

		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.heatchartsheets = heatchartsheets;
	}

	public String getChartNumber() {

		return this.chartNumber.get();
	}

	public void setChartNumber(String chartNumber) {

		this.chartNumber.set(chartNumber);
	}

	public SimpleStringProperty chartNumberProperty() {

		return this.chartNumber;
	}

	public String getEquipment() {

		return this.equipment.get();
	}

	public void setEquipment(String equipment) {

		this.equipment.set(equipment);
	}

	public SimpleStringProperty equipmentProperty() {

		return this.equipment;
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

	public String getPoDetails() {

		return this.poDetails.get();
	}

	public void setPoDetails(String poDetails) {

		this.poDetails.set(poDetails);
	}

	public SimpleStringProperty poDetailsProperty() {

		return this.poDetails;
	}

	public String getDrawingNumber() {

		return this.drawingNumber.get();
	}

	public void setDrawingNumber(String drawingNumber) {

		this.drawingNumber.set(drawingNumber);
	}

	public SimpleStringProperty drawingNumberProperty() {

		return this.drawingNumber;
	}

	public String getSurveyor() {

		return this.surveyor.get();
	}

	public void setSurveyor(String surveyor) {

		this.surveyor.set(surveyor);
	}

	public SimpleStringProperty surveyorProperty() {

		return this.surveyor;
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

	public Set<HeatChartSheets> getHeatchartsheets() {

		return this.heatchartsheets;
	}

	public void setHeatchartsheets(Set<HeatChartSheets> heatchartsheets) {

		this.heatchartsheets = heatchartsheets;
	}

	public void resetHeatChart(HeatChartMaster hcMaster) {

		this.chartNumber.set(hcMaster.getChartNumber());
		this.equipment.set(hcMaster.getEquipment());
		this.customer.set(hcMaster.getCustomer());
		this.poDetails.set(hcMaster.getPoDetails());
		this.drawingNumber.set(hcMaster.getDrawingNumber());
		this.surveyor.set(hcMaster.getDrawingNumber());

		this.status = hcMaster.getStatus();
		this.createdBy = hcMaster.getCreatedBy();
		this.createdDate = hcMaster.getCreatedDate();
		this.heatchartsheets = hcMaster.getHeatchartsheets();
	}
}
