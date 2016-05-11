package com.namelessproject.common.validation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.namelessproject.user.UserEntity;

public class ValidationServiceImplTest {
	
	private ValidationServiceImpl validationServiceImplSUT;
	
	private Validator validator;

	@Before
	public void fixture() {
		validator = Mockito.mock(Validator.class);
		
		validationServiceImplSUT = new ValidationServiceImpl(validator);
	}
	
	@Test
	public void shouldNotReturnListOfValidationFailuresWhenValidatorDoesntFindAnythingInvalid() {
		// Given...
		final UserEntity userEntity = new UserEntity();
		
		when(validator.validate(userEntity)).thenReturn(new HashSet<>());
		
		// When...
		Optional<List<ValidationFailure>> validate = validationServiceImplSUT.validate(userEntity);
		
		// Then...
		assertThat(validate.isPresent(), is(false));
		
		verify(validator, times(1)).validate(userEntity);
		verifyNoMoreInteractions(validator);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldReturnListOfValidationFailuresWhenValidatorFindsSomethingInvalid() {
		// Given...
		final UserEntity userEntity = new UserEntity();
		
		Path path = Mockito.mock(Path.class);
		when(path.toString()).thenReturn("path.value");

		final ConstraintViolation<UserEntity> constraintViolation = Mockito.mock(ConstraintViolation.class);
		when(constraintViolation.getPropertyPath()).thenReturn(path);
		when(constraintViolation.getMessage()).thenReturn("error.message");
		
		final HashSet<ConstraintViolation<UserEntity>> constraintViolations = new HashSet<>();
		constraintViolations.add(constraintViolation);
		
		when(validator.validate(userEntity)).thenReturn(constraintViolations);
		
		// When...
		Optional<List<ValidationFailure>> validate = validationServiceImplSUT.validate(userEntity);
		
		// Then...
		assertThat(validate.isPresent(), is(true));
		
		ValidationFailure violationFound = validate.get().get(0);

		assertThat(violationFound.getField(), is("path.value"));
		assertThat(violationFound.getMessage(), is("error.message"));
		
		verify(validator, times(1)).validate(userEntity);
		verifyNoMoreInteractions(validator);
	}
}
