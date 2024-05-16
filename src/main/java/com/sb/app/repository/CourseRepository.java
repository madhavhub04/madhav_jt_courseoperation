package com.sb.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.app.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

}
