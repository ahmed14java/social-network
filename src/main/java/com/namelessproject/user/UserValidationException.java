package com.namelessproject.user;

import java.util.List;

import com.namelessproject.common.validation.ValidationFailure;

public class UserValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private List<ValidationFailure> validationFailures;

	public UserValidationException(List<ValidationFailure> validationFailures) {
		this.validationFailures = validationFailures;
	}
	
	public List<ValidationFailure> getValidationFailures() {
		return validationFailures;
	}
}
