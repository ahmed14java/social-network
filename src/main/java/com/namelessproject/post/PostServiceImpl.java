package com.namelessproject.post;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	
	@Autowired	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	@Transactional
	public void create(PostEntity post) throws PostValidationException {
		postRepository.save(post);
		
		post.generateKey();
		
		postRepository.save(post);
	}
	
}
