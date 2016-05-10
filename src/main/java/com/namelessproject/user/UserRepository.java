package com.namelessproject.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(final CharSequence username);

	Optional<UserEntity> findByEmail(final CharSequence email);
	
	Optional<UserEntity> findByUsernameAndPassword(final CharSequence username, 
			 									   final CharSequence password);

}
