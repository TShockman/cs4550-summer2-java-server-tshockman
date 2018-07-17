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

import com.shockman.webdev.models.Lesson;
import com.shockman.webdev.models.Module;
import com.shockman.webdev.repositories.LessonRepository;
import com.shockman.webdev.repositories.ModuleRepository;

@RestController
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@PostMapping("/api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(@PathVariable("cid") int cid, @PathVariable("mid")int mid, @RequestBody Lesson newLesson) {
		Optional<Module> opt = moduleRepository.findById(mid);
		if (opt.isPresent()) {
			Module module = opt.get();
			Date now = new Date();
			newLesson.setModule(module);
			newLesson.setCreated(now);
			newLesson.setModified(now);
			return lessonRepository.save(newLesson);
		}
		return null;
	}
	
	@DeleteMapping("/api/lesson/{id}")
	public void deleteLesson(@PathVariable("id") int id) {
		lessonRepository.deleteById(id);
	}
	
	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons() {
		return (List<Lesson>) lessonRepository.findAll();
	}
	
	@GetMapping("/api/lesson/{id}")
	public Optional<Lesson> findLessonById(@PathVariable("id") int id) {
		return lessonRepository.findById(id);
	}
	
	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("cid") int cid, @PathVariable("mid") int mid) {
		Optional<Module> opt = moduleRepository.findById(mid);
		if(opt.isPresent()) {
			Module module = opt.get();
			return module.getLessons();
		}
		return null;
	}
	
	@PutMapping("/api/lesson/{id}")
	public Lesson updateModule(@PathVariable("id") int id, @RequestBody Lesson newLesson) {
		Optional<Lesson> opt = lessonRepository.findById(id);
		if (opt.isPresent()) {
			Lesson lesson = opt.get();
			lesson.setModified(new Date());
			lesson.setTitle(newLesson.getTitle());
			lesson.setModule(newLesson.getModule());
			return lessonRepository.save(lesson);
		}
		return null;
	}
}
