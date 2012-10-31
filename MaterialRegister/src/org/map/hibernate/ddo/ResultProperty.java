package org.map.hibernate.ddo;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class ResultProperty extends SimpleObjectProperty<ResultMaster>
		implements ObservableValue<ResultMaster> {

	public ResultProperty() {

		super(new ResultMaster());
	}

	public ResultProperty(ResultMaster result) {

		super(result);
	}

	@Override
	public void set(ResultMaster result) {

		super.get().setResultCode(result.getResultCode());
		super.get().setResultName(result.getResultName());
		super.get().setRemarks(result.getRemarks());
		super.get().setCreatedBy(result.getCreatedBy());
		super.get().setCreatedDate(result.getCreatedDate());
	}

	@Override
	public void bind(ObservableValue<? extends ResultMaster> result) {

		super.get().resultCodeProperty()
				.bind(result.getValue().resultCodeProperty());
		super.get().resultNameProperty()
				.bind(result.getValue().resultNameProperty());
		super.get().remarksProperty().bind(result.getValue().remarksProperty());
	}

	@Override
	public void bindBidirectional(Property<ResultMaster> result) {

		super.get().resultCodeProperty()
				.bindBidirectional(result.getValue().resultCodeProperty());
		super.get().resultNameProperty()
				.bindBidirectional(result.getValue().resultNameProperty());
		super.get().remarksProperty()
				.bindBidirectional(result.getValue().remarksProperty());
	}

}
