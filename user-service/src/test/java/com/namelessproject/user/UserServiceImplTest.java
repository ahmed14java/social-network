package com.namelessproject.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.namelessproject.common.validation.ValidationFailure;
import com.namelessproject.common.validation.ValidationService;

public class UserServiceImplTest {

	private UserServiceImpl userServiceImplSUT;
	
	private ValidationService validationService;
	private UserRepository userRepository;
	
	@Before
	public void fixture() {
		validationService = Mockito.mock(ValidationService.class);
		userRepository = Mockito.mock(UserRepository.class);
		
		userServiceImplSUT = new UserServiceImpl(validationService, userRepository);
	}
	
	@Test
	public void shouldCreateUserWhenValidationSucceeds() throws Exception {
		// Given...
		final UserEntity userEntity = new UserEntity();
		
		when(validationService.validate(userEntity)).thenReturn(Optional.empty());
		
		// When...
		userServiceImplSUT.create(userEntity);
		
		// Then...
		verify(validationService, times(1)).validate(userEntity);
		verify(userRepository, times(1)).save(userEntity);
		
		verifyNoMoreInteractions(validationService, userRepository);
	}
	
	@Test
	public void shouldThrowUserValidationExceptionWhenValidationDoesntSucceeds() throws Exception {
		// Given...
		final UserEntity userEntity = new UserEntity();
		final List<ValidationFailure> failures = Arrays.asList(new ValidationFailure("field", "message"));
		
		when(validationService.validate(userEntity)).thenReturn(Optional.of(failures));
		
		try {
			// When...
			userServiceImplSUT.create(userEntity);
			fail();
		} catch (UserValidationException e) {
			// Then...
			verify(validationService, times(1)).validate(userEntity);
			verify(userRepository, times(0)).save(userEntity);
			
			assertThat(e.getValidationFailures(), is(failures));
			
			verifyNoMoreInteractions(validationService, userRepository);
		} 
	}	
	
	@Test
	public void shouldFindUserWhenUsernameExists() throws Exception {
		// Given...
		final CharSequence username = "username";
		final UserEntity userEntity = new UserEntity();
		
		when(userRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));

		// When...
		UserEntity retrievedUE = userServiceImplSUT.findByUsername(username);
		
		// Then...
		verify(userRepository, times(1)).findByUsername(username);

		assertThat(retrievedUE, is(userEntity));
		verifyNoMoreInteractions(validationService, userRepository);
	}	
	
	
	@Test
	public void shouldThrowUserNotFoundExceptionWhenUserIsNotFound() throws Exception {
		// Given...
		final CharSequence username = "username";
		
		when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

		// When...
		try {
			userServiceImplSUT.findByUsername(username);
			fail();
		} catch (UserNotFoundException e) {
			verify(userRepository, times(1)).findByUsername(username);
			verifyNoMoreInteractions(validationService, userRepository);	
		}
	}	
}