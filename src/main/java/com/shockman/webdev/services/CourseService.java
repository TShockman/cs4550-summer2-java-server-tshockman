package com.shockman.webdev.services;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shockman.webdev.models.Course;
import com.shockman.webdev.repositories.CourseRepository;

@RestController
public class CourseService {
	@Autowired
	CourseRepository repository;
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		Date now = new Date();
		course.setCreated(now);
		course.setModified(now);
		return repository.save(course);
	}
	
	@GetMapping("/api/course")
	public List<Course> findAllCourses() {
		return (List<Course>) repository.findAll();
	}
	
	@GetMapping("/api/course/{id}")
	public Optional<Course> findCourseById(@PathVariable("id") int id) {
		return repository.findById(id);
	}
	
	@PutMapping("/api/course/{id}")
	public Course updateCourse(@PathVariable("id") int id, @RequestBody Course newCourse) {
		Optional<Course> optional = repository.findById(id);
		if (optional.isPresent()) {
			Course course = optional.get();
			course.setModified(new Date());
			course.setTitle(newCourse.getTitle());
			return repository.save(course);
		}
		return null;
	}
	
	@DeleteMapping("/api/course/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		repository.deleteById(id);
	}
}
