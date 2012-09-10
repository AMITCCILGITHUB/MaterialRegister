package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ValidationMaster implements Serializable, Comparable<ValidationMaster> {

    private static final long serialVersionUID = 1L;
    private ValidationMasterId id;
    private SimpleStringProperty validationName;
    private SimpleStringProperty validationDesc;
    private SimpleIntegerProperty validationValue;
    private String validationStatus;
    private String createdBy;
    private Date createdDate;

    public ValidationMaster() {
        this.id = new ValidationMasterId();
        this.validationName = new SimpleStringProperty("");
        this.validationDesc = new SimpleStringProperty("");
        this.validationValue = new SimpleIntegerProperty(0);
        this.validationStatus = "TRUE";
        this.createdBy = "YSTEM";
        this.createdDate = Calendar.getInstance().getTime();
    }

    public ValidationMaster(ValidationMasterId id, String validationName, String validationDesc, String validationStatus, String createdBy, Date createdDate) {
        this.id = id;
        this.validationName = new SimpleStringProperty(validationName);
        this.validationDesc = new SimpleStringProperty(validationDesc);
        this.validationValue = new SimpleIntegerProperty(0);
        this.validationStatus = validationStatus;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public ValidationMasterId getId() {
        return this.id;
    }

    public void setId(ValidationMasterId id) {
        this.id = id;
    }

    public String getValidationName() {
        return this.validationName.get();
    }

    public void setValidationName(String validationName) {
        this.validationName.set(validationName);
    }

    public SimpleStringProperty validationNameProperty() {
        return this.validationName;
    }

    public String getValidationDesc() {
        return this.validationDesc.get();
    }

    public void setValidationDesc(String validationDesc) {
        this.validationDesc.set(validationDesc);
    }

    public SimpleStringProperty validationDescProperty() {
        return this.validationDesc;
    }

    public int getValidationValue() {
        return validationValue.get();
    }

    public void setValidationValue(int validationValue) {
        this.validationValue.set(validationValue);
    }

    public SimpleIntegerProperty validationValueProperty() {
        return this.validationValue;
    }

    public String getValidationStatus() {
        return this.validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
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

    public void resetDetails(ValidationMaster vm) {
        this.id.resetDetails(vm.getId());

        setValidationName(vm.getValidationName());
        setValidationDesc(vm.getValidationDesc());

        this.validationStatus = vm.getValidationStatus();

        this.createdBy = vm.getCreatedBy();
        this.createdDate = vm.getCreatedDate();
    }

    public void reset() {
        getId().resetDetails();

        this.validationName.set("");
        this.validationDesc.set("");

        this.validationStatus = "TRUE";

        this.createdBy = "";
        this.createdDate = Calendar.getInstance().getTime();
    }

    @Override
    public int compareTo(ValidationMaster o) {
        if (o instanceof ValidationMaster && o != null) {
            if (id.compareTo(o.getId()) == 0) {
                return validationName.get().compareTo(o.getValidationName());
            } else {
                return id.compareTo(o.getId());
            }
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
