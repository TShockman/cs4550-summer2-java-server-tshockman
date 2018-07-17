package com.shockman.webdev.services;

import java.util.Date;
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
import com.shockman.webdev.models.Module;
import com.shockman.webdev.repositories.CourseRepository;
import com.shockman.webdev.repositories.ModuleRepository;

@RestController
public class ModuleService {
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@PostMapping("/api/course/{cid}/module")
	public Module createModule(@PathVariable("cid") int cid, @RequestBody Module newModule) {
		Optional<Course> opt = courseRepository.findById(cid);
		
		if(opt.isPresent()) {
			Course course = opt.get();
			Date now = new Date();
			newModule.setCourse(course);
			newModule.setCreated(now);
			newModule.setModified(now);
			return moduleRepository.save(newModule);
		}
		return null;
	}
	
	@GetMapping("/api/module")
	public List<Module> findAllModules() {
		return (List<Module>) moduleRepository.findAll();
	}
	
	@GetMapping("/api/module/{id}")
	public Optional<Module> findModuleById(@PathVariable("id") int id) {
		return moduleRepository.findById(id);
	}
	
	@DeleteMapping("/api/module/{id}")
	public void deleteModule(@PathVariable("id") int id) {
		moduleRepository.deleteById(id);
	}
	
	@PutMapping("/api/module/{id}")
	public Module updateModule(@PathVariable("id") int id, @RequestBody Module newModule) {
		Optional<Module> optional = moduleRepository.findById(id);
		if (optional.isPresent()) {
			Module module = optional.get();
			module.setModified(new Date());
			module.setTitle(newModule.getTitle());
			module.setCourse(newModule.getCourse());
			return moduleRepository.save(module);
		}
		return null;
	}
	
	@GetMapping("/api/course/{cid}/module")
	List<Module> findAllModulesForCourse(@PathVariable("cid") int cid) {
		Optional<Course> opt = courseRepository.findById(cid);
		if (opt.isPresent()) {
			Course course = opt.get();
			return course.getModules();
		}
		return null;
	}
}
