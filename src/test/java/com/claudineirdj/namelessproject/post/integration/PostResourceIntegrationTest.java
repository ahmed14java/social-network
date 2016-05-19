package com.claudineirdj.namelessproject.post.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.namelessproject.PostServiceApplication;
import com.namelessproject.common.validation.ValidationFailure;
import com.namelessproject.post.PostEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(PostServiceApplication.class)
@WebIntegrationTest
public class PostResourceIntegrationTest {

	protected final String BASE_URL = "http://localhost:50002/user/{username}/post";
	
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
	
	private RestTemplate restTemplate;
	
	@Before
	public void fixture() {
		restTemplate = new TestRestTemplate();
	}
	
	@Test
	public void shouldCreateUserWhenPOSTWithAttributesAreValid() {
		// Given...
		final PostEntity postToBeCreated = new PostEntity();
		postToBeCreated.setTitle("Th!s is          a title");
		postToBeCreated.setContent(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum finibus accumsan ligula, "
				+ "a dictum lectus pulvinar vel. Phasellus at ipsum mollis, "
				+ "feugiat augue ut, tincidunt magna. Morbi nec placerat quam. Nullam accumsan dui "
				+ "quis orci aliquet dictum. Proin vel enim non risus ornare maximus. Morbi in sapien "
				+ "magna. Donec quis est ut diam accumsan aliquet. Quisque et eleifend justo. Integer hendrerit sollicitudin "
				+ "ex eu vulputate. Cras a eleifend felis. In finibus lacus sed lacinia mollis. "
				+ "Curabitur feugiat nisi lacus, quis congue ligula consectetur quis. Nullam tempus risus quis congue tempus. Nullam nisi eros"
				+ ", lobortis ac luctus ac, consequat ac metus. Nunc dapibus est nec dui elementum, eu accumsan dolor malesuada."
				+ "Aenean id lacinia ipsum, eu varius nibh. Vestibulum dignissim, lorem quis gravida ultrices, lorem felis laoreet tortor, sit "
				+ "amet consectetur augue arcu a lacus. Mauris rutrum nec justo eget imperdiet. Sed finibus vitae diam id elementum. Etiam quis "
				+ "magna. Donec quis est ut diam accumsan aliquet. Quisque et eleifend justo. Integer hendrerit sollicitudin "
				+ "ex eu vulputate. Cras a eleifend felis. In finibus lacus sed lacinia mollis. "
				+ "Curabitur feugiat nisi lacus, quis congue ligula consectetur quis. Nullam tempus risus quis congue tempus. Nullam nisi eros"
				+ ", lobortis ac luctus ac, consequat ac metus. Nunc dapibus est nec dui elementum, eu accumsan dolor malesuada."
				+ "Aenean id lacinia ipsum, eu varius nibh. Vestibulum dignissim, lorem quis gravida ultrices, lorem felis laoreet tortor, sit "
				+ "amet consectetur augue arcu a lacus. Mauris rutrum nec justo eget imperdiet. Sed finibus vitae diam id elementum. Etiam quis "
				+ "magna. Donec quis est ut diam accumsan aliquet. Quisque et eleifend justo. Integer hendrerit sollicitudin "
				+ "ex eu vulputate. Cras a eleifend felis. In finibus lacus sed lacinia mollis. "
				+ "Curabitur feugiat nisi lacus, quis congue ligula consectetur quis. Nullam tempus risus quis congue tempus. Nullam nisi eros"
				+ ", lobortis ac luctus ac, consequat ac metus. Nunc dapibus est nec dui elementum, eu accumsan dolor malesuada."
				+ "Aenean id lacinia ipsum, eu varius nibh. Vestibulum dignissim, lorem quis gravida ultrices, lorem felis laoreet tortor, sit "
				+ "amet consectetur augue arcu a lacus. Mauris rutrum nec justo eget imperdiet. Sed finibus vitae diam id elementum. Etiam quis "
				+ "magna. Donec quis est ut diam accumsan aliquet. Quisque et eleifend justo. Integer hendrerit sollicitudin "
				+ "ex eu vulputate. Cras a eleifend felis. In finibus lacus sed lacinia mollis. "
				+ "Curabitur feugiat nisi lacus, quis congue ligula consectetur quis. Nullam tempus risus quis congue tempus. Nullam nisi eros"
				+ ", lobortis ac luctus ac, consequat ac metus. Nunc dapibus est nec dui elementum, eu accumsan dolor malesuada."
				+ "Aenean id lacinia ipsum, eu varius nibh. Vestibulum dignissim, lorem quis gravida ultrices, lorem felis laoreet tortor, sit "
				+ "amet consectetur augue arcu a lacus. Mauris rutrum nec justo eget imperdiet. Sed finibus vitae diam id elementum. Etiam quis "
				+ "fringilla massa, in porta metus. Nulla tincidunt maximus fringilla. Proin egestas dapibus lacus eget dignissim. Duis mauris sapien, "
				+ "ullamcorper non pharetra vel, sollicitudin id est. Fusce sed sapien ut arcu laoreet porta ut sed urna. Ut non commodo odio. Suspendisse "
				+ "dignissim, urna in efficitur feugiat, lacus erat rhoncus leo, a fermentum lectus odio a metus. Curabitur pharetra facilisis ultricies. Curabitur "
				+ "consequat sit amet dui sed lobortis. Nam convallis ut nisi nec viverra.");
		postToBeCreated.setUsername("frodo");
		
		// When...
		ResponseEntity<?> serviceResponse = postPostEntity(postToBeCreated);
		serviceResponse = postPostEntity(postToBeCreated);
		serviceResponse = postPostEntity(postToBeCreated);
		
		// Then...
		assertThat(serviceResponse.getStatusCode(), is(HttpStatus.OK));
		assertThat(serviceResponse.getBody(), is(nullValue()));
		
		// Make sure user was created
//		final HttpHeaders httpHeaders = serviceResponse.getHeaders();
//		final ResponseEntity<UserEntity> createdUser = restTemplate.getForEntity(httpHeaders.getLocation(), UserEntity.class);
//		
//		assertThat(createdUser.getStatusCode(), is(HttpStatus.OK));
//		assertThat(createdUser.getBody(), is(notNullValue()));
	}
	
	private ResponseEntity<?> postPostEntity(PostEntity postEntity) {
		return postPostEntity(postEntity, ResponseEntity.class);
	}
	
	private ResponseEntity<?> postPostEntity(PostEntity postEntity, Class<?> responseType) {
		UriComponents uriComponents =
			    UriComponentsBuilder.fromUriString(BASE_URL).build()
			     .expand(postEntity.getUsername()).encode();
		
		return restTemplate.postForEntity(uriComponents.toString(), postEntity, responseType);
	}
}