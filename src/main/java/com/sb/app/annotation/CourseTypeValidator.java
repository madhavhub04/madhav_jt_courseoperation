package com.sb.app.annotation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseTypeValidator implements ConstraintValidator<CourseTypeValidation, String>{

	@Override
	public boolean isValid(String courseType, ConstraintValidatorContext context) {
		List list = Arrays.asList("LIVE","RECORDING");
		return list.contains(courseType);
	}

	
}
