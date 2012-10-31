package org.map.hibernate.ddo;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class LaboratoryProperty extends SimpleObjectProperty<LaboratoryMaster>
		implements ObservableValue<LaboratoryMaster> {

	public LaboratoryProperty() {

		super(new LaboratoryMaster());
	}

	public LaboratoryProperty(LaboratoryMaster laboratory) {

		super(laboratory);
	}

	@Override
	public void set(LaboratoryMaster laboratory) {

		super.get().setLaboratoryCode(laboratory.getLaboratoryCode());
		super.get().setLaboratoryName(laboratory.getLaboratoryName());
		super.get().setRemarks(laboratory.getRemarks());
		super.get().setCreatedBy(laboratory.getCreatedBy());
		super.get().setCreatedDate(laboratory.getCreatedDate());
	}

	@Override
	public void bind(ObservableValue<? extends LaboratoryMaster> laboratory) {

		super.get().laboratoryCodeProperty()
				.bind(laboratory.getValue().laboratoryCodeProperty());
		super.get().laboratoryNameProperty()
				.bind(laboratory.getValue().laboratoryNameProperty());
		super.get().remarksProperty()
				.bind(laboratory.getValue().remarksProperty());
	}

	@Override
	public void bindBidirectional(Property<LaboratoryMaster> laboratory) {

		super.get()
				.laboratoryCodeProperty()
				.bindBidirectional(
						laboratory.getValue().laboratoryCodeProperty());
		super.get()
				.laboratoryNameProperty()
				.bindBidirectional(
						laboratory.getValue().laboratoryNameProperty());
		super.get().remarksProperty()
				.bindBidirectional(laboratory.getValue().remarksProperty());
	}

}
