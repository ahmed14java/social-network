package com.namelessproject.user.integration;

import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.namelessproject.UserServiceApplication;
import com.namelessproject.common.validation.ValidationFailure;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(UserServiceApplication.class)
@WebIntegrationTest
public abstract class UserIntegrationTest {

	protected final String BASE_URL = "http://localhost:50000/user";
	
	protected String generateRandomString() {
		return generateRandomString(10);
	}
	
	protected String generateRandomString(Integer length) {
		return UUID.randomUUID().toString().replace('-', 'a').substring(0, length);
	}
	
	protected void assertContainsValidationFailure(ValidationFailure[] validationFailures, String field, String errorMessage) {
		for (ValidationFailure validationFailure : validationFailures) {
			if (field.equalsIgnoreCase(validationFailure.getField().toString()) &&
				errorMessage.equalsIgnoreCase(validationFailure.getMessage().toString())) 
				return;
		}
		fail("Validation for [" + field + "] with message + [" + errorMessage + "] is not present");
	}
}