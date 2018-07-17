package com.shockman.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shockman.webdev.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer>{

}
