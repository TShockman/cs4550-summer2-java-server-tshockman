package com.shockman.webdev.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shockman.webdev.models.Course;
import com.shockman.webdev.models.User;
import com.shockman.webdev.repositories.CourseRepository;
import com.shockman.webdev.repositories.UserRepository;

@CrossOrigin(origins="*")
@RestController
public class CourseService {
	@Autowired
	CourseRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course, @RequestParam("ownerId") int ownerId) {
		Optional<User> opt = userRepository.findById(ownerId);
		
		if (opt.isPresent()) {
			User owner = opt.get();
			System.out.println("Course owner " + course.getOwner());
			Date now = new Date();
			course.setCreated(now);
			course.setModified(now);
			course.setOwner(owner);
			return repository.save(course);
		}
		return null;
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
			course.setModules(newCourse.getModules());
			course.setOwner(newCourse.getOwner());
			return repository.save(course);
		}
		return null;
	}
	
	@DeleteMapping("/api/course/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		repository.deleteById(id);
	}
}
