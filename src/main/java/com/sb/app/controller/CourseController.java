package com.sb.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.app.dto.CourseRequestDTO;
import com.sb.app.dto.CourseResponseDTO;
import com.sb.app.dto.ServiceResponse;
import com.sb.app.entity.Course;
import com.sb.app.service.CourseServiceImpl;

@RestController
@RequestMapping("/course")
public class CourseController {

//	@Autowired
	private CourseServiceImpl courseServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	public CourseController(CourseServiceImpl courseServiceImpl) {
		this.courseServiceImpl=courseServiceImpl;
	}
	
	@PostMapping(value = "/new")
	public ServiceResponse<CourseResponseDTO> addNew(@RequestBody @Valid CourseRequestDTO courseRequestDTO){
		ServiceResponse<CourseResponseDTO> serviceResponse = new ServiceResponse<>();
		CourseResponseDTO newCourse = courseServiceImpl.onboardNewCourse(courseRequestDTO);
		serviceResponse.setStatus(HttpStatus.OK);
		serviceResponse.setResponse(newCourse);
		return serviceResponse;
//		return new ResponseEntity<CourseResponseDTO>(newCourse,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/all")
	public ServiceResponse<List<CourseResponseDTO>> findAllCourses(){
		List<CourseResponseDTO> courseResponseDTO = courseServiceImpl.getAllCourses();
		return new ServiceResponse<>(HttpStatus.OK,courseResponseDTO);
	}
	
	@GetMapping(value = "/one/{courseId}")
	public ServiceResponse<CourseResponseDTO> findOne(@PathVariable Integer courseId){
		 CourseResponseDTO responseByPath = courseServiceImpl.findByCourseId(courseId);
		 return new ServiceResponse<>(HttpStatus.OK,responseByPath);
	}
	
	@DeleteMapping(value = "/{courseId}")
	public ServiceResponse<?> deleteById(@PathVariable  Integer courseId){
		courseServiceImpl.deleteById(courseId);
		return new ServiceResponse(HttpStatus.NOT_FOUND,"");
	}
	
	@PutMapping(value = "/{courseId}")
	public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId
											,@RequestBody CourseRequestDTO courRequestDTO){
		CourseResponseDTO courseResponseDTO = courseServiceImpl.updateCourse(courRequestDTO, courseId);
		return new  ServiceResponse<CourseResponseDTO>(HttpStatus.OK,courseResponseDTO);
	}
	
	/*
	 * @GetMapping("/log") public String setLogging() {
	 * 
	 * logger.trace("this is TRACE message");
	 * logger.debug("this is DEBUG messsage"); 
	 * logger.info("this is INFO message");
	 * logger.warn("this  is WARN message"); 
	 * logger.error("this is ERROR message");
	 * return "logger successfully getting..."; }
	 */
	
	
}
