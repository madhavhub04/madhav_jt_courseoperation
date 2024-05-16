package com.sb.app.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
	private int courseId;
	private String courseName;
	private String trainerName;
	private long fees;
	private String duration;
	private Date startDate;
	private String email;
	private String contact;
	private String courseUniqueCode;
}
