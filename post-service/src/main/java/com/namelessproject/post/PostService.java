package com.namelessproject.post;

public interface PostService {

	void create(final PostEntity post) throws PostValidationException;
	
	Iterable<PostEntity> findAllPosts(final CharSequence username);
}
