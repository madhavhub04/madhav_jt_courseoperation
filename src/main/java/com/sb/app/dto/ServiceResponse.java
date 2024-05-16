package com.sb.app.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> {
	
	private HttpStatus status;
	private T response;
	
	private List<ErrorDTO> error;

	public ServiceResponse(HttpStatus status, T response) {
		this.status = status;
		this.response = response;
	}
	
	

}
