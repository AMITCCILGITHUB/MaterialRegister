package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ValidationMasterId implements Serializable,
		Comparable<ValidationMasterId> {

	private static final long serialVersionUID = 1L;
	private SimpleStringProperty validationType;
	private SimpleIntegerProperty validationCode;

	public ValidationMasterId() {

		this.validationType = new SimpleStringProperty("");
		this.validationCode = new SimpleIntegerProperty(0);
	}

	public ValidationMasterId(String validationType, int validationCode) {

		this.validationType = new SimpleStringProperty(validationType);
		this.validationCode = new SimpleIntegerProperty(validationCode);
	}

	public String getValidationType() {

		return this.validationType.get();
	}

	public void setValidationType(String validationType) {

		this.validationType.set(validationType);
	}

	public SimpleStringProperty validationTypeProperty() {

		return this.validationType;
	}

	public int getValidationCode() {

		return this.validationCode.get();
	}

	public void setValidationCode(int validationCode) {

		this.validationCode.set(validationCode);
	}

	public SimpleIntegerProperty validationCodeProperty() {

		return this.validationCode;
	}

	public void resetDetails(ValidationMasterId id) {

		setValidationType(id.getValidationType());
		setValidationCode(id.getValidationCode());
	}

	public void resetDetails() {

		this.validationType.set("");
		this.validationCode.set(0);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ValidationMasterId other = (ValidationMasterId) obj;
		if (!Objects.equals(this.validationType, other.validationType)) {
			return false;
		}
		if (!Objects.equals(this.validationCode, other.validationCode)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {

		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.validationType);
		hash = 83 * hash + Objects.hashCode(this.validationCode);
		return hash;
	}

	@Override
	public int compareTo(ValidationMasterId o) {

		if (this.validationType.get().compareTo(o.getValidationType()) == 0) {
			return validationCode.getValue().compareTo(
					o.validationCodeProperty().getValue());
		} else {
			return this.validationType.get().compareTo(o.getValidationType());
		}
	}
}
