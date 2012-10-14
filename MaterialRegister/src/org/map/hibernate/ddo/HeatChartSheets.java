package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HeatChartSheets implements Serializable,
		Comparable<HeatChartSheets> {

	private static final long serialVersionUID = 1L;
	private HeatChartSheetsId id;
	private MaterialMaster materialmaster;
	private HeatChartMaster heatchartmaster;
	private SimpleIntegerProperty sequenceNumber;
	private SimpleStringProperty partNumber;
	private SimpleStringProperty partName;
	private SimpleStringProperty speciedSize;
	private SimpleStringProperty speciedGrade;
	private String status;
	private String createdBy;
	private Date createdDate;

	public HeatChartSheets() {

		this.id = new HeatChartSheetsId();
		this.materialmaster = new MaterialMaster();
		this.heatchartmaster = new HeatChartMaster();

		this.sequenceNumber = new SimpleIntegerProperty(1);
		this.partNumber = new SimpleStringProperty("");
		this.partName = new SimpleStringProperty("");
		this.speciedSize = new SimpleStringProperty("");
		this.speciedGrade = new SimpleStringProperty("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public HeatChartSheets(HeatChartSheetsId id, MaterialMaster materialmaster,
			HeatChartMaster heatchartmaster, int sequenceNumber,
			String partNumber, String partName, String speciedSize,
			String speciedGrade, String status, String createdBy,
			Date createdDate) {

		this.id = id;
		this.materialmaster = materialmaster;
		this.heatchartmaster = heatchartmaster;

		this.sequenceNumber = new SimpleIntegerProperty(sequenceNumber);
		this.partNumber = new SimpleStringProperty(partNumber);
		this.partName = new SimpleStringProperty(partName);
		this.speciedSize = new SimpleStringProperty(speciedSize);
		this.speciedGrade = new SimpleStringProperty(speciedGrade);

		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public HeatChartSheetsId getId() {

		return this.id;
	}

	public void setId(HeatChartSheetsId id) {

		this.id = id;
	}

	public MaterialMaster getMaterialmaster() {

		return this.materialmaster;
	}

	public void setMaterialmaster(MaterialMaster materialmaster) {

		this.materialmaster = materialmaster;
	}

	public HeatChartMaster getHeatchartmaster() {

		return this.heatchartmaster;
	}

	public void setHeatchartmaster(HeatChartMaster heatchartmaster) {

		this.heatchartmaster = heatchartmaster;
	}

	public int getSequenceNumber() {

		return this.sequenceNumber.get();
	}

	public void setSequenceNumber(int sequenceNumber) {

		this.sequenceNumber.set(sequenceNumber);
	}

	public SimpleIntegerProperty sequenceNumberProperty() {

		return this.sequenceNumber;
	}

	public String getPartNumber() {

		return this.partNumber.get();
	}

	public void setPartNumber(String partNumber) {

		this.partNumber.set(partNumber);
	}

	public SimpleStringProperty partNumberProperty() {

		return this.partNumber;
	}

	public String getPartName() {

		return this.partName.get();
	}

	public void setPartName(String partName) {

		this.partName.set(partName);
	}

	public SimpleStringProperty partNameProperty() {

		return this.partName;
	}

	public String getSpeciedSize() {

		return this.speciedSize.get();
	}

	public void setSpeciedSize(String speciedSize) {

		this.speciedSize.set(speciedSize);
	}

	public SimpleStringProperty speciedSizeProperty() {

		return this.speciedSize;
	}

	public String getSpeciedGrade() {

		return this.speciedGrade.get();
	}

	public void setSpeciedGrade(String speciedGrade) {

		this.speciedGrade.set(speciedGrade);
	}

	public SimpleStringProperty speciedGradeProperty() {

		return this.speciedGrade;
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

	public void resetHeatChartSheets(HeatChartSheets hSheets) {

		this.id.resetHeatChartSheetsId(hSheets.getId());

		this.sequenceNumber = new SimpleIntegerProperty(
				hSheets.getSequenceNumber());
		this.partNumber = new SimpleStringProperty(hSheets.getPartNumber());
		this.partName = new SimpleStringProperty(hSheets.getPartName());
		this.speciedSize = new SimpleStringProperty(hSheets.getSpeciedSize());
		this.speciedGrade = new SimpleStringProperty(hSheets.getSpeciedGrade());

		this.status = hSheets.getStatus();
		this.createdBy = hSheets.getCreatedBy();
		this.createdDate = hSheets.getCreatedDate();
	}

	@Override
	public int compareTo(HeatChartSheets o) {

		if (o instanceof HeatChartSheets && o != null) {
			if (this.id.equals(o.getId())) {
				return sequenceNumber.getValue().compareTo(
						o.sequenceNumberProperty().getValue());
			} else {
				return this.id.compareTo(o.getId());
			}
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
