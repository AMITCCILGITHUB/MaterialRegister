package org.map.hibernate.ddo;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class AgencyProperty extends SimpleObjectProperty<AgencyMaster>
		implements ObservableValue<AgencyMaster> {

	public AgencyProperty() {

		super(new AgencyMaster());
	}

	public AgencyProperty(AgencyMaster agency) {

		super(agency);
	}

	@Override
	public void set(AgencyMaster agency) {

		super.get().setAgencyCode(agency.getAgencyCode());
		super.get().setAgencyName(agency.getAgencyName());
		super.get().setRemarks(agency.getRemarks());
		super.get().setCreatedBy(agency.getCreatedBy());
		super.get().setCreatedDate(agency.getCreatedDate());
	}

	@Override
	public void bind(ObservableValue<? extends AgencyMaster> agency) {

		super.get().agencyCodeProperty()
				.bind(agency.getValue().agencyCodeProperty());
		super.get().agencyNameProperty()
				.bind(agency.getValue().agencyNameProperty());
		super.get().remarksProperty().bind(agency.getValue().remarksProperty());
	}

	@Override
	public void bindBidirectional(Property<AgencyMaster> agency) {

		super.get().agencyCodeProperty()
				.bindBidirectional(agency.getValue().agencyCodeProperty());
		super.get().agencyNameProperty()
				.bindBidirectional(agency.getValue().agencyNameProperty());
		super.get().remarksProperty()
				.bindBidirectional(agency.getValue().remarksProperty());
	}

}
