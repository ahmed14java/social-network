package com.namelessproject.user;

public interface UserService {

	void create(final UserEntity user) throws UserValidationException;
	
	UserEntity findByUsername(final CharSequence username)
							  throws UserNotFoundException;
	
	UserEntity findByUsernameAndPassword(final CharSequence username, 
										 final CharSequence password) 
										 throws UserNotFoundException;
}