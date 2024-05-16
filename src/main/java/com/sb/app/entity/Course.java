package com.sb.app.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="COURSE-TBL")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int courseId;
	private String courseName;
	private String trainerName;
	private long fees;
	private String duration;
//	private Date startDate;
	private String email;
	private String contact;
}
