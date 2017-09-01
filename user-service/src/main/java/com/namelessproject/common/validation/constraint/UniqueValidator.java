package com.namelessproject.common.validation.constraint;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.namelessproject.user.UserRepository;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, Serializable> {

	private UserRepository userRepository;
	private String propertyName;
	
	@Override
	public void initialize(Unique constraint) {
		propertyName = constraint.propertyName();
	}

	@Override
	public boolean isValid(Serializable target, ConstraintValidatorContext context) {
		Optional<?> retrievedEntity = null;
		switch (propertyName) {
			case "username":
				retrievedEntity = userRepository.findByUsername((CharSequence) target);
				break;
			case "email": 
				retrievedEntity = userRepository.findByEmail((CharSequence) target);
				break;
			default:
				throw new RuntimeException(propertyName + " not mapped at the validator");
		}
		return !retrievedEntity.isPresent();
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
