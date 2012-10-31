package org.map.hibernate.ddo;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class CustomerProperty extends SimpleObjectProperty<CustomerMaster>
		implements ObservableValue<CustomerMaster> {

	public CustomerProperty() {

		super(new CustomerMaster());
	}

	public CustomerProperty(CustomerMaster customer) {

		super(customer);
	}

	@Override
	public void set(CustomerMaster customer) {

		super.get().setCustomerCode(customer.getCustomerCode());
		super.get().setCustomerName(customer.getCustomerName());
		super.get().setRemarks(customer.getRemarks());
		super.get().setCreatedBy(customer.getCreatedBy());
		super.get().setCreatedDate(customer.getCreatedDate());
	}

	@Override
	public void bind(ObservableValue<? extends CustomerMaster> customer) {

		super.get().customerCodeProperty()
				.bind(customer.getValue().customerCodeProperty());
		super.get().customerNameProperty()
				.bind(customer.getValue().customerNameProperty());
		super.get().remarksProperty()
				.bind(customer.getValue().remarksProperty());
	}

	@Override
	public void bindBidirectional(Property<CustomerMaster> customer) {

		super.get().customerCodeProperty()
				.bindBidirectional(customer.getValue().customerCodeProperty());
		super.get().customerNameProperty()
				.bindBidirectional(customer.getValue().customerNameProperty());
		super.get().remarksProperty()
				.bindBidirectional(customer.getValue().remarksProperty());
	}

}
