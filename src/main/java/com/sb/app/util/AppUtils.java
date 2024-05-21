package com.sb.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.app.dto.CourseRequestDTO;
import com.sb.app.dto.CourseResponseDTO;
import com.sb.app.entity.Course;

public class AppUtils {
	//DTO TO ENTITY
	
	public  static Course mapDTOToEntity(CourseRequestDTO courseRequestDTO) {
		
		Course course = new Course();
		course.setCourseName(courseRequestDTO.getCourseName());
		course.setFees(courseRequestDTO.getFees());
		course.setTrainerName(courseRequestDTO.getTrainerName());
		course.setDuration(courseRequestDTO.getDuration());
		course.setContact(courseRequestDTO.getContact());
		course.setEmail(courseRequestDTO.getEmail());
//		course.setStartDate(courseRequestDTO.getStartDate());
		return course;
	}
	
	// ENTITY TO DTO
	
	public static CourseResponseDTO mapEntityToDTO(Course course) {
		
		CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
		courseResponseDTO.setCourseId(course.getCourseId());
		courseResponseDTO.setCourseName(course.getCourseName());
		courseResponseDTO.setDuration(course.getDuration());
		courseResponseDTO.setFees(course.getFees());
		courseResponseDTO.setTrainerName(course.getTrainerName());
		courseResponseDTO.setContact(course.getContact());
		courseResponseDTO.setEmail(course.getEmail());
//		courseResponseDTO.setStartDate(course.getStartDate());
		return courseResponseDTO;
		
	}

	public static String convertObjectToJson(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			
		}
		return null;
	}
	
}
