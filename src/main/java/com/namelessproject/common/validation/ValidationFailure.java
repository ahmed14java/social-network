package com.namelessproject.common.validation;

public class ValidationFailure {

	private CharSequence field;
	private CharSequence message;

	public ValidationFailure(CharSequence field, CharSequence message) {
		this.field = field;
		this.message = message;
	}

	public CharSequence getField() {
		return field;
	}

	public CharSequence getMessage() {
		return message;
	}
	
	public static ValidationFailure buildFrom(
								javax.validation.ConstraintViolation<?> javaxCV) {
		return new ValidationFailure(javaxCV.getPropertyPath().toString(),
									   javaxCV.getMessage());	
	}
}
