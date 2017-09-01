package com.namelessproject.common.validation;

import java.util.List;
import java.util.Optional;

public interface ValidationService {

	<T> Optional<List<ValidationFailure>> validate(T entity);
}
