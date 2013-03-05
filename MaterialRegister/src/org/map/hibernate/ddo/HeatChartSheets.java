package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HeatChartSheets implements Serializable,
		Comparable<HeatChartSheets> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty heatChartSheetCode;
	private SimpleIntegerProperty sheetNumber;
	private SimpleIntegerProperty sequenceNumber;

	private MaterialMaster materialmaster;
	private MaterialTests materialtests;
	private HeatChartMaster heatchartmaster;

	private SimpleStringProperty partNumber;
	private SimpleStringProperty partName;
	private SimpleStringProperty specifiedSize;
	private SimpleStringProperty specifiedGrade;

	private String status;
	private String createdBy;
	private Date createdDate;

	public HeatChartSheets() {

		this.heatChartSheetCode = new SimpleIntegerProperty(0);
		this.sheetNumber = new SimpleIntegerProperty(1);
		this.sequenceNumber = new SimpleIntegerProperty(1);

		this.materialmaster = new MaterialMaster();
		this.materialtests = new MaterialTests();
		this.heatchartmaster = new HeatChartMaster();

		this.partNumber = new SimpleStringProperty("");
		this.partName = new SimpleStringProperty("");
		this.specifiedSize = new SimpleStringProperty("");
		this.specifiedGrade = new SimpleStringProperty("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public HeatChartSheets(HeatChartSheets hSheets) {

		this.heatChartSheetCode = new SimpleIntegerProperty(
				hSheets.getHeatChartSheetCode());

		this.sheetNumber = new SimpleIntegerProperty(hSheets.getSheetNumber());
		this.sequenceNumber = new SimpleIntegerProperty(
				hSheets.getSequenceNumber());

		this.materialmaster = new MaterialMaster(hSheets.getMaterialmaster());
		this.materialtests = new MaterialTests(hSheets.getMaterialtests());
		this.heatchartmaster = new HeatChartMaster(hSheets.getHeatchartmaster());

		this.partNumber = new SimpleStringProperty(hSheets.getPartNumber());
		this.partName = new SimpleStringProperty(hSheets.getPartName());
		this.specifiedSize = new SimpleStringProperty(
				hSheets.getSpecifiedSize());
		this.specifiedGrade = new SimpleStringProperty(
				hSheets.getSpecifiedGrade());

		this.status = hSheets.getStatus();
		this.createdBy = hSheets.getCreatedBy();
		this.createdDate = hSheets.getCreatedDate();
	}

	public int getHeatChartSheetCode() {

		return this.heatChartSheetCode.get();
	}

	public void setHeatChartSheetCode(int heatChartSheetCode) {

		this.heatChartSheetCode.set(heatChartSheetCode);
	}

	public SimpleIntegerProperty heatChartSheetCodeProperty() {

		return this.heatChartSheetCode;
	}

	public int getSheetNumber() {

		return this.sheetNumber.get();
	}

	public void setSheetNumber(int sheetNumber) {

		this.sheetNumber.set(sheetNumber);
	}

	public SimpleIntegerProperty sheetNumberProperty() {

		return this.sheetNumber;
	}

	public MaterialMaster getMaterialmaster() {

		return this.materialmaster;
	}

	public void setMaterialmaster(MaterialMaster materialmaster) {

		this.materialmaster.resetTo(materialmaster);
	}

	public MaterialTests getMaterialtests() {

		return materialtests;
	}

	public void setMaterialtests(MaterialTests materialtests) {

		this.materialtests.resetTo(materialtests);
	}

	public HeatChartMaster getHeatchartmaster() {

		return this.heatchartmaster;
	}

	public void setHeatchartmaster(HeatChartMaster heatchartmaster) {

		this.heatchartmaster.resetTo(heatchartmaster);
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

	public String getSpecifiedSize() {

		return this.specifiedSize.get();
	}

	public void setSpecifiedSize(String specifiedSize) {

		this.specifiedSize.set(specifiedSize);
	}

	public SimpleStringProperty specifiedSizeProperty() {

		return this.specifiedSize;
	}

	public String getSpecifiedGrade() {

		return this.specifiedGrade.get();
	}

	public void setSpecifiedGrade(String specifiedGrade) {

		this.specifiedGrade.set(specifiedGrade);
	}

	public SimpleStringProperty specifiedGradeProperty() {

		return this.specifiedGrade;
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

	public void resetTo(HeatChartSheets hSheets) {

		this.heatChartSheetCode = new SimpleIntegerProperty(
				hSheets.getHeatChartSheetCode());

		this.sheetNumber = new SimpleIntegerProperty(hSheets.getSheetNumber());
		this.sequenceNumber = new SimpleIntegerProperty(
				hSheets.getSequenceNumber());

		this.materialmaster = new MaterialMaster(hSheets.getMaterialmaster());
		this.materialtests = new MaterialTests(hSheets.getMaterialtests());
		this.heatchartmaster = new HeatChartMaster(hSheets.getHeatchartmaster());

		this.partNumber = new SimpleStringProperty(hSheets.getPartNumber());
		this.partName = new SimpleStringProperty(hSheets.getPartName());
		this.specifiedSize = new SimpleStringProperty(
				hSheets.getSpecifiedSize());
		this.specifiedGrade = new SimpleStringProperty(
				hSheets.getSpecifiedGrade());

		this.status = hSheets.getStatus();
		this.createdBy = hSheets.getCreatedBy();
		this.createdDate = hSheets.getCreatedDate();
	}

	public void clean() {

		this.heatChartSheetCode.set(0);

		this.sheetNumber.set(1);
		this.sequenceNumber.set(1);

		this.materialmaster.clean();
		this.materialtests.clean();
		this.heatchartmaster.clean();

		this.partNumber.set("");
		this.partName.set("");
		this.specifiedSize.set("");
		this.specifiedGrade.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(HeatChartSheets o) {

		if (o instanceof HeatChartSheets && o != null) {
			return this.heatChartSheetCode.getValue().compareTo(
					o.getHeatChartSheetCode());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
