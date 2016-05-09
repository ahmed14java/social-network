package com.namelessproject.common.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
	
	public Validator validator;

	@Autowired
	public ValidationServiceImpl(Validator validator) {
		this.validator = validator;
	}
		
	public <T> Optional<List<ValidationFailure>> validate(T entity) {
		Set<ConstraintViolation<T>> violations = validator.validate(entity);
		return Optional.ofNullable(prepareResult(violations));
	}
	
	private <T> List<ValidationFailure> prepareResult(Set<ConstraintViolation<T>> violations) {
		if (violations.isEmpty()) { return null; }
		
		List<ValidationFailure> validationFailures = new ArrayList<>();
		for (ConstraintViolation<T> violation : violations) {
			validationFailures.add(ValidationFailure.buildFrom(violation));
		}			

		return validationFailures;
	}
}