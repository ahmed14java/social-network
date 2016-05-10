package com.namelessproject.common.validation.constraint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.namelessproject.UserServiceApplication;
import com.namelessproject.user.UserEntity;
import com.namelessproject.user.UserRepository;
import com.namelessproject.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(UserServiceApplication.class)
public class UniqueValidatorTest {

	@Autowired
	private UserService userRepository;
	
	@Test
	public void test() throws Exception {
		UserEntity ue = new UserEntity();
		ue.setUsername("username");
		ue.setEmail("email");
		ue.setPassword("password");
		
		userRepository.create(ue);
	}
}
