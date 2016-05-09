package com.namelessproject.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namelessproject.common.validation.ValidationFailure;
import com.namelessproject.common.validation.ValidationService;

@Service
public class UserServiceImpl implements UserService {

	private ValidationService validationService;
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(final ValidationService validationService,
						   final UserRepository userRepository) {
		this.validationService = validationService;
		this.userRepository = userRepository;
	}

	@Override
	public void create(UserEntity user) throws UserValidationException {
		validateUser(user);
		userRepository.save(user);
	}

	@Override
	public UserEntity findByUsername(final CharSequence username) 
									 throws UserNotFoundException {
		final Optional<UserEntity> userEntity = 
				userRepository.findByUsername(username);
		
		return userEntity.orElseThrow(() -> new UserNotFoundException());
	}
	
	@Override
	public UserEntity findByUsernameAndPassword(final CharSequence username, 
												final CharSequence password) 
												throws UserNotFoundException {
		final Optional<UserEntity> userEntity = 
				userRepository.findByUsernameAndPassword(username, password);
		
		return userEntity.orElseThrow(() -> new UserNotFoundException());
	}

	private void validateUser(UserEntity user) throws UserValidationException {
		Optional<List<ValidationFailure>> violations = validationService.validate(user);
		if (violations.isPresent()) { throw new UserValidationException(violations.get()); }
	}
}
