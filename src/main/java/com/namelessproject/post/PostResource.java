package com.namelessproject.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.namelessproject.common.validation.ValidationFailure;

@RestController
@RequestMapping("/user/{username}/post")
@CrossOrigin(origins = {"*"})
public class PostResource {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(method = RequestMethod.POST, 
				  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPost(@PathVariable("username") String username,
										@RequestBody PostEntity post) 
									    throws PostValidationException {
		postService.create(post);
	
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{key}")
							.buildAndExpand(post.getKey())
							.toUri());
	
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<PostEntity> findAllPosts(@PathVariable String username) {	
		return postService.findAllPosts(username); 
	}
		
	@ResponseBody
	@ExceptionHandler(PostValidationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<ValidationFailure> handleUserValidationException(PostValidationException e) {
		return e.getValidationFailures();
	}
}