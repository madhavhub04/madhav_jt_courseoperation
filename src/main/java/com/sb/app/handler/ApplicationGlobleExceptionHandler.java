package com.sb.app.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sb.app.dto.ErrorDTO;
import com.sb.app.dto.ServiceResponse;
import com.sb.app.exception.CourseServiceBusinessException;

public class ApplicationGlobleExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ServiceResponse<?> handlerApplicationGlobleException(MethodArgumentNotValidException exception){
		ServiceResponse<?> serviceResponse = new ServiceResponse<>();
		List<ErrorDTO> errorDTOList = new  ArrayList<>();
		
		exception.getBindingResult().getFieldErrors()
				.forEach(error->{
					ErrorDTO errorDTO = new ErrorDTO(error.getDefaultMessage());
					errorDTOList.add(errorDTO);
				});
		serviceResponse.setStatus(HttpStatus.BAD_REQUEST);
		serviceResponse.setError(errorDTOList);
		return serviceResponse;
	}
	
	@ExceptionHandler(CourseServiceBusinessException.class)
	public ServiceResponse<?> handlerServiceException(CourseServiceBusinessException exception){
		
		ServiceResponse<?> serviceResponse = new ServiceResponse<>();
		List<ErrorDTO> errorDTOList = new ArrayList<>();
		errorDTOList.add(new ErrorDTO(exception.getMessage()));
		serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		serviceResponse.setError(errorDTOList);
		return serviceResponse;
		
	}
}
