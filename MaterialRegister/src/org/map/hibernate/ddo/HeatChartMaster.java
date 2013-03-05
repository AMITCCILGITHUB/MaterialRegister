package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HeatChartMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty heatChartCode;
	private SimpleStringProperty chartNumber;
	private SimpleStringProperty equipment;
	private SimpleStringProperty customer;
	private SimpleStringProperty poDetails;
	private SimpleStringProperty drawingNumber;
	private SimpleStringProperty surveyor;
	private SimpleStringProperty tagNumber;
	private String status;
	private String createdBy;
	private Date createdDate;
	private List<HeatChartSheets> heatChartSheets;

	public HeatChartMaster() {

		this.heatChartCode = new SimpleIntegerProperty(0);
		this.chartNumber = new SimpleStringProperty("");
		this.equipment = new SimpleStringProperty("");
		this.customer = new SimpleStringProperty("");
		this.poDetails = new SimpleStringProperty("");
		this.drawingNumber = new SimpleStringProperty("");
		this.surveyor = new SimpleStringProperty("");
		this.tagNumber = new SimpleStringProperty("");

		heatChartSheets = new ArrayList<>();

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public HeatChartMaster(HeatChartMaster hcMaster) {

		this.heatChartCode = new SimpleIntegerProperty(
				hcMaster.getHeatChartCode());
		this.chartNumber = new SimpleStringProperty(hcMaster.getChartNumber());
		this.equipment = new SimpleStringProperty(hcMaster.getEquipment());
		this.customer = new SimpleStringProperty(hcMaster.getCustomer());
		this.poDetails = new SimpleStringProperty(hcMaster.getPoDetails());
		this.drawingNumber = new SimpleStringProperty(
				hcMaster.getDrawingNumber());
		this.surveyor = new SimpleStringProperty(hcMaster.getDrawingNumber());
		this.tagNumber = new SimpleStringProperty(hcMaster.getTagNumber());

		this.heatChartSheets = new ArrayList<>(hcMaster.getHeatChartSheets());

		this.status = hcMaster.getStatus();
		this.createdBy = hcMaster.getCreatedBy();
		this.createdDate = hcMaster.getCreatedDate();
	}

	public int getHeatChartCode() {

		return this.heatChartCode.get();
	}

	public void setHeatChartCode(int heatChartCode) {

		this.heatChartCode.set(heatChartCode);
	}

	public SimpleIntegerProperty heatChartCodeProperty() {

		return this.heatChartCode;
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

	public String getTagNumber() {

		return this.tagNumber.get();
	}

	public void setTagNumber(String tagNumber) {

		this.tagNumber.set(tagNumber);
	}

	public SimpleStringProperty tagNumberProperty() {

		return this.tagNumber;
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

	public List<HeatChartSheets> getHeatChartSheets() {

		return this.heatChartSheets;
	}

	public void setHeatChartSheets(List<HeatChartSheets> heatChartSheets) {

		this.heatChartSheets = heatChartSheets;
	}

	public void resetTo(HeatChartMaster hcMaster) {

		this.heatChartCode.set(hcMaster.getHeatChartCode());
		this.chartNumber.set(hcMaster.getChartNumber());
		this.equipment.set(hcMaster.getEquipment());
		this.customer.set(hcMaster.getCustomer());
		this.poDetails.set(hcMaster.getPoDetails());
		this.drawingNumber.set(hcMaster.getDrawingNumber());
		this.surveyor.set(hcMaster.getDrawingNumber());
		this.tagNumber.set(hcMaster.getTagNumber());

		this.heatChartSheets = new ArrayList<>(hcMaster.getHeatChartSheets());

		this.status = hcMaster.getStatus();
		this.createdBy = hcMaster.getCreatedBy();
		this.createdDate = hcMaster.getCreatedDate();
	}

	public void clean() {

		this.heatChartCode.set(0);
		this.chartNumber.set("");
		this.equipment.set("");
		this.customer.set("");
		this.poDetails.set("");
		this.drawingNumber.set("");
		this.surveyor.set("");
		this.tagNumber.set("");

		this.heatChartSheets.clear();

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}
}
