package org.map.validation;

public class Validator {

	public static ValidationResult validate(ValidationType vt, String... vd) {

		switch (vt) {
			case PASSWORD:
				System.out.println(vd[0] + " " + vd[1]);
				if (vd[0] == null || vd[1] == null) {
					return ValidationResult.NULL_VALUE;
				}

				if (vd[0].length() == 0 || vd[1].length() == 0) {
					return ValidationResult.EMPTY_VALUE;
				}

				if (!vd[0].equals(vd[1])) {
					return ValidationResult.PASSWORD_DO_NOT_MATCH;
				}
			case NONEMPTY:
				if (vd[0] == null) {
					return ValidationResult.NULL_VALUE;
				}

				if (vd[0].length() == 0) {
					return ValidationResult.EMPTY_VALUE;
				}

				return ValidationResult.SUCCESS;
			default:
				return ValidationResult.FAILURE;
		}
	}
}
