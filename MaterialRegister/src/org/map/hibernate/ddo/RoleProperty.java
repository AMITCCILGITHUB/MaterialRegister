package org.map.hibernate.ddo;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class RoleProperty extends SimpleObjectProperty<RoleMaster> implements
		ObservableValue<RoleMaster> {

	public RoleProperty() {

		super(new RoleMaster());
	}

	public RoleProperty(RoleMaster role) {

		super(role);
	}

	@Override
	public void set(RoleMaster role) {

		super.get().setRoleCode(role.getRoleCode());
		super.get().setRoleName(role.getRoleName());
		super.get().setRemarks(role.getRemarks());
		super.get().setCreatedBy(role.getCreatedBy());
		super.get().setCreatedDate(role.getCreatedDate());
	}

	@Override
	public void bind(ObservableValue<? extends RoleMaster> role) {

		super.get().roleCodeProperty().bind(role.getValue().roleCodeProperty());
		super.get().roleNameProperty().bind(role.getValue().roleNameProperty());
		super.get().remarksProperty().bind(role.getValue().remarksProperty());
	}

	@Override
	public void bindBidirectional(Property<RoleMaster> role) {

		super.get().roleCodeProperty()
				.bindBidirectional(role.getValue().roleCodeProperty());
		super.get().roleNameProperty()
				.bindBidirectional(role.getValue().roleNameProperty());
		super.get().remarksProperty()
				.bindBidirectional(role.getValue().remarksProperty());
	}

}
