package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MaterialTestMap implements Serializable, Comparable<MaterialTestMap> {

    private static final long serialVersionUID = 1L;
    private MaterialTestMapId id;
    private SimpleStringProperty testName;
    private SimpleIntegerProperty testValue;
    private String status;
    private String createdBy;
    private Date createdDate;

    @Override
    public String toString() {
        return (testValue.get() == 1) ? testName.get() : "";
    }

    public MaterialTestMap() {
        this.id = new MaterialTestMapId();
        this.testName = new SimpleStringProperty("");
        this.testValue = new SimpleIntegerProperty(0);
        this.status = "TRUE";
        this.createdBy = "SYSTEM";
        this.createdDate = Calendar.getInstance().getTime();
    }

    public MaterialTestMap(MaterialTestMapId id, String testName, int testValue, String status, String createdBy, Date createdDate) {
        this.id = id;
        this.testName = new SimpleStringProperty(testName);
        this.testValue = new SimpleIntegerProperty(testValue);
        this.status = status;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public MaterialTestMapId getId() {
        return this.id;
    }

    public void setId(MaterialTestMapId id) {
        this.id = id;
    }

    public int getTestValue() {
        return this.testValue.get();
    }

    public void setTestValue(int testValue) {
        this.testValue.set(testValue);
    }

    public SimpleIntegerProperty TestValueProperty() {
        return this.testValue;
    }

    public String getTestName() {
        return testName.get();
    }

    public void setTestName(String testName) {
        this.testName.set(testName);
    }

    public SimpleStringProperty TestNameProperty() {
        return this.testName;
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

    @Override
    public int compareTo(MaterialTestMap o) {
        return id.compareTo(o.getId());
    }
}
