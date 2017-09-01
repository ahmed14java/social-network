package com.namelessproject.post;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

	Iterable<PostEntity> findByUsername(final CharSequence username);
	
}
