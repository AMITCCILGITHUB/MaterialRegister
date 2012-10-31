package org.map.hibernate.ddo;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class SpecificationProperty extends
		SimpleObjectProperty<SpecificationMaster> implements
		ObservableValue<SpecificationMaster> {

	public SpecificationProperty() {

		super(new SpecificationMaster());
	}

	public SpecificationProperty(SpecificationMaster specification) {

		super(specification);
	}

	@Override
	public void set(SpecificationMaster specification) {

		super.get().setSpecificationCode(specification.getSpecificationCode());
		super.get().setSpecificationName(specification.getSpecificationName());
		super.get().setRemarks(specification.getRemarks());
		super.get().setCreatedBy(specification.getCreatedBy());
		super.get().setCreatedDate(specification.getCreatedDate());
	}

	@Override
	public void bind(
			ObservableValue<? extends SpecificationMaster> specification) {

		super.get().specificationCodeProperty()
				.bind(specification.getValue().specificationCodeProperty());
		super.get().specificationNameProperty()
				.bind(specification.getValue().specificationNameProperty());
		super.get().remarksProperty()
				.bind(specification.getValue().remarksProperty());
	}

	@Override
	public void bindBidirectional(Property<SpecificationMaster> specification) {

		super.get()
				.specificationCodeProperty()
				.bindBidirectional(
						specification.getValue().specificationCodeProperty());
		super.get()
				.specificationNameProperty()
				.bindBidirectional(
						specification.getValue().specificationNameProperty());
		super.get().remarksProperty()
				.bindBidirectional(specification.getValue().remarksProperty());
	}

}
