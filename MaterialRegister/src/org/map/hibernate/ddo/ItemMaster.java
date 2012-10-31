package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemMaster implements Serializable, Comparable<ItemMaster> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty itemCode;
	private SimpleStringProperty itemName;
	private SimpleStringProperty remarks;
	private String status;
	private String createdBy;
	private Date createdDate;

	public ItemMaster() {

		this.itemCode = new SimpleIntegerProperty(0);
		this.itemName = new SimpleStringProperty("");
		this.remarks = new SimpleStringProperty("");
		this.status = "TRUE";
		this.createdBy = "YSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public ItemMaster(int itemCode, String itemName, String remarks,
			String status, String createdBy, Date createdDate) {

		this.itemCode = new SimpleIntegerProperty(itemCode);
		this.itemName = new SimpleStringProperty(itemName);
		this.remarks = new SimpleStringProperty(remarks);
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public ItemMaster(ItemMaster vm) {

		setItemCode(vm.getItemCode());
		setItemName(vm.getItemName());
		setRemarks(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public int getItemCode() {

		return this.itemCode.get();
	}

	public void setItemCode(int itemCode) {

		this.itemCode.set(itemCode);
	}

	public SimpleIntegerProperty itemCodeProperty() {

		return itemCode;
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

	public String getItemName() {

		return this.itemName.get();
	}

	public void setItemName(String itemName) {

		this.itemName.set(itemName);
	}

	public SimpleStringProperty itemNameProperty() {

		return this.itemName;
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

	public void resetDetails(ItemMaster vm) {

		setItemCode(vm.getItemCode());
		setItemName(vm.getItemName());
		setRemarks(vm.getRemarks());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void reset() {

		this.itemCode.set(0);
		this.itemName.set("");
		this.remarks.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(ItemMaster o) {

		if (o instanceof ItemMaster && o != null) {
			return itemCode.getValue().compareTo(
					o.itemCodeProperty().getValue());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
