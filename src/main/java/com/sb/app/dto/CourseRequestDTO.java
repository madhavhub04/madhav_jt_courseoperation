package com.sb.app.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {
	@NotBlank(message="course name should't NULL OR EMPTY")
	private String courseName;
	private String trainerName;
	@Min(value = 1500,message="course fees can't less than 1500")
	@Max(value = 5000,message="course fees can't more than 5000")
	private long fees;
	private String duration;
	@Past(message="start date can't before cate from current date")
	private Date startDate;
	@Email(message="Invalid email id")
	private String email;
	@Pattern(regexp = "^[0-9]{10}$")
	private String contact;
}
