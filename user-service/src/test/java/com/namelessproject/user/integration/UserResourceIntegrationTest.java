package com.namelessproject.user.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.namelessproject.common.validation.ValidationFailure;
import com.namelessproject.user.UserEntity;

public class UserResourceIntegrationTest extends UserIntegrationTest {

	private RestTemplate restTemplate;
	
	@Before
	public void fixture() {
		restTemplate = new TestRestTemplate();
	}
	
	@Test
	public void shouldCreateUserWhenPOSTWithAttributesAreValid() {
		// Given...
		final UserEntity userToBeCreated = createUserEntity();

		// When...
		final ResponseEntity<?> serviceResponse = postUserEntity(userToBeCreated);
		
		// Then...
		assertThat(serviceResponse.getStatusCode(), is(HttpStatus.OK));
		assertThat(serviceResponse.getBody(), is(nullValue()));
		
		// Make sure user was created
		final HttpHeaders httpHeaders = serviceResponse.getHeaders();
		final ResponseEntity<UserEntity> createdUser = restTemplate.getForEntity(httpHeaders.getLocation(), UserEntity.class);
		
		assertThat(createdUser.getStatusCode(), is(HttpStatus.OK));
		assertThat(createdUser.getBody(), is(notNullValue()));
	}
	
	@Test
	public void shouldRetrieveCreatedUserWhenGETByUsername() {
		// Given...
		final UserEntity userToBeCreated = createUserEntity();

		final URI userLocation = postUserEntity(userToBeCreated).getHeaders().getLocation();

		// When...
		final ResponseEntity<UserEntity> serviceResponse = restTemplate.getForEntity(userLocation, UserEntity.class);
		
		// Then...
		assertThat(userLocation.toString(), is(BASE_URL + "/" + userToBeCreated.getUsername()));

		assertThat(serviceResponse.getStatusCode(), is(HttpStatus.OK));
		
		final UserEntity retrievedUser = serviceResponse.getBody();
		assertThat(retrievedUser.getId(), is(notNullValue()));
		assertThat(retrievedUser.getUsername(), is(userToBeCreated.getUsername()));
		assertThat(retrievedUser.getEmail(), is(userToBeCreated.getEmail()));
		assertThat(retrievedUser.getPassword(), is(userToBeCreated.getPassword()));
	}
	
	@Test
	public void shouldNotCreateUserWhenPOSTWithInvalidAttributes() {
		// Given...
		final UserEntity userToBeCreated = createUserEntity();
		postUserEntity(userToBeCreated);
		
		final UserEntity duplicateUser = userToBeCreated;
		
		// When...
		final ResponseEntity<?> serviceResponse = postUserEntity(duplicateUser, ValidationFailure[].class);

		// Then...
		assertThat(serviceResponse.getStatusCode(), is(HttpStatus.BAD_REQUEST));
		assertThat(serviceResponse.getBody(), is(notNullValue()));

		final ValidationFailure[] validationFailures = (ValidationFailure[]) serviceResponse.getBody();
		
		assertThat(validationFailures.length, is(2));
		assertContainsValidationFailure(validationFailures, "username", "user.username.not.unique");
		assertContainsValidationFailure(validationFailures, "email", "user.email.not.unique");
	}
	
	@Test
	public void shouldNotCreateUserWhenPOSTWithEmptyAttributes() {
		// Given...
		final UserEntity userToBeCreated = new UserEntity();
		
		// When...
		final ResponseEntity<?> serviceResponse = postUserEntity(userToBeCreated, ValidationFailure[].class);

		// Then...
		assertThat(serviceResponse.getStatusCode(), is(HttpStatus.BAD_REQUEST));
		assertThat(serviceResponse.getBody(), is(notNullValue()));

		final ValidationFailure[] validationFailures = (ValidationFailure[]) serviceResponse.getBody();
		
		assertThat(validationFailures.length, is(3));
		assertContainsValidationFailure(validationFailures, "username", "user.username.cannot.be.empty");
		assertContainsValidationFailure(validationFailures, "email", "user.email.cannot.be.empty");
		assertContainsValidationFailure(validationFailures, "password", "user.password.cannot.be.empty");
	}
	
	@Test
	public void shouldReturnNotFoundWhenGETInexistingUser() {
		// Given...
		final String inexistingUserLocation = BASE_URL + "/inexistingUser";

		// When...
		final ResponseEntity<UserEntity> serviceResponse = restTemplate.getForEntity(inexistingUserLocation, UserEntity.class);
		
		// Then...
		assertThat(serviceResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}

	private UserEntity createUserEntity() {
		return createUserEntity(generateRandomString(10), generateRandomString(10), generateRandomString(10));
	}
	
	private UserEntity createUserEntity(final String username, final String email, final String password) {
		final UserEntity userEntity = new UserEntity();
		userEntity.setUsername(username);
		userEntity.setEmail(email);
		userEntity.setPassword(password);
		return userEntity;
	}
	
	private ResponseEntity<?> postUserEntity(UserEntity userEntity) {
		return postUserEntity(userEntity, ResponseEntity.class);
	}
	
	private ResponseEntity<?> postUserEntity(UserEntity userEntity, Class<?> responseType) {
		return restTemplate.postForEntity(BASE_URL, userEntity, responseType);
	}
}