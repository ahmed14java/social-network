package com.claudineirdj.namelessproject.post.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.namelessproject.PostServiceApplication;
import com.namelessproject.post.PostEntity;
import com.namelessproject.post.PostRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(PostServiceApplication.class)
@WebIntegrationTest
public class PostRepositoryIntegration {

	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void shouldInterceptAndPersistPost() throws Exception {
		PostEntity postEntity = new PostEntity();
		postEntity.setContent("blah blah blah");
		postEntity.setTitle("testééé asdasdasd 564210,156 *&¨asdkajdl");
		postEntity.setUsername("username");
		
		postRepository.save(postEntity);
		
		System.out.println(postEntity.getKey());
		System.out.println(postEntity.getCreationDate());
	}
}
