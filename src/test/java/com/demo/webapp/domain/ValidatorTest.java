package com.demo.webapp.domain;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorTest {
	private static final Logger logger = LoggerFactory.getLogger(ValidatorTest.class);

	@Test
	public void t01_user() {
		Person person = new Person();
		person.setName("xxxxxxxx");
		person.setAge(200);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person, PersonAdd.class);

		logger.info("~~~~{}", constraintViolations);
		logger.info("~~~~{}", constraintViolations.size());
	}
}
