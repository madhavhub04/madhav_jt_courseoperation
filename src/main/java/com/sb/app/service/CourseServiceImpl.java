package com.sb.app.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.app.dto.CourseRequestDTO;
import com.sb.app.dto.CourseResponseDTO;
import com.sb.app.entity.Course;
import com.sb.app.exception.CourseServiceBusinessException;
import com.sb.app.repository.CourseRepository;
import com.sb.app.util.AppUtils;

@Service
public class CourseServiceImpl{

	@Autowired
	private CourseRepository courseRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {
		
		Course course = AppUtils.mapDTOToEntity(courseRequestDTO);
		Course entity=null;
		logger.info("-----===>[courseService :: onboardNewCourse() started");
		try {
			logger.info("courseService :: onboardNewCourse() request to db save obj");
			entity = courseRepository.save(course);
			logger.debug("courseService :: onboardNewCourse() response");
			CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(entity);
			courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
			logger.debug("courseService :: onboardNewCourse() response {}",AppUtils.convertObjectToJson(courseResponseDTO));
			logger.info("-----===>[courseService :: onboardNewCourse() ended");
			return courseResponseDTO;
		} catch (Exception exception) {
			logger.error("courseService :: onboardNewCourse() ");
			throw new CourseServiceBusinessException(" onboardNewCourse  server method failed..");
		}
	}
	
	public List<CourseResponseDTO> getAllCourses(){
		try {
		List<Course> coursesEntity = courseRepository.findAll();
		
		return StreamSupport.stream(coursesEntity.spliterator(), false)
				.map(courseEntity->AppUtils.mapEntityToDTO(courseEntity))
				.collect(Collectors.toList());
		}catch(Exception exception) {
			throw new CourseServiceBusinessException(" getAllCourses  server method failed..");
	}
	}
	
	public CourseResponseDTO findByCourseId(Integer courseId) {
		try {
			Course courseEntity = courseRepository.findById(courseId)
					.orElseThrow(() -> new RuntimeException("CourseId not found on server ..."));
			return AppUtils.mapEntityToDTO(courseEntity);
		} catch (Exception exception) {
			throw new CourseServiceBusinessException(" findByCourseId  server method failed..");
		}
	}
	
	public void deleteById(Integer courseId) {
		logger.info("courseServiceimpl:: deleteById() started");
		try {
			logger.debug("courseServiceimpl:: deleteById() input {}",courseId);
		courseRepository.deleteById(courseId);
	}catch (Exception e) {
		logger.error("courseServiceimpl::deleteById() exception occur while deleting by id");
		// TODO: handle exception
	}
}	
	
	public CourseResponseDTO updateCourse(CourseRequestDTO courseRequestDTO, Integer courseId) {
		try {
			Course existingEntity = courseRepository.findById(courseId).orElseThrow(null);

			existingEntity.setCourseName(courseRequestDTO.getCourseName());
			existingEntity.setTrainerName(courseRequestDTO.getTrainerName());
			existingEntity.setDuration(courseRequestDTO.getDuration());
			existingEntity.setFees(courseRequestDTO.getFees());
//			existingEntity.setStartDate(courseRequestDTO.getStartDate());
			existingEntity.setContact(courseRequestDTO.getContact());
			existingEntity.setEmail(courseRequestDTO.getEmail());
			Course updateCourse = courseRepository.save(existingEntity);
			return AppUtils.mapEntityToDTO(updateCourse);
		} catch (Exception exception) {
			throw new CourseServiceBusinessException(" updateCourse  server method failed..");
		}
	}	
	
	
	
}
