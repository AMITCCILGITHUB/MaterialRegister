package org.map.hibernate.ddo;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class TestProperty extends SimpleObjectProperty<TestMaster> implements
		ObservableValue<TestMaster> {

	public TestProperty() {

		super(new TestMaster());
	}

	public TestProperty(TestMaster test) {

		super(test);
	}

	@Override
	public void set(TestMaster test) {

		super.get().setTestCode(test.getTestCode());
		super.get().setTestName(test.getTestName());
		super.get().setRemarks(test.getRemarks());
		super.get().setCreatedBy(test.getCreatedBy());
		super.get().setCreatedDate(test.getCreatedDate());
	}

	@Override
	public void bind(ObservableValue<? extends TestMaster> test) {

		super.get().testCodeProperty().bind(test.getValue().testCodeProperty());
		super.get().testNameProperty().bind(test.getValue().testNameProperty());
		super.get().remarksProperty().bind(test.getValue().remarksProperty());
	}

	@Override
	public void bindBidirectional(Property<TestMaster> test) {

		super.get().testCodeProperty()
				.bindBidirectional(test.getValue().testCodeProperty());
		super.get().testNameProperty()
				.bindBidirectional(test.getValue().testNameProperty());
		super.get().remarksProperty()
				.bindBidirectional(test.getValue().remarksProperty());
	}

}
