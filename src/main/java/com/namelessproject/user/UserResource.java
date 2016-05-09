package com.namelessproject.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.namelessproject.common.validation.ValidationFailure;

@RestController
@RequestMapping("/user")
public class UserResource {

	private UserService userService;
	
	@Autowired
	public UserResource(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST, 
				  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@RequestBody UserEntity user) 
									    throws UserValidationException {
		userService.create(user);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{username}")
							.buildAndExpand(user.getUsername())
							.toUri());
		
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{username}", method = RequestMethod.GET)
	public UserEntity findByUsername(@PathVariable String username) 
									 throws UserNotFoundException {
		return userService.findByUsername(username); 
	}
	
	/** This method will be removed **/
	@RequestMapping(method = RequestMethod.GET)
	public UserEntity validateUsernameAndPassword(@RequestParam("username") final String username, 
								   				  @RequestParam("password") final String password) 
					                              throws UserNotFoundException {
		return userService.findByUsernameAndPassword(username, password);
	}
	
	@ResponseBody
	@ExceptionHandler(UserValidationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<ValidationFailure> handleUserValidationException(UserValidationException e) {
		return e.getValidationFailures();
	}
}
