package com.namelessproject.common.validation.constraint;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.namelessproject.user.UserEntity;
import com.namelessproject.user.UserRepository;

public class UniqueValidatorTest {

	private UserRepository userRepository;
	private Unique unique;
	
	private UniqueValidator uniqueValidator;

	@Before
	public void fixture() {
		userRepository = Mockito.mock(UserRepository.class);
		unique = Mockito.mock(Unique.class);
		
		uniqueValidator = new UniqueValidator();
		uniqueValidator.setUserRepository(userRepository);
	}
	
	@Test
	public void shouldAcknowledgeUsernameIsUnique() throws Exception {
		// Given...
		final CharSequence username = "frodobagging";

		when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
		when(unique.propertyName()).thenReturn("username");

		// When...
		uniqueValidator.initialize(unique);
		Boolean valid = uniqueValidator.isValid((Serializable) username, null);
	
		// Then...
		assertThat(valid, is(true));
		
		verify(userRepository, times(1)).findByUsername(username);
		verifyNoMoreInteractions(userRepository);
	}
	
	@Test
	public void shouldAcknowledgeUsernameIsNotUnique() throws Exception {
		// Given...
		final CharSequence username = "frodobagging";

		when(userRepository.findByUsername(username)).thenReturn(Optional.of(new UserEntity()));
		when(unique.propertyName()).thenReturn("username");

		// When...
		uniqueValidator.initialize(unique);
		Boolean valid = uniqueValidator.isValid((Serializable) username, null);
	
		// Then...
		assertThat(valid, is(false));
		
		verify(userRepository, times(1)).findByUsername(username);		
		verifyNoMoreInteractions(userRepository);
	}
	
	@Test
	public void shouldAcknowledgeEmailIsUnique() throws Exception {
		// Given...
		final CharSequence email = "frodo@shire.com";

		when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
		when(unique.propertyName()).thenReturn("email");

		// When...
		uniqueValidator.initialize(unique);
		Boolean valid = uniqueValidator.isValid((Serializable) email, null);
	
		// Then...
		assertThat(valid, is(true));
		
		verify(userRepository, times(1)).findByEmail(email);		
		verifyNoMoreInteractions(userRepository);
	}
	
	@Test
	public void shouldAcknowledgeEmailIsNotUnique() throws Exception {
		// Given...
		final CharSequence email = "frodo@shire.com";

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(new UserEntity()));
		when(unique.propertyName()).thenReturn("email");

		// When...
		uniqueValidator.initialize(unique);
		Boolean valid = uniqueValidator.isValid((Serializable) email, null);
	
		// Then...
		assertThat(valid, is(false));
		
		verify(userRepository, times(1)).findByEmail(email);				
		verifyNoMoreInteractions(userRepository);
	}
}
