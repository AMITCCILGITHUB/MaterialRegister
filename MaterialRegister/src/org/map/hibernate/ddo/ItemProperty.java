package org.map.hibernate.ddo;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class ItemProperty extends SimpleObjectProperty<ItemMaster> implements
		ObservableValue<ItemMaster> {

	public ItemProperty() {

		super(new ItemMaster());
	}

	public ItemProperty(ItemMaster item) {

		super(item);
	}

	@Override
	public void set(ItemMaster item) {

		super.get().setItemCode(item.getItemCode());
		super.get().setItemName(item.getItemName());
		super.get().setRemarks(item.getRemarks());
		super.get().setCreatedBy(item.getCreatedBy());
		super.get().setCreatedDate(item.getCreatedDate());
	}

	@Override
	public void bind(ObservableValue<? extends ItemMaster> item) {

		super.get().itemCodeProperty().bind(item.getValue().itemCodeProperty());
		super.get().itemNameProperty().bind(item.getValue().itemNameProperty());
		super.get().remarksProperty().bind(item.getValue().remarksProperty());
	}

	@Override
	public void bindBidirectional(Property<ItemMaster> item) {

		super.get().itemCodeProperty()
				.bindBidirectional(item.getValue().itemCodeProperty());
		super.get().itemNameProperty()
				.bindBidirectional(item.getValue().itemNameProperty());
		super.get().remarksProperty()
				.bindBidirectional(item.getValue().remarksProperty());
	}

}
